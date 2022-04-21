/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.config.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.AbstractSharedListener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import io.seata.common.exception.NotSupportYetException;
import io.seata.common.util.CollectionUtils;
import io.seata.common.util.StringUtils;
import io.seata.config.AbstractConfiguration;
import io.seata.config.Configuration;
import io.seata.config.ConfigurationChangeEvent;
import io.seata.config.ConfigurationChangeListener;
import io.seata.config.ConfigurationFactory;
import io.seata.config.ConfigurationKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * The type Nacos configuration.
 *
 * @author slievrly
 * 获取 dataId 属性值的顺序
 * @see #getLatestConfig(String, String, long)
 * 1. SystemProperties
 * 2. seataConfig: registry.conf 中定义的 conf.nacos.dataId 属性名指定的文件
 * 3. 从 nacos 注册中心获取: 本地缓存或者远程
 */
public class NacosConfiguration extends AbstractConfiguration {
    private static volatile NacosConfiguration instance;

    private static final Logger LOGGER = LoggerFactory.getLogger(NacosConfiguration.class);
    private static final String DEFAULT_GROUP = "SEATA_GROUP";
    private static final String DEFAULT_DATA_ID = "seata.properties";
    private static final String GROUP_KEY = "group";
    private static final String PRO_SERVER_ADDR_KEY = "serverAddr";
    private static final String NACOS_DATA_ID_KEY = "dataId";
    private static final String ENDPOINT_KEY = "endpoint";
    private static final String CONFIG_TYPE = "nacos";
    private static final String DEFAULT_NAMESPACE = "";
    private static final String PRO_NAMESPACE_KEY = "namespace";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String ACCESS_KEY = "accessKey";
    private static final String SECRET_KEY = "secretKey";
    // registry.conf 文件的内容
    private static final Configuration FILE_CONFIG = ConfigurationFactory.CURRENT_FILE_INSTANCE;
    /**
     * @see io.seata.config.nacos.NacosConfiguration#getInstance
     * 在构造中初始化 nacos-config-service
     * @see com.alibaba.nacos.client.config.NacosConfigService
     */
    private static volatile ConfigService configService;
    private static final int MAP_INITIAL_CAPACITY = 8;
    private static final ConcurrentMap<String, ConcurrentMap<ConfigurationChangeListener, NacosListener>> CONFIG_LISTENERS_MAP
            = new ConcurrentHashMap<>(MAP_INITIAL_CAPACITY);
    // 加载 registry.conf 中定义的 config.nacos.dataId 对应属性值
    // 该值表示了 nacos 注册中心的一个配置文件, 此配置文件定义了 TC 的一些配置
    // 文件名默认为 seata.properties
    private static volatile Properties seataConfig = new Properties();

    /**
     * Get instance of NacosConfiguration
     *
     * @return instance
     */
    public static NacosConfiguration getInstance() {
        if (instance == null) {
            synchronized (NacosConfiguration.class) {
                if (instance == null) {
                    instance = new NacosConfiguration();
                }
            }
        }
        return instance;
    }

    /**
     * 1. Instantiates a new Nacos configuration.
     * 2. 构建 {@link com.alibaba.nacos.client.config.NacosConfigService}
     * 3. init seata config
     */
    private NacosConfiguration() {
        if (configService == null) {
            try {
                // nacos 注册中心的配置
                // nacos: com.alibaba.nacos.client.config.NacosConfigService 实例
                configService = NacosFactory.createConfigService(getConfigProperties());
                // init #seataConfig,
                initSeataConfig();
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getLatestConfig(String dataId, String defaultValue, long timeoutMills) {
        String value = getConfigFromSysPro(dataId); // 先从 sys-prop 获取
        if (value != null) {
            // 系统有定义, 直接返回
            LOGGER.info("[getLatestConfig]从System-properties获取 dataId: [{}] 配置为: [{}]", dataId, value);
            return value;
        }

        // 再从 #seataConfig 获取, 实际就是加载的 registry.conf 中定义的 config.nacos.dataId 对应属性值
        // 该值表示了 nacos 注册中心的一个配置文件
        value = seataConfig.getProperty(dataId);

        // 如果以上都为定义
        if (null == value) {
            try {
                // 从 nacos 注册中心获取配置: 可能是本地的缓存, 或者 HTTP 请求远程获取
                // nacos-group: 默认 DEFAULT_GROUP, 或者自定义
                value = configService.getConfig(dataId, getNacosGroup(), timeoutMills);
                LOGGER.info("[getLatestConfig]利用 nacos-config-service(com.alibaba.nacos.client.config.NacosConfigService) " +
                        "获取到的 dataId: [{}] 属性值: [{}]", dataId, value);
            } catch (NacosException exx) {
                LOGGER.error(exx.getErrMsg());
            }
        } else {
            LOGGER.info("[getLatestConfig]从 seata-config 获取到的 dataId: [{}] 值为: [{}]", dataId, value);
        }

        return value == null ? defaultValue : value;
    }

    @Override
    public boolean putConfig(String dataId, String content, long timeoutMills) {
        boolean result = false;
        try {
            if (!seataConfig.isEmpty()) {
                seataConfig.setProperty(dataId, content);
                result = configService.publishConfig(getNacosDataId(), getNacosGroup(), getSeataConfigStr());
            } else {
                result = configService.publishConfig(dataId, getNacosGroup(), content);
            }
        } catch (NacosException exx) {
            LOGGER.error(exx.getErrMsg());
        }
        return result;
    }

    @Override
    public boolean putConfigIfAbsent(String dataId, String content, long timeoutMills) {
        throw new NotSupportYetException("not support atomic operation putConfigIfAbsent");
    }

    @Override
    public boolean removeConfig(String dataId, long timeoutMills) {
        boolean result = false;
        try {
            if (!seataConfig.isEmpty()) {
                seataConfig.remove(dataId);
                result = configService.publishConfig(getNacosDataId(), getNacosGroup(), getSeataConfigStr());
            } else {
                result = configService.removeConfig(dataId, getNacosGroup());
            }
        } catch (NacosException exx) {
            LOGGER.error(exx.getErrMsg());
        }
        return result;
    }

    @Override
    public void addConfigListener(String dataId, ConfigurationChangeListener listener) {
        if (StringUtils.isBlank(dataId) || listener == null) {
            return;
        }
        try {
            NacosListener nacosListener = new NacosListener(dataId, listener);
            CONFIG_LISTENERS_MAP.computeIfAbsent(dataId, key -> new ConcurrentHashMap<>())
                    .put(listener, nacosListener);
            configService.addListener(dataId, getNacosGroup(), nacosListener);
        } catch (Exception exx) {
            LOGGER.error("add nacos listener error:{}", exx.getMessage(), exx);
        }
    }

    @Override
    public void removeConfigListener(String dataId, ConfigurationChangeListener listener) {
        if (StringUtils.isBlank(dataId) || listener == null) {
            return;
        }
        Set<ConfigurationChangeListener> configChangeListeners = getConfigListeners(dataId);
        if (CollectionUtils.isNotEmpty(configChangeListeners)) {
            for (ConfigurationChangeListener entry : configChangeListeners) {
                if (listener.equals(entry)) {
                    NacosListener nacosListener = null;
                    Map<ConfigurationChangeListener, NacosListener> configListeners = CONFIG_LISTENERS_MAP.get(dataId);
                    if (configListeners != null) {
                        nacosListener = configListeners.get(listener);
                        configListeners.remove(entry);
                    }
                    if (nacosListener != null) {
                        configService.removeListener(dataId, getNacosGroup(), nacosListener);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public Set<ConfigurationChangeListener> getConfigListeners(String dataId) {
        Map<ConfigurationChangeListener, NacosListener> configListeners = CONFIG_LISTENERS_MAP.get(dataId);
        if (CollectionUtils.isNotEmpty(configListeners)) {
            return configListeners.keySet();
        } else {
            return null;
        }
    }

    /**
     * @see #FILE_CONFIG {@link ConfigurationFactory#CURRENT_FILE_INSTANCE} 默认情况实际就是 registry.conf 内容
     * 构建 {@link NacosConfigService} 的构造参数.
     * 主要属性: namespace
     */
    private static Properties getConfigProperties() {
        Properties properties = new Properties();

        // server-addr
        if (System.getProperty(ENDPOINT_KEY) != null) { // endpoint
            properties.setProperty(ENDPOINT_KEY, System.getProperty(ENDPOINT_KEY));
            properties.put(ACCESS_KEY, Objects.toString(System.getProperty(ACCESS_KEY), ""));
            properties.put(SECRET_KEY, Objects.toString(System.getProperty(SECRET_KEY), ""));
        } else if (System.getProperty(PRO_SERVER_ADDR_KEY) != null) { // serverAddr
            properties.setProperty(PRO_SERVER_ADDR_KEY, System.getProperty(PRO_SERVER_ADDR_KEY));
        } else {
            String address = FILE_CONFIG.getConfig(getNacosAddrFileKey()); // config.nacos.serverAddr
            if (address != null) {
                properties.setProperty(PRO_SERVER_ADDR_KEY, address);
            }
        }

        // namespace
        if (System.getProperty(PRO_NAMESPACE_KEY) != null) {
            properties.setProperty(PRO_NAMESPACE_KEY, System.getProperty(PRO_NAMESPACE_KEY));
        } else {
            String namespace = FILE_CONFIG.getConfig(getNacosNameSpaceFileKey());
            if (namespace == null) {
                namespace = DEFAULT_NAMESPACE;
            }
            properties.setProperty(PRO_NAMESPACE_KEY, namespace);
        }

        // username/password
        String username = StringUtils.isNotBlank(System.getProperty(USER_NAME)) ? System.getProperty(USER_NAME)
                : FILE_CONFIG.getConfig(getNacosUsername());
        if (StringUtils.isNotBlank(username)) {
            String password = StringUtils.isNotBlank(System.getProperty(PASSWORD)) ? System.getProperty(PASSWORD)
                    : FILE_CONFIG.getConfig(getNacosPassword());
            if (StringUtils.isNotBlank(password)) {
                properties.setProperty(USER_NAME, username);
                properties.setProperty(PASSWORD, password);
            }
        }
        return properties;
    }

    // config.nacos.namespace
    private static String getNacosNameSpaceFileKey() {
        return String.join(ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR, ConfigurationKeys.FILE_ROOT_CONFIG, CONFIG_TYPE, PRO_NAMESPACE_KEY);
    }

    // config.nacos.serverAddr
    private static String getNacosAddrFileKey() {
        return String.join(ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR, ConfigurationKeys.FILE_ROOT_CONFIG, CONFIG_TYPE, PRO_SERVER_ADDR_KEY);
    }

    // config.nacos.group
    private static String getNacosGroupKey() {
        return String.join(ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR, ConfigurationKeys.FILE_ROOT_CONFIG, CONFIG_TYPE, GROUP_KEY);
    }

    // config.nacos.dataId
    private static String getNacosDataIdKey() {
        return String.join(ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR, ConfigurationKeys.FILE_ROOT_CONFIG, CONFIG_TYPE, NACOS_DATA_ID_KEY);
    }

    // config.nacos.username
    private static String getNacosUsername() {
        return String.join(ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR, ConfigurationKeys.FILE_ROOT_CONFIG, CONFIG_TYPE,
                USER_NAME);
    }

    private static String getNacosPassword() {
        return String.join(ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR, ConfigurationKeys.FILE_ROOT_CONFIG, CONFIG_TYPE,
                PASSWORD);
    }

    // config.nacos.group
    private static String getNacosGroup() {
        String nacosGroupKey = getNacosGroupKey();
        // System.out.println("nacosGroupKey = " + nacosGroupKey);
        return FILE_CONFIG.getConfig(nacosGroupKey, DEFAULT_GROUP);
    }

    // config.nacos.dataId
    private static String getNacosDataId() {
        return FILE_CONFIG.getConfig(getNacosDataIdKey(), DEFAULT_DATA_ID); // seata.properties
    }

    private static String getSeataConfigStr() {
        StringBuilder sb = new StringBuilder();

        Enumeration<?> enumeration = seataConfig.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String property = seataConfig.getProperty(key);
            sb.append(key).append("=").append(property).append("\n");
        }

        return sb.toString();
    }

    // 加载 nacos 注册中心的 seata 配置文件: 默认为 seata.properties
    // 如果想要修改文件名, 需要设置 registry.conf 中的 config.nacos.dataId 属性,
    // 其值即表示 seata 配置文件名

    /**
     * @see #getLatestConfig(String, String, long)
     */
    private static void initSeataConfig() {
        try {
            // key: config.nacos.dataId, 默认值: seata.properties
            String nacosDataId = getNacosDataId();
            String config = configService.getConfig(nacosDataId, getNacosGroup(), DEFAULT_CONFIG_TIMEOUT);
            LOGGER.info("load seata-config from nacos-server, nacos-data-id[{}], config-content: \r\n{}", nacosDataId, config);
            if (StringUtils.isNotBlank(config)) {
                try (Reader reader = new InputStreamReader(new ByteArrayInputStream(config.getBytes()), StandardCharsets.UTF_8)) {
                    seataConfig.load(reader);
                }
                NacosListener nacosListener = new NacosListener(nacosDataId, null);
                configService.addListener(nacosDataId, getNacosGroup(), nacosListener);
            }
        } catch (NacosException | IOException e) {
            LOGGER.error("init config properties error", e);
        }
    }

    @Override
    public String getTypeName() {
        return CONFIG_TYPE;
    }

    /**
     * Non-blocking subscriptions prohibit adding subscriptions in the thread pool to prevent thread termination
     */
    public static class NacosListener extends AbstractSharedListener {
        private final String dataId;
        private final ConfigurationChangeListener listener;

        /**
         * Instantiates a new Nacos listener.
         *
         * @param dataId   the data id
         * @param listener the listener
         */
        public NacosListener(String dataId, ConfigurationChangeListener listener) {
            this.dataId = dataId;
            this.listener = listener;
        }

        /**
         * Gets target listener.
         *
         * @return the target listener
         */
        public ConfigurationChangeListener getTargetListener() {
            return this.listener;
        }

        @Override
        public void innerReceive(String dataId, String group, String configInfo) {
            //The new configuration method to puts all configurations into a dateId
            if (getNacosDataId().equals(dataId)) {
                Properties seataConfigNew = new Properties();
                if (StringUtils.isNotBlank(configInfo)) {
                    try (Reader reader = new InputStreamReader(new ByteArrayInputStream(configInfo.getBytes()), StandardCharsets.UTF_8)) {
                        seataConfigNew.load(reader);
                    } catch (IOException e) {
                        LOGGER.error("load config properties error", e);
                        return;
                    }
                }

                //Get all the monitored dataids and judge whether it has been modified
                for (Map.Entry<String, ConcurrentMap<ConfigurationChangeListener, NacosListener>> entry : CONFIG_LISTENERS_MAP.entrySet()) {
                    String listenedDataId = entry.getKey();
                    String propertyOld = seataConfig.getProperty(listenedDataId, "");
                    String propertyNew = seataConfigNew.getProperty(listenedDataId, "");
                    if (!propertyOld.equals(propertyNew)) {
                        ConfigurationChangeEvent event = new ConfigurationChangeEvent()
                                .setDataId(listenedDataId)
                                .setNewValue(propertyNew)
                                .setNamespace(group);

                        ConcurrentMap<ConfigurationChangeListener, NacosListener> configListeners = entry.getValue();
                        for (ConfigurationChangeListener configListener : configListeners.keySet()) {
                            configListener.onProcessEvent(event);
                        }
                    }
                }

                seataConfig = seataConfigNew;
                return;
            }

            //Compatible with old writing
            ConfigurationChangeEvent event = new ConfigurationChangeEvent().setDataId(dataId).setNewValue(configInfo)
                    .setNamespace(group);
            listener.onProcessEvent(event);
        }
    }
}

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
package io.seata.config;

import io.seata.common.exception.NotSupportYetException;
import io.seata.common.loader.EnhancedServiceLoader;
import io.seata.common.loader.EnhancedServiceNotFoundException;
import io.seata.common.util.StringUtils;
import io.seata.config.file.FileConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * The type Configuration factory.
 *
 * @author slievrly
 * @author Geng Zhang
 */
public final class ConfigurationFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFactory.class);

    private static final String REGISTRY_CONF_DEFAULT = "registry";
    private static final String ENV_SYSTEM_KEY = "SEATA_ENV";
    public static final String ENV_PROPERTY_KEY = "seataEnv";

    private static final String SYSTEM_PROPERTY_SEATA_CONFIG_NAME = "seata.config.name";

    private static final String ENV_SEATA_CONFIG_NAME = "SEATA_CONFIG_NAME";

    // 启动加载: 默认情况就是加载 registry.conf 文件的内容. 可以指定环境后缀和文件扩展名(conf/yaml/properties) registry-<env>.conf // io.seata.config.FileConfigFactory.SUFFIX_MAP
    public static Configuration CURRENT_FILE_INSTANCE;

    // earlier-init: CURRENT_FILE_INSTANCE, registry.conf
    static {
        load();
    }

    /**
     * @see io.seata.server.ParameterParser
     */
    private static void load() {
        // config/env 的设定有先后关系: 在环境变量或者 sys-props
        // 都是在启动参数设置: io.seata.server.ParameterParser#init
        // seata.config.name
        String seataConfigName = System.getProperty(SYSTEM_PROPERTY_SEATA_CONFIG_NAME);
        if (seataConfigName == null) {
            seataConfigName = System.getenv(ENV_SEATA_CONFIG_NAME); // SEATA_CONFIG_NAME
        }
        if (seataConfigName == null) {
            seataConfigName = REGISTRY_CONF_DEFAULT; // registry
        }
        String envValue = System.getProperty(ENV_PROPERTY_KEY); // seataEnv
        if (envValue == null) {
            envValue = System.getenv(ENV_SYSTEM_KEY); // SEATA_ENV
        }
        // 默认情况下, 加载 registry.conf/registry.yml, 可以自定义 文件名-环境
        // 文件后缀: io.seata.config.FileConfigFactory.SUFFIX_MAP: conf, yml, properties
        Configuration configuration = (envValue == null) ?
                new FileConfiguration(seataConfigName, false) // load /MATA-INF/services/
                : new FileConfiguration(seataConfigName + "-" + envValue, false);

        Configuration extConfiguration = null;
        try {
            extConfiguration = EnhancedServiceLoader.load(ExtConfigurationProvider.class).provide(configuration);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("load Configuration:{}", extConfiguration == null ? configuration.getClass().getSimpleName()
                        : extConfiguration.getClass().getSimpleName());
            }
        } catch (EnhancedServiceNotFoundException ignore) {

        } catch (Exception e) {
            LOGGER.error("failed to load extConfiguration:{}", e.getMessage(), e);
        }
        CURRENT_FILE_INSTANCE = extConfiguration == null ? configuration : extConfiguration;

        FileConfig registryConf = ((FileConfiguration) CURRENT_FILE_INSTANCE).getFileConfig();
        LOGGER.info("\r\n" + registryConf.toString());

    }

    private static final String NAME_KEY = "name";
    private static final String FILE_TYPE = "file";

    private static volatile Configuration instance = null;

    /**
     * @see #CURRENT_FILE_INSTANCE  registry.conf 文件的内容对象
     * @see #instance registry.conf 中 config.type 属性值对应的对象
     * Before:
     * static {
     *     load(); // 构建 {@link #CURRENT_FILE_INSTANCE} registry.conf
     * }
     * 构建 seata-server(registry.conf)-config.type 值对应的对象
     * @return the instance {@link #instance}
     * @see #load() -> getInstance -> buildConfiguration
     * Gets instance.
     */
    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = buildConfiguration(); // 代理对象
                }
            }
        }
        return instance;
    }


    /**
     *
     * @return 构建 registry.conf 中定义的 config.type 属性值所表示实例
     * @see ConfigType ignore case
     * @see io.seata.config.nacos.NacosConfiguration config.type=nacos
     * @see FileConfiguration config.type=file 该类既可作为 registry.conf 实例, 也可作为 config.type=file 时的实例
     * ... other types please see {@link ConfigType} ignore case
     */
    private static Configuration buildConfiguration() {
        String configTypeName = CURRENT_FILE_INSTANCE.getConfig(
                // config.type
                ConfigurationKeys.FILE_ROOT_CONFIG + // config
                        ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR // .
                        + ConfigurationKeys.FILE_ROOT_TYPE); // type

        if (StringUtils.isBlank(configTypeName)) {
            throw new NotSupportYetException("config type can not be null");
        }
        // ignore case
        ConfigType configType = ConfigType.getType(configTypeName);

        Configuration extConfiguration = null; // 默认为 null, spring 时不为 null
        Configuration configuration;

        // 如果 registry.conf 中的 config.type 属性值为 file, 特殊处理
        if (ConfigType.File == configType) {
            // dataId config.file.name: 默认就是 file.conf 文件
            String pathDataId = String.join(ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR, // .
                    ConfigurationKeys.FILE_ROOT_CONFIG, // config
                    FILE_TYPE, // file
                    NAME_KEY); // name

            // 默认为 file.conf 文件
            String name = CURRENT_FILE_INSTANCE.getConfig(pathDataId);
            configuration = new FileConfiguration(name);
            try {
                extConfiguration = EnhancedServiceLoader.load(ExtConfigurationProvider.class).provide(configuration);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("load Configuration:{}", extConfiguration == null
                            ? configuration.getClass().getSimpleName() : extConfiguration.getClass().getSimpleName());
                }
            } catch (EnhancedServiceNotFoundException ignore) {

            } catch (Exception e) {
                LOGGER.error("failed to load extConfiguration:{}", e.getMessage(), e);
            }
        } else { // config.type 值非 file 时
            // io.seata.config.nacos.NacosConfigurationProvider
            // io.seata.config.nacos.NacosConfiguration
            configuration = EnhancedServiceLoader
                    .load(ConfigurationProvider.class, Objects.requireNonNull(configType).name())
                    .provide(); // io.seata.config.nacos.NacosConfiguration
        }
        try {
            Configuration configurationCache;
            // 1. 这里无条件代理成功?
            // 2. 这里为何要使用代理? -- 为了缓存(CONFIG_CACHE) 及监听
            if (null != extConfiguration) {
                configurationCache = ConfigurationCache.getInstance().proxy(extConfiguration);
            } else {
                configurationCache = ConfigurationCache.getInstance().proxy(configuration);
            }
            // 可以注释这里, 看看没有代理会有什么情况
            if (null != configurationCache) {
                extConfiguration = configurationCache;
            }
        } catch (EnhancedServiceNotFoundException ignore) {

        } catch (Exception e) {
            LOGGER.error("failed to load configurationCacheProvider:{}", e.getMessage(), e);
        }
        return null == extConfiguration ? configuration : extConfiguration;
    }

    protected static void reload() {
        ConfigurationCache.getInstance().clear();
        load();
        instance = null;
        getInstance();
    }
}

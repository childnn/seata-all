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

import io.seata.common.loader.EnhancedServiceLoader;
import io.seata.common.loader.LoadLevel;
import io.seata.config.file.FileConfig;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author wangwei-ying
 * @see FileConfig
 */
public class FileConfigFactory {

    // 不同文件类型的解析方式
    /**
     * @see io.seata.config.file.SimpleFileConfig
     */
    public static final String DEFAULT_TYPE = "CONF";

    /**
     * @see io.seata.config.file.YamlFileConfig
     */
    public static final String YAML_TYPE = "YAML";

    private static final LinkedHashMap<String, String> SUFFIX_MAP = new LinkedHashMap<String, String>(4) {
        {
            put("conf", DEFAULT_TYPE);
            put("properties", DEFAULT_TYPE);
            put("yml", YAML_TYPE);
        }
    };


    public static FileConfig load() {
        return loadService(DEFAULT_TYPE, null, null);
    }

    public static FileConfig load(File targetFile, String name) {
        String fileName = targetFile.getName();
        // io.seata.config.FileConfigFactory.SUFFIX_MAP#value
        String configType = getConfigType(fileName); // 默认: CONF
        return loadService(configType, new Class[]{File.class, String.class}, new Object[]{targetFile, name});
    }

    // 文件类型: 后缀
    private static String getConfigType(String fileName) {
        String configType = DEFAULT_TYPE;
        int suffixIndex = fileName.lastIndexOf(".");
        if (suffixIndex > 0) {
            configType = SUFFIX_MAP.getOrDefault(fileName.substring(suffixIndex + 1), DEFAULT_TYPE);
        }

        return configType;
    }

    /**
     *
     * @param name {@link LoadLevel#name()}
     * @param argsType service 实现的构造方法参数类型
     * @param args 构造方法实参
     * @return the file config
     */
    private static FileConfig loadService(String name, Class[] argsType, Object[] args) {
        // 加载 META-INF/services 下的 io.seata.config.file.FileConfig 文件中配置的内容
        // 1. name 是 CONF/YML, io.seata.config.FileConfigFactory.SUFFIX_MAP#value
        // 2. argsType: 构造参数类型
        // 3. 构造实参
        return EnhancedServiceLoader.load(FileConfig.class, name, argsType, args);
    }

    public static Set<String> getSuffixSet() {
        return SUFFIX_MAP.keySet();
    }

    public synchronized static void register(String suffix, String beanActiveName) {
        SUFFIX_MAP.put(suffix, beanActiveName);
    }


}

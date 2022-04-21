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
package io.seata.config.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import io.seata.common.loader.LoadLevel;
import io.seata.common.loader.Scope;
import io.seata.config.FileConfigFactory;
import io.seata.config.FileConfiguration;

import java.io.File;
import java.util.Map;

/**
 * @author wangwei-ying
 */
@LoadLevel(name = FileConfigFactory.DEFAULT_TYPE, scope = Scope.PROTOTYPE)
public class SimpleFileConfig implements FileConfig {

    private Config fileConfig;

    public SimpleFileConfig() {
        // conf 工具类
        fileConfig = ConfigFactory.load();
    }

    public SimpleFileConfig(File file, String name) {
        // conf 工具类 加载 .conf 配置
        if (name.startsWith(FileConfiguration.SYS_FILE_RESOURCE_PREFIX)) {
            Config appConfig = ConfigFactory.parseFileAnySyntax(file);
            fileConfig = ConfigFactory.load(appConfig);
        } else {
            fileConfig = ConfigFactory.load(file.getName());
        }
    }

    @Override
    public String getString(String path) {
        return fileConfig.getString(path);
    }

    @Override
    public String toString() {
        ConfigObject root = fileConfig.root();
        Map<String, Object> map = root.unwrapped();

        // return fileConfig.toString();
        return JSON.toJSONString(map, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }
}

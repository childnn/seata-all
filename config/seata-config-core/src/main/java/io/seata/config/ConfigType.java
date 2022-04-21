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

/**
 * The enum Config type.
 *
 * @author slievrly
 * registry.conf 中的 config.type 类型
 * 设置不区分大小写
 */
public enum ConfigType {
    /**
     * File config type.
     * @see FileConfiguration
     */
    File,
    /**
     * zookeeper config type.
     * @see io.seata.config.zk.ZookeeperConfiguration
     */
    ZK,
    /**
     * Nacos config type.
     * @see io.seata.config.nacos.NacosConfiguration
     */
    Nacos,
    /**
     * Apollo config type.
     * @see io.seata.config.apollo.ApolloConfiguration
     */
    Apollo,
    /**
     * Consul config type
     * @see io.seata.config.consul.ConsulConfiguration
     */
    Consul,
    /**
     * Etcd3 config type
     * @see io.seata.config.etcd3.EtcdConfiguration
     */
    Etcd3,
    /**
     * spring cloud config type
     * @see io.seata.config.springcloud.SpringCloudConfiguration
     */
    SpringCloudConfig,
    /**
     * Custom config type
     * 自定义, 实现 {@link AbstractConfiguration}
     */
    Custom;

    /**
     * Gets type.
     *
     * @param name the name
     * @return the type
     */
    public static ConfigType getType(String name) {
        // ConfigType[] enumConstants = ConfigType.class.getEnumConstants();
        for (ConfigType configType : values()) {
            if (configType.name().equalsIgnoreCase(name)) { // ignore case
                return configType;
            }
        }
        throw new IllegalArgumentException("not support config type: " + name);
    }
}

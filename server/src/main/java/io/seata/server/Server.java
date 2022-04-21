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
package io.seata.server;

import io.seata.common.XID;
import io.seata.common.thread.NamedThreadFactory;
import io.seata.common.util.NetUtil;
import io.seata.core.constants.ConfigurationKeys;
import io.seata.core.rpc.ShutdownHook;
import io.seata.core.rpc.netty.NettyRemotingServer;
import io.seata.core.rpc.netty.NettyServerConfig;
import io.seata.server.coordinator.DefaultCoordinator;
import io.seata.server.env.ContainerHelper;
import io.seata.server.env.PortHelper;
import io.seata.server.metrics.MetricsManager;
import io.seata.server.session.SessionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The type Server.
 *
 * @author slievrly
 * 1. 加载 registry.conf: 其中定义了 seata 的注册中心和配置中心
 * 2.
 */
public class Server {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        // get port first, use to logback.xml
        int port = PortHelper.getPort(args);
        System.setProperty(ConfigurationKeys.SERVER_PORT, Integer.toString(port));

        // create logger
        final Logger logger = LoggerFactory.getLogger(Server.class);
        if (ContainerHelper.isRunningInContainer()) {
            logger.info("The server is running in container.");
        }

        // io.seata.server.ParameterParser.init JVM 启动参数
        // 如果没有设置启动参数, 则会去 Configuration 获取
        //initialize the parameter parser
        //Note that the parameter parser should always be the first line to execute.
        //Because, here we need to parse the parameters needed for startup.
        // init-method: 在 init 中又加载 io.seata.config.ConfigurationFactory
        // ConfigurationFactory 中又有 静态代码块, 加载各种配置
        // 1. 加载 registry.conf: 主要属性 registry.type/config.type
        // 2. 加载 config.type 的属性值对应的 config: 如 配置中心为 nacos 则使用 io.seata.config.nacos.NacosConfiguration
        ParameterParser parameterParser = new ParameterParser(args);

        //initialize the metrics
        MetricsManager.get().init();

        System.setProperty(ConfigurationKeys.STORE_MODE, parameterParser.getStoreMode());

        ThreadPoolExecutor workingThreads = new ThreadPoolExecutor(NettyServerConfig.getMinServerPoolSize(),
                NettyServerConfig.getMaxServerPoolSize(), NettyServerConfig.getKeepAliveTime(), TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(NettyServerConfig.getMaxTaskQueueSize()),
                new NamedThreadFactory("ServerHandlerThread", NettyServerConfig.getMaxServerPoolSize()), new ThreadPoolExecutor.CallerRunsPolicy());

        NettyRemotingServer nettyRemotingServer = new NettyRemotingServer(workingThreads);
        //server port
        nettyRemotingServer.setListenPort(parameterParser.getPort());
        UUIDGenerator.init(parameterParser.getServerNode());
        //
        // io.seata.core.store.StoreMode: data-id='store.mode', ignore case
        // log store mode : file, db, redis
        SessionHolder.init(parameterParser.getStoreMode());

        // TC
        DefaultCoordinator coordinator = new DefaultCoordinator(nettyRemotingServer);
        coordinator.init();
        nettyRemotingServer.setHandler(coordinator);
        // register ShutdownHook
        ShutdownHook.getInstance().addDisposable(coordinator);
        ShutdownHook.getInstance().addDisposable(nettyRemotingServer);

        //127.0.0.1 and 0.0.0.0 are not valid here.
        if (NetUtil.isValidIp(parameterParser.getHost(), false)) {
            XID.setIpAddress(parameterParser.getHost());
        } else {
            XID.setIpAddress(NetUtil.getLocalIp());
        }
        XID.setPort(nettyRemotingServer.getListenPort());

        try {
            nettyRemotingServer.init();
        } catch (Throwable e) {
            logger.error("nettyServer init error:{}", e.getMessage(), e);
            System.exit(-1);
        }

        System.exit(0);
    }
}

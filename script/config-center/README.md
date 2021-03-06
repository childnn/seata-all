# Script usage demo
![Since 1.2.0](https://img.shields.io/badge/Since%20-1.2.0-orange.svg?style=flat-square)

## important attributes 

you only need to follow the instructions below and keep the corresponding configuration in 'config.txt' to run. For more configuration information, please visit [seata.io](https://seata.io/)

| server                   | client                                                       |
| ------------------------ | ------------------------------------------------------------ |
| store.mode: file,db      | config.type: file、nacos 、apollo、zk、consul、etcd3、custom |
| #only db:                | #only file:                                                  |
| store.db.driverClassName | service.default.grouplist                                    |
| store.db.url             | #All:                                                        |
| store.db.user            | service.vgroupMapping.my_test_tx_group                       |
| store.db.password        | service.disableGlobalTransaction                             |

### 运行指定配置中心对应的脚本, 可将 config.txt 中的配置导入到配置中心
## Nacos
shell:
```bash
sh ${SEATAPATH}/script/config-center/nacos/nacos-config.sh -h localhost -p 8848 -g SEATA_GROUP -t 5a3c7d6c-f497-4d68-a71a-2e5e3340b3ca -u username -w password
```

Parameter Description:

-h: host, the default value is localhost.

-p: port, the default value is 8848.

-g: Configure grouping, the default value is 'SEATA_GROUP'.

-t: Tenant information, corresponding to the namespace ID field of Nacos, the default value is ''.

-u: username, nacos 1.2.0+ on permission control, the default value is ''.

-w: password, nacos 1.2.0+ on permission control, the default value is ''.

python:
```bash
python ${SEATAPATH}/script/config-center/nacos/nacos-config.py localhost:8848
```

## Apollo
```bash
sh ${SEATAPATH}/script/config-center/apollo/apollo-config.sh -h localhost -p 8070 -e DEV -a seata-server -c default -n application -d apollo -r apollo -t 3aa026fc8435d0fc4505b345b8fa4578fb646a2c
```
Parameter Description:

-h: host, the default value is localhost.

-p: port, the default value is 8070.

-e: Managed configuration environment, the default value is DEV.

-a: AppId to which the namespace belongs, the default value is seata-server.

-c: Managed configuration cluster name, Generally, you can pass in default. If it is a special cluster, just pass in the name of the corresponding cluster，the default value is default.

-n: Name of the managed namespace, If the format is not properties, you need to add a suffix name, such as sample.yml, the default value is application.

-d: The creator of the item, in the format of a domain account, which is the User ID of the sso system.

-r: Publisher, domain account, note: if namespace.lock.switch in ApolloConfigDB.ServerConfig is set to true (default is false), Then the environment does not allow the publisher and editor to be the same person. So if the editor is zhangsan, the publisher can no longer be zhangsan.

-t: Apollo admin creates third-party applications in http://{portal_address}/open/manage.html, It is best to check whether this AppId has been created before creation. After successful creation, a token will be generated.

For details of the above parameter descriptions, please see:

https://github.com/ctripcorp/apollo/wiki/Apollo%E5%BC%80%E6%94%BE%E5%B9%B3%E5%8F%B0

## Consul
```bash
sh ${SEATAPATH}/script/config-center/consul/consul-config.sh -h localhost -p 8500
```
Parameter Description:

-h: host, the default value is localhost.

-p: port, the default value is 8500.

## Etcd3
```bash
sh ${SEATAPATH}/script/config-center/etcd3/etcd3-config.sh -h localhost -p 2379
```

Parameter Description:

-h: host, the default value is localhost.

-p: port, the default value is 2379.

## ZK
```bash
sh ${SEATAPATH}/script/config-center/zk/zk-config.sh -h localhost -p 2181 -z "/Users/zhangchenghui/zookeeper-3.4.14"
```
Parameter Description:

-h: host, the default value is localhost.

-p: port, the default value is 2181.

-z: zk path.

### 配置说明
####公共部分

|    key                 |  desc              |remark
| -----------------------|-------------------|------------------
|transport.serialization |	client和server通信编解码方式	| seata(ByteBuf)、protobuf、kryo、hession、fst，默认seata
transport.compressor|	client和server通信数据压缩方式	|none、gzip，默认none
transport.heartbeat|	client和server通信心跳检测开关|	默认true开启
registry.type|	注册中心类型	|默认file，支持file 、nacos 、eureka、redis、zk、consul、etcd3、sofa、custom
config.type|	配置中心类型	|默认file，支持file、nacos 、apollo、zk、consul、etcd3、custom
####server端
|key	                 |desc	            |remark
| -----------------------|-------------------|------------------
server.undo.logSaveDays	|undo保留天数|	默认7天,log_status=1（附录3）和未正常清理的undo
server.undo.logDeletePeriod	|undo清理线程间隔时间|	默认86400000，单位毫秒
server.maxCommitRetryTimeout|	二阶段提交重试超时时长	|单位ms,s,m,h,d,对应毫秒,秒,分,小时,天,默认毫秒。默认值-1表示无限重试。公式: timeout>=now-globalTransactionBeginTime,true表示超时则不再重试
server.maxRollbackRetryTimeout|	二阶段回滚重试超时时长|	同commit
server.recovery.committingRetryPeriod|	二阶段提交未完成状态全局事务重试提交线程间隔时间|	默认1000，单位毫秒
server.recovery.asynCommittingRetryPeriod|	二阶段异步提交状态重试提交线程间隔时间|	默认1000，单位毫秒
server.recovery.rollbackingRetryPeriod|	二阶段回滚状态重试回滚线程间隔时间|	默认1000，单位毫秒
server.recovery.timeoutRetryPeriod	|超时状态检测重试线程间隔时间	|默认1000，单位毫秒，检测出超时将全局事务置入回滚会话管理器
store.mode	|事务会话信息存储方式|	file本地文件(不支持HA)，db数据库,redis(支持HA)
store.file.dir|	file模式文件存储文件夹名|	默认sessionStore
store.db.datasource|	db模式数据源类型	|dbcp、druid、hikari；无默认值，store.mode=db时必须指定。
store.db.dbType	|db模式数据库类型	|mysql、oracle、db2、sqlserver、sybaee、h2、sqlite、access、postgresql、oceanbase；无默认值，store.mode=db时必须指定。
store.db.driverClassName|	db模式数据库驱动	|store.mode=db时必须指定
store.db.url|	db模式数据库url	|store.mode=db时必须指定，在使用mysql作为数据源时，建议在连接参数中加上rewriteBatchedStatements=true(详细原因请阅读附录7)
store.db.user	|db模式数据库账户|	store.mode=db时必须指定
store.db.password|	db模式数据库账户密码	|store.mode=db时必须指定
store.db.minConn	|db模式数据库初始连接数	|默认1
store.db.maxConn|	db模式数据库最大连接数	|默认20
store.db.maxWait	|db模式获取连接时最大等待时间|	默认5000，单位毫秒
store.db.globalTable|	db模式全局事务表名|	默认global_table
store.db.branchTable|	db模式分支事务表名	|默认branch_table
store.db.lockTable	|db模式全局锁表名|	默认lock_table
store.db.queryLimit|	db模式查询全局事务一次的最大条数|	默认100
store.redis.host|	redis模式ip	|默认127.0.0.1
store.redis.port	|redis模式端口|	默认6379
store.redis.maxConn|	redis模式最大连接数|	默认10
store.redis.minConn	|redis模式最小连接数|	默认1
store.redis.database|	redis模式默认库|	默认0
store.redis.password	|redis模式密码(无可不填)|	默认null
store.redis.queryLimit|	redis模式一次查询最大条数|	默认100
metrics.enabled|	是否启用Metrics	|默认false关闭，在False状态下，所有与Metrics相关的组件将不会被初始化，使得性能损耗最低
metrics.registryType	|指标注册器类型	|Metrics使用的指标注册器类型，默认为内置的compact（简易）实现，这个实现中的Meter仅使用有限内存计数，性能高足够满足大多数场景；目前只能设置一个指标注册器实现
metrics.exporterList	|指标结果Measurement数据输出器列表	|默认prometheus，多个输出器使用英文逗号分割，例如"prometheus,jmx"，目前仅实现了对接prometheus的输出器
metrics.exporterPrometheusPort|	prometheus输出器Client端口号	|默认9898
####client端
key|	desc|	remark
| -----------------------|-------------------|------------------
seata.enabled	|是否开启spring-boot自动装配|	true、false,(SSBS)专有配置，默认true（附录4）
seata.enableAutoDataSourceProxy=true|	是否开启数据源自动代理	|true、false,seata-spring-boot-starter(SSBS)专有配置,SSBS默认会开启数据源自动代理,可通过该配置项关闭.
seata.useJdkProxy=false|	是否使用JDK代理作为数据源自动代理的实现方式|	true、false,(SSBS)专有配置,默认false,采用CGLIB作为数据源自动代理的实现方式
transport.enableClientBatchSendRequest	|客户端事务消息请求是否批量合并发送|	默认true，false单条发送
client.log.exceptionRate	|日志异常输出概率|	默认100，目前用于undo回滚失败时异常堆栈输出，百分之一的概率输出，回滚失败基本是脏数据，无需输出堆栈占用硬盘空间
service.vgroupMapping.my_test_tx_group	|事务群组（附录1）	|my_test_tx_group为分组，配置项值为TC集群名
service.default.grouplist|	TC服务列表（附录2）|	仅注册中心为file时使用
service.disableGlobalTransaction	|全局事务开关|	默认false。false为开启，true为关闭
client.tm.degradeCheck|	降级开关|	默认false。业务侧根据连续错误数自动降级不走seata事务(详细介绍请阅读附录6)
client.tm.degradeCheckAllowTimes|	升降级达标阈值	|默认10
client.tm.degradeCheckPeriod	|服务自检周期|	默认2000,单位ms.每2秒进行一次服务自检,来决定
client.rm.reportSuccessEnable	|是否上报一阶段成功	|true、false，从1.1.0版本开始,默认false.true用于保持分支事务生命周期记录完整，false可提高不少性能
client.rm.asynCommitBufferLimit	|异步提交缓存队列长度|	默认10000。 二阶段提交成功，RM异步清理undo队列
client.rm.lock.retryInterval	|校验或占用全局锁重试间隔|	默认10，单位毫秒
client.rm.lock.retryTimes	|校验或占用全局锁重试次数|	默认30
client.rm.lock.retryPolicyBranchRollbackOnConflict	|分支事务与其它全局回滚事务冲突时锁策略|	默认true，优先释放本地锁让回滚成功
client.rm.reportRetryCount	|一阶段结果上报TC重试次数|	默认5次
client.rm.tableMetaCheckEnable	|自动刷新缓存中的表结构	|默认false
client.tm.commitRetryCount	|一阶段全局提交结果上报TC重试次数|	默认1次，建议大于1
client.tm.rollbackRetryCount|	一阶段全局回滚结果上报TC重试次数	|默认1次，建议大于1
client.undo.dataValidation	|二阶段回滚镜像校验	|默认true开启，false关闭
client.undo.logSerialization	|undo序列化方式|	默认jackson
client.undo.logTable	|自定义undo表名|	默认undo_log
-----------------------------------------------------------------------------------
事务分组说明。
1.事务分组是什么？
    事务分组是seata的资源逻辑，类似于服务实例。在 file.conf 中的 my_test_tx_group 就是一个事务分组。
2.通过事务分组如何找到后端集群？
    首先程序中配置了事务分组（GlobalTransactionScanner 构造方法的txServiceGroup参数），
    程序会通过用户配置的配置中心去寻找service.vgroupMapping. 事务分组配置项，取得配置项的值就是TC集群的名称。
    拿到集群名称程序通过一定的前后缀+集群名称去构造服务名，各配置中心的服务名实现不同。
    拿到服务名去相应的注册中心去拉取相应服务名的服务列表，获得后端真实的TC服务列表。
3.为什么这么设计，不直接取服务名？
    这里多了一层获取事务分组到映射集群的配置。这样设计后，事务分组可以作为资源的逻辑隔离单位，当发生故障时可以快速failover。
---
关于grouplist问题说明下。
1. 什么时候会用到file.conf中的 default.grouplist？
   当registry.type=file时会用到，其他时候不读。
2. default.grouplist的值列表是否可以配置多个？
   可以配置多个，配置多个意味着集群，但当store.mode=file时，会报错。
   原因是在file存储模式下未提供本地文件的同步，所以需要使用store.mode=db，通过db来共享TC集群间数据
3. 是否推荐使用default.grouplist？
   不推荐，如问题1，当registry.type=file时会用到，也就是说这里用的不是真正的注册中心，
   不具体服务的健康检查机制当tc不可用时无法自动剔除列表，推荐使用nacos 、eureka、redis、zk、consul、etcd3、sofa。
   registry.type=file或config.type=file 设计的初衷是让用户再不依赖第三方注册中心或配置中心的前提下，通过直连的方式，
   快速验证seata服务。    
4.seata-spring-boot-starter 中的配置为什么是 grouplist.default,
   也就是说和 file.conf 中的 default.grouplist写法刚好颠倒了位置?  
   由于spring-boot本身配置文件语法的要求,这个地方需要将 file.conf 中的 default.grouplist 写成 grouplist.default,
   效果是一样的.
---
log_status=1的是防御性的，是收到全局回滚请求，但是不确定某个事务分支的本地事务是否已经执行完成了，
这时事先插入一条branchid相同的数据，插入的假数据成功了，本地事务继续执行就会报唯一索引冲突自动回滚。
假如插入不成功说明表里有数据这个本地事务已经执行完成了，那么取出这条undolog数据做反向回滚操作。
是否开启spring-boot自动装配，如果开启，则会自动配置seata与spring-boot的集成，
包括数据源的自动代理以及GlobalTransactionScanner初始化。
注：1.0版本新特性，需依赖seata-spring-boot-starter。


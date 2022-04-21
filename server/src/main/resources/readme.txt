mysql-bug：
   5.1.x 版本, 更换高版本可解决
    https://bugs.mysql.com/bug.php?id=38060
    java.lang.NullPointerException
            at com.mysql.jdbc.ConnectionImpl.getServerCharacterEncoding(ConnectionImpl.java:3040)
            at com.mysql.jdbc.PreparedStatement.setString(PreparedStatement.java:4072)
            at cz.dynawest.isir.Conversion.ZpracujArchiv(Conversion.java:166)
            at cz.dynawest.isir.xmlvypis.Main.main(Main.java:49)
    public String getServerCharacterEncoding() {
      if (this.io.versionMeetsMinimum(4, 1, 0)) {
    	return (String) this.serverVariables.get("character_set_server");
      } else {
      	return (String) this.serverVariables.get("character_set");
      }
    }


seata 事务分组
https://seata.io/zh-cn/docs/user/txgroup/transaction-group.html

 1、vgroup_mapping.my-tx-group = "seata-server"为事务组名称，这里的值需要和TC中配置的service.vgroup-mapping.my-tx-group一致;
 2、my-tx-group为自定义名称，可以随便取，seata-server这个值也是一样;
 3、事务组的命名不要用下划线’_’，可以用’-'因为在seata的高版本中使用underline下划线 将导致service not to be found。
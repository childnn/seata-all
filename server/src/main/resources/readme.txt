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
{
	// 系统支持的数据库类型定义
	dbtypes : [{
		name : 'MySQL',
		url : 'jdbc:mysql://<server>/<dbname>?zeroDateTimeBehavior=convertToNull',
		driver : 'com.mysql.jdbc.Driver',
		service : 'com.wonders.bigdata.manageplatform.tools.jdbexplorer.dbe.service.impl.MysqlDBInfoService'
	}, {
		name : 'star',
		url : 'jdbc:hive2://<server>/<dbname>',
		driver : 'org.apache.hive.jdbc.HiveDriver',
		service : 'com.wonders.bigdata.manageplatform.tools.jdbexplorer.dbe.service.impl.StarDBInfoService'
	}, {
		name : 'ORACLE10g',
		url : 'jdbc:oracle:thin:@<server>:<dbname>',
		driver : 'oracle.jdbc.driver.OracleDriver',
		service : 'com.wonders.bigdata.manageplatform.tools.jdbexplorer.dbe.service.impl.OracleDBInfoService'
	}, {
		name : 'Derby',
		url : 'jdbc:derby://<server>/<dbname>;create=true',
		driver : 'org.apache.derby.jdbc.ClientDriver',
		service : 'com.wonders.bigdata.manageplatform.tools.jdbexplorer.dbe.service.impl.DerbyDBInfoService'	
	},  {
		name : 'MS_SQLSERVER',
		url : 'jdbc:sqlserver://<server>;databaseName=<dbname>',
		driver : 'com.microsoft.sqlserver.jdbc.SQLServerDriver',
		service : 'com.wonders.bigdata.manageplatform.tools.jdbexplorer.dbe.service.impl.SQLServerDBInfoService'
	}, {
		name : 'HSQL_Server',
		url : 'jdbc:hsqldb:hsql://<server>/<dbname>',
		driver : 'org.hsqldb.jdbcDriver',
		service : 'com.wonders.bigdata.manageplatform.tools.jdbexplorer.dbe.service.impl.HSQLDBInfoService'
	}, {
		name : 'PostgreSQL',
		url : 'jdbc:postgresql://<server>/<dbname>',
		driver : 'org.postgresql.Driver',
		service : 'com.wonders.bigdata.manageplatform.tools.jdbexplorer.dbe.service.impl.PostgreSQLDBInfoService'
	}, {
		name : 'DB2',
		url : 'jdbc:db2://<server>/<dbname>',
		driver : 'com.ibm.db2.jcc.DB2Driver',
		service : 'com.wonders.bigdata.manageplatform.tools.jdbexplorer.dbe.service.impl.DefaultDBInfoService'
	}]
}

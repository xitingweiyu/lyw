package com.wonders.bigdata.manageplatform.utils;

public class  SqConEntity {
	private String name;
	private String clientUrl;
	private String connString;
	private String jdbcDriver;
	private String userName;
	private String password;
	private int maxConnection;

	


	public SqConEntity(String jobType, String clientUrl, String connString, String jdbcDriver,
			String userName, String password, int maxConnection,
			String schemaName, String tableName, String sql, String columns,
			String partitionColumn, String storageType, String outputFormat,
			String hadoopDirectory) {
		this.clientUrl = clientUrl;
		this.connString = connString;
		this.jdbcDriver = jdbcDriver;
		this.userName = userName;
		this.password = password;
		this.maxConnection = maxConnection;
	}
	
	public SqConEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClientUrl() {
		return clientUrl;
	}
	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}
	public String getConnString() {
		return connString;
	}
	public void setConnString(String connString) {
		this.connString = connString;
	}
	public String getJdbcDriver() {
		return jdbcDriver;
	}
	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMaxConnection() {
		return maxConnection;
	}
	public void setMaxConnection(int maxConnection) {
		this.maxConnection = maxConnection;
	}
}

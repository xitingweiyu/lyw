package com.wonders.bigdata.manageplatform.utils;

import java.sql.*;

public class  JdbcConnection{
	
   public static Connection getConnection(String driver, String url, String userName,String password){
	   Connection connection = null;
	   
	   try {
		   Class.forName(driver);
	   } catch (ClassNotFoundException e) {
		   e.printStackTrace();
		   System.err.println("The Driver loaded error,please contact to your Software Designer!");
	   }
	   try {
		connection =DriverManager.getConnection(url, userName, password);
    	} catch (SQLException e) {
		e.printStackTrace();
    	}
	   return connection;
   }

   public static void closeConnection(Connection connection, PreparedStatement pStatement, ResultSet resultSet){
	   try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (pStatement != null) {
				pStatement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   }

	public static void closeConnection(Connection connection, Statement pStatement, ResultSet resultSet){
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (pStatement != null) {
				pStatement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

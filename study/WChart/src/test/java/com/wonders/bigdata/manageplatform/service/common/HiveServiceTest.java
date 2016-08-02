package com.wonders.bigdata.manageplatform.service.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.wonders.bigdata.manageplatform.utils.Messages;

public class HiveServiceTest {
	
	private static String jdbcHiveUrl = Messages.getString("jdbcHiveUrl");
	private static String hiveUser = Messages.getString("hiveUser");
	private static String hivePwd = Messages.getString("hivePwd");

	public static void main(String[] args) {
		String tableName = "DM_MPG_U_SEASON2";
		String table2 = "DM_MZ_TJ";
		String field1 = "kh";
		String field2 = "nyr";
		String condition = "kh>5000977972 and nyr>20100222 and yydm<42515030300";
		
		showField(tableName);
		normalSearch(tableName);
		limitSearch(tableName, 100);
		fieldSearch(tableName, field1, field2);
		joinSearch(tableName, table2, field1, field2);
		conSearch(tableName, condition);
	}
	
	public static void normalSearch(String tableName) {
		String sql = "select * from 48_" + tableName;
		try {
			long bgDate = System.currentTimeMillis();
			final String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
			Connection con;
			con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
			final Statement stmt = con.createStatement();
			@SuppressWarnings("unused")
			ResultSet datSet = stmt.executeQuery(sql);
			long endDate = System.currentTimeMillis();
			System.out.println("表：" + tableName + " 标准查询耗时：" + (endDate - bgDate));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void limitSearch(String tableName, int number) {
		String sql = "select * from 48_" + tableName + " limit " + number;
		try {
			long bgDate = System.currentTimeMillis();
			final String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
			Connection con;
			con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
			final Statement stmt = con.createStatement();
			@SuppressWarnings("unused")
			ResultSet datSet = stmt.executeQuery(sql);
			long endDate = System.currentTimeMillis();
			System.out.println("表：" + tableName + " limit"+number+"查询耗时：" + (endDate - bgDate));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void joinSearch(String table1, String table2, String field1, String field2) {
		String sql = "select a."+ field1 +", b."+ field2 +" from 48_" + table1;
		sql += " a, 48_" + table2 + " b where a."+field1+" = b."+field1;
		try {
			long bgDate = System.currentTimeMillis();
			final String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
			Connection con;
			con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
			final Statement stmt = con.createStatement();
			@SuppressWarnings("unused")
			ResultSet datSet = stmt.executeQuery(sql);
			long endDate = System.currentTimeMillis();
			System.out.println("表：" + table1 + " 连接"+table2+"查询耗时：" + (endDate - bgDate));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void conSearch(String tableName, String condition) {
		String sql = "select * from 48_" + tableName + " where " + condition;
		try {
			long bgDate = System.currentTimeMillis();
			final String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
			Connection con;
			con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
			final Statement stmt = con.createStatement();
			@SuppressWarnings("unused")
			ResultSet datSet = stmt.executeQuery(sql);
			long endDate = System.currentTimeMillis();
			System.out.println("表：" + tableName + " 条件("+condition+")查询耗时：" + (endDate - bgDate));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void fieldSearch(String tableName, String field1, String field2) {
		String sql = "select "+field1+","+field2+" from 48_" + tableName;
		try {
			long bgDate = System.currentTimeMillis();
			final String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
			Connection con;
			con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
			final Statement stmt = con.createStatement();
			@SuppressWarnings("unused")
			ResultSet datSet = stmt.executeQuery(sql);
			long endDate = System.currentTimeMillis();
			System.out.println("表：" + tableName + " 字段查询耗时：" + (endDate - bgDate));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void showField(String tableName) {
		try {
			final String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
			Connection con;
			con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
			final Statement stmt = con.createStatement();
			String desc = "desc 48_" + tableName;
			ResultSet resultSet = stmt.executeQuery(desc);
			while(resultSet.next()){
				System.out.print(resultSet.getString(1) + "                ");
			}
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}

package com.wonders.bigdata.manageplatform.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** JDBC工具类 */
public class  DbUtil {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement pps = null;

	// private final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final String url = "jdbc:mysql://localhost:3306/dataquality";

	private final static String path = "com.mysql.jdbc.Driver";
	// private final static String path = "oracle.jdbc.driver.OracleDriver";

	private final String user = "root";
	private final String password = "root";

	/** 1.加载驱动 (静态代码块) */
	static {
		try {
			Class.forName(path);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/** 2.获取连接 */
	public Connection getConn() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 
	 * @param sql
	 *            String类型的sql语句，可以是 insert update delete
	 * @return int 表示受影响行数， -1表示操作失败。
	 */
	public int update(String sql) {

		try {
			stmt = getConn().createStatement();
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			release();
		}
	}

	/**
	 * 基于PreparedStatement的修改方法 PreparedStatement:表示预编译的 SQL 语句的对象
	 * 
	 * @param sql
	 *            String类型的sql语句，可以是 insert update delete
	 * @param objects
	 * @return
	 */
	public int update(String sql, Object... objects) {

		try {
			// 创建预编译的 SQL 语句对象
			pps = getConn().prepareStatement(sql);
			// ParameterMetaData：用于获取关于PreparedStatement对象中每个参数的类型和属性信息的对象
			ParameterMetaData pmd = pps.getParameterMetaData();
			// 定义变量length代表数组长度，也就是预处理的sql语句中的参数个数
			int length = pmd.getParameterCount();
			// 循环将sql语句中的?设置为obj数组中对应的值，注意从1开始，所以i要加1
			for (int i = 0; i < length; i++) {
				pps.setObject(i + 1, objects[i]);
			}

			return pps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			release();
		}
	}

	/**
	 * 查询操作
	 * 
	 * @param sql
	 * @return 装有Map的List
	 */
	public List<Map<String, Object>> query(String sql) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql);
			/*------将结果集rs转化为List<Map<String,Object>>-----*/
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();// 获得结果集中字段的个数

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < count; i++) {
					String key = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(key);
					map.put(key, value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return list;
	}
	
	
	/**
	 * 执行查询SQL语句
	 * @param sql  语句（查询）
	 * @return 列表，里面装的字符串数组（每一列为一个数组元素。）
	 */
	public List<String[]> queryToArr(String sql){
		List<String[]> rsListSA = new ArrayList<String []>();  //也可以用LinkedList
		try {
			stmt = getConn().createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			//用while循环遍历rs
			while(rs.next()){
				String[] tempArr = new String[columnCount];
				//用for循环所有列值存入数组
				for (int i = 0; i < columnCount; i++) {
					tempArr[i] = rs.getString(i + 1);
				}
				rsListSA.add(tempArr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		release();
		return rsListSA;
	}
	
	

	/** 5.批量更新(事务) */
	public int[] executeBatch(String[] sqls) {

		try {
			conn = getConn();
			conn.setAutoCommit(false);// 设置自动提交为false
			stmt = conn.createStatement();
			for (String sql : sqls) {
				stmt.addBatch(sql);// 添加sql语句进入批处理
			}
			return stmt.executeBatch();
		} catch (SQLException e) {
			try {
				conn.rollback();// 事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			release();
		}
	}

	/** 6.释放资源 */
	public void release() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pps != null) {
			try {
				pps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
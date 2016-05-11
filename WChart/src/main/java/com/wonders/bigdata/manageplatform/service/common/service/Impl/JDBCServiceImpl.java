package com.wonders.bigdata.manageplatform.service.common.service.Impl;

import com.wonders.bigdata.manageplatform.service.common.service.JDBCService;
import com.wonders.bigdata.manageplatform.utils.JdbcConnection;
import com.wonders.bigdata.manageplatform.utils.Messages;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: manageplatform_[JDBC]
 * </p>
 * <p>
 * Description: [JDBCservice实现层]
 * </p>
 *
 * @author WF
 * @version $Revision$ 2015-3-16
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
@Service("jDBCServiceImpl")
public class  JDBCServiceImpl implements JDBCService {

	@Override
	public List<String> getTableName(String driver, String url, String userName, String password) {
		List<String> tableList = new ArrayList<String>();
		Connection connection = JdbcConnection.getConnection(driver, url, userName, password);
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql = Messages.getString("mysql_show_tables");
		if (driver.equals(Messages.getString("jdbc_oracle_driver"))) {
			sql = Messages.getString("oracle_show_tables");
		}
		else if (driver.equals(Messages.getString("jdbc_sybase_driver"))) {
			sql = Messages.getString("sybase_show_tables");
		}
		else if (driver.equals(Messages.getString("jdbc_sqlserver_driver"))) {
			sql = Messages.getString("sqlserver_show_tables");
		}
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				tableList.add(resultSet.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JdbcConnection.closeConnection(connection, pStatement, resultSet);
		}
		return tableList;
	}

	@Override
	public List<String> getColumnName(String driver, String url, String userName, String password, String tableName) {
		List<String> columnList = new ArrayList<String>();
		Connection connection = JdbcConnection.getConnection(driver, url, userName, password);
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql = Messages.getString("mysql_desc_table") + " " + tableName;
		if (driver.equals(Messages.getString("jdbc_oracle_driver"))) {
			sql = "select * from  " + tableName + " where rownum<=1";
		}
		else if (driver.equals(Messages.getString("jdbc_sybase_driver"))) {
			// sql = "sp_help " + tableName;
			sql = "select * from " + tableName;
		}
		else if (driver.equals(Messages.getString("jdbc_sqlserver_driver"))) {
			// sql = Messages.getString("sqlserver_desc_table") + tableName;
			sql = " select top 1 * from " + tableName;
		}
		try {
			pStatement = connection.prepareStatement(sql);

			resultSet = pStatement.executeQuery();
			int currNum = 0;
			while (resultSet.next()) {
				if (driver.equals(Messages.getString("jdbc_mysql_driver"))) {
					columnList.add(resultSet.getString("Field"));
				}
				else if (driver.equals(Messages.getString("jdbc_oracle_driver"))
						|| driver.equals(Messages.getString("jdbc_sybase_driver"))
						|| driver.equals(Messages.getString("jdbc_sqlserver_driver"))) {
					ResultSetMetaData data = resultSet.getMetaData();
					int count = data.getColumnCount();
					for (int i = 1; i <= count; i++) {
						String coluName = data.getColumnName(i);
						columnList.add(coluName);
					}
					if (driver.equals(Messages.getString("jdbc_sybase_driver"))) {
						currNum++;
						if (currNum >= 1) {
							break;
						}
					}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JdbcConnection.closeConnection(connection, pStatement, resultSet);
		}

		return columnList;
	}

	@Override
	public List<String> getColumnValues(String tableName, String colName) {
		String jdbcHiveUrl = Messages.getString("jdbcHiveUrl");
		String hiveUser = Messages.getString("hiveUser");
		String hivePwd = Messages.getString("hivePwd");
		String sql = "select distinct " + colName + " from " + tableName;
		try {
			final String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
			Connection con;
			con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
			final Statement stmt = con.createStatement();
			final ResultSet datSet = stmt.executeQuery(sql);
			List<String> values = new ArrayList<String>();
			while (datSet.next()) {
				values.add(datSet.getString(1));
			}
			return values;
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<ArrayList<String>> getDatas(String driver, String url, String userName, String password,
												 String tableName, List<String> columns, int pageNum, int pageSize, String sortType) {

		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		if (sortType == null || sortType.equals("") || sortType.equals(" "))
			sortType = "asc";
		int start = 0;
		int end = 10;
		if (pageSize == 0)
			pageSize = 1;
		if (pageNum == 0)
			pageNum = 10;
		start = (pageSize - 1) * pageNum;
		end = pageNum * pageSize;

		Connection connection = JdbcConnection.getConnection(driver, url, userName, password);

		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql = "select * from " + tableName + " limit " + start + "," + end;
		if (driver.equals(Messages.getString("jdbc_oracle_driver"))) {
			sql = "select * from " + tableName + " where rownum<=" + end;
		}
		else if (driver.equals(Messages.getString("jdbc_sybase_driver"))) {
			sql = "select * from " + tableName;
		}
		else if (driver.equals(Messages.getString("jdbc_sqlserver_driver"))) {
			sql = "select top " + end + " * from " + tableName;
		}
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			int currNum = 0;
			while (resultSet.next()) {
				ArrayList<String> cols = new ArrayList<String>();
				for (int c = 0; c < columns.size(); c++) {
					cols.add(resultSet.getString(columns.get(c)));
				}
				datas.add(cols);
				if (driver.equals(Messages.getString("jdbc_sybase_driver"))) {
					currNum++;
					if (currNum == end)
						break;
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JdbcConnection.closeConnection(connection, pStatement, resultSet);
		}
		return datas;
	}

	@Override
	public List<List<String>> executeSQL(String sql) {
		List<List<String>> list = new ArrayList<>();

		String driver = Messages.getString("jdbc_mysql_driver");
		String url = Messages.getString("mysql_url");
		String userName = Messages.getString("mysql_user");
		String password = Messages.getString("mysql_passwd");
		Connection connection = JdbcConnection.getConnection(driver, url, userName, password);
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				List<String> newli = new ArrayList<>();
				newli.add(resultSet.getString(1));
				newli.add(resultSet.getString(2));
				list.add(newli);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcConnection.closeConnection(connection, pStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> query(String sql) {
		String driver = Messages.getString("jdbc_mysql_driver");
		String url = Messages.getString("mysql_url");
		String userName = Messages.getString("mysql_user");
		String password = Messages.getString("mysql_passwd");
		Connection connection = JdbcConnection.getConnection(driver, url, userName, password);
		Statement stmt = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSet rs = null;
		try {

			stmt = connection.createStatement();
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
			JdbcConnection.closeConnection(connection, stmt, rs);
		}
		return list;
	}

}

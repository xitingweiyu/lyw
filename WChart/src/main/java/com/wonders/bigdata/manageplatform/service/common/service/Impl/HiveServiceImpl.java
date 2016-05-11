package com.wonders.bigdata.manageplatform.service.common.service.Impl;

import com.wonders.bigdata.manageplatform.service.common.service.HiveService;
import com.wonders.bigdata.manageplatform.utils.Messages;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * <p>
 * Title: manageplatform_[Hive]
 * </p>
 * <p>
 * Description: [HiveService实现层]
 * </p>
 * 
 * @author GLJ
 * @version $Revision$ 2015-03-18
 * @author (latest modification by $Author$)
 * @since 20130601
 */
@Service("hiveServiceImpl")
public class  HiveServiceImpl implements HiveService {

	// TODO 如何与.properties文件关联？
	private static String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");

	private static String jdbcHiveUrl = Messages.getString("jdbcHiveUrl");

	private static String hiveUser = Messages.getString("hiveUser");

	private static String hivePwd = Messages.getString("hivePwd");

	public HiveServiceImpl() {
	}

	@Override
	public boolean existTable(String table) throws SQLException {
		ArrayList<String> allTable = this.getTables();
		boolean flag = false;
		for (int i = 0; i < allTable.size(); i++) {
			if (allTable.get(i).equals(table)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public boolean createTableAsSelect(String targetTableName, String select) throws SQLException {
		String create = "CREATE TABLE " + targetTableName;
		String option = " row format delimited fields terminated by '\001' "; // you can change it
		String as = " AS " + select; // here you can decide which column, table to select, join table or more
										// comprehension clause

		String sql = create + option + as;
		System.out.println("Running: " + sql);
		try {
			Class.forName(jdbcHiveDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
		java.sql.Statement stmt = con.createStatement();
		stmt.execute(sql);
		stmt.close();
		con.close();
		return true;
	}

	@Override
	public void deleteHiveTrueTable(String tableName) throws SQLException {
		String deleteSql = "drop table if exists " + tableName;
		System.out.println("Running: " + deleteSql);
		try {
			Class.forName(jdbcHiveDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
		java.sql.Statement stmt = con.createStatement();
		stmt.execute(deleteSql);
		stmt.close();
		con.close();
	}

	@Override
	public String[] getHiveTableFilePathAndSize(String tableName) throws SQLException {
		String[] pathAndSize = new String[2];
		System.setProperty("HADOOP_USER_NAME", Messages.getString("hiveUser"));
		String rootPath = Messages.getString("HDFSUrl");
		try {
			Class.forName(jdbcHiveDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
		java.sql.Statement stmt = con.createStatement();
		String sql = "describe formatted " + tableName;
		ResultSet set = stmt.executeQuery(sql);
		while (set.next()) {
			String location = set.getString(1);
			if (location != null && "Location:".equals(location.replace(" ", "")))
				pathAndSize[0] = set.getString(2);
			String totalSize = set.getString(2);
			if (totalSize != null && "totalSize".equals(totalSize.replace(" ", "")))
				pathAndSize[1] = set.getString(3);
		}

		// 由于hive创建的是外表，对path和siz进行处理
		// 将path中的节点信息改为port
		if (pathAndSize[0] != null && !pathAndSize[0].contains(rootPath)) {
			String path = pathAndSize[0];
			String[] paths = path.split("://");
			if (paths.length > 1) {
				String dfs = paths[1];
				String[] filPaths = dfs.split("/");
				if (filPaths.length > 0) {
					String f = filPaths[0];
					path = dfs.replace(f, rootPath);
					pathAndSize[0] = path;

				}
			}
		}
		// hive外表不能获取size的处理
		if (pathAndSize[1] == null || pathAndSize[1].equals("")) {
			if (pathAndSize[0] != null) {
				String path = pathAndSize[0];
				Path p = new Path(path);
				long total = 0;
				Configuration conf = new Configuration();
				try {
					FileSystem fs = p.getFileSystem(conf);
					boolean isHad = fs.exists(p);
					if (isHad) {
						RemoteIterator<LocatedFileStatus> fd = fs.listFiles(p, true);// 获取文件夹下所有文件
						while (fd.hasNext()) {
							LocatedFileStatus lf = fd.next();// 获取文件
							System.out.println(lf.getLen());
							total = total + lf.getLen();// 文件大小
						}
					}
					pathAndSize[1] = total + "";
					fs.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		set.close();
		stmt.close();
		con.close();
		return pathAndSize;
	}

	@Override
	public ArrayList<String[]> searchBySelcet(String selectSql) throws SQLException {
		ArrayList<String[]> datas = new ArrayList<String[]>();
		try {
			final String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con;
		con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
		final Statement stmt = con.createStatement();
		ResultSet datSet = null;
		try {
			datSet = stmt.executeQuery(selectSql + " limit 3");
		}
		catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new SQLException(e.getCause());
			}
		}
		final ResultSetMetaData col = datSet.getMetaData();
		final int count = col.getColumnCount();
		final String[] cols = new String[count];
		for (int i = 1; i <= count; i++) {
			final String cloName = col.getColumnName(i);
			cols[i - 1] = cloName;
		}
		datas.add(cols);
		while (datSet.next()) {
			final String[] colDatas = new String[count];
			for (int j = 1; j <= count; j++) {
				colDatas[j - 1] = datSet.getString(j);
			}
			datas.add(colDatas);
		}
		stmt.close();
		con.close();
		return datas;
	}

	@Override
	public List<Map<String, String>> getHiveColunmsByTableName(String hiveurl, String userName, String password,
			String tableName) {
		List<Map<String, String>> colsAndType = new ArrayList<Map<String, String>>();
		try {
			String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con;
		try {
			con = DriverManager.getConnection(hiveurl, userName, password);
			Statement stmt = con.createStatement();
			String sql = "desc " + tableName;
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				Map<String, String> map = new HashMap<String, String>();
				String colunm = resultSet.getString(1);
				String type = resultSet.getString(2);
				map.put("column", colunm);
				map.put("type", type);
				colsAndType.add(map);
			}
			stmt.close();
			con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return colsAndType;
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

	/*
	 * 得到所有表
	 */
	private ArrayList<String> getTables() throws SQLException {
		try {
			Class.forName(jdbcHiveDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		Connection con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
		java.sql.Statement stmt = con.createStatement();
		if (stmt == null)
			return null;
		String sql = "show tables";
		ArrayList<String> result = new ArrayList<String>();
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			result.add(res.getString(1));
		}
		stmt.close();
		con.close();
		return result;
	}
	
	@Override
	public List<Map<String, String[]>> getTablesColNamHasTables(String url, long resourceId,
			String userName, String password,String goOnTableName,String[] hasTableList){
		List<Map<String, String[]>> tableList = new LinkedList<Map<String, String[]>>();
		if(hasTableList.length == 0) {
			return tableList;
		}
		try {
			 String jdbcMysqlDriver = Messages.getString("jdbc_mysql_driver");
			 if(url.contains("jdbc:oracle")){
				 jdbcMysqlDriver = Messages.getString("jdbc_oracle_driver");
			 }else if(url.contains("jdbc:sqlserver")){
				 jdbcMysqlDriver = Messages.getString("jdbc_sqlserver_driver");
			 } else if (url.contains("jdbc:sybase:Tds")) {
				 jdbcMysqlDriver = Messages.getString("jdbc_sybase_driver");
			}
			Class.forName(jdbcMysqlDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		Connection con;
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement stmt = con.createStatement();
			Boolean id = false;
			for(String tableName : hasTableList){
				Map<String,String[]> map = new HashMap<String, String[]>();
				String sql=null;
				if(url.contains("jdbc:oracle")){
					sql = "select * from  " + tableName + " where rownum<=1";
				}else if(url.contains("jdbc:sqlserver")||url.contains("jdbc:sybase:Tds")){
					sql = "select top 1 * from " + tableName;
				}else{
					sql = "select * from " + tableName + " limit 1";
				}
				ResultSet colsSet = stmt.executeQuery(sql);
				ResultSetMetaData data = colsSet.getMetaData();
				int count = data.getColumnCount();
				String[] resourceIds = {resourceId+""};
				String[] cols = new String[count];
				String[] parColumn = {""};
				for(int i=1;i<=count;i++){
					String cloName = data.getColumnName(i);
					String cloType = data.getColumnTypeName(i);
					cols[i-1] = cloName + " String";
					if(parColumn[0].equals("")){
						if(!cloType.equals("DATE")){
							parColumn[0] = cloName;
						}
					}
				}
				if(goOnTableName == null || goOnTableName.equals("")||goOnTableName.equals(" ")){
					id = true;
				}else{
					if(tableName.equals(goOnTableName))
						id=true;
				}
				if(id){
					map.put(tableName, cols);
					String[] talbelNames = {tableName};
					map.put("tableName", talbelNames);
					map.put("resourceId", resourceIds);
					map.put("partitionColumn", parColumn);
					tableList.add(map);
				}
			}
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableList;
		
	}

	@Override
	public String executeSql(String select) throws SQLException {
		ArrayList<String[]> datas = new ArrayList<String[]>();
		String result = "";
		try {
			final String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con;
		con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
		final Statement stmt = con.createStatement();
		ResultSet datSet = null;
		try {
			datSet = stmt.executeQuery(select);
			while (datSet.next()) {
				result = datSet.getString(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			if (e instanceof SQLException) {
				throw new SQLException(e.getCause());
			}
		}

		stmt.close();
		con.close();
		return result;
	}

	@Override
	public List<String> getTablesColName(String url, long resourceId, String userName,
			String password, String goOnTableName) {
		List<String> tableList = new LinkedList<String>();
		if (url.contains("jdbc:sybase:Tds")) {
			tableList = this.getColNameOfSybase(url, resourceId, userName, password, goOnTableName);
			return tableList;
		}
		try {
			String jdbcMysqlDriver = Messages.getString("jdbc_mysql_driver");
			if (url.contains("jdbc:oracle")) {
				jdbcMysqlDriver = Messages.getString("jdbc_oracle_driver");
			}
			else if (url.contains("jdbc:sqlserver")) {
				jdbcMysqlDriver = Messages.getString("jdbc_sqlserver_driver");
			}
			Class.forName(jdbcMysqlDriver);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con;
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement stmt = con.createStatement();
			ResultSet tableSet = null;
			PreparedStatement pStatement = null;
			if (url.contains("jdbc:oracle")) {
				String sql1 = Messages.getString("oracle_show_tables");
				pStatement = con.prepareStatement(sql1);
				tableSet = pStatement.executeQuery();
			}
			else if (url.contains("jdbc:sqlserver")) {
				String sql2 = Messages.getString("sqlserver_show_tables");
				pStatement = con.prepareStatement(sql2);
				tableSet = pStatement.executeQuery();
			}
			else {
				tableSet = con.getMetaData().getTables("", "", "", null);
			}
			Boolean id = false;
			while (tableSet.next()) {
				String tableName = null;
				if (url.contains("jdbc:oracle")) {
					tableName = tableSet.getString(1);
				} else if (url.contains("jdbc:sqlserver")) {
					tableName = tableSet.getString(1);
				} else {
					tableName = tableSet.getString("TABLE_NAME");
				}
				if (goOnTableName == null || goOnTableName.equals("") || goOnTableName.equals(" ")) {
					id = true;
				} else {
					if (tableName.equals(goOnTableName))
						id = true;
				}
				if (id) {
					tableList.add(tableName);
				}
			}
			stmt.close();
			con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return tableList;
	}
	
	private List<String> getColNameOfSybase(String url, long resourceId, String userName,
			String password, String goOnTableName) {
		List<String> tableList = new LinkedList<String>();
		String jdbcMysqlDriver = Messages.getString("jdbc_sybase_driver");
		try {
			Class.forName(jdbcMysqlDriver);
			String sql = Messages.getString("sybase_show_tables");
			Connection con = DriverManager.getConnection(url, userName, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pStatement = con.prepareStatement(sql);
			ResultSet tableSet = pStatement.executeQuery();
			Boolean id = false;
			while (tableSet.next()) {
				String tableName = tableSet.getString("TABLE_NAME");
				if (goOnTableName == null || goOnTableName.equals("") || goOnTableName.equals(" ")) {
					id = true;
				}
				else {
					if (tableName.equals(goOnTableName))
						id = true;
				}
				if (id) {
					tableList.add(tableName);
				}
			}
			stmt.close();
			con.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return tableList;
	}
	
	@Override
	public List<String> getViewsColName(String url, long resourceId, String userName,
			String password, String schemaName, String goOnViewName) {
		List<String> viewList = new LinkedList<String>();
		if (url.contains("jdbc:sybase:Tds")) {
			viewList = getSybaseView(url, resourceId, userName, password, goOnViewName);
			return viewList;
		}
		try {
			String jdbcMysqlDriver = Messages.getString("jdbc_mysql_driver");
			if (url.contains("jdbc:oracle")) {
				jdbcMysqlDriver = Messages.getString("jdbc_oracle_driver");
			} else if (url.contains("jdbc:sqlserver")) {
				jdbcMysqlDriver = Messages.getString("jdbc_sqlserver_driver");
			}
			Class.forName(jdbcMysqlDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con;
		try {
			con = DriverManager.getConnection(url, userName, password);
			Statement stmt = con.createStatement();
			ResultSet viewSet = null;
			PreparedStatement pStatement = null;
			String vn = "name";
			if (url.contains("jdbc:oracle")) {
				String sql1 = Messages.getString("oracle_show_views");
				pStatement = con.prepareStatement(sql1);
				viewSet = pStatement.executeQuery();
			} else if (url.contains("jdbc:sqlserver")) {
				String sql2 = Messages.getString("sqlserver_show_views");
				pStatement = con.prepareStatement(sql2);
				viewSet = pStatement.executeQuery();
			} else {
				String sql3 = Messages.getString("mysql_show_views") +"'"+ schemaName + "'";
				pStatement = con.prepareStatement(sql3);
				viewSet = pStatement.executeQuery();
				vn = "table_name";
			}
			Boolean id = false;
			while (viewSet.next()) {
				String tableName = viewSet.getString(vn);
				if (goOnViewName == null || goOnViewName.equals("") || goOnViewName.equals(" ")) {
					id = true;
				}
				else {
					if (tableName.equals(goOnViewName))
						id = true;
				}
				if (id) {
					viewList.add(tableName);
				}
			}

			stmt.close();
			con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return viewList;
	}
	
	private List<String> getSybaseView(String url, long resourceId, String userName,
			String password, String goOnTableName) {
		List<String> viewList = new LinkedList<String>();
		String jdbcMysqlDriver = Messages.getString("jdbc_sybase_driver");
		try {
			Class.forName(jdbcMysqlDriver);
			String sql = Messages.getString("sybase_show_views") + "'sysquerymetrics'" ;
			Connection con = DriverManager.getConnection(url, userName, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pStatement = con.prepareStatement(sql);
			ResultSet tableSet = pStatement.executeQuery();
			Boolean id = false;
			while (tableSet.next()) {
				String tableName = tableSet.getString("name");
				if (goOnTableName == null || goOnTableName.equals("") || goOnTableName.equals(" ")) {
					id = true;
				}
				else {
					if (tableName.equals(goOnTableName))
						id = true;
				}
				if (id) {
					viewList.add(tableName);
				}
			}
			stmt.close();
			con.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return viewList;
	}

	@Override
	public String createHiveTable(Map<String, String[]> map, String hiveUrl, String HDFSPAth) throws SQLException {

		String hiveUser = Messages.getString("hiveUser");
		String hivePwd = Messages.getString("hivePwd");
		System.setProperty("HADOOP_USER_NAME", hiveUser);
		if (map == null || map.get("tableName") == null || map.get("tableName").length == 0)
			return null;
		try {
			String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con;
		String resourceId = map.get("resourceId")[0];
		String tableName = map.get("tableName")[0];
		con = DriverManager.getConnection(hiveUrl, hiveUser, hivePwd);
		Statement stmt = con.createStatement();
		String[] cols = map.get(tableName);
		String table = resourceId + "_" + tableName;
		try {
			deleteFdfsByHiveTable(table);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 同时删除对应的hdfs文件，因为是建外表
		String dropIfExistsTable = "drop table if exists " + table;
		stmt.execute(dropIfExistsTable);
		String createSql = "create external table " + table + "(";
		if (cols != null && cols.length > 0) {
			for (int i = 0; i < cols.length; i++) {
				if (i == 0) {
					createSql = createSql + cols[i];
				}
				else {
					createSql = createSql + "," + cols[i];
				}
			}
		}
		createSql = createSql + ") ROW FORMAT DELIMITED FIELDS TERMINATED BY '\001' LOCATION ";

		createSql =
				createSql.replace("VARCHAR", "String").replace("DATETIME", "DATE").replace("CHAR", "String")
						.replace("TIMESTAMP", "DATE").replace("BIT", "BIGINT");
		createSql = createSql + " '" + HDFSPAth + "'";
		stmt.execute(createSql);
		// if(createB==false){
		// throw new SQLException("create fail");
		// }
		// String loadData = "LOAD DATA  INPATH '"+ HDFSPAth + "' INTO TABLE " + table;
		// stmt.execute(loadData);
		// if(loadB == false){
		// throw new SQLException("load data fail");
		// }
		// deteleHDFSFile(HDFSPAth);
		// rs.close();
		// createSet.close();
		stmt.close();
		con.close();

		//
		return table;
	}

	/**
	 * 根据表名删除该hive表对应的hdfs文件，主要针对hive中的外表
	 * 
	 * @param tableName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean deleteFdfsByHiveTable(String tableName) throws ClassNotFoundException, SQLException {
		boolean b = false;
		String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
		String jdbcHiveUrl = Messages.getString("jdbcHiveUrl");
		String hiveUser = Messages.getString("hiveUser");
		String hivePwd = Messages.getString("hivePwd");
		String sqoopOutput = Messages.getString("sqoopOutput");
		String HDFSpath = Messages.getString("HDFSpath");
		System.setProperty("HADOOP_USER_NAME", Messages.getString("hiveUser"));
		String rootPath = Messages.getString("HDFSUrl");
		Class.forName(jdbcHiveDriver);
		String path = null;
		Connection con = DriverManager.getConnection(jdbcHiveUrl, hiveUser, hivePwd);
		java.sql.Statement stmt = con.createStatement();
		// 判断该表是否存在
		String sqlHad = "show tables '" + tableName + "'";
		ResultSet had = stmt.executeQuery(sqlHad);
		if (!had.next()) {
			return true;
		}
		String sql = "describe formatted " + tableName;
		ResultSet set = stmt.executeQuery(sql);
		while (set.next()) {
			String location = set.getString(1);
			if (location != null && "Location:".equals(location.replace(" ", "")))
				path = set.getString(2);
		}
		set.close();
		stmt.close();
		con.close();
		if (path != null) {
			String[] paths = null;
			if (path.contains(sqoopOutput)) {
				paths = path.split(sqoopOutput);
			}
			else if (path.contains(HDFSpath)) {
				paths = path.split(HDFSpath);
			}
			if (paths != null && paths.length > 0) {
				String dfs = paths[0];
				path = path.replace(dfs, rootPath);
				Path p = new Path(path);
				Configuration conf = new Configuration();
				try {
					FileSystem fs = p.getFileSystem(conf);
					boolean isHad = fs.exists(p);
					if (isHad) {
						b = fs.delete(p, true);
					}
					else {
						b = true;
					}
					// boolean b = fs.createNewFile(p);
					fs.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return b;
	}

	@Override
	public boolean isExistHFDSFile(String filePath) {
		boolean exist = false;
		System.setProperty("HADOOP_USER_NAME", Messages.getString("hiveUser"));
		Path p = new Path(filePath);
		Configuration conf = new Configuration();
		FileSystem fs;
		try {
			fs = p.getFileSystem(conf);
			boolean is = fs.exists(p);
			if (is) {
				exist = true;
			}
			fs.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return exist;
	}

	@Override
	public boolean isExistHiveTable(String tableName) throws SQLException {
		String hiveUser = Messages.getString("hiveUser");
		String hivePwd = Messages.getString("hivePwd");
		String hiveUrl = Messages.getString("jdbcHiveUrl");
		System.setProperty("HADOOP_USER_NAME", hiveUser);
		boolean exist = false;
		if (tableName == null || tableName.trim().equals(""))
			return false;
		try {
			String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con;
		con = DriverManager.getConnection(hiveUrl, hiveUser, hivePwd);
		Statement stmt = con.createStatement();

		String showTablesql = "show tables '" + tableName + "'";
		ResultSet tableSet = stmt.executeQuery(showTablesql);
		if (tableSet.next()) {
			exist = true;
		}
		return exist;
	}

	@Override
	public String upFileToHDFS(String localPath, String targetPath) {
		String message = "ok";
		String rootPath = Messages.getString("HDFSUrl");
		System.setProperty("HADOOP_USER_NAME", Messages.getString("hiveUser"));
		Path newPath = new Path(localPath);
		String fileString = newPath.getName();
		if (fileString.contains("."))
			fileString = fileString.split("\\.")[0];
		Path p = new Path(rootPath + targetPath + "/" + fileString);
		Configuration conf = new Configuration();
		FileSystem fs;
		try {
			fs = p.getFileSystem(conf);
			if (!fs.exists(p))
				fs.mkdirs(p);
			String targetFilePathString = p.toString() + "/" + newPath.getName();
			Path targetFilePath = new Path(targetFilePathString);
			boolean is = fs.exists(targetFilePath);
			if (is) {
				message = "exist";
				return message;
			}
			fs.copyFromLocalFile(newPath, p);
			fs.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return message;
	}

	@Override
	public String createHiveTableForPer(String tableName, String columnAndTypes, String HDFSPAth, String tableSeperator)
			throws SQLException {
		String hiveUser = Messages.getString("hiveUser");
		String hivePwd = Messages.getString("hivePwd");
		String hiveUrl = Messages.getString("jdbcHiveUrl");
		System.setProperty("HADOOP_USER_NAME", hiveUser);
		if (tableName == null || tableName.trim().equals("") || columnAndTypes == null
				|| columnAndTypes.trim().equals(""))
			return null;
		try {
			String jdbcHiveDriver = Messages.getString("jdbcHiveDriver");
			Class.forName(jdbcHiveDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con;
		con = DriverManager.getConnection(hiveUrl, hiveUser, hivePwd);
		Statement stmt = con.createStatement();
		String table = tableName;

		String showTablesql = "show tables '" + table + "'";
		ResultSet tableSet = stmt.executeQuery(showTablesql);
		if (tableSet.next()) {
			return "exist";
		}

		String createSql = "create external table " + table + "(";
		createSql = createSql + columnAndTypes;
		createSql = createSql + ") ROW FORMAT DELIMITED FIELDS TERMINATED BY '" + tableSeperator + "' LOCATION ";

		createSql =
				createSql.replace("VARCHAR", "String").replace("DATETIME", "DATE").replace("CHAR", "String")
						.replace("TIMESTAMP", "DATE").replace("BIT", "BIGINT");
		createSql = createSql + " '" + HDFSPAth + "'";
		stmt.execute(createSql);
		stmt.close();
		con.close();

		return table;
	}
	
	@Override
	public String genPreviewData(String sql){
		try {
			ArrayList<String[]> datas = this.searchBySelcet(sql);
			if(datas!=null&&datas.size()>0){
				String data = "";
				for(int c=0;c<datas.size();c++){
					if(c==0){
						data = data + "<tr>";
						String[] rows = datas.get(c);
						for(int r=0;r<rows.length;r++){
							String row = rows[r];
							if(row.contains(".")){
								row = row.split("\\.")[1];
							}
							data = data + "<th>" + row + "</th>";
						}
						data = data + "</tr>";
					}else{
						data = data + "<tr>";
						String[] rows = datas.get(c);
						for(int r=0;r<rows.length;r++){
							String row = rows[r];
							data = data + "<td>" + row + "</td>";
						}
						data = data + "</tr>";
					}
				}
				return data;
			}else{
				return "select erro";
			}
		} catch (SQLException e) {
			return "select erro";
		}
	}
}

package com.wonders.bigdata.manageplatform.service.common.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: manageplatform_[Hive]
 * </p>
 * <p>
 * Description: [HiveService接口]
 * </p>
 * 
 * @author GLJ
 * @version $Revision$ 2015-03-18
 * @author (latest modification by $Author$)
 * @since 20130601
 */
public interface  HiveService {

	/**
	 * <p>
	 * Description:[判断hive中该表名是否存在]
	 * </p>
	 * Created by [GLJ] [2015-03-17] Modified by [修改人] [修改时间]
	 * 
	 * @param table
	 * @return 存在则返回TRUE
	 */
	public boolean existTable(String table) throws SQLException;

	/**
	 * <p>
	 * Description:[根据select sql语句在hive中生成一个表]
	 * </p>
	 * Created by [GLJ] [2015-03-17] Modified by [修改人] [修改时间]
	 * 
	 * @param targetTableName
	 * @param select
	 * @return false是表已经存在，TRUE是创建成功
	 */
	public boolean createTableAsSelect(String targetTableName, String select) throws SQLException;

	/**
	 * <p>
	 * Description:[根据表名删除hive的表]
	 * </p>
	 * Created by [GLJ] [2015-03-17] Modified by [修改人] [修改时间]
	 * 
	 * @param tableName
	 */
	public void deleteHiveTrueTable(String tableName) throws SQLException;

	/**
	 * <p>
	 * Description:[根据hive的tableName获取该table对应的HDFS文件的大小以及物理地址]
	 * </p>
	 * Created by [GLJ] [2015-03-17] Modified by [修改人] [修改时间]
	 * 
	 * @param tableName
	 */
	public String[] getHiveTableFilePathAndSize(String tableName) throws SQLException;

	/**
	 * <p>
	 * Description:[根据select语句在hive中查询，预览10条数据]
	 * </p>
	 * Created by [GLJ] [2015-03-17] Modified by [修改人] [修改时间]
	 * 
	 * @param selectSql
	 * @return 包括字段名和数据
	 */
	public ArrayList<String[]> searchBySelcet(String selectSql) throws SQLException;

	/**
	 * <p>
	 * Description:[根据tableName在hive中查询该表的所有字段以及字段类型]
	 * </p>
	 * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param hiveurl
	 * @param userName
	 * @param password
	 * @param tableName
	 * @return list<map> map 中通过“column”去获取字段，通过“type”获取字段类型。
	 */
	public List<Map<String, String>> getHiveColunmsByTableName(String hiveurl, String userName, String password,
			String tableName);

	/**
	 * <p>
	 * Description:[根据表名和字段名查询数据]
	 * </p>
	 * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param tableName 表名
	 * @param colName 字段名
	 * @return
	 */
	public List<String> getColumnValues(String tableName, String colName);

	/**
	 * <p>
	 * Description:[根据数据库连接信息获取元数据结构]
	 * </p>
	 * Created by [CSJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param url 连接字符串
	 * @param userName 用户名
	 * @param password 密码
	 * @param goOnTableName 断点执行表名
	 * @param resourceId 资源Id
	 * @return
	 */
	public List<String> getTablesColName(String url, long resourceId, String userName,
			String password, String goOnTableName);

	/**
	 * <p>
	 * Description:[根据数据库元数据结构和HDFS中的数据创建Hive表]
	 * </p>
	 * Created by [CSJ] [2015-03-23] Modified by [修改人] [修改时间]
	 * 
	 * @param map 元数据结构
	 * @param hiveUrl Hive地址
	 * @param HDFSPath HDFS文件路径
	 * @return
	 */
	public String createHiveTable(Map<String, String[]> map, String hiveUrl, String HDFSPAth) throws SQLException;

	/**
	 * <p>
	 * Description:[判断HFDS文件是否存在]
	 * </p>
	 * Created by [GLJ] [2015-04-02] Modified by [修改人] [修改时间]
	 * 
	 * @param filePath 文件路径
	 * @return
	 */
	public boolean isExistHFDSFile(String filePath);

	/**
	 * <p>
	 * Description:[判断hive表是否存在]
	 * </p>
	 * Created by [GLJ] [2015-04-02] Modified by [修改人] [修改时间]
	 * 
	 * @param tableName 表名
	 * @return
	 */
	public boolean isExistHiveTable(String tableName) throws SQLException;

	/**
	 * <p>
	 * Description:[将服务器上的文件上传到HDFS文件系统中]
	 * </p>
	 * Created by [GLJ] [2015-04-02] Modified by [修改人] [修改时间]
	 * 
	 * @param localPath 服务器上的文件路径
	 * @param targetPath HDFS路径
	 * @return
	 */
	public String upFileToHDFS(String localPath, String targetPath);

	/**
	 * <p>
	 * Description:[为私有包创建hive表]
	 * </p>
	 * Created by [GLJ] [2015-04-02] Modified by [修改人] [修改时间]
	 * 
	 * @param tableName要创建的表名
	 * @param columnAndTypes字段和字段类型，字段间用“，”分隔，字段与类型之间使用“ ”（空格）分隔
	 * @param HDFSPAth 需要导入hive表的文件地址
	 * @param tableSeperator 分隔符
	 * @return
	 */
	public String createHiveTableForPer(String tableName, String columnAndTypes, String HDFSPAth, String tableSeperator)
			throws SQLException;

	/**
	 * <p>
	 * Description:[根据表名删除该hive表对应的hdfs文件，主要针对hive中的外表]
	 * </p>
	 * Created by [GLJ] [2015-04-17] Modified by [修改人] [修改时间]
	 * 
	 * @param tableName 表名
	 * @return boolean
	 */
	public boolean deleteFdfsByHiveTable(String tableName) throws ClassNotFoundException, SQLException;
	
	/**
	 * <p>
	 * Description:[根据SQL语句来获取预览数据字符串]
	 * </p>
	 * Created by [CSJ] [2015-05-20] Modified by [修改人] [修改时间]
	 * 
	 * @param SQL
	 * @return String
	 */
	public String genPreviewData(String sql);

	/**
	 * <p>
	 * Description:[根据数据库连接信息获取视图元数据结构]
	 * </p>
	 * Created by [CSJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param url 连接字符串
	 * @param userName 用户名
	 * @param password 密码
	 * @param goOnViewName 断点执行表名
	 * @param resourceId 资源Id
	 * @return
	 */
	public List<String> getViewsColName(String url, long resourceId,
			String userName, String password, String schemaName, String goOnViewName);

	/**
	 * <p>
	 * Description:[根据选择的表名和视图名查询其元数据结构]
	 * </p>
	 * Created by [CSJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param url 连接字符串
	 * @param resourceId 数据源编号
	 * @param userName 用户名
	 * @param password 密码
	 * @param goOnViewName 断点执行表名
	 * @param hasTables 选择的表名和视图名
	 * @return
	 */
	public List<Map<String, String[]>> getTablesColNamHasTables(String url,
			long resourceId, String userName, String password,
			String goOnTableName, String[] hasTables);



	/**
	 * <p>
	 * Description:[根据select sql语句查询]
	 * </p>
	 * Created by WYP 2016-4-27
	 *
	 * @param select
	 */
	public String executeSql(String select) throws SQLException;
}

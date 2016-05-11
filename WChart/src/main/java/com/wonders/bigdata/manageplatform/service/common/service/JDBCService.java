package com.wonders.bigdata.manageplatform.service.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: manageplatform_[JDBC]
 * </p>
 * <p>
 * Description: [JDBCService接口]
 * </p>
 *
 * @author WF
 * @version $Revision$ 2015-03-20
 * @author (latest modification by $Author$)
 * @since 20130601
 */
public interface  JDBCService {

	/**
	 * <p>
	 * Description:[获取数据库中所有表名]
	 * </p>
	 * Created by [WF] [2015-03-20] Modified by [修改人] [修改时间]
	 *
	 * @param String driver, String url, String userName, String password
	 * @return List<String>
	 */
	public List<String> getTableName(String driver, String url, String userName, String password);

	/**
	 * <p>
	 * Description:[获取数据库 表中所有字段名]
	 * </p>
	 * Created by [WF] [2015-03-20] Modified by [修改人] [修改时间]
	 *
	 * @param String driver, String url, String userName, String password, String tableName
	 * @return List<String>
	 */
	public List<String> getColumnName(String driver, String url, String userName, String password, String tableName);

	/**
	 * <p>
	 * Description:[获取数据库 表中所有字段值]
	 * </p>
	 * Created by [WF] [2015-03-20] Modified by [修改人] [修改时间]
	 *
	 * @param String tableName, String colName
	 * @return List<String>
	 */
	public List<String> getColumnValues(String tableName, String colName);

	/**
	 * <p>
	 * Description:[获取数据]
	 * </p>
	 * Created by [WF] [2015-03-20] Modified by [修改人] [修改时间]
	 *
	 * @param String driver, String url, String userName, String password, String tableName, List<String> columns, int
	 *            pageNum, int pageSize, String sortType
	 * @return ArrayList<ArrayList<String>>
	 */
	public ArrayList<ArrayList<String>> getDatas(String driver, String url, String userName, String password,
												 String tableName, List<String> columns, int pageNum, int pageSize, String sortType);


	/**
	 * <p>
	 * Description:[执行sql]
	 * </p>
	 * Created by WYP
	 */
	public List<List<String>> executeSQL(String sql);


	/**
	 * <p>
	 *     执行查询的sql语句，每一行结果保存为list中的一个元素
	 * </p>
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> query(String sql);

}

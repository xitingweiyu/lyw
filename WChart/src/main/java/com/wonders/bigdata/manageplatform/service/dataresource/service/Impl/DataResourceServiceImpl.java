package com.wonders.bigdata.manageplatform.service.dataresource.service.Impl;

import com.wonders.bigdata.manageplatform.service.common.service.JDBCService;
import com.wonders.bigdata.manageplatform.service.dataresource.dao.DataResourceDao;
import com.wonders.bigdata.manageplatform.service.dataresource.model.po.DataResourcePO;
import com.wonders.bigdata.manageplatform.service.dataresource.service.DataResourceService;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataColumnPO;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataTablePO;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataColumnService;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataTableService;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.CatalogTableDatapackageService;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.StandardCatalogService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bigdata.manageplatform.utils.JdbcConnection;
import com.wonders.bigdata.manageplatform.utils.Messages;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryDataTrackParam;
import com.wonders.bud.framework.common.util.QueryParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

/**
 * <p>
 * Title: manageplatform_[数据源]
 * </p>
 * <p>
 * Description: [数据源service实现层]
 * </p>
 * 
 * @author demo
 * @version $Revision$ 2015-3-16
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
@Service("dataResourceServiceImpl")
public class  DataResourceServiceImpl implements DataResourceService {

	@Resource(name = "dataResourceDaoImpl")
	private DataResourceDao dataResourceDao;


	@Autowired
	private MetadataTableService metadataTableService;

	@Autowired
	private MetadataColumnService metadataColumnService;

	@Autowired
	private JDBCService jdbcService;
	
	@Autowired
	private StandardCatalogService standardCatalogService;
	
	@Autowired
	private CatalogTableDatapackageService catalogTableDatapackageService;

	private int MAX_FIELD_NUM = 50;// 能显示的最大字段数

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public DataResourcePO save(DataResourcePO po) {
		return dataResourceDao.save(po);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(DataResourcePO po) {
		if (po != null) {
			po.setDeleteFlag(Constant.RESOURCE_DELETE);
			dataResourceDao.save(po);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(Long id) {
		// TODO 待完善的业务逻辑
		DataResourcePO po = dataResourceDao.get(id);
		if (po != null) {
			po.setDeleteFlag(Constant.RESOURCE_DELETE);
			dataResourceDao.save(po);
		}
	}

	@Override
	public Page<DataResourcePO> findByPage(Page<DataResourcePO> page) {
		return dataResourceDao.findByPage(page);
	}

	@Override
	public List<DataResourcePO> findByList(QueryParam param) {
		setUnDeleteParam(param);
		return dataResourceDao.findByAnd(param);
	}

	@Override
	public DataResourcePO findById(Long id) {
		DataResourcePO po = dataResourceDao.get(id);
		if (po != null && po.getDeleteFlag() == Constant.RESOURCE_UN_DELETE) {
			return po;
		}
		else {
			return null;
		}
	}

	@Override
	public Long countAllResource() {
		QueryParam param = new QueryParam();
		setUnDeleteParam(param);
		return dataResourceDao.countAll(param);
	}

	/**
	 * <p>
	 * Description:[在条件中统一封装 逻辑删除]
	 * </p>
	 */
	private void setUnDeleteParam(QueryParam param) {
		if (param == null) {
			param = new QueryParam();
		}
		Map<String, Object> eq = param.getEq();
		if (eq == null) {
			eq = new HashMap<String, Object>();
		}
		eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
		param.setEq(eq);
	}

	@Override
	public List<DataResourcePO> findByType(String typeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resourceType", typeId);
		map.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
		List<DataResourcePO> list = new ArrayList<>();
		List<List<String>> l1 = new ArrayList<>();

		String sql = "select * from bd_data_resource where resource_type = "+typeId+" and delete_flag = "+Constant.RESOURCE_UN_DELETE+";";
		l1 = jdbcService.executeSQL(sql);
		for(int i = 0; i<l1.size() ; i++){
			List<String> newli = new ArrayList<>();
			newli = l1.get(i);
			DataResourcePO po = new DataResourcePO();
			po.setId(Long.parseLong(newli.get(0)));
			po.setResourceName(newli.get(1));
			list.add(po);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getDataForDataTrack(String tid, List<Long> tableIds) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		MetadataTablePO mt = metadataTableService.queryByTableId(Long.parseLong(tid));
		List<String> mtos = new ArrayList<String>();
		List<MetadataColumnPO> mcs = metadataColumnService.getAllColumnByTableId(mt.getId());
		for (MetadataColumnPO mc : mcs) {
			HashMap<String, Object> ctmp = new HashMap<String, Object>();
			mtos.add("mc" + mc.getId());
			String cRemark = mc.getRemark();
			if (cRemark == null || "".equals(cRemark) || " ".equals(cRemark)) {
				ctmp.put("name", mc.getColumnName());
			}
			else {
				ctmp.put("name", cRemark);
			}
			ctmp.put("id", "mc" + mc.getId());
			ctmp.put("type", Constant.DATATRACK_TYPE_RESOURCE_FIELD);
			ctmp.put("size", 1212);
			ctmp.put("to", new ArrayList<String>());
			ctmp.put("from", new ArrayList<String>());
			result.add(ctmp);
		}
		HashMap<String, Object> mtmp = new HashMap<String, Object>();
		String tRemark = mt.getRemark();
		if (tRemark == null || "".equals(tRemark) || " ".equals(tRemark)) {
			mtmp.put("name", mt.getTableName());
		}
		else {
			mtmp.put("name", tRemark);
		}
		mtmp.put("id", "mt" + mt.getId());
		mtmp.put("type", Constant.DATATRACK_TYPE_RESOURCE_TABLE);
		mtmp.put("size", 1212);
		mtmp.put("to", mtos);
		mtmp.put("from", new ArrayList<String>());
		if(tableIds.size() > 0) {
			if(tableIds.contains(mt.getId())) {
				result.add(mtmp);
			}
		}else {
			result.add(mtmp);
		}
		
		DataResourcePO dr = dataResourceDao.get(Long.parseLong(mt.getResourceId()));
		HashMap<String, Object> dtmp = new HashMap<String, Object>();
		dtmp.put("name", dr.getResourceName());
		dtmp.put("id", "dr" + dr.getId());
		dtmp.put("type", Constant.DATATRACK_TYPE_RESOURCE);
		dtmp.put("size", 1212);
		List<String> dtos = new ArrayList<String>();
		dtos.add("mt" + mt.getId());
		dtmp.put("to", dtos);
		dtmp.put("from", new ArrayList<String>());
		result.add(dtmp);
		return result;
	}






	/**
	 * 满足条件的资源类型为包的数据源
	 * @param key
	 * @param date
	 * @return
	 */
	private List<DataResourcePO> getDataResourcesForDataTrack(String key, String date) {
		HashMap<String, Object> eq = new HashMap<String, Object>();
		eq.put("deleteFlag", Constant.DATA_DATAPACKAGE_NOT_DELETE);
		QueryDataTrackParam param = QueryDataTrackParam.genParam(eq, "resourceName", key, "createDate", date);
		Map<String, Object> ne = new HashMap<String, Object>();
		ne.put("resourceKind", Constant.RESOURCE_KIND_PACKAGE);
		param.setNe(ne);
		List<DataResourcePO> drs = dataResourceDao.findByAnd(param);
		return drs;
	}

	@Override
	public List<String> getTableName(String driver, String url, String userName, String password) {
		List<String> tableList = new ArrayList<String>();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
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
			connection = JdbcConnection.getConnection(driver, url, userName, password);
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				tableList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			//TODO 添加日志记录
			e.printStackTrace();
		} finally {
			if(connection != null) {
				JdbcConnection.closeConnection(connection, pStatement, resultSet);
			}
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
			sql = "select top * from " + tableName;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JdbcConnection.closeConnection(connection, pStatement, resultSet);
		}

		return columnList;
	}

	@Override
	public ArrayList<ArrayList<String>> getDatas(String driver, String url, String userName, String password,
			String tableName, List<String> columns, int pageSize, int pageNum, Object object) {
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();

		int start = 0;
		int end = 10;
		if (pageNum == 0)
			pageNum = 1;
		if (pageSize == 0)
			pageSize = 10;
		start = (pageNum - 1) * pageSize;
		end = pageNum * pageSize;

		Connection connection = JdbcConnection.getConnection(driver, url, userName, password);

		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql = "select * from " + tableName + " limit " + start + "," + end;
		if (driver.equals(Messages.getString("jdbc_oracle_driver"))) {
			sql = "select * from " + tableName + " where rownum<=" + end;
		}
		else if (driver.equals(Messages.getString("jdbc_sybase_driver"))) {
			sql = "select top "+ end + " * from " + tableName;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JdbcConnection.closeConnection(connection, pStatement, resultSet);
		}
		return datas;
	}



	@Override
	public Long countByParam(QueryParam param) {
		return dataResourceDao.countAll(param);
	}

	@Override
	public List<DataResourcePO> findAllByType(QueryParam param) {
		return dataResourceDao.findByAnd(param);
	}

	@Override
	public boolean duplicatecheck(Object id, Map<String, Object> map) {
		return dataResourceDao.duplicatecheck("id", id, map);
	}

	@Override
	public boolean testConnect(String datadbType, String jdbcUrl, String uName,
			String uPwd) {
		try{
			if(StringUtils.isEmpty(datadbType)){
				return false;
			}
			String driven = getDriver(Integer.valueOf(datadbType));
			Class.forName(driven);
			DriverManager.getConnection(jdbcUrl, uName, uPwd);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/*Add type of connecting error message 
	 * For pages to show the reason of connnect 
	 * Add by zhaiyangyang @2015-5-19
	 * 
	 * */
	//----start---
	@Override
	public int testConnectDB(String datadbType, String jdbcUrl, String uName, String uPwd){
		
		String pa = "com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure";
		String pa2= "com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown database";
		int resultflag;
		try{
			if(StringUtils.isEmpty(datadbType)){
				resultflag = Constant.DBLINK_DBTYPE_EMPTY;
			}
			String driven = getDriver(Integer.valueOf(datadbType));
			Class.forName(driven);
			DriverManager.getConnection(jdbcUrl, uName, uPwd);
			resultflag = Constant.DBLINK_SUCCESS;
		}catch(Exception e){

			e.printStackTrace();
			if(e.toString().contains(pa)){
				resultflag = Constant.DBLINK_ADDRESSORPORT_ERROR;
			}else if(e.toString().contains(pa2)){
				resultflag = Constant.DBLINK_DBNAME_NOT_EXIT;
			}else{
				resultflag = Constant.DBLINK_NAMEORPASSWORD_ERROR;
			}
				
		}
		return resultflag;
	}

	//----End-----
	
	@Override
	public List<Map<String, Object>> getDBVersion(int dbId) {
		switch (dbId) {
		case Constant.RESOURCE_DATABASE_SQLSERVER:
			return getDBVersion(Constant.SS_VERSION, dbId);
		case Constant.RESOURCE_DATABASE_ORACLE:
			return getDBVersion(Constant.ORACLE_VERSION, dbId);
		case Constant.RESOURCE_DATABASE_MYSQL:
			return getDBVersion(Constant.MYSQL_VERSION, dbId);
		case Constant.RESOURCE_DATABASE_SYBASE:
			return getDBVersion(Constant.SYBASE_VERSION, dbId);
		case Constant.RESOURCE_DATABASE_HBASE:
			return emptyVersion("HBase", dbId);
		case Constant.RESOURCE_DATABASE_JSON:
			return emptyVersion("Json", dbId);
		case Constant.RESOURCE_DATABASE_MONGODB:
			return emptyVersion("MongoDB", dbId);
		case Constant.RESOURCE_DATABASE_REDIS:
			return emptyVersion("Redis", dbId);
		default:
			return new ArrayList<Map<String,Object>>();
		}
	}
	
	private List<Map<String, Object>> getDBVersion(String[] versions, int dbId) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Map<String, Object> tmp = null;
		String version = null;
		for(int i = 0; i<versions.length; i++) {
			version = versions[i];
			tmp = new HashMap<String, Object>();
			tmp.put("version", version);
			tmp.put("value", dbId * 10 + i);
			result.add(tmp);
		}
		return result;
	}
	
	private List<Map<String, Object>> emptyVersion(String version, int dbId) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Map<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("version", version);
		tmp.put("value", dbId);
		result.add(tmp);
		return result;
	}
	
	@Override
	public String getDriver(int dbType) {
		String driver =Messages.getString("jdbc_mysql_driver");
		if(dbType >= 10) {
			dbType = dbType / 10;
		}
		if(dbType==Constant.RESOURCE_DATABASE_ORACLE){
			driver =Messages.getString("jdbc_oracle_driver");
		}else if(dbType == Constant.RESOURCE_DATABASE_MONGODB){
			driver =Messages.getString("jdbc_DB2_driver");
		}else if(dbType == Constant.RESOURCE_DATABASE_SQLSERVER){
			driver =Messages.getString("jdbc_sqlserver_driver");
		}else if(dbType == 5){
			driver =Messages.getString("jdbcHiveDriver");
		}else if(dbType == Constant.RESOURCE_DATABASE_SYBASE){           
			driver =Messages.getString("jdbc_sybase_driver");
		}
		return driver;
	}
	
	@Override
	public List<DataResourcePO> findAll(){
		return dataResourceDao.findAll();
	}
}

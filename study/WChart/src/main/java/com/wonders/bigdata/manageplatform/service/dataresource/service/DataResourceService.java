package com.wonders.bigdata.manageplatform.service.dataresource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wonders.bigdata.manageplatform.service.dataresource.model.po.DataResourcePO;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * <p>
 * Title: manageplatform_[用户管理]_[数据源]
 * </p>
 * <p>
 * Description: [数据源service接口]
 * </p>
 * 
 * @author WF
 * @version $Revision$ 2015-3-16
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public interface  DataResourceService {

	/**
	 * <p>
	 * Description:[新增/更新数据源]
	 * </p>
	 * Created by [WF] [2015-3-16] Midified by [修改人] [修改时间]
	 * 
	 * @param DataResourcePO po
	 * @return DataResourcePO
	 */
	public DataResourcePO save(DataResourcePO po);

	/**
	 * <p>
	 * Description:[删除数据源]
	 * </p>
	 * Created by [WF] [2015-3-16] Midified by [修改人] [修改时间]
	 * 
	 * @param DataResourcePO po
	 * @return
	 */
	// XXX 冗余方法，为了方便controller层
	public void delete(DataResourcePO po);

	/**
	 * <p>
	 * Description:[根据ID删除数据源]
	 * </p>
	 * Created by [WF] [2015-3-16] Midified by [修改人] [修改时间]
	 * 
	 * @param po
	 * @return
	 */
	public void deleteById(Long id);

	/**
	 * <p>
	 * Description:[分页查询数据源]
	 * </p>
	 * Created by [WF] [2015-3-16] Midified by [修改人] [修改时间]
	 * 
	 * @param Page<DataResourcePO> page
	 * @return Page<DataResourcePO>
	 */
	public Page<DataResourcePO> findByPage(Page<DataResourcePO> page);

	/**
	 * <p>
	 * Description:[列表查询数据源]
	 * </p>
	 * Created by [WF] [2015-3-16] Midified by [修改人] [修改时间]
	 * 
	 * @param QueryParam param
	 * @return Page<DataResourcePO>
	 */
	public List<DataResourcePO> findByList(QueryParam param);

	/**
	 * <p>
	 * Description:[根据ID数据源]
	 * </p>
	 * Created by [WF] [2015-3-16] Midified by [修改人] [修改时间]
	 * 
	 * @param Long id
	 * @return DataResourcePO
	 */
	public DataResourcePO findById(Long id);

	/**
	 * <p>
	 * Description:[统计数据源个数]
	 * </p>
	 * Created by [WF] [2015-3-16] Midified by [修改人] [修改时间]
	 * 
	 * @param
	 * @return Long
	 */
	public Long countAllResource();

	/**
	 * <p>
	 * Description:[查询某资源分类下的所有资源]
	 * </p>
	 * Created by [CSJ] [2015-3-24] Midified by [修改人] [修改时间]
	 * 
	 * @param typeId
	 * @return List<DataResourcePO>
	 */
	public List<DataResourcePO> findByType(String typeId);

	/**
	 * <p>
	 * Description:[根据元数据表id获取数据追踪数据]
	 * </p>
	 * Created by [CSJ] [2015-3-24] Midified by [修改人] [修改时间]
	 * 
	 * @param tableId
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getDataForDataTrack(String tid, List<Long> tableIds);


	/**
	 * <p>
	 * Description:[获取数据源下的所有表的表名]
	 * </p>
	 * Created by [CSJ] [2015-3-26] Midified by [修改人] [修改时间]
	 * 
	 * @param 数据库驱动
	 * @param 连接字符串
	 * @param 用户名
	 * @param 密码
	 * @return List<String>
	 */
	public List<String> getTableName(String driver, String url, String userName, String password);

	/**
	 * <p>
	 * Description:[获取数据源下某个表的所有字段名]
	 * </p>
	 * Created by [CSJ] [2015-3-26] Midified by [修改人] [修改时间]
	 * 
	 * @param 数据库驱动
	 * @param 连接字符串
	 * @param 用户名
	 * @param 密码
	 * @param 表名
	 * @return List<String>
	 */
	public List<String> getColumnName(String driver, String url, String userName, String password, String tableName);

	/**
	 * <p>
	 * Description:[获取数据源下某个表的所有字段名]
	 * </p>
	 * Created by [CSJ] [2015-3-26] Midified by [修改人] [修改时间]
	 * 
	 * @param 数据库驱动
	 * @param 连接字符串
	 * @param 用户名
	 * @param 密码
	 * @param 表名
	 * @param 字段名
	 * @param 分页条件
	 * @return List<String>
	 */
	public ArrayList<ArrayList<String>> getDatas(String driver, String url, String userName, String password,
			String tableName, List<String> colums, int pageSize, int pageNumber, Object object);


	/**
	 * <p>
	 * Description:[统计个数]
	 * </p>
	 * Created by [WF] [2015-3-16] Midified by [修改人] [修改时间]
	 * 
	 * @param
	 * @return Long
	 */
	public Long countByParam(QueryParam param);
	
	/**
	 * <p>
	 * Description:[查询所有资源]
	 * </p>
	 * 
	 * Created by [Heyh] [2015年5月7日]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param typeId
	 * @return
	 */
	public List<DataResourcePO> findAllByType(QueryParam param);
		
	/**
	 * <p>
	 * Description:[数据源重名校验]
	 * </p>
	 * 
	 * Created by [Heyh] [2015年5月18日]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param id
	 * @param map
	 * @return
	 */
	public boolean duplicatecheck( Object id, Map<String, Object> map);

	/**
	 * <p>
	 * Description:[数据源连接测试]
	 * </p>
	 * 
	 * Created by [CSJ] [2015年5月19日]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param dbTypeId
	 * @param jdbcUrl
	 * @param uName
	 * @param uPwd
	 * @return
	 */
	public boolean testConnect(String dbTypeId, String jdbcUrl, String uName,
			String uPwd);

	/**
	 * <p>
	 * Description:[获取数据库版本]
	 * </p>
	 * 
	 * Created by [CSJ] [2015年6月4日]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param dbId
	 * @return
	 */
	public List<Map<String, Object>> getDBVersion(int dbId);

	/**
	 * <p>
	 * Description:[获取数据库驱动]
	 * </p>
	 * 
	 * Created by [CSJ] [2015年6月4日]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param dbType
	 * @return
	 */
	public String getDriver(int dbType);
	
	/**
	 * <p>
	 * Description:[查询所有资源]
	 * </p>
	 * 
	 * Created by [ZYY] [2015年8月28日]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param unull
	 * @return List<DataResourcePO>
	 */
	public List<DataResourcePO> findAll();
	
	/**
	 * <p>
	 * Description:[数据源连接测试]
	 * </p>
	 * 
	 * Created by [ZYY] [2015年9月18日]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param dbTypeId
	 * @param jdbcUrl
	 * @param uName
	 * @param uPwd
	 * @return
	 */
	public int testConnectDB(String dbTypeId, String jdbcUrl, String uName,
			String uPwd);


}

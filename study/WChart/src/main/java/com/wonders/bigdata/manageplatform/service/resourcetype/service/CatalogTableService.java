package com.wonders.bigdata.manageplatform.service.resourcetype.service;


import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogColumnPO;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogTablePO;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;

import java.util.List;

/**
 * 目录表数据包服务类接口
 * @author xuehan
 * @date 2015年8月25日 下午10:16:47
 */
public interface CatalogTableService {


	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public CatalogTablePO findById(long id);
	

	/**
	 * 统计某数据目录下所有数据包和表的数量
	 * @param catalogId 所在目录id
	 * @return 该目录下的表和数据包的数量
	 */
	public int countByCatalogId(long catalogId);
	
	/**
	 * 在某一数据目录下进行name模糊查询
	 * @param catalogId 数据目录id
	 * @param name 要查找的表或者数据包名称
	 * @return
	 */
	public List<CatalogTablePO> searchByNameAndCatalog(long catalogId, String name);
	
	/**
	 * 在某一数据目录下进行name模糊查询
	 * @param catalogIds 数据目录id数组
	 * @param name 要查找的表或者数据包名称
	 * @return
	 */
	public List<CatalogTablePO> searchByNameAndCatalog(long[] catalogIds, String name, int grade);
	
	/**
	 * 根据给定的页节点id数组查找所有关联
	 * @param ids
	 * @return
	 */
	public List<CatalogTablePO> findByIds(long[] ids);
	
	/**
	 * 根据给定的页节点id数组查找所有关联
	 * @param ids
	 * @return
	 */
	public List<CatalogTablePO> findByIdsWithGrade(long[] ids, int grade);
	
	/**
	 * 根据给定的页节点id数组查找所有关联
	 * @param ids
	 * @return
	 */
	public List<CatalogTablePO> findByIds(long[] ids, int limit, int grade);
	
	/**
	 * 根据给定的页节点id和挂接表的id查找已经存在的CatalogTableDatapackagePO
	 * @param ids
	 * @return List<CatalogTablePO>
	 */
	public List<CatalogTablePO> findCatalog(Long standardId, Long metaTableId);
	

	/**
	 * 根据tableId或者packageId查找
	 * @param id tableId或者packageId
	 * @return
	 */
	public List<CatalogTablePO> findByTablePackageId(long id);
	
	/**
	 * 根据catalogid查找开放的关联表
	 * @param ids
	 * @author rk
	 * @return
	 */
	public List<CatalogTablePO> findTablesByCatalogId(long[] ids);
	
	/**
	 * 根据表id查找所有的关联
	 * @param tablePackageId
	 * @return
	 */
	public List<CatalogTablePO> getTablesByTableId(Long tablePackageId);
	
	/**
	 * 根据根据节点id和表id精确查找关联的表
	 * @param tablePackageId
	 * @param catalogId
	 * @return
	 */
	public CatalogTablePO getTablesByCatalogIdAndTableId(Long catalogId, Long tablePackageId);
	
	/**
	 * 根据standardCatalogId查找关联表的记录
	 * @param id
	 * @return
	 */
	public List<CatalogTablePO> getTablesByCatalogId(Long standardCatalogId);
	
	/**
	 * 根据表明或者包明查找对应记录
	 * @param name
	 * @return
	 */
	public List<CatalogTablePO> getByTableOrPackageName(String name);
	
	/**
	 * 根据catalogIds分页查询根目录节点下的所有表和包
	 * @param catalogIds
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<CatalogTablePO> findPageByRootId(Long[] catalogIds, int pageNum, int pageSize);
	
	/**
	 * 根据数据目录id和元数据表id进行精确查询
	 * @author xh 2015-10-28 10:53:16
	 * @param catalogId 目录id
	 * @param tableId 表id
	 * @return CatalogTablePO
	 */
	public CatalogTablePO findByCatalogIdAndTableId(long catalogId, long tableId);
	
	/**
	 * 查询某数据目录下所有关联表（包括开放与未开放的）
	 * @author xh 2015-10-28 10:55:33
	 * @param catalogId
	 * @return
	 */
	public List<CatalogTablePO> findAllByCatalogId(long catalogId);

	/**
	 * 查询某数据目录下所有未开放关联表
	 * @author xh 2015-10-28 10:56:31
	 * @param catalogId
	 * @return
	 */
	public List<CatalogTablePO> findNotOpenByCatalogId(long catalogId);
	/**
	 * 审批表
	 * @param catalogTable 关联表的表对象
	 * @param cols 字段对象List集合
	 * @return List<CatalogColumnPO>
	 */
	public CatalogTablePO saveApproveCatalogTable(CatalogTablePO catalogTable, List<CatalogColumnPO> cols);
	/**
	 * 根据catalogId查询所有提交的关联表
	 * @author hhl 2015-10-28 10:55:33
	 * @param catalogId
	 * @return
	 */
	public List<CatalogTablePO> findAllSubmitTablesByCatalogId(long catalogId);
	
	/**
	 * 根据catalogId查询所有审核通过的关联表
	 * @author rk 2015-11-09 10:12:23
	 * @param catalogId
	 * @return
	 */
	public List<CatalogTablePO> findAllcheckedTablesByCatalogId(long catalogId);
	
	/**
	 * 根据CatalogId查找该目录下所有表的最高等级
	 * @author WZB
	 * @date 2016-01-27
	 * @param catalogId 开发目录树的id
	 * @return 该目录下表的最高等级
	 */
	public int findCatalogTableTopGrade(long catalogId);

	/**
	 * 根据catalogId和表名进行精确查询
	 * @author RK
	 * @date 2016-02-29
	 * @param catalogId 开发目录树的id,tableName 表名
	 * @return 数据目录挂接表对象
	 */
	public CatalogTablePO findByCatalogIdAndTableName(long catalogId, String tableName);


	/**
	 * 根据获取的参数进行查询
	 * @author XB
	 * @data 2016-3-3 14:02:46
	 * @param param组装条件
	 * @return 数据目录挂接表对象
	 */
	public List<CatalogTablePO> findByParam(QueryParam param);


	/**
	 * Created by XB on 2016/3/30
	 * <br> 根据SQL语句查询有限条的关联表
	 * @param limit 条数
	 * @return 期望的PO信息
	 */
	List<CatalogTablePO> findCatalogTablesBySql(int limit);

	/**
	 * Created by LXL on 2016/4/20
	 * <br> 根据SQL语句查询关联表
	 * @param sql sql语句
	 * @return 表PO信息
     */
	List<CatalogTablePO> findCatalogTablesBySql(String sql);


	public List<CatalogTablePO> findOpenTableAndAduitPassByCatalogid(long catalogId);
}

package com.wonders.bigdata.manageplatform.service.resourcetype.service;


import java.util.List;

import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogTablePO;
import org.apache.poi.util.LongField;

import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.StandardCatalogPO;
import com.wonders.bud.framework.common.page.Page;

/**
 * 目录表数据包服务类接口
 * @author xuehan
 * @date 2015年8月25日 下午10:16:47
 */
public interface  CatalogTableDatapackageService {


	
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
	public List<CatalogTablePO> searchByNameAndCatalog(long[] catalogIds, String name);
	
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
	public List<CatalogTablePO> findByIds(long [] ids, int limit);
	
	/**
	 * 根据给定的页节点id和挂接表的id查找已经存在的CatalogTableDatapackagePO
	 * @param ids
	 * @return List<CatalogTableDatapackagePO>
	 */
	public List<CatalogTablePO> findCatalog(Long standardId,Long metaTableId);
	

	/**
	 * 根据tableId或者packageId查找
	 * @param id tableId或者packageId
	 * @return
	 */
	public List<CatalogTablePO> findByTablePackageId(long id);
	
	/**
	 * 根据catalogid查找关联表
	 * @param ids
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
	public Page<CatalogTablePO> findPageByRootId(Long[] catalogIds,int pageNum,int pageSize);

}

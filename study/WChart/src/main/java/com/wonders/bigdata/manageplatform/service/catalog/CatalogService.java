package com.wonders.bigdata.manageplatform.service.catalog;


import com.wonders.bigdata.manageplatform.service.catalog.model.po.CatalogPO;

import java.util.List;

/**
 * 
 * @author XH 2016-4-28 10:28:27
 *
 */
public interface CatalogService {

	/**
	 * 根据节点id，查寻该节点下所有开放且通过领导审核的数据目录节点
	 * 注意：只会查找一层，并不会递归查找该id节点下所有节点。
	 * @author xh
	 * @date 2015-11-19 15:25:40
	 * @param id
	 * @return
	 */
	List<CatalogPO> findAllRootNodesWithStatus(long id);
}

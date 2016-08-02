package com.wonders.bigdata.manageplatform.service.catalog;



import com.wonders.bigdata.manageplatform.service.catalog.model.po.CatalogTablePO;

import java.util.List;

/**
 * 目录表数据包服务类接口
 * @author xuehan
 * @date 2015年8月25日 下午10:16:47
 */
public interface CatalogTableService2 {

	List<CatalogTablePO> findOpenTableAndAduitPassByCatalogid(long catalogId);
}

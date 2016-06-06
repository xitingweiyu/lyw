package com.wonders.bigdata.manageplatform.service.resourcetype.dao;

import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogTableDatapackagePO;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogTablePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDao;

import java.util.List;

/**
 * 目录表数据包dao接口
 * @author xuehan
 * @date 2015年8月25日 下午10:11:12
 */
public interface CatalogTableDao extends HibernateBaseDao<CatalogTablePO, Long>{

    /**
     * 根据数据目录id查询该数据目录关联的表或者数据包
     * @param ids 数据目录id
     * @param maxResult 结果集个数
     * @return
     */
    public List<CatalogTableDatapackagePO> findByIds(long[] ids, int maxResult);
}
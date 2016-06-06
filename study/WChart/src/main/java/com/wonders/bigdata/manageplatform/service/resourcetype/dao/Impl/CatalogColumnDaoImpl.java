package com.wonders.bigdata.manageplatform.service.resourcetype.dao.Impl;

import com.wonders.bigdata.manageplatform.service.resourcetype.dao.CatalogColumnDao;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogColumnPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * 数据目录字段关联表Dao接口实现类
 * 
 * @author xh 2015-10-28 10:13:19
 *
 */
@Repository("catalogColumnDaoImpl")
public class CatalogColumnDaoImpl extends HibernateBaseDaoImpl<CatalogColumnPO, Long> implements CatalogColumnDao {

}

package com.wonders.bigdata.manageplatform.service.resourcetype.dao.Impl;

import com.wonders.bigdata.manageplatform.service.resourcetype.dao.StandardCatalogDao;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.StandardCatalogPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("standardCatalogDaoImpl")
public class  StandardCatalogDaoImpl extends HibernateBaseDaoImpl<StandardCatalogPO, Long> implements StandardCatalogDao{

}


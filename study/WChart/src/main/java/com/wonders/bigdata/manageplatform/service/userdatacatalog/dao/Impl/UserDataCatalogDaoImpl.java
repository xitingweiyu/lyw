package com.wonders.bigdata.manageplatform.service.userdatacatalog.dao.Impl;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.dao.UserDataCatalogDao;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by WYP on 2016/3/11.
 */
@Repository("userDataCatalogDaoImpl")
public class UserDataCatalogDaoImpl extends HibernateBaseDaoImpl<UserDataCatalogPO, Long> implements UserDataCatalogDao{
}

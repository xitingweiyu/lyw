package com.wonders.bigdata.manageplatform.service.userdatacatalog.dao.Impl;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.dao.UserDataCatalogColumnDao;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogColumnPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by XCL on 2016/3/10.
 */
@Repository("userDataCatalogColumnDaoImpl")
public class UserDataCatalogColumnDaoImpl extends HibernateBaseDaoImpl<UserDataCatalogColumnPO, Long> implements UserDataCatalogColumnDao {



}

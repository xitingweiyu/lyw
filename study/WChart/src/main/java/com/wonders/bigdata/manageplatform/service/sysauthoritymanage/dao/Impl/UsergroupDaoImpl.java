package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.Impl;

import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.UsergroupDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.UsergroupPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

@Repository("usergroupDaoImpl")
public class  UsergroupDaoImpl extends HibernateBaseDaoImpl<UsergroupPO, Long> implements UsergroupDao{

	
}

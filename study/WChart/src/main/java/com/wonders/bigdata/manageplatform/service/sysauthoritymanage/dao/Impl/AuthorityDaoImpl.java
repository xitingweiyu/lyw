package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.Impl;

import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.AuthorityDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.AuthorityPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

/**
 * 
 * @author hsw
 *
 */
@Repository("authorityDaoImpl")
public class  AuthorityDaoImpl extends HibernateBaseDaoImpl<AuthorityPO,Long> implements AuthorityDao{

}

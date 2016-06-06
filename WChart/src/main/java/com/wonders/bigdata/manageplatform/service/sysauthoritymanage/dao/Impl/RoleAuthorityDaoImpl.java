package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.Impl;

import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.RoleAuthorityDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.RoleAuthorityPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

/**
 * 
 * @author hsw
 *
 */
@Repository("roleAuthorityDaoImpl")
public class  RoleAuthorityDaoImpl extends HibernateBaseDaoImpl<RoleAuthorityPO,Long> implements RoleAuthorityDao{

}

package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.Impl;

import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.RoleDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.RolePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

/**
 * role dao 实现
 * @author hsw
 *
 */
@Repository("roleDaoImpl")
public class  RoleDaoImpl extends HibernateBaseDaoImpl<RolePO,Long> implements RoleDao{

}

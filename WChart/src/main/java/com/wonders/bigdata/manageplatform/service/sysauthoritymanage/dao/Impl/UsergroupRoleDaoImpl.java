package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.Impl;

import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.UsergroupRoleDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.UsergroupRolePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

/**
 * 用户组角色 dao 实现类
 * @author xuehan
 * @date 2015年4月13日 下午2:53:58
 */
@Repository("usergroupRoleDaoImpl")
public class  UsergroupRoleDaoImpl extends HibernateBaseDaoImpl<UsergroupRolePO, Long> implements UsergroupRoleDao{

}

package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.Impl;


import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.UserUsergroupDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.UserUsergroupPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

/**
 * 用户/用户组 dao实现类
 * @author xuehan
 * @date 2015年4月13日 上午9:27:48
 */
@Repository("userUsergroupDaoImpl")
public class  UserUsergroupDaoImpl extends HibernateBaseDaoImpl<UserUsergroupPO, Long> implements
		UserUsergroupDao {


}

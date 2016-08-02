package com.wonders.bigdata.manageplatform.service.sdk.dao.Impl;

import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.sdk.dao.SDKOperateLogDao;
import com.wonders.bigdata.manageplatform.service.sdk.model.po.SDKOperateLogPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

/**
 * 
 * @author hsw
 *
 */

@Repository("SDKOperateLogDaoImpl")
public class  SDKOperateLogDaoImpl extends HibernateBaseDaoImpl<SDKOperateLogPO,Long> implements SDKOperateLogDao{

}

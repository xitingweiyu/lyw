package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.Impl;

import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.OperateInfoDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.OperateInfoPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

@Repository("operateInfoDaoImpl")
public class  OperateInfoDaoImpl extends HibernateBaseDaoImpl<OperateInfoPO,Long> implements OperateInfoDao{

}

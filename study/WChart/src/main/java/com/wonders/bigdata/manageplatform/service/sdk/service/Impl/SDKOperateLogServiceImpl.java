package com.wonders.bigdata.manageplatform.service.sdk.service.Impl;

import com.wonders.bigdata.manageplatform.service.sdk.dao.SDKOperateLogDao;
import com.wonders.bigdata.manageplatform.service.sdk.model.po.SDKOperateLogPO;
import com.wonders.bigdata.manageplatform.service.sdk.service.SDKOperateLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * @author hsw
 *
 */

@Service("SDKOperateLogServiceImpl")
public class  SDKOperateLogServiceImpl implements SDKOperateLogService{

	@Resource(name="SDKOperateLogDaoImpl")
	private SDKOperateLogDao SDKOperateLogDao;
	
	@Override
	public void save(SDKOperateLogPO sdkOperateLogPO) {
		SDKOperateLogDao.save(sdkOperateLogPO);
	}

}

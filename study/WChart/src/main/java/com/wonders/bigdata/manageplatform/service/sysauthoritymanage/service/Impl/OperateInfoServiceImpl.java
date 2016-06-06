package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.Impl;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.OperateInfoDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.OperateInfoPO;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.OperateInfoService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author hsw
 *
 */
@Service("operateInfoServiceImpl")
public class  OperateInfoServiceImpl implements OperateInfoService{

	@Resource(name = "operateInfoDaoImpl")
	private OperateInfoDao operateInfoDao;
	
	@Override
	public OperateInfoPO save(OperateInfoPO operateInfoPO) {
		return operateInfoDao.save(operateInfoPO);
	}

	@Override
	public OperateInfoPO get(long id) {
		return operateInfoDao.get(id);
	}

	@Override
	public Page<OperateInfoPO> getOperateInfoPage(Page<OperateInfoPO> page) {
		return operateInfoDao.findByPage(page);
	}

	@Override
	public List<OperateInfoPO> getByOpTypeAndOpId(long operateId,
			int operateType) {
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("operateId", operateId);
		eq.put("operateType", operateType);
		eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
		param.setEq(eq);
		List<OperateInfoPO> operateInfoPOs = operateInfoDao.findByAnd(param);
		return operateInfoPOs;
	}

}

/** 
 * 
 * Copyright (c) 1995-2012 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wonders.bigdata.manageplatform.service.log.service.Impl;

import com.wonders.bigdata.manageplatform.service.log.dao.DataPackageLogDao;
import com.wonders.bigdata.manageplatform.service.log.dao.OperationLogDao;
import com.wonders.bigdata.manageplatform.service.log.dao.TaskLogDao;
import com.wonders.bigdata.manageplatform.service.log.model.po.DataPackageLogPO;
import com.wonders.bigdata.manageplatform.service.log.model.po.OperationLogPO;
import com.wonders.bigdata.manageplatform.service.log.model.po.TaskLogPO;
import com.wonders.bigdata.manageplatform.service.log.service.LogService;
import com.wonders.bigdata.manageplatform.service.task.dao.TaskDao;
import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;
import com.wonders.bigdata.manageplatform.service.task.service.TaskService;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [操作日志service接口实现类]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Service("logServiceImpl")
public class  LogServiceImpl implements LogService {

	@Autowired
	private OperationLogDao operationLogDao;

	@Autowired
	private TaskLogDao taskLogDao;

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private TaskService taskService;

	@Autowired
	private DataPackageLogDao dataPackLogDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public OperationLogPO saveOperationLog(OperationLogPO po) {
		po.setOperateDate(new Date());
		return operationLogDao.save(po);
	}

	@Override
	public Page<OperationLogPO> findOperationLogByPage(Page<OperationLogPO> page) {
		QueryParam param = new QueryParam();
		Map<String, String> order = new HashMap<String, String>();

		if (null != page) {
			if (null != page.getParam()) {
				param = page.getParam();

				if (null != page.getParam().getOrder()) {
					order = page.getParam().getOrder();
				}
			}
		}
		else {
			page = new Page<OperationLogPO>();
		}

		order.put("operateDate", "desc");// 操作日志时间倒序
		param.setOrder(order);
		page.setParam(param);
		return operationLogDao.findByPage(page);
	}

	@Override
	public List<OperationLogPO> findOperationLogByList(Map<String, Object> map) {
		return operationLogDao.findBy(map);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TaskLogPO saveTaskLog(TaskLogPO po) {
		po.setLogTime(new Date());
		return taskLogDao.save(po);
	}

	@Override
	public List<TaskLogPO> getTaskLogByTaskId(QueryParam param) {
		Long taskId = (Long) param.getEq().get("taskId");
		TaskPO task = taskService.getById(taskId);

		if (task != null) {
			return taskLogDao.findByAnd(param);
		}
		else {
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDataPackageLog(DataPackageLogPO dataPackLogPO) {
		dataPackLogDao.save(dataPackLogPO);
	}

	@Override
	public DataPackageLogPO findDataPackageLogById(long id) {
		return dataPackLogDao.findUniqueByProperty("id", id);
	}

	@Override
	public List<DataPackageLogPO> findDataPackageLogsByDpId(long dpId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dpId", dpId);
		return dataPackLogDao.findBy(paramMap);
	}

}

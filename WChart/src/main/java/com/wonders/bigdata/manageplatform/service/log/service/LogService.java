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

package com.wonders.bigdata.manageplatform.service.log.service;

import java.util.List;
import java.util.Map;

import com.wonders.bigdata.manageplatform.service.log.model.po.DataPackageLogPO;
import com.wonders.bigdata.manageplatform.service.log.model.po.OperationLogPO;
import com.wonders.bigdata.manageplatform.service.log.model.po.TaskLogPO;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [日志serviece接口]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (latest modification by GLJ)
 * @since 20100901
 */
public interface  LogService {

	/**
	 * <p>
	 * Description:[操作日志新增]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Modified by [修改人] [修改时间]
	 * 
	 * @param po
	 * @return
	 */
	public OperationLogPO saveOperationLog(OperationLogPO po);

	/**
	 * <p>
	 * Description:[操作日志分页查询]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Modified by [修改人] [修改时间]
	 * 
	 * @param page
	 * @return
	 */

	public Page<OperationLogPO> findOperationLogByPage(Page<OperationLogPO> page);

	/**
	 * <p>
	 * Description:[操作日志列表查询]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Modified by [修改人] [修改时间]
	 * 
	 * @param map
	 * @return
	 */
	public List<OperationLogPO> findOperationLogByList(Map<String, Object> map);

	/**
	 * <p>
	 * Description:[任务日志新增]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Modified by [修改人] [修改时间]
	 * 
	 * @param po
	 * @return
	 */
	public TaskLogPO saveTaskLog(TaskLogPO po);

	/**
	 * <p>
	 * Description:[根据任务id获取任务日志]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Modified by [修改人] [修改时间]
	 * 
	 * @param QueryParam param
	 * @return
	 */
	public List<TaskLogPO> getTaskLogByTaskId(QueryParam param);

	/**
	 * <p>
	 * Description:[新增数据包日志]
	 * </p>
	 * Created by [GLJ] [2015年3月18日] Modified by [修改人] [修改时间]
	 * 
	 * @param dataPackLogPO
	 * @return
	 */
	public void saveDataPackageLog(DataPackageLogPO dataPackLogPO);

	/**
	 * <p>
	 * Description:[根据id查找数据包日志]
	 * </p>
	 * Created by [GLJ] [2015年3月18日] Modified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @return
	 */
	public DataPackageLogPO findDataPackageLogById(long id);

	/**
	 * <p>
	 * Description:[根据dpId查找数据包日志列表]
	 * </p>
	 * Created by [GLJ] [2015年3月18日] Modified by [修改人] [修改时间]
	 * 
	 * @param dpId
	 * @return
	 */
	public List<DataPackageLogPO> findDataPackageLogsByDpId(long dpId);

}

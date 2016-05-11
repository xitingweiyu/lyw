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

package com.wonders.bigdata.manageplatform.service.task.service;

import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [任务service接口]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
public interface  TaskService {

	/**
	 * <p>
	 * Description:[任务新增]
	 * </p>
	 * Created by [Heyh] [2015年3月17日] Midified by [修改人] [修改时间]
	 * 
	 * @param po
	 * @return
	 */
	public TaskPO save(TaskPO po);

	/**
	 * <p>
	 * Description:[任务新增]
	 * </p>
	 * Created by [Heyh] [2015年3月17日] Midified by [修改人] [修改时间]
	 *
	 * @param po
	 * @return
	 */
	public TaskPO simpleSave(TaskPO po);

	/**
	 * <p>
	 * Description:[任务更新]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
	 * 
	 * @param po
	 * @return
	 */
	public TaskPO update(TaskPO po, String gtName);

	/**
	 * <p>
	 * Description:[任务分页查询]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
	 * 
	 * @param page
	 * @return
	 */
	public Page<TaskPO> findByPage(Page<TaskPO> page);

	/**
	 * <p>
	 * Description:[任务列表查询]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
	 * 
	 * @param map
	 * @return
	 */
	public List<TaskPO> findTaskByList(QueryParam param);

	/**
	 * <p>
	 * Description:[启动任务]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @return
	 */
	public String start(long id);

	/**
	 * <p>
	 * Description:[停止任务]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @return
	 */
	public String stop(long id);

	/**
	 * <p>
	 * Description:[重名校验]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	public boolean duplicatecheck(Object id, Map<String, Object> map);

	/**
	 * <p>
	 * Description:[根据id获取任务]
	 * </p>
	 * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @return
	 */
	public TaskPO getById(long id);

	/**
	 * <p>
	 * Description:[任务异常总数]
	 * </p>
	 * Created by [Heyh] [2015年3月17日] Midified by [修改人] [修改时间]
	 * 
	 * @return
	 */
	public Long taskFailCount();

	/**
	 * <p>
	 * Description:[根据id删除任务]
	 * </p>
	 * Created by [Heyh] [2015年3月17日] Midified by [修改人] [修改时间]
	 * 
	 * @param id(逻辑删除)
	 */
	public void deleteById(long id);

	/**
	 * <p>
	 * Description:[根据数据源id获取任务]
	 * </p>
	 * Created by [Heyh] [2015年3月17日] Midified by [修改人] [修改时间]
	 * 
	 * @param dataResourceId
	 * @return
	 */
	public TaskPO getTaskByDrId(long id);

	/**
	 * <p>
	 * Description:[修改任务状态]
	 * </p>
	 * Created by [CSJ] [2015年3月23日] Midified by [修改人] [修改时间]
	 * 
	 * @param taskId
	 * @param status
	 * @return
	 */
	public String changeResourceTaskStatus(long id, int status);

	/**
	 * <p>
	 * Description:[根据查询条件获取任务数量]
	 * </p>
	 * Created by [CSJ] [2015年3月27日] Midified by [修改人] [修改时间]
	 * 
	 * @param QueryParam param
	 * @return
	 */
	public long countTask(QueryParam param);

}

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

package com.wonders.bigdata.manageplatform.service.task.scheduler.manager;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;
import com.wonders.bigdata.manageplatform.utils.SqConEntity;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]_[任务管理]
 * </p>
 * <p>
 * Description: [任务调度器接口]
 * </p>
 * 
 * @author CSJ
 * @version $Revision$ 2015年3月19日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
public interface TaskScheduleManager {

	/**
	 * <p>
	 * Description:[删除调度器中的job]
	 * </p>
	 * Created by  [CSJ] [2015-3-19] Midified by [修改人] [修改时间]
	 * 
	 * @param name group
	 * @return
	 */
	public void deleteScheduleJob(String name, String group) throws SchedulerException;

	/**
	 * <p>
	 * Description:[暂停调度器中的job]
	 * </p>
	 * Created by [CSJ] [2015-3-19] Midified by [修改人] [修改时间]
	 * 
	 * @param name group
	 * @return
	 */
	public void pauseJob(String jobName, String jobGroup) throws SchedulerException;

	/**
	 * <p>
	 * Description:[获取调度器]
	 * </p>
	 * Created by [CSJ] [2015-3-19] Midified by [修改人] [修改时间]
	 * 
	 * @param name group
	 * @return
	 */
	public Scheduler getScheduler();

	/**
	 * <p>
	 * Description:[在调度器中添加Job]
	 * </p>
	 * Created by [CSJ] [2015-3-19] Midified by [修改人] [修改时间]
	 * 
	 * @param name group
	 * @return
	 */
	public void scheduleNewJob(String group, TaskPO task) throws ParseException, SchedulerException;
}

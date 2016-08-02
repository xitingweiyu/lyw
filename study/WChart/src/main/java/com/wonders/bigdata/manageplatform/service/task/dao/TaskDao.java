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

package com.wonders.bigdata.manageplatform.service.task.dao;

import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDao;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [任务Dao接口]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
public interface  TaskDao extends HibernateBaseDao<TaskPO, Long> {

	/**
	 * <p>
	 * Description:[统计异常任务个数]
	 * </p>
	 * Created by [Heyh] [2015年3月17日] Midified by [修改人] [修改时间]
	 * 
	 * @param param
	 * @return
	 */
	public Long countFailTask(QueryParam param);

}

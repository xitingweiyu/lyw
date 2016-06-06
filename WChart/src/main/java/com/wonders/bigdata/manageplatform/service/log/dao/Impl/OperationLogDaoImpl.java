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

package com.wonders.bigdata.manageplatform.service.log.dao.Impl;

import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.log.dao.OperationLogDao;
import com.wonders.bigdata.manageplatform.service.log.model.po.OperationLogPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [操作日志Dao接口实现类]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Repository("operationLogDaoImpl")
public class  OperationLogDaoImpl extends HibernateBaseDaoImpl<OperationLogPO, Long> implements OperationLogDao {

}

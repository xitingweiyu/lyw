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

package com.wonders.bigdata.manageplatform.service.task.dao.Impl;

import com.wonders.bigdata.manageplatform.service.task.dao.QualityDao;
import com.wonders.bigdata.manageplatform.service.task.model.po.QualityPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[分析工具]
 * </p>
 * <p>
 * Description: [数据质量表Dao接口实现]
 * </p>
 * @author XCL
 * @version $Revision$ 2016年4月22日15:48:41
 */
@Repository("QualityDaoImpl")
public class  QualityDaoImpl extends HibernateBaseDaoImpl<QualityPO, Long> implements QualityDao {



}

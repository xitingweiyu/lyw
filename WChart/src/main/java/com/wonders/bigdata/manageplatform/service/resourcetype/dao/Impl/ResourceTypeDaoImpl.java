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

package com.wonders.bigdata.manageplatform.service.resourcetype.dao.Impl;

import com.wonders.bigdata.manageplatform.service.resourcetype.dao.ResourceTypeDao;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.ResourceTypePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[资源分类]
 * </p>
 * <p>
 * Description: [资源分类Dao接口实现类]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月18日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Repository("resourceTypeDaoImpl")
public class  ResourceTypeDaoImpl extends HibernateBaseDaoImpl<ResourceTypePO, Long> implements ResourceTypeDao {

}

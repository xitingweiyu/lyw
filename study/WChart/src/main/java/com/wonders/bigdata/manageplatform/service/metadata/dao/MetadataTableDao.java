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
package com.wonders.bigdata.manageplatform.service.metadata.dao;

import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataTablePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDao;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[数据目录--表]
 * </p>
 * <p>
 * Description: [数据目录--表Dao接口]
 * </p>
 * 
 * @author CSJ
 * @version $Revision$ 2015年3月18日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
public interface  MetadataTableDao extends HibernateBaseDao<MetadataTablePO, Long> {

	/**
	 * <p>
	 * Description: [按照查询条件，统计所有数据源]
	 * </p>
	 * 
	 * @author GLJ
	 * @version $Revision$ 2015-03-25
	 * @author (latest modification by $Author$)
	 * @since 20130601
	 */
	public Long countByQuery(QueryParam param);
}

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
package com.wonders.bigdata.manageplatform.service.metadata.dao.Impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.metadata.dao.MetadataTableDao;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataTablePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[数据目录--表]
 * </p>
 * <p>
 * Description: [数据目录--表Dao实现层]
 * </p>
 * 
 * @author CSJ
 * @version $Revision$ 2015年3月18日
 * @author (latest modification by $Author$)
 * @since 20100901
 */
@Repository("metadataTableDaoImpl")
public class  MetadataTableDaoImpl extends HibernateBaseDaoImpl<MetadataTablePO, Long> implements MetadataTableDao {

	@Override
	public Long countByQuery(QueryParam param) {
		Criteria c = createCriteria();
		c = param.andCriteria(c);
		c.setProjection(null);
		// 获取总条数
		Long totalCount = ((Long) c.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		return totalCount;
	}
}

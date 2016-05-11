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

import com.wonders.bigdata.manageplatform.service.metadata.dao.MetadataColumnDao;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataColumnPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[数据目录--字段]
 * </p>
 * <p>
 * Description: [数据目录--字段Dao实现层]
 * </p>
 * 
 * @author CSJ
 * @version $Revision$ 2015年3月18日
 * @author (latest modification by $Author$)
 * @since 20100901
 */
@Repository("metadataColumnDaoImpl")
public class  MetadataColumnDaoImpl extends HibernateBaseDaoImpl<MetadataColumnPO, Long> implements MetadataColumnDao {

	@Override
	public List<MetadataColumnPO> findByTableNameAndColumnName(String columnName, String tableName) {

		return null;
	}

}

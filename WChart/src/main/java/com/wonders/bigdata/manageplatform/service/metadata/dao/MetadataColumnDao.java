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

import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataColumnPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDao;

import java.util.List;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[数据目录--字段]
 * </p>
 * <p>
 * Description: [数据目录--字段Dao接口]
 * </p>
 * 
 * @author CSJ
 * @version $Revision$ 2015年3月18日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
public interface  MetadataColumnDao extends HibernateBaseDao<MetadataColumnPO, Long> {

	/**
	 * <p>
	 * Description:[根据字段名和表名获取字段]
	 * </p>
	 * Created by [CSJ] [2015-03-24] Modified by [修改人] [修改时间]
	 * 
	 * @param columnName
	 * @param tableName
	 * @return List<MetadataColumnPO>
	 */
	public List<MetadataColumnPO> findByTableNameAndColumnName(String columnName, String tableName);

}

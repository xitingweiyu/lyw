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

import com.wonders.bigdata.manageplatform.service.task.model.po.BdIndexPO;
import com.wonders.bigdata.manageplatform.web.dataquality.vo.DataQualityVO;

import java.util.List;

/**
 *
 * @author XH
 * @version $Revision$ 2016-4-28 10:11:34
 * @author (lastest modification by $Author$)
 */
public interface BdIndexService {

	/**
	 *获取所有指标项
	 * @return
	 */
	List<BdIndexPO> getAll();

	/**
	 * 通过表名和字段名获取数据质量的分析结果
	 * @param tableName 表明
	 * @param columns 该表的所有字段
	 * @return
	 */
	List<DataQualityVO> getByTableName(String tableName, List<String> columns);


}

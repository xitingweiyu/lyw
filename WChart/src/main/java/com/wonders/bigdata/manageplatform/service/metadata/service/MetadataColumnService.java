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

package com.wonders.bigdata.manageplatform.service.metadata.service;

import java.util.List;

import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataColumnPO;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[元数据表字段]
 * </p>
 * <p>
 * Description: [元数据表字段Service接口]
 * </p>
 * 
 * @author GLJ
 * @version $Revision$ 2015年3月20日
 * @author (latest modification by GLJ)
 * @since 20100901
 */
public interface  MetadataColumnService {

	/**
	 * <p>
	 * Description:[根据MetadataTable id获取其所对应所有的列]
	 * </p>
	 * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param tid
	 * @return List<MetadataColumnPO>
	 */
	public List<MetadataColumnPO> getAllColumnByTableId(long tid);

	/**
	 * <p>
	 * Description:[根据 “表名、列名” 获取其所对应所有的列]
	 * </p>
	 * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param columnName
	 * @param tableName
	 * @return List<MetadataColumnPO>
	 */
	public List<MetadataColumnPO> getColumnBySource(String columnName, String tableName);

	/**
	 * <p>
	 * Description:[根据 “表ID、列名” 获取MetadataColumn对象]
	 * </p>
	 * Created by [GLJ] [2015-03-25] Modified by [修改人] [修改时间]
	 * 
	 * @param columnName
	 * @param tableId
	 * @return MetadataColumnPO
	 */
	public MetadataColumnPO getByTableIdAndColumnName(long tableId, String columnName);

	/**
	 * <p>
	 * Description:[根据 “表名、列名” 查询column id]
	 * </p>
	 * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param columnName
	 * @param tableName
	 * @return long[]
	 */
	public long[] findByTableNameAndColumnName(String columnName, String tableName);

	/**
	 * <p>
	 * Description:[根据table id获取数据追踪所需的全部列]
	 * </p>
	 * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param tid
	 * @param key
	 * @param date
	 * @return List<MetadataColumnPO>
	 */
	public List<MetadataColumnPO> getForDataTrackByTableId(Long tid, String key, String date);

	/**
	 * <p>
	 * Description:[根据cId查询列]
	 * </p>
	 * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
	 * 
	 * @param cid
	 * @return MetadataColumnPO
	 */
	public MetadataColumnPO getColumnById(long cid);

	/**
	 * <p>
	 * Description:[保存MetadataColumnPO对象到数据库]
	 * </p>
	 * Created by [GLJ] [2015-03-25] Modified by [修改人] [修改时间]
	 * 
	 * @param mcp
	 * @return MetadataColumnPO
	 */
	// FIXME 和另一个save用处区别？
	public MetadataColumnPO save(MetadataColumnPO mc);

	/**
	 * <p>
	 * Description:[新增元数据列]
	 * </p>
	 * Created by [CSJ] [2015-03-23] Modified by [修改人] [修改时间]
	 * 
	 * @param tableId
	 * @param mcp
	 * @return MetadataColumnPO
	 */
	public MetadataColumnPO save(long tableId, MetadataColumnPO mcp);

	/**
	 * <p>
	 * Description:[删除元数据列]
	 * </p>
	 * Created by [CSJ] [2015-03-23] Modified by [修改人] [修改时间]
	 * 
	 * @param mcp
	 * @return
	 */
	public void delete(MetadataColumnPO mcp);

	/**
	 * <p>
	 * Description:[更新元数据列]
	 * </p>
	 * Created by [CSJ] [2015-03-24] Modified by [修改人] [修改时间]
	 * 
	 * @param MetadataColumnPO cPo
	 * @return MetadataColumnPO
	 */
	public MetadataColumnPO update(MetadataColumnPO cPo);

	/**
	 * <p>
	 * Description:[获取表的所有开放的字段]
	 * </p>
	 * Created by [CSJ] [2015-03-25] Modified by [修改人] [修改时间]
	 * 
	 * @param long tableId
	 * @return List<MetadataColumnPO>
	 */
	public List<MetadataColumnPO> getOpenLevelColumnByTableId(Long tableId);
}

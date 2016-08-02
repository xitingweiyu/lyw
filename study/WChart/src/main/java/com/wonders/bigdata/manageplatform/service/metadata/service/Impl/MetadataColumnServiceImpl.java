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

package com.wonders.bigdata.manageplatform.service.metadata.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.bigdata.manageplatform.service.metadata.dao.MetadataColumnDao;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataColumnPO;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataColumnService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[元数据表字段]
 * </p>
 * <p>
 * Description: [元数据表字段Service接口实现类]
 * </p>
 * 
 * @author GLJ
 * @version $Revision$ 2015年3月20日
 * @author (latest modification by $Author$)
 * @since 20100901
 */
@Service("metadataColumnServiceImpl")
public class  MetadataColumnServiceImpl implements MetadataColumnService {

	@Resource(name = "metadataColumnDaoImpl")
	private MetadataColumnDao metadataColumnDao;

	@Override
	public List<MetadataColumnPO> getAllColumnByTableId(long tid) {
		if (tid == 0) {
			return null;
		}
		List<MetadataColumnPO> result = metadataColumnDao.findByProperty("tableId", tid);
		return result;
	}

	@Override
	public List<MetadataColumnPO> getColumnBySource(String columnName, String tableName) {
		List<MetadataColumnPO> result = new ArrayList<MetadataColumnPO>();

		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("deleteFlag", Constant.METADATA_NOT_DELETE);
		List<String> notNull = new ArrayList<String>();
		notNull.add("source");

		QueryParam param = new QueryParam();
		param.setEq(eq);
		param.setNotNull(notNull);

		List<MetadataColumnPO> cols = metadataColumnDao.findByAnd(param);
		for (MetadataColumnPO c : cols) {
			try {
				JSONObject source = new JSONObject(c.getSource());
				JSONArray cs = source.getJSONArray("resourceColumns");
				for (int i = 0; i < cs.length(); i++) {
					JSONObject o = cs.getJSONObject(i);
					String colTmp = o.getString("column");
					if (colTmp.indexOf('.') != -1) {
						colTmp = colTmp.substring(colTmp.indexOf('.') + 1, colTmp.length());
					}
					if (columnName.equals(colTmp) && tableName.equals(o.getString("table"))) {
						result.add(c);
					}
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public MetadataColumnPO getByTableIdAndColumnName(long tableId, String columnName) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tableId", tableId);
		paramMap.put("columnName", columnName);
		paramMap.put("deleteFlag", Constant.METADATA_NOT_DELETE);

		List<MetadataColumnPO> mcs = metadataColumnDao.findBy(paramMap);

		if (mcs != null && mcs.size() > 0) {
			return mcs.get(0);
		}
		else {
			return new MetadataColumnPO();
		}
	}

	@Override
	public long[] findByTableNameAndColumnName(String columnName, String tableName) {
		// TODO
		// @Query(value="select c.id from bd_metadata_column c, bd_metadata_table t where c.table_id = t.id and c.column_name = ?1 and t.table_name = ?2 and c.delete_flag = 0 and t.delete_flag = 0",
		return null;
	}

	@Override
	public List<MetadataColumnPO> getForDataTrackByTableId(Long tid, String key, String date) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tableId", tid);
		paramMap.put("deleteFlag", Constant.METADATA_NOT_DELETE);
		return metadataColumnDao.findBy(paramMap);
	}

	@Override
	public MetadataColumnPO getColumnById(long cid) {
		return metadataColumnDao.findUniqueByProperty("id", cid);
	}

	@Override
	public MetadataColumnPO save(MetadataColumnPO mc) {
		return metadataColumnDao.save(mc);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MetadataColumnPO save(long tableId, MetadataColumnPO mcp) {
		HashMap<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("tableId", tableId);
		searchParams.put("columnName", mcp.getColumnName());
		searchParams.put("deleteFlag", Constant.METADATA_NOT_DELETE);
		List<MetadataColumnPO> metadataColumnPOs = metadataColumnDao.findBy(searchParams);
		if (metadataColumnPOs != null && metadataColumnPOs.size() > 0) {
			MetadataColumnPO metadataColumnPO = metadataColumnPOs.get(0);
			mcp = metadataColumnPO;
			mcp.setEditDate(new Date());
		}
		metadataColumnDao.save(mcp);
		return mcp;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MetadataColumnPO mcp) {
		mcp.setDeleteFlag(Constant.METADATA_DELETE);
		metadataColumnDao.save(mcp);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MetadataColumnPO update(MetadataColumnPO cPo) {
		return metadataColumnDao.update(cPo);
	}

	@Override
	public List<MetadataColumnPO> getOpenLevelColumnByTableId(Long tableId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tableId", tableId);
		paramMap.put("openLevel", Constant.METADATA_OPEN);
		paramMap.put("deleteFlag", Constant.METADATA_NOT_DELETE);
		return metadataColumnDao.findBy(paramMap);
	}

}

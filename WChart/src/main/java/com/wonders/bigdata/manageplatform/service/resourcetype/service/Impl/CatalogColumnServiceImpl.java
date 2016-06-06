package com.wonders.bigdata.manageplatform.service.resourcetype.service.Impl;

import com.wonders.bigdata.manageplatform.service.resourcetype.dao.CatalogColumnDao;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogColumnPO;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.CatalogColumnService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.util.QueryParam;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标准目录字段关联表service层实现类
 * 
 * @author xh 2015-10-28 11:06:06
 *
 */
@Service("catalogColumnServiceImpl")
public class CatalogColumnServiceImpl implements CatalogColumnService {

	@Autowired
	private CatalogColumnDao catalogColumnDao;

	@Override
	public void saveOrUpdate(CatalogColumnPO catalogColumnPO) {
		catalogColumnDao.save(catalogColumnPO);

	}

	/**
	 * @author xh 2015-10-28 11:11:18
	 */
	@Override
	public void delete(CatalogColumnPO catalogColumnPO) {
		catalogColumnPO.setDeleteFlag(Constant.CATALOGCOLUMN_DELETE);
		catalogColumnDao.save(catalogColumnPO);
	}
	
	@Override
	public void delete(long id) {
		CatalogColumnPO po = catalogColumnDao.get(id);
		po.setDeleteFlag(Constant.CATALOGCOLUMN_DELETE);
		catalogColumnDao.save(po);
	}
	
	@Override
	public CatalogColumnPO getById(long id)
	{
		return catalogColumnDao.get(id);
	}
	/**
	 * @author xh 2015-10-28 11:11:31
	 */
	@Override
	public List<CatalogColumnPO> findAllByCatalogTableId(long catalogTableId) {
		
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("catalogTableId", catalogTableId);
		eq.put("deleteFlag", Constant.CATALOGCOLUMN_UN_DELETE);
		param.setEq(eq);
		List<CatalogColumnPO> tmp = catalogColumnDao.findByAnd(param);
		return tmp;
	}

	/**
	 * @author xh 2015-10-28 11:11:46
	 */
	@Override
	public List<CatalogColumnPO> findOpenByCatalogTableId(long catalogTableId) {
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("catalogTableId", catalogTableId);
		eq.put("deleteFlag", Constant.CATALOGCOLUMN_UN_DELETE);
		//eq.put("openLevel", Constant.CATALOGCOLUMN_OPEN);
		//eq.put("status", Constant.CATALOG_COLUMN_STATUS_SUBMIT_PASS);
		param.setEq(eq);
		List<CatalogColumnPO> tmp = catalogColumnDao.findByAnd(param);
		return tmp;

	}

	/**
	 * @author xh 2015-10-28 11:13:16 
	 */
	@Override
	public List<CatalogColumnPO> findByParam(QueryParam param) {
		List<CatalogColumnPO> tmp = catalogColumnDao.findByAnd(param);
		return tmp;
	}

	/**
	 * Created by LXL on 2016/3/4
	 * <br> 将期望的PO信息存入apply_context字段中
	 *
	 * @param catalogColumnPO 挂接表字段PO
	 */
	@Override
	public String getApplyContextFromPO(CatalogColumnPO catalogColumnPO) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", catalogColumnPO.getId());
			jsonObject.put("catalog_table_id", catalogColumnPO.getCatalogTableId());
			jsonObject.put("column_name", catalogColumnPO.getColumnName());
			jsonObject.put("type_name", catalogColumnPO.getTypeName());
			jsonObject.put("remark", catalogColumnPO.getRemark());
			jsonObject.put("open_level", catalogColumnPO.getOpenLevel());
			jsonObject.put("create_date", catalogColumnPO.getCreateDate());
			jsonObject.put("delete_flag", catalogColumnPO.getDeleteFlag());
			jsonObject.put("description", catalogColumnPO.getDescription());
			jsonObject.put("edit_date", catalogColumnPO.getEditDate());
			jsonObject.put("status", catalogColumnPO.getStatus());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * Created by LXL on 2016/3/4
	 * <br> 根据id解析apply_context字段以获取期望的PO
	 *
	 * @param id 挂接表字段id
	 *
	 * @return 期望的PO信息
	 */
	@Override
	public CatalogColumnPO getPOFromApplyContext(Long id) {
		CatalogColumnPO catalogColumnPO = findById(id);
		CatalogColumnPO catalogColumnPO1 = new CatalogColumnPO();
		try {
			JSONObject jsonObject = new JSONObject(catalogColumnPO.getApplyContext());
			catalogColumnPO1.setId(jsonObject.getLong("id"));
			catalogColumnPO1.setCatalogTableId(jsonObject.getLong("catalog_table_id"));
			catalogColumnPO1.setColumnName(jsonObject.getString("column_name"));
			catalogColumnPO1.setTypeName(jsonObject.getString("type_name"));
			catalogColumnPO1.setRemark(jsonObject.getString("remark"));
			catalogColumnPO1.setOpenLevel(jsonObject.getInt("open_level"));
			catalogColumnPO1.setCreateDate(new Date());
			catalogColumnPO1.setDeleteFlag(jsonObject.getInt("delete_flag"));
			catalogColumnPO1.setDescription(jsonObject.getString("description"));
			catalogColumnPO1.setEditDate(new Date());
			catalogColumnPO1.setStatus(jsonObject.getInt("status"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return catalogColumnPO1;
	}

	@Override
	public CatalogColumnPO findById(long id) {

		return catalogColumnDao.get(id);
	}
}

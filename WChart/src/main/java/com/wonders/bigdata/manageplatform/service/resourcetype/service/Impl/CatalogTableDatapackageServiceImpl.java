package com.wonders.bigdata.manageplatform.service.resourcetype.service.Impl;

import com.wonders.bigdata.manageplatform.service.resourcetype.dao.CatalogTableDao;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogTableDatapackagePO;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogTablePO;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.CatalogTableDatapackageService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("catalogTableDatapackageServiceImpl")
public class  CatalogTableDatapackageServiceImpl implements
		CatalogTableDatapackageService {

	@Autowired
	private CatalogTableDao catalogTableDao;



	@Override
	public CatalogTablePO findById(long id) {

		return null;
	}


	@Override
	public int countByCatalogId(long catalogId) {
		String hql = "from CatalogTableDatapackagePO where catalogId=? and tableOpenLevel="+Constant.METADATA_OPEN;
		int sum = catalogTableDao.find(hql, catalogId).size();
		return sum;
	}

	@Override
	public List<CatalogTablePO> searchByNameAndCatalog(
			long catalogId, String name) {

		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("catalogId", catalogId);
		eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
		param.setEq(eq);
		Map<String, Object> like = new HashMap<String, Object>();
		like.put("tableDatapackageName", name);
		param.setLike(like);
		List<CatalogTablePO> result = catalogTableDao
				.findByAnd(param);
		return result;

	}
	
	@Override
	public List<CatalogTablePO> searchByNameAndCatalog(
			long[] catalogIds, String name) {

		QueryParam queryParam = new QueryParam();
		// 将long转为Long
		Object[] idsL = new Object[catalogIds.length];
		for (int i = 0; i < catalogIds.length; i++) {
			idsL[i] = catalogIds[i];
		}

		Map<String, Object[]> inMap = new HashMap<String, Object[]>();
		inMap.put("catalogId", idsL);
		queryParam.setIn(inMap);
		
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
		eq.put("tableOpenLevel", Constant.METADATA_TABLE_NAME_OPEN_LEVEL_OPEN);
		queryParam.setEq(eq);
		
		Map<String, Object> like = new HashMap<String, Object>();
		like.put("tableDatapackageName", name);
		queryParam.setLike(like);
		return catalogTableDao.findByAnd(queryParam);
	}

	@Override
	public List<CatalogTablePO> findByIds(long[] ids) {
		if(ids==null||ids.length<=0)
			return null;
		QueryParam queryParam = new QueryParam();
		// 将long转为Long
		Object[] idsL = new Object[ids.length];
		for (int i = 0; i < ids.length; i++) {
			idsL[i] = ids[i];
		}

		Map<String, Object[]> inMap = new HashMap<String, Object[]>();
		inMap.put("catalogId", idsL);
		queryParam.setIn(inMap);
		
		Map<String, Object> eqMap = new HashMap<String, Object>();
		eqMap.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
		eqMap.put("tableOpenLevel", Constant.METADATA_TABLE_NAME_OPEN_LEVEL_OPEN);
		queryParam.setEq(eqMap);
		
		return catalogTableDao.findByAnd(queryParam);
	}

	@Override
	public List<CatalogTablePO> findByIds(long[] ids, int limit) {
		
		//return catalogTableDao.findByIds(ids, limit);
		return null;
	}

	@Override
	public List<CatalogTablePO> findCatalog(Long standardId,Long metaTableId){
		QueryParam param=new QueryParam();
		Map<String,Object> eq=new HashMap<String,Object>();
		eq.put("catalogId", standardId);
		eq.put("tablePackageId", metaTableId);
		eq.put("type", Constant.CATALOG_TYPE_TABLE);
		param.setEq(eq);
		return catalogTableDao.findByAnd(param);
	}

	@Override
	public List<CatalogTablePO> findByTablePackageId(long id) {
		
		QueryParam param=new QueryParam();
		Map<String,Object> eq=new HashMap<String,Object>();
		eq.put("tablePackageId", id);
		eq.put("type",Constant.CATALOG_TYPE_PACKAGE);
		eq.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
		param.setEq(eq);
		return catalogTableDao.findByAnd(param);
	}

	@Override
	public List<CatalogTablePO> findTablesByCatalogId(long[] ids) {
		if(ids==null||ids.length<=0)
			return null;
		QueryParam queryParam = new QueryParam();
		// 将long转为Long
		Object[] idsL = new Object[ids.length];
		for (int i = 0; i < ids.length; i++) {
			idsL[i] = ids[i];
		}

		Map<String, Object[]> inMap = new HashMap<String, Object[]>();
		inMap.put("catalogId", idsL);
		queryParam.setIn(inMap);
		
		Map<String, Object> eqMap = new HashMap<String, Object>();
		eqMap.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
		eqMap.put("type", Constant.CATALOG_TYPE_TABLE);
		eqMap.put("tableOpenLevel", Constant.METADATA_TABLE_NAME_OPEN_LEVEL_OPEN);
		queryParam.setEq(eqMap);
		
		return catalogTableDao.findByAnd(queryParam);
	}
	@Override
	public List<CatalogTablePO> getTablesByCatalogId(Long standardCatalogId){
		List<CatalogTableDatapackagePO> pos=new ArrayList<>();
		QueryParam param=new QueryParam();
		Map<String, Object> eq= new HashMap<String, Object>();
		eq.put("catalogId", standardCatalogId);
		eq.put("type", Constant.CATALOG_TYPE_TABLE);
		eq.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
		eq.put("tableOpenLevel", Constant.METADATA_TABLE_NAME_OPEN_LEVEL_OPEN);

		param.setEq(eq);
		//pos= catalogTableDao.findByAnd(param);

		//return pos;
		return null;
	}
	
	@Override
	public List<CatalogTablePO> getTablesByTableId(Long tablePackageId){
		List<CatalogTableDatapackagePO> pos=new ArrayList<>();
		QueryParam param=new QueryParam();
		Map<String, Object> eq= new HashMap<String, Object>();
		eq.put("tablePackageId", tablePackageId);
		eq.put("type", Constant.CATALOG_TYPE_TABLE);
		eq.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
		param.setEq(eq);
//		pos= catalogTableDao.findByAnd(param);
//		return pos;
		return null;
	}
	
		@Override
	public List<CatalogTablePO> getByTableOrPackageName(String name) {
		
		List<CatalogTableDatapackagePO> pos=new ArrayList<>();
		QueryParam param=new QueryParam();
		Map<String, Object> eq= new HashMap<String, Object>();
		eq.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
		eq.put("tableDatapackageName", name);
		param.setEq(eq);
//		pos= catalogTableDao.findByAnd(param);
//		return pos;
			return null;
	}

		@Override
		public Page<CatalogTablePO> findPageByRootId(Long[] catalogIds, int pageNum, int pageSize) {
			// TODO Auto-generated method stub
			QueryParam param = new QueryParam();
			Page<CatalogTableDatapackagePO> page = new Page<CatalogTableDatapackagePO>();
			Map<String, Object[]> in = new HashMap<>();
			Map<String, Object> eq= new HashMap<String, Object>();
			in.put("catalogId", catalogIds);
			eq.put("deleteFlag", 0);
			param.setIn(in);
			param.setEq(eq);
			page.setStart((pageNum - 1) * pageSize);
			page.setPagesize(pageSize);
			page.setParam(param);
			//return catalogTableDao.findByPage(page);
			return null;
		}
}

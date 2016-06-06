package com.wonders.bigdata.manageplatform.service.resourcetype.service.Impl;

import com.wonders.bigdata.NotLoginPropertyTool;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataTableService;
import com.wonders.bigdata.manageplatform.service.resourcetype.dao.StandardCatalogDao;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.StandardCatalogPO;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.CatalogTableDatapackageService;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.StandardCatalogService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service("standardCatalogServiceImpl")
public class StandardCatalogServiceImpl implements StandardCatalogService{


	@Autowired
	private StandardCatalogDao standardCatalogDao;

	@Autowired
	private CatalogTableDatapackageService catalogTableDatapackageService;

	@Autowired
	private MetadataTableService metadataTableService;


	private static NotLoginPropertyTool notLoginPropertyTool;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public StandardCatalogPO save(StandardCatalogPO po) {
		return standardCatalogDao.save(po);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public StandardCatalogPO update(StandardCatalogPO po) {
		return standardCatalogDao.update(po);
	}

	@Override
	public void deleteById(long id) {
		StandardCatalogPO po = standardCatalogDao.get(id);
		if (po != null) {
			po.setDelete_flag(Constant.RESOURCE_DELETE);
			standardCatalogDao.save(po);
		}
	}

	@Override
	public StandardCatalogPO findById(Long id) {
		return standardCatalogDao.get(id);
	}

	@Override
	public List<StandardCatalogPO> findAllRootNodes() {
		// TODO Auto-generated method stub
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("parent_id", new Long(0));
		eq.put("delete_flag", Constant.RESOURCE_UN_DELETE);
		param.setEq(eq);
		List<StandardCatalogPO> list = standardCatalogDao.findByAnd(param);
		return list;
	}

	@Override
	public List<StandardCatalogPO> findChilderes(Long id) {
		// TODO Auto-generated method stub
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("parent_id", id);
		eq.put("delete_flag", Constant.RESOURCE_UN_DELETE);
		param.setEq(eq);
		List<StandardCatalogPO> list = standardCatalogDao.findByAnd(param);
		return list;
	}

	@Override
	public List<StandardCatalogPO> findAllLeafNodes(Long id) {
		List<StandardCatalogPO> listsCatalogPOs = new ArrayList<StandardCatalogPO>();
		getList(id, listsCatalogPOs);
		return listsCatalogPOs;
	}

	private void getList(Long id,List<StandardCatalogPO> listsCatalogPOs){
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("parent_id", id);
		eq.put("delete_flag", Constant.RESOURCE_UN_DELETE);
		param.setEq(eq);
		List<StandardCatalogPO> list = standardCatalogDao.findByAnd(param);
		for(StandardCatalogPO po:list){
			if(po!=null&&po.getIsleafnode().equals(Constant.CATALOG_IS_LEAFNODE_YES)){//判断是否是叶节点
				listsCatalogPOs.add(po);
			}else{
				getList(po.getId(),listsCatalogPOs);
			}
		}
	}

	@Override
	public List<Map<String, Object>> getAllCatalogTables() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<StandardCatalogPO> rts = findAllRootNodes();
		for (StandardCatalogPO rt : rts) {
			Map<String, Object> mrt = new HashMap<String, Object>();
			mrt.put("id", rt.getId());
			mrt.put("label", rt.getName());
			//createTree(rt, mrt);
//			List<StandardCatalogPO> drs = findChilderes(rt.getId());
//			List<Map<String, Object>> mdrs = new ArrayList<Map<String, Object>>();
//			for (StandardCatalogPO dr : drs) {
//				Map<String, Object> mdr = new HashMap<String, Object>();
//				mdr.put("id", dr.getId());
//				mdr.put("label", dr.getName());
//				List<MetadataTablePO> mts = getTablesByResource(dr.getId());
//				List<Map<String, Object>> mmts = new ArrayList<Map<String, Object>>();
//				if (mts != null) {
//					for (MetadataTablePO mt : mts) {
//						Map<String, Object> mmt = new HashMap<String, Object>();
//						mmt.put("id", mt.getId());
//						if (mt.getRemark() != null && !mt.getRemark().trim().equals("")) {
//							mmt.put("label", mt.getTableName() + "(" + mt.getRemark() + ")");
//						}
//						else {
//							mmt.put("label", mt.getTableName());
//						}
//						mmts.add(mmt);
//					}
//				}
//				mdr.put("children", mmts);
//				mdrs.add(mdr);
//			}
//			mrt.put("children", mdrs);
			result.add(mrt);
		}
		return result;
	}



	@Override
	public String getNotLoginCatalogInfo(String catainfo) {
		// TODO Auto-generated method stub
		String temp="";
		notLoginPropertyTool = new NotLoginPropertyTool();
		try {
			temp = notLoginPropertyTool.readProperty(catainfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return temp;
	}

	@Override
	public void SaveNotLoginCatalogInfo(Properties propertie, String description) {
		// TODO Auto-generated method stub
		notLoginPropertyTool = new NotLoginPropertyTool();
		notLoginPropertyTool.saveProperty(propertie, description);

	}

	@Override
	public Page<StandardCatalogPO> findPageByRootId(long rootId,int pageNum,int pageSize) {
		QueryParam param = new QueryParam();
		Page<StandardCatalogPO> page = new Page<StandardCatalogPO>();

		List<StandardCatalogPO> lists = this.findChilderes(rootId);
		Map<String, Object[]> in = new HashMap<>();
		Map<String, String> orderMap = new HashMap<String, String>();
		//子节点id
		Long[] ids = new Long[lists.size()];
		for(int i = 0; i < lists.size(); i++) {
			ids[i] = lists.get(i).getId();
		}
		in.put("id", ids);
		orderMap.put("id", "desc");
		param.setIn(in);
		param.setOrder(orderMap);
		page.setParam(param);

		page.setStart( (pageNum - 1) * pageSize);
		page.setPagesize(pageSize);

		Page<StandardCatalogPO> result = standardCatalogDao.findByPage(page);
		return result;

	}
}

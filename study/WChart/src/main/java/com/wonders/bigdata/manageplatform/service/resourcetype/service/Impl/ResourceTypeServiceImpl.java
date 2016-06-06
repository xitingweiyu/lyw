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

package com.wonders.bigdata.manageplatform.service.resourcetype.service.Impl;

import com.wonders.bigdata.manageplatform.service.common.service.JDBCService;
import com.wonders.bigdata.manageplatform.service.dataresource.dao.DataResourceDao;
import com.wonders.bigdata.manageplatform.service.dataresource.model.po.DataResourcePO;
import com.wonders.bigdata.manageplatform.service.metadata.dao.MetadataTableDao;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataTablePO;
import com.wonders.bigdata.manageplatform.service.resourcetype.dao.ResourceTypeDao;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.ResourceTypePO;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.ResourceTypeService;
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

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[资源分类]
 * </p>
 * <p>
 * Description: [资源分类service接口实现类]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月18日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Service("resourceTypeServiceImpl")
public class  ResourceTypeServiceImpl implements ResourceTypeService {

	
	@Autowired
	private DataResourceDao dataResourceDao;

	@Autowired
	private ResourceTypeDao resourceTypeDao;

	@Autowired
	private MetadataTableDao metadataTableDao;

	@Autowired
	private JDBCService jdbcService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResourceTypePO save(ResourceTypePO po) {
		return resourceTypeDao.save(po);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(long id) {
		// TODO 待完善的业务逻辑
		ResourceTypePO po = resourceTypeDao.get(id);
		if (po != null) {
			po.setDeleteFlag(Constant.RESOURCE_DELETE);
			resourceTypeDao.save(po);
		}
	}

	@Override
	public Page<ResourceTypePO> findByPage(Page<ResourceTypePO> page) {
		return resourceTypeDao.findByPage(page);
	}

	@Override
	public List<ResourceTypePO> findResourceByList(QueryParam param) {
		return resourceTypeDao.findByAnd(param);
	}

	@Override
	public ResourceTypePO findById(Long id) {
		return resourceTypeDao.get(id);
	}

	@Override
	public Boolean isResourceTypeUsed(String id) {
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("resourceType", id);
		eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
		param.setEq(eq);
		List<DataResourcePO> list = dataResourceDao.findByAnd(param);
		boolean flag = false;
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}




	/*
	 * 获取资源下的所有的表
	 */
	private List<MetadataTablePO> getTablesByResource(long drId) {
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("resourceId", drId);
		eq.put("openLevel", Constant.METADATA_TABLE_NAME_OPEN_LEVEL_OPEN);
		eq.put("status", Constant.METADATA_TABLE_STATUS_AUDITED);
		eq.put("deleteFlag", Constant.METADATA_TABLE_NAME_NOT_DELETE);
		param.setEq(eq);
		return metadataTableDao.findByAnd(param);
	}

	@Override
	public List<ResourceTypePO> getAllResourceType() {
		List<ResourceTypePO> types = new ArrayList<>();
		String sql = "select * from bd_resource_type where delete_flag = "+Constant.RESOURCE_UN_DELETE+";";
		List<List<String>> l1 = new ArrayList<>();

		l1 = jdbcService.executeSQL(sql);
		for(int i = 0; i<l1.size() ; i++){
			List<String> newli = new ArrayList<>();
			newli = l1.get(i);
			ResourceTypePO po = new ResourceTypePO();
			po.setId(Long.parseLong(newli.get(0)));
			po.setName(newli.get(1));
			types.add(po);
		}


		return types;
	}

	@Override
	public ResourceTypePO findNameById(long id) {
		return resourceTypeDao.get(id);
	}
}

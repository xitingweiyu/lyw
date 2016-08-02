package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.Impl;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.RoleAuthorityDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.RoleAuthorityPO;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.RoleAuthorityService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author hsw
 *
 */

@Service("roleAuthorityServiceImpl")
public class  RoleAuthorityServiceImpl implements RoleAuthorityService{

	@Resource(name = "roleAuthorityDaoImpl")
	private RoleAuthorityDao roleAuthorityDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public RoleAuthorityPO save(RoleAuthorityPO roleAuthorityPO) {
		return roleAuthorityDao.save(roleAuthorityPO);
	}

	@Override
	public RoleAuthorityPO get(long id) {
		return roleAuthorityDao.get(id);
	}

	@Override
	public List<RoleAuthorityPO> getByRoleId(long roleId) {
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("roleId", roleId);
		eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
		param.setEq(eq);
		List<RoleAuthorityPO> roleAuthorityPOs = roleAuthorityDao.findByAnd(param);
		return roleAuthorityPOs;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(RoleAuthorityPO roleAuthorityPO) {
		roleAuthorityDao.update(roleAuthorityPO);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(RoleAuthorityPO roleAuthorityPO) {
		roleAuthorityPO.setDeleteFlag(Constant.AUTHORITY_DELETE);
		roleAuthorityDao.update(roleAuthorityPO);
	}

	@Override
	public Page<RoleAuthorityPO> getRoleAuthorityPage(Page<RoleAuthorityPO> page) {
		return roleAuthorityDao.findByPage(page);
	}

}

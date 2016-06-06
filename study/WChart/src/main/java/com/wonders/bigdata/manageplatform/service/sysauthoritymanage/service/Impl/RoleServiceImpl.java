package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.Impl;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.RoleDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.RolePO;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.RoleService;
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
 * role service 实现
 * @author hsw
 *
 */
@Service("roleServiceImpl")
public class  RoleServiceImpl implements RoleService{

	@Resource(name = "roleDaoImpl")
	private RoleDao roleDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public RolePO save(RolePO rolePo) {
		return roleDao.save(rolePo);
	}

	@Override
	public RolePO get(long id) {
		return roleDao.get(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(RolePO rolePO) {
		roleDao.update(rolePO);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(RolePO rolePO) {
		rolePO.setDeleteFlag(Constant.AUTHORITY_DELETE);
		roleDao.update(rolePO);
	}

	@Override
	public Page<RolePO> getRolePage(Page<RolePO> page) {
		return roleDao.findByPage(page);
	}

	@Override
	public List<RolePO> getRolePOsByName(String name) {
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		if(name!=null&&!name.trim().equals("")){
			eq.put("name", name);
		}
		eq.put("deleteFlag", Constant.AUTHORITY_NOT_DELETE);
		param.setEq(eq);
		return roleDao.findByAnd(param);
	}
}

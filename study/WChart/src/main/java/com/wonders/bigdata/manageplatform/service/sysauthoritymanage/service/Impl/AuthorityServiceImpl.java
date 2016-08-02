package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.Impl;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.AuthorityDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.AuthorityPO;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.AuthorityService;
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
@Service("authorityServiceImpl")
public class  AuthorityServiceImpl implements AuthorityService{

	@Resource(name = "authorityDaoImpl")
	private AuthorityDao authorityDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AuthorityPO save(AuthorityPO authorityPO) {
		return authorityDao.save(authorityPO);
	}

	@Override
	public AuthorityPO get(long id) {
		return authorityDao.get(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(AuthorityPO authorityPO) {
		authorityDao.update(authorityPO);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(AuthorityPO authorityPO) {
		authorityPO.setDeleteFlag(Constant.AUTHORITY_DELETE);
		authorityDao.update(authorityPO);
	}

	@Override
	public Page<AuthorityPO> getAuthorityPage(Page<AuthorityPO> page) {
		return authorityDao.findByPage(page);
	}

	@Override
	public List<AuthorityPO> getAuthorityPOsByName(String name) {
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		if(name!=null&&!name.trim().equals("")){
			eq.put("name", name);
		}
		eq.put("deleteFlag", Constant.AUTHORITY_NOT_DELETE);
		param.setEq(eq);
		return authorityDao.findByAnd(param);
	}

}

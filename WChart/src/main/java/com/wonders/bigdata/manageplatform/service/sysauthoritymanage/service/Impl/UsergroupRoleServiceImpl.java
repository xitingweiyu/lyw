package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.Impl;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.UsergroupRoleDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.UsergroupRolePO;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.UsergroupRoleService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户组角色service层实现类
 * 
 * @author xuehan
 * @date 2015年4月13日 下午3:04:54
 */
@Service("usergroupRoleServiceImpl")
public class  UsergroupRoleServiceImpl implements UsergroupRoleService {

	@Resource(name = "usergroupRoleDaoImpl")
	private UsergroupRoleDao usergroupRoleDao;

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public UsergroupRolePO save(UsergroupRolePO usergroupRolePO) {
		return usergroupRoleDao.save(usergroupRolePO);
	}

	@Override
	public UsergroupRolePO get(long usergroupRoleId) {
		return usergroupRoleDao.get(usergroupRoleId);
	}

	@Override
	public List<UsergroupRolePO> getByUsergroupId(long usergroupId) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("usergroupId", usergroupId);
		return usergroupRoleDao.findBy(paramMap);

	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(UsergroupRolePO usergroupRolePO) {
		usergroupRoleDao.update(usergroupRolePO);

	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void delete(long usergroupRoleId) {
		
		UsergroupRolePO usergroupRolePO = get(usergroupRoleId);
		
		usergroupRolePO.setDeleteFlag(Constant.USER_DELETE);
		Date deleteDate = new Date();
		usergroupRolePO.setDeleteDate(deleteDate);
		update(usergroupRolePO);

	}

	@Override
	public Page<UsergroupRolePO> getUsergroupRolePage(Page<UsergroupRolePO> page) {
		// TODO Auto-generated method stub
		return usergroupRoleDao.findByPage(page);
	}

}

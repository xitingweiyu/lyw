package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.Impl;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.UserUsergroupDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.UserUsergroupPO;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.UserUsergroupService;
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

@Service("userUsergroupServiceImpl")
public class  UserUsergroupServiceImpl implements UserUsergroupService {

	@Resource(name = "userUsergroupDaoImpl")
	private UserUsergroupDao userUsergroupDao;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public UserUsergroupPO save(UserUsergroupPO userUsergroupPO) {
		return userUsergroupDao.save(userUsergroupPO);
	}

	@Override
	public UserUsergroupPO get(long userUsergroupId) {
		return userUsergroupDao.get(userUsergroupId);
	}

	@Override
	public List<UserUsergroupPO> getByUsergroupId(long usergroupId) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("usergroupId", usergroupId);
		return userUsergroupDao.findBy(paramMap);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(UserUsergroupPO userUserGroupPO) {
		userUsergroupDao.update(userUserGroupPO);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(long userUsergroupId) {
		UserUsergroupPO userUsergroupPO = get(userUsergroupId);
		userUsergroupPO.setDeleteFlag(Constant.USER_DELETE);
		Date deleteDate = new Date();
		userUsergroupPO.setDeleteDate(deleteDate);
		
		update(userUsergroupPO);
	}

	@Override
	public Page<UserUsergroupPO> getUserUsergroupPage(Page<UserUsergroupPO> page) {
		// TODO Auto-generated method stub
		return userUsergroupDao.findByPage(page);
	}

	@Override
	public List<UserUsergroupPO> getByUserId(long userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return userUsergroupDao.findBy(paramMap);
	}

}

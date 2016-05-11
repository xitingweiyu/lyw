package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.Impl;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.dao.UsergroupDao;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.UsergroupPO;
import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service.UsergroupService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * UsergroupService 实现类
 * @author xuehan
 * @date 2015年4月13日 上午9:43:32
 */
@Service("usergroupServiceImpl")
public class  UsergroupServiceImpl implements UsergroupService {
	
	@Resource(name="usergroupDaoImpl")
	private UsergroupDao usergroupDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UsergroupPO save(UsergroupPO usergroupPO) {
		return usergroupDao.save(usergroupPO);
	}
	
	
	@Override
	public UsergroupPO get(long usergroupId) {
		return usergroupDao.get(usergroupId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(UsergroupPO usergroupPO) {
		usergroupDao.update(usergroupPO);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void delete(long usergroupId) {
		UsergroupPO usergroupPO = get(usergroupId);
		usergroupPO.setDeleteFlag(Constant.USER_DELETE);
		Date deleteDate = new Date();
		usergroupPO.setDeleteDate(deleteDate);
		
		update(usergroupPO);
	}

	@Override
	public Page<UsergroupPO> getUsergroupPage(Page<UsergroupPO> page) {
		return usergroupDao.findByPage(page);
	}

}

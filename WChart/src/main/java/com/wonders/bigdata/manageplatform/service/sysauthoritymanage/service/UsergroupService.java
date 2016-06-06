package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.UsergroupPO;
import com.wonders.bud.framework.common.page.Page;

/**
 * 用户组服务类
 * @author xuehan
 * @date 2015年4月13日 上午9:52:36
 */
public interface  UsergroupService {

	/**
	 * 用户组对象新增
	 * 
	 * @param usergroupPO
	 */
	public UsergroupPO save(UsergroupPO usergroupPO);

	/**
	 * 得到用户组对象
	 * 
	 * @param usergroupId
	 *            用户组对象id
	 * @return 用户组对象
	 */
	public UsergroupPO get(long usergroupId);

	/**
	 * 用户组对象更新 
	 * @param usergroupPO
	 */
	public void update(UsergroupPO usergroupPO);
	
	/**
	 * 用户组删除
	 * @param usergroupId
	 */
	public void delete(long usergroupId);
	
	public Page<UsergroupPO> getUsergroupPage(Page<UsergroupPO> page);

}

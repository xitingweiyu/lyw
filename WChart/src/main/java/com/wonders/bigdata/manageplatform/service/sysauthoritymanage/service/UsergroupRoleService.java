package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service;

import java.util.List;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.UsergroupRolePO;
import com.wonders.bud.framework.common.page.Page;

/**
 * 用户组角色
 * @author xuehan
 * @date 2015年4月13日 下午2:55:25
 */
public interface  UsergroupRoleService {
	
	/**
	 * 新增用户组
	 * @param usergroupRolePO
	 * @return
	 */
	public UsergroupRolePO save(UsergroupRolePO usergroupRolePO);
	
	/**
	 * 得到用户组角色对象
	 * @param usergroupRoleId
	 * @return
	 */
	public UsergroupRolePO get(long usergroupRoleId);
	
	/**
	 * 根据用户组id查询
	 * @param usergroupId
	 * @return
	 */
	public List<UsergroupRolePO> getByUsergroupId(long usergroupId);
	
	/**
	 * 用户组角色更新
	 * @param usergroupRolePO
	 */
	public void update(UsergroupRolePO usergroupRolePO);
	
	/**
	 * 根据用户组角色id删除
	 * @param usergroupRoleId
	 */
	public void delete(long usergroupRoleId);
	
	/**
	 * 根据查询条件查询
	 * @param page
	 * @return
	 */
	public Page<UsergroupRolePO> getUsergroupRolePage(Page<UsergroupRolePO> page);
	
}

package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service;


import java.util.List;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.RoleAuthorityPO;
import com.wonders.bud.framework.common.page.Page;

/**
 * 
 * @author hsw
 *
 */
public interface  RoleAuthorityService {

	/**
	 * 保存角色、权限对象
	 * @param roleAuthorityPO
	 * @return
	 */
	public RoleAuthorityPO save(RoleAuthorityPO roleAuthorityPO);
	
	/**
	 * 根据id获取角色、权限对象
	 * @param id
	 * @return
	 */
	public RoleAuthorityPO get(long id);
	
	/**
	 * 根据角色id获取角色、权限对象列表
	 * @param roleId
	 * @return
	 */
	public List<RoleAuthorityPO> getByRoleId(long roleId);
	
	/**
	 * 更新角色、权限对象
	 * @param roleAuthorityPO
	 */
	public void update(RoleAuthorityPO roleAuthorityPO);
	
	/**
	 * 删除角色、权限对象
	 * @param roleAuthorityPO
	 */
	public void delete(RoleAuthorityPO roleAuthorityPO);
	
	/**
	 * 根据条件进行分页查询
	 * @param page
	 * @return
	 */
	public Page<RoleAuthorityPO> getRoleAuthorityPage(Page<RoleAuthorityPO> page);
}

package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.RolePO;
import com.wonders.bud.framework.common.page.Page;

import java.util.List;

public interface  RoleService {

	/**
	 * 保存角色对象
	 * @param rolePo
	 * @return
	 */
	public RolePO save(RolePO rolePo); 
	
	/**
	 * 根据id活体角色对象
	 * @param id
	 * @return
	 */
	public RolePO get(long id);
	
	/**
	 * 更新角色对象
	 * @param rolePO
	 */
	public void update(RolePO rolePO);
	
	/**
	 * 删除角色对象
	 * @param rolePO
	 */
	public void delete(RolePO rolePO);
	
	/**
	 * 根据条件查询页
	 * @param page
	 * @return
	 */
	public Page<RolePO> getRolePage(Page<RolePO> page);
	
	/**
	 * 根据角色名进行查询
	 * @param name
	 * @return
	 */
	public List<RolePO> getRolePOsByName(String name);
	
}

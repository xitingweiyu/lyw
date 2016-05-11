package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service;

import java.util.List;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.AuthorityPO;
import com.wonders.bud.framework.common.page.Page;

public interface  AuthorityService {

	/**
	 * 新增权限对象
	 * @param authorityPO
	 * @return
	 */
	public AuthorityPO save(AuthorityPO authorityPO);
	
	/**
	 * 根据id进行查询
	 * @param id
	 * @return
	 */
	public AuthorityPO get(long id);
	
	/**
	 * 更新权限对象
	 * @param authorityPO
	 */
	public void update(AuthorityPO authorityPO);
	
	/**
	 * 删除权限对象
	 * @param authorityPO
	 */
	public void delete(AuthorityPO authorityPO);
	
	/**
	 * 进行分页查询
	 * @param page
	 * @return
	 */
	public Page<AuthorityPO> getAuthorityPage(Page<AuthorityPO> page);
	
	/**
	 * 根据权限名进行查询
	 * @param name
	 * @return
	 */
	public List<AuthorityPO> getAuthorityPOsByName(String name);
}

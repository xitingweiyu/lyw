package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service;

import java.util.List;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.UserUsergroupPO;
import com.wonders.bud.framework.common.page.Page;

/**
 * 用户/用户组 服务接口定义
 * 
 * @author xuehan
 * @date 2015年4月13日 上午11:13:43
 */
public interface  UserUsergroupService {

	/**
	 * 新增用户/用户组
	 * 
	 * @param userUserGroupPO
	 * @return
	 */
	public UserUsergroupPO save(UserUsergroupPO userUsergroupPO);

	/**
	 * 得到用户/用户组对象
	 * 
	 * @param userUsergroupId
	 * @return
	 */
	public UserUsergroupPO get(long userUsergroupId);

	/**
	 * 根据用户组id查询
	 * 
	 * @param usergroupId
	 * @return
	 */
	public List<UserUsergroupPO> getByUsergroupId(long usergroupId);

	/**
	 * 用户/用户组对象更新
	 * 
	 * @param userUserGroupPO
	 */
	public void update(UserUsergroupPO userUserGroupPO);

	/**
	 * 用户/用户组对象删除 用法:
	 * 先通过上面定义的get方法获取角色对象（UserUsergroupPO）,然后调用UserUsergroupDao的delete方法
	 * 
	 * @param userUsergroupId
	 */
	public void delete(long userUsergroupId);

	/**
	 * 根据条件分页查询 
	 * <br />用法:调用该方法时，page参数的组织 
	 * <br />Page< UserUsergroupPO > page = new Page<UserUsergroupPO >(); 
	 * <br />page. setStart(1);//设置从第几条开始 page.
	 * <br />setPagesize(10);//设置每页多少条 QueryParam queryParam = new
	 * <br />QueryParam();//设置查询条件 page. setParam(queryParam);
	 * 
	 * 
	 * @param page
	 * @return
	 */
	public Page<UserUsergroupPO> getUserUsergroupPage(Page<UserUsergroupPO> page);
	
	/**
	 * 根据用户id查询该用户所属的所有组
	 * @param userId
	 * @return
	 */
	public List<UserUsergroupPO> getByUserId(long userId);

}

package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.service;


import java.util.List;

import com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po.OperateInfoPO;
import com.wonders.bud.framework.common.page.Page;

/**
 * 
 * @author hsw
 *
 */
public interface  OperateInfoService {

	/**
	 * 保存操作信息对象
	 * @param operateInfoPO
	 * @return
	 */
	public OperateInfoPO save(OperateInfoPO operateInfoPO);
	
	/**
	 * 根据id进行查询
	 * @param id
	 * @return
	 */
	public OperateInfoPO get(long id);
	
	/**
	 * 根据条件进行分页查询
	 * @param page
	 * @return
	 */
	public Page<OperateInfoPO> getOperateInfoPage(Page<OperateInfoPO> page);
	
	/**
	 * 根据操作类型和操作类型id查询
	 * @param operateId
	 * @param operateType
	 * @return
	 */
	public List<OperateInfoPO> getByOpTypeAndOpId(long operateId,int operateType);
}

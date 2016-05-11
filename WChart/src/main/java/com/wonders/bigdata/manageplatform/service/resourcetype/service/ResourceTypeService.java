/** 
 * 
 * Copyright (c) 1995-2012 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wonders.bigdata.manageplatform.service.resourcetype.service;

import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.ResourceTypePO;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[资源分类]
 * </p>
 * <p>
 * Description: [资源分类service接口]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月18日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
public interface  ResourceTypeService {

	/**
	 * <p>
	 * Description:[资源分类新增]
	 * </p>
	 * Created by [Heyh] [2015年3月18日] Midified by [修改人] [修改时间]
	 * 
	 * @param po
	 * @return
	 */
	public ResourceTypePO save(ResourceTypePO po);

	/**
	 * <p>
	 * Description:[根据id删除资源分类]
	 * </p>
	 * Created by [Heyh] [2015年3月18日] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 */
	public void deleteById(long id);

	/**
	 * <p>
	 * Description:[资源分类分页查询]
	 * </p>
	 * Created by [Heyh] [2015年3月18日] Midified by [修改人] [修改时间]
	 * 
	 * @param po
	 * @return
	 */
	public Page<ResourceTypePO> findByPage(Page<ResourceTypePO> page);

	/**
	 * <p>
	 * Description:[资源分类列表查询]
	 * </p>
	 * Created by [Heyh] [2015年3月18日] Midified by [修改人] [修改时间]
	 * 
	 * @param po
	 * @return
	 */
	public List<ResourceTypePO> findResourceByList(QueryParam param);

	/**
	 * <p>
	 * Description:[根据id获取资源详情]
	 * </p>
	 * Created by [Heyh] [2015年3月18日] Midified by [修改人] [修改时间]
	 * 
	 * @param po
	 * @return
	 */
	public ResourceTypePO findById(Long id);

	/**
	 * <p>
	 * Description:[判断资源分类是否被引用]
	 * </p>
	 * Created by [WF] [2015年3月19日] Midified by [修改人] [修改时间]
	 * 
	 * @param Long id(资源分类ID)
	 * @return Boolean true为被引用，false为不被引用
	 */
	public Boolean isResourceTypeUsed(String id);


	/**
	 * <p>
	 * Description:[获取所有的资源分类]
	 * </p>
	 * Created by [CSJ] [2015年3月24日] Midified by [修改人] [修改时间]
	 * 
	 * @param
	 * @return List<ResourceTypePO>
	 */
	public List<ResourceTypePO> getAllResourceType();

	/**
	 * <p>
	 * Description:[根据id获取资源分类名称]
	 * </p>
	 * Created by [Heyh] [2015年4月22日] Midified by [修改人] [修改时间]
	 * 
	 * @param id
	 * @return
	 */
	public ResourceTypePO findNameById(long id);	
}

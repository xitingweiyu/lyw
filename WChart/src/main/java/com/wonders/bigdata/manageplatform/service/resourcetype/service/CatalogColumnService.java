package com.wonders.bigdata.manageplatform.service.resourcetype.service;

import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogColumnPO;
import com.wonders.bud.framework.common.util.QueryParam;

import java.util.List;

/**
 * 数据目录关联表服务层接口定义
 * @author xh 2015-10-28 10:18:27
 *
 */
public interface CatalogColumnService {

	/**
	 * 新增或更新数据目录字段关联表对象
	 * @author xh 2015-10-28 10:18:38
	 * @param catalogColumnPO 数据目录字段关联表对象
	 */
	public void saveOrUpdate(CatalogColumnPO catalogColumnPO);
	
	/**
	 * 删除数据目录关联表对象
	 * @author xh 2015-10-28 10:18:50
	 * @param catalogColumnPO 数据目录关联表对象
	 */
	public void delete(CatalogColumnPO catalogColumnPO);
	
	/**
	 * 删除数据目录关联表对象
	 * @author xh 2015-10-28 10:18:50
	 */
	public void delete(long id);
	
	/**
	 * 根据数据目录表id查询所有关联字段对象（包括开放与不开放）
	 * @author xh 2015-10-28 10:20:25
	 * @param catalogId 目录表中的id
	 * @return List<CatalogColumnPO>
	 */
	public List<CatalogColumnPO> findAllByCatalogTableId(long catalogTableId);
	
	/**
	 * 根据数据目录表id查询所有开放的且通过审核的关联字段对象
	 * @author xh 2015-10-28 10:22:25
	 * @param catalogTableId
	 * @return List<CatalogColumnPO>
	 */
	public List<CatalogColumnPO> findOpenByCatalogTableId(long catalogTableId);
	
	/**
	 * 根据条件查询
	 * @param param 条件参数，QueryParam对象
	 * @return List<CatalogColumnPO>
	 */
	public List<CatalogColumnPO> findByParam(QueryParam param);
	
	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
	public CatalogColumnPO getById(long id);

	/**
	 * Created by LXL on 2016/3/4
	 * <br> 将期望的PO信息存入apply_context字段中
	 * @param catalogColumnPO 挂接表字段PO
	 */
	String getApplyContextFromPO(CatalogColumnPO catalogColumnPO);

	/**
	 * Created by LXL on 2016/3/4
	 * <br> 根据id解析apply_context字段以获取期望的PO
	 * @param id 挂接表字段id
	 * @return 期望的PO信息
	 */
	CatalogColumnPO getPOFromApplyContext(Long id);

	/**
	 * Created by LXL on 2016/3/4
	 * <br> 根据id查询PO
	 * @param id
	 * @return
     */
	CatalogColumnPO findById(long id);
}

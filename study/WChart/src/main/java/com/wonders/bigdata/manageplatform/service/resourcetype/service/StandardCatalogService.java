package com.wonders.bigdata.manageplatform.service.resourcetype.service;


import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.StandardCatalogPO;
import com.wonders.bud.framework.common.page.Page;

/**
 *
 * @author renkai
 *
 */
public interface StandardCatalogService {

	/**
	 * <p>
	 * Description:[标准目录新增]
	 * </p>
	 * @param po
	 * @return
	 *
	 */
	public StandardCatalogPO save(StandardCatalogPO po);

	/**
	 * <p>
	 * Description:[标准目录更新]
	 * </p>
	 * @param po
	 * @return
	 */
	public StandardCatalogPO update(StandardCatalogPO po);

	/**
	 * <p>
	 * Description:[根据id删除标准目录]
	 * </p>
	 *
	 * @param id
	 */
	public void deleteById(long id);

	/**
	 * <p>
	 * Description:[根据id获取标准目录]
	 * </p>
	 *
	 * @param po
	 * @return
	 */
	public StandardCatalogPO findById(Long id);

	/**
	 * <p>
	 * Description:[查询所有根目录节点]
	 * </p>
	 *
	 * @param po
	 * @return
	 */
	List<StandardCatalogPO> findAllRootNodes();

	/**
	 * <p>
	 * Description:[查询所有子节点]
	 * </p>
	 *
	 * @param po
	 * @return
	 */
	List<StandardCatalogPO> findChilderes(Long id);

	/**
	 * <p>
	 * Description:[查询某个节点下的所有叶节点]
	 * </p>
	 *
	 * @param po
	 * @return
	 */
	List<StandardCatalogPO> findAllLeafNodes(Long id);

	/**
	 * 获取所有标准目录以及其下的所有表
	 * @return
	 */
	public  List<Map<String, Object>> getAllCatalogTables();

	/**
	 * 非登录页面的属性读取
	 * @return
	 */
	public String getNotLoginCatalogInfo(String catainfo);

	/**
	 * 非登录页面的property文件保存
	 * @return
	 */
	public void SaveNotLoginCatalogInfo(Properties propertie,String description);


	/**
	 * 通过根节点翻页查询
	 * @param rootId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public  Page<StandardCatalogPO> findPageByRootId(long rootId,int pageNum,int pageSize);

}

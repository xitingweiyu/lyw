package com.wonders.bigdata.manageplatform.service.userdatacatalog.service;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogTablePO;
import com.wonders.bud.framework.common.page.Page;

import java.util.List;

/**
 * Created by XCL on 2016/3/10.
 */
public interface UserDataCatalogTableService {
    /**
     * 新增
     * @param userDataCatalogTablePO
     */
    public UserDataCatalogTablePO Save(UserDataCatalogTablePO userDataCatalogTablePO);

    /**
     * 更新
     * @param userDataCatalogTablePO
     */
    public UserDataCatalogTablePO update(UserDataCatalogTablePO userDataCatalogTablePO);

    /**
     * 根据id进行精确查询
      * @param id
     * @return UserDataCatalogTablePO
     */
    public UserDataCatalogTablePO findById(long id);

    /**
     *根据name在某用户下进行模糊查询
     * @param name
     * @param userId
     * @return List< UserDataCatalogTablePO>
     */
    public List< UserDataCatalogTablePO> findByName(String name, long userId);

    /**
     *根据remark在某用户下进行模糊查询
     * @param name
     * @param userId
     * @return List< UserDataCatalogTablePO>
     */
    public List< UserDataCatalogTablePO> findByRemark(String remark, long userId);

    /**
     * 通过catalogId查找对应表
     * @author XH 2016-3-14 14:47:40
     * @param catalogId 目录ID
     * @return List
     */
    public List<UserDataCatalogTablePO> findByUserCatalogId(Long catalogId);

    /**
     * 分页查找用户等级
     * @return
     */
    public Page<UserDataCatalogTablePO> getUserCatalogPage(Page<UserDataCatalogTablePO> page);

	public UserDataCatalogTablePO findUniqeByName(String tableName);

    /**
     * Created by LXL on 2016/3/30
     * <br> 查找DpID相同的预处理表
     * @param dpId 分析id
     * @return 表list
     */
	public List<UserDataCatalogTablePO> findAllPreByDpId(Long dpId);
    
    /**
     * Created by XB on 2016/4/15
     * <br> 根据表名查找关联记录
     * @param tableName
     * @return 表list
     */
	public List<UserDataCatalogTablePO> findAllTablesByTableName(String tableName);

}

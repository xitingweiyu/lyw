package com.wonders.bigdata.manageplatform.service.userdatacatalog.service;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogPO;

import java.util.List;

/**
 * Created by wyp on 2016/3/10.
 */
public interface UserDataCatalogService {
    /**
     * @param userDataCatalogPO
     * @return
     * @description  新增一个用户资产数据目录对象
     * @Created by [WYP] [2016-03-11]
     */
    public UserDataCatalogPO save(UserDataCatalogPO userDataCatalogPO);


    /**
     * @param userDataCatalogPO
     * @return
     * @description  更新一个用户资产数据目录对象
     * @Created by [WYP] [2016-03-11]
     */
    public UserDataCatalogPO update(UserDataCatalogPO userDataCatalogPO);

    /**
     * @param userId
     * @return  list< UserDataCatalogPO>
     * @description  根据用户id查找所有根节点
     * @Created by [WYP] [2016-03-11]
     */
    public List<UserDataCatalogPO> findRootNodesByUserId(long userId);

    /**
     * @param parentId
     * @return  list< UserDataCatalogPO>
     * @description  根据租户资产目录查询所有一级子节点
     * @Created by [WYP] [2016-03-11]
     */
    public List<UserDataCatalogPO> findFirstChildrenNodesByParentId(long parentId, long userId);

    /**
     * @param parentId
     * @return  list< UserDataCatalogPO>
     * @description  根据parentId查询出所有子节点，包括子节点的子节点…
     * @Created by [WYP] [2016-03-11]
     */
    public List<UserDataCatalogPO> findChildrenNodesByParentId(long parentId, long userId);

    /**
     * @param id
     * @return   UserDataCatalogPO
     * @description  根据Id查询
     * @Created by [WYP] [2016-03-11]
     */
    public UserDataCatalogPO findNodeById(long id);


    /**
     * @param userId , name
     * @return   list< UserDataCatalogPO>
     * @description  根据名称模糊查询某用户下的所有对象
     * @Created by [WYP] [2016-03-11]
     */
    public List<UserDataCatalogPO> findNodesByName(long userId, String name);

    UserDataCatalogPO findById(long id);
}

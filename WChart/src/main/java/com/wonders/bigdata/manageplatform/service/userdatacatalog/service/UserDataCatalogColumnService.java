package com.wonders.bigdata.manageplatform.service.userdatacatalog.service;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogColumnPO;

import java.util.List;

/**
 * Created by XCL on 2016/3/10.
 */
public interface UserDataCatalogColumnService {
    /**
     * 新增
     * @param userDataCatalogColumnPO
     */
    public UserDataCatalogColumnPO save(UserDataCatalogColumnPO userDataCatalogColumnPO);


    /**
     * 更新
     * @param userDataCatalogColumnPO
     */
    public UserDataCatalogColumnPO update(UserDataCatalogColumnPO userDataCatalogColumnPO);


    /**
     * 根据id进行精确查询
     * @param id
     */
    public UserDataCatalogColumnPO findById(long id);

    /**
     * 根据userCatalogtableId查询
     * @param userCatalogTableId
     * @return
     */
    public List< UserDataCatalogColumnPO> findByUserCatalogTableId(long userCatalogTableId);



}

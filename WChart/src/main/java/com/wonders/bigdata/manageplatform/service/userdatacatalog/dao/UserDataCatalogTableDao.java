package com.wonders.bigdata.manageplatform.service.userdatacatalog.dao;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogTablePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDao;

import java.util.List;

/**
 * Created by XCL on 2016/3/10.
 */
public interface UserDataCatalogTableDao extends HibernateBaseDao<UserDataCatalogTablePO,Long> {
    /**
     * 根据自己所写hql语句得到结果
     * @param variable
     * @param userId
     * @return
     */
    public List<UserDataCatalogTablePO> findByHQL(String type, String variable, long userId);

    /**
     * 通过catalogId查找对应表
     * @author XH 2016-3-14 14:48:36
     * @param catalogId 目录ID
     * @return List
     */
    List<UserDataCatalogTablePO> findByUserCatalogId(Long catalogId);

}

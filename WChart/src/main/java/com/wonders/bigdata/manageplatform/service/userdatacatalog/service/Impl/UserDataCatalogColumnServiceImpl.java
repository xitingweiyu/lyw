package com.wonders.bigdata.manageplatform.service.userdatacatalog.service.Impl;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.dao.UserDataCatalogColumnDao;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogColumnPO;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.service.UserDataCatalogColumnService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XCL on 2016/3/10.
 */
@Service("userDataCatalogColumnServiceImpl")
public class UserDataCatalogColumnServiceImpl implements UserDataCatalogColumnService {


    @Autowired
    private UserDataCatalogColumnDao userDataCatalogColumnDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDataCatalogColumnPO save(UserDataCatalogColumnPO userDataCatalogColumnPO) {
        if(userDataCatalogColumnPO.getDeleteFlag() == null || userDataCatalogColumnPO.getCreateDate() == null){
            if(userDataCatalogColumnPO.getDeleteFlag() == null)
                userDataCatalogColumnPO.setDeleteFlag(0);//补全删除位
            if(userDataCatalogColumnPO.getCreateDate() == null)
                userDataCatalogColumnPO.setCreateDate(new Date());//补全时间
        }
        return userDataCatalogColumnDao.save(userDataCatalogColumnPO);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDataCatalogColumnPO update(UserDataCatalogColumnPO userDataCatalogColumnPO) {
           return userDataCatalogColumnDao.update(userDataCatalogColumnPO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDataCatalogColumnPO findById(long id) {
        return userDataCatalogColumnDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserDataCatalogColumnPO> findByUserCatalogTableId(long userCatalogTableId) {
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("userdatacatalogtableId", userCatalogTableId);
        eq.put("deleteFlag", Constant.USERDATACATALOG_TABLE_NOT_DELETE);
        param.setEq(eq);
        List<UserDataCatalogColumnPO> tmp = userDataCatalogColumnDao.findByAnd(param);
        return tmp;
    }

}

package com.wonders.bigdata.manageplatform.service.userdatacatalog.service.Impl;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.dao.UserDataCatalogDao;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogPO;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.service.UserDataCatalogService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/11.
 */
@Service("userDataCatalogServiceImpl")
public class UserDataCatalogServiceImpl implements UserDataCatalogService {

    @Resource(name = "userDataCatalogDaoImpl")
    private UserDataCatalogDao userDataCatalogDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDataCatalogPO save(UserDataCatalogPO userDataCatalogPO) {
        return userDataCatalogDao.save(userDataCatalogPO);
    }

    @Override
    public UserDataCatalogPO update(UserDataCatalogPO userDataCatalogPO) {
        return userDataCatalogDao.save(userDataCatalogPO);
    }

    @Override
    public List<UserDataCatalogPO> findRootNodesByUserId(long userId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("parentId", null);
        paramMap.put("deleteFlag", Constant.USERDATACATALOG_NOT_DELETE);
        return userDataCatalogDao.findBy(paramMap);
    }

    @Override
    public List<UserDataCatalogPO> findFirstChildrenNodesByParentId(long parentId, long userId) {
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<>();
        eq.put("parentId", parentId);
        eq.put("userId", userId);
        eq.put("deleteFlag", Constant.USERDATACATALOG_NOT_DELETE);
        param.setEq(eq);
        return userDataCatalogDao.findByAnd(param);
    }

    @Override
    public List<UserDataCatalogPO> findChildrenNodesByParentId(long parentId, long userId) {
        List<UserDataCatalogPO> list = new ArrayList<>();
        List<UserDataCatalogPO> resultList = new ArrayList<>();
        list = findFirstChildrenNodesByParentId(parentId,userId);
        //递归查询所有子节点
        for(UserDataCatalogPO udcpo : list){
            resultList.add(udcpo);
            List<UserDataCatalogPO> tempList = findFirstChildrenNodesByParentId(udcpo.getId(),userId);
            if(tempList.size() > 0) findChildrenNodesByParentId(udcpo.getId(),userId);
            else   continue;
        }

        return resultList;
    }



    @Override
    public UserDataCatalogPO findNodeById(long id) {
        return userDataCatalogDao.findUniqueByProperty("id",id);
    }

    @Override
    public List<UserDataCatalogPO> findNodesByName(long userId, String name) {
        QueryParam query = new QueryParam();
        Map<String , Object> map = new HashMap<>();
        map.put("userId",userId);
        Map<String,Object> likemap = new HashMap<>();
        likemap.put("name",name);
        map.put("deleteFlag", Constant.USERDATACATALOG_NOT_DELETE);
        query.setEq(map);
        query.setLike(likemap);
        return userDataCatalogDao.findByAnd(query);
    }

    @Override
    public UserDataCatalogPO findById(long id){
        return userDataCatalogDao.get(id);
    }
}

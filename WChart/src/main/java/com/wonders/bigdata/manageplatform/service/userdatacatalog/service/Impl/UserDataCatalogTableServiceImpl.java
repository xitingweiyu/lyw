package com.wonders.bigdata.manageplatform.service.userdatacatalog.service.Impl;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.dao.UserDataCatalogTableDao;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogTablePO;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.service.UserDataCatalogTableService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.page.Page;
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
 * Created by XCL on 2016/3/11.
 */
@Service("userDataCatalogTableServiceImpl")
public class UserDataCatalogTableServiceImpl implements UserDataCatalogTableService{

    @Autowired
    private UserDataCatalogTableDao userDataCatalogTableDao;
    /**
     * 新增
     *
     * @param userDataCatalogTablePO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDataCatalogTablePO Save(UserDataCatalogTablePO userDataCatalogTablePO) {
        if(userDataCatalogTablePO.getDeleteFlag() == null || userDataCatalogTablePO.getCreateDate() == null){
            if(userDataCatalogTablePO.getDeleteFlag() == null)
                userDataCatalogTablePO.setDeleteFlag(0);//补全删除位
            if(userDataCatalogTablePO.getCreateDate() == null)
                userDataCatalogTablePO.setCreateDate(new Date());//补全时间
        }
        return userDataCatalogTableDao.save(userDataCatalogTablePO);
    }

    /**
     * 更新
     *
     * @param userDataCatalogTablePO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDataCatalogTablePO update(UserDataCatalogTablePO userDataCatalogTablePO) {
           return userDataCatalogTableDao.update(userDataCatalogTablePO);
    }

    /**
     * 根据id进行精确查询
     *
     * @param id
     * @return UserDataCatalogTablePO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDataCatalogTablePO findById(long id) {
        return userDataCatalogTableDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDataCatalogTablePO findUniqeByName(String tableName) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("deleteFlag", Constant.METADATA_NOT_DELETE);
        paramMap.put("tableName", tableName);
        List<UserDataCatalogTablePO> udctPOs = userDataCatalogTableDao.findBy(paramMap);

        UserDataCatalogTablePO udctPO = new UserDataCatalogTablePO();
        if (udctPOs != null && udctPOs.size() > 0) {
        	udctPO = udctPOs.get(0);
            return udctPO;
        } else {
            return null;
        }
    }
    /**
     * 根据name在某用户下进行模糊查询
     *
     * @param name
     * @param userId
     * @return List< UserDataCatalogTablePO>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserDataCatalogTablePO> findByName(String name, long userId) {
        return userDataCatalogTableDao.findByHQL("tableName",name,userId);
    }

    /**
     * 根据remark在某用户下进行模糊查询
     *
     * @param remark
     * @param userId
     * @return List< UserDataCatalogTablePO>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserDataCatalogTablePO> findByRemark(String remark, long userId) {
        return userDataCatalogTableDao.findByHQL("tableRemark",remark,userId);
    }

    @Override
    public List<UserDataCatalogTablePO> findByUserCatalogId(Long catalogId) {
        return userDataCatalogTableDao.findByUserCatalogId(catalogId);
    }

    @Override
    public Page<UserDataCatalogTablePO> getUserCatalogPage(Page<UserDataCatalogTablePO> page){
        return userDataCatalogTableDao.findByPage(page);
    }

    /**
     * Created by LXL on 2016/3/30
     * <br> 查找DpID相同的预处理表
     * @param dpId 分析id
     * @return 表list
     */
    @Override
    public List<UserDataCatalogTablePO> findAllPreByDpId(Long dpId) {
        QueryParam queryParam = new QueryParam();
        Map<String,Object> eq = new HashMap<>();
        eq.put("deleteFlag", Constant.USERDATACATALOG_TABLE_NOT_DELETE);
        eq.put("type", Constant.USERDATACATALOG_TABLE_TYPE_PRE);
        eq.put("dpId",dpId);
        queryParam.setEq(eq);
        return userDataCatalogTableDao.findByAnd(queryParam);
    }
    
    @Override
    public List<UserDataCatalogTablePO> findAllTablesByTableName(String tableName) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("deleteFlag", Constant.METADATA_NOT_DELETE);
        paramMap.put("tableName", tableName);
        List<UserDataCatalogTablePO> udctPOs = userDataCatalogTableDao.findBy(paramMap);
        return udctPOs;
    }
}

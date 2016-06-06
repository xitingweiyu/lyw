package com.wonders.bigdata.manageplatform.service.userdatacatalog.dao.Impl;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.dao.UserDataCatalogTableDao;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogTablePO;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.bud.framework.common.util.QueryParam;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XCL on 2016/3/11.
 */
@Repository("userDataCatalogTableDaoImpl")
public class UserDataCatalogTableDaoImpl extends HibernateBaseDaoImpl<UserDataCatalogTablePO, Long> implements UserDataCatalogTableDao {


    /**
     * 根据自己所写hql语句得到结果
     *
     * @param variable
     * @param userId
     * @return
     */
    @Override
    public List<UserDataCatalogTablePO> findByHQL(String type, String variable, long userId) {
        String hql = "from UserDataCatalogTablePO  where userId = :userId and deleteFlag = :deleteFlag ";
        if(variable != null && !variable.trim().equals("")){
            hql = hql + " and "+ type +" like '%"+variable + "%'";
        }
        Session session = getSession();
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("deleteFlag", Constant.USERDATACATALOG_TABLE_NOT_DELETE);
        List<UserDataCatalogTablePO> result = query.list();
        return result;
    }

    @Override
    public List<UserDataCatalogTablePO> findByUserCatalogId(Long catalogId) {

        QueryParam qp = new QueryParam();
        Map<String,Object> eq = new HashMap<>();
        eq.put("deleteFlag", Constant.USERDATACATALOG_TABLE_NOT_DELETE);
        eq.put("userdatacatalogId", catalogId);
        qp.setEq(eq);
        return findByAnd(qp);
    }
}

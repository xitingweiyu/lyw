package com.wonders.bigdata.manageplatform.service.resourcetype.dao.Impl;
import com.wonders.bigdata.manageplatform.service.resourcetype.dao.CatalogTableDao;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogTableDatapackagePO;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogTablePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 目录表数据包dao接口实现类
 *
 * @author xuehan
 * @date 2015年8月25日 下午10:14:25
 */
@Repository("catalogTableDaoImpl")
public class CatalogTableDaoImpl extends HibernateBaseDaoImpl<CatalogTablePO, Long>
        implements CatalogTableDao {


    @Override
    public List<CatalogTableDatapackagePO> findByIds(long[] ids, int maxResult) {
        return null;
    }
}
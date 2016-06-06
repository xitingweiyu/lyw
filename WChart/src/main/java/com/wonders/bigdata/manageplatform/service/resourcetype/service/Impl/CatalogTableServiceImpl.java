package com.wonders.bigdata.manageplatform.service.resourcetype.service.Impl;

import com.wonders.bigdata.manageplatform.service.resourcetype.dao.CatalogTableDao;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogColumnPO;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.CatalogTablePO;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.CatalogColumnService;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.CatalogTableService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标准目录关联表service接口实现类
 *
 * @author xh 2015-10-28 10:57:31
 */
@Service("catalogTableServiceImpl")
public class CatalogTableServiceImpl implements CatalogTableService {
    private static Logger log = LoggerFactory.getLogger(CatalogTableServiceImpl.class);
    @Autowired
    private CatalogTableDao catalogTableDao;

    @Autowired
    private CatalogColumnService catalogColumnService;




    @Override
    public CatalogTablePO findById(long id) {

        return catalogTableDao.get(id);
    }


    @Override
    public int countByCatalogId(long catalogId) {
        String hql = "from CatalogTablePO where catalogId=? and dataGrade>=" + Constant.METADATA_OPEN
                + " and deleteFlag=" + Constant.METADATA_NOT_DELETE;
        int sum = catalogTableDao.find(hql, catalogId).size();
        return sum;
    }

    @Override
    public List<CatalogTablePO> searchByNameAndCatalog(long catalogId, String name) {

        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", catalogId);
        eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
        param.setEq(eq);
        Map<String, Object> like = new HashMap<String, Object>();
        like.put("remark", name);
        param.setLike(like);
        List<CatalogTablePO> result = catalogTableDao.findByAnd(param);
        return result;

    }

    @Override
    public List<CatalogTablePO> searchByNameAndCatalog(long[] catalogIds, String name, int grade) {

        QueryParam queryParam = new QueryParam();
        // 将long转为Long
        Object[] idsL = new Object[catalogIds.length];
        for (int i = 0; i < catalogIds.length; i++) {
            idsL[i] = catalogIds[i];
        }

        Map<String, Object[]> inMap = new HashMap<String, Object[]>();
        inMap.put("catalogId", idsL);

        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
        eq.put("status", Constant.METADATA_TABLE_STATUS_AUDITED);
        queryParam.setEq(eq);

        Map<String, Object> like = new HashMap<String, Object>();
        like.put("tableName", name);
        queryParam.setLike(like);

        if (grade >= 0) {
            Object[] grades = new Object[grade];
            for (int i = 1; i <= grade; i++) {
                grades[i - 1] = i;
            }
            //inMap.put("dataGrade", grades);
            queryParam.setIn(inMap);
        }
        return catalogTableDao.findByAnd(queryParam);
    }

    @Override
    public List<CatalogTablePO> findByIds(long[] ids) {
        if (ids == null || ids.length <= 0)
            return null;
        QueryParam queryParam = new QueryParam();
        // 将long转为Long
        Object[] idsL = new Object[ids.length];
        for (int i = 0; i < ids.length; i++) {
            idsL[i] = ids[i];
        }

        Map<String, Object[]> inMap = new HashMap<String, Object[]>();
        inMap.put("catalogId", idsL);
        queryParam.setIn(inMap);

        Map<String, Object> eqMap = new HashMap<String, Object>();
        eqMap.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
        eqMap.put("status", Constant.METADATA_TABLE_STATUS_AUDITED);
        eqMap.put("dataGrade", Constant.METADATA_TABLE_NAME_OPEN_LEVEL_OPEN);
        queryParam.setEq(eqMap);

        return catalogTableDao.findByAnd(queryParam);
    }

    @Override
    public List<CatalogTablePO> findByIdsWithGrade(long[] ids, int grade) {
        if (ids == null || ids.length <= 0)
            return null;
        QueryParam queryParam = new QueryParam();
        // 将long转为Long
        Object[] idsL = new Object[ids.length];
        for (int i = 0; i < ids.length; i++) {
            idsL[i] = ids[i];
        }
        /*Object[] grades = new Object[grade];
        for (int i = 1; i <= grade; i++) {
            grades[i - 1] = i;
        }*/

        Map<String, Object[]> inMap = new HashMap<String, Object[]>();
        inMap.put("catalogId", idsL);
        // inMap.put("dataGrade", grades);
        queryParam.setIn(inMap);

        Map<String, Object> eqMap = new HashMap<String, Object>();
        eqMap.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
        eqMap.put("status", Constant.METADATA_TABLE_STATUS_AUDITED);
        queryParam.setEq(eqMap);

        return catalogTableDao.findByAnd(queryParam);
    }


    @Override
    public List<CatalogTablePO> findByIds(long[] ids, int limit, int grade) {

        return null;
    }

    @Override
    public List<CatalogTablePO> findCatalog(Long standardId, Long metaTableId) {
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", standardId);
        eq.put("tableId", metaTableId);
        eq.put("type", Constant.CATALOG_TYPE_TABLE);
        param.setEq(eq);
        return catalogTableDao.findByAnd(param);
    }


    @Override
    public List<CatalogTablePO> findByTablePackageId(long id) {

        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("tableId", id);
        //eq.put("type", Constant.CATALOG_TYPE_PACKAGE);
        eq.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
        param.setEq(eq);
        return catalogTableDao.findByAnd(param);
    }

    @Override
    public List<CatalogTablePO> findTablesByCatalogId(long[] ids) {
        if (ids == null || ids.length <= 0)
            return null;
        QueryParam queryParam = new QueryParam();
        // 将long转为Long
        Object[] idsL = new Object[ids.length];
        for (int i = 0; i < ids.length; i++) {
            idsL[i] = ids[i];
        }

        Map<String, Object[]> inMap = new HashMap<String, Object[]>();
        inMap.put("catalogId", idsL);
        queryParam.setIn(inMap);

        Map<String, Object> eqMap = new HashMap<String, Object>();
        eqMap.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
        //eqMap.put("type", Constant.CATALOG_TYPE_TABLE);
        //eqMap.put("dataGrade", Constant.CATALOG_OPEN);
        queryParam.setEq(eqMap);

        return catalogTableDao.findByAnd(queryParam);
    }

    @Override
    public List<CatalogTablePO> getTablesByCatalogId(Long standardCatalogId) {
        List<CatalogTablePO> pos = new ArrayList<>();
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", standardCatalogId);
        eq.put("type", Constant.CATALOG_TYPE_TABLE);
        eq.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
        eq.put("dataGrade", Constant.METADATA_TABLE_NAME_OPEN_LEVEL_OPEN);

        param.setEq(eq);
        pos = catalogTableDao.findByAnd(param);
        return pos;
    }

    @Override
    public List<CatalogTablePO> getTablesByTableId(Long tablePackageId) {
        log.info("根据表id查找所有的关联");
        List<CatalogTablePO> pos = new ArrayList<>();
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("tableId", tablePackageId);
        eq.put("type", Constant.CATALOG_TYPE_TABLE);
        eq.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
        param.setEq(eq);
        pos = catalogTableDao.findByAnd(param);
        return pos;
    }

    @Override
    public CatalogTablePO getTablesByCatalogIdAndTableId(Long catalogId, Long tablePackageId) {
        List<CatalogTablePO> pos = new ArrayList<>();
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", catalogId);
        eq.put("tableId", tablePackageId);
        eq.put("type", Constant.CATALOG_TYPE_TABLE);
        eq.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
        param.setEq(eq);
        pos = catalogTableDao.findByAnd(param);

        return pos.size() > 0 ? pos.get(0) : null;
    }

    @Override
    public List<CatalogTablePO> getByTableOrPackageName(String name) {

        List<CatalogTablePO> pos = new ArrayList<>();
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("deleteFlag", Constant.CATALOG_NOT_DELETE);
        eq.put("tableName", name);
        param.setEq(eq);
        pos = catalogTableDao.findByAnd(param);
        return pos;
    }

    @Override
    public Page<CatalogTablePO> findPageByRootId(Long[] catalogIds, int pageNum, int pageSize) {
        return null;
    }


    /**
     * @author xh 2015-10-28 11:00:58
     */
    @Override
    public CatalogTablePO findByCatalogIdAndTableId(long catalogId, long tableId) {
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", catalogId);
        eq.put("tableId", tableId);
        eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
        param.setEq(eq);
        List<CatalogTablePO> tmp = catalogTableDao.findByAnd(param);
        CatalogTablePO catalogTablePO = null;
        if (tmp.size() > 0) {
            catalogTablePO = tmp.get(0);
        }
        return catalogTablePO;
    }

    /**
     * @author xh 2015-10-28 11:01:11
     */
    @Override
    public List<CatalogTablePO> findAllByCatalogId(long catalogId) {
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", catalogId);
        eq.put("deleteFlag", Constant.DATA_DATAPACKAGE_NOT_DELETE);
        param.setEq(eq);
        List<CatalogTablePO> tmp1 = catalogTableDao.findByAnd(param);
        // 查询已经删除但处于审核中的挂接表
        eq.put("catalogId", catalogId);
        eq.put("deleteFlag", Constant.DATA_DATAPACKAGE_DELETE);
        eq.put("status", Constant.CATALOG_STATUS_SUBMIT);
        param.setEq(eq);
        List<CatalogTablePO> tmp2 = catalogTableDao.findByAnd(param);
        tmp1.addAll(tmp2);
        return tmp1;
    }

    /**
     * @author xh 2015-10-28 11:01:22
     */
    @Override
    public List<CatalogTablePO> findNotOpenByCatalogId(long catalogId) {
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", catalogId);
        eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
        eq.put("dataGrade", Constant.RESOURCE_NOT_OPEN);
        param.setEq(eq);
        List<CatalogTablePO> tmp = catalogTableDao.findByAnd(param);
        return tmp;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CatalogTablePO saveApproveCatalogTable(CatalogTablePO catalogTable, List<CatalogColumnPO> cols) {

        try {
            for (int i = 0; i < cols.size(); i++) {
                CatalogColumnPO col = cols.get(i);
                catalogColumnService.saveOrUpdate(col);
            }
            return catalogTableDao.update(catalogTable);
        } catch (Exception e) {
            log.error("审批表", e);
            return null;
        }

    }

    @Override
    public List<CatalogTablePO> findAllSubmitTablesByCatalogId(long catalogId) {

        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", catalogId);
        eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
        //eq.put("type", Constant.CATALOG_TYPE_TABLE);
        eq.put("status", Constant.CATALOG_TABLE_STATUS_SUBMIT);
        param.setEq(eq);
        List<CatalogTablePO> tmp = catalogTableDao.findByAnd(param);
        return tmp;
    }

    @Override
    public List<CatalogTablePO> findAllcheckedTablesByCatalogId(long catalogId) {
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", catalogId);
        eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
        eq.put("status", Constant.CATALOG_TABLE_STATUS_SUBMIT_PASS);
        param.setEq(eq);
        List<CatalogTablePO> tmp = catalogTableDao.findByAnd(param);
        return tmp;
    }


    /**
     * 根据CatalogId查找该目录下所有表的最高等级
     *
     * @param catalogId 开发目录树的id
     * @return 该目录下表的最高等级
     * @author WZB
     * @date 2016-01-27
     */
    public int findCatalogTableTopGrade(long catalogId) {
        int dataGrade = -1;
        long[] catalogIds = {catalogId};

        List<CatalogTablePO> catalogTablePos = findByIds(catalogIds);

        if (catalogTablePos != null) {
            for (int i = 0; i < catalogTablePos.size(); i++) {
                if (catalogTablePos.get(i).getDataGrade() > dataGrade) {
                    dataGrade = catalogTablePos.get(i).getDataGrade();
                }
            }
        }

        return dataGrade;
    }

    @Override
    public CatalogTablePO findByCatalogIdAndTableName(long catalogId, String tableName) {
        Map<String, Object> eq = new HashMap<>();
        QueryParam query = new QueryParam();
        eq.put("catalogId", catalogId);
        eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);
        eq.put("tableName", tableName);
        query.setEq(eq);
        List<CatalogTablePO> catalogTablePOs = catalogTableDao.findByAnd(query);
        if (catalogTablePOs.size() == 0) {
            return null;
        } else {
            return catalogTablePOs.get(0);
        }
    }

    @Override
    public List<CatalogTablePO> findByParam(QueryParam param) {
        List<CatalogTablePO> list = catalogTableDao.findByAnd(param);
        return list;
    }



    @Override
    public List<CatalogTablePO> findCatalogTablesBySql(int limit) {
        List<CatalogTablePO> list = new ArrayList<>();
        String sql = "select * from bd_catalog_table where delete_flag =0 and status =2 order by create_date desc limit " + limit + ";";
        //list = catalogTableDao.findBySQL(sql);
        return null;
    }

    /**
     * Created by LXL on 2016/4/20
     * <br> 根据SQL语句查询关联表
     *
     * @param sql sql语句
     * @return 表PO信息
     */
    @Override
    public List<CatalogTablePO> findCatalogTablesBySql(String sql) {
        List<CatalogTablePO> list = new ArrayList<>();
        //list = catalogTableDao.findBySQL(sql);
        return null;
    }

    /**
     * 根据CatalogId查找该目录下开发且审批通过的表(未删除的表)(包括申请包创建时判断数据等级来搜索符合条件的表)
     *
     * @param catalogId 开发目录树的id
     * @return 该目录下开发且审批通过的表(未删除的表)
     * @author xh
     * @date 2015-11-19 16:28:54
     * @see XB 修改 2016-1-14 17:07:28 增加数据等级判断
     */
    @Override
    public List<CatalogTablePO> findOpenTableAndAduitPassByCatalogid(long catalogId) {

        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        eq.put("catalogId", catalogId);
        eq.put("deleteFlag", Constant.RESOURCE_UN_DELETE);

        Map<String, Object> ne = new HashMap<>();
        ne.put("dataGrade", Constant.CATALOG_TABLE_OPEN_LEVEL_NOT_OPEN);
        param.setNe(ne);
        param.setEq(eq);
        return catalogTableDao.findByAnd(param);
    }

}

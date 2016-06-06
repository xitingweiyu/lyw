package com.wonders.bigdata.manageplatform.service.catalog.impl;

import com.wonders.bigdata.manageplatform.service.catalog.CatalogTableService2;
import com.wonders.bigdata.manageplatform.service.catalog.model.po.CatalogTablePO;
import com.wonders.bigdata.manageplatform.service.common.service.JDBCService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xh on 2016/4/28.
 */
@Service("catalogTableServiceImpl2")
public class CatalogTableServiceImpl2 implements CatalogTableService2 {

    @Autowired
    private JDBCService jdbcService;


    @Override
    public List<CatalogTablePO> findOpenTableAndAduitPassByCatalogid(long catalogId) {

        List<CatalogTablePO> result = new LinkedList<>();

        String sql = "select * from bd_catalog_table where catalog_id = " + catalogId +
                " and delete_flag = " + Constant.RESOURCE_UN_DELETE + " and data_grade <> " +
                Constant.CATALOG_TABLE_OPEN_LEVEL_NOT_OPEN;

        List<Map<String, Object>> list = jdbcService.query(sql);

        for (Map<String, Object> map : list) {

            CatalogTablePO catalogTable = new CatalogTablePO();
            catalogTable.setId(Long.parseLong(map.get("id").toString()));
            catalogTable.setCatalogId(Long.parseLong(map.get("catalog_id").toString()));
            catalogTable.setTableId(Long.parseLong(map.get("table_id").toString()));
            catalogTable.setTableName(map.get("table_name").toString());
            catalogTable.setRemark(map.get("remark").toString());
            result.add(catalogTable);

        }
        return result;


    }
}

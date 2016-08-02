package com.wonders.bigdata.manageplatform.service.catalog.impl;

import com.wonders.bigdata.manageplatform.service.catalog.CatalogService;
import com.wonders.bigdata.manageplatform.service.catalog.model.po.CatalogPO;
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
@Service("catalogServiceImpl")
public class CatalogServiceImpl implements CatalogService{


    @Autowired
    private JDBCService jdbcService;


    @Override
    public List<CatalogPO> findAllRootNodesWithStatus(long id) {


        String sql = "select * from bd_standard_catalog where parent_id = " + id + " and delete_flag = "
                + Constant.CATALOG_NOT_DELETE + " and open_level = " + Constant.CATALOG_OPEN;

        List<Map<String, Object>> list = jdbcService.query(sql);
        List<CatalogPO> result = new LinkedList<>();

        for(Map<String, Object> map : list) {
            CatalogPO catalogPO = new CatalogPO();
            catalogPO.setId(Long.parseLong(map.get("id").toString()));
            catalogPO.setParentId(Long.parseLong(map.get("parent_id").toString()));
            catalogPO.setName(map.get("name").toString());
            catalogPO.setType(map.get("type").toString());
            result.add(catalogPO);
        }
        return result;
    }
}

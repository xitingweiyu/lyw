package com.wonders.bigdata.manageplatform.web.dataquality.controller;

import com.wonders.bigdata.manageplatform.service.catalog.CatalogService;
import com.wonders.bigdata.manageplatform.service.catalog.CatalogTableService2;
import com.wonders.bigdata.manageplatform.service.catalog.model.po.CatalogPO;
import com.wonders.bigdata.manageplatform.service.catalog.model.po.CatalogTablePO;
import com.wonders.bigdata.manageplatform.service.common.service.HiveService;
import com.wonders.bigdata.manageplatform.service.task.model.po.BdIndexPO;
import com.wonders.bigdata.manageplatform.service.task.service.BdIndexService;
import com.wonders.bigdata.manageplatform.utils.Messages;
import com.wonders.bigdata.manageplatform.web.dataquality.vo.DataQualityVO;
import com.wonders.bud.framework.common.util.RestMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据质量查看Controller
 * Created by xh on 2016/4/25.
 */
@Controller
@RequestMapping("api/dataquality/result")
public class DataQualityResultController {

    protected Logger log = Logger.getLogger(DataQualityResultController.class);

    @Autowired
    private CatalogService catalogService;

    @Autowired
    CatalogTableService2 catalogTableService2;

    @Autowired
    private BdIndexService bdIndexService;

    @Autowired
    private HiveService hiveService;


    @RequestMapping(value = "/indexAll", method = RequestMethod.GET)
    @ResponseBody
    public RestMsg<List<BdIndexPO>> getAllIndex() {

        RestMsg<List<BdIndexPO>> restMsg = new RestMsg<>();

        try {
            List<BdIndexPO> result = bdIndexService.getAll();
            restMsg.setResult(result);
            restMsg.successMsg();
        } catch (Exception e) {
            e.printStackTrace();
            restMsg.errorMsg();
        }
        return restMsg;

    }

    @RequestMapping(value = "/getByTableName", method = RequestMethod.POST)
    @ResponseBody
    public RestMsg<List<DataQualityVO>> getByTableName(String tableName) {

        RestMsg<List<DataQualityVO>> restMsg = new RestMsg<>();
        try {

            List<String> columns = getColumnByTableName(tableName);
            List<DataQualityVO> result = bdIndexService.getByTableName(tableName, columns);
            restMsg.setResult(result);
            restMsg.successMsg();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            restMsg.setCode(-1);
        }catch (Exception e) {
            e.printStackTrace();
            restMsg.setCode(-2);
        }
        return restMsg;
    }

    /**
     * 根据表名到hive中获取所有字段名
     * @param tableName 表名
     * @return List
     * @throws IllegalArgumentException 表不存在时抛出该异常
     * @throws SQLException 发生hive错误，抛出该异常
     */
    private List<String> getColumnByTableName(String tableName) throws IllegalArgumentException, SQLException {

        List<String> result = new ArrayList<>();
        boolean tableExist = hiveService.existTable(tableName);
        if(!tableExist) {
            throw new IllegalArgumentException("表不存在");
        }

        List<Map<String, String>> columns = hiveService.getHiveColunmsByTableName(Messages.getString("jdbcHiveUrl"),
                Messages.getString("hiveUser"),
                Messages.getString("hivePwd"),
                tableName);


        /**
         * 获取所有字段
         */
        for(Map<String, String> map : columns) {
            result.add(map.get("column").toString());
        }

        return result;
    }


    /**
     * 进入申请包创建页面时，将请求此方法，异步加载树并过滤数据等级，获取组成前台页面ztree控件中需要的数据
     *
     * @param request
     * @return 构成申请包页面Ztree的数据
     * @author XB
     * @date 创建 2016-1-29 09:56:36
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStandardCatalogTreeRootDatePripackage", method = RequestMethod.GET)
    @ResponseBody
    public RestMsg<List<Map<String, Object>>> getStandardCatalogTreeRootDatePripackage(HttpServletRequest request) {
        RestMsg<List<Map<String, Object>>> rm = new RestMsg<List<Map<String, Object>>>();

        String idStr = request.getParameter("id");// 从创建数据包页面传过来的ztree节点id


        long id = 0;// 存储最终需要查找的节点id

        List<Map<String, Object>> result = new ArrayList<>();
        try {
            if (!StringUtils.isEmpty(idStr)) {// 如果页面传过来了id，说明需要加载指定节点下的节点
                if (idStr.contains("P")) {
                    idStr = idStr.split("P")[0];// 将前台页面传过来的ID:P格式的数据分离出ID
                }
                id = Long.parseLong(idStr);// 将id赋值页面传过来的值
            }

            // 获取指定开放且通过审核的节点(只是一层节点，不包括该节点下的子节点)
            List<CatalogPO> allOpenWithAduitPassNodes = catalogService.findAllRootNodesWithStatus(id);

            // 循环组织构成Ztree的数据json数据
            for (CatalogPO standardCatalogPO : allOpenWithAduitPassNodes) {
                Map<String, Object> catalogItem = new HashMap<>();// 存储构成ztree的每一个节点信息(非表)
                catalogItem.put("id", standardCatalogPO.getId() + "P");// 构成ztree需要id值,此处将父节点id加上一个:P是为了防止当
                // 目录表的id和表的id相同时，将会出现{id=2,
                // pId=2
                // name="XX"}情况
                catalogItem.put("pId", id + "P");// 构成ztree需要pId值,标示该节点的父节点是哪一个
                catalogItem.put("name", standardCatalogPO.getName());//// 构成ztree需要name值，用于前台ztree显示
                catalogItem.put("drag", false);// 自定义属性，标示该节点是否可以拖拽,false标示不可拖拽
                // 获取该节点下的孩子节点
                List<CatalogPO> children = catalogService.findAllRootNodesWithStatus(standardCatalogPO.getId());
                // 该节点下所有开放并且审批通过的表
                List<CatalogTablePO> openAndAduitPassTable = catalogTableService2
                        .findOpenTableAndAduitPassByCatalogid(standardCatalogPO.getId());

                boolean isParent = false;
                // 通过是否有孩子或者是否有表判断该节点是否是父节点
                if (children.size() > 0 || openAndAduitPassTable.size() > 0) {
                    isParent = true;// 是父节点则设置isParent属性为true。
                }

                catalogItem.put("isParent", isParent);// 标示该节点是否是个父节点，前台展示需要
                result.add(catalogItem);
            }

            // 获取指定节点下的所有表
            List<CatalogTablePO> openAndAduitPassTable = catalogTableService2
                    .findOpenTableAndAduitPassByCatalogid(id);

            for (CatalogTablePO catalogTablePO : openAndAduitPassTable) {
                Map<String, Object> tableItem = new HashMap<>();// 存储构成ztree的每一个节点信息(表)
                tableItem.put("id", catalogTablePO.getId());
                tableItem.put("pId", id + "P");
                tableItem.put("name", (catalogTablePO.getRemark() == null || "".equals(catalogTablePO.getRemark())) ? catalogTablePO.getTableName() : catalogTablePO.getRemark() + "(" + catalogTablePO.getTableName() + ")");
                tableItem.put("drag", true);// 自定义属性，标示该节点是否可以拖拽,true标示可以拖拽
                tableItem.put("conditions", catalogTablePO.getConditions());
                result.add(tableItem);
            }

            rm.successMsg();
            rm.setResult(result);

        } catch (Exception e) {
            rm.errorMsg();
            rm = rm.errorMsg("获取数据失败！");
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            log.error("获取申请包创建左侧树数据", e);
            return rm;
        }

        return rm;
    }
}

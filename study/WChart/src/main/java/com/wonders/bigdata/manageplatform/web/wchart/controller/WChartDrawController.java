package com.wonders.bigdata.manageplatform.web.wchart.controller;

import com.wonders.bigdata.manageplatform.service.wchart.service.ChartBoxService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bigdata.manageplatform.service.common.service.HiveService;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogColumnPO;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogTablePO;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.service.UserDataCatalogColumnService;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.service.UserDataCatalogTableService;
import com.wonders.bigdata.manageplatform.service.wchart.model.ChartBoxPO;
import com.wonders.bigdata.manageplatform.web.wchart.vo.WChartDrawNodeVO;
import com.wonders.bud.framework.common.util.RestMsg;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>  Created by LXL on 2016/5/6  </p>
 */
@Controller
@RequestMapping("api/wchart/draw")
public class WChartDrawController {
    protected Logger log = Logger.getLogger(WChartDrawController.class);

    @Resource
    private HiveService hiveService;

    @Resource
    private UserDataCatalogTableService userDataCatalogTableService;

    @Resource
    private UserDataCatalogColumnService userDataCatalogColumnService;

    @Resource
    private ChartBoxService chartBoxService;

    @RequestMapping(value = "/initTableTree")
    @ResponseBody
    public RestMsg<List<WChartDrawNodeVO>> initTableTree(HttpServletRequest request) {
        // 初始化返回信息
        RestMsg<List<WChartDrawNodeVO>> returnMsg = new RestMsg<>(RestMsg.RECODE_OK, RestMsg.SUCCESS);
        List<WChartDrawNodeVO> wChartDrawNodeVOs = new ArrayList<>();
        try {

            String tableIdsFromPage = request.getParameter("tableIds");
            String[] tableIds = tableIdsFromPage.split(",");
            for (String tableId : tableIds) {
                UserDataCatalogTablePO userDataCatalogTablePO = userDataCatalogTableService.findById(Long.parseLong(tableId));
                WChartDrawNodeVO wChartDrawNodeTableVO = new WChartDrawNodeVO();
                wChartDrawNodeTableVO.setParentId(WChartDrawNodeVO.VIRTUAL_ROOT);
                wChartDrawNodeTableVO.setName(userDataCatalogTablePO.getTableRemark());
                wChartDrawNodeTableVO.setRealName(userDataCatalogTablePO.getTableName());
                wChartDrawNodeTableVO.setId(WChartDrawNodeVO.VIRTUAL_ROOT + "-" + userDataCatalogTablePO.getId().toString());
                wChartDrawNodeTableVO.setRealId(userDataCatalogTablePO.getId());
                List<UserDataCatalogColumnPO> userDataCatalogColumnPOs = userDataCatalogColumnService.findByUserCatalogTableId(userDataCatalogTablePO.getId());
                if (userDataCatalogColumnPOs.size() > 0) {
                    wChartDrawNodeTableVO.setIsParent(true);
                } else {
                    wChartDrawNodeTableVO.setIsParent(false);
                }
                wChartDrawNodeVOs.add(wChartDrawNodeTableVO);
            }
            returnMsg.setResult(wChartDrawNodeVOs);
        } catch (Exception e) {
            returnMsg.errorMsg("");
            log.error(e.getLocalizedMessage());
        }
        return returnMsg;
    }

    @RequestMapping(value = "/asynDrawTree")
    @ResponseBody
    public RestMsg<List<WChartDrawNodeVO>> asynDrawTree(HttpServletRequest request) {
        // 初始化返回信息
        RestMsg<List<WChartDrawNodeVO>> returnMsg = new RestMsg<>(RestMsg.RECODE_OK, RestMsg.SUCCESS);
        List<WChartDrawNodeVO> wChartDrawNodeVOs = new ArrayList<>();
        try {
            String tableIdFromPage = request.getParameter("id");
            String tableRealIdFromPage = request.getParameter("realId");
            UserDataCatalogTablePO userDataCatalogTablePO = userDataCatalogTableService.findById(Long.parseLong(tableRealIdFromPage));
            List<UserDataCatalogColumnPO> userDataCatalogColumnPOs = userDataCatalogColumnService.findByUserCatalogTableId(userDataCatalogTablePO.getId());
            for (UserDataCatalogColumnPO userDataCatalogColumnPO : userDataCatalogColumnPOs) {
                WChartDrawNodeVO wChartDrawNodeColumnVO = new WChartDrawNodeVO();
                wChartDrawNodeColumnVO.setParentId(tableIdFromPage);
                wChartDrawNodeColumnVO.setName(userDataCatalogColumnPO.getRemark());
                wChartDrawNodeColumnVO.setRealName(userDataCatalogColumnPO.getName());
                wChartDrawNodeColumnVO.setId(tableIdFromPage + "-" + userDataCatalogColumnPO.getId().toString());
                wChartDrawNodeColumnVO.setRealId(userDataCatalogColumnPO.getId());
                wChartDrawNodeColumnVO.setIsParent(false);
                wChartDrawNodeVOs.add(wChartDrawNodeColumnVO);
            }
            returnMsg.setResult(wChartDrawNodeVOs);
        } catch (Exception e) {
            returnMsg.errorMsg("");
            log.error(e.getLocalizedMessage());
        }
        return returnMsg;
    }

    @RequestMapping(value = "/getElement")
    @ResponseBody
    public RestMsg<List<String>> getElement(HttpServletRequest request) {
        // 初始化返回信息
        RestMsg<List<String>> returnMsg = new RestMsg<>(RestMsg.RECODE_OK, RestMsg.SUCCESS);

        try {
            /*Long tableId = Long.parseLong(request.getParameter("tableId"));
            Long columnId = Long.parseLong(request.getParameter("columnId"));
            UserDataCatalogTablePO userDataCatalogTablePO = userDataCatalogTableService.findById(tableId);
            UserDataCatalogColumnPO userDataCatalogColumnPO = userDataCatalogColumnService.findById(columnId);
            List<String> returnResult = hiveService.getColumnValues(userDataCatalogTablePO.getTableName(), userDataCatalogColumnPO.getName());
            returnMsg.setResult(returnResult);*/
        } catch (Exception e) {
            returnMsg.errorMsg("");
            log.error(e.getLocalizedMessage());
        }
        return returnMsg;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public RestMsg<String> save(HttpServletRequest request) {
        // 初始化返回信息
        RestMsg<String> returnMsg = new RestMsg<>(RestMsg.RECODE_OK, RestMsg.SUCCESS);

        try {
            Long type = Long.parseLong(request.getParameter("type"));
            Long userId = Long.parseLong(request.getParameter("userId"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String element = request.getParameter("element");
            ChartBoxPO chartBoxPO = new ChartBoxPO();
            chartBoxPO.setUserId(userId);
            chartBoxPO.setChartTypeId(type);
            chartBoxPO.setTitle(title);
            chartBoxPO.setDescription(description);
            chartBoxPO.setElements(element);
            chartBoxPO.setCreateDate(new Date());
            chartBoxPO.setUpdateDate(new Date());
            chartBoxPO.setDeleteFlag(Constant.CHART_BOX_UN_DELETE);
            chartBoxService.save(chartBoxPO);
        } catch (Exception e) {
            returnMsg.errorMsg("");
            log.error(e.getLocalizedMessage());
        }
        return returnMsg;
    }
}

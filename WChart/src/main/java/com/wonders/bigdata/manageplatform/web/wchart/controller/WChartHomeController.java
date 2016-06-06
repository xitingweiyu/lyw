package com.wonders.bigdata.manageplatform.web.wchart.controller;

import com.wonders.bigdata.manageplatform.service.task.scheduler.manager.TaskScheduleManager;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogColumnPO;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogTablePO;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.service.UserDataCatalogColumnService;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.service.UserDataCatalogTableService;
import com.wonders.bigdata.manageplatform.web.wchart.vo.WChartDrawNodeVO;
import com.wonders.bud.framework.common.util.RestMsg;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>  Created by LXL on 2016/5/5  </p>
 */
@Controller
@RequestMapping("api/wchart/home")
public class WChartHomeController {

    protected Logger log = Logger.getLogger(WChartHomeController.class);


}

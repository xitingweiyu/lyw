package com.wonders.bigdata.manageplatform.web.dataquality.controller;

import com.wonders.bigdata.manageplatform.service.dataresource.model.po.DataResourcePO;
import com.wonders.bigdata.manageplatform.service.dataresource.service.DataResourceService;
import com.wonders.bigdata.manageplatform.service.log.model.po.TaskLogPO;
import com.wonders.bigdata.manageplatform.service.log.service.LogService;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataTablePO;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataTableService;
import com.wonders.bigdata.manageplatform.service.resourcetype.model.po.ResourceTypePO;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.ResourceTypeService;
import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;
import com.wonders.bigdata.manageplatform.service.task.scheduler.manager.TaskScheduleManager;
import com.wonders.bigdata.manageplatform.service.task.service.TaskService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bigdata.manageplatform.utils.CronExpression;
import com.wonders.bigdata.manageplatform.web.dataquality.vo.TaskLogVO;
import com.wonders.bigdata.manageplatform.web.manager.resourcecollect.vo.TaskMonitorVO;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.page.PageVO;
import com.wonders.bud.framework.common.util.QueryParam;
import com.wonders.bud.framework.common.util.RestMsg;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.quartz.JobKey.jobKey;

/**
 * Created by WYP on 2016/4/25.
 */
@Controller
@RequestMapping("api/dataqualityTask")
public class DataQualityTaskController {
    protected Logger log = Logger.getLogger(DataQualityTaskController.class);

    @Autowired
    private TaskScheduleManager tsm;

    @Autowired
    ResourceTypeService resourceTypeService;

    @Autowired
    DataResourceService dataResourceService;

    @Autowired
    MetadataTableService metadataTableService;

    @Autowired
    TaskService taskService;

    @Autowired
    LogService logService;
    /**
     * 初始化数据源分类列表
     * @param request
     * @return
     */
    @RequestMapping( value = "/initResourceType" , method = RequestMethod.POST)
    @ResponseBody
    public RestMsg<List<ResourceTypePO>> initResourceType(HttpServletRequest request){
        RestMsg<List<ResourceTypePO>> rm = new RestMsg<>();
        List<ResourceTypePO> list = new ArrayList<>();
        try {
            list = resourceTypeService.getAllResourceType();
            rm.setResult(list);
            rm.successMsg();
        }catch(Exception e){
            e.printStackTrace();
            rm.errorMsg();
        }
        return rm;
    }

    /**
     * 初始化数据源列表
     * @param request
     * @return
     */
    @RequestMapping( value = "/initResourceTabList" , method = RequestMethod.POST)
    @ResponseBody
    public RestMsg<List<DataResourcePO>> initResourceTabList(HttpServletRequest request){
        RestMsg<List<DataResourcePO>> rm = new RestMsg<>();
        String rId = request.getParameter("typeId");
        List<DataResourcePO> list = new ArrayList<>();

        try {
            if (StringUtils.isNotEmpty(rId)) {
                list = dataResourceService.findByType(rId);
                rm.setResult(list);
                rm.successMsg();
            }
        }catch (Exception e){
            e.printStackTrace();
            rm.errorMsg();
        }

        return rm;
    }

    /**
     * 初始化数据表树
     * @author WYP
     */
    @RequestMapping(value = "/initTablesTree" , method = RequestMethod.POST)
    @ResponseBody
    public RestMsg<String> initTablesTree(HttpServletRequest request){
        RestMsg<String> rm = new RestMsg<>();

        // 初始化并获取根节点
        String jsonData = "";
        List<Object> jsonList = new ArrayList<Object>();

        try {
            String resourceId = request.getParameter("resourceId");
            String resourceName = request.getParameter("resourceName");
            String taskId = request.getParameter("taskId");
            String setables = request.getParameter("setables");
            String tables = "";
            if(StringUtils.isNotEmpty(taskId)){
                Long tid = Long.parseLong(taskId);
                TaskPO tpo = taskService.getById(tid);
                tables = tpo.getResourceTables();
            }else if(StringUtils.isNotEmpty(setables)){
                tables = setables;
            }

            List<MetadataTablePO> lists = metadataTableService.getTableByResourceId(resourceId);

            Map<String, Object> rootNode = new HashMap<String, Object>();
           // rootNode.put("id", 0);
           // rootNode.put("pId", -1);
            //rootNode.put("name", resourceName);
            //jsonList.add(rootNode);

            // 树不空时
            if (lists.size() > 0) {

                for (int i = 0; i < lists.size(); i++) {
                    // 获取节点信息
                    Map<String, Object> node = new HashMap<String, Object>();
                    node.put("id", lists.get(i).getId());
                    node.put("pId", 0);
                    node.put("name", lists.get(i).getTableName());
                    if(StringUtils.isNotEmpty(tables)){
                        if(tables.indexOf(lists.get(i).getTableName()) > -1){
                            node.put("checked",true);
                        }
                    }
                    //node.put("chkDisabled",true);

                    // 转成jason格式
                    jsonList.add(node);

                }
            }
            // JSON格式转换
            JSONArray data = JSONArray.fromObject(jsonList);
            jsonData = data.toString();
            rm.setResult(jsonData);
            rm.successMsg();
        }catch (Exception e){
            e.printStackTrace();
            rm.errorMsg();
        }
        return rm;
    }


    /**
     * Description:[任务新增]
     * Created by WYP [2015年3月25日]
     * @param request
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public RestMsg<Object> addTask(@ModelAttribute("task") TaskMonitorVO task, HttpServletRequest request) {
        RestMsg<Object> rm = new RestMsg<Object>();
        Long id = task.getId();
        String column = task.getTableColumn();
        try{
            //资源名称重名校验
            QueryParam param = new QueryParam();
                //任务名称重名校验
                Map<String, Object> check = new HashMap<String, Object>();
                String name = task.getName();

                check.put("name", name);
                check.put("deleteFlag", Constant.TASK_NOT_DELETE);
                if ((id != 0 && !taskService.duplicatecheck(id, check)) //编辑重命名验证
                        || !taskService.duplicatecheck(null, check)		//保存重命名验证
                        ) {
                    String cron = CronExpression.generateExp(task); //生成cron表达式
                    TaskPO po = new  TaskPO();
                    String message = "任务创建成功 ！";
                    if(id != 0) {
                        po = taskService.getById(id);
                        tsm.getScheduler().deleteJob(jobKey(po.getName(), Constant.TASK_GROUP));
                        message = "任务修改成功 ！";
                    }
                    TaskMonitorVO.vo2Po(po, task);
                    po.setCronExpression(cron);
                    String tables = (task.getSelectedtableNames().substring(0,task.getSelectedtableNames().length()-1));
                    po.setResourceTables(tables);
                    String tabCol = organize(tables,column);
                    po.setTableColumn(tabCol);
                    SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                    Date date =  formatter.parse(task.getBeginTime());
                    po.setBeginTime(date);

                    taskService.save(po);
                    rm = rm.successMsg();
                    rm.setMsg(message);
                }else{
                    rm.setMsg("任务名称为【" + name + "】已存在！");
                }
                //}

        }catch (Exception e) {
            e.printStackTrace();
            rm.errorMsg("任务创建失败！");
        }
        return rm;
    }

    public String organize(String name, String column){
        String[] strt = name.split(",");
        String[] strc = column.split(",");
        JSONObject json = new JSONObject();

        for(int i = 0; i<strt.length; i++){
            String temp = strc[i*5]+","+ strc[i*5+1]+","+ strc[i*5+2]+","+ strc[i*5+3]+","+ strc[i*5+4];
            try {
                json.put(strt[i], temp);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return json.toString();
    }

    /**
     * 任务分页
     * @author WYP
     * @param request
     * @return
     */
    @RequestMapping(value = "/task/page" , method = RequestMethod.GET)
    @ResponseBody
    public RestMsg<PageVO<TaskMonitorVO>> taskPage(HttpServletRequest request){
       // RestMsg<PageVO<TaskPO>> rm = new RestMsg<>();

        RestMsg<PageVO<TaskMonitorVO>> rm = new RestMsg<PageVO<TaskMonitorVO>>();
        List<TaskMonitorVO> taskTable = new ArrayList<TaskMonitorVO>();
        String startStr = request.getParameter("start");
        String pagesizeStr = request.getParameter("pagesize");
        String name = request.getParameter("name");
        String statusStr = request.getParameter("status");
        int start = 0;
        Page<TaskPO> page = new Page<TaskPO>();
        int pagesize = 5;
        try {
            if (StringUtils.isNotEmpty(startStr) && !"0".equals(startStr)){
                start = Integer.parseInt(startStr);
            }
            if (StringUtils.isNotEmpty(pagesizeStr)){
                pagesize = Integer.parseInt(pagesizeStr);
                page.setPagesize(pagesize);
            }
        } catch (NumberFormatException e) {
            log.error(e.getLocalizedMessage());
            rm.errorMsg("任务列表查询失败！");
        }
        QueryParam param = new QueryParam();
        Map<String, Object> eq = new HashMap<String, Object>();
        Map<String, Object> ne = new HashMap<String, Object>();
        eq.put("deleteFlag", Constant.TASK_NOT_DELETE);//把未删除的标志位加入到查询条件中
        if(StringUtils.isEmpty(statusStr) || "all".equals(statusStr)) {
            ne.put("status", Constant.TASK_ID_NULL);
            param.setNe(ne);
        } else {
            try {
                int status = Integer.parseInt(statusStr);
                eq.put("status", status);
            } catch (NumberFormatException e) {
                log.error("任务监控分页",e);
                rm.errorMsg("任务列表查询失败！");
            }

        }
        if (name != null) {
            Map<String, Object> like = new HashMap<String, Object>();
            like.put("name", name);
            param.setLike(like);
        }
        param.setEq(eq);

        page.setStart(start);

        page.setParam(param);
        Page<TaskPO> taskPage = taskService.findByPage(page);//按照查询条件分页查询任务数据

        eq.put("status", Constant.TASK_STATUS_SUCCESS);
        param.setEq(eq);
        long taskSuccess = taskService.countTask(param);//计数执行成功的任务
        eq.put("status",Constant.TASK_STATUS_ERROR);
        param.setEq(eq);
        long taskError = taskService.countTask(param);//计数执行出错的任务
        eq.put("status", Constant.TASK_STATUS_RUNNING);
        param.setEq(eq);
        long taskRunning = taskService.countTask(param);//计数正在执行的任务
        eq.put("status", Constant.TASK_STATUS_STOP);
        param.setEq(eq);
        long taskStop = taskService.countTask(param);//计数已停止的任务
        //FIXME   Status属性已改，此处逻辑不通
        eq.put("status", Constant.TASK_STATUS_ACTIVE);
        param.setEq(eq);
        long taskActive = taskService.countTask(param);//计数已激活的任务*/

        if(taskPage.getResult().size()>0){
            for (TaskPO po : taskPage.getResult()) {
                TaskMonitorVO tv = CronExpression.parseExp(po.getCronExpression(), po.getExecuteType(), po.getCreateDate());
                TaskMonitorVO.po2Vo(tv, po);
                String[] temp = tv.getBeginTime().split(" ");
                tv.setBeginTime(temp[0]+" "+temp[1].replaceAll("-", ":"));
                tv.setResourceTables(po.getResourceTables());
                taskTable.add(tv);
            }
        }
        PageVO<TaskMonitorVO> pageVO = new PageVO<TaskMonitorVO>();
        if (pagesize > 0) {
            page.setPage(start / pagesize + 1);
        } else {
            page.setPage(0);
        }

        Map<String, Object> count = new HashMap<String, Object>();
        count.put("taskSuccess", taskSuccess);
        count.put("taskError", taskError);
        count.put("taskRunning", taskRunning);
        count.put("taskStop", taskStop);
        count.put("taskActive", taskActive);
        long taskAll = taskSuccess + taskActive + taskError + taskRunning + taskStop;//任务总数
        count.put("taskAll", taskAll);
        pageVO.setRow(taskPage.getPagesize());;
        pageVO.setTotal(taskPage.getTotal());
        pageVO.setData(taskTable);
        pageVO.setExt(count);
        rm = rm.successMsg();
        rm.setResult(pageVO);

        return rm;

    }



    /**
     * <p>
     * Description:获取任务日志(按照时间段获取)
     * </p>
     *
        @author WYP
     */
    @RequestMapping(value = "/log/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public RestMsg<TaskLogVO> getLog(HttpServletRequest request, @PathVariable Long taskId) {
        RestMsg<TaskLogVO> rm = new RestMsg<TaskLogVO>();
        //获取页面参数
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        try {
            TaskLogVO vo = new TaskLogVO();
            QueryParam param = new QueryParam();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
                Map<String, Object[]> between = new HashMap<String, Object[]>();
                Date obj[] = {sdf.parse(beginTime), sdf.parse(endTime)};
                if(obj[0].after(obj[1])){
                    rm.errorMsg("请检查起始时间是否正确！起始时间应先于结束时间");
                    return rm;
                }
                between.put("logTime", obj);
                param.setBetween(between);
            }else if(StringUtils.isNotEmpty(beginTime) && StringUtils.isEmpty(endTime)){
                Map<String, Object> ge = new HashMap<String, Object>();
                ge.put("logTime", sdf.parse(beginTime));
                param.setGe(ge);
            }else if(StringUtils.isEmpty(beginTime) && StringUtils.isNotEmpty(endTime)){
                Map<String, Object> le = new HashMap<String, Object>();
                le.put("logTime", sdf.parse(endTime));
                param.setLe(le);
            }
            HashMap<String, Object> eq = new HashMap<String, Object>();
            eq.put("taskId", taskId);
            param.setEq(eq);
            HashMap<String, String> order = new HashMap<String, String>();
            order.put("id", "desc");
            param.setOrder(order);

            List<TaskLogPO> list = logService.getTaskLogByTaskId(param);
            TaskPO taskPO = taskService.getById(taskId);
            if (list != null && list.size() > 0) {
                vo.setTask(taskPO);
                vo.setLogs(list);
            }
            rm.setResult(vo);
            rm.successMsg();
        } catch (Exception e) {
            log.error("获取任务日志",e);
            rm.errorMsg("任务日志获取失败！");
        }
        return rm;
    }


    /**
     * <p>
     * Description:任务停止
     * </p>
     *
     * @author WYP
     * @return RestMsg<PageVO<TaskVO>>
     */
    @RequestMapping(value = "/stop/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestMsg<Object> stopTask(HttpServletRequest request, @PathVariable Long id) {
        RestMsg<Object> rm = new RestMsg<Object>();
        try {
            String result = taskService.stop(id);
            if("success".equals(result) ) {
                rm = rm.successMsg();
                rm.setMsg("任务停止成功！");
            } else {
                rm.errorMsg(result);
            }
        } catch (Exception e) {
            log.error("任务停止",e);
            rm.errorMsg("任务停止失败！");
        }
        return rm;
    }


    /**
     * <p>
     * Description:删除任务
     * </p>
     *
     * @author WYP
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public RestMsg<Object> deleteTask(HttpServletRequest request, @PathVariable Long id) {
        RestMsg<Object> rm = new RestMsg<Object>();
        try {
            taskService.deleteById(id);;
            rm = rm.successMsg();
            rm.setMsg("任务删除成功！");
        } catch (Exception e) {
            log.error("删除任务",e);
            rm.errorMsg("任务删除失败！");
        }
        return rm;
    }


    /**
     * <p>
     * Description:任务激活
     * </p>
     *
     * @author WF
     * @version $Revision$ 2015-03-24
     * @author (latest modification by $Author$)
     * @since 20130601
     * @param request
     * @return
     */
    @RequestMapping(value = "/start/{id}", method = RequestMethod.POST)
    @ResponseBody
    public RestMsg<Object> startTask(HttpServletRequest request, @PathVariable Long id) {
        RestMsg<Object> rm = new RestMsg<Object>();
        try {
            String result = taskService.start(id);
            if("success".equals(result) ) {
                rm = rm.successMsg();
                rm.setMsg("任务激活成功！");
            } else {
                rm.errorMsg(result);
            }
        } catch (Exception e) {
            log.error("任务激活",e);
            rm.errorMsg("任务激活失败！");
        }
        return rm;
    }

    /**
     * 已选择表回填
     * @author WYP
     * @param request
     * @return
     */
    @RequestMapping( value = "/rewriteTables" , method = RequestMethod.POST)
    @ResponseBody
    public RestMsg<TaskPO> rewriteTables(HttpServletRequest request){
        RestMsg<TaskPO> rm = new RestMsg<>();
        String taskId = request.getParameter("taskId");
        if(StringUtils.isNotEmpty(taskId)){
            TaskPO tpo = taskService.getById(Long.parseLong(taskId));
            rm.successMsg();
            rm.setResult(tpo);
        }else{
            rm.errorMsg();
        }
        return rm;
    }

}

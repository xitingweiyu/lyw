package com.wonders.bigdata.manageplatform.service.task.scheduler.job;

import com.wonders.bigdata.manageplatform.service.common.service.HiveService;
import com.wonders.bigdata.manageplatform.service.log.model.po.TaskLogPO;
import com.wonders.bigdata.manageplatform.service.log.service.LogService;
import com.wonders.bigdata.manageplatform.service.task.model.po.QualityPO;
import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;
import com.wonders.bigdata.manageplatform.service.task.service.QualityService;
import com.wonders.bigdata.manageplatform.service.task.service.TaskService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bigdata.manageplatform.utils.Messages;
import org.apache.sqoop.model.MSubmission;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by WYP on 2016/4/27.
 */
public class DataQuality {
    private TaskPO task;
    private HiveService hiveService;
    private LogService logService;


    private TaskService taskService;
    private QualityService qualityService;

    public QualityService getQualityService() {
        return qualityService;
    }

    public void setQualityService(QualityService qualityService) {
        this.qualityService = qualityService;
    }

    public TaskPO getTask() {
        return task;
    }

    public void setTask(TaskPO task) {
        this.task = task;
    }

    public HiveService getHiveService() {
        return hiveService;
    }

    public void setHiveService(HiveService hiveService) {
        this.hiveService = hiveService;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    /*
    * 任务提交函数
    */
    public void submission() {
        MSubmission submission = null;
        String hiveurl = Messages.getString("jdbcHiveUrl");
        String hiveUsername = Messages.getString("hiveUser");
        String hivePwd = Messages.getString("hivePwd");

        String tables = task.getResourceTables();
        String tabCol = task.getTableColumn();
        task.setStatus(Constant.TASK_STATUS_RUNNING);
        taskService.simpleSave(task);

        String[] str = tables.split(",");
        List<Map<String,String>> list = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(tabCol);
            //遍历所有表名
            for (int i = 0; i < str.length; i++) {
                list = hiveService.getHiveColunmsByTableName(hiveurl, hiveUsername, hivePwd, str[i]);
                //获得表下所有字段名称
                String col = json.getString(str[i]);
                String[] colinfo = col.split(",");
                for (int j = 0; j < list.size(); j++) {
                    Map<String, String> map = new HashMap<>();
                    map = list.get(j);
                    String column = map.get("column");
                    String columnType = map.get("type");
                    //对每个字段有5次执行查询操作

                    for (int k = 1; k < 6; k++) {
                        if ("1".equals(colinfo[k-1])) {

                            String sql = "";
                            switch (k) {
                                case 4:
                                    sql = "select max(" + column + ") from " + str[i] + " ;";
                                    break;
                                case 5:
                                    sql = "select min(" + column + ") from " + str[i] + " ;";
                                    break;
                                case 1:
                                    sql = "select count(*) from " + str[i] + " where " + column + " = null;";
                                    break;
                                case 2:
                                    sql = "select count(*) from " + str[i] + " where " + column + " = 0;";
                                    break;
                                case 3:
                                    sql = "select count(*) from " + str[i] + " where " + column + " = 'null';";
                                    break;
                            }
                            try {
                                String result = hiveService.executeSql(sql);
                                saveDataQuality(str[i], column, columnType, result, k);
                                String content = "" + column + " in " + str[i] + " result is " + result;
                                addTaskLog(content, task.getId());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            task.setStatus(Constant.TASK_STATUS_SUCCESS);
            taskService.simpleSave(task);
        }catch (Exception e){
            task.setStatus(Constant.TASK_STATUS_ERROR);
            taskService.simpleSave(task);
        }

    }


    /**
     * 保存数据到dataQuality表
     * @param tableName
     * @param column
     * @param columntype
     * @param result
     */
    public void saveDataQuality(String tableName, String column, String columntype, String result,Integer indexId){
        QualityPO qualityPO  = new QualityPO();
        qualityPO.setTableName(tableName);
        qualityPO.setColumnName(column);
        qualityPO.setColumnType(columntype);
        qualityPO.setIndexId((long)indexId);
        switch (indexId){
            case 1: qualityPO.setIndexName("空值");
                break;
            case 2: qualityPO.setIndexName("0值");
                break;
            case 3: qualityPO.setIndexName("null值");
                break;
            case 4: qualityPO.setIndexName("最大值");
                break;
            case 5: qualityPO.setIndexName("最小值");
                break;
        }
        qualityPO.setResult(result);
        qualityPO.setCreateDate(new Date());
        qualityService.save(qualityPO);

    }



    /*
	 * 为每个任务添加任务日志
	 */
    public void addTaskLog(String content, long taskId) {
        TaskLogPO tlp = new TaskLogPO();
        tlp.setTaskId(taskId);
        tlp.setLogTime(new Date());
        tlp.setMessage(content);
        logService.saveTaskLog(tlp);
    }

}


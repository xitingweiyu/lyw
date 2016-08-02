package com.wonders.bigdata.manageplatform.web.manager.resourcecollect.vo;

import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;

import java.util.List;
import java.util.Map;


/**<p>
 * Title: manageplatform_[大数据管理平台]_[任务监控]
 * </p>
 * <p>
 * Description: [任务监控VO]
 * </p>
 * 
 * @author WF
 * @version $Revision$ 2015年3月24日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
public class  TaskMonitorVO{
	private long id;
	private String name;
	private int executeType;
	private int incrementType;
	private int taskType;
	private int status;
	private long dataResourceId;
	private String beginTime;
	private String taskFrequency;
	private String timeGapUnit;
	private int timeGap;
	private String resourceName;
	private String resourceTables;
	private int timeout;
	private String selectedtableNames;
	private String selectedtableIds;
	private List<Map<String, Object>> tables;
	private String tableColumn;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getExecuteType() {
		return executeType;
	}
	public void setExecuteType(int executeType) {
		this.executeType = executeType;
	}
	public int getIncrementType() {
		return incrementType;
	}
	public void setIncrementType(int incrementType) {
		this.incrementType = incrementType;
	}
	public int getTaskType() {
		return taskType;
	}
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getDataResourceId() {
		return dataResourceId;
	}
	public void setDataResourceId(long dataResourceId) {
		this.dataResourceId = dataResourceId;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getTaskFrequency() {
		return taskFrequency;
	}
	public void setTaskFrequency(String taskFrequency) {
		this.taskFrequency = taskFrequency;
	}
	public String getTimeGapUnit() {
		return timeGapUnit;
	}
	public void setTimeGapUnit(String timeGapUnit) {
		this.timeGapUnit = timeGapUnit;
	}
	public int getTimeGap() {
		return timeGap;
	}
	public void setTimeGap(int timeGap) {
		this.timeGap = timeGap;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public String getResourceTables() {
		return resourceTables;
	}
	public void setResourceTables(String resourceTables) {
		this.resourceTables = resourceTables;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public List<Map<String, Object>> getTables() {
		return tables;
	}
	public void setTables(List<Map<String, Object>> tables) {
		this.tables = tables;
	}

	public String getSelectedtableNames() {
		return selectedtableNames;
	}

	public void setSelectedtableNames(String selectedtableNames) {
		this.selectedtableNames = selectedtableNames;
	}

	public String getSelectedtableIds() {
		return selectedtableIds;
	}

	public void setSelectedtableIds(String selectedtableIds) {
		this.selectedtableIds = selectedtableIds;
	}

	public String getTableColumn() {
		return tableColumn;
	}

	public void setTableColumn(String tableColumn) {
		this.tableColumn = tableColumn;
	}

	/*
             * 任务存储实体转视图实体
             * */
	public static void po2Vo(TaskMonitorVO tv, TaskPO tp){
		tv.setId(tp.getId());
		tv.setName(tp.getName());
		tv.setExecuteType(tp.getExecuteType());
		tv.setTaskType(tp.getTaskType());
		tv.setStatus(tp.getStatus());
		tv.setDataResourceId(tp.getDataResourceId());
		tv.setIncrementType(tp.getIncrementType());
		tv.setTimeout(tp.getTimeout());
		tv.setResourceTables(tp.getResourceTables());
	}
	
	/*
	 * 任务视图实体转存储实体
	 * */
	public static void vo2Po(TaskPO tp, TaskMonitorVO tv) {
		if(tv.getId() != 0) {
			tp.setId(tv.getId());
		}
		tp.setExecuteType(tv.getExecuteType());
		tp.setName(tv.getName());
		tp.setTaskType(tv.getTaskType());
		tp.setStatus(tv.getStatus());
		tp.setIncrementType(tv.getIncrementType());
		tp.setTimeout(tv.getTimeout());
		tp.setDataResourceId(tv.getDataResourceId());
		tp.setTaskEntityId(0L);
		tp.setResourceTables(tv.getResourceTables());
	}
	
}

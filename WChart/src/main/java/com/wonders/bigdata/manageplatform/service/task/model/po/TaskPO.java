/** 
 * 
 * Copyright (c) 1995-2012 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wonders.bigdata.manageplatform.service.task.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [任务表PO]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Entity
@Table(name = "bd_task")
public class  TaskPO {
	// Fields

	private Long id;//编号
	private String name;//名称
	private Long dataResourceId;//数据源编号
	private Integer executeType;//执行方式
	private Integer incrementType;//增量方式
	private Integer taskType;//任务类型
	private Long taskEntityId;//任务实体编号
	private Integer status;//任务状态
	private String cronExpression;//cron调度表达式
	private Long userId;//用户编号
	private Date createDate;//创建时间
	private Date updateDate;//更新时间
	private Integer deleteFlag;//删除标志
	private String resourceTables;//资源表
	private int timeout;//响应时间
	private int collectWay;
	private Date beginTime;
	private String tableColumn;
	// Constructors

	/** default constructor */
	public TaskPO() {
	}

	/** minimal constructor */
	public TaskPO(String name, Long dataResourceId, Integer executeType, Integer incrementType, Integer taskType,
			Long taskEntityId, Integer status, Long userId, Date createDate, Date updateDate, Integer deleteFlag,
				  Integer collectWay, Date beginTime) {
		this.name = name;
		this.dataResourceId = dataResourceId;
		this.executeType = executeType;
		this.incrementType = incrementType;
		this.taskType = taskType;
		this.taskEntityId = taskEntityId;
		this.status = status;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.deleteFlag = deleteFlag;
		this.collectWay = collectWay;
		this.beginTime = beginTime;
	}

	/** full constructor */
	public TaskPO(String name, Long dataResourceId, Integer executeType, Integer incrementType, Integer taskType,
			Long taskEntityId, Integer status, String cronExpression, Long userId, Date createDate, Date updateDate,
			Integer deleteFlag, int timeout, String resourceTables, Integer collectWay, Date beginTime, String tableColumn) {
		this.name = name;
		this.dataResourceId = dataResourceId;
		this.executeType = executeType;
		this.incrementType = incrementType;
		this.taskType = taskType;
		this.taskEntityId = taskEntityId;
		this.status = status;
		this.cronExpression = cronExpression;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.deleteFlag = deleteFlag;
		this.timeout = timeout;
		this.resourceTables = resourceTables;
		this.collectWay = collectWay;
		this.beginTime = beginTime;
		this.tableColumn = tableColumn;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "data_resource_id", nullable = false)
	public Long getDataResourceId() {
		return this.dataResourceId;
	}

	public void setDataResourceId(Long dataResourceId) {
		this.dataResourceId = dataResourceId;
	}

	@Column(name = "execute_type", nullable = false)
	public Integer getExecuteType() {
		return this.executeType;
	}

	public void setExecuteType(Integer executeType) {
		this.executeType = executeType;
	}

	@Column(name = "increment_type", nullable = false)
	public Integer getIncrementType() {
		return this.incrementType;
	}

	public void setIncrementType(Integer incrementType) {
		this.incrementType = incrementType;
	}

	@Column(name = "task_type", nullable = false)
	public Integer getTaskType() {
		return this.taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	@Column(name = "task_entity_id", nullable = false)
	public Long getTaskEntityId() {
		return this.taskEntityId;
	}

	public void setTaskEntityId(Long taskEntityId) {
		this.taskEntityId = taskEntityId;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "cron_expression")
	public String getCronExpression() {
		return this.cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "create_date", nullable = false, length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date", nullable = false, length = 0)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "delete_flag", nullable = false)
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	@Column(name = "timeout")
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Column(name = "resource_tables" )//nullable = false)
	public String getResourceTables() {
		return resourceTables;
	}

	public void setResourceTables(String resourceTables) {
		this.resourceTables = resourceTables;
	}

	@Column(name = "collect_way")
	public int getCollectWay() {
		return collectWay;
	}

	public void setCollectWay(int collectWay) {
		this.collectWay = collectWay;
	}

	@Column(name = "begin_time", length = 0)
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "table_column")
	public String getTableColumn() {
		return tableColumn;
	}

	public void setTableColumn(String tableColumn) {
		this.tableColumn = tableColumn;
	}
}

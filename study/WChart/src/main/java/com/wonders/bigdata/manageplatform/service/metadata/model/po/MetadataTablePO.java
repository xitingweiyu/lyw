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

package com.wonders.bigdata.manageplatform.service.metadata.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [元数据表PO]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Entity
@Table(name = "bd_metadata_table")
public class  MetadataTablePO {
	// Fields

	private Long id; // id

	private String tableName; // 表名

	private String schemaName; // 数据库名

	private String remark; // 备注

	private String description;// 描述

	private String example; // 样例数据

	private String resourceId;// 原数据id

	private Integer openLevel; // 开放等级

	private Integer status; // 表状态

	private Date createDate; // 创建时间

	private Date editDate; // 修改时间

	private Integer deleteFlag;// 删除标志

	private String source; // 记录该表是通过哪些表生成的

	// Constructors

	/** default constructor */
	public MetadataTablePO() {
	}

	/** minimal constructor */
	public MetadataTablePO(Date createDate) {
		this.createDate = createDate;
	}

	/** full constructor */
	public MetadataTablePO(String tableName, String schemaName, String remark, String description, String example,
			String resourceId, Integer openLevel, Integer status, Date createDate, Date editDate, Integer deleteFlag,
			String source) {
		this.tableName = tableName;
		this.schemaName = schemaName;
		this.remark = remark;
		this.description = description;
		this.example = example;
		this.resourceId = resourceId;
		this.openLevel = openLevel;
		this.status = status;
		this.createDate = createDate;
		this.editDate = editDate;
		this.deleteFlag = deleteFlag;
		this.source = source;
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

	@Column(name = "table_name")
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "schema_name")
	public String getSchemaName() {
		return this.schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "description", length = 2000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "example", length = 65535)
	public String getExample() {
		return this.example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	@Column(name = "resource_id")
	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "open_level")
	public Integer getOpenLevel() {
		return this.openLevel;
	}

	public void setOpenLevel(Integer openLevel) {
		this.openLevel = openLevel;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "create_date", nullable = false, length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "edit_date", length = 0)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "source")
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}

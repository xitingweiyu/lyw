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
 * Description: [元数据字段表PO]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Entity
@Table(name = "bd_metadata_column")
public class  MetadataColumnPO {
	// Fields

	private Long id; // id

	private Long tableId; // 表ID

	private String columnName;// 列名

	private String typeName; // 字段类型

	private String remark; // 备注

	private String description;// 描述

	private Integer openLevel;// 开放等级

	private Date createDate; // 创建日期

	private Date editDate; // 修改时间

	private Integer deleteFlag;// 删除位

	private String source; // 记录该字段是通过哪张表生成

	// Constructors

	/** default constructor */
	public MetadataColumnPO() {
	}

	/** minimal constructor */
	public MetadataColumnPO(Date createDate) {
		this.createDate = createDate;
	}

	/** full constructor */
	public MetadataColumnPO(Long tableId, String columnName, String typeName, String remark, String description,
			Integer openLevel, Date createDate, Date editDate, Integer deleteFlag, String source) {
		this.tableId = tableId;
		this.columnName = columnName;
		this.typeName = typeName;
		this.remark = remark;
		this.description = description;
		this.openLevel = openLevel;
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

	@Column(name = "table_id")
	public Long getTableId() {
		return this.tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	@Column(name = "column_name")
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "type_name")
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	@Column(name = "open_level")
	public Integer getOpenLevel() {
		return this.openLevel;
	}

	public void setOpenLevel(Integer openLevel) {
		this.openLevel = openLevel;
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

package com.wonders.bigdata.manageplatform.service.resourcetype.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 数据目录字段关联PO
 * 
 * @author xh 2015年10月28日10:01:00
 *
 */
@Entity
@Table(name = "bd_catalog_column")
public class CatalogColumnPO {

	private Long id; // 主键，自增长

	private Long catalogTableId; // 数据目录关联表id

	private String columnName; // 字段名

	private String typeName; // 字段类型

	private String remark; // 备注

	private Integer openLevel; // 开放等级 0开放，1不开放

	private Date createDate; // 创建时间

	private Integer deleteFlag; // 删除位，0正常，1已删除

	private String description; // 描述

	private Date editDate; // 修改时间

	private Integer status; // 状态 0：未提交审核，1：已提交审核，2：审核通过，3：审核未通过

	private String applyContext;

	public CatalogColumnPO() {
		super();
	}

	public CatalogColumnPO(Long catalogTableId, String columnName, String typeName, String remark, Integer openLevel,
			Date createDate, Integer deleteFlag, String description, Date editDate, Integer status) {
		super();
		this.catalogTableId = catalogTableId;
		this.columnName = columnName;
		this.typeName = typeName;
		this.remark = remark;
		this.openLevel = openLevel;
		this.createDate = createDate;
		this.deleteFlag = deleteFlag;
		this.description = description;
		this.editDate = editDate;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "catalog_table_id")
	public Long getCatalogTableId() {
		return catalogTableId;
	}

	public void setCatalogTableId(Long catalogTableId) {
		this.catalogTableId = catalogTableId;
	}

	@Column(name = "column_name")
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "type_name")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "open_level")
	public Integer getOpenLevel() {
		return openLevel;
	}

	public void setOpenLevel(Integer openLevel) {
		this.openLevel = openLevel;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "edit_date")
	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "apply_context")
	public String getApplyContext() {
		return applyContext;
	}

	public void setApplyContext(String applyContext) {
		this.applyContext = applyContext;
	}
}

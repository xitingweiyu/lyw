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
 * Title: manageplatform_[大数据管理平台]_[分析工具]
 * </p>
 * <p>
 * Description: [数据质量表PO]
 * </p>
 * @author XCL
 * @version $Revision$ 2016年4月22日15:54:53
 */
@Entity
@Table(name = "bd_data_quality")
public class  QualityPO {

	private Long id;//编号

	private Long tableId;//编号
	private String tableName;//名称

	private String columnName;//名称
	private String columnType;//名称

	private String indexName;//名称
	private Long indexId;//编号

	private String result;//名称

	private Date createDate;//创建时间
	private Date updateDate;//更新时间
	// Constructors

	/** default constructor */
	public QualityPO() {
	}

	/** full constructor */
	public QualityPO(Long id, Long tableId, String tableName, String columnName, String columnType, String indexName, Long indexId, String result, Date createDate, Date updateDate) {
		this.id = id;
		this.tableId = tableId;
		this.tableName = tableName;
		this.columnName = columnName;
		this.columnType = columnType;
		this.indexName = indexName;
		this.indexId = indexId;
		this.result = result;
		this.createDate = createDate;
		this.updateDate = updateDate;
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


	@Column(name = "create_date",  length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date", length = 0)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "table_id" )
	public Long getTableId() {
		return tableId;
	}

	@Column(name = "table_name",length = 255)
	public String getTableName() {
		return tableName;
	}

	@Column(name = "column_name",  length = 255)
	public String getColumnName() {
		return columnName;
	}

	@Column(name = "column_type",  length = 255)
	public String getColumnType() {
		return columnType;
	}

	@Column(name = "index_name",  length = 255)
	public String getIndexName() {
		return indexName;
	}

	@Column(name = "index_id" )
	public Long getIndexId() {
		return indexId;
	}

	@Column(name = "result",  length = 2000)
	public String getResult() {
		return result;
	}


	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

	public void setResult(String result) {
		this.result = result;
	}
}

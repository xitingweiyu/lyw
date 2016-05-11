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

package com.wonders.bigdata.manageplatform.service.dataresource.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [资产表PO]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Entity
@Table(name = "bd_data_resource")
public class  DataResourcePO {
	// Fields

	private Long id;

	private String resourceName;

	private String resourceType;

	private Long orgId;

	private Long userId;

	private Date createDate;

	private Date updateDate;

	private Integer resourceSize;

	private Integer status;

	private Integer openLevel;

	private Integer resourceKind;

	private Integer databaseType;

	private String schemaName;

	private String description;

	private String exampleTables;

	private String jdbcUrl;

	private String jdbcPassword;

	private String jdbcUsername;

	private String fileDir;

	private String serverIp;

	private Integer serverPort;

	private Integer deleteFlag;

	// Constructors

	/** default constructor */
	public DataResourcePO() {
	}

	/** minimal constructor */
	public DataResourcePO(String resourceName, String resourceType, Long userId, Date createDate, Date updateDate,
			Integer status, Integer openLevel, Integer resourceKind, Integer databaseType, Integer deleteFlag) {
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.openLevel = openLevel;
		this.resourceKind = resourceKind;
		this.databaseType = databaseType;
		this.deleteFlag = deleteFlag;
	}

	/** full constructor */
	public DataResourcePO(String resourceName, String resourceType, Long orgId, Long userId, Date createDate,
			Date updateDate, Integer resourceSize, Integer status, Integer openLevel, Integer resourceKind,
			Integer databaseType, String schemaName, String description, String exampleTables, String jdbcUrl,
			String jdbcPassword, String jdbcUsername, String fileDir, String serverIp, Integer serverPort,
			Integer deleteFlag) {
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.orgId = orgId;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.resourceSize = resourceSize;
		this.status = status;
		this.openLevel = openLevel;
		this.resourceKind = resourceKind;
		this.databaseType = databaseType;
		this.schemaName = schemaName;
		this.description = description;
		this.exampleTables = exampleTables;
		this.jdbcUrl = jdbcUrl;
		this.jdbcPassword = jdbcPassword;
		this.jdbcUsername = jdbcUsername;
		this.fileDir = fileDir;
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.deleteFlag = deleteFlag;
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

	@Column(name = "resource_name", nullable = false)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "resource_type", nullable = false)
	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "org_id")
	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	@Column(name = "resource_size")
	public Integer getResourceSize() {
		return this.resourceSize;
	}

	public void setResourceSize(Integer resourceSize) {
		this.resourceSize = resourceSize;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "open_level", nullable = false)
	public Integer getOpenLevel() {
		return this.openLevel;
	}

	public void setOpenLevel(Integer openLevel) {
		this.openLevel = openLevel;
	}

	@Column(name = "resource_kind", nullable = false)
	public Integer getResourceKind() {
		return this.resourceKind;
	}

	public void setResourceKind(Integer resourceKind) {
		this.resourceKind = resourceKind;
	}

	@Column(name = "database_type", nullable = false)
	public Integer getDatabaseType() {
		return this.databaseType;
	}

	public void setDatabaseType(Integer databaseType) {
		this.databaseType = databaseType;
	}

	@Column(name = "schema_name")
	public String getSchemaName() {
		return this.schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Column(name = "description", length = 2000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "example_tables", length = 1000)
	public String getExampleTables() {
		return this.exampleTables;
	}

	public void setExampleTables(String exampleTables) {
		this.exampleTables = exampleTables;
	}

	@Column(name = "jdbc_url")
	public String getJdbcUrl() {
		return this.jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	@Column(name = "jdbc_password", length = 50)
	public String getJdbcPassword() {
		return this.jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	@Column(name = "jdbc_username", length = 50)
	public String getJdbcUsername() {
		return this.jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}

	@Column(name = "file_dir")
	public String getFileDir() {
		return this.fileDir;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	@Column(name = "server_ip", length = 50)
	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	@Column(name = "server_port")
	public Integer getServerPort() {
		return this.serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	@Column(name = "delete_flag", nullable = false)
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}

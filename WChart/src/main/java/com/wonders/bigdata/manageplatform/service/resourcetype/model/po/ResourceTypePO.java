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

package com.wonders.bigdata.manageplatform.service.resourcetype.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [数据源类型表PO]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Entity
@Table(name = "bd_resource_type")
public class  ResourceTypePO {
	// Fields

	private Long id;

	private String name;

	private String iconPath;

	private String description;

	private Long userId;

	private Date createDate;

	private Date updateDate;

	private int deleteFlag;

	// Constructors

	/** default constructor */
	public ResourceTypePO() {
	}

	/** minimal constructor */
	public ResourceTypePO(String name, Long userId, Date createDate, Date updateDate) {
		this.name = name;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	/** full constructor */
	public ResourceTypePO(String name, String iconPath, String description, Long userId, Date createDate,
			Date updateDate, int deleteFlag) {
		this.name = name;
		this.iconPath = iconPath;
		this.description = description;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "icon_path")
	public String getIconPath() {
		return this.iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Column(name = "delete_flag", nullable = false, length = 1)
	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}

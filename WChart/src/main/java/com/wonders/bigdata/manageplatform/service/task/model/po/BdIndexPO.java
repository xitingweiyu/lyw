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
 * Description: [数据质量指标表PO]
 * </p>
 * @author XCL
 * @version $Revision$ 2016年4月22日15:48:41
 */
@Entity
@Table(name = "bd_index")
public class  BdIndexPO {

	private Long id;//编号
	private String name;//名称
	private String remark;//备注
	private String description;//描述
	private Date createDate;//创建时间
	private Integer deleteFlag;//删除标志

	// Constructors

	/** default constructor */
	public BdIndexPO() {

	}
	/** full constructor */
	public BdIndexPO(Long id, String name, String remark, String description, Date createDate, Integer deleteFlag) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.description = description;
		this.createDate = createDate;
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


	@Column(name = "create_date", nullable = false, length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	@Column(name = "delete_flag", nullable = false)
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "remark", nullable = false)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

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

package com.wonders.bigdata.manageplatform.service.log.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [数据包日志PO]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Entity
@Table(name = "bd_datapack_log")
public class  DataPackageLogPO {
	// Fields

	private Long id; // id

	private Long dpId; // 数据包id

	private String message;// 日志信息

	private Date logTime; // 日志时间

	// Constructors

	/** default constructor */
	public DataPackageLogPO() {
	}

	/** minimal constructor */
	public DataPackageLogPO(Long dpId, Date logTime) {
		this.dpId = dpId;
		this.logTime = logTime;
	}

	/** full constructor */
	public DataPackageLogPO(Long dpId, String message, Date logTime) {
		this.dpId = dpId;
		this.message = message;
		this.logTime = logTime;
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

	@Column(name = "dp_id", nullable = false)
	public Long getDpId() {
		return this.dpId;
	}

	public void setDpId(Long dpId) {
		this.dpId = dpId;
	}

	@Column(name = "message", length = 1000)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "log_time", nullable = false, length = 0)
	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}

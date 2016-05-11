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
 * Description: [操作日志PO]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Entity
@Table(name = "bd_operation_log")
public class  OperationLogPO {
	// Fields

	private Long id; // id

	private Long userId; // 用户

	private Date operateDate; // 操作时间

	private String content; // 操作内容
	
	private int operationType;//操作类型

	// Constructors

	/** default constructor */
	public OperationLogPO() {
	}

	/** minimal constructor */
	public OperationLogPO(Long userId, Date operateDate) {
		this.userId = userId;
		this.operateDate = operateDate;
	}

	/** full constructor */
	public OperationLogPO(Long userId, Date operateDate, String content, int operationType) {
		this.userId = userId;
		this.operateDate = operateDate;
		this.content = content;
		this.operationType = operationType;
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

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "operate_date", nullable = false, length = 0)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "operation_type")
	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}
	
}

package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 操作信息对象，主要用于存入各种用户状态等
 * @author hsw
 *
 */
@Entity
@Table(name="bd_operate_info")
public class  OperateInfoPO {

	//主键id
	private Long id;
	
	//操作者id
	private Long userId;
	
	//操作类型id
	private Long typeId;
	
	//操作类型
	private Integer operateType;
	
	//操作时间
	private Date operateDate;
	
	//操作内容
	private String content;
	
	//删除位（0：未删除，1：已删除）
	private Integer deleteFlag;
	
	//创建时间
	private Date createDate;
	
	public  OperateInfoPO() {
	}
	
	public  OperateInfoPO(long userId,long typeId,int operateType,Date operateDate,String content,int deleteFlag,Date createDate) {
		this.userId = userId;
		this.typeId = typeId;
		this.operateType = operateType;
		this.operateDate = operateDate;
		this.content = content;
		this.deleteFlag = deleteFlag;
		this.createDate = createDate;
	}

	// Property accessors
	   @Id @GeneratedValue(strategy=IDENTITY)
	   @Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name="type_id")
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Column(name="operate_type")
	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	@Column(name="operate_date")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="delete_flag")
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}

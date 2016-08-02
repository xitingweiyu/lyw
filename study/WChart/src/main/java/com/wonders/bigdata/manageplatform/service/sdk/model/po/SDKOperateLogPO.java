package com.wonders.bigdata.manageplatform.service.sdk.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * sdk操作日志记录表
 * @author hsw
 *
 */
@Entity
@Table(name = "bd_sdk_operate_log")
public class   SDKOperateLogPO {

	//主键
	private Long id;
	
	//用户id
	private Long userId;
	
	//操作类型,0是连接，1是查询
	private Integer operateType;
	
	//操作内容
	private String content;
	
	//创建时间
	private Date createDate;
	
	//状态，连接、查询成功与否,0表示成功，1表示失败
	private Integer status;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
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

	@Column(name="operate_type")
	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	} 
}

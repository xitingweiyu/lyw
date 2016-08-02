package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户组PO
 * 
 * @author xuehan
 *
 */
@Entity
@Table(name = "bd_usergroup")
public class  UsergroupPO {

	// 主键
	private Long id;

	// 用户组命
	private String name;

	// 描述
	private String description;

	// 创建时间
	private Date createDate;

	// 删除位（0：未删除，1：已删除）
	private Integer deleteFlag;

	// 删除时间
	private Date deleteDate;

	public UsergroupPO() {
	}

	public UsergroupPO(String name, String description, Date createDate,
			Integer deleteFlag) {
		this.name = name;
		this.description = description;
		this.createDate = createDate;
		this.deleteFlag = deleteFlag;
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="delete_flag")
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name="delete_date")
	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

}

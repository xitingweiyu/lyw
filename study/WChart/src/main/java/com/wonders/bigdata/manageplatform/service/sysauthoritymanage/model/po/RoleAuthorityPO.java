package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色，权限对象
 * @author hsw
 *
 */
@Entity
@Table(name="bd_role_authority")
public class  RoleAuthorityPO {

	//主键id
	private Long id;
	
	//角色id
	private Long roleId;
	
	//权限id
	private Long authorityId;
	
	//创建时间
	private Date createDate;
	
	//删除时间
	private Date deleteDate;
	
	//删除位（0,：未删除，1：已删除）
	private Integer deleteFlag;

	
	// Property accessors
	   @Id @GeneratedValue(strategy=IDENTITY)
	   @Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="role_id")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name="authority_id")
	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="delete_date")
	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Column(name="delete_flag")
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}

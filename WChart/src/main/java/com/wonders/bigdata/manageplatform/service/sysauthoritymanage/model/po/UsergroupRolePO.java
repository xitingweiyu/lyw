package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户组角色PO
 * 
 * @author xuehan
 * @date 2015年4月13日 下午2:00:42
 */

@Entity
@Table(name = "bd_usergroup_role")
public class  UsergroupRolePO {

	// 主键id
	private Long id;

	// 用户组id
	private Long usergroupId;

	// 角色id
	private Long roleId;

	// 创建时间
	private Date createDate;

	// 删除日期
	private Date deleteDate;

	// 删除位 0未删除，1已删除
	private Integer deleteFlag;

	public UsergroupRolePO() {
	}

	public UsergroupRolePO(Long usergroupId, Long roleId, Date createDate,
			Integer deleteFlag) {
		super();
		this.usergroupId = usergroupId;
		this.roleId = roleId;
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

	@Column(name = "usergroup_id")
	public Long getUsergroupId() {
		return usergroupId;
	}

	public void setUsergroupId(Long usergroupId) {
		this.usergroupId = usergroupId;
	}

	@Column(name = "role_id")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "delete_date")
	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}

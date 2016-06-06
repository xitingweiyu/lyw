package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 用户/用户组PO
 * 
 * @author xuehan
 * @date 2015年4月10日 下午5:13:35
 */
@Entity
@Table(name = "bd_user_usergroup")
public class  UserUsergroupPO {

	// 主键id
	private Long id;

	// 用户id
	private Long userId;

	// 用户组id
	private Long usergroupId;

	// 创建时间
	private Date createDate;

	// 删除位
	private Integer deleteFlag;

	// 删除时间
	private Date deleteDate;

	public UserUsergroupPO() {
	}

	public UserUsergroupPO(Long userId, Long usergroupId, Date createDate,
			Integer deleteFlag) {
		super();
		this.userId = userId;
		this.usergroupId = usergroupId;
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

	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name="usergroup_id")
	public Long getUsergroupId() {
		return usergroupId;
	}

	public void setUsergroupId(Long usergroupId) {
		this.usergroupId = usergroupId;
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

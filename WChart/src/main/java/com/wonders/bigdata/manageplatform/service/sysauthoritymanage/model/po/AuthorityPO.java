package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限对象
 * @author hsw
 *
 */
@Entity
@Table(name="bd_authority")
public class  AuthorityPO {

	//主键id
	private Long id;
	
	//权限/权限组名
	private String name;
	
	//权限编码
	private String authorityNum;
	
	//actions字符串
	private String actions;
	
	//权限类型（0权限、1权限组）
	private Integer authorityType;
	
	//权限组包含的权限ids
	//格式如：1,2,3,4(5;6;7),8   1，2，3，8是权限，4是权限组，5，6，7是权限组4下的子权限.
	private String containAuthorityIds;
	
	//描述
	private String description;
	
	//创建时间
	private Date createDate;
	
	//删除位，0未删除，1已删除
	private Integer deleteFlag;
	
	//删除时间
	private Date deleteDate;
	
	public AuthorityPO() {
	}
	
	public AuthorityPO(String name,String authorityNum,String actions,Integer authorityType,String containAuthorityIds,
			String description,Date createDate,Integer deleteFlag,Date deleteDate){
		this.name = name;
		this.authorityNum = authorityNum;
		this.actions = actions;
		this.authorityType = authorityType;
		this.containAuthorityIds = containAuthorityIds;
		this.description = description;
		this.createDate = createDate;
		this.deleteDate = deleteDate;
		this.deleteFlag = deleteFlag;
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

	 @Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="authority_num")
	public String getAuthorityNum() {
		return authorityNum;
	}

	public void setAuthorityNum(String authorityNum) {
		this.authorityNum = authorityNum;
	}

	@Column(name="actions")
	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	@Column(name="authority_type")
	public Integer getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(Integer authorityType) {
		this.authorityType = authorityType;
	}

	@Column(name="contain_authority_ids")
	public String getContainAuthorityIds() {
		return containAuthorityIds;
	}

	public void setContainAuthorityIds(String containAuthorityIds) {
		this.containAuthorityIds = containAuthorityIds;
	}

	@Column(name="description")
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

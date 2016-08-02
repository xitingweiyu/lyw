package com.wonders.bigdata.manageplatform.service.sysauthoritymanage.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 角色对象
 * @author hsw
 *
 */

@Entity
@Table(name="bd_role")
public class  RolePO {

	//主键id
	private Long id;
	
	//角色名
	private String name;
	
	//描述
	private String description;
	
	//创建时间
	private Date createDate;
	
	//删除位，0未删除，1已删除
	private Integer deleteFlag;
	
	//删除时间
	private Date deleteDate;
	
	public RolePO(){
		
	}
	
	public RolePO(String name,String description,Date createDate,Integer deleteFlag){
		this.name = name;
		this.description = description;
		this.createDate = createDate;
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

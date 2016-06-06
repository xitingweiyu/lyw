package com.wonders.bigdata.manageplatform.service.catalog.model.po;


import java.util.Date;

/**
 * 大数据平台目录
 */
public class CatalogPO {
	//Field
	//主键
	private Long id;
	
	//父节点id
	private Long parentId;
	
	//名称
	private String name;
	
	//编码
	private String decode;
	
	//类型
	private String type;
	
	//描述
	private String description;
	
	//创建时间
	private Date createDate;
	
	//删除位，0：正常，1：已删除
	private int deleteFlag;
	
	private Integer openLevel;//开放等级 0开放, 1不开放
	
	private Integer status;//，0：未提交审核，1：已提交审核，2：审核通过，3：审核未通过

	private String result; //审核通过或者不同过时的原因
	
	private String applyContext;

	/** default constructor */
	public CatalogPO(){
	}

	public Long getId() {
		return id;
	}


	public CatalogPO(Long id, Long parentId, String name, String decode, String type, String description,
					 Date createDate, int deleteFlag, Integer openLevel, Integer status) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.decode = decode;
		this.type = type;
		this.description = description;
		this.createDate = createDate;
		this.deleteFlag = deleteFlag;
		this.openLevel = openLevel;
		this.status = status;

	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getParentId() {
		return parentId;
	}	

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDecode() {
		return decode;
	}

	public void setDecode(String decode) {
		this.decode = decode;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getOpenLevel() {
		return openLevel;
	}

	public void setOpenLevel(Integer openLevel) {
		this.openLevel = openLevel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getApplyContext() {
		return applyContext;
	}

	public void setApplyContext(String applyContext) {
		this.applyContext = applyContext;
	}
}

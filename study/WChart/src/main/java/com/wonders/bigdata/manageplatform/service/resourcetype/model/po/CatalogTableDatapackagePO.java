package com.wonders.bigdata.manageplatform.service.resourcetype.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据目录标准关联PO
 * 
 * @author xuehan
 * @date 2015年8月25日 下午9:11:12
 */

@Entity
@Table(name = "bd_catalog_tableDatapackage")
public class  CatalogTableDatapackagePO {
	
	//主键
	private Long id;
	//标准目录id
	private Long catalogId;
	//表或者数据id
	private Long tablePackageId;
	//类型，0：表，1：数据包
	private Integer type;
	//数据包或者表名和备注，格式为：备注（表名)，如果没有备注，则直接是表名
	private String tableDatapackageName;
	//创建时间
	private Date createDate;
	//删除位，0：正常，1：已删除
	private Integer deleteFlag;
	//开放等级 0：开放,1：不开放
	private Integer tableOpenLevel;


	public CatalogTableDatapackagePO() {
	}

	public CatalogTableDatapackagePO(Long catalogId, Long tablePackageId,
			Integer type, String tableDatapackageName, Date createDate,
			Integer deleteFlag,Integer tableOpenLevel) {
		super();
		this.catalogId = catalogId;
		this.tablePackageId = tablePackageId;
		this.type = type;
		this.tableDatapackageName = tableDatapackageName;
		this.createDate = createDate;
		this.deleteFlag = deleteFlag;
		this.tableOpenLevel = tableOpenLevel;
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

	@Column(name = "catalog_id")
	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	@Column(name = "tablepackage_id")
	public Long getTablePackageId() {
		return tablePackageId;
	}

	public void setTablePackageId(Long tablePackageId) {
		this.tablePackageId = tablePackageId;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "tabledatapackage_name")
	public String getTableDatapackageName() {
		return tableDatapackageName;
	}

	public void setTableDatapackageName(String tableDatapackageName) {
		this.tableDatapackageName = tableDatapackageName;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "table_open_level")
	public Integer getTableOpenLevel() {
		return tableOpenLevel;
	}
	
	public void setTableOpenLevel(Integer tableOpenLevel) {
		this.tableOpenLevel = tableOpenLevel;
	}
	@Override
	public String toString() {
		return id + ", catalogId= " + catalogId + ", tablePackageId="
				+ tablePackageId + ", type" + type + ", tableDatapackageName="
				+ tableDatapackageName + ", createData=" + createDate + ", deleteFlag=" + deleteFlag + ",tableOpenLevel="+ tableOpenLevel;
	}

}

package com.wonders.bigdata.manageplatform.service.resourcetype.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 数据目录标准关联PO
 * 
 * @author xuehan
 * @date 2015年8月25日 下午9:11:12
 */

@Entity
@Table(name = "bd_catalog_table")
public class CatalogTablePO {

	// 主键
	private Long id;

	private Long catalogId;// 标准目录id

	private Long tableId;// 表id

	private Integer type;// 类型，0：表，1：数据包

	private String tableName;// 表名和备注，格式为：备注（表名)，如果没有备注，则直接是表名

	private Date createDate;// 创建时间

	private Integer deleteFlag;// 删除位，0：正常，1：已删除

	private Integer dataGrade;// 数据等级

	private String conditions;// sql语句中where条件,以sql语句形式组织

	private String remark;// 备注

	private String description;// 描述

	private Integer status;
	
	private Long nums; //数据表的记录总数
	
    private Float space; //占用空间
    
	private String example; //预览数据，保存时时应该保存html标签形式

	private String result; //审核结果描述

	private String applyContext;//

	public CatalogTablePO() {
	}

	public CatalogTablePO(Long catalogId, Long tableId, Integer type, String tableName,
						  Date createDate, Integer deleteFlag, Integer dataGrade, String conditions, String remark,
						  String description, Integer status, String example) {
		super();
		this.catalogId = catalogId;
		this.tableId = tableId;
		this.type = type;
		this.tableName = tableName;
		this.createDate = createDate;
		this.deleteFlag = deleteFlag;
		this.dataGrade = dataGrade;
		this.conditions = conditions;
		this.remark = remark;
		this.description = description;
		this.status = status;
		this.example = example;
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

	@Column(name = "table_id")
	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "table_name")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	@Column(name = "data_grade")
	public Integer getDataGrade() {
		return dataGrade;
	}

	public void setDataGrade(Integer tableOpenLevel) {
		this.dataGrade = tableOpenLevel;
	}
	
	@Column(name = "conditions")
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "example")
	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}
	
	@Column(name = "nums")
	public Long getNums() {
		return nums;
	}

	public void setNums(Long nums) {
		this.nums = nums;
	}
	
	@Column(name = "space")
	public Float getSpace() {
		return space;
	}

	public void setSpace(Float space) {
		this.space = space;
	}

	@Column(name = "result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "apply_context")
	public String getApplyContext() {
		return applyContext;
	}

	public void setApplyContext(String applyContext) {
		this.applyContext = applyContext;
	}

	@Override
	public String toString() {
		return id + ", catalogId= " + catalogId + ", tablePackageId=" + tableId + ", type" + type
				+ ", tableDatapackageName=" + tableName + ", createData=" + createDate + ", deleteFlag="
				+ deleteFlag + ",dataGrade=" + dataGrade;
	}

	

}

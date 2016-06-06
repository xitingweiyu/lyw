package com.wonders.bigdata.manageplatform.service.catalog.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 数据目录标准关联PO
 *
 * @author xuehan
 * @date 2015年8月25日 下午9:11:12
 */

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDataGrade() {
        return dataGrade;
    }

    public void setDataGrade(Integer tableOpenLevel) {
        this.dataGrade = tableOpenLevel;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Long getNums() {
        return nums;
    }

    public void setNums(Long nums) {
        this.nums = nums;
    }

    public Float getSpace() {
        return space;
    }

    public void setSpace(Float space) {
        this.space = space;
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

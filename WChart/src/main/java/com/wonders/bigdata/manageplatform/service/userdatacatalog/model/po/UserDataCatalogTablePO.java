package com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by XCL on 2016/3/10.
 */
@Entity
@Table(name = "bd_userdatacatalog_table")
public class UserDataCatalogTablePO {
    private Long id; //主键
    private String tableName;//名称
    private Long userdatacatalogId;//租户资产目录id
    private String tableDescription;//表描述
    private Date createDate;//创建时间
    private Integer deleteFlag;//删除位，0：未删除，1：已删除
    private Long dpId;//用户id
    private String tableRemark;//表备注
    private Long tableId;//元数据表id
    private Long nums;//表的数据量
    private Float space;//表所占空间
    private String example;//预览数据
    private Integer createStatus;//创建状态，0:创建中，1：创建成功，2：创建失败
    private Integer dataGrade;//数据等级
    private Long userId;//用户id
    private Integer type;//类型，0：上传的表，1：申请下来的表
    private Integer shareStatus; //分享状态，0：未分享，1：待审核，2.审核通过，3审核未通过



    public UserDataCatalogTablePO() {
    }

    public UserDataCatalogTablePO(Long id, String tableName, Long userdatacatalogId, String tableDescription, Date createDate, Integer deleteFlag, Long dpId, String tableRemark, Long tableId, Long nums, Float space, String example, Integer createStatus, Integer dataGrade, Long userId, Integer type) {
        this.id = id;
        this.tableName = tableName;
        this.userdatacatalogId = userdatacatalogId;
        this.tableDescription = tableDescription;
        this.createDate = createDate;
        this.deleteFlag = deleteFlag;
        this.dpId = dpId;
        this.tableRemark = tableRemark;
        this.tableId = tableId;
        this.nums = nums;
        this.space = space;
        this.example = example;
        this.createStatus = createStatus;
        this.dataGrade = dataGrade;
        this.userId = userId;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    @Column(name = "userdatacatalog_id")
    public Long getUserdatacatalogId() {
        return userdatacatalogId;
    }

    @Column(name = "table_description")
    public String getTableDescription() {
        return tableDescription;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    @Column(name = "delete_flag")
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    @Column(name = "dp_id")
    public Long getDpId() {
        return dpId;
    }

    @Column(name = "table_remark")
    public String getTableRemark() {
        return tableRemark;
    }

    @Column(name = "table_id")
    public Long getTableId() {
        return tableId;
    }

    @Column(name = "nums")
    public Long getNums() {
        return nums;
    }

    @Column(name = "space")
    public Float getSpace() {
        return space;
    }

    @Column(name = "example")
    public String getExample() {
        return example;
    }

    @Column(name = "create_status")
    public Integer getCreateStatus() {
        return createStatus;
    }

    @Column(name = "data_grade")
    public Integer getDataGrade() {
        return dataGrade;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }
    @Column(name = "share_status")
    public Integer getShareStatus() {
		return shareStatus;
	}

    public void setId(Long id) {
        this.id = id;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setUserdatacatalogId(Long userdatacatalogId) {
        this.userdatacatalogId = userdatacatalogId;
    }

    public void setTableDescription(String tableDescription) {
        this.tableDescription = tableDescription;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setDpId(Long dpId) {
        this.dpId = dpId;
    }

    public void setTableRemark(String tableRemark) {
        this.tableRemark = tableRemark;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public void setNums(Long nums) {
        this.nums = nums;
    }

    public void setSpace(Float space) {
        this.space = space;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setCreateStatus(Integer createStatus) {
        this.createStatus = createStatus;
    }

    public void setDataGrade(Integer dataGrade) {
        this.dataGrade = dataGrade;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public void setShareStatus(Integer shareStatus) {
		this.shareStatus = shareStatus;
	}
    
    
}

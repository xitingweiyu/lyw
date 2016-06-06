package com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by XCL on 2016/3/10.
 */
@Entity
@Table(name = "bd_userdatacatalog_column")
public class UserDataCatalogColumnPO {
    private Long id; //主键
    private String name;//名称
    private Long userdatacatalogtableId;//父节点id
    private String description;//描述
    private Date createDate;//创建时间
    private Integer deleteFlag;//删除位，0：未删除，1：已删除
    private String typeName;//字段类型
    private String remark;//备注

    public UserDataCatalogColumnPO() {
    }

    public UserDataCatalogColumnPO(Long id, String name, Long userdatacatalogtableId, String description, Date createDate, Integer deleteFlag, String typeName, String remark) {
        this.id = id;
        this.name = name;
        this.userdatacatalogtableId = userdatacatalogtableId;
        this.description = description;
        this.createDate = createDate;
        this.deleteFlag = deleteFlag;
        this.typeName = typeName;
        this.remark = remark;
    }
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }
    @Column(name = "userdatacatalogtable_id")
    public Long getUserdatacatalogtableId() {
        return userdatacatalogtableId;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }
    @Column(name = "delete_flag")
    public Integer getDeleteFlag() {
        return deleteFlag;
    }
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserdatacatalogtableId(Long userdatacatalogtableId) {
        this.userdatacatalogtableId = userdatacatalogtableId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

package com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by wyp on 2016/3/10.
 */
@Entity
@Table(name = "bd_userdata_catalog")
public class UserDataCatalogPO {
    private Long id; //主键
    private String name;//名称
    private Long parentId;//父节点id
    private String description;//描述
    private Date createDate;//创建时间
    private Integer deleteFlag;//删除位，0：未删除，1：已删除
    private Long userId;//用户id
    private String remark;//备注


    public UserDataCatalogPO() {
    }

    public UserDataCatalogPO(Long parentId, String name, Long id, String description, Date createDate, String remark, Long userId, Integer deleteFlag) {
        this.parentId = parentId;
        this.name = name;
        this.id = id;
        this.description = description;
        this.createDate = createDate;
        this.remark = remark;
        this.userId = userId;
        this.deleteFlag = deleteFlag;
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
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
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
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {this.name = name;}

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

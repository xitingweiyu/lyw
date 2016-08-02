package com.wonders.bigdata.manageplatform.service.wchart.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>  Created by LXL on 2016/5/10  </p>
 */
@Entity
@Table(name = "bd_chart_type")
public class ChartTypePO {
    private Long id; //主键
    private String mainType;//类型编号
    private String secondType;//标题
    private String description;//描述
    private String elements;//维度
    private Integer elementNum;//维度数
    private Date createDate;//创建时间
    private Date updateDate;//更新时间
    private Integer deleteFlag;//删除位，0：未删除，1：已删除

    public ChartTypePO(Long id, String mainType, String secondType, String description, String elements,Integer elementNum,Date createDate, Date updateDate, Integer deleteFlag) {
        this.id = id;
        this.mainType = mainType;
        this.secondType = secondType;
        this.description = description;
        this.elements = elements;
        this.elementNum = elementNum;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteFlag = deleteFlag;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "main_type")
    public String getMainType() {
        return mainType;
    }

    @Column(name = "second_type")
    public String getSecondType() {
        return secondType;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "elements")
    public String getElements() {
        return elements;
    }

    @Column(name = "element_num")
    public Integer getElementNum() {
        return elementNum;
    }

    @Column(name = "creat_date")
    public Date getCreateDate() {
        return createDate;
    }

    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    @Column(name = "delete_flag")
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }

    public void setElementNum(Integer elementNum) {
        this.elementNum = elementNum;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}

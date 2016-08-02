package com.wonders.bigdata.manageplatform.service.wchart.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>  Created by LXL on 2016/5/10  </p>
 */
@Entity
@Table(name = "bd_chart_box")
public class ChartBoxPO {
    private Long id; //主键
    private Long userId; //用户Id
    private Long chartTypeId;//类型编号
    private String title;//标题
    private String description;//描述
    private String elements;//维度
    private Date createDate;//创建时间
    private Date updateDate;//更新时间
    private Integer deleteFlag;//删除位，0：未删除，1：已删除

    public ChartBoxPO() {
    }

    public ChartBoxPO(Long id, Long userId, Long chartTypeId, String title, String description, String elements, Date createDate, Date updateDate, Integer deleteFlag) {
        this.id = id;
        this.userId = userId;
        this.chartTypeId = chartTypeId;
        this.title = title;
        this.description = description;
        this.elements = elements;
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

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "chart_type_id")
    public Long getChartTypeId() {
        return chartTypeId;
    }

    public void setChartTypeId(Long chartTypeId) {
        this.chartTypeId = chartTypeId;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "elements")
    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }

    @Column(name = "creat_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "delete_flag")
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}

package com.wonders.bigdata.manageplatform.web.wchart.vo;

/**
 * <p>  Created by LXL on 2016/5/6  </p>
 */
public class WChartDrawNodeVO {
    public final static String VIRTUAL_ROOT = "-1"; // 虚拟根节点

    private String  parentId; 	 // 父节点id
    private String  id; 		 // 节点id
    private String  name; 		 // 节点名称
    private String  realName; 	 // 原名
    private Long    realId;
    private boolean isParent;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getRealId() {
        return realId;
    }

    public void setRealId(Long realId) {
        this.realId = realId;
    }
}

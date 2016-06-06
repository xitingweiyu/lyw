package com.wonders.bigdata.manageplatform.web.dataquality.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xh on 2016/4/25.
 */
public class DataQualityVO {
    private Long id;
    private String tableName;
    private String columnName;
    private String columnType;
    private Map<String, String> result;

    public DataQualityVO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}

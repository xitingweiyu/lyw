package com.wonders.bigdata.manageplatform.service.wchart.service;

import java.util.List;

/**
 * <p>  Created by LXL on 2016/5/9  </p>
 */
public interface WChartService {

    /**
     * Created by LXL on 2016/5/9
     * <br> 根据表ID和字段ID从Hive中获取相应的字段的数据
     * @param tableId 表ID
     * @param columnId 字段ID
     * @return 获取的数据
     */
    public List<String> getDataFromHiveByTableIdAndColumnId(Long tableId, Long columnId);

}

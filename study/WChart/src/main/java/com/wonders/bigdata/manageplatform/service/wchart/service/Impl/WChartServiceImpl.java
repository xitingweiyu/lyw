package com.wonders.bigdata.manageplatform.service.wchart.service.Impl;

import com.wonders.bigdata.manageplatform.service.common.service.HiveService;
import com.wonders.bigdata.manageplatform.service.common.service.JDBCService;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogColumnPO;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogTablePO;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.service.UserDataCatalogColumnService;
import com.wonders.bigdata.manageplatform.service.userdatacatalog.service.UserDataCatalogTableService;
import com.wonders.bigdata.manageplatform.service.wchart.service.WChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>  Created by LXL on 2016/5/9  </p>
 */
@Service("WChartDrawServiceImpl")
public class WChartServiceImpl implements WChartService {

    @Autowired
    private JDBCService jdbcService;

    @Autowired
    private HiveService hiveService;

    @Autowired
    private UserDataCatalogTableService userDataCatalogTableService;

    @Autowired
    private UserDataCatalogColumnService userDataCatalogColumnService;

    /**
     * Created by LXL on 2016/5/9
     * <br> 根据表ID和字段ID从Hive中获取相应的字段的数据
     * @param tableId  表ID
     * @param columnId 字段ID
     * @return 获取的数据
     */
    @Override
    public List<String> getDataFromHiveByTableIdAndColumnId(Long tableId, Long columnId) {
        UserDataCatalogTablePO userDataCatalogTablePO = userDataCatalogTableService.findById(tableId);
        UserDataCatalogColumnPO userDataCatalogColumnPO = userDataCatalogColumnService.findById(columnId);
        List<String> returnResult = hiveService.getColumnValues(userDataCatalogTablePO.getTableName(),userDataCatalogColumnPO.getName());
        return returnResult;
    }
}

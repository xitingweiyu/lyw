package com.wonders.bigdata.manageplatform.service.wchart.service;

import com.wonders.bigdata.manageplatform.service.userdatacatalog.model.po.UserDataCatalogPO;
import com.wonders.bigdata.manageplatform.service.wchart.model.ChartBoxPO;

/**
 * <p>  Created by LXL on 2016/5/10  </p>
 */
public interface ChartBoxService {
    /**
     * Created by LXL on 2016/5/10
     * <br> 新增
     * @param chartBoxPO PO
     * @return 新增结果
     */
    public ChartBoxPO save(ChartBoxPO chartBoxPO);

    /**
     * Created by LXL on 2016/5/10
     * <br> 更新
     * @param chartBoxPO PO
     * @return 更新结果
     */
    public ChartBoxPO update(ChartBoxPO chartBoxPO);

    /**
     * Created by LXL on 2016/5/10
     * <br> 根据ID查询
     * @param id id
     * @return PO
     */
    public ChartBoxPO findById(long id);
}

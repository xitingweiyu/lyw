package com.wonders.bigdata.manageplatform.service.wchart.service;

import com.wonders.bigdata.manageplatform.service.wchart.model.ChartTypePO;
/**
 * Created by hyf on 2016/5/10.
 */
public interface ChartTypeService{
    /**
     * Created by LXL on 2016/5/10
     * <br> 新增
     * @param chartTypePO PO
     * @return 新增结果
     */
    public ChartTypePO save(ChartTypePO chartTypePO);

    /**
     * Created by LXL on 2016/5/10
     * <br> 更新
     * @param chartTypePO PO
     * @return 更新结果
     */
    public ChartTypePO update(ChartTypePO chartTypePO);

    /**
     * Created by LXL on 2016/5/10
     * <br> 根据ID查询
     * @param id id
     * @return PO
     */
    public ChartTypePO findById(long id);
}

package com.wonders.bigdata.manageplatform.service.wchart.service.Impl;

import com.wonders.bigdata.manageplatform.service.wchart.dao.ChartTypeDao;
import com.wonders.bigdata.manageplatform.service.wchart.model.ChartTypePO;
import com.wonders.bigdata.manageplatform.service.wchart.service.ChartTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by hyf on 2016/5/10.
 */
public class ChartTypeServiceImpl implements ChartTypeService{

    @Resource(name = "ChartTypeDaoImpl")
    private ChartTypeDao chartTypeDao;

    /**
     * Created by LXL on 2016/5/10
     * <br> 新增
     *
     * @param chartTypePO PO
     *
     * @return 新增结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ChartTypePO save(ChartTypePO chartTypePO) {
        return chartTypeDao.save(chartTypePO);
    }

    /**
     * Created by LXL on 2016/5/10
     * <br> 更新
     *
     * @param chartTypePO PO
     *
     * @return 更新结果
     */
    @Override
    public ChartTypePO update(ChartTypePO chartTypePO) {
        return chartTypeDao.save(chartTypePO);
    }

    /**
     * Created by LXL on 2016/5/10
     * <br> 根据ID查询
     *
     * @param id id
     *
     * @return PO
     */
    @Override
    public ChartTypePO findById(long id) {
        return chartTypeDao.get(id);
    }
}

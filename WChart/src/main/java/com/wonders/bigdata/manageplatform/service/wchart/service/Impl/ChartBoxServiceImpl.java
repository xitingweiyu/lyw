package com.wonders.bigdata.manageplatform.service.wchart.service.Impl;

import com.wonders.bigdata.manageplatform.service.wchart.dao.ChartBoxDao;
import com.wonders.bigdata.manageplatform.service.wchart.model.ChartBoxPO;
import com.wonders.bigdata.manageplatform.service.wchart.service.ChartBoxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>  Created by LXL on 2016/5/10  </p>
 */
@Service("chartBoxServiceImpl")
public class ChartBoxServiceImpl implements ChartBoxService {

    @Resource(name = "chartBoxDaoImpl")
    private ChartBoxDao chartBoxDao;

    /**
     * Created by LXL on 2016/5/10
     * <br> 新增
     *
     * @param chartBoxPO PO
     *
     * @return 新增结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ChartBoxPO save(ChartBoxPO chartBoxPO) {
        return chartBoxDao.save(chartBoxPO);
    }

    /**
     * Created by LXL on 2016/5/10
     * <br> 更新
     *
     * @param chartBoxPO PO
     *
     * @return 更新结果
     */
    @Override
    public ChartBoxPO update(ChartBoxPO chartBoxPO) {
        return chartBoxDao.save(chartBoxPO);
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
    public ChartBoxPO findById(long id) {
        return chartBoxDao.get(id);
    }


}

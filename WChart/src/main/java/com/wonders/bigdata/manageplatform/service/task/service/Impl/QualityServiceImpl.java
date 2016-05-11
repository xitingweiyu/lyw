package com.wonders.bigdata.manageplatform.service.task.service.Impl;

import com.wonders.bigdata.manageplatform.service.task.dao.QualityDao;
import com.wonders.bigdata.manageplatform.service.task.model.po.QualityPO;
import com.wonders.bigdata.manageplatform.service.task.service.QualityService;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
@Service("QualityServiceImpl")
public class  QualityServiceImpl implements QualityService {

    @Resource(name = "QualityDaoImpl")
    private QualityDao qualityDao;

    @Override
    public QualityPO save(QualityPO po) {
        qualityDao.save(po);
        return po;
    }

    @Override
    public Page<QualityPO> findByPage(Page<QualityPO> page) {
        return null;
    }

    @Override
    public List<QualityPO> findQualityByList(QueryParam param) {
        return null;
    }

    @Override
    public QualityPO getById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}

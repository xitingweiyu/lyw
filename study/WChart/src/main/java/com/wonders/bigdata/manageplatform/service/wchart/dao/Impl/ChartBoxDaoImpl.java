package com.wonders.bigdata.manageplatform.service.wchart.dao.Impl;

import com.wonders.bigdata.manageplatform.service.wchart.dao.ChartBoxDao;
import com.wonders.bigdata.manageplatform.service.wchart.model.ChartBoxPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by hyf on 2016/5/10.
 */
@Repository("chartBoxDaoImpl")
public class ChartBoxDaoImpl extends HibernateBaseDaoImpl<ChartBoxPO, Long> implements ChartBoxDao {
}

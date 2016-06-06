package com.wonders.bigdata.manageplatform.service.wchart.dao.Impl;

import com.wonders.bigdata.manageplatform.service.wchart.dao.ChartTypeDao;
import com.wonders.bigdata.manageplatform.service.wchart.model.ChartTypePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import org.springframework.stereotype.Repository;
/**
 * Created by hyf on 2016/5/10.
 */
@Repository("chartTypeDaoImpl")
public class ChartTypeDaoImpl extends HibernateBaseDaoImpl<ChartTypePO, Long> implements ChartTypeDao {
}
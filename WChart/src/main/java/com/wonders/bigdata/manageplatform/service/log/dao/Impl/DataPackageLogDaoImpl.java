package com.wonders.bigdata.manageplatform.service.log.dao.Impl;

import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.log.dao.DataPackageLogDao;
import com.wonders.bigdata.manageplatform.service.log.model.po.DataPackageLogPO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;

/**
 * <p>
 * Title: manageplatform_[管理员]_[数据包日志]
 * </p>
 * <p>
 * Description: [数据包日志dao实现层]
 * </p>
 * 
 * @author GLJ
 * @version $Revision$ 2015-03-17
 * @author (latest modification by $Author$)
 * @since 20130601
 */
@Repository("dataPackageLogDaoImpl")
public class  DataPackageLogDaoImpl extends HibernateBaseDaoImpl<DataPackageLogPO, Long> implements DataPackageLogDao {

}

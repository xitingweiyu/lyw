package com.wonders.bigdata.manageplatform.service.dataresource.dao.Impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.wonders.bigdata.manageplatform.service.dataresource.dao.DataResourceDao;
import com.wonders.bigdata.manageplatform.service.dataresource.model.po.DataResourcePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDaoImpl;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * <p>
 * Title: manageplatform_[用户管理平台]_[数据源]
 * </p>
 * <p>
 * Description: [数据源dao接口实现类]
 * </p>
 * 
 * @author WF
 * @version $Revision$ 2013-3-16
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
@Repository("dataResourceDaoImpl")
public class  DataResourceDaoImpl extends HibernateBaseDaoImpl<DataResourcePO, Long> implements DataResourceDao {

	@Override
	public Long countAll(QueryParam param) {
		Criteria c = createCriteria();
		c = param.andCriteria(c);
		c.setProjection(null);
		// 获取总条数
		Long totalCount = ((Long) c.setProjection(Projections.rowCount()).uniqueResult()).longValue();
		return totalCount;
	}

}

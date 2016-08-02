package com.wonders.bigdata.manageplatform.service.dataresource.dao;

import com.wonders.bigdata.manageplatform.service.dataresource.model.po.DataResourcePO;
import com.wonders.bud.framework.common.hibernate.HibernateBaseDao;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * <p>
 * Title: manageplatform_[用户管理平台]_[数据源]
 * </p>
 * <p>
 * Description: [数据源dao接口]
 * </p>
 * 
 * @author WF
 * @version $Revision$ 2015-3-16
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
public interface  DataResourceDao extends HibernateBaseDao<DataResourcePO, Long> {

	/**
	 * <p>
	 * Description: [统计所有数据源]
	 * </p>
	 */
	public Long countAll(QueryParam param);
}

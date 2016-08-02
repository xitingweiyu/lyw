package com.wonders.bud.framework.common.hibernate;



import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryBaseParam;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * hibernate DAO基类
 * 
 * 提供hql分页查询，拷贝更新等一些常用功能。
 * 
 * @author dy
 * 
 * @param <T>
 * @param <ID>
 */
public class HibernateTempDaoImpl implements HibernateTempDao{
	protected SessionFactory sessionFactory;
	/**
	 * 日志，可用于子类
	 */
	protected Logger log = Logger.getLogger(HibernateTempDaoImpl.class);
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		Session session = sessionFactory.getCurrentSession();
        if(null==session || false==session.isOpen()){  
            session = sessionFactory.openSession();
        } 
        return session;  
	}

	@Override
	public <T> T save(T po) {
		getSession().saveOrUpdate(po);
		return po;
	}
	
	@Override
	public <T> T update(T po) {
		getSession().merge(po);
		return po;
	}
	
	@Override
	public <T> void delete(T obj) {
		getSession().delete(obj);
	}

	@Override
	public <T> void deleteById(Object id, Class<?> clazz) {
		delete(get(id, clazz));
	}


	/**
	 * @see Session.get(Class,Serializable)
	 * @param id
	 * @return 持久化对象
	 */
	@Override
	public <T> T get(Object id ,Class<?> clazz) {
		return get(id, clazz, false);
	}

	/**
	 * @see Session.get(Class,Serializable,LockMode)
	 * @param id
	 *            对象ID
	 * @param lock
	 *            是否锁定，使用LockOptions.UPGRADE
	 * @return 持久化对象
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Object id ,Class<?> clazz, boolean lock) {
		T entity;
		if (lock) {
			entity = (T) getSession().get(clazz, (Serializable)id,LockOptions.UPGRADE);
		} else {
			entity = (T) getSession().get(clazz, (Serializable)id);
		}
		return entity;
	}

	/**
	 * 通过HQL查询对象列表
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            数量可变的参数
	 */
	@Override
	public List<?> find(String hql, Object[] values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 通过HQL查询唯一对象
	 */
	@Override
	public Object findUnique(String hql, Object[] values, Class<?> clazz) {
		return createQuery(hql, clazz, values).uniqueResult();
	}
	
	/**
	 * 按属性查找对象列表
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(String property, Object value, Class<?> clazz) {
		Assert.hasText(property);
		List<T> result = createCriteria(clazz, Restrictions.eq(property, value)).list();
		
		return result;
	}

	/**
	 * 按属性查找唯一对象
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T findUniqueByProperty(String property, Object value, Class<?> clazz) {
		Assert.hasText(property);
		Assert.notNull(value);
		return (T) createCriteria(clazz, Restrictions.eq(property, value))
				.uniqueResult();
	}

	/**
	 * 按属性统计记录数
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public int countByProperty(String property, Object value, Class<?> clazz) {
		Assert.hasText(property);
		Assert.notNull(value);
		return ((Number) (createCriteria(clazz, Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}

	/**
	 * 按Criterion查询列表数据.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	public List<?> findByCriteria(Class<?> clazz, Criterion... criterion) {
		return createCriteria(clazz, criterion).list();
	}

	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 */
	public Criteria createCriteria(Class<?> clazz, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(clazz);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		List<T> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findBy(Map<String, Object> paramMap, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		for (Iterator<String> i = paramMap.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (filterProperty(name, clazz)) {
				criteria.add(Restrictions.eq(name, paramMap.get(name)));
			} else {
				log.error("Could not resolve property:" + name);
			}
		}
		List<T> result = criteria.list();
		return result;
	}

	private boolean filterProperty(String name, Class<?> clazz) {
		try {
			BeanWrapper bw = new BeanWrapperImpl(clazz.newInstance());
			bw.getPropertyValue(name);
		} catch (Exception e) {
			log.error("Could not resolve property:" + name, e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByLike(Map<String, String> paramMap, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		for (Iterator<String> iter = paramMap.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			if (filterProperty(name, clazz)) {
				criteria.add(Restrictions.like(name,paramMap.get(name), MatchMode.ANYWHERE));
			} else {
				log.error("Could not resolve property:" + name);
			}
		}
		List<T> result = criteria.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Page<T> findByPage(Page<T> page, Class<?> clazz) {
		Criteria c = createCriteria(clazz);
		c = page.getParam().andCriteria(c);
		c.setProjection(null);

		// 获取总条数
		int totalCount = ((Long) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		c.setProjection(null);
		c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

		// 设置page
		if (page.getStart() > -1) {
			c.setFirstResult(page.getStart());
		}
		if (page.getPagesize() > -1) {
			c.setMaxResults(page.getPagesize());
		}
		List<T> result = c.list();
		page.setResult(result);
		page.setTotal(totalCount);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByAnd(QueryBaseParam param, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		criteria = param.andCriteria(criteria);
		List<T> result = criteria.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByAnd(QueryParam param, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		criteria = param.andCriteria(criteria);
		List<T> result = criteria.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByOr(QueryBaseParam param, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		criteria = param.orCriteria(criteria);
		List<T> result = criteria.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByOr(QueryParam param, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		criteria = param.orCriteria(criteria);
		List<T> result = criteria.list();
		return result;
	}

	@Override
	public boolean duplicatecheck(String idProperty, Object id, String property, Object value, Class<?> clazz) {
		if(null == id){
			List<?> list = createCriteria(clazz, Restrictions.eq(property, value)).list();
			if(list != null && list.size()>0) {
				return true;
			}
		}else{
			List<?> list = createCriteria(clazz, Restrictions.eq(property, value)).add(Restrictions.ne(idProperty, id)).list();
			if(list != null && list.size()>0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object findMax(String max, Map<String, Object> Map, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		for (Iterator<String> i = Map.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (filterProperty(name, clazz)) {
				criteria.add(Restrictions.eq(name, Map.get(name)));
			} else {
				log.error("Could not resolve property:" + name);
			}
		}
		return criteria.setProjection(Projections.max(max)).uniqueResult();
	}
	
	@Override
	public Object findMin(String min, Map<String, Object> Map, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		for (Iterator<String> i = Map.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (filterProperty(name, clazz)) {
				criteria.add(Restrictions.eq(name, Map.get(name)));
			} else {
				log.error("Could not resolve property:" + name);
			}
		}
		return criteria.setProjection(Projections.min(min)).uniqueResult();
	}

	@Override
	public Object findAvg(String avg, Map<String, Object> Map, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		for (Iterator<String> i = Map.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (filterProperty(name, clazz)) {
				criteria.add(Restrictions.eq(name, Map.get(name)));
			} else {
				log.error("Could not resolve property:" + name);
			}
		}
		return criteria.setProjection(Projections.avg(avg)).uniqueResult();
	}

	@Override
	public Object findSum(String sum, Map<String, Object> Map, Class<?> clazz) {
		Criteria criteria = createCriteria(clazz);
		for (Iterator<String> i = Map.keySet().iterator(); i.hasNext();) {
			String name = i.next();
			if (filterProperty(name, clazz)) {
				criteria.add(Restrictions.eq(name, Map.get(name)));
			} else {
				log.error("Could not resolve property:" + name);
			}
		}
		return criteria.setProjection(Projections.sum(sum)).uniqueResult();
	}

	@Override
	public Object[] findStatisticByAnd(QueryParam param, Class<?> clazz) {
		Criteria criteria = param.andCriteria(createCriteria(clazz));
		criteria.setProjection(null);
		criteria.setProjection(param.projectionList());
		Object[] result = (Object[]) criteria.uniqueResult();
		return result;
	}
	
	@Override
	public Object[] findStatisticByOr(QueryParam param, Class<?> clazz) {
		Criteria criteria = param.orCriteria(createCriteria(clazz));
		criteria.setProjection(null);
		criteria.setProjection(param.projectionList());
		Object[] result = (Object[]) criteria.uniqueResult();
		return result;
	}
	
	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处�?辅助函数.
	 */
	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}
}

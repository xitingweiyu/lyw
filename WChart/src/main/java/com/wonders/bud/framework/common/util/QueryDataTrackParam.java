package com.wonders.bud.framework.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wonders.bigdata.manageplatform.utils.Messages;

public class QueryDataTrackParam extends QueryBaseParam {
	private Map<String, String> likes;//like 查询条件<属性, 值列表>
	private Map<String, Object[]> between;// between 查询条件<属性, 值列表>
	private Map<String, Object> eq; //and 查询条件<属性, 值>
	
	/**
	 * <p>获得 likes</p>
	 * @return Map<String,Object[]> likes
	 *
	 */
	public Map<String, String> getLikes() {
		return likes;
	}
	/**
	 * <p>设置 likes</p>
	 * @param Map<String,Object[]> likes
	 */
	public void setLikes(Map<String, String> likes) {
		this.likes = likes;
	}
	/**
	 * <p>获得 between</p>
	 * @return Map<String,Object[]> between
	 *
	 */
	public Map<String, Object[]> getBetween() {
		return between;
	}
	/**
	 * <p>设置 between</p>
	 * @param Map<String,Object[]> between
	 */
	public void setBetween(Map<String, Object[]> between) {
		this.between = between;
	}
	
	/**
	 * <p>获得 and</p>
	 * @return Map<String,Object> and
	 *
	 */
	public Map<String, Object> getEq() {
		return eq;
	}
	/**
	 * <p>设置 and</p>
	 * @param Map<String,Object> and
	 */
	public void setEq(Map<String, Object> eq) {
		this.eq = eq;
	}
	
	/**
	 * 
	 *
	 * <p>Description:构造与查询条件 </p>
	 *
	 * Created by [CSJ] [2015-3-19]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param criteria
	 * @return
	 */
	@Override
	public Criteria andCriteria(Criteria criteria){
		List<Criterion> cList = getCriterions();
		
		//与查询条件组装
		if(null != cList && cList.size()>0){
			for (int i = 0; i < cList.size(); i++) {
				criteria.add(cList.get(i));
			}
		}
		
		//order
		if(null != order){
			for (Map.Entry<String, String> ord : order.entrySet()) {
				String field = ord.getKey();
				String sort = ord.getValue();
				if (Messages.getString("").equalsIgnoreCase(sort)) //$NON-NLS-1$
					criteria.addOrder(Order.desc(field));
				else
					criteria.addOrder(Order.asc(field));
			}
		}
		return criteria;
	}
	
	/**
	 * 
	 *
	 * <p>Description:构造或查询条件 </p>
	 *
	 * Created by [CSJ] [2014-5-14]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param criteria
	 * @return
	 */
	@Override
	public Criteria orCriteria(Criteria criteria){
		List<Criterion> cList = getCriterions();
		
		//或查询条件组装
		if(null != cList && cList.size()>0){
			if(cList.size() <2){
				criteria.add(Restrictions.or(cList.get(0)));
			}else{
				LogicalExpression orExp = Restrictions.or(cList.get(0),cList.get(1));
				for (int i = 2; i < cList.size(); i++) {
					orExp = Restrictions.or(orExp, cList.get(i));
				}
				criteria.add(orExp);
			}
		}
		
		//order
		if(null != order){
			for (Map.Entry<String, String> ord : order.entrySet()) {
				String field = ord.getKey();
				String sort = ord.getValue();
				if ("desc".equalsIgnoreCase(sort)) //$NON-NLS-1$
					criteria.addOrder(Order.desc(field));
				else
					criteria.addOrder(Order.asc(field));
			}
		}
		return criteria;
	}
	
	/**
	 * 
	 *
	 * <p>Description:查询条件 </p>
	 *
	 * Created by [dy] [2014-5-15]
	 * Midified by [修改人] [修改时间]
	 *
	 * @return List<Criterion>
	 */
	private List<Criterion> getCriterions() {
		List<Criterion> cList = new ArrayList<Criterion>(); 
		if(null != likes) {
			Iterator<String> keySet = likes.keySet().iterator();
			if(keySet.hasNext()) {
				String k = keySet.next();
				String[] keys = likes.get(k).split(";");//取出查询关键字，并分割
				String[] newKeys = keys[0].split(" ");
				if(newKeys.length > 0) {
					Criterion c = Restrictions.like(k,  "%" + newKeys[0] + "%");
					for(int i=1; i<newKeys.length; i++) {
						c = Restrictions.or(c, Restrictions.like(k,  "%" + newKeys[i] + "%"));
					}
					
					if(keys.length > 1) {//多次查询
						for(int j=1; j<keys.length; j++) {
							String[] oldKeys = keys[j].split(" ");
							if(oldKeys.length > 0) {
								Criterion tmp = Restrictions.like(k, "%" + oldKeys[0] + "%");
								for(int t=0; t<oldKeys.length; t++ ) {
									tmp = Restrictions.or(tmp, Restrictions.like(k,  "%" + oldKeys[t] + "%"));//某一次查询的条件逻辑或
								}
								c = Restrictions.and(c, tmp);//多次查询的条件相与
							}
						}
					}
					
					cList.add(c);
				}
			}
		}
		
		if(null != between) {
			for (Map.Entry<String, Object[]> entry : between.entrySet()) {
				String key = entry.getKey().toString();
				Object[] value = entry.getValue();
				Criterion c = Restrictions.between(key, value[0], value[1]);
				cList.add(c);
			}
		}
		
		if(null != eq) {
			for (Map.Entry<String, Object> query : eq.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.eq(field, val);
				cList.add(c);
			}
		}
		// <>
		if (null != ne) {
			for (Map.Entry<String, Object> query : ne.entrySet()) {
				String field = query.getKey().toString();
				Object val = query.getValue();
				Criterion c = Restrictions.ne(field, val);
				cList.add(c);
			}
		}
		return cList;
	}
	
	public static QueryDataTrackParam genParam(Map<String, Object> eq,String keyProp, String key, String dateProp, String date) {
		QueryDataTrackParam param = new QueryDataTrackParam();
		param.setEq(eq);
		Map<String, String> likes = new HashMap<String, String>();
		likes.put(keyProp, key);
		param.setLikes(likes);
		if(date != null && !"".equals(date) && !" ".equals(date)) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				Date bgDate = sdf.parse(date + " 00:00:00" );
				Date endDate = sdf.parse(date + " 23:59:59");
				Date[] bet = {bgDate, endDate};
				Map<String, Object[]> between = new HashMap<String, Object[]>();
				between.put(dateProp, bet);
				param.setBetween(between);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return param;
	}
}

package com.wonders.bud.framework.common.page;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wonders.bud.framework.common.util.QueryParam;

/**
 * 分页工具类
 * @author Think
 *
 */

public class PageUtil {
	
	/**
	 * PO分页查询，分页信息设置（排序）
	 * @param page Page分页对象
	 * @param start 从第几条开始
	 * @param row 每页条数
	 * @param sortorder 排序规则，升序还是降序
	 * @param sortfield 排序列
	 * @param defaultField 默认排序列
	 */
	@SuppressWarnings("rawtypes")
	public static void pageSplit(Page page, String start, String row, String sortorder, String sortfield, String defaultField){
		
		//分页信息
		if (StringUtils.isNotBlank(start)){
			page.setStart(Integer.valueOf(start));
		}
		if (StringUtils.isNotBlank(row)){
			page.setPagesize(Integer.valueOf(row));
		}
		
		//排序信息
		Map<String, String> order = new HashMap<String, String>();
		QueryParam param = new QueryParam();
		if(page != null && page.getParam() != null){
			param = page.getParam();
			if(page.getParam().getOrder() != null){
				order = page.getParam().getOrder();
			}
		}
		
		if (StringUtils.isNotBlank(sortfield)){
			if (StringUtils.isNotBlank(sortorder)){
				order.put(sortfield, sortorder);
			}else{
				order.put(sortfield , "desc");
			}
		}else if(StringUtils.isNotBlank(defaultField)){
			if (StringUtils.isNotBlank(sortorder)){
				order.put(defaultField, sortorder);
			}else{
				order.put(defaultField, "desc");
			}
		}
		param.setOrder(order);
		page.setParam(param);
		
	}
	
	/**
	 * <p>
	 * Description:[PO分页查询，分页信息设置（不排序）]
	 * </p>
	 * 
	 * @author Dy
	 * @since 20100901
	 * @param page Page分页对象
	 * @param start 从第几条开始
	 * @param row 每页条数
	 */
	@SuppressWarnings("rawtypes")
	public static void pageSplit(Page page, String start, String row){
		
		//分页信息
		if (StringUtils.isNotBlank(start)){
			page.setStart(Integer.valueOf(start));
		}else{
			page.setStart(0);
		}
		if (StringUtils.isNotBlank(row)){
			page.setPagesize(Integer.valueOf(row));
		}else{
			page.setPagesize(10);
		}
		
	}
	
	/**
	 * 将Page对象的分页信息设置到PageVO中
	 * @param page
	 * @param pageVo
	 */
	@SuppressWarnings("rawtypes")
	public static void pageVOSplit(Page page, PageVO pageVo){
		if (page.getPagesize() > 0) {
			pageVo.setPage(page.getStart() / page.getPagesize() + 1);
			if(page.getTotal()%page.getPagesize()==0){
				pageVo.setTotalPages(page.getTotal()/page.getPagesize());
			}else{
				pageVo.setTotalPages(page.getTotal()/page.getPagesize()+1);
			}		
		} else {
			pageVo.setPage(1); 
			pageVo.setTotalPages(1);
		}
		pageVo.setRow(page.getPagesize());
		pageVo.setTotal(page.getTotal());
	}

}

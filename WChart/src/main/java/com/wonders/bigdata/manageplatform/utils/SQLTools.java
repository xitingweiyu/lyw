/** 
* 
 * Copyright (c) 1995-2012 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wonders.bigdata.manageplatform.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>
 * Title: manageplatform_[大数据管理平台]_[SQL]
 * </p>
 * <p>
 * Description: [SQL处理函数合集]
 * </p>
 * 
 * @author GLJ
 * @version $Revision$ 2015-03-24
 * @author (latest modification by $Author$)
 * @since 20100901
 */
public class  SQLTools {
	
	//解析sql
	public static List<Map<String, String>> reSqMap(String sql){
		List<Map<String, String>> maps = new ArrayList<Map<String,String>>();
		if(!sql.equals("")&&sql.contains("tables")){
			JSONObject sqlJsonObject;
			try {
				sqlJsonObject = new JSONObject(sql);
				JSONArray tablesArray = sqlJsonObject.getJSONArray("tables");
				if(tablesArray!=null&&tablesArray.length()>0){
					for(int i=0;i<tablesArray.length();i++){
						JSONObject tableSql = tablesArray.getJSONObject(i);
						String selectString = tableSql.getString("sql");
						String tableName = tableSql.getString("name");
						Map<String, String> map = reSql(selectString);
						map.put("tableName", tableName);
						maps.add(map);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
			
		return maps;
	}

	/**
	 * 解析sql 只能解析一些常规的sql语句
	 * @param sql
	 * @return 返回的是map，key=tables时，value是sql中包含的所有表名，表之间使用“，"(英文逗号),key=tableColumns时，value是sql语句中字段对应的表名；key=conditions时，value是where后面的条件
	 * 例如：sql为"select * from test   t,yes   y"  解析之后是"{tableColumns=*, tables=test,yes,conditions=}"
	 * 如果sql为"select  (q.id+q.tableid) irf,q.visitor_firstname,v.company  from qcore_info q  join visitor_info v left join r on q.id=v.id where q.id>100 and q.id<300 or v.id<300" 
	 * 解析之后是"{conditions=qcore_info.id>100 and qcore_info.id<300 or visitor_info.id<300, tableColumns=irf|qcore_info.id+qcore_info.tableid;visitor_firstname|qcore_info.visitor_firstname;company|visitor_info.company, 
	 * tables=qcore_info,visitor_info,r}"
	 */
	public static Map<String,String> reSql(String sql){
		Map<String, String> map = new HashMap<String, String>();
		if(sql == null || sql.equals(""))
			return map;
		
		sql = sql.toLowerCase();
		sql = sql.replace("\n", "");
		sql = sql.replaceAll(" +", " ");//将多个空格转换为一个空格
		//根据from进行划分
		String[] froms = sql.split("from");
		if(froms !=null && froms.length>1){
			String colunmsString = froms[0].replace("select", "").trim();
			String fromAfterString = froms[1];
			String tables = "";
			String[] tableArray=null;
			String[] tableAndCondition = fromAfterString.split("where");
			String afterWhereString = ""; //where后面的字符串
			if(tableAndCondition.length>1){
				afterWhereString = tableAndCondition[1].trim();
				
			}
			String tableString = tableAndCondition[0];
			if(!tableString.contains("join")){
				tableArray = tableString.split(",");
			}else if(tableString.contains("join")){
				String[] joinTableArray = tableString.split("join");
				tableArray = new String[joinTableArray.length];
				
				/*
				 * 处理join前面的表
				 */
				for(int p=0;p<joinTableArray.length-1;p++){
					String preJoinTableString = joinTableArray[p];
					//处理right join的情况
					if(preJoinTableString.contains("right")){
						tableArray[p] = preJoinTableString.split("right")[0];
					}else if(preJoinTableString.contains("inner")){//处理内连接的情况
						tableArray[p] = preJoinTableString.split("inner")[0];
					}else if(preJoinTableString.contains("left")){//处理left连接的情况
						tableArray[p] = preJoinTableString.split("left")[0];
					}else{//默认连接
						tableArray[p] = preJoinTableString;
					}
				}
				
				/**
				 * 处理join后面的表
				 */
				String afterJoinTablString = joinTableArray[joinTableArray.length-1];
				if(afterJoinTablString.contains("on")){
					tableArray[joinTableArray.length-1] = afterJoinTablString.split("on")[0];
				}else{
					tableArray[joinTableArray.length-1] = afterJoinTablString;
				}
				
			}
			if(tableArray!=null&&tableArray.length>0){
				for(int t=0;t<tableArray.length;t++){
					String tab = tableArray[t];
					tab = tab.trim();
					if(tab.contains(" ")&&!tab.contains(" as ")){
						String tableName = tab.split(" ")[0].trim();//获取表名
						String rname = tab.split(" ")[1].trim();//获取别名
						if(t == 0){
							tables = tables + tableName;
						}else{
							tables = tables + "," + tableName;
						}
						
						colunmsString = colunmsString.replace(rname+".", tableName+".");
						afterWhereString = afterWhereString.replace(rname+".", tableName+".");
					}else if(tab.contains("as")){
						String tableName = tab.split("as")[0].trim();//获取表名
						String rname = tab.split("as")[1].trim();//获取别名
						if(t == 0){
							tables = tables + tableName;
						}else{
							tables = tables + "," + tableName;
						}
						
						colunmsString = colunmsString.replace(rname+".", tableName+".");//用表名取代别名
						afterWhereString = afterWhereString.replace(rname+".", tableName+".");//去掉条件中的表别名
					}else{
						String tableName = tab;//获取表名
						if(t == 0){
							tables = tables + tableName;
						}else{
							tables = tables + "," + tableName;
						}
					}
				}
			}
			map.put("tables", tables);
			
			//获取字段
			String tableColumns = "";
			colunmsString = colunmsString.trim();
			if(colunmsString.equals("*")){
				tableColumns = "*";
			}else{
				String[] reColumnArray = colunmsString.split(",");
				for(int r=0;r<reColumnArray.length;r++){
					String reColumnString = reColumnArray[r].trim();
					//如果+、-、*、/、%前后有空格去掉
					reColumnString = reColumnString.replace(" +", "+").replace("+ ", "+").replace(" + ", "+");
					reColumnString = reColumnString.replace(" -", "-").replace("- ", "-").replace(" - ", "-");
					reColumnString = reColumnString.replace(" *", "*").replace("* ", "*").replace(" * ", "*");
					reColumnString = reColumnString.replace(" /", "/").replace("/ ", "/").replace(" / ", "/");
					reColumnString = reColumnString.replace(" %", "%").replace("% ", "%").replace(" % ", "%");
					
					
					String columnName = "";
					String columnAsName = "";
					/**
					 * 解析别名
					 */
					if(reColumnString.contains(" ")&&!reColumnString.contains(" as ")){
						columnAsName = reColumnString.split(" ")[1].trim();
						columnName = reColumnString.split(" ")[0].trim();
					}else if(reColumnString.contains(" as ")){
						columnAsName = reColumnString.split(" as ")[1].trim();
						columnName = reColumnString.split(" as ")[0].trim();
					}else{
						columnName = reColumnString.trim();
						if(reColumnString.contains(".")&&!reColumnString.contains("+")&&!reColumnString.contains("-")
								&&!reColumnString.contains("%")&&!reColumnString.contains("/")){
							if(reColumnString.contains("*")){
								columnAsName = reColumnString;
							}else{
								columnAsName = reColumnString.split("\\.")[1].trim();
							}
						}else{
							columnAsName = reColumnString;
						}
					}
					
					/**
					 * 解析原字段 
					 */
					String oldColums = "";
					columnName.replace(" (", "(");
					//如果是以去掉开头的"("
					if(columnName.startsWith("("))
						columnName = columnName.replaceFirst("\\(", "");
					if(columnName.contains("count(")||columnName.contains("sum(")||columnName.contains("avg(")||columnName.contains("max(")
							||columnName.contains("min(")||columnName.contains("ascii(")||columnName.contains("char(")){
						oldColums = columnName.substring(columnName.indexOf("("), columnName.indexOf(")"));
					}else if(columnName.contains("||")){
						String[] columnsStrings = columnName.split("\\|\\|");
						for(int c=0;c<columnsStrings.length;c++){
							String coluString = columnsStrings[c].trim();
							if(c==0){
								oldColums = oldColums + coluString;
							}else{
								oldColums = oldColums + "," + coluString;
							}
						}
					}else if(!columnName.contains("case")&&!columnName.contains("(")){//处理一般的字段，不带函数的情况
						//去掉")"
						columnName = columnName.replace(")", "");
						String [] colStrings = null;
						if(columnName.contains("+")){
							colStrings = columnName.split("\\+");
						}else if(columnName.contains("-")){
							colStrings = columnName.split("\\-");
						}else if(columnName.contains("/")){
							colStrings = columnName.split("\\/");
						}else if(columnName.contains("%")){
							colStrings = columnName.split("\\%");
						}else if(columnName.contains("*")&&!columnName.endsWith("*")){
							colStrings = columnName.split("\\*");
						}else{
							colStrings = new String[1];
							colStrings[0] = columnName;
						}
						
						if(colStrings!=null){
							for(int co=0;co<colStrings.length;co++){
								String col = colStrings[co];
								if(co==0){
									oldColums = oldColums + col;
								}else{
									oldColums = oldColums + "," + col;
								}
							}
						}else{
							oldColums = columnName;
						}
					}else{
						oldColums = columnName;
					}
					
					if(r==0){
						tableColumns = tableColumns  + columnAsName + "|" + oldColums;
					}else{
						tableColumns = tableColumns + ";"  + columnAsName + "|" + oldColums;
					}
					
					
				}
			}
			
			map.put("tableColumns", tableColumns);
			/**
			 * 解析条件，where后面的条件
			 */
			String conditions = "";
			afterWhereString  = afterWhereString.trim();
			if(!afterWhereString.equals("")){
				int andC = afterWhereString.indexOf(" and ");
				int orC = afterWhereString.indexOf(" or ");
				String lastCondition = "";
				if(andC>orC){
					String[] ands = afterWhereString.split(" and ");
					for(int a=0;a<ands.length-1;a++){
						conditions = conditions + ands[a].trim() +  " and ";
					}
					lastCondition = ands[ands.length-1];
				}else if(andC<orC){
					String[] ors = afterWhereString.split(" or ");
					for(int o=0;o<ors.length-1;o++){
						conditions = conditions + ors[o].trim() +  " or ";
					}
					lastCondition = ors[ors.length-1];
				}else if(andC==orC){
					lastCondition = afterWhereString;
				}
				lastCondition = lastCondition.trim();
				if(!lastCondition.equals("")){
					if(lastCondition.contains(" like ")){
						String[] lastconditions = lastCondition.split(" ");
						conditions = conditions + lastconditions[0] + " " + lastconditions[1] + " " + lastconditions[2];
					}else{
						//去掉比较符前后的空格
						lastCondition = lastCondition.replace("> ", ">").replace(" >", ">").replace(" > ", ">");
						lastCondition = lastCondition.replace("<= ", "<=").replace(" <=", "<=").replace(" <= ", "<=");
						lastCondition = lastCondition.replace("< ", "<").replace(" <", "<").replace(" < ", "<");
						lastCondition = lastCondition.replace("<= ", "<=").replace(" <=", "<=").replace(" <= ", "<=");
						lastCondition = lastCondition.replace("= ", "=").replace(" =", "=").replace(" = ", "=");
						String[] laString = lastCondition.split(" ");
						conditions = conditions + laString[0];
					}
				}
			}
			
			map.put("conditions", conditions);
			
		}
		return map;
	}
}

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

package com.wonders.bigdata.manageplatform.service.metadata.service.Impl;

import com.wonders.bigdata.manageplatform.service.common.service.HiveService;
import com.wonders.bigdata.manageplatform.service.common.service.JDBCService;
import com.wonders.bigdata.manageplatform.service.dataresource.dao.DataResourceDao;
import com.wonders.bigdata.manageplatform.service.dataresource.model.po.DataResourcePO;
import com.wonders.bigdata.manageplatform.service.metadata.dao.MetadataColumnDao;
import com.wonders.bigdata.manageplatform.service.metadata.dao.MetadataTableDao;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataColumnPO;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataTablePO;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataTableService;
import com.wonders.bigdata.manageplatform.service.resourcetype.service.CatalogTableDatapackageService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryDataTrackParam;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[元数据表]
 * </p>
 * <p>
 * Description: [元数据表Service接口实现类]
 * </p>
 * 
 * @author GLJ
 * @version $Revision$ 2015年3月20日
 * @author (latest modification by $Author$)
 * @since 20100901
 */
@Service("metadataTableServiceImpl")
public class  MetadataTableServiceImpl implements MetadataTableService {

	@Resource(name = "metadataTableDaoImpl")
	private MetadataTableDao metadataTableDao;
	
	@Resource(name = "metadataColumnDaoImpl")
	private MetadataColumnDao metadataColumnDao;
	
	@Resource(name = "dataResourceDaoImpl")
	private DataResourceDao dataResourceDao;
	
	@Autowired
	private HiveService hiveService;

	@Autowired
	private JDBCService jdbcService;

	@Autowired
	private CatalogTableDatapackageService catalogTableDatapackageService;
	@Override
	public MetadataTablePO queryByTableId(Long id) {
		return metadataTableDao.get(id);
	}

	@Override
	public List<MetadataTablePO> getTableByResourceId(String resourceId) {


		List<MetadataTablePO> list = new ArrayList<>();
		String sql = "select * from bd_metadata_table where resource_id = "+resourceId+" and delete_flag = "+Constant.METADATA_TABLE_NAME_NOT_DELETE+";";
		List<List<String>> l1 = new ArrayList<>();
		l1 = jdbcService.executeSQL(sql);

		for(int i = 0; i<l1.size() ; i++){
			List<String> newli = new ArrayList<>();
			newli = l1.get(i);
			MetadataTablePO po = new MetadataTablePO();
			po.setId(Long.parseLong(newli.get(0)));
			po.setTableName(newli.get(1));
			list.add(po);
		}

		return list;
	}

	@Override
	public List<MetadataTablePO> getTableForDataTrackByResourceId(String resourceId, String key, String date) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceId", resourceId);
		paramMap.put("deleteFlag", Constant.METADATA_TABLE_NAME_NOT_DELETE);
		QueryDataTrackParam param = QueryDataTrackParam.genParam(paramMap, "tableName", key, "createDate", date);
		return metadataTableDao.findByAnd(param);
	}

	@Override
	public MetadataTablePO queryByTableName(String tableName) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tableName", tableName);
		List<MetadataTablePO> metadataTablePOs = metadataTableDao.findBy(paramMap);

		MetadataTablePO metadataTablePO = new MetadataTablePO();
		if (metadataTablePOs != null && metadataTablePOs.size() > 0)
			metadataTablePO = metadataTablePOs.get(0);

		return metadataTablePO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		MetadataTablePO metadataTablePO = metadataTableDao.get(id);
		metadataTablePO.setDeleteFlag(Constant.METADATA_TABLE_NAME_DELETE);
		metadataTableDao.save(metadataTablePO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MetadataTablePO save(MetadataTablePO mtp) {
		MetadataTablePO metadataTablePO2 = queryByTableName(mtp.getTableName());
		if (metadataTablePO2 != null && metadataTablePO2.getTableName() != null) {
			mtp = metadataTablePO2;
			mtp.setEditDate(new Date());
			metadataTableDao.save(mtp);
		}
		else {
			metadataTableDao.save(mtp);
			mtp = queryByTableName(mtp.getTableName());
		}

		return mtp;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MetadataTablePO update(Map<String, Object> columns, String tId, String oe, String rId,
			String tableRemark, String table_permission, String status, String desc) {
		
		addExampleTableToDataResource(oe, rId, tId);//把提供样例的表的id存入数据源记录中
		Long tableId = Long.parseLong(tId);
		List<Long> columnId = new ArrayList<Long>();
		List<String> columnRemark = new ArrayList<String>();
		List<Integer> columnOpenLevel = new ArrayList<Integer>();
		List<String> columnDescription = new ArrayList<String>();
		Set<String> key = columns.keySet();
		for (Object obj : key.toArray()) {
			try {
				String[] str = (String[]) columns.get(obj);
				for (int i = 0; i < str.length; i++) {
					if (obj.toString().equals("id")) {
						columnId.add(Long.valueOf(((String[]) columns.get(obj))[i]));
					} else if (obj.toString().equals("remark")) {
						columnRemark.add(((String[]) columns.get(obj))[i]);
					} else if (obj.toString().equals("permission")) {
						columnOpenLevel.add(Integer.valueOf(((String[]) columns.get(obj))[i]));
					} else if (obj.toString().equals("description")) {
						columnDescription.add(((String[]) columns.get(obj))[i]);
					}
				}
			} catch (Exception e) {
					if (obj.toString().equals("id")) {
						columnId.add(Long.valueOf(((String) columns.get(obj))));
					} else if (obj.toString().equals("remark")) {
						columnRemark.add(((String) columns.get(obj)));
					} else if (obj.toString().equals("permission")) {
						columnOpenLevel.add(Integer.valueOf(((String) columns.get(obj))));
					} else if (obj.toString().equals("description")) {
						columnDescription.add(((String) columns.get(obj)));
					}
			}
			
		}
		MetadataColumnPO cPo = new MetadataColumnPO();
		for (int i = 0; i < columnId.size(); i++) {
			 cPo = metadataColumnDao.get(columnId.get(i));
			 cPo.setOpenLevel(columnOpenLevel.get(i));
			 cPo.setRemark(columnRemark.get(i));
			 cPo.setDescription(columnDescription.get(i));
			 metadataColumnDao.save(cPo);
		}
		MetadataTablePO mPo = metadataTableDao.get(tableId);
		//判断example是否为空
		if(mPo.getExample()==null || mPo.getExample().equals("") || mPo.getExample().equals(" ")){
			mPo.setExample(hiveService.genPreviewData("select * from "+mPo.getTableName()));
		}
		mPo.setRemark(tableRemark);
		mPo.setOpenLevel(Integer.parseInt(table_permission));
		mPo.setStatus(Integer.parseInt(status));
		mPo.setDescription(desc);
		mPo = metadataTableDao.save(mPo);
		return mPo;
	}
	
	/**
	 * 修改该表可否预览
	 * @param checkbox
	 * @param resourceId/tableId
	 * @author tg
	 */
	private void addExampleTableToDataResource(Object check,String resource_id,String table_id){
		DataResourcePO dataPo = dataResourceDao.get(Long.parseLong(resource_id));
		String exampleTables = dataPo.getExampleTables();
		if(check!=null){
			boolean exisitOrNot = true;
			if(exampleTables!=null && !"".equals(exampleTables.trim())){
				String[] exampleTable = exampleTables.split(",");
				for(int i=0;i<exampleTable.length;i++){
					if(exampleTable[i].equalsIgnoreCase(table_id)){
						exisitOrNot = false;
					}
				}
				if(exisitOrNot){
					exampleTables = exampleTables +","+table_id;
				}
			}else{
				exampleTables = table_id;
			}
			String pass = "";
			for(int i=0;i<exampleTables.length();i++){
				String a = String.valueOf(exampleTables.charAt(i));
				if(a==","){
					int c=i+1;
					String b = String.valueOf(exampleTables.charAt(c));
					if(b==","){
						i++;
					}
				}
				pass = pass+a;
			}
			dataPo.setExampleTables(pass);
			dataResourceDao.save(dataPo);
		}else{
			if(exampleTables!=null && !"".equals(exampleTables.trim())){
				String str ="";//写入内容
				String[] exampleTable = exampleTables.split(",");
				for(int i=0;i<exampleTable.length;i++){
					if(exampleTable[i]==null){
						if(!table_id.equals(exampleTable[i])){
							str = str + exampleTable[i]+",";
						}
					}
				}
				String pass = "";
				for(int i=0;i<str.length();i++){
					String a = String.valueOf(str.charAt(i));
					if(a==","){
						int c=i+1;
						String b = String.valueOf(str.charAt(c));
						if(b==","){
							i++;
						}
					}
					pass = pass+a;
				}
				dataPo.setExampleTables(pass); 
				dataResourceDao.save(dataPo);
			}
		}
		
	}

	@Override
	public long countByQuery(QueryParam param) {
		return metadataTableDao.countByQuery(param);
	}

	@Override
	public Page<MetadataTablePO> findByPage(Page<MetadataTablePO> page) {
		return metadataTableDao.findByPage(page);
	}

	@Override
	public List<MetadataTablePO> findByList(QueryParam param) {
		return metadataTableDao.findByAnd(param);
	}

	@Override
	public List<MetadataTablePO> getOpenTableByResourceId(String resourceId) {
		HashMap<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("resourceId", resourceId);
		searchParams.put("openLevel", Constant.METADATA_TABLE_NAME_OPEN_LEVEL_OPEN);
		searchParams.put("status", Constant.METADATA_TABLE_STATUS_AUDITED);
		searchParams.put("deleteFlag", Constant.METADATA_TABLE_NAME_NOT_DELETE);
		return metadataTableDao.findBy(searchParams);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MetadataTablePO update(MetadataTablePO table) {
		return metadataTableDao.save(table);
	}

	@Override
	public MetadataTablePO findById(Long id){
		return metadataTableDao.get(id);
	}
	

	@Override
	public List<MetadataTablePO> geMetadataTable(Long metaTableId){
		List<MetadataTablePO> returnTables=new ArrayList<MetadataTablePO>();
		QueryParam param = new QueryParam();
		Map<String, Object> eq = new HashMap<String, Object>();
		eq.put("id", metaTableId);
		eq.put("deleteFlag", Constant.METADATA_TABLE_NAME_NOT_DELETE);
		//eq.put("openLevel", Constant.METADATA_TABLE_NAME_OPEN_LEVEL_OPEN);
		param.setEq(eq);
		returnTables = metadataTableDao.findByAnd(param);
		return returnTables;
	}

	@Override
	public List<MetadataTablePO> getAllRejectedMetadataTabel() {
		// TODO Auto-generated method stub
		QueryParam param = new QueryParam();
		Map<String, Object> eq =  new HashMap<String,Object>();
		eq.put("deleteFlag", Constant.METADATA_TABLE_NAME_NOT_DELETE);
		eq.put("status", Constant.METADATA_TABLE_STATUS_AUDITED_FAILED);
		param.setEq(eq);
		return metadataTableDao.findByAnd(param);
	}
}

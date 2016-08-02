/**
 * Copyright (c) 1995-2012 Wonders Information Co.,Ltd.
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with
 * the terms of the license agreement you entered into with Wonders Group.
 * <p/>
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wonders.bigdata.manageplatform.service.metadata.service;

import java.util.List;
import java.util.Map;

import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataTablePO;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[元数据表]
 * </p>
 * <p>
 * Description: [元数据表Service接口]
 * </p>
 *
 * @author GLJ
 * @version $Revision$ 2015年3月20日
 * @author (latest modification by GLJ)
 * @since 20100901
 */
public interface  MetadataTableService {

    /**
     * <p>
     * Description:[根据id获取元数据-表]
     * </p>
     * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
     *
     * @param id
     * @return MetadataTablePO
     */
    public MetadataTablePO queryByTableId(Long id);

    /**
     * <p>
     * Description:[根据原数据id（schemaId）获取该schema下所有表]
     * </p>
     * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
     *
     * @param resourceId
     * @return List<MetadataTablePO>
     */
    public List<MetadataTablePO> getTableByResourceId(String resourceId);

    /**
     * <p>
     * Description:[根据资源id获取数据追踪所需的元数据表数据]
     * </p>
     * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
     *
     * @param resourceId
     * @param key
     * @param date
     * @return List<MetadataTablePO>
     */
    public List<MetadataTablePO> getTableForDataTrackByResourceId(String resourceId, String key, String date);

    /**
     * <p>
     * Description:[根据tableName进行精确查询]
     * </p>
     * Created by [GLJ] [2015-03-20] Modified by [修改人] [修改时间]
     *
     * @param tableName
     * @return MetadataTablePO
     */
    public MetadataTablePO queryByTableName(String tableName);

    /**
     * <p>
     * Description:[删除元数据表]
     * </p>
     * Created by [CSJ] [2015-03-23] Modified by [修改人] [修改时间]
     *
     * @param id
     * @return
     */
    public void delete(Long id);

    /**
     * <p>
     * Description:[新增元数据表]
     * </p>
     * Created by [CSJ] [2015-03-23] Modified by [修改人] [修改时间]
     *
     * @param mtp
     * @return
     */
    public MetadataTablePO save(MetadataTablePO mtp);

    /**
     * <p>
     * Description:[更新元数据表]
     * </p>
     * Created by [CSJ] [2015-03-24] Modified by [修改人] [修改时间]
     *
     * @param Map<String, Object> columns
     * @param String tableId
     * @param String oe
     * @param String resourceId
     * @param String tableRemark
     * @param String tablePermission
     * @param String status
     * @param String desc
     * @return MetadataTablePO
     */
    public MetadataTablePO update(Map<String, Object> columns, String tId, String oe, String rId,
                                  String tableRemark, String table_permission, String status, String desc);

    /**
     * <p>
     * Description:[获取符合条件的元表个数]
     * </p>
     * Created by [GLJ] [2015-03-25] Modified by [修改人] [修改时间]
     *
     * @param param
     * @return
     */
    public long countByQuery(QueryParam param);

    /**
     * <p>
     * Description:[分页查询数据源]
     * </p>
     * Created by [CSJ] [2015-3-27] Midified by [修改人] [修改时间]
     *
     * @param Page<MetadataTablePO> page
     * @return Page<MetadataTablePO>
     */
    public Page<MetadataTablePO> findByPage(Page<MetadataTablePO> page);

    /**
     * <p>
     * Description:[根据资源id获取所有审核通过的具有开放权限的元数据表的列表]
     * </p>
     * Created by [CSJ] [2015-3-27] Midified by [修改人] [修改时间]
     *
     * @param resourceId
     * @return List<MetadataTablePO>
     */
    public List<MetadataTablePO> getOpenTableByResourceId(String resourceId);

    /**
     * <p>
     * Description:[列表查询数据源]
     * </p>
     * Created by [WF] [2015-4-3] Midified by [修改人] [修改时间]
     *
     * @param QueryParam param
     * @return List<MetadataTablePO>
     */
    public List<MetadataTablePO> findByList(QueryParam param);

    /**
     * <p>
     * Description:[更新元数据表]
     * </p>
     * Created by [CSJ] [2015-03-24] Modified by [修改人] [修改时间]
     *
     * @param MetadataTablePO mPo
     * @return MetadataTablePO
     */
    public MetadataTablePO update(MetadataTablePO table);

    /**
     * <p>
     * Description:[根据id查找元数据]
     * </p>
     * Created by
     *
     * @param MetadataTablePO mPo
     * @return MetadataTablePO
     */
    public MetadataTablePO findById(Long id);


    /*
     * 根据metaTableId查找没被删除的metaTable
     * */
    public List<MetadataTablePO> geMetadataTable(Long metaTableId);

    /**
     * 获得所有被驳回的MetadataTable
     * @return
     */
    public List<MetadataTablePO> getAllRejectedMetadataTabel();
}

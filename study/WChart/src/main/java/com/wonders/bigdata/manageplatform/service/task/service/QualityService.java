package com.wonders.bigdata.manageplatform.service.task.service;

import com.wonders.bigdata.manageplatform.service.task.model.po.QualityPO;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */

public interface  QualityService {

    /**
     * <p>
     * Description:[任务新增]
     * </p>
     * Created by [Heyh] [2015年3月17日] Midified by [修改人] [修改时间]
     *
     * @param po
     * @return
     */
    public QualityPO save(QualityPO po);


    /**
     * <p>
     * Description:[任务分页查询]
     * </p>
     * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
     *
     * @param page
     * @return
     */
    public Page<QualityPO> findByPage(Page<QualityPO> page);

    /**
     * <p>
     * Description:[任务列表查询]
     * </p>
     * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
     *
     * @param map
     * @return
     */
    public List<QualityPO> findQualityByList(QueryParam param);




    /**
     * <p>
     * Description:[根据id获取任务]
     * </p>
     * Created by [Heyh] [2015年3月16日] Midified by [修改人] [修改时间]
     *
     * @param id
     * @return
     */
    public QualityPO getById(long id);



    /**
     * <p>
     * Description:[根据id删除任务]
     * </p>
     * Created by [Heyh] [2015年3月17日] Midified by [修改人] [修改时间]
     *
     * @param id(逻辑删除)
     */
    public void deleteById(long id);



}

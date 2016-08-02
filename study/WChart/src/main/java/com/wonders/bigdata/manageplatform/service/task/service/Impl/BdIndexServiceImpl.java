package com.wonders.bigdata.manageplatform.service.task.service.Impl;

import com.wonders.bigdata.manageplatform.service.task.dao.BdIndexDao;
import com.wonders.bigdata.manageplatform.service.task.dao.QualityDao;
import com.wonders.bigdata.manageplatform.service.task.model.po.BdIndexPO;
import com.wonders.bigdata.manageplatform.service.task.model.po.QualityPO;
import com.wonders.bigdata.manageplatform.service.task.service.BdIndexService;
import com.wonders.bigdata.manageplatform.web.dataquality.vo.DataQualityVO;
import com.wonders.bud.framework.common.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xh on 2016/4/28.
 */
@Service("bdIndexServiceImpl")
public class BdIndexServiceImpl implements BdIndexService{

    @Autowired
    private BdIndexDao bdIndexDao;

    @Autowired
    private QualityDao qualityDao;

    @Override
    public List<BdIndexPO> getAll() {
        return bdIndexDao.findAll();
    }

    @Override
    public List<DataQualityVO> getByTableName(String tableName, List<String> columns) {

        List<DataQualityVO> result = new LinkedList<>();

        for (String columnName : columns) {

            QueryParam queryParam = new QueryParam();

            Map<String,Object> eq = new HashMap<>();
            eq.put("tableName", tableName);
            eq.put("columnName", columnName);
            queryParam.setEq(eq);

            Map<String,String> order = new HashMap<>();
            order.put("indexId", "asc");
            queryParam.setOrder(order);

            List<QualityPO> list = qualityDao.findByAnd(queryParam);

            DataQualityVO dataQualityVO = new DataQualityVO();
            dataQualityVO.setColumnName(columnName);

            if (null != list && list.size() > 0) {

                dataQualityVO.setId(list.get(0).getId());

                Map<String, String> indexResultMap = new HashMap<>();

                /**
                 * 遍历该次统计结过，并存入list
                 */
                for (QualityPO qualityPO: list) {

                    String indexName = qualityPO.getIndexName() == null ? "" :  qualityPO.getIndexName();
                    String indexResult = qualityPO.getResult() == null ? "" : qualityPO.getResult();
                    indexResultMap.put(indexName, indexResult);
                }

                dataQualityVO.setResult(indexResultMap);
            }
            result.add(dataQualityVO);
        }
        return result;
    }
}

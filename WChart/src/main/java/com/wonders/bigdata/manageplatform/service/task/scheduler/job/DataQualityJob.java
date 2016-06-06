package com.wonders.bigdata.manageplatform.service.task.scheduler.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by WYP on 2016/4/27.
 */
public class  DataQualityJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jdMap = context.getJobDetail().getJobDataMap();
        DataQuality dataQuality = (DataQuality) jdMap.get("DataQuality");//从Job存储中获取SqoopETL对象
        dataQuality.submission();
    }
}

package com.wonders.bigdata.manageplatform.service.task.scheduler.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.bigdata.manageplatform.service.task.scheduler.sqoop.SqoopETL;

public class SqoopJob implements Job {

	@Override
	public void  execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jdMap = context.getJobDetail().getJobDataMap();
		SqoopETL sqoopDao = (SqoopETL) jdMap.get("sqoopDao");//从Job存储中获取SqoopETL对象
		sqoopDao.submission();//调用SqoopETL对象的submission方法执行
	}

}

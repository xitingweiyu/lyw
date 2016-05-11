package com.wonders.bigdata.manageplatform.service.task.scheduler.manager.Impl;

import com.wonders.bigdata.manageplatform.service.common.service.HiveService;
import com.wonders.bigdata.manageplatform.service.dataresource.service.DataResourceService;
import com.wonders.bigdata.manageplatform.service.log.service.LogService;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataColumnService;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataTableService;
import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;
import com.wonders.bigdata.manageplatform.service.task.scheduler.job.DataQuality;
import com.wonders.bigdata.manageplatform.service.task.scheduler.job.DataQualityJob;
import com.wonders.bigdata.manageplatform.service.task.scheduler.manager.TaskScheduleManager;
import com.wonders.bigdata.manageplatform.service.task.service.QualityService;
import com.wonders.bigdata.manageplatform.service.task.service.TaskService;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Component()
public class TaskScheduleManagerImpl implements TaskScheduleManager {

	private final  Scheduler scheduler;

	@Autowired
	private LogService logService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private MetadataTableService metadataTableService;

	@Autowired
	private MetadataColumnService metadataColumnService;

	@Autowired
	private HiveService hiveService;

	@Autowired
	private QualityService qualityService;

	@Autowired
	private DataResourceService dataResourceService;

	public Scheduler getScheduler() {
		return scheduler;
	}

	public static String GROUP = "COM.WONDERSGROUP.BIGDATA";

	public TaskScheduleManagerImpl() throws SchedulerException {
		this.scheduler = StdSchedulerFactory.getDefaultScheduler();
	}

	/*
	 * 新增任务到调度器
	 * @param 调度器group名称
	 * @param 任务对象
	 * @param 数据库库表结构
	 * @param sqoop连接配置信息
	 */
	@Override
	public void scheduleNewJob(String group, TaskPO task) throws ParseException, SchedulerException {
		JobDetail job = newJob(DataQualityJob.class).withIdentity(task.getName(), group) // name "myJob", group "group1"
				.build();

		DataQuality dataQuality = new DataQuality();
		dataQuality.setHiveService(hiveService);
		dataQuality.setTask(task);
		dataQuality.setLogService(logService);
		dataQuality.setTaskService(taskService);
		dataQuality.setQualityService(qualityService);
		job.getJobDataMap().put("DataQuality", dataQuality);//把DataQuality对象放到quartz Job存储

		Trigger trigger =
				newTrigger().withIdentity("trigger_" + task.getName(), group)
						.withSchedule(cronSchedule(task.getCronExpression())).build();//设置job的触发器
		this.scheduler.scheduleJob(job, trigger);//把job放进调度器
	}

	@Override
	public void deleteScheduleJob(String name, String group) throws SchedulerException {

	}

	@Override
	public void pauseJob(String jobName, String jobGroup) throws SchedulerException {
		// TODO Auto-generated method stub

	}

}

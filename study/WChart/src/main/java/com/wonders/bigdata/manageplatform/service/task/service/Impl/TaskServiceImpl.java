package com.wonders.bigdata.manageplatform.service.task.service.Impl;

import com.wonders.bigdata.manageplatform.service.common.service.HiveService;
import com.wonders.bigdata.manageplatform.service.dataresource.model.po.DataResourcePO;
import com.wonders.bigdata.manageplatform.service.dataresource.service.DataResourceService;
import com.wonders.bigdata.manageplatform.service.metadata.dao.MetadataTableDao;
import com.wonders.bigdata.manageplatform.service.task.dao.TaskDao;
import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;
import com.wonders.bigdata.manageplatform.service.task.scheduler.manager.TaskScheduleManager;
import com.wonders.bigdata.manageplatform.service.task.service.TaskService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bigdata.manageplatform.utils.Messages;
import com.wonders.bigdata.manageplatform.utils.SqConEntity;
import com.wonders.bud.framework.common.page.Page;
import com.wonders.bud.framework.common.util.QueryParam;
import org.apache.commons.lang3.ArrayUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

import static org.quartz.JobKey.jobKey;

/**
 * <p>
 * Title: manageplatform_[大数据管理平台]_[管理员]
 * </p>
 * <p>
 * Description: [任务service接口实现]
 * </p>
 * 
 * @author Heyh
 * @version $Revision$ 2015年3月16日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */

@Service("taskServiceImpl")
public class  TaskServiceImpl implements TaskService {
	
	private static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	@Autowired
	private TaskDao taskDao;

	@Autowired
	private DataResourceService dataResourceService;

	@Autowired
	private MetadataTableDao metadataTableDao;

	@Autowired
	private TaskScheduleManager tsm;

	@Autowired
	private HiveService hiveService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TaskPO save(TaskPO po) {
		if(po.getCreateDate() == null) {
			po.setCreateDate(new Date());
		}
		po.setUpdateDate(new Date());
		po.setDeleteFlag(Constant.TASK_NOT_DELETE);
		po.setUserId(-1l);
		po = taskDao.save(po);
		String result = scheduleTask(po, null);
		if(!"success".equals(result)) {
			throw new RuntimeException(result);
		}
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TaskPO simpleSave(TaskPO po) {

		po = taskDao.save(po);
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TaskPO update(TaskPO po, String gtName) {
		po.setCreateDate(po.getCreateDate());
		po.setUpdateDate(new Date());
		po = taskDao.update(po);
		String result = scheduleTask(po, gtName);
		if(!"success".equals(result)) {
			throw new RuntimeException(result);
		}
		return po;
	}

	private String scheduleTask(TaskPO task, String gtName) {
		try {
			if (tsm.getScheduler().checkExists(jobKey(task.getName(), Constant.TASK_GROUP))) {// 检查调度器中是否已经存在任务
				tsm.getScheduler().deleteJob(jobKey(task.getName(), Constant.TASK_GROUP));// 调度器中存在任务则删除任务
			}
			if (task.getStatus() == Constant.TASK_STATUS_ACTIVE) {
				List<Map<String, String[]>> list = new ArrayList<Map<String, String[]>>();

				/*DataResourcePO drp = dataResourceService.findById(task.getDataResourceId());// 获取数据源
				list = getMetadata(drp.getJdbcUrl(), drp.getId(), drp.getJdbcUsername(), drp.getJdbcPassword(),
						drp.getSchemaName(), gtName, task.getResourceTables());
				if(list.size() == 0) {
					return "无法连接数据库！";
				}*/
				//SqConEntity sce = getSqConByResource(drp);
				tsm.scheduleNewJob(Constant.TASK_GROUP, task);// 新增任务到调度器

				if (!tsm.getScheduler().isShutdown()) {// 如果调度器已经关掉，则重启调度器
					tsm.getScheduler().start();
				}
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			return "cron表达式解析错误，请检查！"; //$NON-NLS-1$
		} catch (SchedulerException e1) {
			logger.error(e1.getMessage(), e1);
			return "更新成功！由于调度原因，该任务不能被调度！"; //$NON-NLS-1$
		} catch (Exception e2) {
			logger.error(e2.getMessage(), e2);
			return "方法发生异常！";
		}
		return "success"; //$NON-NLS-1$
	}
	
	private List<Map<String, String[]>> getMetadata(String jdbcUrl, long drId, String usrName, String pwd,
			String schemaName, String gtName, String rTables) {
		List<Map<String, String[]>> result = new LinkedList<Map<String, String[]>>();
		String[] rts = rTables.split(";");
		String[] tables = rts[0].split(",");
		if(rts.length >= 2) {
			String[] views = rts[1].split(",");
			tables = (String[]) ArrayUtils.addAll(tables, views);
		}
		result = hiveService.getTablesColNamHasTables(jdbcUrl, drId, usrName, pwd, gtName, tables);
		return result;
	}

	@Override
	public Page<TaskPO> findByPage(Page<TaskPO> page) {
		QueryParam param = new QueryParam();
		Map<String, String> map = new HashMap<String, String>();

		if (null != page) {
			if (null != page.getParam()) {
				param = page.getParam();

				if (null != page.getParam().getOrder()) {
					map = page.getParam().getOrder();
				}
			}
			//page = new Page<TaskPO>();
		}
		map.put("updateDate", "desc");// 时间 //$NON-NLS-1$ //$NON-NLS-2$
		param.setOrder(map);
		unDelete(param);
		page.setParam(param);
		return taskDao.findByPage(page);
	}

	@Override
	public List<TaskPO> findTaskByList(QueryParam param) {
		Map<String, String> order = new HashMap<String, String>();
		if (param != null) {
			if (param.getOrder() != null) {
				order = param.getOrder();
			}
		}
		order.put("updateDate", "desc"); //$NON-NLS-1$ //$NON-NLS-2$
		param.setOrder(order);
		unDelete(param);
		return taskDao.findByAnd(param);
	}

	@Override
	public boolean duplicatecheck(Object id, Map<String, Object> map) {
		return taskDao.duplicatecheck("id", id, map); //$NON-NLS-1$
	}

	@Override
	public TaskPO getById(long id) {
		TaskPO po = taskDao.get(id);
		if (po != null && po.getDeleteFlag() == Constant.TASK_NOT_DELETE) {
			return po;
		}
		else {
			return null;
		}
	}

	@Override
	public Long taskFailCount() {
		QueryParam param = new QueryParam();
		HashMap<String, Object> eq = new HashMap<String, Object>();
		eq.put("status", Constant.TASK_STATUS_ERROR); //$NON-NLS-1$
		param.setEq(eq);
		unDelete(param);
		return taskDao.countFailTask(param);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(long id) {
		TaskPO po = taskDao.get(id);
		if (po != null) {
			po.setDeleteFlag(Constant.TASK_DELETE);
			taskDao.update(po);
			try {
				if (tsm.getScheduler().checkExists(jobKey(po.getName(), Constant.TASK_GROUP))) {// 检查调度器中是否已经存在任务
					tsm.getScheduler().deleteJob(jobKey(po.getName(), Constant.TASK_GROUP));// 调度器中存在任务则删除任务
				}
			}
			catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String start(long id) {
		TaskPO task = this.getById(id);
		if (task == null) {
			return "任务不存在！"; //$NON-NLS-1$
		}
		try {

			tsm.scheduleNewJob(Constant.TASK_GROUP,task);// 重新把任务加入到调度器中
			if (!tsm.getScheduler().isShutdown()) {
				tsm.getScheduler().start();
			}
		} catch (SchedulerException e) {
			return "由于调度原则，不能激活成功"; //$NON-NLS-1$
		} catch (ParseException e) {
			return "cron表达式出错，请仔细检查！"; //$NON-NLS-1$
		}
		task.setStatus(Constant.TASK_STATUS_ACTIVE);
		taskDao.save(task);
		return "success";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String stop(long id) {
		TaskPO task = this.getById(id);
		if (task == null) {
			return "任务不存在！";
		}
		try {
			tsm.getScheduler().deleteJob(jobKey(task.getName(), Constant.TASK_GROUP));// 把任务从调度器中删除
		}
		catch (SchedulerException e) {
			return "由于调度原则，不能删除成功！";
		}
		task.setStatus(Constant.TASK_STATUS_STOP);
		taskDao.save(task);
		return "success";
	}

	// 过滤删除状态的任务
	public void unDelete(QueryParam param) {
		Map<String, Object> eq = param.getEq();
		if (eq == null) {
			eq = new HashMap<String, Object>();
		}
		eq.put("deleteFlag", Constant.TASK_NOT_DELETE); //未删除
		param.setEq(eq);

	}

	/*
	 * 根据dataResource数据信息生成Sqoop连接实体对象
	 * @param DataResourcePO对象
	 * @return SqConEntity对象
	 */
	public SqConEntity getSqConByResource(DataResourcePO po) {
		SqConEntity sce = new SqConEntity();
		sce.setName(po.getResourceName());
		sce.setClientUrl(Messages.getString("sqoopServerUrl")); //获取sqoop服务器地址
		sce.setConnString(po.getJdbcUrl());
		sce.setUserName(po.getJdbcUsername());
		sce.setPassword(po.getJdbcPassword());
		sce.setMaxConnection(po.getResourceSize());
		int dbt = po.getDatabaseType();
		String driver = dataResourceService.getDriver(dbt);//获取JDBC驱动
		sce.setJdbcDriver(driver); 
		return sce;
	}

	@Override
	public TaskPO getTaskByDrId(long id) {
		TaskPO task = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dataResourceId", id);
		param.put("deleteFlag", Constant.TASK_NOT_DELETE);
		List<TaskPO> tasks = taskDao.findBy(param);
		if (tasks.size() > 0) {
			task = tasks.get(0);
		}
		return task;
	}

	/*
	 * 改变资源同步任务的状态
	 * @param 任务id
	 * @param 状态值
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String changeResourceTaskStatus(long id, int status) {
		if (id == 0 || status == 0) {
			return "error";
		}
		TaskPO task = getById(id);
		if (status != task.getStatus()) {
			task.setStatus(status);
			taskDao.save(task);
		}
		return "success";
	}

	@Override
	public long countTask(QueryParam param) {
		return taskDao.countFailTask(param);
	}

}

package com.wonders.bigdata.manageplatform.service.task.scheduler.sqoop;

import com.wonders.bigdata.manageplatform.service.common.service.HiveService;
import com.wonders.bigdata.manageplatform.service.dataresource.service.DataResourceService;
import com.wonders.bigdata.manageplatform.service.log.model.po.TaskLogPO;
import com.wonders.bigdata.manageplatform.service.log.service.LogService;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataColumnPO;
import com.wonders.bigdata.manageplatform.service.metadata.model.po.MetadataTablePO;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataColumnService;
import com.wonders.bigdata.manageplatform.service.metadata.service.MetadataTableService;
import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;
import com.wonders.bigdata.manageplatform.service.task.service.TaskService;
import com.wonders.bigdata.manageplatform.utils.Constant;
import com.wonders.bigdata.manageplatform.utils.Messages;
import com.wonders.bigdata.manageplatform.utils.SqConEntity;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.client.SubmissionCallback;
import org.apache.sqoop.model.*;
import org.apache.sqoop.submission.SubmissionStatus;
import org.apache.sqoop.validation.Status;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class SqoopETL {
	private SqoopClient  sqoopClient;// sqoop客户端对象

	private TaskPO taskPO;// 资源同步任务对象

	private List<Map<String, String[]>> list;// 数据库库表结构

	private SqConEntity sce;// sqoop连接实体对象

	private TaskService taskService;

	private LogService logService;

	private MetadataTableService metadataTableService;

	private MetadataColumnService metadataColumnService;

	private HiveService hfs;

	private DataResourceService dataResourceService;

	private List<MetadataTablePO> oldMetadataTablePOs = new ArrayList<MetadataTablePO>();
	
	private final static int SQOOP_JOB_TIMEOUT_TIME = Integer.parseInt(Messages.getString("sqoop_job_timeout_time"));//SQOOP任务的默认最长反应时间

	/*
	 * 创建sqoop连接
	 */
	public MConnection createConnection(SqConEntity se) {
		this.sqoopClient = new SqoopClient(se.getClientUrl());
		MConnection newCon = sqoopClient.newConnection(1);
		MConnectionForms conForms = newCon.getConnectorPart();
		MConnectionForms frameworkForms = newCon.getFrameworkPart();
		newCon.setName(se.getName());
		conForms.getStringInput("connection.connectionString").setValue(se.getConnString());// 数据库连接url字符串
		conForms.getStringInput("connection.jdbcDriver").setValue(se.getJdbcDriver());// 数据库驱动
		conForms.getStringInput("connection.username").setValue(se.getUserName());// 数据库用户名
		conForms.getStringInput("connection.password").setValue(se.getPassword());// 数据库密码
		frameworkForms.getIntegerInput("security.maxConnections").setValue(se.getMaxConnection());// sqoop的最大连接数
		try {
			Status status = sqoopClient.createConnection(newCon);
			if (status.canProceed()) {
				return newCon;
			}
			else {
				System.out.println("Check for status and forms error ");
				return null;
			}
		}
		catch (Exception e) {
			System.out.println("创建连接出错！");
			System.out.println(e.getMessage());
			return null;
		}
	}

	/*
	 * sqoop任务提交函数
	 */
	public void submission() {
		MConnection con = createConnection(sce);
		if (con == null) {
			return;
		}
		Long conId = con.getPersistenceId();
		MSubmission submission = null;
			// sqoop任务执行回调内部类
			class CallBack implements SubmissionCallback {

				/*
				 * sqoop任务完成回调函数
				 */
				@Override
				public void finished(MSubmission arg0) {

				}

				/*
				 * sqoop任务提交回调函数
				 */
				@Override
				public void submitted(MSubmission arg0) {
					taskService.changeResourceTaskStatus(taskPO.getId(), Constant.TASK_STATUS_RUNNING);
					addTaskLog(sqoopClient.getJob(arg0.getJobId()).getConnectorPart().getStringInput("table.tableName")
							.getValue()
							+ " submitted success!", arg0.getJobId());
					System.out.println(sqoopClient.getJob(arg0.getJobId()).getConnectorPart()
							.getStringInput("table.tableName").getValue()
							+ " submited success! " + "jobId=" + arg0.getJobId());
				}

				/*
				 * sqoop任务更新回调函数
				 */
				@Override
				public void updated(MSubmission arg0) {
				}
			}

			boolean f = true;
			boolean hasError = false;
			Iterator<Map<String, String[]>> iterator = list.iterator();
			MJob newjob = sqoopClient.newJob(conId, org.apache.sqoop.model.MJob.Type.IMPORT);
			long jobId = 0;
			int index = 0;
			final int sqoop_job_timeout_time = (taskPO.getTimeout() != 0)?taskPO.getTimeout():SQOOP_JOB_TIMEOUT_TIME;//如果任务没有设置timeout，则使用默认值
			while (f && iterator.hasNext()) {// 循环提交sqoop任务
				f = false;
				CallBack callback = new CallBack();
				Map<String, String[]> tableInfo = iterator.next();
				Map<String, String> info = updateSqoopJob(newjob, tableInfo);//更新sqoopJob配置信息
				if (jobId == 0) {
					sqoopClient.createJob(newjob);
					jobId = newjob.getPersistenceId();
				} else {
					sqoopClient.updateJob(newjob);
				}
				final long id = jobId;

				if (taskPO != null && taskPO.getId() != 0 && index != 0) {
					TaskPO taskPO1 = taskService.getById(taskPO.getId());
					if (taskPO1 != null) {
						if (taskPO1.getStatus() != Constant.TASK_STATUS_RUNNING)
							break;
					}
				}
				index++;

				/**
				 * 启用一个线程，用于监听sqoop执行任务的时间，如果时间超过3（3*60*60*1000）小时，则停止掉该任务
				 */
				
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(sqoop_job_timeout_time * 60 * 60 * 1000);
							sqoopClient.stopSubmission(id);
						} catch (InterruptedException e) {
						}
					}
				});
				thread.start();
				long startTime = System.currentTimeMillis();
				try {
					submission = sqoopClient.startSubmission(jobId, callback, 100);
				} catch (InterruptedException e1) {
					if(thread.isAlive()){
						thread.interrupt();//发生异常停止掉
					}
					break;
				}// 提交并执行sqoop任务
				thread.interrupt();// 如果没有超时，自动退出监听线程
				if (submission.getStatus().compareTo(SubmissionStatus.SUCCEEDED) == 0) {// 任务执行成功，则把数据写入到hive中
					try {
						hfs.createHiveTable(tableInfo, Messages.getString("jdbcHiveUrl"), info.get("oString"));
						saveTableAndColumn(tableInfo);
						addTaskLog(newjob.getConnectorPart().getStringInput("table.tableName").getValue()
								+ " finished success!", jobId);
						System.out.println(newjob.getConnectorPart().getStringInput("table.tableName").getValue()
								+ " finished success!" + "jobId=" + jobId);
					} catch (Exception e) {
						addTaskLog("connect error! insert into table"
										+ newjob.getConnectorPart().getStringInput("table.tableName").getValue()
										+ "failed!", jobId);
						hasError = true;
						//删除本次生成的hive表和hdfs文件
						deleteHdfsHiveTable(info.get("oString"), info.get("hiveTableName"));
						if(thread.isAlive()){
							thread.interrupt();//发生异常停止掉
						}
						break;
					}
					f = true;
				}

				if (submission.getStatus().compareTo(SubmissionStatus.FAILED) == 0
						|| submission.getExceptionInfo() != null) {// 任务执行出错，打印出错信息，并记录到任务日志中
					System.out.println(submission.getExceptionInfo());
					long errorTime = System.currentTimeMillis();
					if (errorTime - startTime >= (sqoop_job_timeout_time * 60 * 60 * 1000)) {
						addTaskLog(
								"sqoop run " + newjob.getConnectorPart().getStringInput("table.tableName").getValue()
										+ " timeOut! ", jobId);
					} else {
						addTaskLog("Task " + newjob.getConnectorPart().getStringInput("table.tableName").getValue()
								+ " Exception happened! " + submission.getExceptionInfo(), jobId);
					}
					f = true;
					hasError = true;
					//删除本次生成的hive表和hdfs文件
					deleteHdfsHiveTable(info.get("oString"), info.get("hiveTableName"));
					if(thread.isAlive()){
						thread.interrupt();//发生异常停止掉
					}
					break;
				}
			}

			// 删除本次没有的表
			for (MetadataTablePO noMetadataTablePO : oldMetadataTablePOs) {
				metadataTableService.delete(noMetadataTablePO.getId());
			}

			if (hasError) {// 如果执行过程中存在错误
				taskService.changeResourceTaskStatus(taskPO.getId(), Constant.TASK_STATUS_ERROR);// 任务执行出错
				addTaskLog("Task " + taskPO.getName() + " finished with error!", 0);
			} else {
				taskService.changeResourceTaskStatus(taskPO.getId(), Constant.TASK_STATUS_SUCCESS);// 任务执行成功
				addTaskLog("Task " + taskPO.getName() + " finished success!", 0);
			}

			/************** 修改数据源的更新时间 ******************************/
			//DataResourcePO drp = sce.getDrp();
			//drp.setUpdateDate(new Date());
			//dataResourceService.save(drp);
	}
	
	/**
	 * 在sqoop导入时出现问题，删除已经生成的hdfs文件，hive在创建表时出现问题，删除已经创建的表和hdfs文件
	 * @param HDFSPath
	 * @param HiveTableName
	 */
	private void deleteHdfsHiveTable(String HDFSPath,String HiveTableName){
		String HDFSUrl = Messages.getString("HDFSUrl");
		String HDFSFilePath = HDFSUrl + HDFSPath;
		System.setProperty("HADOOP_USER_NAME", Messages.getString("hiveUser"));
		try {
			try {
				hfs.deleteFdfsByHiveTable(HiveTableName);
				hfs.deleteHiveTrueTable(HiveTableName);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}//如果表存在，删除表
			
			//删除hdfs文件
			Path p = new Path(HDFSFilePath);
			Configuration conf = new Configuration();
			try {
				FileSystem fs = p.getFileSystem(conf);
				boolean isHad = fs.exists(p);
				if (isHad) {
					fs.delete(p, true);
				}
				// boolean b = fs.createNewFile(p);
				fs.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * 为每个任务添加任务日志
	 */
	public void addTaskLog(String content, long jobId) {
		TaskLogPO tlp = new TaskLogPO();
		tlp.setTaskId(taskPO.getId());
		tlp.setLogTime(new Date());
		tlp.setMessage(content);
		tlp.setJobId(jobId);
		logService.saveTaskLog(tlp);
	}

	private void saveTableAndColumn(Map<String, String[]> tableInfo) {
		/************************ 保存元数据表信息 **************************************/
		MetadataTablePO mtp = new MetadataTablePO();
		String resourceId = tableInfo.get("resourceId")[0];
		mtp.setResourceId(resourceId);
		String tableName = tableInfo.get("tableName")[0];
		mtp.setTableName(resourceId + "_" + tableName);
		//mtp.setSchemaName(sce.getDrp().getSchemaName());
		mtp.setCreateDate(new Date());
		mtp.setDeleteFlag(Constant.METADATA_NOT_DELETE);
		mtp.setOpenLevel(Constant.METADATA_NOT_OPEN);
		MetadataTablePO metadataTablePO = metadataTableService.save(mtp);

		/******************** 保存表的所有列的信息 ***************************************/
		String[] cols = tableInfo.get(tableName);
		long tableId = metadataTablePO.getId();

		List<MetadataColumnPO> oldMetadataColumnPOs = new ArrayList<MetadataColumnPO>();// 用于保存原来的
		List<MetadataColumnPO> hasDeleMetadataColumnPOs = new ArrayList<MetadataColumnPO>();// 用于这次没有的
		List<MetadataColumnPO> hadColumnPOs = metadataColumnService.getAllColumnByTableId(tableId);// 得到tableid下所有列
		oldMetadataColumnPOs.addAll(hadColumnPOs);
		hasDeleMetadataColumnPOs.addAll(hadColumnPOs);
		for (int i = 0; i < cols.length; i++) {
			MetadataColumnPO mcp = new MetadataColumnPO();
			String[] tmp = cols[i].split(" ");
			mcp.setColumnName(tmp[0]);
			mcp.setCreateDate(new Date());
			mcp.setDeleteFlag(Constant.METADATA_NOT_DELETE);
			mcp.setOpenLevel(Constant.METADATA_NOT_OPEN);
			mcp.setTableId(tableId);
			mcp.setTypeName(tmp[1]);
			metadataColumnService.save(tableId, mcp);
			for (MetadataColumnPO metadataColumnPO : oldMetadataColumnPOs) {
				if (tmp[0].equals(metadataColumnPO.getColumnName()))
					hasDeleMetadataColumnPOs.remove(metadataColumnPO);
			}
		}

		// 删除这次没有的字段
		for (MetadataColumnPO metadataColumnPO : hasDeleMetadataColumnPOs) {
			metadataColumnPO.setEditDate(new Date());
			metadataColumnService.delete(metadataColumnPO);
		}
	}
	
	/**
	 * 更新sqoop Job配置
	 * */
	private Map<String, String> updateSqoopJob(MJob newjob, Map<String, String[]> tableInfo) {
		Map<String, String> result = new HashMap<String, String>();
		MJobForms connectorForm = newjob.getConnectorPart();
		MJobForms frameworkForm = newjob.getFrameworkPart();
		newjob.setName("ImportJob");
		String tableName = tableInfo.get("tableName")[0];
		connectorForm.getStringInput("table.tableName").setValue(tableName);
		String[] cols = tableInfo.get(tableName);
		String[] tmp = cols[0].split(" ");
		String[] partitionColumns = tableInfo.get("partitionColumn");
		if (partitionColumns[0].equals("")) {
			connectorForm.getStringInput("table.partitionColumn").setValue(tmp[0]);
		} else {
			connectorForm.getStringInput("table.partitionColumn").setValue(partitionColumns[0]);
		}
		String hiveTableName = tableInfo.get("resourceId")[0] + "_" + tableInfo.get("tableName")[0];
		String oString = Messages.getString("sqoopOutput") + new Date().getTime() + tableName;
		frameworkForm.getEnumInput("output.storageType").setValue(Messages.getString("storageType"));
		frameworkForm.getEnumInput("output.outputFormat").setValue(Messages.getString("outputFormat"));// Other
																										// option:
																										// SEQUENCE_FILE
		frameworkForm.getStringInput("output.outputDirectory").setValue(oString);
		frameworkForm.getIntegerInput("throttling.extractors").setValue(1);// 指定map的个数
		result.put("hiveTableName", hiveTableName);
		result.put("oString", oString);
		return result;
	}
	

	public SqConEntity getSce() {
		return sce;
	}

	public void setSce(SqConEntity sce) {
		this.sce = sce;
	}

	public List<Map<String, String[]>> getList() {
		return list;
	}

	public void setList(List<Map<String, String[]>> list) {
		this.list = list;
	}

	public TaskPO getTaskPO() {
		return taskPO;
	}

	public void setTaskPO(TaskPO taskPO) {
		this.taskPO = taskPO;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public MetadataTableService getMetadataTableService() {
		return metadataTableService;
	}

	public void setMetadataTableService(MetadataTableService metadataTableService) {
		this.metadataTableService = metadataTableService;
	}

	public MetadataColumnService getMetadataColumnService() {
		return metadataColumnService;
	}

	public void setMetadataColumnService(MetadataColumnService metadataColumnService) {
		this.metadataColumnService = metadataColumnService;
	}

	public HiveService getHfs() {
		return hfs;
	}

	public void setHfs(HiveService hfs) {
		this.hfs = hfs;
	}

	public DataResourceService getDataResourceService() {
		return dataResourceService;
	}

	public void setDataResourceService(DataResourceService dataResourceService) {
		this.dataResourceService = dataResourceService;
	}

}

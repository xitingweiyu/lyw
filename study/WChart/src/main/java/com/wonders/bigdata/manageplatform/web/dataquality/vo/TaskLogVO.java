package com.wonders.bigdata.manageplatform.web.dataquality.vo;

/**<p>
 * Title: manageplatform_[大数据管理平台]_[任务日志]
 * </p>
 * <p>
 * Description: [任务日志VO]
 * </p>
 * 
 * @author WF
 * @version $Revision$ 2015年3月24日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */

import com.wonders.bigdata.manageplatform.service.log.model.po.TaskLogPO;
import com.wonders.bigdata.manageplatform.service.task.model.po.TaskPO;

import java.util.List;

public class TaskLogVO {
	private TaskPO task;
	private List<TaskLogPO> Logs;
	
	public List<TaskLogPO> getLogs() {
		return Logs;
	}
	public void setLogs(List<TaskLogPO> logs) {
		Logs = logs;
	}
	public TaskPO getTask() {
		return task;
	}
	public void setTask(TaskPO task) {
		this.task = task;
	}

}

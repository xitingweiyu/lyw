package com.wonders.bigdata.manageplatform.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.wonders.bigdata.manageplatform.web.manager.resourcecollect.vo.TaskMonitorVO;


public class  CronExpression {
	private static String BASESTR = "ywMdhms";
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(CronExpression.class);
	
	/*
	 * 解析传过来的时间参数，生成cron表达式
	 * @param TaskVO task
	 * */
	public static String generateExp(TaskMonitorVO task){
		int f = -1;
		String result = "";
		String[] t = task.getBeginTime().split("-| |:");
		if(task.getTimeGap() == 0) {
			task.setTimeGap(1);
		}
		List<String> tmp = new ArrayList<String>();
		Collections.addAll(tmp, t);
		Calendar cal = Calendar.getInstance();
		try{
			cal.set(Integer.parseInt(tmp.get(0)), Integer.parseInt(tmp.get(1))-1,Integer.parseInt(tmp.get(2)), Integer.parseInt(tmp.get(3)), Integer.parseInt(tmp.get(4)), Integer.parseInt(tmp.get(5)));
			String w = cal.get(Calendar.DAY_OF_WEEK)+"";//根据时间获取该时间是星期几
			tmp.add(1, w);//把星期加入到时间数组中
			if(task.getExecuteType() == 3) {//如果认为是间隔时间执行，根据间隔单位改变数组某个位置的字符串值。字符串改为【原值/时间间隔】,如“05/2”。
				f = BASESTR.indexOf(task.getTimeGapUnit());
				tmp.set(f, tmp.get(f) + "/" + task.getTimeGap());
			} else if (task.getExecuteType() == 2) {//如果任务是计划执行，则根据计划执行的值设置标志位。该值的范围是{每周，每月，每天}
				if(task.getTaskFrequency().equals("d")){
					f = 4;
				} else if(task.getTaskFrequency().equals("m")) {
					f = 3;
				} else {
					f = 1;
				}
			}
			
			/*************************生成cron表达式*******************************/
			/**cron表达式是逆序显示的，秒在前，年在后。如“0/15 0/30 * * * ?”
			 * 此方法中星期的特殊字符为‘？’，其余的域的特殊字符为‘*’ 
			 * */
			StringBuffer buf = new StringBuffer();
			int s = tmp.size();
			if(f != 1) {
				for(int i=s-1; i>=0; i--) {
					/**
					 * tmp的顺序是年->周->月->日->时->分->秒
					 * 如果位置在标记的位置前，则把该位置的值置为特殊字符，然后拼成字符串
					 * 否则  如果位置为星期且间隔单位不是星期，则把值替换为特殊字符，拼成字符串，
					 * 其他情况则值不变，拼接成字符串
					 * */
					if(i < f){
						if (i == 1) {//星期
							buf.append("?").append(" ");
						} else if (i == 0) {//年
							buf.append("*");
						} else {
							buf.append("*").append(" ");
						}
					} else {
						if (i == 0) {
							buf.append(tmp.get(i));
						} else if(i == 1){
							if(-1 != tmp.get(i).indexOf('/')){
								buf.append(tmp.get(i)).append(" ");
							}else{
								buf.append("?").append(" ");
							}
						}else{
							buf.append(tmp.get(i)).append(" ");
						}
					}
				}
			} else {
				/**
				 * 如果标记位是星期，把秒、分、时域的值不变，拼接成字符串，天域替换成‘？’，星期域值不变，其他的域替换成‘*’
				 * */
				for(int i=s-1; i>=s-3; i--){
					buf.append(tmp.get(i)).append(" ");
				}
				buf.append("?").append(" ");
				buf.append("*").append(" ");
				buf.append(tmp.get(1)).append(" ");
				buf.append("*");
			}
			result = buf.toString();
		}catch(ArrayIndexOutOfBoundsException e){//捕捉数组越界异常
			logger.error(e.getMessage(), e);
			return "error";
		}
		return result;
	}
	
	/**
	 * 解析cron表达式，生成开始时间，时间间隔、时间间隔单位、任务频率
	 * 
	 * 如果是特殊字符，则把特殊字符的域替换成当前时间的域
	 * 如果不是特殊字符，则值不变
	 * 如果任务的执行方式是间隔执行且某个域包含‘/’，则分割域的值，用前面的值替换当前的值，后面的值为间隔数量，根据当前域的位置确定间隔的单位
	 * 星期域的值无关紧要，不需要进行操作，直接抛弃
	 * 
	 * @param cron表达式
	 * @param 任务执行方式
	 * @param 任务创建时间
	 * */
	public static TaskMonitorVO parseExp(String exp, int type, Date date){
		TaskMonitorVO taskVO = new TaskMonitorVO();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String[] t = exp.split(" ");
		StringBuffer buf = new StringBuffer();
		int len = t.length;
		if(type == Constant.TASK_EXCUTYPE_GAP){//执行方式为固定时间间隔
			for(int i=len-1; i>=0; i--) {
				if(!t[i].equals("*") && !t[i].equals("?")){
					int index = t[i].indexOf('/');
					if(index != -1) {
						taskVO.setTimeGapUnit( BASESTR.charAt(6-i)+"");//获取时间间隔单位
						String[] s = t[i].split("/");
						taskVO.setTimeGap(Integer.parseInt(s[1]));//获取时间间隔
						if(i != 5){
							if(i == 3){
								buf.append(s[0]).append(" ");
							}else if(i == 0){
								buf.append(s[0]);
							}else{
								buf.append(s[0]).append("-");
							}
						}
					} else {
						if(i != 5){
							if(i == 3){
								buf.append(t[i]).append(" ");
							}else if(i == 0){
								buf.append(t[i]);
							}else{
								buf.append(t[i]).append("-");
							}
						}
					}
				} else {
					if(i == 0){
						buf.append(""+cal.get(Calendar.SECOND));
					} else if (i == 1) {
						buf.append(""+cal.get(Calendar.MINUTE)).append("-");
					} else if (i == 2) {
						buf.append(""+cal.get(Calendar.HOUR)).append("-");
					} else if (i == 3) {
						buf.append(""+cal.get(Calendar.DAY_OF_MONTH)).append(" ");
					} else if (i == 4) {
						buf.append(""+(cal.get(Calendar.MONTH)+1)).append("-");
					} else if (i == 6) {
						buf.append(""+cal.get(Calendar.YEAR)).append("-");
					}
				}
			}
		} else if(type == Constant.TASK_EXCUTYPE_FREQ) {//执行方式为计划执行
			int j = 0;
			int k = -1;
			for(int i=len-1; i>=0; i--) {
				if(!t[i].equals("*") && !t[i].equals("?")){
					if(i != 5) {
						if(i == 0){
							buf.append(t[i]);
						} else if(i == 3){
							buf.append(t[i]).append(" ");
						} else {
							buf.append(t[i]).append("-");
						}
					}
					j++;
				} else {
					if(t[i].equals("?")) {
						k = i;
					}
					if(i == 0){
						buf.append(""+cal.get(Calendar.SECOND));
					} else if (i == 1) {
						buf.append(""+cal.get(Calendar.MINUTE)).append("-");
					} else if (i == 2) {
						buf.append(""+cal.get(Calendar.HOUR)).append("-");
					} else if (i == 3) {
						buf.append(""+cal.get(Calendar.DAY_OF_MONTH)).append(" ");
					} else if (i == 4) {
						buf.append(""+(cal.get(Calendar.MONTH)+1)).append("-");
					} else if (i == 6) {
						buf.append(""+cal.get(Calendar.YEAR)).append("-");
					}
				}
			}
			/*************根据*所处的位置判断计划执行的任务频率********************/
			if(j == 3){
				taskVO.setTaskFrequency("d");
			} else if(j == 4 && k == 5) {
				taskVO.setTaskFrequency("m");
			} else if (j == 4 && k == 3){
				taskVO.setTaskFrequency("w");
			}
		} else {//任务的执行方式是执行一次时
			for(int i=len-1; i>=0; i--) {
				if(i != 5) {
					if(i == 0){
						buf.append(t[i]);
					} else if(i == 3){
						buf.append(t[i]).append(" ");
					} else {
						buf.append(t[i]).append("-");
					}
				}
			}
		}
		taskVO.setBeginTime(buf.toString());
		return taskVO;
	}
	
}

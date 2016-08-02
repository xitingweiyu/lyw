/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : dataquality

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2016-04-22 15:23:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bd_index`
-- ----------------------------
DROP TABLE IF EXISTS `bd_index`;
CREATE TABLE `bd_index` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `remark` varchar(2000) default NULL,
  `description` varchar(2000) default NULL,
  `create_date` datetime default NULL,
  `delete_flag` int(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `bd_data_quality`
-- ----------------------------
DROP TABLE IF EXISTS `bd_data_quality`;
CREATE TABLE `bd_data_quality` (
  `id` bigint(20) NOT NULL auto_increment,
  `table_id` bigint(20) default NULL,
  `table_name` varchar(255) default NULL,
  `column_name` varchar(255) default NULL,
  `column_type` varchar(255) default NULL,
  `index_id` bigint(20) default NULL,
  `index_name` varchar(255) default NULL,
  `result` varchar(2000) default NULL,
  `create_date` datetime default NULL,
  `update_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for bd_task
-- ----------------------------
DROP TABLE IF EXISTS `bd_task`;
CREATE TABLE `bd_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `data_resource_id` bigint(20) NOT NULL,
  `execute_type` int(11) NOT NULL,
  `increment_type` int(11) NOT NULL,
  `task_type` int(11) NOT NULL,
  `task_entity_id` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `cron_expression` varchar(255) default NULL,
  `user_id` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `delete_flag` int(11) NOT NULL,
  `collect_way` int(11) default NULL,
  `resource_tables` text,
  `timeout` int(11) default NULL,
  `begin_time` datetime default NULL,
  `table_column` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='任务表';


-- ----------------------------
-- Table structure for bd_task_log
-- ----------------------------
DROP TABLE IF EXISTS `bd_task_log`;
CREATE TABLE `bd_task_log` (
  `id` bigint(20) NOT NULL auto_increment,
  `task_id` bigint(20) NOT NULL,
  `message` varchar(1000) default NULL,
  `log_time` datetime NOT NULL,
  `job_id` bigint(20),
  `type` int(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='任务日志表';
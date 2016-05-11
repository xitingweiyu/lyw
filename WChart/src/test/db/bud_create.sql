drop table if exists TB_USER;
/*==============================================================*/
/* Table: TB_USER                                        */
/*==============================================================*/
create table TB_USER
(
   ID             int not null auto_increment comment '主键',
   LOGIN                varchar(255) not null comment '登录名',
   PASSWORD             varchar(255) not null comment '密码',
   STATUS               tinyint(1) not null comment '激活状态：1：正常、2：锁定',
   EMAIL                varchar(255) comment '邮件',
   PHONE                varchar(255) comment '手机',
   NAME                 varchar(255) comment '用户名称',
   C_TIME               datetime not null comment '注册时间',
   LAST_LOGIN_TIME      datetime comment '最后登录时间',
   LAST_LOGIN_IP        varchar(50) comment '最后登录IP',
   primary key (ID)
);

alter table TB_USER comment '用户';
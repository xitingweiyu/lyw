package com.wonders.bigdata.manageplatform.utils;

import java.sql.Timestamp;

/**
 * <p>
 * Title: manageplatform_[常量]_[模块名]
 * </p>
 * <p>
 * Description: [常量字典定义]
 * </p>
 *
 * @author All
 * @version $Revision$ 2015-03-16
 * @author (latest modification by $Author$)
 * @since 20100901
 */
public class Constant {

    // 资源类型
    public final static int RESOURCE_NOT_OPEN = 1;// 开放等级 不开放

    // 用户申请常量
    public final static int USERDATAAPPLY_STATUS_SEC_IMPORT = 7;// 用户导入数据成功
    public final static int USERDATAAPPLY_STATUS_LEADER_REJECT = 8;// 领导同意管理员驳回
    public final static int USERDATAAPPLY_STATUS_UNLASTAUDIT = 9;// 待授权（管理员创建完成）
    public final static int USERDATAAPPLY_STATUS_TENANT_ACCEPT = 10;// 租户接受
    public final static int USERDATAAPPLY_STATUS_TENANT_REJECT = 11;// 租户不接受
    public final static int USERDATAAPPLY_STATUS_LEADER_END = 12;// 领导终止
    public final static int USERDATAAPPLY_STATUS_ADMIN_TMP = 13;// 管理员暂存

    public final static int USERDATAAPPLY_STATUS_ADMIN_REJECT = 4;// 管理员驳回
    public final static int USERDATAAPPLY_PROCESS_ING = 0;// 申请进行中
    public final static int USERDATAAPPLY_PROCESS_ED = 1;// 申请已经结束

    public final static int USERDATAAPPLY_TYPE_PUBLIC = 0;// 公共包申请
    public final static int USERDATAAPPLY_TYPE_PRIVATE = 1;// 私有包分享申请
    public final static int USERDATAAPPLY_TYPE_APPLY = 2;// 申请包申请记录

    //区分申请包中的表是新增的还是选择之前的
    public final static int APPLYPACKAGE_TABLE_TYPE_NEW=0;//申请包的表是新建的
    public final static int APPLYPACKAGE_TABLE_TYPE_SELECTED=1;//申请包的表是选择的。


    // 数据包常量
    public final static int DATA_DATAPACKAGE_PAGESIZE = 15;// 非登录页面首页表或包的数目

    public final static int DATA_DATAPACKAGE_TYPE_PUBLIC = 0;// 公有数据包
    public final static int DATA_DATAPACKAGE_TYPE_PRIVATE = 1;// 私有数据包
    public final static int DATA_DATAPACKAGE_TYPE_APPLY = 2;// 申请数据包
    public final static int DATA_DATAPACKAGE_TYPE_SHARESET=3;//用户数据分享组装包

    /**
     * 数据包创建失败原因
     */

    public final static int DATA_DATAPACKAGE_FAIL_REASON_USER_NOPLACE = 0;// 失败原因为用户空间不足
    public final static int DATA_DATAPACKAGE_FAIL_REASON_CONNECTION = 1;// 失败原因为网络原因
    public final static int DATA_DATAPACKAGE_FAIL_REASON_SYS_NOPLACE = 2;// 失败原因为平台硬盘不足
    public final static int DATA_DATAPACKAGE_FAIL_REASON_OTHER = 3;// 失败原因为其他
    /**
     * 缺少创建数据包的sql语句
     */
    public final static int DATA_DATAPACKAGE_FAIL_REASON_NO_SQL = 4;
    /**
     * 标示将要创建的数据包中包含已存在的表
     */
    public final static int DATA_DATAPACKAGE_FAIL_REASON_TABLE_EXIST = 5;

    /*
     * 数据包创建失败之后，租户是否寻求管理员帮助
     */
    public final static int DATA_DATAPACKAGE_BEG_HELP_NO = 0;// 未寻求帮助
    public final static int DATA_DATAPACKAGE_BEG_HELP_HAD = 1;// 已经寻求帮助

    public final static int DATA_DATAPACKAGE_CREATE_USER_IMPORT = 3;// 用户导入数据包
    public final static int DATA_DATAPACKAGE_OPEN_STATUS_INIT = -2;// 数据包开放待确认
    public final static int DATA_DATAPACKAGE_OPEN_STATUS_INIT_FAILED = -3;// 数据包开放确认未通过
    public final static int DATA_DATAPACKAGE_OPEN_STATUS_SUCCESS = -4;// 数据包开放确认通过
    public final static int DATA_DATAPACKAGE_OPEN = 1;// 数据包开放
    public final static int DATA_DATAPACKAGE_NOT_OPEN = 0;// 数据包不开放

    // 分析工具路径
    public final static String ANALYSE_TOOLS_URL_DBEXPLORER = "/tools/jdbexplorer/dbe/main.jsp";// JDBExplorer

    // 数据包HIVE关联表
    public final static int DATADPHIVE_NOT_DELETE = 0;// 数据包hive关联表未删除
    public final static int DATADPHIVEDELETE = 1;// 数据包hive关联表已删除
    public final static int DATADPHIVE_STATUS_CREATING = 0;// 数据包hive关联表创建中
    public final static int DATADPHIVE_STATUS_CREATED = 1;// 数据包hive关联表创建完成

    // 角色—权限对应
    public final static String[] AUTH_ROLE_USER = { "login/success", "login/tenant", "datapack", "tenancy2", "tenancy",
            "tools", "uploadPack", "analyse", "initAction", "dbeTreeAction", "dbeSQLQueryAction", "dbeGridAction",
            "dbeAction" };// 用户的角色权限限制

    // 权限
    public final static int AUTHORITY_NOT_DELETE = 0;// 权限/权限组未删除
    public final static int AUTHORITY_DELETE = 1;// 权限/权限组未删除
    public final static int AUTHORITY_TYPE_AUTHOTITY = 0;// 权限类型-权限
    public final static int AUTHORITY_TYPE_GROUP = 1;// 权限类型-权限组

    // 标准目录
    public final static String CATALOG_IS_LEAFNODE_YES = "y";// 标准目录是叶节点
    public final static String CATALOG_IS_LEAFNODE_NO = "n";// 标注目录不是叶子节点

    // 数据库链接错误类型
    public final static int DBLINK_DBTYPE_EMPTY = 1;// 数据库类型为空
    public final static int DBLINK_SUCCESS = 2;// 链接成功
    public final static int DBLINK_ADDRESSORPORT_ERROR = 3;// 数据库IP或端口号错误
    public final static int DBLINK_DBNAME_NOT_EXIT = 4; // 数据库名不存在
    public final static int DBLINK_NAMEORPASSWORD_ERROR = 5;// 数据库用户名和密码错误
    public final static int DBLINK_ERROR = 6;// 数据库连接失败

    /**
     * 标准目录表删除位：已删除
     */
    public final static Integer CATALOG_DELETE = 1;
    /**
     * 标准目录表删除位：未删除
     */
    public final static Integer CATALOG_NOT_DELETE = 0;
    /**
     * 标准目录关联表类型常量，标示类型为表
     */
    public final static Integer CATALOG_TYPE_TABLE = 0;
    public final static int CATALOG_OPEN = 0;// 数据目录开放
    public final static int CATALOG_NOT_OPEN = 1;// 数据目录不开放
    public final static int CATALOG_STATUS_UN_SUBMIT = 0;// 数据目录节点未提交
    public final static int CATALOG_STATUS_SUBMIT = 1;// 数据目录节点已提交
    public final static int CATALOG_STATUS_SUBMIT_PASS = 2;// 数据目录节点审核通过
    public final static int CATALOG_STATUS_SUBMIT_NOT_PASS = 3;// 数据目录节点审核未通过

    public final static Integer CATALOG_TABLE_TYPE_SOURCE = 0;
    public final static Integer CATALOG_TABLE_TYPE_PACKAGE = 1;
    public final static Integer CATALOG_TABLE_TYPE_CHILD = 2;

    /**
     * 标准目录关联表类型常量，标示类型为数据包
     */
    public final static Integer CATALOG_TYPE_PACKAGE = 1;
    public final static int CATALOG_OPEN_LEVEL_OPEN = 0;// 元数据表名对外开放
    public final static int CATALOG_OPEN_LEVEL_NOT_OPEN = 1;// 元数据表名对外不开放
    public final static int CATALOG_TABLE_UN_DELETE = 0;// 数据目录表未删除
    public final static int CATALOG_TABLE_DELETE = 1;// 数据目录表已删除
    public final static int CATALOG_TABLE_STATUS_UN_SUBMIT = 0;// 数据目录表未提交
    public final static int CATALOG_TABLE_STATUS_SUBMIT = 1;// 数据目录表已提交
    public final static int CATALOG_TABLE_STATUS_SUBMIT_PASS = 2;// 数据目录表审核通过
    public final static int CATALOG_TABLE_STATUS_SUBMIT_NOT_PASS = 3;// 数据目录表审核未通过
    public final static int CATALOG_TABLE_OPEN_LEVEL_NOT_OPEN = -1; // 数据目录表不开放
    public final static int CATALOG_TABLE_OPEN_LEVEL_OPEN = 0; // 数据目录表开放

    // sdk 操作日志类型
    public final static Integer SDK_OPERATE_TYPE_CONNECTOR = 0;// 操作类型为连接
    public final static Integer SDK_OPERATE_TYPE_SEARCH = 1;// 操作类型为查询
    public final static Integer SDK_OPERATE_STATUS_SUCCESS = 0;// 操作成功
    public final static Integer SDK_OPERATE_STATUS_FAIL = 1;// 操作失败

    // 组织是否删除标识
    public final static Integer ORGANAZATION_NOT_DELETE = 0;// 机构未删除
    public final static Integer ORGANAZATION_DELETE = 1;// 机构删除
    public final static Integer ORGANAZATION_MAX_SIZE = 200;// 图片最大大小

    // 数据目录关联字段
    public final static int CATALOGCOLUMN_UN_DELETE = 0;// 数据目录关联字段未删除
    public final static int CATALOGCOLUMN_DELETE = 1;// 数据目录关联字段已删除
    public final static int CATALOGCOLUMN_OPEN = 0;// 数据目录关联字段已开放
    public final static int CATALOGCOLUMN_NOT_OPEN = 1;// 数据目录关联字段未开放
    public final static int CATALOG_COLUMN_STATUS_UN_SUBMIT = 0;// 数据目录字段未提交
    public final static int CATALOG_COLUMN_STATUS_SUBMIT = 1;// 数据目录字段已提交
    public final static int CATALOG_COLUMN_STATUS_SUBMIT_PASS = 2;// 数据目录字段审核通过
    public final static int CATALOG_COLUMN_STATUS_SUBMIT_NOT_PASS = 3;// 数据目录字段审核未通过

    // 用户数据包表
    public final static int USERDATAPACKAGE_TYPE_APPLY = 0;// 用户申请包
    public final static int USERDATAPACKAGE_TYPE_PRIVATE = 1;// 用户私有数据包

    // 历史申请表
    public final static int APPLYHISTORY_NOT_DELETE = 0;// 未删除
    public final static int APPLYHISTORY_DELETE = 1;// 已删除
    public final static int APPLYHISTORY_TYPE_APPLY = 0;// 操作的对象为申请（即针对的是bd_user_dataapply表的记录）
    public final static int APPLYHISTORY_TYPE_DATAPACKAGE = 1;// 资产（数据包）

    // 申请操作表
    public final static int APPLYOPERATE_NOT_DELETE = 0;// 未删除
    public final static int APPLYOPERATE_DELETE = 1;// 删除

    public final static int APPLYOPERATE_TYPE_APPLY = 0;// 操作的对象为数据包（申请包，私有包，公共包，即针对的是bd_user_dataapply表的记录）申请
    public final static int APPLYOPERATE_TYPE_OTHER = 1;// 另外，如数据目录开放申请等。

    // 预处理结果表
    public final static int DATAPREPROCESS_TABLE_NOT_DELETE = 0;// 未删除
    public final static int DATAPREPROCESS_TABLE_DELETE = 1;// 已经删除

    // 预处理中间过程状态
    public final static int DATAPREPROCESS_TABLE_STATUS_NO_EXECUTE = 0;// 暂存
    public final static int DATAPREPROCESS_TABLE_STATUS_WAIT_EXECUTE = 1;// 待创建
    public final static int DATAPREPROCESS_TABLE_STATUS_EXECUTEING = 2;// 创建中
    public final static int DATAPREPROCESS_TABLE_STATUS_SUCCESS = 3;// 创建成功
    public final static int DATAPREPROCESS_TABLE_STATUS_FAIL = 4;// 创建失败

    // 数据预处理
    public final static int DATAPREPROCESS_TASK_NOT_DELETE = 0;// 未删除
    public final static int DATAPREPROCESS_TASK_DELETE = 1;// 已经删除

    // 预处理结果
    public final static int DATAPREPROCESS_TASK_STATUS_NO_EXECUTE = 0;// 暂存
    public final static int DATAPREPROCESS_TASK_STATUS_WAIT_EXECUTE = 1;// 待执行
    public final static int DATAPREPROCESS_TASK_STATUS_EXECUTEING = 2;// 执行中
    public final static int DATAPREPROCESS_TASK_STATUS_SUCCESS = 3;// 执行成功
    public final static int DATAPREPROCESS_TASK_STATUS_FAIL = 4;// 执行失败

    public String getUploadFileDir() {
        return uploadFileDir;
    }

    public void setUploadFileDir(String uploadFileDir) {
        this.uploadFileDir = uploadFileDir;
    }

    private String uploadFileDir;
    private String visitUrl;

    // 资源类型
    public final static int RESOURCE_KIND_SQL = 1;// 关系型
    public final static int RESOURCE_KIND_NOSQL = 2;// nosql型
    public final static int RESOURCE_KIND_FILE = 3;// 文件型
    public final static int RESOURCE_KIND_PACKAGE = 4;// 数据包类型
    public final static int RESOURCE_KIND_USER = 5;// 租户上传分享后的资源类型
    public final static int RESOURCE_DELETE = 1;// 资源删除状态
    public final static int RESOURCE_UN_DELETE = 0;// 资源没有被删除状态
    public final static int RESOURCE_CONNECTED = 2; // 资源处于连接状态
    public final static int RESOURCE_DISCONNECTED = 1; // 资源处于断开状态
    public final static int RESOURCE_OPEN_LEVEL_LD = 1;// 资源开放等级 ：落地
    public final static int RESOURCE_OPEN_LEVEL_REFFERENCE = 2; // 资源开放等级 ：引用
    public final static int RESOURCE_OPEN_LEVEL_STRUCTURE = 3; // 资源开放等级 ：结构

    /**
     * 资源数据库类型
     */
    public final static int RESOURCE_DATABASE_MYSQL     = 1; // 数据库 MySQL
    public final static int RESOURCE_DATABASE_ORACLE    = 2; // 数据库 Oracle
    public final static int RESOURCE_DATABASE_MONGODB   = 3; // 数据库 MongoDB
    public final static int RESOURCE_DATABASE_SQLSERVER = 4; // 数据库 SQL Server
    public final static int RESOURCE_DATABASE_REDIS     = 5; // 数据库 Redis
    public final static int RESOURCE_DATABASE_HBASE     = 6; // 数据库 HBase
    public final static int RESOURCE_DATABASE_JSON      = 7; // 数据库 JSON
    public final static int RESOURCE_DATABASE_SYBASE    = 8; // 数据库 Sybase
    /**
     * 导出任务常量
     */
    public final static int EXPORT_TASK_STATUS_ACTIVE = 1;// 暂存
    public final static int EXPORT_TASK_STATUS_STOP = 2;// 待执行
    public final static int EXPORT_TASK_STATUS_RUNNING = 3;// 导出任务正在执行
    public final static int EXPORT_TASK_STATUS_ERROR = 4;// 导出任务执行出错
    public final static int EXPORT_TASK_STATUS_SUCCESS = 5;// 导出任务执行成功
    public final static int EXPORT_TASK_NOT_DELETE = 0;// 导出任务未删除
    public final static int EXPORT_TASK_DELETE = 1;// 导出任务已删除


    /**
     * MySQL版本常量
     */
    public final static String[] MYSQL_VERSION = {
            "MYSQL_5"
    };
    public final static int MYSQL_5 = 10; // MySQL 5 编号

    /**
     * Oracle版本常量
     */
    public final static String[] ORACLE_VERSION = {
            "ORACLE_8",
            "ORACLE_9",
            "ORACLE_10",
            "ORACLE_11"
    };
    public final static int ORACLE_8  = 20; // Oracle 8 编号
    public final static int ORACLE_9  = 21; // Oracle 9 编号
    public final static int ORACLE_10 = 22; // Oracle 10 编号
    public final static int ORACLE_11 = 23; // Oracle 11 编号

    /**
     * MongoDB版本常量
     */
    public final static String[] MONGODB_VERSION = {
            "MONGODB_3"
    };
    public final static int MONGODB_3 = 30; // MongoDB 3 编号

    /**
     * SQL Server版本常量
     */
    public final static String[] SQLSERVER_VERSION = {
            "SQLSERVER_2005",
            "SQLSERVER_2008",
            "SQLSERVER_2012"
    };
    public final static int SQLSERVER_2005 = 40; // SQL Server 2005 编号
    public final static int SQLSERVER_2008 = 41; // SQL Server 2008 编号
    public final static int SQLSERVER_2012 = 42; // SQL Server 2012 编号

    /**
     * Sybase版本常量
     */
    public final static String[] SYBASE_VERSION = {
            "SYBASE_10"
    };
    public final static int SYBASE_10 = 80; // Sybase 10 编号


    // 任务常量
    public final static int TASK_STATUS_ACTIVE = 1;// 任务已激活
    public final static int TASK_STATUS_STOP = 2;// 任务已停止
    public final static int TASK_STATUS_RUNNING = 3;// 任务正在执行
    public final static int TASK_STATUS_ERROR = 4;// 任务执行出错
    public final static int TASK_STATUS_SUCCESS = 5;// 任务执行成功
    public final static int TASK_ID_NULL = 0;// 任务id不存在
    public final static int TASK_NOT_DELETE = 0;// 任务未删除
    public final static int TASK_DELETE = 1;// 任务已删除
    public final static int TASK_EXCUTYPE_ONE = 1;// 任务执行一次
    public final static int TASK_EXCUTYPE_FREQ = 2;// 任务计划执行
    public final static int TASK_EXCUTYPE_GAP = 3;// 任务固定时间间隔执行
    public final static String TASK_GROUP = "group1";// 任务的group名称
    public final static Timestamp TASK_TIME_INIT = Timestamp.valueOf("1970-01-01 00:00:01"); // 控制增量导入，
    // 时间初始化，add
    // by
    // CHT
    // 2015/10/10
    public final static String TASK_ORC_BUCKET = "100";
    public static final String TASK_ORC_TEMP_BUCKET = "10";

    // 元数据列常量
    public final static int METADATA_DELETE = 1;// 元数据列已删除
    public final static int METADATA_NOT_DELETE = 0;// 元数据列未删除
    public final static int METADATA_OPEN = 0;// 元数据列开放权限
    public final static int METADATA_NOT_OPEN = 1;// 元数据列未开放权限
    // 用户申请常量
    public final static int USERDATAAPPLY_NOT_DELETE = 0;// 用户数据包申请未删除
    public final static int USERDATAAPPLY_DELETE = 1;// 用户数据包申请已删除
    public final static int USERDATAAPPLY_STATUS_UNSUBMIT = 0;// 用户申请未提交
    public final static int USERDATAAPPLY_STATUS_UNAUDIT = 1;// 用户申请未审核
    public final static int USERDATAAPPLY_STATUS_FIR_AUDITED = 2;// 用户申请初审通过
    public final static int USERDATAAPPLY_STATUS_FIR_AUDIT_FAILED = 3;// 用户申请初审未通过
    public final static int USERDATAAPPLY_STATUS_RECALL = 4;// 用户申请已撤回
    public final static int USERDATAAPPLY_STATUS_SEC_AUDITED = 5;// 用户申请终审通过
    public final static int USERDATAAPPLY_STATUS_SEC_AUDITED_FAILED = 6;// 用户申请终审未通过
    // 元数据表常量
    public final static int METADATA_TABLE_NAME_NOT_DELETE = 0;// 元数据表名未删除
    public final static int METADATA_TABLE_NAME_DELETE = 1;// 元数据表名已删除
    public final static int METADATA_TABLE_NAME_OPEN_LEVEL_OPEN = 0;// 元数据表名对外开放
    public final static int METADATA_TABLE_NAME_OPEN_LEVEL_NOT_OPEN = 1;// 元数据表名对外不开放
    // 元数据表状态
    public final static int METADATA_TABLE_STATUS_SAVED = 0;// 元数据表已保存
    public final static int METADATA_TABLE_STATUS_SUBMIT = 1;// 元数据表已提交
    public final static int METADATA_TABLE_STATUS_AUDITED = 2;// 元数据表审核通过
    public final static int METADATA_TABLE_STATUS_AUDITED_FAILED = 3;// 元数据表审核未通过
    // 用户数据包常量
    public final static int USERDATAPACKAGE_NOT_DELETE = 0;// 用户数据包拥有记录未删除
    public final static int USERDATAPACKAGE_DELETE = 1;// 用户数据包拥有记录已删除
    public final static int USERDATAPACKAGE_NOT_USED = 0;// 用户数据包未使用
    public final static int USERDATAPACKAGE_USED = 1;// 用户数据包已使用
    public final static int USERDATAPACKAGE_STATUS_PERSON = 2;// 私有上传的包
    public final static int USERDATAPACKAGE_STATUS_PERSON_SUBMIT = 3;// 私有包已提交共享
    public final static int USERDATAPACKAGE_STATUS_PERSON_SUBMIT_PASS = 4;// 提交共享的私有包已通过审核
    public final static int USERDATAPACKAGE_STATUS_PERSON_SUBMIT_NOT_PASS = 5;// 提供共享的私有包未通过审核
    // 用户数据包Sql模板
    public final static int USERDPSQLTEMPLATE_NOT_DELETE = 0;// 用户数据包Sql模板未删除
    public final static int USERDPSQLTEMPLATE_DELETE = 1;// 用户数据包Sql模板已删除
    // 用户
    public final static int USER_NOT_DELETE = 0;// 用户未删除
    public final static int USER_DELETE = 1;// 用户已经删除
    public final static int USER_STATUS_NOT_AUDIT = 0;// 用户未审核
    public final static int USER_STATUS_NORMAL = 1;// 用户状态——正常
    public final static int USER_STATUS_FREEZE = 2;// 用户状态——领导审核未通过
    public final static int USER_STATUS_LOCLED = 3;// 用户状态——锁定
    public final static int USER_STATUS_LOGOFF = 4;// 用户状态——注销
    public final static int USER_STATUS_LOSS = 5;// 用户状态——挂失
    // 数据包常量
    public final static int DATA_DATAPACKAGE_NOT_DELETE = 0;// 数据包未删除
    public final static int DATA_DATAPACKAGE_DELETE = 1;// 数据包已删除
    public final static int DATA_DATAPACKAGE_AUTHORITY_OPEN = 0;// 公有数据包
    public final static int DATA_DATAPACKAGE_AUTHORITY_PRIVATE = 1;// 私有数据包
    public final static int DATA_DATAPACKAGE_CREATE_STATUS_DEFAULT = -1;// 数据包创建状态默认值
    public final static int DATA_DATAPACKAGE_CREATE_STATUS_CREATING = 0;// 数据包创建中
    public final static int DATA_DATAPACKAGE_CREATE_STATUS_CREATED = 1;// 数据包创建完成
    public final static int DATA_DATAPACKAGE_CREATE_STATUS_CREATE_FAIL = 2;// 数据包创建失败
    public final static int DATA_DATAPACKAGE_CREATE_STATUS_INIT = 3;// 数据包创建待确认
    public final static int DATA_DATAPACKAGE_CREATE_STATUS_INIT_FAILED = 4;// 数据包创建确认未通过
    public final static int DATA_DATAPACKAGE_CREATE_STATUS_SPACEFULL = 5;// 用户空间满
    // 用户空间申请常量
    public final static int USERSPACEAPPLY_NOT_DELETE = 0;// 用户拥有记录未删除
    public final static int USERSPACEAPPLY_DELETE = 1;// 用户拥有记录已删除
    public final static int USERSPACEAPPLY_STATUS_UNAUDIT = 0;// 用户申请未审核
    public final static int USERSPACEAPPLY_STATUS_AUDITED = 1;// 用户申请审核通过
    public final static int USERSPACEAPPLY_STATUS_AUDIT_NOT_PASS = 2;// 用户申请审核未通过
    public final static int USERSPACEAPPLY_STATUS_RECALL = 3;// 用户申请已撤回
    // 用户分析常量
    public final static int USEANALYSE_NOT_DELETE = 0;// 数据包分析拥有记录未删除
    public final static int USEANALYSE_DELETE = 1;// 数据包分析拥有记录已删除
    public final static int USEANALYSE_NOT_ANALYSE = 0;// 数据包分析状态为未分析
    public final static int USEANALYSE_ING_ANALYSE = 1;// 数据包分析状态为分析中
    public final static int USEANALYSE_END_ANALYSE = 2;// 数据包分析状态为分析结束
    public final static int USEANALYSE_FALSE_ANALYSE = 3;// 数据包分析状态为分析出错


    //流程引擎申请列表常量
    public final static int SNAKE_ORDER_STATE_TODO = 1; //正在进行流程
    public final static int SNAKE_ORDER_STATE_Temp = 3; //正在暂存流程
    public final static int SNAKE_ORDER_STATE_DONE = 0; //正在完结流程
    public final static int SNAKE_ORDER_STATE_ALL = -1; //所有流程
    public final static int SNAKE_ORDER_STATE_Termin = 2; //终结流程

    public final static int SNAKE_OPERATOR_TRUE = 1; //可操作标识
    public final static int SNAKE_OPERATOR_FALSE = 0; //不可操作标识

    // 数据追踪类型常量
    public final static int DATATRACK_TYPE_RESOURCE = 0;// 数据追踪中的数据源类型
    public final static int DATATRACK_TYPE_RESOURCE_TABLE = 1;// 数据追踪中的源表类型
    public final static int DATATRACK_TYPE_RESOURCE_FIELD = 2;// 数据追踪中的源字段类型
    public final static int DATATRACK_TYPE_DATAPACK_FIELD = 3;// 数据追踪中的包字段类型
    public final static int DATATRACK_TYPE_DATAPACK_TABLE = 4;// 数据追踪中的包字段类型
    public final static int DATATRACK_TYPE_DATAPACK = 5;// 数据追踪中的包类型
    public final static int DATATRACK_TYPE_USER = 6;// 数据追踪中的用户类型

    // 数据等级常量
    public final static int DATAGRADE_NOT_DELETE = 0;// 用户数据包拥有记录未删除
    public final static int DATAGRADE_DELETE = 1;// 用户数据包拥有记录已删除


    // 任务的采集方式
    /**
     * 标示该任务的采集方式是全量导入
     */
    public static int COLLECT_WAY_ALL = 1;
    /**
     * 标示该任务的采集方式是增量导入
     */
    public static int COLLECT_WAY_INCREMENT = 2;
    /**
     * 标示该任务的采集方式是分区导入
     */
    public static int COLLECT_WAY_PARTITON = 3;

    // 分析工具常量
    public final static int ANALYSE_TOOLS_DBEXPLORER = 1;// JDBExplorer
    public final static int ANALYSE_TOOLS_RSTUDIO = 2;// RStudio
    public final static int ANALYSE_TOOLS_SQL = 3;//SQL生成器
    public final static int ANALYSE_TOOLS_PYTHON = 4;//PYTHON
    public final static int ANALYSE_TOOLS_KETTLE =5;//KETTLE
    public final static int ANALYSE_TOOLS_BI = 6;//BI工具
    public final static int ANALYSE_TOOLS_Visualization_Tools =7;//可视化工具

    // 分析工具名称
    public final static String ANALYSE_TOOLSNAME_DBEXPLORER = "JDBExplorer";// JDBExplorer
    public final static String ANALYSE_TOOLSNAME_RSTUDIO = "RStudio";// RStudio
    public final static String ANALYSE_TOOLSNAME_PYTHON = "Python";// RStudio

    // 增量导入约束条件
    public final static String TIME_COL_NAME = "timestamp";

    public final static int USER_GRADE_NOT_DELETE = 0;
    public final static int USER_GRADE_DELETE = 1;
    public final static int USER_GRADE_DATA_GRADE_NOT_OPEN=-1;//用户的数据等级

    //指向租户或者管理员是否能撤销操作，1：可撤销;0：不可撤销
    public final static int OperatorCanCancel = 1;
    public final static int OperatorNotCancel = 0;

    //申请包用户暂存
    public final static int APPLY_USER_TEMP_STORE = 1;
    public final static int APPLY_ADMIN_TEMP_STORE = 2;

    //公共包管理员暂存
    public final static int PUB_ADMIN_TEMP_STORE = 1;


    //任务日志
    public final static int TASK_TYPE_IMPORT = 0; //导入日志
    public final static int TASK_TYPE_EXPORT = 1; //导出任务
    /**
     * 标准目录表删除位：已删除
     */

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }


    /**
     * 用户等级默认值.适用于管理员，领导，超级管理员角色
     */
    public final static int USER_GRADE_DEFAULT = 0;

    /**
     * 数据目录
     */
    public final static int CATALOG_TABLE_STATUS_APPLY = 4;// 数据目录表申请流程中
    public final static int CATALOG_TABLE_STATUS_CREATEING = 5;// 数据目录表创建中
    public final static int CATALOG_TABLE_STATUS_CREATE_SUCCESS = 6;// 数据目录表创建成功
    public final static int CATALOG_TABLE_STATUS_CREATE_FAIL = 7;// 数据目录表创失败
    public final static int CATALOG_TABLE_STATUS_CREATE_REFUSE = 8;// 数据目录表确认生成时，拒接生成
    public final static int CATALOG_TABLE_STATUS_CREATE_NOT_AUTHOR = 9;// 授权不通过


    /**
     * 租户资产目录表
     */
    public final static int USERDATACATALOG_NOT_DELETE = 0;// 租户资产目录未删除
    public final static int USERDATACATALOG_DELETE = 1;// 租户资产目录已经删除
    public final static Long USERDATACATALOG_PARENTID_PRIVATE = -1L;// 租户资产目录的父节点为私有资产
    public final static Long USERDATACATALOG_PARENTID_APPLY = -2L;  // 租户资产目录的父节点为申请资产
    public final static Long USERDATACATALOG_PARENTID_CLASSIFY = -3L;// 租户资产目录的父节点为整理后资产

    /**
     * 租户资产配置常量
     */
    public final static int USERDATACATALOG_TABLE_NOT_DELETE = 0;// 租户资产目录关联表未删除
    public final static int USERDATACATALOG_TABLE_DELETE = 1;// 租户资产目录关联表已经删除
    public final static int USERDATACATALOG_TABLE_CREATE_StATUS_CREATING = 0;// 租户资产目录关联表创建中
    public final static int USERDATACATALOG_TABLE_CREATE_StATUS_SUCCESS = 1;// 租户资产目录关联表创建成功
    public final static int USERDATACATALOG_TABLE_CREATE_StATUS_FAILED = 2;// 租户资产目录关联表创建失败
    public final static int USERDATACATALOG_TABLE_TYPE_PRIVATE = 0;// 租户资产目录关联表是上传的
    public final static int USERDATACATALOG_TABLE_TYPE_APPLY = 1;// 租户资产目录关联表是申请的
    public final static int USERDATACATALOG_TABLE_TYPE_PRE = 2;// 租户资产目录关联表是预处理产生的
    /**
     * 租户资产目录关联字段常量
     */
    public final static int USERDATACATALOG_COLUMN_NOT_DELETE = 0;// 租户资产目录关联字段未删除
    public final static int USERDATACATALOG_COLUMN_DELETE = 1;// 租户资产目录关联字段已经删除

    /**
     * 虚拟机常量
     */
    public final static int VM_NOT_DELETE = 0;//虚拟机未删除
    public final static int VM_DELETE = 1;//虚拟机已删除
    public final static int VM_SYSTEM_TYPE_WINDOWS = 0;//虚拟机系统为Windows
    public final static int VM_SYSTEM_TYPE_LINUX = 1;//虚拟机系统为Linux
    public final static int VM_STATUS_NORMAL = 0;//虚拟机状态为正常
    public final static int VM_STATUS_FAULT = 1;//虚拟机窗台为故障


    /*
     *python 配置文件
     */
    public final static int OPREATE_DATA_LOG_TYPE_RSTUDIO = 0;// 数据操作日志工具是rstudio
    public final static int OPREATE_DATA_LOG_TYPE_JDBEXPORT = 1;// 数据操作日志工具是JDBEXPORT
    public final static int OPREATE_DATA_LOG_TYPE_PYTHON= 1;// 数据操作日志工具是PYTHON

    public final static int USER_PYTHON_NOT_DELETE = 0;// 用户Python算法包未删除
    public final static int USER_PYTHON_DELETE = 1;// 用户Python算法包已删除

    public final static int USER_PYTHON_TYPE_WATING = 0;// 用户Python算法包待扫描
    public final static int USER_PYTHON_NORMAL = 1;// 用户Python算法包可正常使用
    public final static int USER_PYTHON_NOT_NORMAL = 2;// 用户Python算法包不正常

    /**
     * 数据表分享申请常量
     */
    public final static String USER_TABLESHATE_DAFAULTRESOURCE_ID = "0"; //用户数据表分享后默认包id
    public final static int USER_TABLESHARE_INIT = 0; //用户数据表分享初始状态 （未分享）
    public final static int USER_TABLESHARE_COMMIT = 1; //用户数据表分享已提交 （待审核）
    public final static int USER_TABLESHARE_AUDIT_PASS = 2; //用户数据表分享审核通过
    public final static int USER_TABLESHARE_AUDIT_NOTPASS = 3; //用户数据表分审核未通过
    public final static int USER_TABLESHARE_RECALL = 4;//用户数据表分享撤回

    /**
     * 租户上传算法工具包常量
     */
    public final static int USER_TOOLS_PREPROCESS = 0;
    public final static int USER_TOOLS_ALGORITHM = 1;
    public final static int USER_TOOLS_VISUAL = 2;

    //用户工具状态
    public final static int USER_TOOLS_UNINSTALL = 0;
    public final static int USER_TOOLS_NORMAL = 1;
    public final static int USER_TOOLS_NOTABLEUSE = 2;

    //删除位判断
    public final static int USER_TOOLS_NOTDELETE = 0;
    public final static int USER_TOOLS_DELETE = 1;
    //工具类型判断位
    public final static int USER_TOOLS_KIND_WINOPEN = 0;//工具以window.open方式打开
    public final static int USER_TOOLS_KIND_LOADOPEN = 1;//工具以loadMainPage方式打开
    public final static int USER_TOOLS_KIND_HIVEABLE = 2;//工具支持hive，c/s类型
    public final static int USER_TOOLS_KIND_HIVEUNABLE = 3;//工具不支持hive，c/s类型
    public final static int USER_TOOLS_KIND_HIVEUNABLE_WINOPEN = 4;//不支持hive的B/s类型，工具以window.open方式打开

    //判断分析任务中导出的表是否导出成功的状态位
    public final static int USER_EXPORT_SUCCESS = 0;//导出成功
    public final static int USER_EXPORT_UNSUCCESS = 1;//导出失败
    public final static int USER_EXPORT_PREPARING = 2;//正在导出

    /**
     * MySQL版本常量
     */
    public final static String[] SS_VERSION = {
            "SS_VERSION_5"
    };

    // 图表id
    public final static int CHART_TYPE_BAR_BASE = 1;
    public final static int CHART_TYPE_BAR_HORIZONTAL = 2;
    public final static int CHART_TYPE_BAR_ACCUMULATIVE  = 3;
    public final static int CHART_TYPE_BAR_ACCUMULATIVE_HORIZONTAL = 4;
    public final static int CHART_TYPE_LINE_BASE = 5;
    public final static int CHART_TYPE_LINE_HORIZONTAL = 6;
    public final static int CHART_TYPE_LINE_ACCUMULATIVE = 7;
    public final static int CHART_TYPE_LINE_STACKED_AREA = 8;
    public final static int CHART_TYPE_PIE_BASE = 9;
    public final static int CHART_TYPE_PIE_NIGHTINGALL = 10;
    public final static int CHART_TYPE_PIE_ANNULUS = 11;
    public final static int CHART_BOX_DELETE = 1;
    public final static int CHART_BOX_UN_DELETE = 0;

}

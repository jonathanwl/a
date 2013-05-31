/*
 * 共享数据、接口、类
 */
package com.wasoft.calldb;

/**
 * <p>Title: 共享数据</p>
 * <p>Description: calldb 使用的共享静态数据</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: wasoft</p>
 * @version 1.0
 */

public class ShareData
{

    /** logger level name */
    public static final String LOG_NAME = "com.wasoft.calldb";
    /** logger config file name */
    public static final String LOG_CFG_FILENAME = "log4j.properties";//"log.calldb.properties";

    /** SQL语句参数类型为输入 */
    public static final int SQL_INPUT = 0;
    /** SQL语句参数类型为输出 */
    public static final int SQL_OUTPUT = 1;
    /** SQL语句参数类型为输入输出 */
    public static final int SQL_INPUT_OUTPUT = 2;

    /** 服务器ADDRESS */
    static final String SRV_ADDRESS = "192.168.0.100";
    /** 服务器PORT */
    static final int SRV_PORT = 7001;
    /** 服务器登录用户名 */
    static final String SRV_USER = "weblogic";
    /** 服务器登录用户密码 */
    static final String SRV_PASSWORD = "weblogic";
    /** 服务器JDBC数据源名 */
    static final String DB_SOURCE = "sybDataSource";
    /** 服务器JDBC数据源URL */

    static final String DB_URL = "t3://";
    /** 服务器JDBC数据源FACTORY */
    static final String DB_INITIAL_CONTEXT_FACTORY =
        "weblogic.jndi.WLInitialContextFactory";

    /** 数据库连接选择：0-web server; 1-jdbc */
    static final int SQL_CONNECT_WEB = 0;
    static final int SQL_CONNECT_JDBC = 1;
    static final int SQL_CONNECT_SEL = SQL_CONNECT_JDBC;

    public ShareData()
    {
    }

    public String toString()
    {
        return getClass().getName();
    }

}

/*
 * �������ݡ��ӿڡ���
 */
package com.wasoft.calldb;

/**
 * <p>Title: ��������</p>
 * <p>Description: calldb ʹ�õĹ���̬����</p>
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

    /** SQL����������Ϊ���� */
    public static final int SQL_INPUT = 0;
    /** SQL����������Ϊ��� */
    public static final int SQL_OUTPUT = 1;
    /** SQL����������Ϊ������� */
    public static final int SQL_INPUT_OUTPUT = 2;

    /** ������ADDRESS */
    static final String SRV_ADDRESS = "192.168.0.100";
    /** ������PORT */
    static final int SRV_PORT = 7001;
    /** ��������¼�û��� */
    static final String SRV_USER = "weblogic";
    /** ��������¼�û����� */
    static final String SRV_PASSWORD = "weblogic";
    /** ������JDBC����Դ�� */
    static final String DB_SOURCE = "sybDataSource";
    /** ������JDBC����ԴURL */

    static final String DB_URL = "t3://";
    /** ������JDBC����ԴFACTORY */
    static final String DB_INITIAL_CONTEXT_FACTORY =
        "weblogic.jndi.WLInitialContextFactory";

    /** ���ݿ�����ѡ��0-web server; 1-jdbc */
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

/*
 * 呼叫数据库唯一类
 */
package com.wasoft.calldb.business;

import javax.sql.*;
import com.wasoft.calldb.*;

/**
 * <p>
 * Title: 呼叫数据库唯一类
 * </p>
 * <p>
 * Description: 呼叫数据库唯一类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: wasoft
 * </p>
 * 
 * @version 1.0
 */

public class DbSingleton {
	public String toString() {
		return getClass().getName();
	}

	public static DbSingleton makeDbSingleton() {
		if (dbSingleton == null) {
			dbSingleton = new DbSingleton();
		}
		return dbSingleton;
	}

	/**
	 * 初始化日志方法
	 */
	public static void setInitLog() throws CallDbException {
		try {
			if (initCount == 0) {
				org.apache.log4j.PropertyConfigurator
						.configure(ShareData.LOG_CFG_FILENAME);
				xlog = org.apache.log4j.Logger.getLogger(ShareData.LOG_NAME);
				initCount++;
			}
		} catch (Exception err) {
			throw new CallDbException("系统初始化失败[DbSingleton.setInitLog()]\n"
					+ err.getMessage());
		}
	}

	/** 得到公积金数据库连接 */
	public com.wasoft.calldb.BasicOperation getGjjDb() throws CallDbException {
		if (this.ds == null) {
			throw new CallDbException("公积金数据库服务器连接信息未设置!");
		} else {
			return new com.wasoft.calldb.BasicOperation(ds, databaseType);
		}
	}

	/** 得到公积金数据库连接 */
	public com.wasoft.calldb.BasicOperation getTempDb(DataSource ds,
			int databaseType) throws CallDbException {
		return new com.wasoft.calldb.BasicOperation(ds, databaseType);
	}

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public DataSource getDataSource() {
		return this.ds;
	}

	public int getDatabaseType() {
		return this.databaseType;
	}

	public void setDatabaseType(int databaseType) {
		this.databaseType = databaseType;
	}

	public DbFactory getDbFactory() {
		if (dbFactory == null) {
			dbFactory = new DbFactory();
		}
		return dbFactory;
	}

	// --------------------------------------------------------------------------
	private DbSingleton() {
	}

	private static DbSingleton dbSingleton = null;

	/** 数据库工厂 */
	private DbFactory dbFactory = null;

	/** 数据源 */
	private DataSource ds = null;

	/** 数据库类型 */
	private int databaseType = com.wasoft.calldb.BasicOperation.DB_TYPE_SYBASE;

	/** 临时数据源 */
	@SuppressWarnings("unused")
	private DataSource tempDs = null;

	@SuppressWarnings("unused")
	private int tempDbType = com.wasoft.calldb.BasicOperation.DB_TYPE_SYBASE;

	@SuppressWarnings("unused")
	private static org.apache.log4j.Logger xlog = null;

	private static int initCount = 0;

	// --------------------------------------------------------------------------
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		DbSingleton dbSingleton1 = DbSingleton.makeDbSingleton();
	}

}

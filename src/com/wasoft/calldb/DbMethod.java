package com.wasoft.calldb;

import java.util.*;
import javax.sql.*;

import com.wasoft.Constant;
import com.wasoft.calldb.business.*;
import com.wasoft.calldb.sql.Row;
import com.wasoft.model.xtgl.*;
import com.wasoft.util.RandomGUID;

/**
 * <p>Title: 客户数据库调用接口</p>
 * <p>Description: 客户数据库调用接口</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: wasoft</p>
 * @version 1.0
 */

public class DbMethod
{

    /**
     * 初始化构造
     * @param ds 数据源
     * @param databaseType
     * @param path root path
     */
    public DbMethod(DataSource tempDs, int tempDbType)
        throws CallDbException
    {
        this.tempDs = tempDs;
        this.tempDbType = tempDbType;
        dbSingleton = DbSingleton.makeDbSingleton();    
        dbSingleton.setInitLog();
    }

    public String toString()
    {
        return getClass().getName();
    }

    public static DbMethod makeDbSingleton()
    {
        if (dbMethod == null)
        {
            dbMethod = new DbMethod();
        }
        return dbMethod;
    }

    /**
     * 设置数据源及数据库类型
     * @param ds 数据源
     * @param databaseType
     * @param path root path
     */
    public void setDataSource(DataSource ds, int databaseType)
        throws CallDbException
    {
        dbSingleton.setDataSource(ds);
        dbSingleton.setDatabaseType(databaseType);
        //dbSingleton.setInitLog();
    }

    /**
     * 执行数据库操作
     * @param procedureName 调用过程约定名
     * @param obj 参数值对象
     */
    public void Execute(String procedureName, Object obj)
        throws CallDbException
    {
        this.Execute(procedureName, obj, null);
    }

    /**
     * 执行数据库操作
     * @param procedureName 调用过程约定名
     * @param obj 参数值对象
     * @throws CallDbException
     */
    public void Execute(String procedureName, Object obj, User user)
        throws
        CallDbException
    {
        xlog.debug("====================");
        xlog.debug("Entry Execute(" + procedureName
                   + ", " + (obj == null ? "null" : obj.getClass().getName()) +
                   ", "
                   + (user == null ? "null" : user.getUserid()) + ")");
        /*****************begindbtrace***********************/
        boolean bDbtrace=Constant.bDbtrace;
        Dbtrace dbtrace = new Dbtrace((new RandomGUID()).toString());
        if (user!= null&&bDbtrace) {
			try {
				dbtrace.setUserid(user.getUserid());
				dbtrace.setIp(user.getLoginip());
				dbtrace.setModule_name(user.getModule_name().trim());
				dbtrace.setWebservice_method(user.getWebservice_method());
				dbtrace.setStart_time(new Date());
				Execute("Common_BEGINDBTRACE", dbtrace);
			} catch (Exception e) {
				xlog.error("begin dbtrace err:" + e.getMessage());
			}
		}
		/**************************enddbtrace********************/
        DbFactory df = dbSingleton.getDbFactory();
        AbstractDbInterface di = df.getDbInterface(procedureName);
        di.setDatabaseType(dbSingleton.getDatabaseType());
        di.Execute(procedureName, obj, user, tempDs, tempDbType);
        /************************begindbtrace************************/
        if (user!= null&&bDbtrace){
	        try{
				dbtrace.setEnd_time(new Date());			
				dbtrace.calDuration();
				dbtrace.setRet_count(0);	
				dbtrace.setMethod_name(user.getMethod_name().trim().equals("")?procedureName:user.getMethod_name().trim());
				Execute("Common_ENDDBTRACE", dbtrace);
	        }
	        catch(Exception e){
	        	xlog.error("end dbtrace err:" + e.getMessage());
	        }
        }
		/*********************enddbtrace***********************/     
        xlog.debug("Return Execute");
    }

    /**
     * 执行数据库操作，有结果集
     * @param procedureName 调用过程约定名
     * @param obj 参数值对象
     * @return 结果集向量
     * @throws CallDbException
     */
    public List <Row>Open(String procedureName, Object obj)
        throws CallDbException
    {
        return this.Open(procedureName, obj, null);
    }

    /**
     * 执行数据库操作，有结果集
     * @param procedureName 调用过程约定名
     * @param obj 参数值对象
     * @return 结果集向量
     * @throws CallDbException
     */
    public List <Row> Open(String procedureName, Object obj, User user)
        throws CallDbException{
        xlog.debug("Entry Open(" + procedureName
                   + ", " + (obj == null ? "null" : obj.getClass().getName()) +
                   ", "
                   + (user == null ? "null" : user.getUserid()) + ")");
         /*****************begindbtrace***********************/
        boolean bDbtrace=Constant.bDbtrace;
        Dbtrace dbtrace = new Dbtrace((new RandomGUID()).toString());
        if (user!= null&&bDbtrace) {
			try {
				xlog.debug("===============start==========");
				dbtrace.setUserid(user.getUserid());
				dbtrace.setIp(user.getLoginip());
				dbtrace.setModule_name(user.getModule_name().trim());
				dbtrace.setWebservice_method(user.getWebservice_method());
				dbtrace.setStart_time(new Date());
				Execute("Common_BEGINDBTRACE", dbtrace);
			} catch (Exception e) {
				xlog.error("begin dbtrace err:" + e.getMessage());
			}
		}
		/**************************enddbtrace********************/
        DbFactory df = dbSingleton.getDbFactory();
        AbstractDbInterface di = df.getDbInterface(procedureName);
        di.setDatabaseType(dbSingleton.getDatabaseType());
        List <Row> ret = di.Open(procedureName, obj, user, tempDs, tempDbType);        
        /************************begindbtrace************************/
        if (user!= null&&bDbtrace){
	        try{
	        	xlog.debug("===============end==========");
				dbtrace.setEnd_time(new Date());			
				dbtrace.calDuration();
				dbtrace.setRet_count(ret.size());	
				dbtrace.setMethod_name(user.getMethod_name().trim().equals("")?procedureName:user.getMethod_name().trim());
				Execute("Common_ENDDBTRACE", dbtrace);
	        }
	        catch(Exception e){
	        	xlog.error("end dbtrace err:" + e.getMessage());
	        }
        }
		/*********************enddbtrace***********************/     

        xlog.debug("Return Open");
        return ret;
    }

    public List <Row>Open(String procedureName, Object obj, int pagesize,int pagenum)
    					throws CallDbException
	{
	    return this.Open(procedureName, obj, null, pagesize, pagenum);
	}
    
    public List <Row> Open(String procedureName, Object obj, User user,int pagesize,int pagenum)
    										throws CallDbException
	{
	    xlog.debug("====================");
	    xlog.debug("Entry Open(" + procedureName
	               + ", " + (obj == null ? "null" : obj.getClass().getName()) +
	               ", "
	               + (user == null ? "null" : user.getUserid()) + ", " + pagesize + ", " + pagenum +")");
	    /*****************begindbtrace***********************/
	     boolean bDbtrace=Constant.bDbtrace;
	     Dbtrace dbtrace = new Dbtrace((new RandomGUID()).toString());
	     if (user!= null&&bDbtrace) {
			try {
				xlog.debug("===============start==========");
				dbtrace.setUserid(user.getUserid());
				dbtrace.setIp(user.getLoginip());
				dbtrace.setModule_name(user.getModule_name().trim());
				dbtrace.setWebservice_method(user.getWebservice_method());
				dbtrace.setStart_time(new Date());
				Execute("Common_BEGINDBTRACE", dbtrace);
			} catch (Exception e) {
				xlog.error("begin dbtrace err:" + e.getMessage());
			}
		}
		/**************************enddbtrace********************/
	    
	    DbFactory df = dbSingleton.getDbFactory();
	    AbstractDbInterface di = df.getDbInterface(procedureName);
	    di.setDatabaseType(dbSingleton.getDatabaseType());
	    List <Row> ret = di.Open(procedureName, obj, user, tempDs, tempDbType, pagesize, pagenum); 
	    /************************begindbtrace************************/
	     if (user!= null&&bDbtrace){
		        try{
		        	xlog.debug("===============end==========");
					dbtrace.setEnd_time(new Date());
					dbtrace.calDuration();
					dbtrace.setRet_count(ret.size());	
					dbtrace.setMethod_name(user.getMethod_name().trim().equals("")?procedureName:user.getMethod_name().trim());
					Execute("Common_ENDDBTRACE", dbtrace);
		        }
		        catch(Exception e){
		        	xlog.error("end dbtrace err:" + e.getMessage());
		        }
	     }
			/*********************enddbtrace***********************/    
	    xlog.debug("Return Open");
	    return ret;
	}
    
    /**
     * 得到数据库类型
     * @return
     */
    public int getDatabaseType()
    {
        return dbSingleton.getDatabaseType();
    }

    public DataSource getDataSource()
    {
      return dbSingleton.getDataSource();
    }
//--------------------------------------------------------------------------
    private DbMethod()
    {
        this.tempDs = null;
        dbSingleton = DbSingleton.makeDbSingleton();
    }

    private static DbMethod dbMethod = null;
    private DbSingleton dbSingleton;
    private boolean bDbtrace = false;
    /** 数据源集合 */
    private DataSource tempDs = null;
    private int tempDbType = BasicOperation.DB_TYPE_SYBASE;

    private static org.apache.log4j.Logger xlog = org.apache.log4j.Logger.
        getLogger(com.wasoft.calldb.ShareData.LOG_NAME);
//--------------------------------------------------------------------------

	public boolean isBDbtrace() {
		return bDbtrace;
	}

	public void setBDbtrace(boolean dbtrace) {
		bDbtrace = dbtrace;
	}
	
	class DataSourceInfo{
		String id;
		DataSource ds;
		int DbType;
	}
}

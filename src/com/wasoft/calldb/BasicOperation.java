/*
 * 数据库基础操作
 */
package com.wasoft.calldb;

import java.sql.*;
import java.util.*;
import javax.sql.*;

import org.mortbay.log.Log;

import com.wasoft.calldb.sql.*;
import com.wasoft.calldb.sql.column.DoubleColumn;
import com.wasoft.calldb.sql.column.IntColumn;

/**
 * <p>Title: 数据库基础操作</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: wasoft</p>
 * @version 1.0
 */

public class BasicOperation
{
    /**
     *
     * @param ds 数据源
     * @param databaseType 数据库类型
     * @throws CallDbException
     */
    public BasicOperation(DataSource ds, int databaseType)
        throws CallDbException
    {
        if (ds == null)
        {
            throw new CallDbException("BasicOperation: 服务器连接数据源不能为NULL!");
        }
        this.ds = ds;
        this.databaseType = databaseType;
    }

    public String toString()
    {
        return getClass().getName();
    }

    public void disconnect()
        throws CallDbException
    {
        clearParameters();
        try
        {
            if (cnt != null && !cnt.isClosed())
            {
                cnt.close();
                cnt = null;
            }
        }
        catch (Exception e)
        {
            throw new CallDbException(e.getMessage());
        }
    }
	public void closestmt()
		throws CallDbException
	{
	  try
	  {
		  if (stmt != null)
          {
              stmt.close();
              stmt = null;
          }
		if (this.pstmt != null)
		{
		  pstmt.close();
		  pstmt = null;
		}
		if (this.cstmt != null)
		{
		  cstmt.close();
		  cstmt = null;
		}
	  }
	  catch(Exception e)
	  {
		throw new CallDbException(e.getMessage());
	  }
	}
    /**
     * 清除参数信息
     */
    public void clearParameters()
        throws CallDbException
    {
        try
        {
            if (cnt != null && !cnt.isClosed())
            {
                if (stmt != null)
                {
                    stmt.close();
                    stmt = null;
                }
                if (pstmt != null)
                {
                    pstmt.close();
                    pstmt = null;
                }
                if (cstmt != null)
                {
                    cstmt.close();
                    cstmt = null;
                }
            }
            this.sqlParameterExt = null;
        }
        catch (Exception err)
        {
            throw new CallDbException("关闭数据集操作失败：" + err.getMessage());
        }
    }

    public boolean connect()
        throws CallDbException
    {
        try
        {
            if (cnt == null || cnt.isClosed())
            {
                cnt = ds.getConnection();
            }
        }
        catch (Exception err)
        {
            throw new CallDbException("连接数据库操作失败：" + err.getMessage());
        }
        return true;
    }

    public void setSqlParameterExt(SqlParameterExt sqlParameterExt)
    {
        this.sqlParameterExt = sqlParameterExt;
    }

    /**
     * 查询
     * @param sqlStatement 查询语句
     * @param scroll 滚动
     * @param rw 读写
     * @return 结果集
     * @throws CallDbException
     */
    public ResultSet query(String sqlStatement, int scroll, int rw)
        throws CallDbException
    {
        ResultSet rs = null;
        xlog.info("SQL=[" + sqlStatement + "]");
        try
        {
            this.connect();
            if (sqlParameterExt != null && sqlParameterExt.getSize() > 0)
            {
                // 使用PreparedStatement，设置所有的sqlParameter
                pstmt = cnt.prepareStatement(sqlStatement, scroll, rw);
                pstmt.clearParameters();
                sqlParameterExt.setBoParameter(pstmt, databaseType);
                xlog.debug("before query");
                rs = pstmt.executeQuery();
                xlog.debug("after query");
            }
            else
            {
                // 使用规范的Statement
                stmt = cnt.createStatement(scroll, rw);
                xlog.debug("before query");
                rs = stmt.executeQuery(sqlStatement);
                xlog.debug("after query");
            }
            // 保存结果到行对象的列表中
            return rs;
        }
        catch (Exception err)
        {
            throw new CallDbException("查询数据库操作失败：" + err.getMessage());
        }
    }

    /**
     * 
     * @param sqlStatement
     * @param scroll
     * @param rw
     * @param pagesize 	每页记录数
     * @param pagenum 	欲查询第几页
     * @return
     * @throws CallDbException
     */
    public ResultSet query(String sqlStatement, int scroll, int rw, int pagesize,int pagenum)
    							throws CallDbException
	{    	 
    	if (pagesize != 0 && pagenum > 0)
    	{
    		int minrn;	//欲查询页第一行在整个查询记录中所对应的行数
        	int maxrn;	//欲查询页最后行在整个查询记录中所对应的行数
        	try
        	{	        	
	        	minrn = pagesize * (pagenum - 1);
	        	maxrn = pagesize * pagenum;
	        	
		    	switch (this.databaseType)
		        {
		            case BasicOperation.DB_TYPE_ORACLE:
		            	sqlStatement = "select * from (select rownum rn,a.* from (select * from (" + sqlStatement +
		            					") where rownum<=" + maxrn + ") a) b where b.rn>" + minrn;
		                break;
		            case BasicOperation.DB_TYPE_INFORMIX:
		            	throw new CallDbException("不支持的数据库类型!");
		                
		            case BasicOperation.DB_TYPE_MSSQLSERVER:
		            	throw new CallDbException("不支持的数据库类型!");
		            	
		            case BasicOperation.DB_TYPE_DB2:
		            	//as400
		            	if (((GjjmxDataSource)ds).getDriverClassName().indexOf("as400") > 0)
		            	{
		            		sqlStatement = "select * from (" + sqlStatement + ") a where rrn(a)>" + minrn + " and rrn(a)<=" + maxrn;
		            	}
		            	else//db2udb
		            	{
		            		sqlStatement = "select * from (select rownumber() over()  rn,a.* from (" + sqlStatement + ") a) b " +
		            					"where b.rn >" + minrn + " and b.rn<=" + maxrn;
		            	}		            	
		                break;
		            case BasicOperation.DB_TYPE_SYBASE:
		            	sqlStatement = "select rn=identity(12), a.* into #tmp_table from (" + sqlStatement + ") a " +
		            					"select * from #tmp_table where rn >" + minrn + " and rn<=" + maxrn;
		                break;
		            default:
		                throw new CallDbException("未定义的数据库类型!");
		        }
        	}
        	catch(Exception e)
        	{
        		throw new CallDbException(e.getMessage());
        	}
    	}
    	
    	return query(sqlStatement, scroll, rw);
	}
    private ResultSet getSummary(String sqlStatement, int pagesize,int pagenum) throws Exception{
    	 StringBuffer params = new StringBuffer();
    	 ResultSet rs=null;
    	 String paramstr="";
    	 if (pagesize != 0 && pagenum > 0){
    		 if(!hjparameter.trim().equals("")){
    			    String[] hjparams=hjparameter.split(",");
    			    params.append(",");
    				for (int i = 0; i < hjparams.length; i++) {
    					String param="sum("+hjparams[i]+") "+hjparams[i];
    					params.append(param).append(",");
    				}
    				paramstr=params.substring(0, params.length()-1);
    		 }
    		 String sqlStatement1=sqlStatement;
    		 if(sqlStatement.indexOf("order")!=-1){
    			 sqlStatement1=sqlStatement1.substring(0,sqlStatement1.indexOf("order"));
    		 }
	    	  rs = query("select count(*) cnt"+paramstr+" from (" + sqlStatement1 + ")", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	    	 if (!rs.next()){
	    		throw new CallDbException("查询总记录数出错!");
	    	 }
        }
    	return rs;
    }
    public List <Row> queryToList(String sqlStatement, int pagesize,int pagenum) throws CallDbException{
	    xlog.debug("Enter BasicOperation.queryToList()");
	    ResultSet rs = query(sqlStatement,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, pagesize, pagenum);
	    try
	    {
	    	 List<Row> list = toList(rs);
	    	 int count =0;
	    	 ResultSet rs1 = getSummary(sqlStatement, pagesize, pagenum);
	    	 if(rs1!=null){
		    	 count = rs1.getInt("cnt"); 	//总记录	        	
		    	 int pages = count / pagesize;	//总页数
		    	 if (count%pagesize != 0){
		    		pages += 1;
		    	 }
		    	 if(pagenum > pages){
		    		pagenum = pages;
		    	 }
		    	 xlog.debug("总记录数：" + count + "，总页数：" + pages);
	    	 }
	    	 if (list.size() > 0){
	    		 Row row = list.get(0);	    		
	    		 row.addColumns(new Column[]{new IntColumn("count", count)}, new int[]{Types.INTEGER});
	    		 if(!hjparameter.trim().equals("")){
	    			 String[] hjparams=hjparameter.split(",");
    				 for (int i = 0; i < hjparams.length; i++) {
    					 String param=hjparams[i];
    					 double paramvalue=0;
    					 if(rs1!=null){
    					     paramvalue=rs1.getDouble(param);
    					 }
    					 row.addColumns(new Column[]{new DoubleColumn(param+"hj",paramvalue)}, new int[]{Types.DOUBLE});
    				 }
	    		 }
	    	 }
	         return list;
	    }
	    catch (Exception err)
	    {
	        throw new CallDbException(err.getMessage());
	    }
	}
    
    /**
     * 查询是否有记录
     * @param sqlStatement
     * @return true-有结果
     * @throws CallDbException
     */
    public boolean queryResult(String sqlStatement)
        throws CallDbException
    {
        ResultSet rs = query(sqlStatement,
                             ResultSet.TYPE_FORWARD_ONLY,
                             ResultSet.CONCUR_READ_ONLY);
        try
        {
            if (rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception err)
        {
            throw new CallDbException(err.getMessage());
        }
    }

    /**
     * 查询
     * @param sqlStatement 查询语句
     * @param scroll 滚动
     * @param rw 读写
     * @return 结果集
     * @throws CallDbException
     */
    public ResultSet query(String sqlStatement)
        throws CallDbException
    {
        return query(sqlStatement,
                     ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public ResultSet queryToScroll(String sqlStatement)
        throws CallDbException
    {
        return query(sqlStatement,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY
                     );
    }

    public List <Row> queryToList(String sqlStatement)
        throws CallDbException
    {
        xlog.debug("Enter BasicOperation.queryToList()");
        ResultSet rs = query(sqlStatement);
        try
        {
            return toList(rs);
        }
        catch (Exception err)
        {
            throw new CallDbException(err.getMessage());
        }
    }

    public int update(String sqlStatement)
        throws CallDbException
    {
        int noOfRows = 0;

        xlog.info("SQL=[" + sqlStatement + "]");
        try
        {
            this.connect();
            if (sqlParameterExt != null && sqlParameterExt.getSize() > 0)
            {
                // Use a PreparedStatement and set all values
                pstmt = cnt.prepareStatement(sqlStatement);
                pstmt.clearParameters();
                sqlParameterExt.setBoParameter(pstmt, databaseType);
                noOfRows = pstmt.executeUpdate();
            }
            else
            {
                // Use a regular Statement
                stmt = cnt.createStatement();
                noOfRows = stmt.executeUpdate(sqlStatement);
            }
            return noOfRows;
        }
        catch (Exception err)
        {
            throw new CallDbException("更新数据库操作失败：" + err.getMessage());
        }
    }

    public boolean execute(String sqlStatement)
        throws CallDbException
    {
        xlog.info("SQL=[" + sqlStatement + "]");
        boolean ret = false;
        try
        {
            this.connect();
            if (sqlParameterExt != null && sqlParameterExt.getSize() > 0)
            {
                // Use a PreparedStatement and set all values
                pstmt = cnt.prepareStatement(sqlStatement);
                pstmt.clearParameters();
                sqlParameterExt.setBoParameter(pstmt, databaseType);
                ret = pstmt.execute();
            }
            else
            {
                // Use a regular Statement
                stmt = cnt.createStatement();
                ret = stmt.execute(sqlStatement);
            }
            return ret;
        }
        catch (Exception err)
        {
            throw new CallDbException("更新数据库操作失败：" + err.getMessage());
        }
    }

    /**
     * 如果有参数，请同时调用setSqlParameter、setSqlParameterType
     * @throws SQLException
     */
    public void executeProcedure(String sqlStatement)
        throws CallDbException
    {
        exeProcedure(sqlStatement);
        try
        {
		  if (sqlParameterExt != null)
		  {
			sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
		  }
        }
        catch (Exception err)
        {
            throw new CallDbException("执行数据库存储操作失败：" + err.getMessage());
        }
    }

    public List <Row> queryProcedureToList(String sqlStatement,
                                     String tableName)
        throws CallDbException
    {
        return queryProcedureToList(sqlStatement, tableName, null);
    }

    public List <Row> queryProcedureToList(String sqlStatement,
                                     String tableName, String queryStatement)
        throws CallDbException
    {
        ResultSet rs = queryProcedure(sqlStatement, tableName, queryStatement);
        try
        {
            List <Row> ret = toList(rs);
            sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
            switch (this.databaseType)
            {
                case BasicOperation.DB_TYPE_ORACLE:
                    if (tableName != null && !tableName.trim().equals(""))
                    {
                        this.sqlParameterExt = null;
                        execute("truncate table " + tableName); // 删除临时表
                    }
                    else
                    {
                        throw new CallDbException("未定义的数据库表名!");
                    }
                    break;
                case BasicOperation.DB_TYPE_INFORMIX:
                    throw new CallDbException("未实现的数据库类型!");
                case BasicOperation.DB_TYPE_MSSQLSERVER:
                    throw new CallDbException("未实现的数据库类型!");
                case BasicOperation.DB_TYPE_DB2:
                    break;
                case BasicOperation.DB_TYPE_SYBASE:
                    break;
                default:
                    throw new CallDbException("未定义的数据库类型!");
            }
            return ret;
        }
        catch (Exception err)
        {
            throw new CallDbException(err.getMessage());
        }
    }
    
    public List <Row> queryProcedureToList(String sqlStatement,
	            String tableName, String queryStatement,int pagesize,int pagenum) throws CallDbException{
    	List <Row> ret = null;
		try
		{
		   ret = queryProcedure(sqlStatement,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, tableName, queryStatement,pagesize, pagenum);
		   sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
		   switch (this.databaseType){
		       case BasicOperation.DB_TYPE_ORACLE:
				    if (tableName != null && !tableName.trim().equals("")){
				       this.sqlParameterExt = null;
				       execute("truncate table " + tableName); // 删除临时表
				    }
				    else{
				       throw new CallDbException("未定义的数据库表名!");
				    }
				    break;
			   case BasicOperation.DB_TYPE_INFORMIX:
				    throw new CallDbException("未实现的数据库类型!");
			   case BasicOperation.DB_TYPE_MSSQLSERVER:
				    throw new CallDbException("未实现的数据库类型!");
			   case BasicOperation.DB_TYPE_DB2:
				    break;
			   case BasicOperation.DB_TYPE_SYBASE:
				    break;
			   default:
				    throw new CallDbException("未定义的数据库类型!");
		    }
			return ret;
		}
		catch (Exception err)
		{
		throw new CallDbException(err.getMessage());
		}
	}
    
    public List <List<Row>> queryToMoreList(String[] sqlStatementArray)
        throws CallDbException
    {
      List <List<Row>>ret = new ArrayList<List<Row>>();
      try
      {
        for (int i = 0; i < sqlStatementArray.length; i++)
        {
          ret.add(queryToList(sqlStatementArray[i]));
        }
        return ret;
      }
      catch(Exception err)
      {
            throw new CallDbException("执行数据库查询操作失败：" + err.getMessage());
      }
    }

    public List <List<Row>> queryProcedureToMoreList(String sqlStatement,
                                         String[] tableNameArray)
        throws CallDbException
    {
        return queryProcedureToMoreList(sqlStatement, tableNameArray, null);
    }

    public List <List<Row>> queryProcedureToMoreList(String sqlStatement,
                                         String[] tableNameArray,
                                         String[] queryStatementArray)
        throws CallDbException
    {
        List<List<Row>> ret = new ArrayList<List<Row>>();
        ResultSet rs = null;
        String s = "";
        try
        {
            switch (this.databaseType)
            {
                case BasicOperation.DB_TYPE_ORACLE:
                    if (tableNameArray != null && tableNameArray.length > 0)
                    {
                        executeProcedure(sqlStatement);
                        this.sqlParameterExt = null;
                        for (int i = 0; i < tableNameArray.length; i++)
                        {
                            if (queryStatementArray != null &&
                                queryStatementArray.length > 0)
                            {
                                s = queryStatementArray[i];
                                ret.add(queryToList(s));
                            }
                            else
                            {
                                s = "select * from " + tableNameArray[i];
                                ret.add(queryToList(s));
                            }
                            execute("truncate table " + tableNameArray[i]); // 删除临时表
                        }
                        return ret;
                    }
                    else
                    {
                        throw new CallDbException("未定义的数据库表名!");
                    }
                case BasicOperation.DB_TYPE_INFORMIX:
                    throw new CallDbException("未实现的数据库类型!");
                case BasicOperation.DB_TYPE_MSSQLSERVER:
                    throw new CallDbException("未实现的数据库类型!");
                case BasicOperation.DB_TYPE_DB2:                	
                case BasicOperation.DB_TYPE_SYBASE:

//          rs = queryProcedure(sqlStatement,
//                              ResultSet.TYPE_FORWARD_ONLY,
//                              ResultSet.CONCUR_READ_ONLY);
//          ret.add(toList(rs));
//          while (cstmt.getMoreResults()) {
//            rs = cstmt.getResultSet();
//            ret.add(toList(rs));
//          }
                    exeProcedure(sqlStatement);
                    int rowCount = -1;
                    boolean b = false;
                    while (true)
                    {
                        rowCount = cstmt.getUpdateCount();
                        if (!b && rowCount >= 0)
                        { // 它是更新计数
                            b = cstmt.getMoreResults();
                            continue;
                        }
                        rs = cstmt.getResultSet();
                        if (rs != null)
                        {
                            ret.add(toList(rs));
                            b = cstmt.getMoreResults();
                            continue;
                        }
                        break; // 没有其它结果
                    }
                    break;
                default:
                    throw new CallDbException("未定义的数据库类型!");
            }
            sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
            return ret;
        }
        catch (Exception err)
        {
            throw new CallDbException("执行数据库存储操作失败：" + err.getMessage());
        }
    }

    /**
     * 从结果集中取得所有数据返回一个行对象的列表
     *
     * @param rs the ResultSet
     * @return a List of Row objects
     * @exception SQLException, thrown by the JDBC API calls
     * @exception UnsupportedTypeException, if the returned value
     *            doesn't match any Value subclass
     */
    public List <Row> toList(ResultSet rs)
        throws SQLException, UnsupportedTypeException
    {
        xlog.debug("Enter BasicOperation.toList()");
        List <Row> rows = new ArrayList <Row>();
        while (rs != null && rs.next())
        {
            Row row = new Row(rs);           
            rows.add(row);
        }
        //rs.close();
        xlog.debug("BasicOperation.toList return rows=[" + rows.size() + "]");
        return rows;
    }

    /** 数据库类型 */
    public static final int DB_TYPE_ORACLE = 0;
    public static final int DB_TYPE_INFORMIX = 1;
    public static final int DB_TYPE_MSSQLSERVER = 2;
    public static final int DB_TYPE_DB2 = 3;
    public static final int DB_TYPE_SYBASE = 4;

//--------------------------------------------------------------------------
    /** JDBC连接数据源 */
    private DataSource ds = null;
    /** JDBC连接 */
    private Connection cnt = null;
    /** 数据库类型*/
    protected int databaseType = DB_TYPE_SYBASE;
    /** SQL参数 */
    private SqlParameterExt sqlParameterExt = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private String hjparameter="";
    private static org.apache.log4j.Logger xlog = org.apache.log4j.Logger.
        getLogger(com.wasoft.calldb.ShareData.LOG_NAME);

    /**
     * 如果有参数，请同时调用setSqlParameter、setSqlParameterType
     * @throws SQLException
     */
    private void exeProcedure(String sqlStatement)
        throws CallDbException
    {
        String s = "";
        try
        {
            this.connect();
            s = "{call " + clearParamName(sqlStatement.trim()) + "}";
            cstmt = cnt.prepareCall(s);
            xlog.info("SQL=[" + s + "]");
            if (sqlParameterExt != null && sqlParameterExt.getSize() > 0)
            {
                cstmt.clearParameters();
                sqlParameterExt.setBoParameterAndType(cstmt, databaseType);
            }
            cstmt.execute();
			//cstmt.close();
			//this.disconnect();
        }
        catch (Exception err)
        {
            throw new CallDbException("执行数据库存储操作失败：" + err.getMessage());
        }
    }

    private String clearParamName(String sqlStatement)
    {
    	String tmp = sqlStatement.replaceAll("[pP][0-9]*,", ",");
    	tmp = tmp.replaceAll("[pP][0-9]*[)]", ")");
		return tmp;
    }
    
    private ResultSet queryProcedure(String sqlStatement, int scroll, int rw)
        throws CallDbException
    {
        ResultSet rs = null;
        String s = "";
        try
        {
            this.connect();
            s = "{call " + clearParamName(sqlStatement.trim()) + "}";
            cstmt = cnt.prepareCall(s, scroll, rw);
            xlog.info("SQL=[" + s + "]");
            if (sqlParameterExt != null && sqlParameterExt.getSize() > 0)
            {
                cstmt.clearParameters();
                sqlParameterExt.setBoParameterAndType(cstmt, databaseType);
            }
            rs = cstmt.executeQuery();
            return rs;
        }
        catch (Exception err)
        {
            throw new CallDbException("执行数据库存储操作失败：" + err.getMessage());
        }
    }

    private ResultSet queryProcedure(String sqlStatement, int scroll, int rw,
                                     String tableName, String queryStatement)
        throws CallDbException
    {
        String s = "";
        switch (this.databaseType)
        {
            case BasicOperation.DB_TYPE_ORACLE:
                if ((tableName != null && !tableName.trim().equals(""))
                    || (queryStatement != null && !queryStatement.trim().equals("")))
                {
                    executeProcedure(sqlStatement);
                    if (queryStatement != null &&
                        !queryStatement.trim().equals(""))
                    {
                        s = queryStatement;
                    }
                    else
                    {
                        s = "select * from " + tableName;
                    }
                    SqlParameterExt tmpSpx = this.sqlParameterExt; // 保存参数
                    this.sqlParameterExt = null;
                    ResultSet rs = query(s, scroll, rw);
                    this.sqlParameterExt = tmpSpx;
                    return rs;
                }
                else
                {
                    throw new CallDbException("未定义的数据库表名!");
                }
            case BasicOperation.DB_TYPE_INFORMIX:
                throw new CallDbException("未实现的数据库类型!");
            case BasicOperation.DB_TYPE_MSSQLSERVER:
                throw new CallDbException("未实现的数据库类型!");
            case BasicOperation.DB_TYPE_DB2:                
            case BasicOperation.DB_TYPE_SYBASE:
                return queryProcedure(sqlStatement, scroll, rw);
            default:
                throw new CallDbException("未定义的数据库类型!");
        }
    }
    
    private List <Row>  queryProcedure(String sqlStatement, int scroll, int rw,String tableName, String queryStatement,int pagesize,int pagenum) throws CallDbException{
		String s = "";
		List <Row> ret=null;
		try{
			switch (this.databaseType){
			    case BasicOperation.DB_TYPE_ORACLE:
				   if ((tableName != null && !tableName.trim().equals(""))|| (queryStatement != null && !queryStatement.trim().equals(""))){
				       executeProcedure(sqlStatement);
				       if (queryStatement != null &&!queryStatement.trim().equals("")){
				           s = queryStatement;
				       }
				       else{
				           s = "select * from " + tableName;
				       }
				       SqlParameterExt tmpSpx = this.sqlParameterExt; // 保存参数
				       this.sqlParameterExt = null;
				       ResultSet rs=null;
				       ResultSet rs1=null;
				       int count =0;
				       if (pagesize != 0 && pagenum > 0){
				           rs = query(s, scroll, rw, pagesize, pagenum);
				           rs1= getSummary(queryStatement,pagesize,pagenum);
				           if(rs1!=null){
					           count = rs1.getInt("cnt"); 	//总记录	        	
						       int pages = count / pagesize;	//总页数
						       if (count%pagesize != 0){
						    		pages += 1;
						       }
						       xlog.debug("总记录数：" + count + "，总页数：" + pages);
				           }
				       }
				       else{
				    	   rs = query(s, scroll, rw);
				       }
				       ret = toList(rs);
					   if (ret.size() > 0){
				    		Row row = ret.get(0);	    		
				    		row.addColumns(new Column[]{new IntColumn("count", count)}, new int[]{Types.INTEGER});
				    		if(!hjparameter.trim().equals("")){
				    			 String[] hjparams=hjparameter.split(",");
			    				 for (int i = 0; i < hjparams.length; i++) {
			    					 String param=hjparams[i];
			    					 double paramvalue=0;
			    					 if(rs1!=null){
			    					     paramvalue=rs1.getDouble(param);
			    					 }
			    					 row.addColumns(new Column[]{new DoubleColumn(param+"hj",paramvalue)}, new int[]{Types.DOUBLE});
			    				 }
				    		}
				       }
				       this.sqlParameterExt = tmpSpx;
				       return ret;
				   }
				   else{
				        throw new CallDbException("未定义的数据库表名!");
				   }
			    case BasicOperation.DB_TYPE_INFORMIX:
			        throw new CallDbException("未实现的数据库类型!");
			    case BasicOperation.DB_TYPE_MSSQLSERVER:
			        throw new CallDbException("未实现的数据库类型!");
			    case BasicOperation.DB_TYPE_DB2:                
			    case BasicOperation.DB_TYPE_SYBASE:
			    default:
			        throw new CallDbException("未定义的数据库类型!");
			}
		}
		catch (Exception err)
        {
            throw new CallDbException("执行数据库存储操作失败：" + err.getMessage());
        }
	}

    private ResultSet queryProcedure(String sqlStatement,
                                     String tableName, String queryStatement)
        throws CallDbException
    {
        return queryProcedure(sqlStatement,
                              ResultSet.TYPE_FORWARD_ONLY,
                              ResultSet.CONCUR_READ_ONLY,
                              tableName, queryStatement);
    }

    private ResultSet queryProcedureToScroll(String sqlStatement,
                                             String tableName,
                                             String queryStatement)
        throws CallDbException
    {
        return queryProcedure(sqlStatement,
                              ResultSet.TYPE_SCROLL_SENSITIVE,
                              ResultSet.CONCUR_READ_ONLY,
                              tableName, queryStatement);
    }

    private List <List<Row>> queryProcedureToMoreList(String sqlStatement)
        throws CallDbException
    {
        ResultSet rs = null;
        String s = "";
        try
        {
            this.connect();
            s = "{call " + clearParamName(sqlStatement.trim()) + "}";
            cstmt = cnt.prepareCall(s);
            xlog.info("SQL=[" + s + "]");
            if (sqlParameterExt != null && sqlParameterExt.getSize() > 0)
            {
                cstmt.clearParameters();
                sqlParameterExt.setBoParameterAndType(cstmt, databaseType);
            }
            rs = cstmt.executeQuery();
            List<List<Row>> ret = new ArrayList<List<Row>>();
            ret.add(toList(rs));
            while (cstmt.getMoreResults())
            {
                rs = cstmt.getResultSet();
                ret.add(toList(rs));
            }
            sqlParameterExt.setBoParameterOutValue(cstmt, databaseType);
            return ret;
        }
        catch (Exception err)
        {
            throw new CallDbException("执行数据库存储操作失败：" + err.getMessage());
        }
    }
    
    public void setHjParameter(String parameter) throws CallDbException{
         xlog.info("SQL=[" + parameter + "]");
         hjparameter=parameter;
    }

//--------------------------------------------------------------------------
    public static void main(String[] args)
    {
    }
}

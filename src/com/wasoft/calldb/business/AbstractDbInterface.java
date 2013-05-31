package com.wasoft.calldb.business;

import java.lang.reflect.*;
import java.sql.ResultSet;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.*;
import com.wasoft.calldb.*;
import com.wasoft.calldb.sql.Row;
import com.wasoft.calldb.util.*;
import com.wasoft.model.xtgl.User;


/**
 * 调用数据库接口
 * <p>
 * Title: 调用数据库接口
 * </p>
 * <p>
 * Description: 调用数据库接口
 * 
 * @version 1.0
 */
public abstract class AbstractDbInterface {
	public AbstractDbInterface() {
		AddOracleMethod();
	}

	public String toString() {
		return getClass().getName();
	}

	public void setDatabaseType(int databaseType) {
		this.databaseType = databaseType;
	}

	/**
	 * 执行数据库操作，有结果集
	 * 
	 * @param procedureName
	 *            调用过程约定名
	 * @param obj
	 *            参数值对象
	 * @param cv
	 *            通用变量类
	 * @param tempDs
	 *            临时数据源
	 * @throws CallDbException
	 */
	public final void Execute(String procedureName, Object obj, User user,
			DataSource tempDs, int tempDbType) throws CallDbException {
		String s = "";
		getBasicOperation(tempDs, tempDbType);
		try {
			initOper(procedureName, obj);

			// ---------- Oracle ----------
			if (databaseType == BasicOperation.DB_TYPE_ORACLE
					&& XTool.arrayIndexOf(arrOracle, procedureName) >= VL_ZERO) {
				s = "executeOracle" + procedureName;
			}
			// ---------- MsSql ----------
			else if (databaseType == BasicOperation.DB_TYPE_MSSQLSERVER
					&& XTool.arrayIndexOf(arrMsSql, procedureName) >= VL_ZERO) {
				s = "executeMsSql" + procedureName;
			}
			// ---------- Sybase or General----------
			else {				
				s = "execute" + procedureName;
			}
			xlog.debug("Call Method is " + this.toString() + "." + s);

			Method m = null;
			if (user == null) {
				m = this.getClass().getMethod(s, new Class[] { Object.class });
				m.invoke(this, new Object[] { obj });
			} else {
				m = this.getClass().getMethod(s,
						new Class[] { Object.class, User.class });
				m.invoke(this, new Object[] { obj, user });
			}

			destroyOper(procedureName, obj);
		} catch (NoSuchMethodException err) {
			xlog.debug(err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (SecurityException err) {
			xlog.debug(err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (IllegalAccessException err) {
			xlog.debug(err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (IllegalArgumentException err) {
			xlog.debug(err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (InvocationTargetException err) {
			xlog.debug(err.getMessage());
			if (err.getCause() instanceof CallDbException) {
				throw (CallDbException) err.getCause();
			} else {
				throw new CallDbException("调用过程约定名[" + s + "]未成功!");
			}
		} finally {
			closeConnect();
		}
	}

	/**
	 * 执行数据库操作，有结果集
	 * 
	 * @param procedureName
	 *            调用过程约定名
	 * @param obj
	 *            参数值对象
	 * @param cv
	 *            通用变量类
	 * @param tempDs
	 *            临时数据源
	 * @return
	 * @throws CallDbException
	 */
	@SuppressWarnings("unchecked")
	public final List <Row> Open(String procedureName, Object obj, User user,
			DataSource tempDs, int tempDbType) throws CallDbException {
		String s = "";
		getBasicOperation(tempDs, tempDbType);
		try {
			initOper(procedureName, obj);

			// ---------- Oracle ----------
			if (databaseType == BasicOperation.DB_TYPE_ORACLE
					&& XTool.arrayIndexOf(arrOracle, procedureName) >= VL_ZERO) {
				s = "openOracle" + procedureName;
			}
			// ---------- MsSql ----------
			else if (databaseType == BasicOperation.DB_TYPE_MSSQLSERVER
					&& XTool.arrayIndexOf(arrMsSql, procedureName) >= VL_ZERO) {
				s = "openMsSql" + procedureName;
			}
			// ---------- Sybase or General----------
			else {				
				s = "open" + procedureName;
			}
			xlog.debug("Call Method is " + this.toString() + "." + s);

			Method m = null;
			List <Row> lst = null;
			if (user == null) {
				m = this.getClass().getMethod(s, new Class[] { Object.class });
				lst = (List <Row>) m.invoke(this, new Object[] { obj });
			} else {
				m = this.getClass().getMethod(s,
						new Class[] { Object.class, User.class });
				lst = (List <Row>) m.invoke(this, new Object[] { obj, user });
			}
			destroyOper(procedureName, obj);
			return lst;
		} catch (NoSuchMethodException err) {
			xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (SecurityException err) {
			xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (IllegalAccessException err) {
			xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (IllegalArgumentException err) {
			xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (InvocationTargetException err) {
			if (err.getCause() instanceof CallDbException) {
				throw (CallDbException) err.getCause();
			} else {
				xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
				throw new CallDbException("调用过程约定名[" + s + "]未成功!");
			}
		} finally {
			closeConnect();
		}
	}

	@SuppressWarnings("unchecked")
	public final List <Row> Open(String procedureName, Object obj, User user,
			DataSource tempDs, int tempDbType, int pagesize, int pagenum) throws CallDbException {
		String s = "";
		getBasicOperation(tempDs, tempDbType);
		try {
			initOper(procedureName, obj);

			// ---------- Oracle ----------
			if (databaseType == BasicOperation.DB_TYPE_ORACLE
					&& XTool.arrayIndexOf(arrOracle, procedureName) >= VL_ZERO) {
				s = "openOracle" + procedureName;
			}
			// ---------- MsSql ----------
			else if (databaseType == BasicOperation.DB_TYPE_MSSQLSERVER
					&& XTool.arrayIndexOf(arrMsSql, procedureName) >= VL_ZERO) {
				s = "openMsSql" + procedureName;
			}
			// ---------- Sybase or General----------
			else {				
				s = "open" + procedureName;
			}
			xlog.debug("Call Method is " + this.toString() + "." + s);

			Method m = null;
			List <Row> lst = null;
			if (user == null) {
				m = this.getClass().getMethod(s, new Class[] { Object.class, int.class, int.class });
				lst = (List <Row>) m.invoke(this, new Object[] { obj, pagesize, pagenum });
			} else {
				m = this.getClass().getMethod(s, new Class[] { Object.class, User.class, int.class, int.class });
				lst = (List <Row>) m.invoke(this, new Object[] { obj, user, pagesize, pagenum });
			}
			destroyOper(procedureName, obj);
			return lst;
		} catch (NoSuchMethodException err) {
			xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (SecurityException err) {
			xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (IllegalAccessException err) {
			xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (IllegalArgumentException err) {
			xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
			throw new CallDbException("调用过程约定名[" + s + "]未成功!");
		} catch (InvocationTargetException err) {
			if (err.getCause() instanceof CallDbException) {
				throw (CallDbException) err.getCause();
			} else {
				xlog.debug("调用过程约定名[" + s + "]未成功:" + err.getMessage());
				throw new CallDbException("调用过程约定名[" + s + "]未成功!");
			}
		} finally {
			closeConnect();
		}
	}
	// --------------------------------------------------------------------------
	protected BasicOperation getBasicOperation(DataSource ds, int tempDbType)
			throws CallDbException {
		if (bo == null) {
			if (ds == null) {
				bo = DbSingleton.makeDbSingleton().getGjjDb();
				xlog.debug("Get Gjjmx BasicOperation");
			} else {
				bo = DbSingleton.makeDbSingleton().getTempDb(ds, tempDbType);
				this.databaseType = tempDbType;
				xlog.debug("get Temp BasicOperation");
			}
			if (this.databaseType == BasicOperation.DB_TYPE_SYBASE)
			{								
				try
				{
					if (getTranChained() == 1)					
					{
						bo.execute("commit");
						bo.execute("set chained off");
					}
				}
				catch(Exception e)
				{
					xlog.error("sybase chained error:" + e.getMessage());
				}
			}
		}
		return bo;
	}
	protected int getTranChained()
	{
		int ret = 0;
		try
		{
			ResultSet rs = bo.query("select @@tranchained");
			rs.next();
			ret = rs.getInt(1);
			rs.close();
		}
		catch(Exception e){}
		return ret;
	}
	protected void closeConnect() throws CallDbException {
		xlog.debug("Entry closeConnect");
		if (bo != null) {
			bo.disconnect();
		}
		xlog.debug("Return closeConnect");
	}

	protected void initOper(String procedureName, Object obj)
			throws CallDbException {
		xlog.debug("Entry initOper");
		xlog.debug("Return initOper");
	}

	protected void destroyOper(String procedureName, Object obj)
			throws CallDbException {
		xlog.debug("Entry destroyOper");
		xlog.debug("Return destroyOper");
	}

	protected String sqlPlus() throws CallDbException {
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			return "||";
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			return "||";
		case BasicOperation.DB_TYPE_SYBASE:
			return "+";
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
	}

	protected String sqlReplace(String lhs, String mds, String rhs,
			String start, String len) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = "replace(" + lhs + "," + mds + "," + rhs + ")";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = "replace(" + lhs + "," + mds + "," + rhs + ")";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = "stuff(" + lhs + "," + start + "," + len + "," + rhs + ")";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlCharIndex(String lhs, String rhs)
			throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = " instr(" + rhs + "," + lhs + ") ";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " position1(" + lhs + "," + rhs + ") ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " charindex(" + lhs + "," + rhs + ") ";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}
	
	protected String sqlCharIndex(String rhs, String lhs,String mds)
	throws CallDbException {
       String ret = "";
       switch (databaseType) {
       case BasicOperation.DB_TYPE_ORACLE:
	       ret = " instr(" + rhs + ","+lhs+","+mds+")";
	       break;
       case BasicOperation.DB_TYPE_INFORMIX:
	       throw new CallDbException("未实现的数据库类型!");
       case BasicOperation.DB_TYPE_MSSQLSERVER:
	       throw new CallDbException("未实现的数据库类型!");
       case BasicOperation.DB_TYPE_DB2:
	       ret = " position1(" + lhs + "," + rhs + ") ";
	       break;
       case BasicOperation.DB_TYPE_SYBASE:
	       ret = "charindex("+lhs + ","+rhs+","+mds+")";
	       break;
       default:
	   throw new CallDbException("未定义的数据库类型!");
     }
     return ret;
   }

	protected String sqlSubString(String lhs, String mds, String rhs)
			throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = " substr(" + lhs + "," + mds + "," + rhs + ") ";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " substr(" + lhs + "," + mds + "," + rhs + ") ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " substring(" + lhs + "," + mds + "," + rhs + ") ";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlIsNull(String lhs) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = lhs + " is null ";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " ifnull(" + lhs + ",'') = '' ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " isnull(" + lhs + ",'') = ''";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlIsNotNull(String lhs) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = lhs + " is not null ";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " ifnull(" + lhs + ",'') <> '' ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " isnull(" + lhs + ",'') <> ''";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlStrIsNull(String lhs) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = " trim(" + lhs + ") is null ";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " ifnull(" + lhs + ",'') = '' ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " isnull(" + lhs + ",'') = '' ";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlStrIsNotNull(String lhs) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = " trim(" + lhs + ") is not null ";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " ifnull(" + lhs + ",'') <> '' ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " isnull(" + lhs + ",'') <> '' ";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlNumIsNull(String str) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = " nvl(" + str + ",0)";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " value(" + str + ",0) ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " isnull(" + str + ",0)";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlDateIsNull(String lhs) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = " " + lhs + " is null ";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " " + lhs + " is null ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " isnull(" + lhs + ",'') = '' ";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlDateIsNotNull(String lhs) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = " " + lhs + " is not null ";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " " + lhs + " is not null ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " isnull(" + lhs + ",'') <> '' ";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}	
	
	protected String sqlStrInSelIsNull(String str) throws CallDbException{
       String ret = "";
       switch (databaseType)
       {
	      case BasicOperation.DB_TYPE_ORACLE:
		     ret =" nvl(" + str + ",' ')";
		     break;
	      case BasicOperation.DB_TYPE_INFORMIX:
		     throw new CallDbException("未实现的数据库类型!");
	      case BasicOperation.DB_TYPE_MSSQLSERVER:
		     throw new CallDbException("未实现的数据库类型!");
	      case BasicOperation.DB_TYPE_DB2:
              ret =" ifnull(" + str + ",' ')";
		      break;
	      case BasicOperation.DB_TYPE_SYBASE:
		      ret = " isnull(" + str +",' ')";
		      break;
	      default:
		      throw new CallDbException("未定义的数据库类型!");
       }
       return ret;
    }
	
	protected String sqlTrim(String lhs) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = "trim(" + lhs + ")";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = "trim(" + lhs + ")";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = "ltrim(rtrim(" + lhs + "))";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}
	
	protected String sqlDate(String sdate) throws CallDbException{
       String ret = "";
       switch(databaseType)
      {
         case BasicOperation.DB_TYPE_ORACLE:
             ret = "to_date('" + sdate + "','yyyy-mm-dd')";
             break;
         case BasicOperation.DB_TYPE_INFORMIX:
             throw new CallDbException("未实现的数据库类型!");
         case BasicOperation.DB_TYPE_MSSQLSERVER:
             throw new CallDbException("未实现的数据库类型!");
         case BasicOperation.DB_TYPE_DB2:
             ret = "'" + sdate + "'";
             break;
         case BasicOperation.DB_TYPE_SYBASE:
             ret = "'"+sdate+"'";
             break;
         default:
             throw new CallDbException("未定义的数据库类型!");
       }
       return ret;
    }
	
//  字段转换
    protected String sqlDateSwap(String ldate)
        throws CallDbException
    {
      String ret = "";
      switch(databaseType)
      {
        case BasicOperation.DB_TYPE_ORACLE:
            ret = "to_char(" + ldate + ",'yyyy-mm-dd')";
            break;
        case BasicOperation.DB_TYPE_INFORMIX:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_MSSQLSERVER:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_DB2:
            ret = "varchar(" + ldate + ")";
            break;
        case BasicOperation.DB_TYPE_SYBASE:
            ret = "convert(char(8),"+ldate+",112)";
            break;
        default:
            throw new CallDbException("未定义的数据库类型!");
        }
        return ret;
    }
    
//  字段转换
    protected String sqlDatetime(String ldate)
        throws CallDbException
    {
      String ret = "";
      switch(databaseType)
      {
        case BasicOperation.DB_TYPE_ORACLE:
            ret = "to_char(" + ldate + ",'yyyy-mm-dd hh24-mi-ss')";
            break;
        case BasicOperation.DB_TYPE_INFORMIX:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_MSSQLSERVER:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_DB2:
            ret = "varchar(" + ldate + ")";
            break;
        case BasicOperation.DB_TYPE_SYBASE:
        	ret = "convert(char(8),"+ldate+",112)";
            break;
        default:
            throw new CallDbException("未定义的数据库类型!");
        }
        return ret;
    }
    
// 日期值转换月度值
    protected String sqlDateValueToYd(String sdate)
        throws CallDbException
    {
      String ret = "";
      switch(databaseType)
      {
        case BasicOperation.DB_TYPE_ORACLE:
            ret = "to_char('" + sdate + "','yyyymm')";
            break;
        case BasicOperation.DB_TYPE_INFORMIX:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_MSSQLSERVER:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_DB2:
            ret = "substr(replace(varchar(" + sdate + "),'-',''),1,6)";
            break;
        case BasicOperation.DB_TYPE_SYBASE:
            ret = "convert(char(6),'"+sdate+"',112)";
            break;
        default:
            throw new CallDbException("未定义的数据库类型!");
        }
        return ret;
    }
//日期字段转称月度
    protected String sqlDateToYd(String ldate)
        throws CallDbException
    {
      String ret = "";
      switch(databaseType)
      {
        case BasicOperation.DB_TYPE_ORACLE:
            ret = "to_char(" + ldate + ",'yyyymm')";
            break;
        case BasicOperation.DB_TYPE_INFORMIX:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_MSSQLSERVER:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_DB2:
            ret = "substr(replace(char(" + ldate + "),'-',''),1,6)";
            break;
        case BasicOperation.DB_TYPE_SYBASE:
            ret = "convert(char(6),"+ldate+",112)";
            break;
        default:
            throw new CallDbException("未定义的数据库类型!");
        }
        return ret;
    }
    
//  日期字段转成年
    protected String sqlDateToY(String ldate)
        throws CallDbException
    {
      String ret = "";
      switch(databaseType)
      {
        case BasicOperation.DB_TYPE_ORACLE:
            ret = "to_char(" + ldate + ",'yyyy')";
            break;
        case BasicOperation.DB_TYPE_INFORMIX:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_MSSQLSERVER:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_DB2:
            ret = "substr("+ldate + ",1,4)";
            break;
        case BasicOperation.DB_TYPE_SYBASE:
            ret = "convert(char(4),"+ldate+",112)";
            break;
        default:
            throw new CallDbException("未定义的数据库类型!");
        }
        return ret;
    }
    
    protected String sqlDateMinus(String ldate)
        throws CallDbException
    {
      String ret = "";
      switch(databaseType)
      {
        case BasicOperation.DB_TYPE_ORACLE:
            ret = "sysdate-"+ldate;
            break;
        case BasicOperation.DB_TYPE_INFORMIX:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_MSSQLSERVER:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_DB2:
            ret = "days(current timestamp)-days("+ldate+")";
            break;
        case BasicOperation.DB_TYPE_SYBASE:
            ret =" datediff(day,"+ldate+",getdate())";
            break;
        default:
            throw new CallDbException("未定义的数据库类型!");
        }
        return ret;
    }

    protected String sqlDateAdds(String ldate)
        throws CallDbException
    {
      String ret = "";
      switch(databaseType)
      {
        case BasicOperation.DB_TYPE_ORACLE:
            ret = ldate+"-sysdate";
            break;
        case BasicOperation.DB_TYPE_INFORMIX:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_MSSQLSERVER:
            throw new CallDbException("未实现的数据库类型!");
        case BasicOperation.DB_TYPE_DB2:
            ret = "days("+ldate+")-days(current timestamp)";
            break;
        case BasicOperation.DB_TYPE_SYBASE:
            ret =" datediff(day,getdate(),"+ldate+")";
            break;
        default:
            throw new CallDbException("未定义的数据库类型!");
        }
        return ret;
    }

	protected String sqlCurrDateTime() throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = "sysdate";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = "(CURRENT TIMESTAMP)";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = "getdate()";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}
	
	protected String sqlTime(String ldate) throws CallDbException {
		String ret = "";
	      switch(databaseType)
	      {
	        case BasicOperation.DB_TYPE_ORACLE:
	            ret = "to_char(" + ldate + ",'hh24-mi-ss')";
	            break;
	        case BasicOperation.DB_TYPE_INFORMIX:
	            throw new CallDbException("未实现的数据库类型!");
	        case BasicOperation.DB_TYPE_MSSQLSERVER:
	            throw new CallDbException("未实现的数据库类型!");
	        case BasicOperation.DB_TYPE_DB2:
	            ret ="time("+ldate +")";
	            break;
	        case BasicOperation.DB_TYPE_SYBASE:
	            ret = "convert(char(8),"+ldate+",108)";
	            break;
	        default:
	            throw new CallDbException("未定义的数据库类型!");
	        }
	        return ret;
	}

	protected String sqlTrunc(String str) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = "trunc(" + str + ")";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " date(" + str + ") ";
		case BasicOperation.DB_TYPE_SYBASE:
			ret = str;
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlreplaceIsNull(String str,String str1) throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = " nvl(" + str + ",'"+str1+"')";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = " ifnull(" + str + ",'"+str1+"') ";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = " isnull(" + str + ",'"+str1+"')";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;

	}

	protected String sqlReplace(String lhs, String mds, String rhs)
			throws CallDbException {
		String ret = "";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = "replace(" + lhs + "," + mds + "," + rhs + ")";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = "replace(" + lhs + "," + mds + "," + rhs + ")";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = "str_replace(" + lhs + "," + mds + "," + rhs + ")";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlAddmonth(String str1,String str2) throws CallDbException {
		String ret = ".";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = "add_months("+str1+","+str2+")";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = str1+"+"+str2+" months";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = "dateadd(month,"+str2+","+str1+")";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}
	
	protected String sqlAddday(String str1,String str2) throws CallDbException {
		String ret = ".";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = str1+"+"+str2;
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = str1+"+"+str2+" days";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = "dateadd(dd,"+str2+","+str1+")";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}
	
	
	protected String sqlDbDot() throws CallDbException {
		String ret = ".";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = ".";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = ".";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = "..";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}
	
	protected String sqlCharLength(String str) throws CallDbException {
		String ret = ".";
		switch (databaseType) {
		case BasicOperation.DB_TYPE_ORACLE:
			ret = "length("+str+")";
			break;
		case BasicOperation.DB_TYPE_INFORMIX:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_MSSQLSERVER:
			throw new CallDbException("未实现的数据库类型!");
		case BasicOperation.DB_TYPE_DB2:
			ret = "length("+str+")";
			break;
		case BasicOperation.DB_TYPE_SYBASE:
			ret = "char_length("+str+")";
			break;
		default:
			throw new CallDbException("未定义的数据库类型!");
		}
		return ret;
	}

	protected String sqlZhzt()
	{
		return "case when a070='正常' then '0' else (case when a070='封存' then '1' else (case when a070='销户' then '2' else '3' end) end ) end a070";
	}
	protected void AddOracleMethod() {
	}

	protected String[] arrOracle = null;

	protected String[] arrMsSql = null;

	protected BasicOperation bo = null;

	protected int databaseType = BasicOperation.DB_TYPE_SYBASE;

	protected static org.apache.log4j.Logger xlog = org.apache.log4j.Logger
			.getLogger(com.wasoft.calldb.ShareData.LOG_NAME);

	/** 状态值 */
	protected static final short VL_ZERO = (short) 0;

	protected static final short VL_ONE = (short) 1;

	protected static final short VL_TWO = (short) 2;

	protected static final short VL_THREE = (short) 3;

	protected static final short VL_FOUR = (short) 4;

	protected static final short VL_FIVE = (short) 5;

	protected static final short VL_SIX = (short) 6;

	protected static final short VL_SEVEN = (short) 7;

	protected static final short VL_EIGHT = (short) 8;

	protected static final short VL_NINE = (short) 9;

	protected static final short VL_TEN = (short) 10;

	protected static final double CHECK_DOUBLE = 0.00001;
	// --------------------------------------------------------------------------
	protected String[] mksqlexps = { "MK_CONCAT\\s*\\([^)]+\\s*\\)","sysdate","MK_GETSYSDATE","to_char\\s*\\([^)]+\\s*\\)",
			"MK_GETDATE\\s*\\([^)]+\\s*\\)","MK_GETTIME\\s*\\([^)]+\\s*\\)", "MK_SUBSTR\\s*\\([^)]+\\s*\\)",
			"MK_ADDMONTH\\s*\\([^)]+\\s*\\)", "MK_INSTR\\s*\\([^)]+\\s*\\)","MK_LENGTH", "MK_REPLACE\\s*\\([^)]+\\s*\\)",
			"MK_GETYM\\s*\\([^)]+\\s*\\)", "MK_GETYEAR\\s*\\([^)]+\\s*\\)","MK_GETFULLDATE\\s*\\([^)]+\\s*\\)",
			"MK_Auth_User\\s*\\([^)]+\\s*\\)","MK_Auth_User_Str\\s*\\([^)]+\\s*\\)","MK_GETDATETIME\\s*\\([^)]+\\s*\\)", 
			"MK_AUTH\\s*\\([^)]+\\s*\\)","to_date\\s*\\([^)]+\\s*\\)"};
	
	protected String parse_replace(String sql) throws CallDbException {
		// 用于存储替换过程中和替换后的sql语句
		StringBuffer sb = null;
		for(int k=0;k<mksqlexps.length;k++){
			sb=new StringBuffer();
			Pattern p = Pattern.compile(mksqlexps[k]);
			Matcher m = p.matcher(sql);
		    while (m.find()) {
			      String[] matchStrs = m.group().split(",");
			     for (int i = 0; i < matchStrs.length; i++) {
				     if (matchStrs[i].indexOf("(") > -1) {
					     if(matchStrs.length>1){
					        matchStrs[i] = matchStrs[i].substring(matchStrs[i].indexOf("(") + 1, matchStrs[i].length());
					     }else{
					    	 matchStrs[i] = matchStrs[i].substring(matchStrs[i].indexOf("(") + 1, matchStrs[i].length()-1);
					     }
				     } else if (matchStrs[i].indexOf(")") > -1) {
					      matchStrs[i] = matchStrs[i].substring(0, matchStrs[i].indexOf(")"));
				     }
			     }
			     String replacestr = "";
			     replacestr = "(" + matchStrs[0];
			     switch (k){
			          case 0:         
				           for (int j = 1; j < matchStrs.length; j++) {
				      	      replacestr = replacestr + sqlPlus() + matchStrs[j];
			       	       }
				           replacestr = replacestr + ")";
				           break;
			          case 1:
			    		  replacestr =sqlCurrDateTime();
			    		  break;
			          case 2:
			    		  replacestr =sqlCurrDateTime();
			    		  break;
			          case 3:
			    		  replacestr =sqlDateSwap(matchStrs[0]);
			    		  break;
			    	  case 4:
			    		  replacestr =sqlDateSwap(matchStrs[0]);
			    		  break;
			    	  case 5:
			    		  replacestr =sqlTime(matchStrs[0]);
			    		  break;
			    	  case 6:
			    		  replacestr =sqlSubString(matchStrs[0],matchStrs[1],matchStrs[2]);
			    		  break;
			    	  case 7:
			    		  replacestr =sqlAddmonth(matchStrs[0],matchStrs[1]);
			    		  break;
			    	  case 8:
			    		  replacestr =sqlCharIndex(matchStrs[0],matchStrs[1],matchStrs[2]);
			    		  break;
			    	  case 9:
			    		  replacestr =sqlCharLength(matchStrs[0]);
			    		  break;
			    	  case 10:
			    		  replacestr =sqlReplace(matchStrs[0],matchStrs[1],matchStrs[2]);
			    		  break;
			    	  case 11:
			    		  replacestr =sqlDateToYd(matchStrs[0]);
			    		  break;
			    	  case 12:
			    		  replacestr =sqlDateToY(matchStrs[0]);
			    		  break;
			    	  case 13:
			    		  replacestr =sqlDatetime(matchStrs[0]);
			    		  break;
			    	  case 14:
			    		  break;
			    	  case 15:
			    		  break;
			    	  case 16:
			    		  replacestr =sqlDatetime(matchStrs[0]);
			    		  break;
			    	  case 17:
			    		  break;
			    	  case 18:
			    		  replacestr =sqlDate(matchStrs[0]);
			    		  break;
			    	  default:
			    		  throw new CallDbException("未定义的函数!");
			    		  
			     }
			     m.appendReplacement(sb, replacestr);
		     }
		 m.appendTail(sb);
		 sql=sb.toString();
		}
		return sb.toString();
	}	
}

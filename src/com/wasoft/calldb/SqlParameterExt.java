package com.wasoft.calldb;

import java.sql.*;
import java.util.*;
import com.wasoft.calldb.sql.*;
import com.wasoft.calldb.sql.value.*;
/*
 * 数据库参数类
 */
public class SqlParameterExt
{
	@Deprecated
    public SqlParameterExt(int len)
    {
        this.sqlParameter = new LinkedList <Parameter>();
    }

    public SqlParameterExt()
    {
        this.sqlParameter = new LinkedList <Parameter>();
    }

    public String toString()
    {
        return getClass().getName();
    }

    /**
     * 参数增加
     * @param v 参数列表
     * @param valueType 参数类型
     * @throws CallDbException
     */
    public void add(Value v, int valueType)
    {
        Parameter p = new Parameter(v, valueType);
        this.sqlParameter.add(p);
    }

    /**
     * 参数增加，默认参数类型为输入类型
     * @param v 参数列表
     * @throws CallDbException
     */
    public void add(Value v)
    {
        this.add(v, SQL_INPUT);
    }

    /**
     * 参数插入
     * @param idx 索引
     * @param v 参数列表
     * @param valueType 参数类型
     */
    public void add(int index, Value v, int valueType)
    {
        Parameter p = new Parameter(v, valueType);
        this.sqlParameter.add(index, p);
    }

    /**
     * 参数插入
     * @param idx 索引
     * @param v 参数列表
     */
    public void add(int index, Value v)
    {
        this.add(index, v, SQL_INPUT);
    }

    /**
     * 参数删除，默认参数类型为输入类型
     * @param v 参数列表
     * @throws CallDbException
     */
    public void remove(int index)
    {
        index--;
        //assert (index < this.sqlParameter.size()):"超出参数预设长度!";
        this.sqlParameter.remove(index);
    }

    public void set(int index, Value v, int valueType)
    {
        index--;
        //assert (index < this.sqlParameter.size()):"超出参数预设长度!";
        Parameter p = new Parameter(v, valueType);
        this.sqlParameter.set(index, p);
    }

    public void set(int index, Value v)
    {
        this.set(index, v, SQL_INPUT);
    }

    public Value get(int index)
    {
        index--;
        //assert (index < this.sqlParameter.size()):"超出参数预设长度!";
        return ( (Parameter)this.sqlParameter.get(index)).getValue();
    }

    public void setType(int index, int valueType)
    {
        index--;
        //assert (index < this.sqlParameter.size()):"超出参数预设长度!";
        Parameter p = (Parameter)this.sqlParameter.get(index);
        p.setType(valueType);
        this.sqlParameter.set(index, p);
    }

    public int getType(int index)
    {
        index--;
        //assert (index < this.sqlParameter.size()):"超出参数预设长度!";
        return ( (Parameter)this.sqlParameter.get(index)).getType();
    }

    public int getSize()
    {
        return this.sqlParameter.size();
    }

    /**
     * 为所有在value列表中的值对象调用PreparedStatement上的setXXX()方法
     *
     * @param pstmt the PreparedStatement
     * @param values a List with Value objects
     * @exception SQLException
     */
    public void setBoParameter(PreparedStatement pstmt, int databaseType)
        throws SQLException
    {
        try
        {
            for (int i = 0; i < this.sqlParameter.size(); i++)
            {
                Value v = ( (Parameter)sqlParameter.get(i)).getValue();
                boolean ret = false;
                if (v instanceof BytesValue)
                {
                  xlog.info("parameter " + String.valueOf(i + 1) + "=[" + ((BytesValue)v).getBytesCount() + "Bytes]");
                }
                else
                {
				  xlog.info("parameter " + String.valueOf(i + 1) + "=["
							+ v.getString() + "]");
				}
                // Set the value using the method corresponding to the type.
                // Set methods are indexed from 1, so add 1 to i
                switch (databaseType)
                {
                    case BasicOperation.DB_TYPE_ORACLE:
                        ret = setOracleBoParameter(i, pstmt, v);
                        break;
                    case BasicOperation.DB_TYPE_INFORMIX:
                        throw new SQLException("未实现的数据库类型!");
                    case BasicOperation.DB_TYPE_MSSQLSERVER:
                        throw new SQLException("未实现的数据库类型!");
                    case BasicOperation.DB_TYPE_DB2:
                    	ret = setDB2BoParameter(i, pstmt, v);
                        break;
                    case BasicOperation.DB_TYPE_SYBASE:
                        ret = setSybaseBoParameter(i, pstmt, v);
                        break;
                    default:
                        throw new SQLException("未定义的数据库类型!");
                }
                if (ret)
                {
                    continue;
                }

                if (v instanceof BigDecimalValue)
                {
                    pstmt.setBigDecimal(i + 1, v.getBigDecimal());
                }
                else if (v instanceof BooleanValue)
                {
                    pstmt.setBoolean(i + 1, v.getBoolean());
                }
                else if (v instanceof ByteValue)
                {
                    pstmt.setByte(i + 1, v.getByte());
                }
                else if (v instanceof BytesValue)
                {
                    pstmt.setBytes(i + 1, v.getBytes());
                }
                else if (v instanceof DateValue)
                {
                    pstmt.setDate(i + 1, v.getDate());
                }
                else if (v instanceof DoubleValue)
                {
                    pstmt.setDouble(i + 1, v.getDouble());
                }
                else if (v instanceof FloatValue)
                {
                    pstmt.setFloat(i + 1, v.getFloat());
                }
                else if (v instanceof IntValue)
                {
                    pstmt.setInt(i + 1, v.getInt());
                }
                else if (v instanceof LongValue)
                {
                    pstmt.setLong(i + 1, v.getLong());
                }
                else if (v instanceof ShortValue)
                {
                    pstmt.setShort(i + 1, v.getShort());
                }
                else if (v instanceof StringValue)
                {
                    pstmt.setString(i + 1, v.getString());
                }
                else if (v instanceof TimeValue)
                {
                    pstmt.setTime(i + 1, v.getTime());
                }
                else if (v instanceof TimestampValue)
                {
                    pstmt.setTimestamp(i + 1, v.getTimestamp());
                }
                else
                {
                    pstmt.setObject(i + 1, v.getObject());
                }
            }
        }
        catch (UnsupportedConversionException e)
        {
            xlog.error("数据类型转换不成功!", e);
        }
    }

    public void setBoParameterAndType(CallableStatement pstmt, int databaseType)
        throws SQLException
    {
        setBoParameter(pstmt, databaseType);
        setBoParameterType(pstmt, databaseType);
    }

    public void setBoParameterOutValue(CallableStatement pstmt,
                                       int databaseType)
        throws
        SQLException
    {
        for (int i = 0; i < this.sqlParameter.size(); i++)
        {
            Parameter p = (Parameter)sqlParameter.get(i);
            if (p.getType() == SQL_OUTPUT
                || p.getType() == SQL_INPUT_OUTPUT)
            {
                Value v = p.getValue();
                Value ret = null;

                switch (databaseType)
                {
                    case BasicOperation.DB_TYPE_ORACLE:
                        ret = setOracleBoParameterOutValue(i, pstmt, v);
                        break;
                    case BasicOperation.DB_TYPE_INFORMIX:
                        throw new SQLException("未实现的数据库类型!");
                    case BasicOperation.DB_TYPE_MSSQLSERVER:
                        throw new SQLException("未实现的数据库类型!");
                    case BasicOperation.DB_TYPE_DB2:
                    	ret = setDB2BoParameterOutValue(i, pstmt, v);
                        break;
                    case BasicOperation.DB_TYPE_SYBASE:
                        ret = setSybaseBoParameterOutValue(i, pstmt, v);
                        break;
                    default:
                        throw new SQLException("未定义的数据库类型!");
                }
                if (ret == null)
                {
                    if (v instanceof BigDecimalValue)
                    {
                        v = new BigDecimalValue(pstmt.getBigDecimal(i + 1));
                    }
                    else if (v instanceof BooleanValue)
                    {
                        v = new BooleanValue(pstmt.getBoolean(i + 1));
                    }
                    else if (v instanceof ByteValue)
                    {
                        v = new ByteValue(pstmt.getByte(i + 1));
                    }
                    else if (v instanceof BytesValue)
                    {
                        v = new BytesValue(pstmt.getBytes(i + 1));
                    }
                    else if (v instanceof DateValue)
                    {
                        v = new DateValue(pstmt.getDate(i + 1));
                    }
                    else if (v instanceof DoubleValue)
                    {
                        v = new DoubleValue(pstmt.getDouble(i + 1));
                    }
                    else if (v instanceof FloatValue)
                    {
                        v = new FloatValue(pstmt.getFloat(i + 1));
                    }
                    else if (v instanceof IntValue)
                    {
                        v = new IntValue(pstmt.getInt(i + 1));
                    }
                    else if (v instanceof LongValue)
                    {
                        v = new LongValue(pstmt.getLong(i + 1));
                    }
                    else if (v instanceof ShortValue)
                    {
                        v = new ShortValue(pstmt.getShort(i + 1));
                    }
                    else if (v instanceof StringValue)
                    {
                        v = new StringValue(pstmt.getString(i + 1));
                    }
                    else if (v instanceof TimeValue)
                    {
                        v = new TimeValue(pstmt.getTime(i + 1));
                    }
                    else if (v instanceof TimestampValue)
                    {
                        v = new TimestampValue(pstmt.getTimestamp(i + 1));
                    }
                    else
                    {
                        v = new ObjectValue(pstmt.getObject(i + 1));
                    }
                }
                else
                {
                    v = ret;
                }
                p.setValue(v);
                sqlParameter.set(i, p);
                xlog.info("out parameter " + String.valueOf(i + 1) + "=["
                          + v.getString() + "]");
            }
        }
    }

    /** SQL语句参数类型为输入 */
    public static final int SQL_INPUT = 0;
    /** SQL语句参数类型为输出 */
    public static final int SQL_OUTPUT = 1;
    /** SQL语句参数类型为输入输出 */
    public static final int SQL_INPUT_OUTPUT = 2;

//--------------------------------------------------------------------------
    /**
     * 设置Sybase数据库参数
     * @param idx 参数列表索引
     * @param pstmt 预定义语句参数
     * @param v 自定义参数值
     * @return true-已处理
     * @throws SQLException, UnsupportedConversionException
     */
    private boolean setSybaseBoParameter(int idx, PreparedStatement pstmt,
                                         Value v)
        throws SQLException, UnsupportedConversionException
    {
        return false;
    }

    /**
     * 设置Oracle数据库参数
     * @param idx 参数列表索引
     * @param pstmt 预定义语句参数
     * @param v 自定义参数值
     * @return true-已处理
     * @throws SQLException, UnsupportedConversionException
     */
    private boolean setOracleBoParameter(int idx, PreparedStatement pstmt,
                                         Value v)
        throws SQLException, UnsupportedConversionException
    {
        if (v instanceof BooleanValue)
        {
            java.math.BigDecimal value = v.getBoolean()
                ? java.math.BigDecimal.valueOf(1L)
                : java.math.BigDecimal.valueOf(0L);
            pstmt.setBigDecimal(idx + 1, value);
            return true;
        }
        else if (v instanceof IntValue)
        {
            pstmt.setBigDecimal(idx + 1, java.math.BigDecimal.valueOf(v.getInt()));
            return true;
        }
        else if (v instanceof LongValue)
        {
            pstmt.setBigDecimal(idx + 1, java.math.BigDecimal.valueOf(v.getLong()));
            return true;
        }
        else if (v instanceof ShortValue)
        {
            pstmt.setBigDecimal(idx + 1,
                                java.math.BigDecimal.valueOf(v.getShort()));
            return true;
        }
        else if (v instanceof StringValue)
        {
            String s = v.getString();
            pstmt.setString(idx + 1, (s.equals("") ? " " : s));
            return true;
        }
//    else if (v instanceof DateValue) {
//      pstmt.setTimestamp(idx + 1, new Timestamp(v.getDate().getTime()));
//      return true;
//    }
        return false;
    }

    private boolean setDB2BoParameter(int idx, PreparedStatement pstmt,
            Value v)
    		throws SQLException, UnsupportedConversionException
    {
    	return setOracleBoParameter(idx, pstmt, v);
    }
    
    private void setBoParameterType(CallableStatement pstmt, int databaseType)
        throws
        SQLException
    {
        for (int i = 0; i < this.sqlParameter.size(); i++)
        {
            Parameter p = (Parameter)sqlParameter.get(i);
            if (p.getType() == SQL_OUTPUT
                || p.getType() == SQL_INPUT_OUTPUT)
            {
                Value v = p.getValue();
                boolean ret = false;

                switch (databaseType)
                {
                    case BasicOperation.DB_TYPE_ORACLE:
                        ret = setOracleBoParameterType(i, pstmt, v);
                        break;
                    case BasicOperation.DB_TYPE_INFORMIX:
                        throw new SQLException("未实现的数据库类型!");
                    case BasicOperation.DB_TYPE_MSSQLSERVER:
                        throw new SQLException("未实现的数据库类型!");
                    case BasicOperation.DB_TYPE_DB2:
                    	ret = setDB2BoParameterType(i, pstmt, v);
                        break;
                    case BasicOperation.DB_TYPE_SYBASE:
                        ret = setSybaseBoParameterType(i, pstmt, v);
                        break;
                    default:
                        throw new SQLException("未定义的数据库类型!");
                }
                if (ret)
                {
                    continue;
                }

                if (v instanceof BigDecimalValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.DECIMAL);
                }
                else if (v instanceof BooleanValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.BIT);
                }
                else if (v instanceof ByteValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.TINYINT);
                }
                else if (v instanceof BytesValue)
                {
                    pstmt.registerOutParameter(i + 1,
                                               java.sql.Types.LONGVARBINARY);
                }
                else if (v instanceof DateValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.DATE);
                }
                else if (v instanceof DoubleValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.DOUBLE);
                }
                else if (v instanceof FloatValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.REAL);
                }
                else if (v instanceof IntValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.INTEGER);
                }
                else if (v instanceof LongValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.BIGINT);
                }
                else if (v instanceof ShortValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.SMALLINT);
                }
                else if (v instanceof StringValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.CHAR);
                }
                else if (v instanceof TimeValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.TIME);
                }
                else if (v instanceof TimestampValue)
                {
                    pstmt.registerOutParameter(i + 1, java.sql.Types.TIMESTAMP);
                }
                else
                {
                    xlog.error("无效的数据类型!", new SQLException("无效的数据类型!"));
                }
            }
        }
    }

    /**
     * 设置参数值及传递类型
     * @param idx 参数列表索引
     * @param pstmt 预定义语句参数
     * @param v 自定义参数值
     * @return true-已处理
     * @throws SQLException
     */
    private boolean setOracleBoParameterType(int idx, CallableStatement pstmt,
                                             Value v)
        throws SQLException
    {
        if (v instanceof BooleanValue
            || v instanceof IntValue
            || v instanceof LongValue
            || v instanceof ShortValue)
        {
            pstmt.registerOutParameter(idx + 1, java.sql.Types.NUMERIC);
            return true;
        }
        return false;
    }
    private boolean setDB2BoParameterType(int idx, CallableStatement pstmt,
            								Value v)
		throws SQLException
	{
		return setOracleBoParameterType(idx, pstmt, v);
	}
    
    /**
     * 设置参数值及传递类型
     * @param idx 参数列表索引
     * @param pstmt 预定义语句参数
     * @param v 自定义参数值
     * @return true-已处理
     * @throws SQLException
     */
    private boolean setSybaseBoParameterType(int idx, CallableStatement pstmt,
                                             Value v)
        throws SQLException
    {
        return false;
    }

    /**
     * 得到参数输出值
     * @param idx 参数列表索引
     * @param pstmt 预定义语句参数
     * @param v 自定义参数值
     * @return not null-已处理
     * @throws SQLException
     */
    private Value setSybaseBoParameterOutValue(int idx, CallableStatement pstmt,
                                               Value v)
        throws SQLException
    {
        return null;
    }

    /**
     * 得到参数输出值
     * @param idx 参数列表索引
     * @param pstmt 预定义语句参数
     * @param v 自定义参数值
     * @return not null-已处理
     * @throws SQLException
     */
    private Value setOracleBoParameterOutValue(int idx, CallableStatement pstmt,
                                               Value v)
        throws SQLException
    {
        if (v instanceof BooleanValue)
        {
            boolean value = (pstmt.getBigDecimal(idx + 1).intValue() > 0)
                ? true : false;
                return new BooleanValue(value);
        }
        else if (v instanceof IntValue)
        {
            return new IntValue(pstmt.getBigDecimal(idx + 1).intValue());
        }
        else if (v instanceof LongValue)
        {
            return new LongValue(pstmt.getBigDecimal(idx + 1).longValue());
        }
        else if (v instanceof ShortValue)
        {
            return new ShortValue( (short)pstmt.getBigDecimal(idx + 1).intValue());
        }

        return null;
    }
    private Value setDB2BoParameterOutValue(int idx, CallableStatement pstmt,
            Value v)
    	throws SQLException
    {
    	return setOracleBoParameterOutValue(idx, pstmt, v);
    }
    
    private final class Parameter
    {
        public Parameter(Value value, int type)
        {
            this.value = value;
            this.type = type;
        }

        public String toString()
        {
            return getClass().getName();
        }

        public void setValue(Value value)
        {
            this.value = value;
        }

        public Value getValue()
        {
            return this.value;
        }

        public void setType(int type)
        {
            this.type = type;
        }

        public int getType()
        {
            return this.type;
        }

        private Value value;
        private int type;
    }

    /** SQL参数列表 */
    private LinkedList <Parameter> sqlParameter = null;

    private static org.apache.log4j.Logger xlog =
        org.apache.log4j.Logger.getLogger(ShareData.LOG_NAME);

//--------------------------------------------------------------------------
}

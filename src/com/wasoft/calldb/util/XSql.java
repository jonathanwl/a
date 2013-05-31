/*
 * 扩展SQL工具类
 */
package com.wasoft.calldb.util;

import java.sql.*;
import java.math.BigDecimal;

/**
 * <p>Title: 扩展SQL工具类</p>
 * <p>Description: 扩展SQL工具类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: wasoft</p>
 * @version 1.0
 */

public class XSql
{
    public XSql()
    {
    }

    public String toString()
    {
        return getClass().getName();
    }

    public static double getDefRsDouble(int columnIndex, ResultSet rs,
                                        double value)
        throws SQLException
    {
        BigDecimal ret = rs.getBigDecimal(columnIndex);
        if (rs.wasNull())
        {
            return value;
        }
        else
        {
            return ret.doubleValue();
        }
    }

    public static double getDefRsDouble(String columnName, ResultSet rs,
                                        double value)
        throws SQLException
    {
        return getDefRsDouble(rs.findColumn(columnName), rs, value);
    }

    public static double getDefRsDouble(int columnIndex, ResultSet rs)
        throws SQLException
    {
        return getDefRsDouble(columnIndex, rs, 0D);
    }

    public static double getDefRsDouble(String columnName, ResultSet rs)
        throws SQLException
    {
        return getDefRsDouble(columnName, rs, 0D);
    }

    public static double getRsDouble(int columnIndex, ResultSet rs)
        throws SQLException
    {
        BigDecimal ret = rs.getBigDecimal(columnIndex);
        if (rs.wasNull())
        {
            throw new SQLException("数据集字段为Null值!");
        }
        else
        {
            return ret.doubleValue();
        }
    }

    public static double getRsDouble(String columnName, ResultSet rs)
        throws SQLException
    {
        return getRsDouble(rs.findColumn(columnName), rs);
    }

    public static String getDefRsString(int columnIndex, ResultSet rs,
                                        String value)
        throws SQLException
    {
        String ret = rs.getString(columnIndex);
        if (rs.wasNull())
        {
            return value;
        }
        else
        {
            return ret;
        }
    }

    public static String getDefRsString(String columnName, ResultSet rs,
                                        String value)
        throws SQLException
    {
        return getDefRsString(rs.findColumn(columnName), rs, value);
    }

    public static String getDefRsString(int columnIndex, ResultSet rs)
        throws SQLException
    {
        return getDefRsString(columnIndex, rs, "");
    }

    public static String getDefRsString(String columnName, ResultSet rs)
        throws SQLException
    {
        return getDefRsString(columnName, rs, "");
    }

    public static String getTrimRsString(int columnIndex, ResultSet rs)
        throws SQLException
    {
        return getDefRsString(columnIndex, rs, "").trim();
    }

    public static String getTrimRsString(String columnName, ResultSet rs)
        throws SQLException
    {
        return getDefRsString(columnName, rs, "").trim();
    }

    public static String getRsString(int columnIndex, ResultSet rs)
        throws SQLException
    {
        String ret = rs.getString(columnIndex);
        if (rs.wasNull())
        {
            throw new SQLException("数据集字段为Null值!");
        }
        else
        {
            return ret;
        }
    }

    public static String getRsString(String columnName, ResultSet rs)
        throws SQLException
    {
        return getRsString(rs.findColumn(columnName), rs);
    }

    /**
     * 查找并定位结果集，结果集必须是可滚动的
     * 调用此函数时当前记录指针不被保留，请自行保存
     * @param columnIndex 查询列，字符串类型
     * @param rs 结果集
     * @param value 要比较的值
     * @return 找到的行数，未找到返回-1
     * @throws SQLException
     */
    public static int locate(int columnIndex, ResultSet rs, String value)
        throws SQLException
    {
        rs.beforeFirst();
        while (rs.next())
        {
            if (getDefRsString(1, rs).trim().equals(value.trim()))
            {
                return rs.getRow();
            }
        }
        return -1;
    }

    public static int locate(String columnName, ResultSet rs, String value)
        throws SQLException
    {
        return locate(rs.findColumn(columnName), rs, value);
    }

//--------------------------------------------------------------------------

//--------------------------------------------------------------------------
    public static void main(String[] args)
    {
        //XSql XSql1 = new XSql();
    }

}

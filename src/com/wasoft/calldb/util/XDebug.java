/*
 * 程序调试工具类
 */
package com.wasoft.calldb.util;

/**
 * <p>Title: 程序调试工具类</p>
 * <p>Description: 程序调试工具类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: wasoft</p>
 * @version 1.0
 */

public class XDebug
{
    public String toString()
    {
        return getClass().getName();
    }

    public static XDebug makeXDebug()
    {
        if (xDebug == null)
        {
            xDebug = new XDebug();
        }
        return xDebug;
    }

    /**
     * 是否显示错误信息
     * @param value
     */
    public static void setDisplay(boolean value)
    {
        display = value;
    }

    public static void println(String msg)
    {
        if (display)
        {
            System.out.println(msg);
        }
    }

    public static void println(Object obj)
    {
        if (display)
        {
            System.out.println(obj);
        }
    }

    public static void printLine()
    {
        if (display)
        {
            System.out.println("======================================"
                               + "======================================");
        }
    }

//--------------------------------------------------------------------------
    /** 是否显示调试信息 */
    private static boolean display = true;
    private static XDebug xDebug = null;

    private XDebug()
    {
    }

//--------------------------------------------------------------------------
    public static void main(String[] args)
    {
        //XDebug XDebug1 = new XDebug();
    }

}

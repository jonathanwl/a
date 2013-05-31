/*
 * ������Թ�����
 */
package com.wasoft.calldb.util;

/**
 * <p>Title: ������Թ�����</p>
 * <p>Description: ������Թ�����</p>
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
     * �Ƿ���ʾ������Ϣ
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
    /** �Ƿ���ʾ������Ϣ */
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

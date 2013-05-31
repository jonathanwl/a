/*
 * 工具类
 */
package com.wasoft.calldb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <p>Title: 工具类</p>
 * <p>Description: 工具类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: wasoft</p>
 * @version 1.0
 */

public class XTool
{
    public XTool()
    {
    }

    public String toString()
    {
        return getClass().getName();
    }

    /**
     * 保留小数位数并四舍五入
     * @param value 源值
     * @param precision 精度位数
     * @return
     */
    public static double round(double value, int precision)
    {
        if (precision == 0)
        {
            return Math.round(value);
        }
        else if (precision > 0)
        {
            long l = (long)Math.pow(10, precision);
            return Math.round(value * l) / (double)l;
        }
//	else {
//	  throw new Exception("小数位不能为负值");
//	}
        return 0D;
    }

    /**
     * 查找字符串数组索引
     * @param arr 字符串数组
     * @param procedureName 要查找字符串
     * @return 索引值 <0为未找到
     */
    public static int arrayIndexOf(String[] arr, String value)
    {
        if (arr == null || value == null
            || arr.length <= 0 || value.length() <= 0)
        {
            return -1;
        }

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i].equals(value))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * 转换为日期
     * @param year
     * @param mon
     * @param day
     * @return
     */
    public static java.util.Date Str2Date(String year, String mon, String day)
    {
	  Calendar cal = Calendar.getInstance();
	  cal.set(Integer.parseInt(year), Integer.parseInt(mon) - 1, Integer.parseInt(day));
	  return cal.getTime();
	}
    public static java.util.Date Str2Date(String str)
    {
      if (str != null && !str.trim().equals(""))
      {
        if (str.indexOf("-") != -1)
        {
          String[] arrDate = str.split("-");
          return Str2Date(arrDate[0], arrDate[1], arrDate[2]);
        }
        else
        {
          return Str2Date(str.substring(0, 4), str.substring(4, 6), str.substring(6));
        }
      }
      else
      {
   //       return new java.util.Date();
          return null;
      }
    }
    
    
    /*
     * 带时间日期转换
     * 
     */
    public static java.util.Date Str2Time(String timestring)
    {
    	DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    	Date date = null;
    	try {
            if(timestring.indexOf("CST")>0){
            	sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);              
                date = (Date) sdf.parse(timestring);
                //formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            }else{
            	if(timestring.length()>10){
            		date = format2.parse(timestring);
            	}else{
            		Str2Date(timestring);
            	}
            }
    	} catch (Exception e) {   
            e.printStackTrace();   
    	}  
        return date;
	}
//--------------------------------------------------------------------------

//--------------------------------------------------------------------------
    public static void main(String[] args)
    {
        //XTool XTool1 = new XTool();
        System.out.println(String.valueOf(round(123.445, 2)));
    }

}

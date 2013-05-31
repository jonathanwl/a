package com.wasoft.util;

import java.util.*;
import java.text.*;
import java.sql.Timestamp;

public class DateUtil
{
  public DateUtil()
  {
  }

  /**
   * 转换为日期
   * @param year
   * @param mon
   * @param day
   * @return
   */
  public static Date Str2Date(String year, String mon, String day)
  {
	  return Str2Date(year,mon,day, "0","0", "0");
  }
  public static Date Str2Date(String year, String mon, String day, String hour, String minute, String second)
  {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(mon) - 1, Integer.parseInt(day),
				Integer.parseInt(hour), Integer.parseInt(minute), Integer.parseInt(second));
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	    //return new Date(Integer.parseInt(year) - 1900, Integer.parseInt(mon) - 1, Integer.parseInt(day));
  }
  public static Date Str2Date(String str)
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
        return Str2Date(str.substring(0,4), str.substring(4,6), str.substring(6));
      }
    }
    else
    {
      return new Date();
    }
  }
  public static Date Str2DateTime(String str)
  {
    if (str != null && !str.trim().equals(""))
    {
    	try{
	      String [] dt = str.split(" ");	      
	      String [] d = dt[0].split("-");
	      String [] t = dt[1].split(":");
	      return Str2Date(d[0], d[1], d[2], t[0], t[1], t[2]);
    	}
    	catch(Exception e){
    		System.err.println("转换时间出错：" + e.getMessage());
    		return new Date();
    	}    	
    }
    else
    {
      return new Date();
    }
  }
  /**
   * 对于给定的日期表达式，返回指定数目的月数以前或以后的日期。
   *
   * @param date
   * @param mon
   * @return
   */
  public static Date GoMonth(Date date, int mon)
  {
    //Timestamp st = new Timestamp(date.getTime());
    Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.MONTH, mon);
	return cal.getTime();
  }

  /**
   * 对于给定的日期表达式，返回指定数目的天数以前或以后的日期。
   * @param date
   * @param day
   */
//  public static Date GoDate(Date date, int day)
//  {
//    long time = date.getTime();
//    time += (long) day * 24 * 60 * 60 * 1000;
//    return new Date(time);
//  }
  public static Date GoDate(Date date, int n) {
      try 
      {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.DATE, n); // 增加一天
        // cd.add(Calendar.MONTH, n);//增加一个月

        return cd.getTime();

      }
      catch (Exception e) {
        return null;
      }
    }


  /**
   * 获取日期
   * @return 系统日期,输出格式如：2002-12-16
   */
  public static String getCurDate()
  {
      Calendar c = Calendar.getInstance();
      Timestamp ts = new Timestamp(c.getTime().getTime());
      String curDate = String.valueOf(ts);
      curDate = curDate.substring(0,curDate.indexOf(" "));

      return curDate;
  }

  /**
   * 获取时间
   * @return 系统时间,输出格式如：10:33:20
   */
  public static String getCurTime()
  {
      Calendar c = Calendar.getInstance();

      Timestamp ts = new Timestamp(c.getTime().getTime());
      String curTime = String.valueOf(ts);
      curTime = curTime.substring(11,curTime.indexOf("."));

      return curTime;
  }

  /**
   * 获取日期与时间
   * @return 系统日期与时间,输出格式如：2002-12-16 10:33:20
   */
  public static String getCurDateTime()
  {
      Calendar c=Calendar.getInstance();
      Timestamp ts=new Timestamp(c.getTime().getTime());
      String curDateTime=String.valueOf(ts);
      curDateTime=curDateTime.substring(0,curDateTime.indexOf("."));
      return curDateTime;
  }

  /**
   * 格式化输出系统日期
   * @param date 系统日期,格式如：2002-12-16
   * @return 格式化的系统日期,输出格式如：20021216[yyyyMMdd]
   */
  public static String formatDate(Date date, String pattern)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(date);
  }
  public static String formatDate(String date, String pattern)
      throws Exception
  {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    //Date dt = sdf.parse(date);
    return sdf.format(date);
  }
  /*
  public static String formatDate(String date)
  {
      String cdate=date.substring(0,4)+date.substring(5,7)+date.substring(8,10);
      return cdate;
  }
  public static String formatDate(Date date)
  {
    Timestamp ts = new Timestamp(date.getTime());
    String DateTime = String.valueOf(ts);
    DateTime = DateTime.substring(0,DateTime.indexOf("."));

    return formatDate(DateTime);
  }
*/


  /**
   * 格式化输出系统时间
   * @param time 系统时间,格式如：10:33:20
   * @return 格式化的系统时间,输出格式如：103320
   */
  public static String formatTime(String time)
  {
      String ctime=time.substring(0,2)+time.substring(3,5)+time.substring(6,8);
      return ctime;
  }

  /**
   * 获取系统年份
   * @return 系统年份,输出格式如：2003
   */
  public static int getYear()
  {
      String curDate=getCurDate();
      int year=Integer.parseInt(curDate.substring(0,4));
      return year;
  }

  /**
   * 获取系统月份
   * @return 系统月份,输出格式如：10
   */
  public static int getMon()
  {
      String curDate=getCurDate();
      int mon=Integer.parseInt(curDate.substring(5,7));

      return mon;
  }

  /**
   * 获取系统日期
   * @return 系统日期,输出格式如：20
   */
  public static int getDd()
  {
      String curDate=getCurDate();
      int dd=Integer.parseInt(curDate.substring(8,10));

      return dd;
  }
  
  public static String getDateTimeDiff(Date dt1, Date dt2)
  {
	  Calendar cal1 = Calendar.getInstance();
	  cal1.setTime(dt1);
	  Calendar cal2 = Calendar.getInstance();
	  cal2.setTime(dt2);
	  
	  long tickCount = cal2.getTimeInMillis() - cal1.getTimeInMillis();
	  
	  return getDateTime(tickCount);
  }
  
  public static String getDateTime(long tickCount)
  {
	  StringBuffer sb = new StringBuffer();
	  int day = (int)(tickCount/(1000*60*60*24));
	  tickCount %= (1000*60*60*24);//除去天后剩下的ms数
	  int hour = (int)(tickCount/(1000*60*60));
	  tickCount %= (1000*60*60);
	  int min = (int)(tickCount/(1000*60));
	  tickCount %= (1000*60);
	  int sec = (int)(tickCount/1000);
	  
	  if (day  > 0) 	sb.append(day  > 9 ? day : "0" + day ).append("天");
	  if (hour > 0)		sb.append(hour > 9 ? hour: "0" + hour).append("小时");
	  if (min  > 0) 	sb.append(min  > 9 ? min : "0" + min ).append("分钟");
	  if (sec  >= 0) 	sb.append(sec  > 9 ? sec : "0" + sec ).append("秒");
	  return sb.toString();
  }
  
  public static String getBeginDateOfMonth(Date dt, String pattern) throws Exception
  {
	  GregorianCalendar c = new GregorianCalendar();	  
	  c.setTime(dt);
	  c.set(GregorianCalendar.DATE, 1);
	  //return c.get(GregorianCalendar.YEAR) + "-" + (mon<10 ? ("0" + String.valueOf(mon)) : String.valueOf(mon)) + "-" + "01";
	  return formatDate(c.getTime(), pattern);
  }
  /**
   * 
   * @return本月最后一日
   * @throws Exception
   */
  public static String getEndDateOfMonth(Date dt, String pattern) throws Exception
  {
	  GregorianCalendar c = new GregorianCalendar();
	  c.setTime(dt);	  
	  GregorianCalendar end = new GregorianCalendar(c.get(GregorianCalendar.YEAR), c.get(GregorianCalendar.MONTH) + 1, 0);
	  return formatDate(end.getTime(), pattern);
  }
  
  public static void main(String[] args) throws Exception
  {
	  System.out.println(getBeginDateOfMonth(new Date(), "yyyy_MM_dd"));
	  System.out.println(getEndDateOfMonth(new Date(), "yyyy_MM_dd"));
  }
}

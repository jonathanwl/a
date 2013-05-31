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
   * ת��Ϊ����
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
    		System.err.println("ת��ʱ�����" + e.getMessage());
    		return new Date();
    	}    	
    }
    else
    {
      return new Date();
    }
  }
  /**
   * ���ڸ��������ڱ��ʽ������ָ����Ŀ��������ǰ���Ժ�����ڡ�
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
   * ���ڸ��������ڱ��ʽ������ָ����Ŀ��������ǰ���Ժ�����ڡ�
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
        cd.add(Calendar.DATE, n); // ����һ��
        // cd.add(Calendar.MONTH, n);//����һ����

        return cd.getTime();

      }
      catch (Exception e) {
        return null;
      }
    }


  /**
   * ��ȡ����
   * @return ϵͳ����,�����ʽ�磺2002-12-16
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
   * ��ȡʱ��
   * @return ϵͳʱ��,�����ʽ�磺10:33:20
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
   * ��ȡ������ʱ��
   * @return ϵͳ������ʱ��,�����ʽ�磺2002-12-16 10:33:20
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
   * ��ʽ�����ϵͳ����
   * @param date ϵͳ����,��ʽ�磺2002-12-16
   * @return ��ʽ����ϵͳ����,�����ʽ�磺20021216[yyyyMMdd]
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
   * ��ʽ�����ϵͳʱ��
   * @param time ϵͳʱ��,��ʽ�磺10:33:20
   * @return ��ʽ����ϵͳʱ��,�����ʽ�磺103320
   */
  public static String formatTime(String time)
  {
      String ctime=time.substring(0,2)+time.substring(3,5)+time.substring(6,8);
      return ctime;
  }

  /**
   * ��ȡϵͳ���
   * @return ϵͳ���,�����ʽ�磺2003
   */
  public static int getYear()
  {
      String curDate=getCurDate();
      int year=Integer.parseInt(curDate.substring(0,4));
      return year;
  }

  /**
   * ��ȡϵͳ�·�
   * @return ϵͳ�·�,�����ʽ�磺10
   */
  public static int getMon()
  {
      String curDate=getCurDate();
      int mon=Integer.parseInt(curDate.substring(5,7));

      return mon;
  }

  /**
   * ��ȡϵͳ����
   * @return ϵͳ����,�����ʽ�磺20
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
	  tickCount %= (1000*60*60*24);//��ȥ���ʣ�µ�ms��
	  int hour = (int)(tickCount/(1000*60*60));
	  tickCount %= (1000*60*60);
	  int min = (int)(tickCount/(1000*60));
	  tickCount %= (1000*60);
	  int sec = (int)(tickCount/1000);
	  
	  if (day  > 0) 	sb.append(day  > 9 ? day : "0" + day ).append("��");
	  if (hour > 0)		sb.append(hour > 9 ? hour: "0" + hour).append("Сʱ");
	  if (min  > 0) 	sb.append(min  > 9 ? min : "0" + min ).append("����");
	  if (sec  >= 0) 	sb.append(sec  > 9 ? sec : "0" + sec ).append("��");
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
   * @return�������һ��
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

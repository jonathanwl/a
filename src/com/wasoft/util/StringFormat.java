package com.wasoft.util;

import java.text.*;
import java.util.*;

/**
 * 提供用于格式化字符串的静态方法
 * 把用户输入的字符格式化成HTML的输出格式
 */
public class StringFormat
{
  // Static format objects
  private static SimpleDateFormat dateFormat = new SimpleDateFormat();
  private static DecimalFormat numberFormat = new DecimalFormat();

  /**
   * 日期字符串是否能以指定格式显示
   *
   * @param dateString 用于显示的日期/时间
   * @param dateFormatPattern 解析日期字符串所用的模式
   *   模式由java.text.SimpleDateFormat定义的模式字符表示
   * @return 有效返回true，否则返回false
   */
  public static boolean isValidDate(String dateString, String dateFormatPattern)
  {
	Date validDate = null;
	synchronized (dateFormat)
	{
	  try
	  {
		dateFormat.applyPattern(dateFormatPattern);
		dateFormat.setLenient(false);
		validDate = dateFormat.parse(dateString);
	  }
	  catch (ParseException e)
	  {
		// Ignore and return null
	  }
	}
	return validDate != null;
  }

  /**
   * 数字字符串是否在一个有效的整数范围内
   *
   * @param numberString 表示整数的字符串
   * @param min 范围的最小值
   * @param max 范围的最大值
   * @return 有效返回true，否则返回false
   */
  public static boolean isValidInteger(String numberString, int min, int max)
  {
	Integer validInteger = null;
	try
	{
	  Number aNumber = numberFormat.parse(numberString);
	  int anInt = aNumber.intValue();
	  if (anInt >= min && anInt <= max)
	  {
		validInteger = new Integer(anInt);
	  }
	}
	catch (ParseException e)
	{
	  // Ignore and return null
	}
	return validInteger != null;
  }

  /**
   * 检验邮件地址的有效性
   *
   * @param emailAddrString 表示邮件的字符串
   * @return 有效返回true，否则返回false
   */
  public static boolean isValidEmailAddr(String emailAddrString)
  {
	boolean isValid = false;
	if (emailAddrString != null &&
		emailAddrString.indexOf("@") != -1 &&
		emailAddrString.indexOf(".") != -1)
	{
	  isValid = true;
	}
	return isValid;
  }

  /**
   * 字符串是否在一个有效的字符串集合里，可忽略大小写
   *
   * @param value 需要验证的字符串
   * @param validStrings 有效的字符串集合
   * @param ignoreCase 为ture忽略大小写
   * @return 有效返回true，否则返回false
   */
  public static boolean isValidString(String value, String[] validStrings,
									  boolean ignoreCase)
  {
	boolean isValid = false;
	for (int i = 0; validStrings != null && i < validStrings.length; i++)
	{
	  if (ignoreCase)
	  {
		if (validStrings[i].equalsIgnoreCase(value))
		{
		  isValid = true;
		  break;
		}
	  }
	  else
	  {
		if (validStrings[i].equals(value))
		{
		  isValid = true;
		  break;
		}
	  }
	}
	return isValid;
  }

  /**
   * 返回可正常显示的HTML字符码
   *
   * @param in 欲转换的字符串
   * @return 转换后的字符串
   */
  public static String toHTMLString(String in)
  {
	StringBuffer out = new StringBuffer();
	for (int i = 0; in != null && i < in.length(); i++)
	{
	  char c = in.charAt(i);
	  if (c == '\'')
	  {
		out.append("&#39;");
	  }
	  else if (c == '\"')
	  {
		out.append("&#34;");
	  }
	  else if (c == '<')
	  {
		out.append("&lt;");
	  }
	  else if (c == '>')
	  {
		out.append("&gt;");
	  }
	  else if (c == '&')
	  {
		out.append("&amp;");
	  }
	  else
	  {
		out.append(c);
	  }
	}
	return out.toString();
  }

  /**
   * 使用模式转换字符串为日期，java.text.SimpleDateFormat类为模式描述
   *
   * @param dateString 要转换的字符串
   * @param dateFormatPattern 模式
   * @return 相对应的日期
   * @exception ParseException, 字符串不匹配模式
   */
  public static Date toDate(String dateString, String dateFormatPattern) throws
	  ParseException
  {
	Date date = null;
	if (dateFormatPattern == null)
	{
	  dateFormatPattern = "yyyy-MM-dd";
	}
	synchronized (dateFormat)
	{
	  dateFormat.applyPattern(dateFormatPattern);
	  dateFormat.setLenient(false);
	  date = dateFormat.parse(dateString);
	}
	return date;
  }

  /**
   * 返回日期时间字符串中的日期部分
   * @param strDateTime
   * @return
   * 如：2004-6-6 00:00:00.0 返回 2004-6-6
   */
  public static String getDate(String strDateTime)
  {
    return strDateTime.substring(0,strDateTime.indexOf(" "));
  }
  /**
   * 使用模式转换字符串为数字型，java.text.NumberFormat类为模式描述
   *
   * @param numString 转换字符串
   * @param numFormatPattern 模式
   * @return 相应的数字
   * @exception ParseException, 字符串不匹配模式
   */
  public static Number toNumber(String numString, String numFormatPattern) throws
	  ParseException
  {
	Number number = null;
	if (numFormatPattern == null)
	{
	  numFormatPattern = "######.##";
	}
	synchronized (numberFormat)
	{
	  numberFormat.applyPattern(numFormatPattern);
	  number = numberFormat.parse(numString);
	}
	return number;
  }

  /**
   * 用to替换字符串中in中的from
   *
   * @param in 源字符串
   * @param from 要替换的子字符串
   * @param to 代替字符串
   * @return 返回替换后的字符串
   */
  public static String replaceInString(String in, String from, String to)
  {
	if (in == null || from == null || to == null)
	{
	  return in;
	}

	StringBuffer newValue = new StringBuffer();
	char[] inChars = in.toCharArray();
	int inLen = inChars.length;
	char[] fromChars = from.toCharArray();
	int fromLen = fromChars.length;

	for (int i = 0; i < inLen; i++)
	{
	  if (inChars[i] == fromChars[0] && (i + fromLen) <= inLen)
	  {
		boolean isEqual = true;
		for (int j = 1; j < fromLen; j++)
		{
		  if (inChars[i + j] != fromChars[j])
		  {
			isEqual = false;
			break;
		  }
		}
		if (isEqual)
		{
		  newValue.append(to);
		  i += fromLen - 1;
		}
		else
		{
		  newValue.append(inChars[i]);
		}
	  }
	  else
	  {
		newValue.append(inChars[i]);
	  }
	}
	return newValue.toString();
  }

  /**
   * 去除字符串左边空格
   * @param source 源字符串
   * @return
   */
  public static String LTrim(String source)
  {
	if (source == null || source.length() == 0)
	{
	  return "";
	}
	if (source.charAt(0) != ' ')
	{
	  return source;
	}
	else
	{
	  int i;
	  for (i = 0; i < source.length(); i++)
	  {
		if (source.charAt(i) != ' ')
		{
		  break;
		}
	  }
	  return source.substring(i);
	}
  }

  /**
   * 去除字符串右边空格
   * @param source
   * @return
   */
  public static String RTrim(String source)
  {
	if (source == null || source.length() == 0)
	{
	  return "";
	}
	if (source.charAt(source.length() - 1) != ' ')
	{
	  return source;
	}
	else
	{
	  int i;
	  for (i = source.length() - 1; i > 0; i--)
	  {
		if (source.charAt(i) != ' ')
		{
		  break;
		}
	  }
	  return source.substring(0, i + 1);
	}
  }

  /**
   * 去除字符串前后空格
   * @param source
   * @return
   */
  public static String Trim(String source)
  {
	return LTrim(RTrim(source));
  }

  /**
   * 返回重复个数的字符串
   * @param str  要重复的字符串
   * @param number  重复次数
   * @return
   */
  public static String repeatString(String str, int number)
  {
    str = getNullString(str);
	if (number < 0)
	{
	  return "";
	}
	StringBuffer sb = new StringBuffer(str.length() * number);
	for (int i = 0; i < number; i++)
	{
	  sb.append(str);
	}
	return sb.toString();
  }

  /**
   * 返回用于前台alert显示的字符串
   * @param str
   * @return
   */
  public static String getAlertString(String str)
  {
    str = getNullString(str);
	return str.replace( (char)10, ' ').replace( (char)13, ' ').replace('"','\'');
  }

  public static String getNullString(String str)
  {
	return (str == null) ? "" : str;
  }

  /**
   * 以HEX返回字节数组中的值
   * @param bytes
   * @return
   */
  public static final String encodeHex(byte[] bytes)
  {
	StringBuffer buff = new StringBuffer(bytes.length * 2);
	String b;
	for (int i = 0; i < bytes.length; i++)
	{
	  b = Integer.toHexString(bytes[i]);
      // byte是两个字节的，而上面的Integer.toHexString会把字节扩展为4个字节
	  buff.append(b.length() > 2 ? b.substring(6, 8) : b);
	  buff.append(" ");
	}
	return buff.toString();
  }

  /**
   * 返回字节数组中的值
   * @param bytes
   * @return
   */
  public static final String arrayToString(byte[] bytes)
  {
	StringBuffer buff = new StringBuffer();
	for (int i = 0; i < bytes.length; i++)
	{
	  buff.append(bytes[i] + " ");
	}
	return buff.toString();
  }
}

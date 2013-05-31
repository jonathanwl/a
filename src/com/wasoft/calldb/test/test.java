package com.wasoft.calldb.test;

import java.util.Date;
import java.util.Random;
import com.wasoft.util.*;
public class test {

	public test() {
		super();
	}
	
	public static void out(String str)
	{
		System.out.println(str);
	}
	
	public static void main(String[] args) throws Exception {

		//out("" + Constant.getTaskType(Constant.Organ.Bank, Constant.cmdYhqqgjjGrye));
		//("Asia/Shanghai");
				
		System.setProperty("user.timezone","Asia/Shanghai");
		out("Date()=" + new Date());
		out("Calendar=" + DateUtil.getCurDateTime());
		//out("" + TimeZone.getDefault() );

		for(int i=0; i<10;i++)
		{			
			double d = (new Random()).nextDouble();
			long l = (long)(d * (Math.pow(10,6)) * 100);
			double dd = l / 100.0;
			out(i + "," + d + "," + l + "," + dd);
			out((new java.util.Date()).toString());
			//Thread.sleep(2000);
		}

		//ParseXML px = new ParseXML();
		//out(px.transFormer("./config/config.xml", "./config/config.xsl"));
		out("P_dfeefef".toLowerCase().replace("p_", "tmp_").replace("tx_",""));		
		StringBuffer sb = new StringBuffer("123456780");
		sb.insert(sb.length() - 1, '9');
		out(sb.toString());

	}	

}

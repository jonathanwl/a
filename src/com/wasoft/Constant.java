package com.wasoft;

import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Constant 
{	
	private static final Log log = LogFactory.getLog(Constant.class);
	public static final String FILE_SEP 		= System.getProperty("file.separator");
	public static final String UserDir			= System.getProperty("user.dir");
	public static final String CONFIG_PATH		= "./config/";
	
	public static String CONGFIG_FILE 			= CONFIG_PATH + "config.xml";
	public static String CONTAINER_FILE			= CONFIG_PATH + "jetty.xml";
	public static final String DATA_PATH		= UserDir + FILE_SEP + "data";
	public static final String LOG_PATH			= UserDir + FILE_SEP + "log";
	public static final String INDEX_STORE_PATH	= UserDir + FILE_SEP + "index";
	
	//在linux环境下仍然出现乱码的情况可以在操作系统上设置export LANG=zh_CN.GBK
	public static final String CHARSET			= "GBK";
	
	//是否启用监控服务，该服务提供浏览器方式下的交易监控
	public static final boolean RunMonitorServer= true;

	public static int WebServicePort		= 8081;//WebService数据服务端口
	public static boolean bDbtrace	= false;//日志监控控制开关
	
	//监控端颜色常量
	public static final String COLOR_RECEIVE 	= "blue";
	public static final String COLOR_SEND 		= "maroon";
	public static final String COLOR_INIT 		= "gray";
	public static final String COLOR_ERROR 		= "red";
	public static final String COLOR_RUNNING	= "limegreen";
	
	public static final int DELAYTIME 			= 60 * (1000 * 60);//定时任务时间触发间隔
		

	/**
	 * 获取一个小于max的随机整数
	 * @param max
	 * @return
	 */
	public static int getRandInt(int max)
	{
		return (new Random()).nextInt(max);
	}
	/**
	 * 获取一个大于或等于min但小于max的随机整数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandInt(int min, int max)
	{
		return min + getRandInt(max-min);
	}
	
	public static void main(String[] args) throws Exception {
		log.debug("");
	}
	
	//////////////////////////////////////////////////////
	//以下参数从配置文件获取初始数据							//
	//////////////////////////////////////////////////////
	public static int POOL_SIZE, LogCounter;

	public static boolean dbMonitor, serviceTimer;	
}


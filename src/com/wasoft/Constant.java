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
	
	//��linux��������Ȼ�����������������ڲ���ϵͳ������export LANG=zh_CN.GBK
	public static final String CHARSET			= "GBK";
	
	//�Ƿ����ü�ط��񣬸÷����ṩ�������ʽ�µĽ��׼��
	public static final boolean RunMonitorServer= true;

	public static int WebServicePort		= 8081;//WebService���ݷ���˿�
	public static boolean bDbtrace	= false;//��־��ؿ��ƿ���
	
	//��ض���ɫ����
	public static final String COLOR_RECEIVE 	= "blue";
	public static final String COLOR_SEND 		= "maroon";
	public static final String COLOR_INIT 		= "gray";
	public static final String COLOR_ERROR 		= "red";
	public static final String COLOR_RUNNING	= "limegreen";
	
	public static final int DELAYTIME 			= 60 * (1000 * 60);//��ʱ����ʱ�䴥�����
		

	/**
	 * ��ȡһ��С��max���������
	 * @param max
	 * @return
	 */
	public static int getRandInt(int max)
	{
		return (new Random()).nextInt(max);
	}
	/**
	 * ��ȡһ�����ڻ����min��С��max���������
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
	//���²����������ļ���ȡ��ʼ����							//
	//////////////////////////////////////////////////////
	public static int POOL_SIZE, LogCounter;

	public static boolean dbMonitor, serviceTimer;	
}


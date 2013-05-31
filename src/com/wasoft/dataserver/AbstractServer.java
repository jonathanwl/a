package com.wasoft.dataserver;

import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wasoft.Constant;
import com.wasoft.InitEnv;
import com.wasoft.dataserver.ws.StartService;
import com.wasoft.jetty.AppServer;

public class AbstractServer 
{
	private static final Log log = LogFactory.getLog(AbstractServer.class);
	protected ServerSocket serverSocket;
    protected ExecutorService executorService;//�̳߳�    

    public AbstractServer()
    {    	
    	if (InitEnv.initServer())
		{	
    		//WEBSERVICE���ݷ���
    		StartService.start();
    		
    		//������ط��񣬲�����ҵ��������������ݴ���
    		if (Constant.RunMonitorServer)
	        {
	        	(new AppServer()).start();
	        }
    		//������ʱ����
    		startTimerTask();   		
		}
    }
    
    @SuppressWarnings("unchecked")
    protected void startTimerTask()
    {
    	try
		{	    		
    		String className = "atwasoft";
    		className = className.substring(0, 1).toUpperCase() + className.substring(1) + "TimerTask";		
    		Class <TimerTask> clazz = null;
    		TimerTask tt = null;
    		try {
    			clazz = (Class <TimerTask>)Class.forName("com.wasoft.communication.TimerTask." + className);
    			Constructor cons = clazz.getConstructor(new Class[] {});
    			tt = (TimerTask)cons.newInstance();
    			//log.debug("load class:" + clazz.getName());
    		} 
    		catch (ClassNotFoundException e)
    		{
    			//log.debug("δ���嶨ʱ������:[" + className + "]");
    		}
    		if (tt != null)
    		{
    			Thread.sleep(5000);
    			(new Timer(true)).schedule(tt , 0, Constant.DELAYTIME);
    		}    		
		}
		catch(Exception e)
		{
			log.error("������ʱ����ʧ�ܣ�" + e.getMessage());
		}
    }

}


package com.wasoft.dataserver;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataServer extends AbstractServer
{
	private static final Log log = LogFactory.getLog(DataServer.class);

	public DataServer(){
		super();
	}
    public static void main(String[] args) throws IOException {
    	
        new DataServer();
    }
    
    public static void GuiStart()
    {
    	(new Thread(new Runnable(){
    		public void run()
    		{
               	try
               	{
               		new DataServer();
               	}
               	catch(Exception e)
               	{
               		log.error(e.getMessage());
               	}
            }
        })).start();  	
    	
    }

}
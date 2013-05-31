package com.wasoft.jetty;

import java.io.FileInputStream;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mortbay.jetty.Server;
import org.mortbay.xml.XmlConfiguration;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import com.wasoft.Constant;
import com.wasoft.InitEnv;
import com.wasoft.javabean.*;

public class AppServer extends Thread
{
	private static final Log log = LogFactory.getLog(AppServer.class);
	
    public AppServer()
    {    	 
    	
    }
    
    public static class HandleServlet extends HttpServlet
    {
		private static final long serialVersionUID = -7169674364673998456L;

		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException
		{
			request.setCharacterEncoding("GBK");
    		response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("GBK");
            
            response.setHeader("Pragma","No-cache");  
            response.setHeader("Cache-Control","no-cache");  
            response.setDateHeader("Expires", 0);
		}
    }
    
    public static class DefaultServlet extends HandleServlet
    {
		private static final long serialVersionUID = -5517081260700658500L;
    	
        protected void doGet(HttpServletRequest request,
        				HttpServletResponse response) throws ServletException, IOException
        {
        	String userid = request.getParameter("userid");
    		String password = request.getParameter("password");
    		if (userid != null && password != null)
        	{        		
        		String result = "false";
        		if (userid.equals(InitEnv.getUserid()) && password.equals(InitEnv.getPassword()))
        		{
        			result = "true";
        		}
        		else
        		{
        			result = "false";
        		}
        		//记录日志
        		String s = "";
        		
        		response.setContentType("text/xml");
        		PrintWriter out = response.getWriter();
        		out.print("<results><result>" + result + "</result></results>");
        		out.flush();
    			out.close();
        	}
        }
    }
    
    public static class NetDataServiceServlet extends HandleServlet
    {
		private static final long serialVersionUID = 4689187528377480142L;

		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException
		{
			super.doGet(request, response);			
            response.getWriter().println(DataServiceTraceInfo.getInstance().getTraceInfo2Html());
		}
    }

	public void run() {
        try
        {
        	log.debug("");
        	log.debug("启动JEE容器服务...");
	        Server server = new Server();
	        XmlConfiguration config = new XmlConfiguration(new FileInputStream(Constant.CONTAINER_FILE)); 
	        config.configure(server);

	        //个人网上业务平台数据请求监控
	        Context ctx = new Context(server, "/net", Context.SESSIONS);	        
	        ctx.addServlet(new ServletHolder(new NetDataServiceServlet()), "/NetDataService");
	        
       
	        //缺省servlet，处理跟踪请求和重定向到监控页面
	        Context defaultCtx = new Context(server, "/", Context.SESSIONS);
	        defaultCtx.addServlet(new ServletHolder(new DefaultServlet()), "/*");	
	        server.start();
	        log.debug("JEE容器启动成功，监听端口：[" + server.getConnectors()[0].getPort() + "]");
	        server.join();	        
        }
        catch(Exception e)
        {
        	log.error("启动JEE容器服务失败：" + e.getMessage());
        }		
	}
	
	public static void main(String[] args) throws IOException {
        new AppServer().run();
    }
}

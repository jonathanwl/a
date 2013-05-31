package com.wasoft.dataserver.ws;

import javax.xml.ws.Endpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wasoft.Constant;
import com.wasoft.dataserver.ws.common.Wasys3_common;
import com.wasoft.dataserver.ws.da.Da;
import com.wasoft.dataserver.ws.report.Report;


public class StartService {
	private static final Log log = LogFactory.getLog(StartService.class);
	private static final String server = "0.0.0.0";

	public static void start()
	{

			try {
			log.debug("");
			log.debug("启动WEB数据服务 ...");
			String url = "http://" + server + ":" + Constant.WebServicePort;
			Endpoint.publish(url + "/CommonService", new Wasys3_common());
			log.debug(url + "/CommonService 发布成功");
			
			Endpoint.publish(url + "/DaService", new Da());
			log.debug(url + "/DaService 发布成功");

            Endpoint.publish(url + "/ReportService", new Report());
			log.debug(url + "/ReportService 发布成功");
//			Endpoint.publish(url + "/controlcenterService", new ControlCenter());
//			log.debug(url + "/controlcenterService 发布成功");
			
			log.debug("WEB数据服务启动成功，监听端口：[" + Constant.WebServicePort + "]");
		} catch (Exception e) {
			log.error("WEB数据服务启动失败：" + e.getMessage());
		}

	}

	public static void main(String[] args)
	{
		StartService.start();
	}
}

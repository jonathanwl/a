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
			log.debug("����WEB���ݷ��� ...");
			String url = "http://" + server + ":" + Constant.WebServicePort;
			Endpoint.publish(url + "/CommonService", new Wasys3_common());
			log.debug(url + "/CommonService �����ɹ�");
			
			Endpoint.publish(url + "/DaService", new Da());
			log.debug(url + "/DaService �����ɹ�");

            Endpoint.publish(url + "/ReportService", new Report());
			log.debug(url + "/ReportService �����ɹ�");
//			Endpoint.publish(url + "/controlcenterService", new ControlCenter());
//			log.debug(url + "/controlcenterService �����ɹ�");
			
			log.debug("WEB���ݷ��������ɹ��������˿ڣ�[" + Constant.WebServicePort + "]");
		} catch (Exception e) {
			log.error("WEB���ݷ�������ʧ�ܣ�" + e.getMessage());
		}

	}

	public static void main(String[] args)
	{
		StartService.start();
	}
}

package com.wasoft.dataserver.ws.report;

import java.util.List;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.WebMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.wasoft.calldb.DbMethod;
import com.wasoft.calldb.sql.Row;
import com.wasoft.dataserver.ws.report.bean.*;
import com.wasoft.javabean.DataServiceTraceInfo;

@WebService(serviceName = "ReportService", targetNamespace = "http://ws.report.wasoft.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Report {
	private static final Log log = LogFactory.getLog(Report.class);
	
	
	@WebMethod
	@WebResult(partName = "version")
	public String getVer() {
		DataServiceTraceInfo.getInstance().getTrace("���ذ汾��",
				DataServiceTraceInfo.TYPE.Report);
		return "wasoft ReportWebService ver 1.0";

	}
	
	@WebMethod
	//��������
	public 	String addReport(ReportXx reportXx){
		String trace = "";
		try
		{
		   DbMethod.makeDbSingleton().Execute("REPORT_ADD",reportXx);			
		
		} catch (Exception e) {
			trace =  e.getMessage();
			log.error(trace);
		
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Report);
		}
		   return trace;
			
	}
	@WebMethod
	//����༭
	public 	String editReport(ReportXx reportXx){
		String trace = "";
		try
		{	
			if(reportXx.getReport_file().length>0){
				DbMethod.makeDbSingleton().Execute("REPORT_UPDATE",reportXx);
			}else{
				DbMethod.makeDbSingleton().Execute("REPORT_EDIT",reportXx);
			}		
		} catch (Exception e) {
			trace =  e.getMessage();
			log.error(trace);
		
		} finally {
			DataServiceTraceInfo.getInstance().getTrace(trace,
					DataServiceTraceInfo.TYPE.Report);
		}
		   return trace;
			
	}
	@WebMethod
	//����ģ���������ѯ
	public  ReportModule[] getMenu(ReportXx reportXx)	
	{	
		String trace = "�����б�";
		ReportModule[] rmArry= null;	
	try
	{
		List<Row> lst = DbMethod.makeDbSingleton().Open("REPORT_MENU_CX",reportXx);	
		rmArry = new ReportModule[lst.size()];
		for (int i = 0; i < lst.size(); i++) {
			Row row = lst.get(i);
			ReportModule rm = new ReportModule();	
			rm.setBm(row.getTrimString("f_name"));
			rm.setMc(row.getTrimString("f_caption"));
			rmArry[i] = rm;
		}
	} catch (Exception e) {
		trace = "<font color='red'>�б��ѯ����" + e.getMessage() + "</font>";
		log.error(trace);
		return null;
	} finally {
		DataServiceTraceInfo.getInstance().getTrace(trace,
				DataServiceTraceInfo.TYPE.Report);
	}
	return rmArry;
	}
	@WebMethod
	//��ѯ�鼯��
	public  ReportModule[] getGjd()	
	{	
		String trace = "�����б�";
		ReportModule[] rmArry= null;	
	try
	{
		List<Row> lst = DbMethod.makeDbSingleton().Open("REPORT_GJD_CX",null);	
		rmArry = new ReportModule[lst.size()];
		for (int i = 0; i < lst.size(); i++) {
			Row row = lst.get(i);
			ReportModule rm = new ReportModule();	
			rm.setBm(row.getTrimString("bm"));
			rm.setMc(row.getTrimString("mc"));
			rmArry[i] = rm;
		}
	} catch (Exception e) {
		trace = "<font color='red'>�б��ѯ����" + e.getMessage() + "</font>";
		log.error(trace);
		return null;
	} finally {
		DataServiceTraceInfo.getInstance().getTrace(trace,
				DataServiceTraceInfo.TYPE.Report);
	}
	return rmArry;
	}
	@WebMethod
	//����Ӧ���������ѯ
	public  ReportModule[] getApp(ReportXx reportXx)	
	{	
		String trace = "�����б�";
		ReportModule[] rmArry= null;	
	try
	{
		List<Row> lst = DbMethod.makeDbSingleton().Open("REPORT_APP_CX",reportXx);	
		rmArry = new ReportModule[lst.size()];
		for (int i = 0; i < lst.size(); i++) {
			Row row = lst.get(i);
			ReportModule rm = new ReportModule();	
			rm.setBm(row.getTrimString("f_applicationkey"));
			rm.setMc(row.getTrimString("f_caption"));
			rmArry[i] = rm;
		}
	} catch (Exception e) {
		trace = "<font color='red'>�б��ѯ����" + e.getMessage() + "</font>";
		log.error(trace);
		return null;
	} finally {
		DataServiceTraceInfo.getInstance().getTrace(trace,
				DataServiceTraceInfo.TYPE.Report);
	}
	return rmArry;
	}
	
	@WebMethod
	//��ȡ������Ϣ
	public ReportXx getReport(ReportXx reportXx){
		 String trace="";
		try
		{  		
			DbMethod.makeDbSingleton().Execute("REPORT_GETREPORT",reportXx);	
		} catch (Exception e) {
				trace =  e.getMessage();				
				log.error(trace);
		} finally {
				DataServiceTraceInfo.getInstance().getTrace(trace,
						DataServiceTraceInfo.TYPE.Report);
			}
		reportXx.setTrace(trace);
		return reportXx;
	}
	@WebMethod
	//Ԥ����ӡʱ�����ݿⱨ��Ԥ�汾��
	public ReportXx getReport_Preview(ReportXx reportXx){
		 String trace="";
		try
		{  		
			DbMethod.makeDbSingleton().Execute("REPORT_GETREPORT_PREVIEW",reportXx);	
		} catch (Exception e) {
				trace =  e.getMessage();				
				log.error(trace);
		} finally {
				DataServiceTraceInfo.getInstance().getTrace(trace,
						DataServiceTraceInfo.TYPE.Report);
			}
		reportXx.setTrace(trace);
		return reportXx;
	}
	@WebMethod
	//��ȡӦ�ñ�ʾ�����б���ID
	public ReportXx[] getReport_id(ReportXx reportXx){
		 String trace="";
		 ReportXx[] rxArray= null;
		try
		{  		
			List<Row> list = DbMethod.makeDbSingleton().Open("REPORT_ID",reportXx);	
			rxArray = new ReportXx[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = (Row) list.get(i);
				ReportXx rx = new ReportXx();
				rx.setId(row.getString("id"));
				rxArray[i] = rx;
			}
		} catch (Exception e) {
				trace =  e.getMessage();				
				log.error(trace);
		} finally {
				DataServiceTraceInfo.getInstance().getTrace(trace,
						DataServiceTraceInfo.TYPE.Report);
			}		
		reportXx.setTrace(trace);
		return rxArray;
	}
	@WebMethod
	//��ձ�������
	public void clearReport(String app_key){
		 String trace="";
		try
		{  		
			DbMethod.makeDbSingleton().Execute("REPORT_CLEAR",app_key);			
		} catch (Exception e) {
				trace =  e.getMessage();				
				log.error(trace);
		} finally {
				DataServiceTraceInfo.getInstance().getTrace(trace,
						DataServiceTraceInfo.TYPE.Report);
			}		
	}
	@WebMethod
	//��ձ�������
	public void recoverReport(ReportXx reportXx){
		 String trace="";
		try
		{  		
			DbMethod.makeDbSingleton().Execute("REPORT_RECOVER_BLOB",reportXx);			
		} catch (Exception e) {
				trace =  e.getMessage();				
				log.error(trace);
		} finally {
				DataServiceTraceInfo.getInstance().getTrace(trace,
						DataServiceTraceInfo.TYPE.Report);
			}		
	}
	@WebMethod
	//��ȡӦ��Ӧ�������б���
	public ReportXx[] getReport_appkey(ReportXx reportXx){
		 String trace="";
		 ReportXx[] rxArray= null;
		try
		{  		
			List<Row> list = DbMethod.makeDbSingleton().Open("REPORT_APPKEY",reportXx);	
			rxArray = new ReportXx[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Row row = (Row) list.get(i);
				ReportXx rx = new ReportXx();
				rx.setId(row.getString("id"));
				rx.setApp_key(row.getString("app_key"));
				rx.setGjdbm(row.getString("gjdbm"));
				rx.setModule_key(row.getString("module_key"));
				rx.setModule_name(row.getString("module_name"));
				rx.setReport_title(row.getString("report_title"));
				rx.setReport_filename(row.getString("report_filename"));
				rx.setReport_document(row.getString("report_document"));
				rx.setEditor(row.getString("editor"));
				rx.setEditing_time(row.getString("editing_time"));				
				rxArray[i] = rx;
			}
		} catch (Exception e) {
				trace =  e.getMessage();				
				log.error(trace);
		} finally {
				DataServiceTraceInfo.getInstance().getTrace(trace,
						DataServiceTraceInfo.TYPE.Report);
			}		
		reportXx.setTrace(trace);
		return rxArray;
	}
}
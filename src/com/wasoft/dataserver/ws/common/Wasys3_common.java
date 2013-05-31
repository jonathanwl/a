package com.wasoft.dataserver.ws.common;

/**
 * web���񷢲��࣬���Ϊ@WebMethod�ķ�����Ϊ�����ṩ����ķ�������
 * ͨ��webservice
 */
import java.sql.*;
import java.util.*;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.WebMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wasoft.calldb.CallDbException;
import com.wasoft.calldb.DbMethod;
import com.wasoft.calldb.sql.Row;
import com.wasoft.dataserver.ws.CommBase;
import com.wasoft.dataserver.ws.common.bean.*;
import com.wasoft.javabean.DataServiceTraceInfo;

@WebService(serviceName = "CommonService", targetNamespace = "http://ws.common.wasoft.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)

public class Wasys3_common extends CommBase{
	private static final Log log = LogFactory.getLog(Wasys3_common.class);

		@WebMethod
		@WebResult(partName = "version")
		public String getVer() {
			DataServiceTraceInfo.getInstance().getTrace("���ذ汾��",
					DataServiceTraceInfo.TYPE.Common);
			return "wasoft CommonWebService ver 1.0";

		}
		
		/**
		 * ͨ����ͼsql��ѯ����1
		 */
		@WebMethod
		@WebResult(partName = "StringArray")
		public String[][] find1(
								@WebParam(partName = "sql") String sql,
								@WebParam(partName = "pagesize") int pagesize,
								@WebParam(partName = "pagenum") int pagenum				
							
		){
			String trace = "��ѯ"+sql;	
			String[][] str1=null;
			try
			{
				List <Row> lst=DbMethod.makeDbSingleton().Open("Common_GETTABLE", sql);	
				str1=paging(lst,pagesize,pagenum);	
				return str1;	
			}
			catch(Exception e)
			{  
				trace = "<font color='red'>��ѯ����" + e.getMessage() + "</font>";
				log.error(trace);
			}
			finally{
				DataServiceTraceInfo.getInstance().getTrace(trace, DataServiceTraceInfo.TYPE.Common);
			}
			return null;
		}
		/**
		 * ͨ���ĵ�sql��ѯ����
		 */
		@WebMethod
		@WebResult(partName = "StringArray")
		public String[][] find2(@WebParam(partName = "sql") String sql){
			String trace = "��ѯ"+sql;
			String[][] str1=null;
			String[] str2=null;
			int i=0;
			int j=0;
			try
			{
				List <Row> lst=DbMethod.makeDbSingleton().Open("Common_GETTABLE", sql);	
				str1=new String[lst.size()][];
		        for(Row row : lst){
		            i=row.getColumnCount();
	                str2 = new String[i];
	               for (int k= 0;k < i; k++){
	            	   String str= row.getColumnType(k+1)+"##"+row.getColumnName(k+1);
	                   
	                   switch (row.getColumnType(k+1)){
	                       case Types.BLOB:
	                    	   str += "##"+" ";
	                           break;
	                       case Types.FLOAT:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.NUMERIC:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.DOUBLE:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.DECIMAL:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.SMALLINT:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.BIGINT:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.INTEGER:
		                    	   str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
	                         default:
	                               str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?" ":row.getTrimString(k+1));
	                   }   
	                   str2[k]=str;
	               }
	             str1[j++]=str2;	
		
	            }	
				return str1;	
			}
			catch(Exception e)
			{  
				trace = "<font color='red'>��ѯ����" + e.getMessage() + "</font>";
				log.error(trace);
			}
			finally{
				DataServiceTraceInfo.getInstance().getTrace(trace, DataServiceTraceInfo.TYPE.Common);
			}
			return null;
		}
		
		/**
		 * ͨ��sql��ѯ����2��ҳ
		 */
		@WebMethod
		@WebResult(partName = "StringArray")
		public String[][] find3(
				@WebParam(partName = "sql") String sql,
				@WebParam(partName = "pagesize") int pagesize,
				@WebParam(partName = "pagenum") int pagenum	
		){
			System.out.print("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwfind3find3find3");
			String trace = "��ѯ"+sql;
			String[][] str1=null;
			try
			{
				List <Row> lst=DbMethod.makeDbSingleton().Open("Common_GETTABLE", sql);	
				str1=paging1(lst,pagesize,pagenum);	
				return str1;		
			}
			catch(Exception e)
			{  
				trace = "<font color='red'>��ѯ����" + e.getMessage() + "</font>";
				log.error(trace);
			}
			finally{
				DataServiceTraceInfo.getInstance().getTrace(trace, DataServiceTraceInfo.TYPE.Common);
			}
			return null;
		}
		
		/**
		 * ͨ��������ѯt_mk_sys_files����
		 */
		@WebMethod
		@WebResult(partName = "StringArray")
		public String[][] find4(
				@WebParam(partName = "tablename") String tablename,
				@WebParam(partName = "dataId") long dataId,
				@WebParam(partName = "fieldname") String fieldname	
		){
			String sql = "select id, f_filename, f_filesize, f_created as f_createtime, f_author_name as f_creator, f_author_id, " +
			             "f_attach_name as f_attach_name, f_attach_type as f_attachetype, f_source_tablename as f_srctablename, " +
			             "f_source_fieldname as f_fieldname, f_source_dataid as f_documentid from t_mk_sys_files";
	        String sqlwhere = "";
	        if (tablename != null && !tablename.equals("")) {
		        sqlwhere = "f_source_tablename='" + tablename +"'";
	        }
	        if (dataId > 0) {
		        sqlwhere = sqlwhere + " and f_source_dataid=" + dataId;
	        }
	        if (fieldname != null && !fieldname.equals("")) {
		        sqlwhere = sqlwhere + " and f_source_fieldname='" + fieldname + "'";
	        }
	        if (!sqlwhere.equals("")) {
		        sql = sql + " where " + sqlwhere;
	        }
	        sql = sql + " order by f_created desc";
			String trace = "��ѯ"+sql;
			String[][] str1=null;
			String[] str2=null;
			int i=0;
			int j=0;
			try
			{
				List <Row> lst=DbMethod.makeDbSingleton().Open("Common_GETTABLE", sql);	
				str1=new String[lst.size()][];
		        for(Row row : lst){
		            i=row.getColumnCount();
	                str2 = new String[i];
	               for (int k= 0;k < i; k++){
	            	   String str= row.getColumnType(k+1)+"##"+row.getColumnName(k+1);
	                   
	                   switch (row.getColumnType(k+1)){
	                       case Types.BLOB:
	                    	   str += "##"+" ";
	                           break;
	                       case Types.FLOAT:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.NUMERIC:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.DOUBLE:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.DECIMAL:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.SMALLINT:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.BIGINT:
		                    	 str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
		                     case Types.INTEGER:
		                    	   str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?"0":row.getTrimString(k+1));
		                           break;
	                         default:
	                               str +="##"+(row.getTrimString(k+1)==null||row.getTrimString(k+1).trim().equals("")?" ":row.getTrimString(k+1));
	                   }   
	                   str2[k]=str;
	               }
	             str1[j++]=str2;	
		
	            }	
				return str1;		
			}
			catch(Exception e)
			{  
				trace = "<font color='red'>��ѯ����" + e.getMessage() + "</font>";
				log.error(trace);
			}
			finally{
				DataServiceTraceInfo.getInstance().getTrace(trace, DataServiceTraceInfo.TYPE.Common);
			}
			return null;
		}
		
		/**
		 * ͨ�ò�ѯ��Ľṹ
		 */
		@WebMethod
		@WebResult(partName = "StringArray")
		public String[][] find5(@WebParam(partName = "tablename") String tablename)
		{
			String sql =  "select * from "+tablename+ " where 1>2";
			String trace = "��ѯ"+sql;
			String[][] str1=null;
			String[] str2=null;

			try
			{
				List <Row> lst=DbMethod.makeDbSingleton().Open("Common_GETTABLE1", sql);	
				str1=new String[lst.size()][];
				for (int i= 0;i < lst.size();i++){
	            	 List  lis=(List)lst.get(i); 
	            	 str2=new String[4];
	            	 str2[0]=lis.get(0).toString();
	            	 str2[1]=lis.get(1).toString();
	            	 str2[2]=lis.get(2).toString();
	            	 str2[3]=lis.get(3).toString();
	            	 str1[i]=str2;
	            }  	
				return str1;		
			}
			catch(Exception e)
			{  
				trace = "<font color='red'>��ѯ����" + e.getMessage() + "</font>";
				log.error(trace);
			}
			finally{
				DataServiceTraceInfo.getInstance().getTrace(trace, DataServiceTraceInfo.TYPE.Common);
			}
			return null;
		}
		
	   /*ͨ��ִ��sql
	    */
		@WebMethod
		@WebResult(partName = "int")
		public int execute(@WebParam(partName = "sql")String sql) {
			String trace = "ִ��"+sql;	
			int i=1;
			try
			{
				DbMethod.makeDbSingleton().Execute("Common_SETTABLE", sql);		
			}
			catch(Exception e)
			{  
				trace = "<font color='red'>ִ�У�" + e.getMessage()+ "</font>";
		       log.error(trace);
		        return 0;
			}
			finally{
				DataServiceTraceInfo.getInstance().getTrace(trace, DataServiceTraceInfo.TYPE.Common);
			}
			return i;
		}
		
		/*ͨ��ִ��sql �������
		    */
			@WebMethod
			@WebResult(partName = "int")
			public int dataCount(@WebParam(partName = "sql")String sql) {
				String trace = "ִ��"+sql;	
				int i=0;
				try
				{
					List <Row> lst=DbMethod.makeDbSingleton().Open("Common_GETTABLE", sql);
					i=lst.size();
				}
				catch(Exception e)
				{  
					trace = "<font color='red'>ִ�У�" + e.getMessage()+ "</font>";
			       log.error(trace);
			        return 0;
				}
				finally{
					DataServiceTraceInfo.getInstance().getTrace(trace, DataServiceTraceInfo.TYPE.Common);
				}
				return i;
			}
			
		
		/*ͨ��ִ��sql ��newSeqId
		    */
			@WebMethod
			@WebResult(partName = "int")
			public long newSeqId(@WebParam(partName = "tablename")String tablename) {
				String trace = "ִ��"+tablename;	
				long m=0;
				try
				{
					String str = "SELECT F_SEQ FROM T_MK_SYS_SEQ WHERE F_TABLENAME='"+tablename.toUpperCase()+"'";
					List <Row> lst=DbMethod.makeDbSingleton().Open("Common_GETTABLE", str);	
			        if (lst.size()== 0)
			        {
			          String object1 = "0";
			          String str1 = "SELECT MAX(ID) id FROM " + tablename;
			          List <Row> lst1=DbMethod.makeDbSingleton().Open("Common_GETTABLE", str1);	
			          if (lst1.size() > 0)
			          {
			            Row object2 = lst1.get(0);
			            object1 = String.valueOf(object2.getDefInt("id"));
			            if(object1 == null)
			              object1 = "0"; 
			          }
			          String str2 ="INSERT INTO T_MK_SYS_SEQ (F_TABLENAME, F_SEQ) VALUES('"+ tablename.toUpperCase()+"',"+((String)object1)+")";
			          DbMethod.makeDbSingleton().Execute("Common_SETTABLE", str2);
			        }
			        String str3 = "UPDATE T_MK_SYS_SEQ SET F_SEQ = F_SEQ + 1 WHERE F_TABLENAME='" +tablename.toUpperCase()+ "'";
			        DbMethod.makeDbSingleton().Execute("Common_SETTABLE", str3);
			        String str4 = "SELECT F_SEQ FROM T_MK_SYS_SEQ WHERE F_TABLENAME ='"+tablename.toUpperCase() + "'";
			        List <Row> lst2=DbMethod.makeDbSingleton().Open("Common_GETTABLE", str4);
			        String str5="";
			        for(Row row : lst2){
			        	str5 = row.getTrimString("F_SEQ");
			        }
			        m = Long.parseLong(str5);;		
				}
				catch(Exception e)
				{  
					trace = "<font color='red'>ִ�У�" + e.getMessage()+ "</font>";
			       log.error(trace);
			        return 0;
				}
				finally{
					DataServiceTraceInfo.getInstance().getTrace(trace, DataServiceTraceInfo.TYPE.Common);
				}
				return m;
			}
			
}		
		
	
	
	
		 
	
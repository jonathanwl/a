package com.wasoft.calldb.business.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wasoft.calldb.BasicOperation;
import com.wasoft.calldb.CallDbException;
import com.wasoft.calldb.SqlParameterExt;
import com.wasoft.calldb.business.AbstractDbInterface;
import com.wasoft.calldb.sql.Row;
import com.wasoft.calldb.sql.value.BytesValue;
import com.wasoft.calldb.sql.value.StringValue;
import com.wasoft.dataserver.ws.report.bean.ReportXx;


public class ReportServiceImpl extends AbstractDbInterface{
	public ReportServiceImpl() {
		super();
	}

	public String toString() {
		return getClass().getName();
	}
	
	/**
     * ��������
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_ADD(Object obj) throws CallDbException {
		ReportXx pmt =(ReportXx) obj;
				//��ȡ����
				try {
					ResultSet rs = bo.query("select wasys3_reportid.NEXTVAL as id from dual");
					if (rs.next())
					{
						pmt.setId(rs.getString("id"));									   
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}       
				SqlParameterExt spx = null;			
				spx = new SqlParameterExt();
				spx.add(new StringValue(pmt.getId())); // p1					
				String sql = "insert into t_wa_sys_report(id) values(?)";
				bo.setSqlParameterExt(spx);
				try {
					bo.execute(sql);
					bo.clearParameters();
					executeREPORT_UPDATE(pmt);
					executeREPORT_RECOVER_BLOB(pmt);
				} catch (Exception err) {
					xlog.error("���ӱ����ɹ���" + err.getMessage());
					throw new CallDbException("���ӱ����ɹ���");
				}		
	}
	/**
     * �޸ı�����Ϣ
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_UPDATE(Object obj) throws CallDbException {
		ReportXx pmt =(ReportXx) obj;
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(pmt.getApp_key())); // p1 ϵͳkey
			spx.add(new StringValue(pmt.getGjdbm())); // p2  �鼯�����
			spx.add(new StringValue(pmt.getModule_key())); // p3  ģ��
			spx.add(new StringValue(pmt.getModule_name())); // p4  ģ��
			spx.add(new StringValue(pmt.getReport_title())); // p5  ����
			spx.add(new StringValue(pmt.getReport_filename()));// p6  �ļ���
			spx.add(new StringValue(pmt.getReport_document()));//p7	�ĵ�
			spx.add(new StringValue(pmt.getEditor()));//p8	�޸���
			spx.add(new StringValue(pmt.getId()));//p9  ID	
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			String sql = "update t_wa_sys_report set app_key=?,gjdbm=?,module_key=?,module_name=?," +
						 "report_title=?,report_filename=?,report_document=?,editor=?,editing_time=to_char(systimestamp,'yyyy-mm-dd hh24:mi:ss') " +
						 "where id=?";
			bo.execute(sql);
				executeREPORT_UPDATE_BLOB(obj);
		} catch (Exception e) {
			xlog.error("�޸ı����ɹ�:" + e.getMessage());
			throw new CallDbException("�޸ı����ɹ���");
		}    	
    }
	/**
     * ����BLOB����
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_UPDATE_BLOB(Object obj) throws CallDbException {
		ReportXx pmt =(ReportXx) obj;
        SqlParameterExt spx = null;
        String s = "";
        try
        {       
            spx = new SqlParameterExt();
            spx.add(new BytesValue(pmt.getReport_file()));
            spx.add(new StringValue(pmt.getId()));
        }
        catch (Exception err)
        {
            throw new CallDbException("�������Ϸ�!" + err.getMessage());
        }
        try
        {   
          s = "update t_wa_sys_report set report_file=? where id=?";      
          bo.clearParameters();
          bo.setSqlParameterExt(spx);
          bo.update(s);
        }
        catch (Exception e)
        {
          xlog.debug(e.getMessage());
          throw new CallDbException("����BLOB����ʧ�ܣ�");
        }
        finally
        {
        	bo.closestmt();
        }
	}
	/**
     * ����BLOB��ʼ����
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_RECOVER_BLOB(Object obj) throws CallDbException {
		ReportXx pmt =(ReportXx) obj;
        SqlParameterExt spx = null;
        String s = "";
        try
        {       
            spx = new SqlParameterExt();
            spx.add(new BytesValue(pmt.getReport_file()));
            spx.add(new StringValue(pmt.getId()));
        }
        catch (Exception err)
        {
            throw new CallDbException("�������Ϸ�!" + err.getMessage());
        }
        try
        {   
          s = "update t_wa_sys_report set report_file_recover=? where id=?";      
          bo.clearParameters();
          bo.setSqlParameterExt(spx);
          bo.update(s);
        }
        catch (Exception e)
        {
          xlog.debug(e.getMessage());
          throw new CallDbException("����BLOB��ʼ����ʧ�ܣ�");
        }
        finally
        {
        	bo.closestmt();
        }
	}
	/**
     * �༭������Ϣ-�ޱ����ļ��޸�
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_EDIT(Object obj) throws CallDbException {
		ReportXx pmt =(ReportXx) obj;
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(pmt.getReport_title())); // p1  ����
			spx.add(new StringValue(pmt.getReport_document()));//p2	�ĵ�
			spx.add(new StringValue(pmt.getEditor()));//p3	�޸���
			spx.add(new StringValue(pmt.getId()));//p4 ID	
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			String sql = "update t_wa_sys_report set report_title=?,report_document=?,editor=?,editing_time=to_char(systimestamp,'yyyy-mm-dd hh24:mi:ss') " +
						 "where id=?";
			bo.execute(sql);
		} catch (Exception e) {
			xlog.error("�༭�����ɹ�:" + e.getMessage());
			throw new CallDbException("�༭�����ɹ���");
		}    	
    }
	/**
     * ��ѯģ������
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public List <Row> openREPORT_GJD_CX(Object obj) throws CallDbException 
	{
		String str = "select bm,mc from bm_a075 order by bm ";
		return bo.queryToList(str);
	}
	/**
     * ��ѯ�鼯��
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public List <Row> openREPORT_MENU_CX(Object obj) throws CallDbException 
	{
		ReportXx pmt =(ReportXx) obj;
		String app_key = (pmt.getApp_key()==null || "".equals(pmt.getApp_key()))? "%" :pmt.getApp_key().trim();
		String str = "select f_name,f_caption  from t_mk_sys_menu where f_applicationkey like '"+app_key;
		if("unitapp".equals(app_key.trim())){
			str+="' and f_level = '1'";
		}else{
			str+="' and f_level = '1' ";
		}
		return bo.queryToList(str);
	}
	/**
     * Ӧ�ñ�ʶ����
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public List <Row> openREPORT_APP_CX(Object obj) throws CallDbException 
	{
		ReportXx pmt =(ReportXx) obj;
		String app_key = (pmt.getApp_key()==null || "".equals(pmt.getApp_key()))? "%" :pmt.getApp_key().trim();
		String str = "select f_applicationkey,f_name,f_caption  from t_mk_sys_menu where f_applicationkey like '"+app_key+"' and f_level = '1' ";
		return bo.queryToList(str);
	}
	/**
     * ��ȡ����
     * @param obj ���� ID
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_GETREPORT(Object obj) throws CallDbException 
	{
		ReportXx pmt =(ReportXx) obj;
		String str="";
		if(pmt.getId()!= null && "".equals(pmt.getId()) == false){
			str = "select * from t_wa_sys_report where id='"+pmt.getId().trim()+"'";
		}
		ResultSet rs = bo.query(str);
        byte[] b = null;
        try {
			if (rs.next())
			{
				pmt.setId(rs.getString("id"));
				pmt.setApp_key(rs.getString("app_key"));
				pmt.setGjdbm(rs.getString("gjdbm"));
				pmt.setModule_key(rs.getString("module_key"));
				pmt.setModule_name(rs.getString("module_name"));
				pmt.setReport_title(rs.getString("report_title"));
				pmt.setReport_filename(rs.getString("report_filename"));
				pmt.setReport_document(rs.getString("report_document"));
				pmt.setEditor(rs.getString("editor"));
				pmt.setEditing_time(rs.getString("editing_time"));
			    if (databaseType == BasicOperation.DB_TYPE_ORACLE)
			    {
			      java.sql.Blob blob = rs.getBlob("report_file");
			      b = blob.getBytes(1, (int)blob.length()); //��BLObȡ���ֽ�������
			    }
			    else
			    {
			      b = rs.getBytes("report_file");
			    }
			    pmt.setReport_file(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	/**
     * ��ȡ����
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_GETREPORT_PREVIEW(Object obj) throws CallDbException 
	{
		ReportXx pmt =(ReportXx) obj;
		SqlParameterExt spx = null;
		String str="";
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue((pmt.getApp_key()== null || "".equals(pmt.getApp_key())) ? "" : pmt.getApp_key().trim())); // p1  Ӧ�ñ�ʶ
			spx.add(new StringValue((pmt.getModule_key()== null ||"".equals(pmt.getModule_key())) ? "" : pmt.getModule_key().trim()));//p2	ģ���ʶ
			spx.add(new StringValue((pmt.getReport_filename()== null ||"".equals(pmt.getReport_filename())) ? "" : pmt.getReport_filename().trim()));//p3	������
			spx.add(new StringValue((pmt.getGjdbm()== null ||"".equals(pmt.getGjdbm())) ? "" : pmt.getGjdbm().trim()));//p4	�鼯�����
		} catch (Exception err) {
			throw new CallDbException("�������Ϸ�!" + err.getMessage());
		}
		    bo.setSqlParameterExt(spx);
			str = "select * from t_wa_sys_report where app_key like ? and module_key like ? and report_filename like ? and gjdbm like ? ";
		    ResultSet rs = bo.query(str);
        byte[] b = null;
        try {
			if (rs.next())
			{
				pmt.setApp_key(rs.getString("app_key"));
				pmt.setGjdbm(rs.getString("gjdbm"));
				pmt.setModule_key(rs.getString("module_key"));
				pmt.setReport_filename(rs.getString("report_filename"));
			    if (databaseType == BasicOperation.DB_TYPE_ORACLE)
			    {
			      java.sql.Blob blob = rs.getBlob("report_file");
			      b = blob.getBytes(1, (int)blob.length()); //��BLObȡ���ֽ�������
			    }
			    else
			    {
			      b = rs.getBytes("report_file");
			    }
			    pmt.setReport_file(b);
			}else{
				throw new CallDbException("δȡ�ñ����ļ���");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	/**
     * ��ȡӦ���±���ID
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public List<Row> openREPORT_ID(Object obj) throws CallDbException 
	{
		ReportXx pmt =(ReportXx) obj;
		String app_key =  ((pmt.getApp_key()==null ||"".equals(pmt.getApp_key())) ? "%" : pmt.getApp_key());
		String str = "select id from t_wa_sys_report where app_key like '"+app_key+"' order by id";   	
		return bo.queryToList(str);
	}
	/**
     * ��ձ���
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_CLEAR(Object obj) throws CallDbException 
	{
		String pmt =(String) obj;
		String app_key =  ((pmt==null || "".equals(pmt)) ? "%" : pmt);
		String sql = "delete from t_wa_sys_report where app_key like '"+app_key+"'";
		try {
			bo.execute(sql);
		} catch (Exception err) {
			xlog.error("��������ɹ���" + err.getMessage());
			throw new CallDbException("��������ɹ���");
		}
	}
	/**
     * ��ȡӦ���±���
     * @param obj ���� 
     * @return
     * @throws CallDbException
     */
	public List<Row> openREPORT_APPKEY(Object obj) throws CallDbException 
	{
		ReportXx pmt =(ReportXx) obj;
		List <ReportXx> ls = null;
		String app_key =  ((pmt.getApp_key()==null ||"".equals(pmt.getApp_key())) ? "%" : pmt.getApp_key());
		String str = "select * from t_wa_sys_report where app_key like '"+app_key+"' order by id";   	
		 return bo.queryToList(str);
       

	}
}

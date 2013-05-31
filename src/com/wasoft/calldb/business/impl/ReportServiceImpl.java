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
     * 创建报表
     * @param obj 参数 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_ADD(Object obj) throws CallDbException {
		ReportXx pmt =(ReportXx) obj;
				//获取序列
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
					xlog.error("增加报表不成功：" + err.getMessage());
					throw new CallDbException("增加报表不成功！");
				}		
	}
	/**
     * 修改报表信息
     * @param obj 参数 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_UPDATE(Object obj) throws CallDbException {
		ReportXx pmt =(ReportXx) obj;
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(pmt.getApp_key())); // p1 系统key
			spx.add(new StringValue(pmt.getGjdbm())); // p2  归集点编码
			spx.add(new StringValue(pmt.getModule_key())); // p3  模块
			spx.add(new StringValue(pmt.getModule_name())); // p4  模块
			spx.add(new StringValue(pmt.getReport_title())); // p5  标题
			spx.add(new StringValue(pmt.getReport_filename()));// p6  文件名
			spx.add(new StringValue(pmt.getReport_document()));//p7	文档
			spx.add(new StringValue(pmt.getEditor()));//p8	修改人
			spx.add(new StringValue(pmt.getId()));//p9  ID	
		} catch (Exception err) {
			throw new CallDbException("参数不合法!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			String sql = "update t_wa_sys_report set app_key=?,gjdbm=?,module_key=?,module_name=?," +
						 "report_title=?,report_filename=?,report_document=?,editor=?,editing_time=to_char(systimestamp,'yyyy-mm-dd hh24:mi:ss') " +
						 "where id=?";
			bo.execute(sql);
				executeREPORT_UPDATE_BLOB(obj);
		} catch (Exception e) {
			xlog.error("修改报表不成功:" + e.getMessage());
			throw new CallDbException("修改报表不成功！");
		}    	
    }
	/**
     * 更新BLOB数据
     * @param obj 参数 
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
            throw new CallDbException("参数不合法!" + err.getMessage());
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
          throw new CallDbException("更新BLOB数据失败！");
        }
        finally
        {
        	bo.closestmt();
        }
	}
	/**
     * 备份BLOB初始数据
     * @param obj 参数 
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
            throw new CallDbException("参数不合法!" + err.getMessage());
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
          throw new CallDbException("更新BLOB初始数据失败！");
        }
        finally
        {
        	bo.closestmt();
        }
	}
	/**
     * 编辑报表信息-无报表文件修改
     * @param obj 参数 
     * @return
     * @throws CallDbException
     */
	public void executeREPORT_EDIT(Object obj) throws CallDbException {
		ReportXx pmt =(ReportXx) obj;
		SqlParameterExt spx = null;
		try {
			spx = new SqlParameterExt();
			spx.add(new StringValue(pmt.getReport_title())); // p1  标题
			spx.add(new StringValue(pmt.getReport_document()));//p2	文档
			spx.add(new StringValue(pmt.getEditor()));//p3	修改人
			spx.add(new StringValue(pmt.getId()));//p4 ID	
		} catch (Exception err) {
			throw new CallDbException("参数不合法!" + err.getMessage());
		}
		try {
			bo.setSqlParameterExt(spx);
			String sql = "update t_wa_sys_report set report_title=?,report_document=?,editor=?,editing_time=to_char(systimestamp,'yyyy-mm-dd hh24:mi:ss') " +
						 "where id=?";
			bo.execute(sql);
		} catch (Exception e) {
			xlog.error("编辑报表不成功:" + e.getMessage());
			throw new CallDbException("编辑报表不成功！");
		}    	
    }
	/**
     * 查询模块名称
     * @param obj 参数 
     * @return
     * @throws CallDbException
     */
	public List <Row> openREPORT_GJD_CX(Object obj) throws CallDbException 
	{
		String str = "select bm,mc from bm_a075 order by bm ";
		return bo.queryToList(str);
	}
	/**
     * 查询归集点
     * @param obj 参数 
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
     * 应用标识名称
     * @param obj 参数 
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
     * 获取报表
     * @param obj 参数 ID
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
			      b = blob.getBytes(1, (int)blob.length()); //从BLOb取出字节流数据
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
     * 获取报表
     * @param obj 参数 
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
			spx.add(new StringValue((pmt.getApp_key()== null || "".equals(pmt.getApp_key())) ? "" : pmt.getApp_key().trim())); // p1  应用标识
			spx.add(new StringValue((pmt.getModule_key()== null ||"".equals(pmt.getModule_key())) ? "" : pmt.getModule_key().trim()));//p2	模块标识
			spx.add(new StringValue((pmt.getReport_filename()== null ||"".equals(pmt.getReport_filename())) ? "" : pmt.getReport_filename().trim()));//p3	报表名
			spx.add(new StringValue((pmt.getGjdbm()== null ||"".equals(pmt.getGjdbm())) ? "" : pmt.getGjdbm().trim()));//p4	归集点编码
		} catch (Exception err) {
			throw new CallDbException("参数不合法!" + err.getMessage());
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
			      b = blob.getBytes(1, (int)blob.length()); //从BLOb取出字节流数据
			    }
			    else
			    {
			      b = rs.getBytes("report_file");
			    }
			    pmt.setReport_file(b);
			}else{
				throw new CallDbException("未取得报表文件！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	/**
     * 获取应用下报表ID
     * @param obj 参数 
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
     * 清空报表
     * @param obj 参数 
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
			xlog.error("清除报表不成功：" + err.getMessage());
			throw new CallDbException("清除报表不成功！");
		}
	}
	/**
     * 获取应用下报表
     * @param obj 参数 
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

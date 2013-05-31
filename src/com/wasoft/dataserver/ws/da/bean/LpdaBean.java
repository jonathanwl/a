package com.wasoft.dataserver.ws.da.bean;

/**
 * 电子档案系统中使用的bean,对应数据库中的ef_lpda表,其中xmbh字段是主键
 * 
 * @author YANG 2010-8-3
 */

public class LpdaBean extends DaBean {

	public LpdaBean() {
	}

	private String xmbh;// 项目编号 xmbh Varchar 30 业务库 关键字

	private String xm;// 项目名称 xm Varchar 60 业务库

	private int id; // id long 10
	
	private String type;
	
	private String typeb;
	
	private String jsrq_begin;
	
	private String jsrq_end;
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeb() {
		return typeb;
	}

	public void setTypeb(String typeb) {
		this.typeb = typeb;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXmbh() {
		return xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	public String getJsrq_begin() {
		return jsrq_begin;
	}

	public void setJsrq_begin(String jsrq_begin) {
		this.jsrq_begin = jsrq_begin;
	}

	public String getJsrq_end() {
		return jsrq_end;
	}

	public void setJsrq_end(String jsrq_end) {
		this.jsrq_end = jsrq_end;
	}

}

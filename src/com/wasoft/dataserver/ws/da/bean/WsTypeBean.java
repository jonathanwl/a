package com.wasoft.dataserver.ws.da.bean;

public class WsTypeBean extends DaBean {
	
	private String tblName;

	private long id;

	private String pid;
	
	private String typeid;
	
	private String mc;

	public String getTblName() {
		return tblName;
	}
	
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}
}

package com.wasoft.dataserver.ws.controlcenter.bean;

public class SystemYearlyDTO {
//	private java.util.Date begindate;
//
//	private java.util.Date zwdate;
//
//	private java.util.Date enddate;
//
//	private boolean zwclgn;
//
//	private boolean jzbhsfsr;
//
//	private String ver;
//
//	private String captions;
//
//	private short dzzhcd;
//
//	private short ckzhcd;
//
//	private short yhzhcd;
//
//	private short dwbmcd;
//
//	private short zgzhcd;
//
//	private short htbhcd;
//
//	private short dwbmtype;
//
//	private short htbhtype;
//
//	private short bttype;

	private String begindate;

	private String zwdate;

	private String enddate;

	private String zwclgn;

	private String jzbhsfsr;

	private String ver;

	private String captions;

	private String dzzhcd;

	private String ckzhcd;

	private String yhzhcd;

	private String dwbmcd;

	private String zgzhcd;

	private String htbhcd;

	private String dwbmtype;

	private String htbhtype;

	private String bttype;

	
	
	public SystemYearlyDTO() {
		super();
	}

	public SystemYearlyDTO(String begindate, String zwdate, String enddate, String zwclgn, String jzbhsfsr, String ver, String captions, String dzzhcd, String ckzhcd, String yhzhcd, String dwbmcd, String zgzhcd, String htbhcd, String dwbmtype, String htbhtype, String bttype) {
		super();
		this.begindate = begindate;
		this.zwdate = zwdate;
		this.enddate = enddate;
		this.zwclgn = zwclgn;
		this.jzbhsfsr = jzbhsfsr;
		this.ver = ver;
		this.captions = captions;
		this.dzzhcd = dzzhcd;
		this.ckzhcd = ckzhcd;
		this.yhzhcd = yhzhcd;
		this.dwbmcd = dwbmcd;
		this.zgzhcd = zgzhcd;
		this.htbhcd = htbhcd;
		this.dwbmtype = dwbmtype;
		this.htbhtype = htbhtype;
		this.bttype = bttype;
	}

	public String getBegindate() {
		return begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public String getBttype() {
		return bttype;
	}

	public void setBttype(String bttype) {
		this.bttype = bttype;
	}

	public String getCaptions() {
		return captions;
	}

	public void setCaptions(String captions) {
		this.captions = captions;
	}

	public String getCkzhcd() {
		return ckzhcd;
	}

	public void setCkzhcd(String ckzhcd) {
		this.ckzhcd = ckzhcd;
	}

	public String getDwbmcd() {
		return dwbmcd;
	}

	public void setDwbmcd(String dwbmcd) {
		this.dwbmcd = dwbmcd;
	}

	public String getDwbmtype() {
		return dwbmtype;
	}

	public void setDwbmtype(String dwbmtype) {
		this.dwbmtype = dwbmtype;
	}

	public String getDzzhcd() {
		return dzzhcd;
	}

	public void setDzzhcd(String dzzhcd) {
		this.dzzhcd = dzzhcd;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getHtbhcd() {
		return htbhcd;
	}

	public void setHtbhcd(String htbhcd) {
		this.htbhcd = htbhcd;
	}

	public String getHtbhtype() {
		return htbhtype;
	}

	public void setHtbhtype(String htbhtype) {
		this.htbhtype = htbhtype;
	}

	public String getJzbhsfsr() {
		return jzbhsfsr;
	}

	public void setJzbhsfsr(String jzbhsfsr) {
		this.jzbhsfsr = jzbhsfsr;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getYhzhcd() {
		return yhzhcd;
	}

	public void setYhzhcd(String yhzhcd) {
		this.yhzhcd = yhzhcd;
	}

	public String getZgzhcd() {
		return zgzhcd;
	}

	public void setZgzhcd(String zgzhcd) {
		this.zgzhcd = zgzhcd;
	}

	public String getZwclgn() {
		return zwclgn;
	}

	public void setZwclgn(String zwclgn) {
		this.zwclgn = zwclgn;
	}

	public String getZwdate() {
		return zwdate;
	}

	public void setZwdate(String zwdate) {
		this.zwdate = zwdate;
	}
	
	
}

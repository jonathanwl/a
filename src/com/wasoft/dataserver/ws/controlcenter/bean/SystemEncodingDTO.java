package com.wasoft.dataserver.ws.controlcenter.bean;

public class SystemEncodingDTO {
	private String bm;

	private String mc;// 名称

	private String fwmj;// 房屋建筑面积

	private String cs1;// 小于房屋建筑面积的允许贷款成数

	private String cs2;// 大于房屋建筑面积的允许贷款成数

	private String sf;

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getCs1() {
		return cs1;
	}

	public void setCs1(String cs1) {
		this.cs1 = cs1;
	}

	public String getCs2() {
		return cs2;
	}

	public void setCs2(String cs2) {
		this.cs2 = cs2;
	}

	public String getFwmj() {
		return fwmj;
	}

	public void setFwmj(String fwmj) {
		this.fwmj = fwmj;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSf() {
		return sf;
	}

	public void setSf(String sf) {
		this.sf = sf;
	}

}

package com.wasoft.dataserver.ws.controlcenter.bean;

/**
 * @author Administrator
 * 
 */
public class HandFormulationDTO {

	private String bm;

	private String mc;

	private String jsgs;// 月还金额计算公式

	private String sfqy;// 还贷频率（多少个月一次）

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getJsgs() {
		return jsgs;
	}

	public void setJsgs(String jsgs) {
		this.jsgs = jsgs;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

}

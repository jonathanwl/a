package com.wasoft.dataserver.ws.controlcenter.bean;

public class EntrustPayBank {
	private String bm;//编码
	private String mc;//名称
	private String fr;//法人
	private String dz;//地址
	private String yb;//邮编
	private String tel;//电话
	private String gjdbm;//所属归集点
	
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	public String getFr() {
		return fr;
	}
	public void setFr(String fr) {
		this.fr = fr;
	}
	public String getGjdbm() {
		return gjdbm;
	}
	public void setGjdbm(String gjdbm) {
		this.gjdbm = gjdbm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getYb() {
		return yb;
	}
	public void setYb(String yb) {
		this.yb = yb;
	}
	
}

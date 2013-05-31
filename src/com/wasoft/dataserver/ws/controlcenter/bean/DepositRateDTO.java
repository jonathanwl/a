package com.wasoft.dataserver.ws.controlcenter.bean;

public class DepositRateDTO {
	private java.util.Date date1;//开始执行日期 
	private double lbbm ;//类别编码
	private double lbmc1  ;//类别名称1    
	private double sf     ;//有否支取
	private double lbmc2 ;//类别名称2 
	private double nll ;//年利率  
	public java.util.Date getDate1() {
		return date1;
	}
	public void setDate1(java.util.Date date1) {
		this.date1 = date1;
	}
	public double getLbbm() {
		return lbbm;
	}
	public void setLbbm(double lbbm) {
		this.lbbm = lbbm;
	}
	public double getLbmc1() {
		return lbmc1;
	}
	public void setLbmc1(double lbmc1) {
		this.lbmc1 = lbmc1;
	}
	public double getLbmc2() {
		return lbmc2;
	}
	public void setLbmc2(double lbmc2) {
		this.lbmc2 = lbmc2;
	}
	public double getNll() {
		return nll;
	}
	public void setNll(double nll) {
		this.nll = nll;
	}
	public double getSf() {
		return sf;
	}
	public void setSf(double sf) {
		this.sf = sf;
	}
	
}

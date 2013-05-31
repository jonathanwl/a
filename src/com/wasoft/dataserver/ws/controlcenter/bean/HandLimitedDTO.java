package com.wasoft.dataserver.ws.controlcenter.bean;

public class HandLimitedDTO {
    private String  bm ;       // 编码
    private String  mc ;       // 名称
    private double  min_yjgz ; // 月均工资下限
    private double  max_yjgz ; // 月均工资上限
    private double  min_ycje ; // 月存金额下限
    private double  max_ycje ; // 月存金额上限
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public double getMax_ycje() {
		return max_ycje;
	}
	public void setMax_ycje(double max_ycje) {
		this.max_ycje = max_ycje;
	}
	public double getMax_yjgz() {
		return max_yjgz;
	}
	public void setMax_yjgz(double max_yjgz) {
		this.max_yjgz = max_yjgz;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public double getMin_ycje() {
		return min_ycje;
	}
	public void setMin_ycje(double min_ycje) {
		this.min_ycje = min_ycje;
	}
	public double getMin_yjgz() {
		return min_yjgz;
	}
	public void setMin_yjgz(double min_yjgz) {
		this.min_yjgz = min_yjgz;
	}
    
}

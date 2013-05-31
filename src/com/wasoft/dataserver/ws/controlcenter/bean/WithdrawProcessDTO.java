package com.wasoft.dataserver.ws.controlcenter.bean;

public class WithdrawProcessDTO {
    private String bm;    // 编码
    private double dydy;  // 提取金额大于等于
    private double xy;    // 提取金额小于
    private String spjb;  // 审批级别编码
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public double getDydy() {
		return dydy;
	}
	public void setDydy(double dydy) {
		this.dydy = dydy;
	}
	public String getSpjb() {
		return spjb;
	}
	public void setSpjb(String spjb) {
		this.spjb = spjb;
	}
	public double getXy() {
		return xy;
	}
	public void setXy(double xy) {
		this.xy = xy;
	}
    
}

package com.wasoft.dataserver.ws.controlcenter.bean;

public class WithdrawProcessDTO {
    private String bm;    // ����
    private double dydy;  // ��ȡ�����ڵ���
    private double xy;    // ��ȡ���С��
    private String spjb;  // �����������
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

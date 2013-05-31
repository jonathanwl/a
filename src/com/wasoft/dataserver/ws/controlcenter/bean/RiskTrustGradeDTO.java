package com.wasoft.dataserver.ws.controlcenter.bean;

public class RiskTrustGradeDTO {

	private String bm;

	private String mc;

	// private double minfsz;//评估分数低限
	//
	// private double maxfsz;//评估分数高限
	//
	// private double dkjebl;//允许贷款金额比例

	private String minfsz;// 评估分数低限

	private String maxfsz;// 评估分数高限

	private String dkjebl;// 允许贷款金额比例

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getDkjebl() {
		return dkjebl;
	}

	public void setDkjebl(String dkjebl) {
		this.dkjebl = dkjebl;
	}

	public String getMaxfsz() {
		return maxfsz;
	}

	public void setMaxfsz(String maxfsz) {
		this.maxfsz = maxfsz;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getMinfsz() {
		return minfsz;
	}

	public void setMinfsz(String minfsz) {
		this.minfsz = minfsz;
	}

}

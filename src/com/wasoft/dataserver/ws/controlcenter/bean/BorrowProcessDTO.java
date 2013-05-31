package com.wasoft.dataserver.ws.controlcenter.bean;

/*yzz
 */
public class BorrowProcessDTO {
	private String bm = "";

	private String spjbbm;// 审批级别编码

	private double min_dkje;// 贷款金额大于等于

	private double max_dkje;// 贷款金额小于等于

	private String gjdbm = "";

	private double max_dknx;// 借款年限小于

	private double min_dknx;// 贷款年限大于

	private String dbfs = "";

	private String fwxz = "";

	private String cwVer = "";

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getCwVer() {
		return cwVer;
	}

	public void setCwVer(String cwVer) {
		this.cwVer = cwVer;
	}

	public String getDbfs() {
		return dbfs;
	}

	public void setDbfs(String dbfs) {
		this.dbfs = dbfs;
	}

	public String getFwxz() {
		return fwxz;
	}

	public void setFwxz(String fwxz) {
		this.fwxz = fwxz;
	}

	public String getGjdbm() {
		return gjdbm;
	}

	public void setGjdbm(String gjdbm) {
		this.gjdbm = gjdbm;
	}

	public double getMax_dkje() {
		return max_dkje;
	}

	public void setMax_dkje(double max_dkje) {
		this.max_dkje = max_dkje;
	}

	public double getMax_dknx() {
		return max_dknx;
	}

	public void setMax_dknx(double max_dknx) {
		this.max_dknx = max_dknx;
	}

	public double getMin_dkje() {
		return min_dkje;
	}

	public void setMin_dkje(double min_dkje) {
		this.min_dkje = min_dkje;
	}

	public double getMin_dknx() {
		return min_dknx;
	}

	public void setMin_dknx(double min_dknx) {
		this.min_dknx = min_dknx;
	}

	public String getSpjbbm() {
		return spjbbm;
	}

	public void setSpjbbm(String spjbbm) {
		this.spjbbm = spjbbm;
	}

}

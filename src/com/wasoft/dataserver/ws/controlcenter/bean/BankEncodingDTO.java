package com.wasoft.dataserver.ws.controlcenter.bean;


public class BankEncodingDTO {
	private String bm;         // 单位编码
    private String mc;         // 单位名称
    private String gjdbm;      // 归集点编码
    private String jgbm;       // 银行机构编码
    private String txip;       // 银行通讯机ip地址
    private String txdkh;      // 银行通讯机通讯端口号
   
    
    
	public BankEncodingDTO(String bm, String mc, String gjdbm, String jgbm, String txip, String txdkh) {
		super();
		this.bm = bm;
		this.mc = mc;
		this.gjdbm = gjdbm;
		this.jgbm = jgbm;
		this.txip = txip;
		this.txdkh = txdkh;
	}
	
	
	public BankEncodingDTO(String bm) {
		super();
		this.bm = bm;
	}


	public BankEncodingDTO() {
		// TODO Auto-generated constructor stub
	}


	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getGjdbm() {
		return gjdbm;
	}
	public void setGjdbm(String gjdbm) {
		this.gjdbm = gjdbm;
	}
	public String getJgbm() {
		return jgbm;
	}
	public void setJgbm(String jgbm) {
		this.jgbm = jgbm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	
	public String getTxdkh() {
		return txdkh;
	}
	public void setTxdkh(String txdkh) {
		this.txdkh = txdkh;
	}
	public String getTxip() {
		return txip;
	}
	public void setTxip(String txip) {
		this.txip = txip;
	}
    
    
}

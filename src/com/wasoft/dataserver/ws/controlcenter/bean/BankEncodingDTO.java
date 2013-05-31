package com.wasoft.dataserver.ws.controlcenter.bean;


public class BankEncodingDTO {
	private String bm;         // ��λ����
    private String mc;         // ��λ����
    private String gjdbm;      // �鼯�����
    private String jgbm;       // ���л�������
    private String txip;       // ����ͨѶ��ip��ַ
    private String txdkh;      // ����ͨѶ��ͨѶ�˿ں�
   
    
    
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

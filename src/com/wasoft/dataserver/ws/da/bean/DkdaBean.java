package com.wasoft.dataserver.ws.da.bean;

/**
 * ���ӵ���ϵͳ��ʹ�õ�bean,��Ӧ���ݿ��е�ef_dkda��,����id�ֶ�������
 * 
 * @author YANG 2010-9-19
 */

public class DkdaBean extends DaBean {

	public DkdaBean() {
	}

	private Long id; // ID Long 10 �Զ����� ����

	private String dah; // ������ dah Varchar 20 �Զ�����

	private String ygdh; // Ԥ�鵵�� ygdh Varchar 20 �Զ����� ���

	private String cbwdbm; // �а�������� cbwdbm Varchar 6 ҵ���

	private String cbwd; // �а��������� cbwd Varchar 60 ҵ���

	private String gjdbm; // �鼯����� gjdbm Varchar 4 ҵ���

	private String gjd;// �鼯������ gjd Varchar 60 ҵ���

	private String dkhtbh; // �����ͬ��� dkhtbh Varchar 20 ҵ���

	private String zgzh; // ְ���˺� zgzh Varchar 30 ҵ���

	private String zgxm; // ְ������ zgxm Varchar 60 ҵ���

	private String dwzh; // ��λ�˺� dwzh Varchar 20 ҵ���

	private String dw; // ��λ���� dw Varchar 100 ҵ���

	private String zjhm; // ֤������ zjhm Varchar 40 ҵ���

	private String xb; // �Ա� xb Varchar 2 ҵ���

	private String dkhtrq; // �����ͬ���� dkhtrq Date 8 ҵ���

	private String fkrq; // �ſ����� fkrq Date 8 ҵ���

	private String fkyh; // �ſ����� fkyh Varchar 60 ҵ���

	private String gflx; // �������� gflx Varchar 60 ҵ���

	private String gfxxdz; // ������ϸ��ַ gfxxdz Varchar 120 ҵ���

	private int dknx; // �������� dknx Int 2 ҵ���
	
	private String type;
	
	private String typeb;
	
	private String tjrq_begin;
	
	private String tjrq_end;
	
	private String ph;
	
	private String sxh;
	
	
	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getSxh() {
		return sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
	}

	public String getTjrq_begin() {
		return tjrq_begin;
	}

	public void setTjrq_begin(String tjrqBegin) {
		tjrq_begin = tjrqBegin;
	}

	public String getTjrq_end() {
		return tjrq_end;
	}

	public void setTjrq_end(String tjrqEnd) {
		tjrq_end = tjrqEnd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeb() {
		return typeb;
	}

	public void setTypeb(String typeb) {
		this.typeb = typeb;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}

	public String getYgdh() {
		return ygdh;
	}

	public void setYgdh(String ygdh) {
		this.ygdh = ygdh;
	}

	public String getCbwdbm() {
		return cbwdbm;
	}

	public void setCbwdbm(String cbwdbm) {
		this.cbwdbm = cbwdbm;
	}

	public String getCbwd() {
		return cbwd;
	}

	public void setCbwd(String cbwd) {
		this.cbwd = cbwd;
	}

	public String getGjdbm() {
		return gjdbm;
	}

	public void setGjdbm(String gjdbm) {
		this.gjdbm = gjdbm;
	}

	public String getGjd() {
		return gjd;
	}

	public void setGjd(String gjd) {
		this.gjd = gjd;
	}

	public String getDkhtbh() {
		return dkhtbh;
	}

	public void setDkhtbh(String dkhtbh) {
		this.dkhtbh = dkhtbh;
	}

	public String getZgzh() {
		return zgzh;
	}

	public void setZgzh(String zgzh) {
		this.zgzh = zgzh;
	}

	public String getZgxm() {
		return zgxm;
	}

	public void setZgxm(String zgxm) {
		this.zgxm = zgxm;
	}

	public String getDwzh() {
		return dwzh;
	}

	public void setDwzh(String dwzh) {
		this.dwzh = dwzh;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getDkhtrq() {
		return dkhtrq;
	}

	public void setDkhtrq(String dkhtrq) {
		this.dkhtrq = dkhtrq;
	}

	public String getFkrq() {
		return fkrq;
	}

	public void setFkrq(String fkrq) {
		this.fkrq = fkrq;
	}

	public String getFkyh() {
		return fkyh;
	}

	public void setFkyh(String fkyh) {
		this.fkyh = fkyh;
	}

	public String getGflx() {
		return gflx;
	}

	public void setGflx(String gflx) {
		this.gflx = gflx;
	}

	public String getGfxxdz() {
		return gfxxdz;
	}

	public void setGfxxdz(String gfxxdz) {
		this.gfxxdz = gfxxdz;
	}

	public int getDknx() {
		return dknx;
	}

	public void setDknx(int dknx) {
		this.dknx = dknx;
	}

}

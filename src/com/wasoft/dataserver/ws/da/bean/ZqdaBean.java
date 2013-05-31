package com.wasoft.dataserver.ws.da.bean;

/**
 * ���ӵ���ϵͳ��ʹ�õ�bean,��Ӧ���ݿ��е�ef_zqda��,����dah�ֶ�������
 * 
 * @author DING 2010-7-30
 */
public class ZqdaBean extends DaBean {

	private int id; // id long 10

	private String zgzh; // ְ���˺� zgzh Varchar 30 ҵ��� �ؼ���

	private String tqlsh; // ��ȡ��ˮ�� tqlsh varchar 20 ҵ��� �ؼ���

	private String zgxm; // ְ������ zgxm Varchar 60 ҵ���

	private String dwzh;// ��λ�˺� dwzh Varchar 20 ҵ���

	private String dw; // ��λ���� dw Varchar 100 ҵ���

	private String zjhm;// ֤������ zjhm Varchar 40 ҵ���

	private String cbwdbm;// �а�������� cbwdbm Varchar 6 ҵ���

	private String cbwd;// �а��������� cbwd Varchar 60 ҵ���

	private String gjdbm;// �鼯����� gjdbm Varchar 4 ҵ���

	private String gjd;// �鼯������ gjd Varchar 60 ҵ���

	private String sczqrq;// �ϴ�֧ȡ���� sczqrq Date 8 ҵ���

	private String zqrq;// �ϴ�֧ȡ���� zqrq Date 8 ҵ���

	private String zqyt;// ֧ȡ��; zqyt varchar 60 ҵ���

	private String dah;// ������ dah Varchar 20

	private String rzczy;// ���˲���Ա rzczy Varchar 20

	private String djbh;// ���ݱ�� djbh Varchar 20

	private String fhczy;// ҵ�񸴺˲���Ա fhczy Varchar 20

	private String sprq;// �������� sprq Date 8 ҵ���
	
	private String tjrq_begin;
	
	private String tjrq_end;
	
	private String zqrq_begin;
	
	private String zqrq_end;

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

	public String getZqrq_begin() {
		return zqrq_begin;
	}

	public void setZqrq_begin(String zqrqBegin) {
		zqrq_begin = zqrqBegin;
	}

	public String getZqrq_end() {
		return zqrq_end;
	}

	public void setZqrq_end(String zqrqEnd) {
		zqrq_end = zqrqEnd;
	}

	public ZqdaBean() {

	}

	public ZqdaBean(String zgzh, String zgxm, String zjhm, String zqrq) {
		super();
		this.zgzh = zgzh;
		this.zgxm = zgxm;
		this.zjhm = zjhm;
		this.zqrq = zqrq;
	}

	public String getCbwd() {
		return cbwd;
	}

	public void setCbwd(String cbwd) {
		this.cbwd = cbwd;
	}

	public String getCbwdbm() {
		return cbwdbm;
	}

	public void setCbwdbm(String cbwdbm) {
		this.cbwdbm = cbwdbm;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public String getDwzh() {
		return dwzh;
	}

	public void setDwzh(String dwzh) {
		this.dwzh = dwzh;
	}

	public String getGjd() {
		return gjd;
	}

	public void setGjd(String gjd) {
		this.gjd = gjd;
	}

	public String getGjdbm() {
		return gjdbm;
	}

	public void setGjdbm(String gjdbm) {
		this.gjdbm = gjdbm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSczqrq() {
		return sczqrq;
	}

	public void setSczqrq(String sczqrq) {
		this.sczqrq = sczqrq;
	}

	public String getTqlsh() {
		return tqlsh;
	}

	public void setTqlsh(String tqlsh) {
		this.tqlsh = tqlsh;
	}

	public String getZgxm() {
		return zgxm;
	}

	public void setZgxm(String zgxm) {
		this.zgxm = zgxm;
	}

	public String getZgzh() {
		return zgzh;
	}

	public void setZgzh(String zgzh) {
		this.zgzh = zgzh;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getZqrq() {
		return zqrq;
	}

	public void setZqrq(String zqrq) {
		this.zqrq = zqrq;
	}

	public String getZqyt() {
		return zqyt;
	}

	public void setZqyt(String zqyt) {
		this.zqyt = zqyt;
	}

	public String getDjbh() {
		return djbh;
	}

	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}

	public String getFhczy() {
		return fhczy;
	}

	public void setFhczy(String fhczy) {
		this.fhczy = fhczy;
	}

	public String getRzczy() {
		return rzczy;
	}

	public void setRzczy(String rzczy) {
		this.rzczy = rzczy;
	}

	public String getSprq() {
		return sprq;
	}

	public void setSprq(String sprq) {
		this.sprq = sprq;
	}

}

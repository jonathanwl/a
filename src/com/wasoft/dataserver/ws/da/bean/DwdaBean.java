package com.wasoft.dataserver.ws.da.bean;

/**
 * ���ӵ���ϵͳ��ʹ�õ�bean,��Ӧ���ݿ��е�ef_dwda��,����ID�ֶ�������
 * 
 * @author YANG 2010-9-19
 */

public class DwdaBean extends DaBean {

	public DwdaBean() {
	}

	private Long id; // ID Long 10 �Զ����� ����

	private String dah; // ������ dah Varchar 20 �Զ�����

	private String ygdh; // Ԥ�鵵�� ygdh Varchar 20 �Զ����� ���

	private String cbwdbm;// �а�������� cbwdbm Varchar 6 ҵ���

	private String cbwd;// �а��������� cbwd Varchar 60 ҵ���

	private String gjdbm;// �鼯����� gjdbm Varchar 4 ҵ���

	private String gjd;// �鼯������ gjd Varchar 60 ҵ���

	private String dwzh;// ��λ�˺� dwzh Varchar 20 ҵ��� �ؼ���

	private String dw;// ��λ���� dw Varchar 100 ҵ���

	private String ckzh;// ����˺� ckzh Varchar 30 ҵ���

	private String khrq;// �������� khrq Date 8 ҵ���

	private String xhrq;// �������� xhrq Date 8 ҵ���

	private String fcrq;// ������� fcrq Date 8 ҵ���

	private String zzjgdm;// ��֯�������� zzjgdm Varchar 20 ҵ���

	private String dwysdm;// ��λԤ����� dwysdm Varchar 20 ҵ���

	private String dwdz;// ��λ��ַ dwdz Varchar 120 ҵ���

	private String gjzgybm;// �鼯ר��Ա���� gjzgydm Varchar 2 ҵ���

	private String gjzgy;// �鼯ר��Ա���� gjzgy Varchar 60 ҵ���
	
	private String type;
	
	private String typeb;
	
	private String khrq_begin;
	
	private String khrq_end;
	
	private String xhrq_begin;
	
	private String xhrq_end;
	
	private String fcrq_begin;
	
	private String fcrq_end;

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

	public String getKhrq_begin() {
		return khrq_begin;
	}

	public void setKhrq_begin(String khrqBegin) {
		khrq_begin = khrqBegin;
	}

	public String getKhrq_end() {
		return khrq_end;
	}

	public void setKhrq_end(String khrqEnd) {
		khrq_end = khrqEnd;
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

	public String getCkzh() {
		return ckzh;
	}

	public void setCkzh(String ckzh) {
		this.ckzh = ckzh;
	}

	public String getKhrq() {
		return khrq;
	}

	public void setKhrq(String khrq) {
		this.khrq = khrq;
	}

	public String getXhrq() {
		return xhrq;
	}

	public void setXhrq(String xhrq) {
		this.xhrq = xhrq;
	}

	public String getFcrq() {
		return fcrq;
	}

	public void setFcrq(String fcrq) {
		this.fcrq = fcrq;
	}

	public String getZzjgdm() {
		return zzjgdm;
	}

	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}

	public String getDwysdm() {
		return dwysdm;
	}

	public void setDwysdm(String dwysdm) {
		this.dwysdm = dwysdm;
	}

	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}

	public String getGjzgybm() {
		return gjzgybm;
	}

	public void setGjzgybm(String gjzgybm) {
		this.gjzgybm = gjzgybm;
	}

	public String getGjzgy() {
		return gjzgy;
	}

	public void setGjzgy(String gjzgy) {
		this.gjzgy = gjzgy;
	}

	public String getXhrq_begin() {
		return xhrq_begin;
	}

	public void setXhrq_begin(String xhrq_begin) {
		this.xhrq_begin = xhrq_begin;
	}

	public String getXhrq_end() {
		return xhrq_end;
	}

	public void setXhrq_end(String xhrq_end) {
		this.xhrq_end = xhrq_end;
	}

	public String getFcrq_begin() {
		return fcrq_begin;
	}

	public void setFcrq_begin(String fcrq_begin) {
		this.fcrq_begin = fcrq_begin;
	}

	public String getFcrq_end() {
		return fcrq_end;
	}

	public void setFcrq_end(String fcrq_end) {
		this.fcrq_end = fcrq_end;
	}

}

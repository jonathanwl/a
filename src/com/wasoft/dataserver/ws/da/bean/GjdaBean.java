package com.wasoft.dataserver.ws.da.bean;

/**
 * ���ӵ���ϵͳ��ʹ�õ�bean,��Ӧ���ݿ��е�ef_gjda��,����ID�ֶ�������
 * 
 * @author YANG 2010-9-19
 */

public class GjdaBean extends DaBean {

	public GjdaBean() {
	}

	private Long id; // ID Long 10 �Զ����� ����

	private String dah; // ������ dah Varchar 20 �Զ�����

	private String ygdh; // Ԥ�鵵�� ygdh Varchar 20 �Զ����� ���

	private String cbwdbm;// �а�������� cbwdbm Varchar 6 ҵ���

	private String cbwd;// �а��������� cbwd Varchar 60 ҵ���

	private String gjdbm;// �鼯����� gjdbm Varchar 4 ҵ���

	private String gjd;// �鼯������ gjd Varchar 60 ҵ���

	private String zgzh; // ְ���˺� zgzh Varchar 30 ҵ��� �ؼ���

	private String zgxm; // ְ������ zgxm Varchar 60 ҵ���

	private String dwzh;// ��λ�˺� dwzh Varchar 20 ҵ���

	private String dw; // ��λ���� dw Varchar 100 ҵ���

	private String zjhm;// ֤������ zjhm Varchar 40 ҵ���

	private String xb;// �Ա� xb Varchar 2 ҵ���

	private String khrq;// �������� khrq Date 8 ҵ���

	private String xhrq;// �������� xhrq Date 8 ҵ���
	
	private String type;
	
	private String typeb;
	
	private String khrq_begin ;
	
	private String khrq_end ;

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

	public String getKhrq_begin() {
		return khrq_begin;
	}

	public void setKhrq_begin(String khrq_begin) {
		this.khrq_begin = khrq_begin;
	}

	public String getKhrq_end() {
		return khrq_end;
	}

	public void setKhrq_end(String khrq_end) {
		this.khrq_end = khrq_end;
	}

}

package com.wasoft.dataserver.ws.da.bean;

import java.io.Serializable;
/**
 * ����ɨ��¼��-ʵ�ﵵ��
 * @author zwh 2011��12��17��
 * ������˹鵵-ʵ�ﵵ���鵵
 * @author zwh 2011��12��20��
 */
public class SwdalrMXBean implements Serializable{
	private long id;//
	private String cjr;//������//
	private String cjrq;//��������//
	private String cjrq_begin;
	private String cjrq_end;
	
	private String tm;//��������//
	private String ztlb;//�������//
	private String zt;
	private String ygdh;//Ԥ�鵵��//
	private String thyy;//�˻�ԭ��
	
	private String type;//��������//
	private String typeb;//�������ͱ���//
	private String ywlsh;//ҵ����ˮ��
	private String tjr;//�ύ��//
	private String tjrq;//�ύ����//
	
	private String bcnx;//��������//
	private String bmjb;//���ܼ���//
	private int count;
	private String glb;//��������
	
	private int isAdmin;//�Ƿ�Ϊϵͳ����Ա 
	
	public String getGlb() {
		return glb;
	}

	public void setGlb(String glb) {
		this.glb = glb;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getTjr() {
		return tjr;
	}
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	public String getTjrq() {
		return tjrq;
	}
	public void setTjrq(String tjrq) {
		this.tjrq = tjrq;
	}
	public String getBcnx() {
		return bcnx;
	}
	public void setBcnx(String bcnx) {
		this.bcnx = bcnx;
	}
	public String getBmjb() {
		return bmjb;
	}
	public void setBmjb(String bmjb) {
		this.bmjb = bmjb;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	public String getCjrq_begin() {
		return cjrq_begin;
	}
	public void setCjrq_begin(String cjrqBegin) {
		cjrq_begin = cjrqBegin;
	}
	public String getCjrq_end() {
		return cjrq_end;
	}
	public void setCjrq_end(String cjrqEnd) {
		cjrq_end = cjrqEnd;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public String getZtlb() {
		return ztlb;
	}
	public void setZtlb(String ztlb) {
		this.ztlb = ztlb;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getYgdh() {
		return ygdh;
	}
	public void setYgdh(String ygdh) {
		this.ygdh = ygdh;
	}
	public String getThyy() {
		return thyy;
	}
	public void setThyy(String thyy) {
		this.thyy = thyy;
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
	public String getYwlsh() {
		return ywlsh;
	}
	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}

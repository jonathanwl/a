package com.wasoft.dataserver.ws.da.bean;

import java.io.Serializable;

/**
 * ����ɨ��¼��-�Ƽ�����¼��
 * @author zwh 2011-12-15
 * ������˹鵵-�Ƽ������鵵
 * @author zwh 2011��12��20��
 */
public class KjMXBean implements Serializable{
	private long id;//
	private String cjr;//������//
	private String cjrq;//��������//
	private String tm;//��������//
	private String ygdh;//Ԥ�鵵��//
	
	private String typeb;//�������ͱ���//
	private String type;//��������//
	private String ywlsh;//ҵ����ˮ��
	private int count;
	private String begin;//��ʼ��������
	
	private String end;//������������
	private String ztlb;//�������//
	private String tjr;//�ύ��//
	private String tjrq;//�ύ����//
	private String bcnx;//��������//
	
	private String bmjb;//���ܼ���//
	
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
	public String getZtlb() {
		return ztlb;
	}
	public void setZtlb(String ztlb) {
		this.ztlb = ztlb;
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
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
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
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public String getYgdh() {
		return ygdh;
	}
	public void setYgdh(String ygdh) {
		this.ygdh = ygdh;
	}
	public String getTypeb() {
		return typeb;
	}
	public void setTypeb(String typeb) {
		this.typeb = typeb;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

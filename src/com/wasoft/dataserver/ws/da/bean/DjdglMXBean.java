package com.wasoft.dataserver.ws.da.bean;

import java.io.Serializable;

/**
 * ��������-����������
 * @author zwh 2011��12��15��
 *
 */
public class DjdglMXBean extends DaBean implements Serializable{
	private long id;
	private String dah;//������
	private String tjr;//�ύ��
	private String tjrq;//�ύ����
	private String tjrq_begin;
	
	private String tjrq_end;
	private String jsr;//������
	private String jsrq;//��������
	private String jsrq_begin;
	private String jsrq_end;
	
	private String zxr;//ע����
	private String zxrq;//ע������
	private String zxyy;//ע��ԭ��
	private int bcnx;//ע���󱣴�����
	private String bmjb;//���ܼ���
	
	private int clfs;//���Ϸ���
	private String cfwz;//���λ��
	private String gh;//���
	private String hh;//�к�
	private int sxh;//����˳���
	
	private String type;//��������
	private int count;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDah() {
		return dah;
	}
	public void setDah(String dah) {
		this.dah = dah;
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
	public String getJsr() {
		return jsr;
	}
	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	public String getJsrq_begin() {
		return jsrq_begin;
	}
	public void setJsrq_begin(String jsrqBegin) {
		jsrq_begin = jsrqBegin;
	}
	public String getJsrq_end() {
		return jsrq_end;
	}
	public void setJsrq_end(String jsrqEnd) {
		jsrq_end = jsrqEnd;
	}
	public String getZxr() {
		return zxr;
	}
	public void setZxr(String zxr) {
		this.zxr = zxr;
	}
	public String getZxrq() {
		return zxrq;
	}
	public void setZxrq(String zxrq) {
		this.zxrq = zxrq;
	}
	public String getZxyy() {
		return zxyy;
	}
	public void setZxyy(String zxyy) {
		this.zxyy = zxyy;
	}
	public int getBcnx() {
		return bcnx;
	}
	public void setBcnx(int bcnx) {
		this.bcnx = bcnx;
	}
	public String getBmjb() {
		return bmjb;
	}
	public void setBmjb(String bmjb) {
		this.bmjb = bmjb;
	}
	public int getClfs() {
		return clfs;
	}
	public void setClfs(int clfs) {
		this.clfs = clfs;
	}
	public String getCfwz() {
		return cfwz;
	}
	public void setCfwz(String cfwz) {
		this.cfwz = cfwz;
	}
	public String getGh() {
		return gh;
	}
	public void setGh(String gh) {
		this.gh = gh;
	}
	public String getHh() {
		return hh;
	}
	public void setHh(String hh) {
		this.hh = hh;
	}
	public int getSxh() {
		return sxh;
	}
	public void setSxh(int sxh) {
		this.sxh = sxh;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}

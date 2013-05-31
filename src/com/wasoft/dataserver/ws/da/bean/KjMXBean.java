package com.wasoft.dataserver.ws.da.bean;

import java.io.Serializable;

/**
 * 材料扫描录入-科技档案录入
 * @author zwh 2011-12-15
 * 档案审核归档-科技档案归档
 * @author zwh 2011年12月20日
 */
public class KjMXBean implements Serializable{
	private long id;//
	private String cjr;//创建人//
	private String cjrq;//创建日期//
	private String tm;//案卷题名//
	private String ygdh;//预归档号//
	
	private String typeb;//档案类型编码//
	private String type;//档案类型//
	private String ywlsh;//业务流水号
	private int count;
	private String begin;//开始创建日期
	
	private String end;//结束创建日期
	private String ztlb;//载体类别//
	private String tjr;//提交人//
	private String tjrq;//提交日期//
	private String bcnx;//保存年限//
	
	private String bmjb;//保密级别//
	
	private String glb;//管理部名称
	
	private int isAdmin;//是否为系统管理员 
	
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

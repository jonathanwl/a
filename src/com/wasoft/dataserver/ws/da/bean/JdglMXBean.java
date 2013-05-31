package com.wasoft.dataserver.ws.da.bean;

import java.io.Serializable;

/**
 * 鉴定管理-鉴定管理
 * @author zwh 2011年12月15日
 *
 */
public class JdglMXBean extends DaBean implements Serializable{
	private long id;
	private String ygdh;//预归档号
	private String dah;//档案号
	private String dash; //档案室号
	private String gh;//柜号
	private String grow; //档案柜行
	private String ph; //档案排号
	private String hh;//盒号
	private String sxh;  //档案顺序号
	public String getDash() {
		return dash;
	}
	public void setDash(String dash) {
		this.dash = dash;
	}
	public String getGrow() {
		return grow;
	}
	public void setGrow(String grow) {
		this.grow = grow;
	}
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
	private int bcnx;//保存年限
	private String bmjb;//保密级别
	private int clfs;//材料份数
	private String type;//档案类型
	private String zxr;//注销人
	
	private String zxrq;//注销日期
	private String zxrq_begin;
	private String zxrq_end;
	private String zxyy;//注销原因
	private String cfwz;//存放位置
	
	private int count;
	
	public String getZxrq_begin() {
		return zxrq_begin;
	}
	public void setZxrq_begin(String zxrqBegin) {
		zxrq_begin = zxrqBegin;
	}
	public String getZxrq_end() {
		return zxrq_end;
	}
	public void setZxrq_end(String zxrqEnd) {
		zxrq_end = zxrqEnd;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getYgdh() {
		return ygdh;
	}
	public void setYgdh(String ygdh) {
		this.ygdh = ygdh;
	}
	public String getDah() {
		return dah;
	}
	public void setDah(String dah) {
		this.dah = dah;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getCfwz() {
		return cfwz;
	}
	public void setCfwz(String cfwz) {
		this.cfwz = cfwz;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}

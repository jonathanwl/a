package com.wasoft.dataserver.ws.da.bean;

/**
 * 电子档案系统中使用的bean,对应数据库中的ef_tsda表,其中dah字段是主键
 * 
 * @author DING 2010-7-30
 */
public class TsdaBean extends DaBean {

	public TsdaBean() {
	}

	private long id; // id long 10

	private String tm;// 题名 tm Varchar 160

	private String dah;// 档案号 dah Varchar 20

	private String ygdh;// 预归档号 ygdh Varchar 20

	private String ztlb;// 载体类别 tm Varchar 10

	private String zrz;// 责任人 zrz Varchar 30

	private String cjr;
	private String begin;
	private String end;
	private String f_type;
	private String dash;
	private String bmjb;
	private String ywlsh;
	private int bcnx;
	private String gh;
	private String grow;
	private String hh;
	private int count;
	private String sxh;
	private String cjrq;
	private String type;
	private String typeb;
	private String cjrq_begin;
	private String cjrq_end;
	
	
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

	public String getSxh() {
		return sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
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

	public String getF_type() {
		return f_type;
	}

	public void setF_type(String fType) {
		f_type = fType;
	}

	public String getDash() {
		return dash;
	}

	public void setDash(String dash) {
		this.dash = dash;
	}

	public String getBmjb() {
		return bmjb;
	}

	public void setBmjb(String bmjb) {
		this.bmjb = bmjb;
	}

	public String getYwlsh() {
		return ywlsh;
	}

	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}

	public int getBcnx() {
		return bcnx;
	}

	public void setBcnx(int bcnx) {
		this.bcnx = bcnx;
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public String getGrow() {
		return grow;
	}

	public void setGrow(String grow) {
		this.grow = grow;
	}

	public String getHh() {
		return hh;
	}

	public void setHh(String hh) {
		this.hh = hh;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCjrq() {
		return cjrq;
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getZrz() {
		return zrz;
	}

	public void setZrz(String zrz) {
		this.zrz = zrz;
	}

	public String getZtlb() {
		return ztlb;
	}

	public void setZtlb(String ztlb) {
		this.ztlb = ztlb;
	}
}

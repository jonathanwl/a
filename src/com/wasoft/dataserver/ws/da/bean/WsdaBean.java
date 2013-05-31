package com.wasoft.dataserver.ws.da.bean;

/**
 * 电子档案系统中使用的bean,对应数据库中的ef_wsda表,其中dah字段是主键
 * 
 * @author DING 2010-7-30
 */
public class WsdaBean extends DaBean {

	public WsdaBean() {
	}

	private long id; // id long 10

	private String tm; // 题名 tm Varchar 160 页面录入

	private String yjml;// 一级目录 Yjml Varchar 20 下拉选择

	private String ejml;// 二级目录 Ejml Varchar 20 下拉选择

	private String sjml;// 三级目录 Sjml Varchar 20 下拉选择

	private String dah;// 档案号 dah Varchar 20 自动生成

	private String ygdh; // 预归档号 ygdh Varchar 20

	private String zrz;// 责任者 Zrz Varchar 30 自动生成
	
	private String zrbm;

	private String cjr;
	
	private String begin;//开始创建时间/
	
	private String end;//结束创建时间/
	
	private String F_TYPE;//
	 
	private String YWLSH;//业务流水号
	
	private String F_TYPEB;//
	
	private String tjr;
	
	private String tjrq;
	
	private String dash;
	
	private String gh;
	
	private String grow;
	
	private String hh;
	
	private String sxh;
	
	private String jsr;
	
	private String bmjb;
	
	private String jsrq;
	
	private int bcnx;
	
	private String zxr;
	
	private String zxrq;
	
	private String zxyy;
	
	private int clfs;
	
	private String f_state;

	private int count;

	private String cfwz;
	
	private String tjrq_begin;
	
	private String tjrq_end;
	
	private String type;
	
	private String typeb;
	
	
	
	
	
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

	public int getBcnx() {
		return bcnx;
	}

	public int getClfs() {
		return clfs;
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

	public String getF_TYPE() {
		return F_TYPE;
	}

	public void setF_TYPE(String fTYPE) {
		F_TYPE = fTYPE;
	}

	public String getYWLSH() {
		return YWLSH;
	}

	public void setYWLSH(String yWLSH) {
		YWLSH = yWLSH;
	}

	public String getF_TYPEB() {
		return F_TYPEB;
	}

	public void setF_TYPEB(String fTYPEB) {
		F_TYPEB = fTYPEB;
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

	public String getGrow() {
		return grow;
	}

	public void setGrow(String grow) {
		this.grow = grow;
	}

	public String getDash() {
		return dash;
	}

	public void setDash(String dash) {
		this.dash = dash;
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

	public String getSxh() {
		return sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
	}

	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getBmjb() {
		return bmjb;
	}

	public void setBmjb(String bmjb) {
		this.bmjb = bmjb;
	}

	public String getJsrq() {
		return jsrq;
	}

	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}

	

	public void setBcnx(int bcnx) {
		this.bcnx = bcnx;
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

	

	public void setClfs(int clfs) {
		this.clfs = clfs;
	}

	public String getF_state() {
		return f_state;
	}

	public void setF_state(String fState) {
		f_state = fState;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCfwz() {
		return cfwz;
	}

	public void setCfwz(String cfwz) {
		this.cfwz = cfwz;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getZrbm() {
		return zrbm;
	}

	public void setZrbm(String zrbm) {
		this.zrbm = zrbm;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}

	public String getEjml() {
		return ejml;
	}

	public void setEjml(String ejml) {
		this.ejml = ejml;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSjml() {
		return sjml;
	}

	public void setSjml(String sjml) {
		this.sjml = sjml;
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

	public String getYjml() {
		return yjml;
	}

	public void setYjml(String yjml) {
		this.yjml = yjml;
	}

	public String getZrz() {
		return zrz;
	}

	public void setZrz(String zrz) {
		this.zrz = zrz;
	}

}

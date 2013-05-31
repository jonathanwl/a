package com.wasoft.dataserver.ws.da.bean;

/**
 * 电子档案系统中使用的bean,对应数据库中的ef_zqda表,其中dah字段是主键
 * 
 * @author DING 2010-7-30
 */
public class ZqdaBean extends DaBean {

	private int id; // id long 10

	private String zgzh; // 职工账号 zgzh Varchar 30 业务库 关键字

	private String tqlsh; // 提取流水号 tqlsh varchar 20 业务库 关键字

	private String zgxm; // 职工姓名 zgxm Varchar 60 业务库

	private String dwzh;// 单位账号 dwzh Varchar 20 业务库

	private String dw; // 单位名称 dw Varchar 100 业务库

	private String zjhm;// 证件号码 zjhm Varchar 40 业务库

	private String cbwdbm;// 承办网点编码 cbwdbm Varchar 6 业务库

	private String cbwd;// 承办网点名称 cbwd Varchar 60 业务库

	private String gjdbm;// 归集点编码 gjdbm Varchar 4 业务库

	private String gjd;// 归集点名称 gjd Varchar 60 业务库

	private String sczqrq;// 上次支取日期 sczqrq Date 8 业务库

	private String zqrq;// 上次支取日期 zqrq Date 8 业务库

	private String zqyt;// 支取用途 zqyt varchar 60 业务库

	private String dah;// 档案号 dah Varchar 20

	private String rzczy;// 入账操作员 rzczy Varchar 20

	private String djbh;// 单据编号 djbh Varchar 20

	private String fhczy;// 业务复核操作员 fhczy Varchar 20

	private String sprq;// 审批日期 sprq Date 8 业务库
	
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

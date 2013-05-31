package com.wasoft.dataserver.ws.da.bean;

/**
 * 电子档案系统中使用的bean,对应数据库中的ef_gjda表,其中ID字段是主键
 * 
 * @author YANG 2010-9-19
 */

public class GjdaBean extends DaBean {

	public GjdaBean() {
	}

	private Long id; // ID Long 10 自动生成 主键

	private String dah; // 档案号 dah Varchar 20 自动生成

	private String ygdh; // 预归档号 ygdh Varchar 20 自动生成 外键

	private String cbwdbm;// 承办网点编码 cbwdbm Varchar 6 业务库

	private String cbwd;// 承办网点名称 cbwd Varchar 60 业务库

	private String gjdbm;// 归集点编码 gjdbm Varchar 4 业务库

	private String gjd;// 归集点名称 gjd Varchar 60 业务库

	private String zgzh; // 职工账号 zgzh Varchar 30 业务库 关键字

	private String zgxm; // 职工姓名 zgxm Varchar 60 业务库

	private String dwzh;// 单位账号 dwzh Varchar 20 业务库

	private String dw; // 单位名称 dw Varchar 100 业务库

	private String zjhm;// 证件号码 zjhm Varchar 40 业务库

	private String xb;// 性别 xb Varchar 2 业务库

	private String khrq;// 开户日期 khrq Date 8 业务库

	private String xhrq;// 销户日期 xhrq Date 8 业务库
	
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

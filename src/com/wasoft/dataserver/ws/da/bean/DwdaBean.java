package com.wasoft.dataserver.ws.da.bean;

/**
 * 电子档案系统中使用的bean,对应数据库中的ef_dwda表,其中ID字段是主键
 * 
 * @author YANG 2010-9-19
 */

public class DwdaBean extends DaBean {

	public DwdaBean() {
	}

	private Long id; // ID Long 10 自动生成 主键

	private String dah; // 档案号 dah Varchar 20 自动生成

	private String ygdh; // 预归档号 ygdh Varchar 20 自动生成 外键

	private String cbwdbm;// 承办网点编码 cbwdbm Varchar 6 业务库

	private String cbwd;// 承办网点名称 cbwd Varchar 60 业务库

	private String gjdbm;// 归集点编码 gjdbm Varchar 4 业务库

	private String gjd;// 归集点名称 gjd Varchar 60 业务库

	private String dwzh;// 单位账号 dwzh Varchar 20 业务库 关键字

	private String dw;// 单位名称 dw Varchar 100 业务库

	private String ckzh;// 存款账号 ckzh Varchar 30 业务库

	private String khrq;// 开户日期 khrq Date 8 业务库

	private String xhrq;// 销户日期 xhrq Date 8 业务库

	private String fcrq;// 封存日期 fcrq Date 8 业务库

	private String zzjgdm;// 组织机构代码 zzjgdm Varchar 20 业务库

	private String dwysdm;// 单位预算代码 dwysdm Varchar 20 业务库

	private String dwdz;// 单位地址 dwdz Varchar 120 业务库

	private String gjzgybm;// 归集专管员代码 gjzgydm Varchar 2 业务库

	private String gjzgy;// 归集专管员名称 gjzgy Varchar 60 业务库
	
	private String type;
	
	private String typeb;
	
	private String khrq_begin;
	
	private String khrq_end;
	
	private String xhrq_begin;
	
	private String xhrq_end;
	
	private String fcrq_begin;
	
	private String fcrq_end;

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

	public String getKhrq_begin() {
		return khrq_begin;
	}

	public void setKhrq_begin(String khrqBegin) {
		khrq_begin = khrqBegin;
	}

	public String getKhrq_end() {
		return khrq_end;
	}

	public void setKhrq_end(String khrqEnd) {
		khrq_end = khrqEnd;
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

	public String getCkzh() {
		return ckzh;
	}

	public void setCkzh(String ckzh) {
		this.ckzh = ckzh;
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

	public String getFcrq() {
		return fcrq;
	}

	public void setFcrq(String fcrq) {
		this.fcrq = fcrq;
	}

	public String getZzjgdm() {
		return zzjgdm;
	}

	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}

	public String getDwysdm() {
		return dwysdm;
	}

	public void setDwysdm(String dwysdm) {
		this.dwysdm = dwysdm;
	}

	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}

	public String getGjzgybm() {
		return gjzgybm;
	}

	public void setGjzgybm(String gjzgybm) {
		this.gjzgybm = gjzgybm;
	}

	public String getGjzgy() {
		return gjzgy;
	}

	public void setGjzgy(String gjzgy) {
		this.gjzgy = gjzgy;
	}

	public String getXhrq_begin() {
		return xhrq_begin;
	}

	public void setXhrq_begin(String xhrq_begin) {
		this.xhrq_begin = xhrq_begin;
	}

	public String getXhrq_end() {
		return xhrq_end;
	}

	public void setXhrq_end(String xhrq_end) {
		this.xhrq_end = xhrq_end;
	}

	public String getFcrq_begin() {
		return fcrq_begin;
	}

	public void setFcrq_begin(String fcrq_begin) {
		this.fcrq_begin = fcrq_begin;
	}

	public String getFcrq_end() {
		return fcrq_end;
	}

	public void setFcrq_end(String fcrq_end) {
		this.fcrq_end = fcrq_end;
	}

}

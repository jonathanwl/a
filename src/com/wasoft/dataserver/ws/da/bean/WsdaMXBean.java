package com.wasoft.dataserver.ws.da.bean;
/**
 * 材料扫描录入-文书档案查询
 * @author zwh 2011-12-13
 * 档案审核归档-文书档案归档
 */
public class WsdaMXBean {
	private long id; // id long 10//
	private String tm; // 题名 tm Varchar 160 页面录入//
	private String yjml;// 一级目录 Yjml Varchar 20 下拉选择//
	private String ejml;// 二级目录 Ejml Varchar 20 下拉选择//
	private String sjml;// 三级目录 Sjml Varchar 20 下拉选择//

	private String ygdh; // 预归档号 ygdh Varchar 20/
	private String zrz;// 责任者 Zrz Varchar 30 自动生成//
	private String zrbm;//责任部门/
	private String cjr;//创建人/
	private String cjsj;//创建时间
	
	private String begin;//开始创建时间/	
	private String end;//结束创建时间/	
	private String type;//	 
	private String ywlsh;//业务流水号//	
	private String typeb;//
	
	private String tjr;//提交人//
	private String tjrq;//提交日期//
	private String tjrq_begin;//
	private String tjrq_end;//
	private String gh;//柜号//
	
	private String hh;//盒号//
	private String bcnx;//保存年限//
	private String bmjb;//保密级别//
	private int clfs;//材料份数//
	private int count;
	
	private String glb;//管理部名称
	private int isAdmin;
	
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

	public String getTypeb() {
		return typeb;
	}

	public void setTypeb(String typeb) {
		this.typeb = typeb;
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

	public String getYjml() {
		return yjml;
	}

	public void setYjml(String yjml) {
		this.yjml = yjml;
	}

	public String getEjml() {
		return ejml;
	}

	public void setEjml(String ejml) {
		this.ejml = ejml;
	}

	public String getSjml() {
		return sjml;
	}

	public void setSjml(String sjml) {
		this.sjml = sjml;
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

	public String getZrbm() {
		return zrbm;
	}

	public void setZrbm(String zrbm) {
		this.zrbm = zrbm;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
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

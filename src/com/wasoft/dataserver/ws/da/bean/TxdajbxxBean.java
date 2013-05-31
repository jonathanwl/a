package com.wasoft.dataserver.ws.da.bean;

import java.util.List;

public class TxdajbxxBean extends DaBean {

	private long id; // ID number ID

	private String txqzh; // 他项权证号

	private String ygdh; // 与归档号 varchar（20） YGDH

	private String tjrbm; // 提交人编码 varchar（20） TJRBM

	private String tjr; // 提交人 varchar（60） TJR

	private String tjrq; // 提交日期 date TJRQ

	private String jsrbm; // JSRBM

	private String jsr; // JSR

	private String jsrq; // JSRQ

	private int bcnx; // BCNX

	private String zxrbm; // ZXRBM

	private String zxr; // ZXR

	private String zxrq; // ZXRQ

	private String zxyy; // ZXYY

	private String bmjb; // /BMJB

	private int clfs; // CLFS

	private String cfwz; // CFWZ

	private String gh; // GH

	private String hh; // HH

	private String sxh; // SXH

	private String dah; // DAH

	private String f_type; // F_type

	private String f_state; // F_STATE

	private String tblName; // 操作表名参数

	private String dash; // 档案室号

	private String grow; // 档案架号

	private String gjdbm; // s

	private String thyy; // 退回原因

	private String tablename; // 删除未提交档案 需要此字段 2011.3.28 zy

	private String ret;// 删除未提交档案 返回值ret 需要此字段 2011.3.28 zy

	public String getThyy() {
		return thyy;
	}

	public void setThyy(String thyy) {
		this.thyy = thyy;
	}

	public String getGjdbm() {
		return gjdbm;
	}

	public void setGjdbm(String gjdbm) {
		this.gjdbm = gjdbm;
	}

	public TxdajbxxBean(long id, String idString, String operation,
			String tblName) {
		super();
		this.id = id;
		this.idString = idString;
		this.operation = operation;
		this.tblName = tblName;

	}

	private String operation;// 归档、注销、销毁等的操作标志

	private String idString;// id的字符串形式

	private String clbm; // 材料编码

	private String cl; // 材料名称

	private byte[] clyx; // 材料影像

	private List list;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public TxdajbxxBean() {
	}

	public TxdajbxxBean(long id) {
		this.id = id;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
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

	public String getTjrbm() {
		return tjrbm;
	}

	public void setTjrbm(String tjrbm) {
		this.tjrbm = tjrbm;
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

	public String getJsrbm() {
		return jsrbm;
	}

	public void setJsrbm(String jsrbm) {
		this.jsrbm = jsrbm;
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

	public int getBcnx() {
		return bcnx;
	}

	public void setBcnx(int bcnx) {
		this.bcnx = bcnx;
	}

	public String getZxrbm() {
		return zxrbm;
	}

	public void setZxrbm(String zxrbm) {
		this.zxrbm = zxrbm;
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

	public String getSxh() {
		return sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}

	public String getF_type() {
		return f_type;
	}

	public void setF_type(String f_type) {
		this.f_type = f_type;
	}

	public String getF_state() {
		return f_state;
	}

	public void setF_state(String f_state) {
		this.f_state = f_state;
	}

	public String getTblName() {
		return tblName;
	}

	public void setTblName(String tblName) {
		this.tblName = tblName;
	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public String getClbm() {
		return clbm;
	}

	public void setClbm(String clbm) {
		this.clbm = clbm;
	}

	public byte[] getClyx() {
		return clyx;
	}

	public void setClyx(byte[] clyx) {
		this.clyx = clyx;
	}

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

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getTxqzh() {
		return txqzh;
	}

	public void setTxqzh(String txqzh) {
		this.txqzh = txqzh;
	}

}

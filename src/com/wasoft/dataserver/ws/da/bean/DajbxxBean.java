package com.wasoft.dataserver.ws.da.bean;

public class DajbxxBean extends DaBean {

	private long id; // ID number ID

	private String ygdh; // 与归档号 varchar（20） YGDH

	private String ywlsh; // 与归档号 varchar（20） YWLSH

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
	
	private String ph; //PH

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

	private String czybm;// 操作员编码

	private String czy;// 操作员

	private String xgczy;// 修改操作员

	private String zt;// 修改操作员

	private String czfs;// 修改操作员

	private String createDate;// 上传时间

	private String updateDate;// 修改时间
	//报表用的开始
	
	private String jsrq2; // JSRQ
	
	private String tjrq2; // 提交日期 date TJRQ

	private String zxrq2; // ZXRQ
	
    private String TM;//wsda
	
	private String YJML;//wsda
	
	private String EJML;//wsda
	
	private String SJML;//wsda
	
	private String ZRZ;//wsda
	
	private String DWZH;//grgida
	
	private String DW;//grgida
	
	private String DWDZ;//grgjda
	
	private String CBWD;//grgjda
	
	private String Zgzh;//grgjda
	
	private String Zgxm;//grgjda
	
	private String Zjhm;//grgjda
	
	private String Xb;//grgjda
	
	private String Khrq;//grgjda
	
	private String GFLX;//dkda
	
	private String DKNX;//dkda
	
	private String Zqyt;//zqda
	
	private String ZQRQ;//zqda
	
	private String JSRQ;//zqda
	
	private String ZCZH;//zqda
	
	private String Gjzgy;//dwda
	
	private String Xhrq;//dwda
	
   
	
	private String Fcrq;//dwda
	
	private String Xmbh;//lpda
	
    private String Xm;//lpda
    
    private String ZTLB;//tsda
    
    private String DYR;//dazhgdtj
	 
    private String bj;//标记 
	
    
    
    private String f_typeb;
    
    private String TjrqBegin;
	
	private String TjrqEnd;
	
	private String JsrqBegin;
	
	private String JsrqEnd;
	//报表结束.
    
    
    public String getF_typeb() {
		return f_typeb;
	}



	public void setF_typeb(String f_typeb) {
		this.f_typeb = f_typeb;
	}



	public String getBj() {
		return bj;
	}



	public void setBj(String bj) {
		this.bj = bj;
	}



	public String getDYR() {
		return DYR;
	}



	public void setDYR(String dYR) {
		DYR = dYR;
	}



	public String getZxrq2() {
		return zxrq2;
	}

	

	public String getJsrq2() {
		return jsrq2;
	}



	public void setJsrq2(String jsrq2) {
		this.jsrq2 = jsrq2;
	}



	public void setZxrq2(String zxrq2) {
		this.zxrq2 = zxrq2;
	}
	
	public String getZTLB() {
		return ZTLB;
	}

	public void setZTLB(String zTLB) {
		ZTLB = zTLB;
	}

	public String getXmbh() {
		return Xmbh;
	}

	public void setXmbh(String xmbh) {
		Xmbh = xmbh;
	}

	public String getXm() {
		return Xm;
	}

	public void setXm(String xm) {
		Xm = xm;
	}

	
	
	
	public String getGjzgy() {
		return Gjzgy;
	}

	public void setGjzgy(String gjzgy) {
		Gjzgy = gjzgy;
	}

	public String getXhrq() {
		return Xhrq;
	}

	public void setXhrq(String xhrq) {
		Xhrq = xhrq;
	}

	public String getFcrq() {
		return Fcrq;
	}

	public void setFcrq(String fcrq) {
		Fcrq = fcrq;
	}

	public String getZCZH() {
		return ZCZH;
	}

	public void setZCZH(String zCZH) {
		ZCZH = zCZH;
	}

	public String getTjrq2() {
		return tjrq2;
	}

	public void setTjrq2(String tjrq2) {
		this.tjrq2 = tjrq2;
	}


	public String getZqyt() {
		return Zqyt;
	}

	public void setZqyt(String zqyt) {
		Zqyt = zqyt;
	}

	public String getZQRQ() {
		return ZQRQ;
	}

	public void setZQRQ(String zQRQ) {
		ZQRQ = zQRQ;
	}

	public String getJSRQ() {
		return JSRQ;
	}

	public void setJSRQ(String jSRQ) {
		JSRQ = jSRQ;
	}

	public String getDKNX() {
		return DKNX;
	}

	public void setDKNX(String dKNX) {
		DKNX = dKNX;
	}

	public String getGFLX() {
		return GFLX;
	}

	public void setGFLX(String gFLX) {
		GFLX = gFLX;
	}

	public String getZgzh() {
		return Zgzh;
	}

	public void setZgzh(String zgzh) {
		Zgzh = zgzh;
	}

	public String getZgxm() {
		return Zgxm;
	}

	public void setZgxm(String zgxm) {
		Zgxm = zgxm;
	}

	public String getZjhm() {
		return Zjhm;
	}

	public void setZjhm(String zjhm) {
		Zjhm = zjhm;
	}

	public String getXb() {
		return Xb;
	}

	public void setXb(String xb) {
		Xb = xb;
	}

	public String getKhrq() {
		return Khrq;
	}

	public void setKhrq(String khrq) {
		Khrq = khrq;
	}


	
	
	
	
	public String getDWZH() {
		return DWZH;
	}

	public void setDWZH(String dWZH) {
		DWZH = dWZH;
	}

	public String getDW() {
		return DW;
	}

	public void setDW(String dW) {
		DW = dW;
	}

	public String getDWDZ() {
		return DWDZ;
	}

	public void setDWDZ(String dWDZ) {
		DWDZ = dWDZ;
	}



	public String getCBWD() {
		return CBWD;
	}

	public void setCBWD(String cBWD) {
		CBWD = cBWD;
	}


	
	public String getTM() {
		return TM;
	}

	public void setTM(String tM) {
		this.TM = tM;
	}

	public String getYJML() {
		return YJML;
	}

	public void setYJML(String yJML) {
		this.YJML = yJML;
	}

	public String getEJML() {
		return EJML;
	}

	public void setEJML(String eJML) {
		this.EJML = eJML;
	}

	public String getSJML() {
		return SJML;
	}

	public void setSJML(String sJML) {
		this.SJML = sJML;
	}

	public String getZRZ() {
		return ZRZ;
	}

	public void setZRZ(String zRZ) {
		this.ZRZ = zRZ;
	}

	
	
	
	

  
	public String getThyy() {
		return thyy;
	}

	public String getCzybm() {
		return czybm;
	}

	public void setCzybm(String czybm) {
		this.czybm = czybm;
	}

	public String getCzy() {
		return czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
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

	public DajbxxBean(long id, String idString, String operation, String tblName) {
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


	public DajbxxBean() {
	}

	public DajbxxBean(long id) {
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

	public String getYwlsh() {
		return ywlsh;
	}

	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}

	public String getXgczy() {
		return xgczy;
	}

	public void setXgczy(String xgczy) {
		this.xgczy = xgczy;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getCzfs() {
		return czfs;
	}

	public void setCzfs(String czfs) {
		this.czfs = czfs;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}



	public String getTjrqBegin() {
		return TjrqBegin;
	}



	public void setTjrqBegin(String tjrqBegin) {
		TjrqBegin = tjrqBegin;
	}



	public String getTjrqEnd() {
		return TjrqEnd;
	}



	public void setTjrqEnd(String tjrqEnd) {
		TjrqEnd = tjrqEnd;
	}



	public String getJsrqBegin() {
		return JsrqBegin;
	}



	public void setJsrqBegin(String jsrqBegin) {
		JsrqBegin = jsrqBegin;
	}



	public String getJsrqEnd() {
		return JsrqEnd;
	}



	public void setJsrqEnd(String jsrqEnd) {
		JsrqEnd = jsrqEnd;
	}
	public String getPh() {
		return ph;
	}



	public void setPh(String ph) {
		this.ph = ph;
	}


}

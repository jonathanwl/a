package com.wasoft.dataserver.ws.da.bean;

public class TJReportBean {
	
	private String  str; //sql语句
	
	private String  jyr; //借阅人
	
	private String dalx; // 档案类型 Dalx Varchar 20 下拉选择
	
	private String djr; // 登记人 djr Varchar 10 页面录入
	
	private String gjd; // 归集点
	
	private String xhr; // 销毁人 djr Varchar 10 页面录入
	
	private String xhjdr; // 销毁监督人 djr Varchar 10 页面录入
	
	private String XHRQ_begin;  //销毁日期

	private String XHRQ_end;	//销毁日期
	
	private String RQ_begin;  //开始日期

	private String RQ_end;	//  结束日期
	 
	private String RQ2_begin;  //开始日期

	private String RQ2_end;	//  结束日期
	
	private String  jsrq; //归档日期
	
	private String  dw; //单位名称
	
	private String  cbwd; //承办网点
	
	private String DYR;//打印人
	
	private String DAZT;//档案状态
		

	private long id; // ID number ID
	
	private String Bcdz;//保存地址
	
	private int BCNX;//保存年限
	
	private String F_TYPE;//档案类型
	
	private String F_UPDATER_NAME;//更新人
	
	private String TJR;//提交人
	
	private String JSR;//接收人
	
	private String CFWZ;//存放位置
	
	private String ZXRQ;//注销日期
	
	private String ZXR;//注销人
	
	private String czybm;//操作员编码

	private String czy;//操作员

	private String ksrq;//开始日期

	private String JSJ;//计算机
	
	private String DWZH;//单位职工
	
	private String ZGZH;//职工账号
	
	private String XM;//项目名称
	
	private String JYRQ_begin;//借阅日期
	
	private String JYRQ_end;//借阅日期
	
    private String NHRQ_begin;//拟还日期
    
    private String NHRQ_end;//拟还日期
	
	
	


	public String getNHRQ_begin() {
		return NHRQ_begin;
	}
	public void setNHRQ_begin(String nHRQBegin) {
		NHRQ_begin = nHRQBegin;
	}
	public String getNHRQ_end() {
		return NHRQ_end;
	}
	public void setNHRQ_end(String nHRQEnd) {
		NHRQ_end = nHRQEnd;
	}
	public String getJYRQ_begin() {
		return JYRQ_begin;
	}
	public void setJYRQ_begin(String jYRQBegin) {
		JYRQ_begin = jYRQBegin;
	}
	public String getJYRQ_end() {
		return JYRQ_end;
	}
	public void setJYRQ_end(String jYRQEnd) {
		JYRQ_end = jYRQEnd;
	}
	public String getXM() {
		return XM;
	}
	public void setXM(String xM) {
		XM = xM;
	}
	public String getDWZH() {
		return DWZH;
	}
	public void setDWZH(String dWZH) {
		DWZH = dWZH;
	}
	public String getZGZH() {
		return ZGZH;
	}
	public void setZGZH(String zGZH) {
		ZGZH = zGZH;
	}
	public String getJSJ() {
		return JSJ;
	}
	public void setJSJ(String jSJ) {
		JSJ = jSJ;
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
	public String getKsrq() {
		return ksrq;
	}
	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}
	public String getCznr() {
		return cznr;
	}
	public void setCznr(String cznr) {
		this.cznr = cznr;
	}
	private String cznr;
	
	
	public String getZXRQ() {
		return ZXRQ;
	}
	public void setZXRQ(String zXRQ) {
		ZXRQ = zXRQ;
	}
	public String getZXR() {
		return ZXR;
	}
	public void setZXR(String zXR) {
		ZXR = zXR;
	}
	public String getCFWZ() {
		return CFWZ;
	}
	public void setCFWZ(String cFWZ) {
		CFWZ = cFWZ;
	}
	public String getF_TYPE() {
		return F_TYPE;
	}
	public void setF_TYPE(String fTYPE) {
		F_TYPE = fTYPE;
	}
	public String getF_UPDATER_NAME() {
		return F_UPDATER_NAME;
	}
	public void setF_UPDATER_NAME(String fUPDATERNAME) {
		F_UPDATER_NAME = fUPDATERNAME;
	}
	public String getTJR() {
		return TJR;
	}
	public void setTJR(String tJR) {
		TJR = tJR;
	}
	public String getJSR() {
		return JSR;
	}
	public void setJSR(String jSR) {
		JSR = jSR;
	}

	public int getBCNX() {
		return BCNX;
	}
	public void setBCNX(int bCNX) {
		BCNX = bCNX;
	}
	public String getDAZT() {
		return DAZT;
	}
	public void setDAZT(String dAZT) {
		DAZT = dAZT;
	}
	public String getDYR() {
		return DYR;
	}
	public void setDYR(String dYR) {
		DYR = dYR;
	}
	
	public String getBcdz() {
		return Bcdz;
	}
	public void setBcdz(String bcdz) {
		Bcdz = bcdz;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getXHRQ_begin() {
		return XHRQ_begin;
	}
	public void setXHRQ_begin(String xhrq_begin) {
		XHRQ_begin = xhrq_begin;
	}
	public String getXHRQ_end() {
		return XHRQ_end;
	}
	public void setXHRQ_end(String xhrq_end) {
		XHRQ_end = xhrq_end;
	}
	public String getJyr() {
		return jyr;
	}
	public void setJyr(String jyr) {
		this.jyr = jyr;
	}
	public String getDalx() {
		return dalx;
	}
	public void setDalx(String dalx) {
		this.dalx = dalx;
	}
	public String getDjr() {
		return djr;
	}
	public void setDjr(String djr) {
		this.djr = djr;
	}
	public String getXhr() {
		return xhr;
	}
	public void setXhr(String xhr) {
		this.xhr = xhr;
	}
	public String getXhjdr() {
		return xhjdr;
	}
	public void setXhjdr(String xhjdr) {
		this.xhjdr = xhjdr;
	}
	public String getGjd() {
		return gjd;
	}
	public void setGjd(String gjd) {
		this.gjd = gjd;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getRQ_begin() {
		return RQ_begin;
	}
	public void setRQ_begin(String rq_begin) {
		RQ_begin = rq_begin;
	}
	public String getRQ_end() {
		return RQ_end;
	}
	public void setRQ_end(String rq_end) {
		RQ_end = rq_end;
	}
	public String getRQ2_begin() {
		return RQ2_begin;
	}
	public void setRQ2_begin(String rq2_begin) {
		RQ2_begin = rq2_begin;
	}
	public String getRQ2_end() {
		return RQ2_end;
	}
	public void setRQ2_end(String rq2_end) {
		RQ2_end = rq2_end;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getCbwd() {
		return cbwd;
	}
	public void setCbwd(String cbwd) {
		this.cbwd = cbwd;
	}
	 
}
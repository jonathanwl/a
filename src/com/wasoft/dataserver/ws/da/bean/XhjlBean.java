package com.wasoft.dataserver.ws.da.bean;

public class XhjlBean extends DaBean{
	
	private Long id; //编号
	
	private String DAH;//档案号
	
	private String DALX;//档案类型
	
	private String GJDBM;//轨迹点编码
	
	private String GJD;//轨迹点
	
	private String XHRBM;//销毁人编码
	
	private String XHR;//销毁人
	
	private String XHYY;//销毁原因
	
	private String XHRQ;//销毁日期
	
	private String XHRQ2;//销毁日期
	
	private String XHJDR;//销毁监督人
	
	private String DAZT;//销毁状态
	
	private String F_STATE;
	
	private String F_CREATOR_NAME;
	
	private String F_CREATOR_ID;
	

	private String F_CREATOR_DEPT_NAME;
	
	private String F_CREATOR_DEPT_ID;
	
	private String F_CREATOR_DEPT_TIME;
	
	private String F_UPDATER_NAME;
	
	private String F_UPDATER_ID;
	
	private String F_UPDATER_TIME;
	
	private String P_ID;
	
	private String BJ;//选择打印
	
	private String tjrq_begin;
	
	private String tjrq_end;
	
	private String jsrq_begin;
	
	private String jsrq_end;
	
	private String type;
	
	private String xhrq_begin;
	
	private String xhrq_end;
	
	private int count;
	
	public String getXhrq_begin() {
		return xhrq_begin;
	}

	public void setXhrq_begin(String xhrqBegin) {
		xhrq_begin = xhrqBegin;
	}

	public String getXhrq_end() {
		return xhrq_end;
	}

	public void setXhrq_end(String xhrqEnd) {
		xhrq_end = xhrqEnd;
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

	public String getJsrq_begin() {
		return jsrq_begin;
	}

	public void setJsrq_begin(String jsrqBegin) {
		jsrq_begin = jsrqBegin;
	}

	public String getJsrq_end() {
		return jsrq_end;
	}

	public void setJsrq_end(String jsrqEnd) {
		jsrq_end = jsrqEnd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getBJ() {
		return BJ;
	}

	public void setBJ(String bJ) {
		BJ = bJ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getXHRQ2() {
		return XHRQ2;
	}

	public void setXHRQ2(String xHRQ2) {
		XHRQ2 = xHRQ2;
	}

	public String getDAH() {
		return DAH;
	}

	public void setDAH(String dAH) {
		DAH = dAH;
	}

	public String getDALX() {
		return DALX;
	}

	public void setDALX(String dALX) {
		DALX = dALX;
	}

	public String getGJDBM() {
		return GJDBM;
	}

	public void setGJDBM(String gJDBM) {
		GJDBM = gJDBM;
	}

	public String getGJD() {
		return GJD;
	}

	public void setGJD(String gJD) {
		GJD = gJD;
	}

	public String getXHRBM() {
		return XHRBM;
	}

	public void setXHRBM(String xHRBM) {
		XHRBM = xHRBM;
	}

	public String getXHR() {
		return XHR;
	}

	public void setXHR(String xHR) {
		XHR = xHR;
	}

	public String getXHYY() {
		return XHYY;
	}

	public void setXHYY(String xHYY) {
		XHYY = xHYY;
	}

	public String getXHRQ() {
		return XHRQ;
	}

	public void setXHRQ(String xHRQ) {
		XHRQ = xHRQ;
	}

	public String getXHJDR() {
		return XHJDR;
	}

	public void setXHJDR(String xHJDR) {
		XHJDR = xHJDR;
	}

	public String getDAZT() {
		return DAZT;
	}

	public void setDAZT(String dAZT) {
		DAZT = dAZT;
	}

	public String getF_STATE() {
		return F_STATE;
	}

	public void setF_STATE(String fSTATE) {
		F_STATE = fSTATE;
	}

	public String getF_CREATOR_NAME() {
		return F_CREATOR_NAME;
	}

	public void setF_CREATOR_NAME(String fCREATORNAME) {
		F_CREATOR_NAME = fCREATORNAME;
	}

	public String getF_CREATOR_ID() {
		return F_CREATOR_ID;
	}

	public void setF_CREATOR_ID(String fCREATORID) {
		F_CREATOR_ID = fCREATORID;
	}

	public String getF_CREATOR_DEPT_NAME() {
		return F_CREATOR_DEPT_NAME;
	}

	public void setF_CREATOR_DEPT_NAME(String fCREATORDEPTNAME) {
		F_CREATOR_DEPT_NAME = fCREATORDEPTNAME;
	}

	public String getF_CREATOR_DEPT_ID() {
		return F_CREATOR_DEPT_ID;
	}

	public void setF_CREATOR_DEPT_ID(String fCREATORDEPTID) {
		F_CREATOR_DEPT_ID = fCREATORDEPTID;
	}

	public String getF_CREATOR_DEPT_TIME() {
		return F_CREATOR_DEPT_TIME;
	}

	public void setF_CREATOR_DEPT_TIME(String fCREATORDEPTTIME) {
		F_CREATOR_DEPT_TIME = fCREATORDEPTTIME;
	}

	public String getF_UPDATER_NAME() {
		return F_UPDATER_NAME;
	}

	public void setF_UPDATER_NAME(String fUPDATERNAME) {
		F_UPDATER_NAME = fUPDATERNAME;
	}

	public String getF_UPDATER_ID() {
		return F_UPDATER_ID;
	}

	public void setF_UPDATER_ID(String fUPDATERID) {
		F_UPDATER_ID = fUPDATERID;
	}

	public String getF_UPDATER_TIME() {
		return F_UPDATER_TIME;
	}

	public void setF_UPDATER_TIME(String fUPDATERTIME) {
		F_UPDATER_TIME = fUPDATERTIME;
	}

	public String getP_ID() {
		return P_ID;
	}

	public void setP_ID(String pID) {
		P_ID = pID;
	}

	

}

package com.wasoft.dataserver.ws.controlcenter.bean;

/**
 * 贷款利率设置Plain Old Java Object (POJO)类
 */
public class BorrowRateDTO {

	// private short dkqx;//贷款期限
	private String dkqx;// 贷款期限

	private String kssj;// 开始执行日期

	// private double lldx ;//公积金贷款执行利率
	//
	// private double yhlldx ;//商业贷款利率

	private String lldx;// 公积金贷款执行利率

	private String yhlldx;// 商业贷款利率

	private String lllb;

	private String ksrq;// 开始执行日期

	private String jsrq;//

	private String llid;// 唯一ID号

	// private double jzlldx;//公积金贷款基准利率
	//
	// private double minlldx;//公积金贷款利率低限
	//
	// private double maxlldx ;//公积金贷款利率限

	private String jzlldx;// 公积金贷款基准利率

	private String minlldx;// 公积金贷款利率低限

	private String maxlldx;// 公积金贷款利率限

	public BorrowRateDTO(String llid) {
		super();
		this.llid = llid;
	}

	public BorrowRateDTO() {
		super();
	}

	public BorrowRateDTO(String dkqx, String kssj, String lldx, String yhlldx,
			String lllb, String ksrq, String jsrq, String jzlldx,
			String minlldx, String maxlldx) {
		super();
		this.dkqx = dkqx;
		this.kssj = kssj;
		this.lldx = lldx;
		this.yhlldx = yhlldx;
		this.lllb = lllb;
		this.ksrq = ksrq;
		this.jsrq = jsrq;
		this.jzlldx = jzlldx;
		this.minlldx = minlldx;
		this.maxlldx = maxlldx;
	}

	public String getDkqx() {
		return dkqx;
	}

	public void setDkqx(String dkqx) {
		this.dkqx = dkqx;
	}

	public String getJsrq() {
		return jsrq;
	}

	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}

	public String getJzlldx() {
		return jzlldx;
	}

	public void setJzlldx(String jzlldx) {
		this.jzlldx = jzlldx;
	}

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getLldx() {
		return lldx;
	}

	public void setLldx(String lldx) {
		this.lldx = lldx;
	}

	public String getLlid() {
		return llid;
	}

	public void setLlid(String llid) {
		this.llid = llid;
	}

	public String getLllb() {
		return lllb;
	}

	public void setLllb(String lllb) {
		this.lllb = lllb;
	}

	public String getMaxlldx() {
		return maxlldx;
	}

	public void setMaxlldx(String maxlldx) {
		this.maxlldx = maxlldx;
	}

	public String getMinlldx() {
		return minlldx;
	}

	public void setMinlldx(String minlldx) {
		this.minlldx = minlldx;
	}

	public String getYhlldx() {
		return yhlldx;
	}

	public void setYhlldx(String yhlldx) {
		this.yhlldx = yhlldx;
	}

}

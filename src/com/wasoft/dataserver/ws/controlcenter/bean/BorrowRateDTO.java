package com.wasoft.dataserver.ws.controlcenter.bean;

/**
 * ������������Plain Old Java Object (POJO)��
 */
public class BorrowRateDTO {

	// private short dkqx;//��������
	private String dkqx;// ��������

	private String kssj;// ��ʼִ������

	// private double lldx ;//���������ִ������
	//
	// private double yhlldx ;//��ҵ��������

	private String lldx;// ���������ִ������

	private String yhlldx;// ��ҵ��������

	private String lllb;

	private String ksrq;// ��ʼִ������

	private String jsrq;//

	private String llid;// ΨһID��

	// private double jzlldx;//����������׼����
	//
	// private double minlldx;//������������ʵ���
	//
	// private double maxlldx ;//���������������

	private String jzlldx;// ����������׼����

	private String minlldx;// ������������ʵ���

	private String maxlldx;// ���������������

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

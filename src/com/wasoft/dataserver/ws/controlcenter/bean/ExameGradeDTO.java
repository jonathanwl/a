package com.wasoft.dataserver.ws.controlcenter.bean;

public class ExameGradeDTO {

	private String bm;// ����

	private String mc;// ����

	private String spbl;// ���������ޱ���

	private String spsx;// ����ʱ��

	public ExameGradeDTO() {
		super();
	}

	public ExameGradeDTO(String bm) {
		super();
		this.bm = bm;
	}

	public ExameGradeDTO(String bm, String mc, String spbl, String spsx) {
		super();
		this.bm = bm;
		this.mc = mc;
		this.spbl = spbl;
		this.spsx = spsx;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getSpbl() {
		return spbl;
	}

	public void setSpbl(String spbl) {
		this.spbl = spbl;
	}

	public String getSpsx() {
		return spsx;
	}

	public void setSpsx(String spsx) {
		this.spsx = spsx;
	}

}

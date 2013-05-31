package com.wasoft.dataserver.ws.controlcenter.bean;

public class WithdrawReasonDTO {
	private String tqlx;// 提取类型

	private String bm;// 编码

	private String mc;// 名称

	private float sybj;// 剩余本金

	private int hjcs;// 汇缴次数

	private float tqbl;// 提取比例

	private String tqzq;// 提取周期

	private int xhnl;// 男销年龄

	private int xhnlnv;// 女销户年龄

	private int xztj;// 限制条件

	private int sftqsx;// 是否设定提取上限

	private int sfxyzxsp;// 是否需要中心审批

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public int getHjcs() {
		return hjcs;
	}

	public void setHjcs(int hjcs) {
		this.hjcs = hjcs;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public int getSftqsx() {
		return sftqsx;
	}

	public void setSftqsx(int sftqsx) {
		this.sftqsx = sftqsx;
	}

	public int getSfxyzxsp() {
		return sfxyzxsp;
	}

	public void setSfxyzxsp(int sfxyzxsp) {
		this.sfxyzxsp = sfxyzxsp;
	}

	public float getSybj() {
		return sybj;
	}

	public void setSybj(float sybj) {
		this.sybj = sybj;
	}

	public float getTqbl() {
		return tqbl;
	}

	public void setTqbl(float tqbl) {
		this.tqbl = tqbl;
	}

	public String getTqlx() {
		return tqlx;
	}

	public void setTqlx(String tqlx) {
		this.tqlx = tqlx;
	}

	public String getTqzq() {
		return tqzq;
	}

	public void setTqzq(String tqzq) {
		this.tqzq = tqzq;
	}

	public int getXhnl() {
		return xhnl;
	}

	public void setXhnl(int xhnl) {
		this.xhnl = xhnl;
	}

	public int getXhnlnv() {
		return xhnlnv;
	}

	public void setXhnlnv(int xhnlnv) {
		this.xhnlnv = xhnlnv;
	}

	public int getXztj() {
		return xztj;
	}

	public void setXztj(int xztj) {
		this.xztj = xztj;
	}
	
	
}

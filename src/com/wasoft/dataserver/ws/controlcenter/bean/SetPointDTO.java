package com.wasoft.dataserver.ws.controlcenter.bean;

public class SetPointDTO {

	public SetPointDTO(String bm, String mc, String txdz, String khyh,
			String yhzh, String fzr, String cwzg, String cwjz, String cwsh,
			String cwtz, String yzbm, String lxdh, String czdh, String znjl,
			String zwdate, String ygjrs) {
		super();
		this.bm = bm;
		this.mc = mc;
		this.txdz = txdz;
		this.khyh = khyh;
		this.yhzh = yhzh;
		this.fzr = fzr;
		this.cwzg = cwzg;
		this.cwjz = cwjz;
		this.cwsh = cwsh;
		this.cwtz = cwtz;
		this.yzbm = yzbm;
		this.lxdh = lxdh;
		this.czdh = czdh;
		this.znjl = znjl;
		this.zwdate = zwdate;
		this.ygjrs = ygjrs;
	}

	// private String userid;
	// private String username;
	// public String getUserid() {
	// return userid;
	// }
	// public void setUserid(String userid) {
	// this.userid = userid;
	// }
	// public String getUsername() {
	// return username;
	// }
	// public void setUsername(String username) {
	// this.username = username;
	// }

	// private String userid;
	// private String roleid;
	// private String menusub;

	private String bm;

	private String mc;

	private String txdz;

	private String khyh;

	private String yhzh;

	private String fzr;

	private String cwzg;

	private String cwjz;

	private String cwsh;

	private String cwtz;

	private String yzbm;

	private String lxdh;

	private String czdh;

	private String znjl;

	private String zwdate;

	private String ygjrs;

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getCwjz() {
		return cwjz;
	}

	public void setCwjz(String cwjz) {
		this.cwjz = cwjz;
	}

	public String getCwsh() {
		return cwsh;
	}

	public void setCwsh(String cwsh) {
		this.cwsh = cwsh;
	}

	public String getCwtz() {
		return cwtz;
	}

	public void setCwtz(String cwtz) {
		this.cwtz = cwtz;
	}

	public String getCwzg() {
		return cwzg;
	}

	public void setCwzg(String cwzg) {
		this.cwzg = cwzg;
	}

	public String getCzdh() {
		return czdh;
	}

	public void setCzdh(String czdh) {
		this.czdh = czdh;
	}

	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	public String getKhyh() {
		return khyh;
	}

	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getTxdz() {
		return txdz;
	}

	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}

	public String getYgjrs() {
		return ygjrs;
	}

	public void setYgjrs(String ygjrs) {
		this.ygjrs = ygjrs;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getZnjl() {
		return znjl;
	}

	public void setZnjl(String znjl) {
		this.znjl = znjl;
	}

	public String getZwdate() {
		return zwdate;
	}

	public void setZwdate(String zwdate) {
		this.zwdate = zwdate;
	}

	public SetPointDTO() {
		super();
	}

	public SetPointDTO(String bm) {
		super();
		this.bm = bm;
	}

}

package com.wasoft.dataserver.ws.controlcenter.bean;

public class ApplyStuffDTO {
	private String bm;// 编码

	private String mc;// 名称

	private String fwlxbm; // 房屋类型编码
	private String fwlxz; // 房屋
	private String yjfs; // 原件份数
	private String bz; // 备注
	private String cllbbm; // 材料类别编码：01-全局，02-配偶，03-外地户籍，04-委托收款,05-开发商法定代表授权，06-共同申请人，07-共同产权人，08-住房是否抵押09-卖方是否有配偶，99-其他
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCllbbm() {
		return cllbbm;
	}
	public void setCllbbm(String cllbbm) {
		this.cllbbm = cllbbm;
	}
	public String getFwlxbm() {
		return fwlxbm;
	}
	public void setFwlxbm(String fwlxbm) {
		this.fwlxbm = fwlxbm;
	}
	public String getFwlxz() {
		return fwlxz;
	}
	public void setFwlxz(String fwlxz) {
		this.fwlxz = fwlxz;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getYjfs() {
		return yjfs;
	}
	public void setYjfs(String yjfs) {
		this.yjfs = yjfs;
	}
	
	
	
}

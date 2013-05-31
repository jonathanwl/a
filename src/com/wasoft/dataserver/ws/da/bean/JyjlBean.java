package com.wasoft.dataserver.ws.da.bean;

/**
 * 电子档案系统中使用的bean,对应数据库中的ef_jyjl表,其中ID字段是主键
 * 
 * @author Yang 2010-9-19
 */
public class JyjlBean extends DaBean {

	public JyjlBean() {
	}

	private Long id; // ID Long 10 自动生成 主键

	private String dah; // 档案号 dah Varchar 20 自动生成

	private String ygdh; // 预归档号 ygdh Varchar 20 自动生成 外键

	private String dalx; // 档案类型 Dalx Varchar 20 下拉选择

	private int jyjs; // 借阅卷数 Jyjs Int 2 页面录入

	private String jybh;

	private String jyr; // 借阅人 Jyr Varchar 20 自动生成

	private String jymd; // 借阅目的 Jymd Varchar 10 页面录入

	private String jyrq; // 借阅日期 Jyrq Date 8 自动生成

	private String nhrq; // 拟还日期 Nhrq Date 8 页面录入

	private String ghrq; // 归还日期 hgrq Date 8

	private String djrbm; // 登记人编码 djrbm Varchar 10 页面录入

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

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	private String djr; // 登记人 djr Varchar 10 页面录入

	private String bcdz; // 档案保存地址 Bcdz Varchar 120 自动生成
	
	private String dash; //档案室号

	private String gh; // 柜号 Gh varchar 60 自动生成
	
	private String grow; //档案柜行
	
	private String ph;  //档案排号	

	private String hh; // 盒号 Hh varchar 60 自动生成

	private int sxh; // 顺序号 Sxh Int 5 自动生成

	private String dazt; // 档案状态 Dazt Varchar 10 自动生成

	private String chxx; // 催还信息 chxx Varchar 100 页面录入

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}

	public String getYgdh() {
		return ygdh;
	}

	public void setYgdh(String ygdh) {
		this.ygdh = ygdh;
	}

	public String getDalx() {
		return dalx;
	}

	public void setDalx(String dalx) {
		this.dalx = dalx;
	}

	public int getJyjs() {
		return jyjs;
	}

	public void setJyjs(int jyjs) {
		this.jyjs = jyjs;
	}

	public String getJybh() {
		return jybh;
	}

	public void setJybh(String jybh) {
		this.jybh = jybh;
	}

	public String getJyr() {
		return jyr;
	}

	public void setJyr(String jyr) {
		this.jyr = jyr;
	}

	public String getJymd() {
		return jymd;
	}

	public void setJymd(String jymd) {
		this.jymd = jymd;
	}

	public String getJyrq() {
		return jyrq;
	}

	public void setJyrq(String jyrq) {
		this.jyrq = jyrq;
	}

	public String getNhrq() {
		return nhrq;
	}

	public void setNhrq(String nhrq) {
		this.nhrq = nhrq;
	}

	public String getGhrq() {
		return ghrq;
	}

	public void setGhrq(String ghrq) {
		this.ghrq = ghrq;
	}

	public String getDjrbm() {
		return djrbm;
	}

	public void setDjrbm(String djrbm) {
		this.djrbm = djrbm;
	}

	public String getDjr() {
		return djr;
	}

	public void setDjr(String djr) {
		this.djr = djr;
	}

	public String getBcdz() {
		return bcdz;
	}

	public void setBcdz(String bcdz) {
		this.bcdz = bcdz;
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

	public int getSxh() {
		return sxh;
	}

	public void setSxh(int sxh) {
		this.sxh = sxh;
	}

	public String getDazt() {
		return dazt;
	}

	public void setDazt(String dazt) {
		this.dazt = dazt;
	}

	public String getChxx() {
		return chxx;
	}

	public void setChxx(String chxx) {
		this.chxx = chxx;
	}

}

package com.wasoft.dataserver.ws.da.bean;

public class XndaBean extends DaBean {

	private long id; // number(10)

	private String dash; // number(2)

	private String gh; // number(4)

	private String hh; // number(6)

	private String f_type; // varchar2(20)
	
	private int damax;
	
	private int dnum;
	
	private String bum;			//部门名称

	public String getBum() {
		return bum;
	}

	public void setBum(String bum) {
		this.bum = bum;
	}

	public int getDamax() {
		return damax;
	}

	public void setDamax(int damax) {
		this.damax = damax;
	}

	public int getDnum() {
		return dnum;
	}

	public void setDnum(int dnum) {
		this.dnum = dnum;
	}

	private String bj;
	
	private String num;	   	//获取档案盒当前档案数

	private String dashcount;

	private String ghcount;

	private String hhcount;

	private String style_dash;

	private String style_hh;

	private String style_gh_row;

	private String style_gh_column;

	private String QB;

	public String getQB() {
		return QB;
	}

	public void setQB(String qb) {
		QB = qb;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDash() {
		return dash;
	}

	public void setDash(String dash) {
		this.dash = dash;
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

	public String getF_type() {
		return f_type;
	}

	public void setF_type(String f_type) {
		this.f_type = f_type;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getDashcount() {
		return dashcount;
	}

	public void setDashcount(String dashcount) {
		this.dashcount = dashcount;
	}

	public String getGhcount() {
		return ghcount;
	}

	public void setGhcount(String ghcount) {
		this.ghcount = ghcount;
	}

	public String getHhcount() {
		return hhcount;
	}

	public void setHhcount(String hhcount) {
		this.hhcount = hhcount;
	}

	public String getStyle_dash() {
		return style_dash;
	}

	public void setStyle_dash(String style_dash) {
		this.style_dash = style_dash;
	}

	public String getStyle_hh() {
		return style_hh;
	}

	public void setStyle_hh(String style_hh) {
		this.style_hh = style_hh;
	}

	public String getStyle_gh_row() {
		return style_gh_row;
	}

	public void setStyle_gh_row(String style_gh_row) {
		this.style_gh_row = style_gh_row;
	}

	public String getStyle_gh_column() {
		return style_gh_column;
	}

	public void setStyle_gh_column(String style_gh_column) {
		this.style_gh_column = style_gh_column;
	}

}

package com.wasoft.dataserver.ws.da.bean;

public class InitDaBean extends DaBean{
	private long id;    //number(10)
	
	private int ghnum;   //档案柜号数量
	
	private int grownum;	//档案柜行数量
	
	private int  hhnum;    //档案盒号数量
	
	private int maxnum;    //每盒档案数量
	
	private int phnum;     //每柜档案排数

	public int getPhnum() {
		return phnum;
	}

	public void setPhnum(int phnum) {
		this.phnum = phnum;
	}

	public int getGhnum() {
		return ghnum;
	}

	public void setGhnum(int ghnum) {
		this.ghnum = ghnum;
	}

	public int getGrownum() {
		return grownum;
	}

	public void setGrownum(int grownum) {
		this.grownum = grownum;
	}

	public int getHhnum() {
		return hhnum;
	}

	public void setHhnum(int hhnum) {
		this.hhnum = hhnum;
	}

	public int getMaxnum() {
		return maxnum;
	}

	public void setMaxnum(int maxnum) {
		this.maxnum = maxnum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}

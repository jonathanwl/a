package com.wasoft.dataserver.ws.da.bean;

public class InitDaBean extends DaBean{
	private long id;    //number(10)
	
	private int ghnum;   //�����������
	
	private int grownum;	//������������
	
	private int  hhnum;    //�����к�����
	
	private int maxnum;    //ÿ�е�������
	
	private int phnum;     //ÿ�񵵰�����

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

package com.wasoft.dataserver.ws.da.bean;

public class JnmlBean extends DaBean {

	private long id; // 主健

	private String cl; // 材料名称

	private String ygdh; // 预归档号

	private byte[] clyx; // 材料影像

	private String fileName;
	
	private int  yjfs; // 原件份数	
	
	private int fyfs ; // 复印份数
	
	private int fyys ; // 复印页数
	
	private String zt ; // 状态
	
	private String sfqq; //是否齐全 
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public JnmlBean() {

	}

	public JnmlBean(long id, String ygdh, byte[] clyx) {
		this.id = id;
		this.ygdh = ygdh;
		this.clyx = clyx;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public byte[] getClyx() {
		return clyx;
	}

	public void setClyx(byte[] clyx) {
		this.clyx = clyx;
	}

	public String getYgdh() {
		return ygdh;
	}

	public void setYgdh(String ygdh) {
		this.ygdh = ygdh;
	}

	public int getYjfs() {
		return yjfs;
	}

	public void setYjfs(int yjfs) {
		this.yjfs = yjfs;
	}

	public int getFyfs() {
		return fyfs;
	}

	public void setFyfs(int fyfs) {
		this.fyfs = fyfs;
	}

	public int getFyys() {
		return fyys;
	}

	public void setFyys(int fyys) {
		this.fyys = fyys;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getSfqq() {
		return sfqq;
	}

	public void setSfqq(String sfqq) {
		this.sfqq = sfqq;
	}

}

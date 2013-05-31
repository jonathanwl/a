package com.wasoft.dataserver.ws.da.bean;

public class DaLxBean extends DaBean {

	private long id;
	private String name;
	private String lxbz;
	private int count;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLxbz() {
		return lxbz;
	}
	public void setLxbz(String lxbz) {
		this.lxbz = lxbz;
	}
}

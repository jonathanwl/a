package com.wasoft.dataserver.ws.da.bean;

/**
 * ÉÏ´«bean
 * 
 * @author wl
 * 
 */
public class BlobBean extends DaBean {

	private byte[] data = null;

	private String name = "";

	private String error = "";

	private String p_id;

	private long id;

	private String tableName;

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String pId) {
		p_id = pId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

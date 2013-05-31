package com.wasoft.dataserver.ws.da.bean;
/**
 * 查询无关联下拉列表公用bean。如需修改请联系author
 * @author wl
 *
 */
public class ComboxBean {
/**
 * display 为显示字段的值
 * value   为真实保存的值
 * tblName 为操作表名
 */
	private String display;
	private String value;
	private String tblName;
	public String getTblName() {
		return tblName;
	}
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}

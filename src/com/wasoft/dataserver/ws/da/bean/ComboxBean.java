package com.wasoft.dataserver.ws.da.bean;
/**
 * ��ѯ�޹��������б���bean�������޸�����ϵauthor
 * @author wl
 *
 */
public class ComboxBean {
/**
 * display Ϊ��ʾ�ֶε�ֵ
 * value   Ϊ��ʵ�����ֵ
 * tblName Ϊ��������
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

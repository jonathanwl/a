package com.wasoft.dataserver.ws.da.bean;

/**
 * ���ӵ���ϵͳ��ʹ�õ�bean,
 * 
 * @author wl 2010-9-10
 */
public class UserInfoBean extends DaBean {

	public UserInfoBean() {
	}

	private String userName;

	private String userId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
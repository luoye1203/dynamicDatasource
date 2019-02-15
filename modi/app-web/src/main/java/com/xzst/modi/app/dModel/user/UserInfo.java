package com.xzst.modi.app.dModel.user;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 730863452165463427L;

	public UserInfo() {
	}

	public UserInfo(String username, String yhxm, String yhbh, String role, String sfzh) {
		this.username = username;
		this.yhxm = yhxm;
		this.yhbh = yhbh;
		this.role = role;
		this.sfzh = sfzh;
	}

	private String username;
	private String password;
	private String yhxm;
	private String yhbh;
	private String role;
	private String sfzh;
	private String sfqy;
	private String dlzh;

	public String getDlzh() {
		return dlzh;
	}

	public void setDlzh(String dlzh) {
		this.dlzh = dlzh;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getYhxm() {
		return yhxm;
	}

	public void setYhxm(String yhxm) {
		this.yhxm = yhxm;
	}

	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", yhxm='" + yhxm + '\'' +
				", yhbh='" + yhbh + '\'' +
				'}';
	}
}
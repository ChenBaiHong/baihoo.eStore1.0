package com.walkerChen.estore.bean.substance;

import java.sql.Timestamp;


public class User {
	private String id; // 用户id
	private String username; //用户名
	private String password; // 密码
	private String nickname; // 昵称
	private String email; // 邮箱
	private String cellphone;//联系电话
	private String address;//联系地址
	private boolean state; // 是否激活
	private String activecode; // 激活码UUID获取
	private Timestamp updatetime; // 更新时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getActivecode() {
		return activecode;
	}
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public User(String id, String username, String password, String nickname,
			String email, String cellphone, String address, boolean state,
			String activecode, Timestamp updatetime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.cellphone = cellphone;
		this.address = address;
		this.state = state;
		this.activecode = activecode;
		this.updatetime = updatetime;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + ", email=" + email
				+ ", cellphone=" + cellphone + ", address=" + address
				+ ", state=" + state + ", activecode=" + activecode
				+ ", updatetime=" + updatetime + "]";
	}

	
}

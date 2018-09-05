package com.yzgs.domain;

import java.io.Serializable;

/**
 * 便利店销售管理员账号实体
 * @author lenovo
 * 下午9:59:18
 */
public class Manage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;//主键

	private String name;//管理员名字
	
	private String account;//管理员账号
	
	private String password;//管理员密码
	
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Manage [id=" + id + ", name=" + name + ", account=" + account
				+ ", password=" + password + "]";
	}
	
	

}

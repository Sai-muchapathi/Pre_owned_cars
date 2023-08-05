package com.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "buyer")
public class Buyer {
	
	@Id
	String id;
	String buyerId;
	String name;
	String address;
	String mobile;
	String emailId;
	String password;

	public Buyer() {
		super();
	}

	public Buyer(String id, String buyerId, String name, String address, String mobile, String emailId,
			String password) {
		this.id = id;
		this.buyerId = buyerId;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.emailId = emailId;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Buyer [id=" + id + ", buyerId=" + buyerId + ", name=" + name + ", address=" + address + ", mobile="
				+ mobile + ", emailId=" + emailId + ", password=" + password + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}

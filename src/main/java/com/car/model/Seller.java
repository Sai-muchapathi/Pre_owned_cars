package com.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seller")
public class Seller {
	
	@Id
	String id;
	String sellerId;
	String name;
	String address;
	String mobile;
	String emailId;
	String password;

	public Seller() {
		super();
	}

	public Seller(String id, String sellerId, String name, String address, String mobile, String emailId,
			String password) {
		this.id = id;
		this.sellerId = sellerId;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.emailId = emailId;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", sellerId=" + sellerId + ", name=" + name + ", address=" + address + ", mobile="
				+ mobile + ", emailId=" + emailId + ", password=" + password + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
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

package com.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment")
public class Payment {
	
	@Id
	String id;
	String paymentId;
	String carId;
	String sellerId;
	String buyerId;
	String bookingDate;
	double amount;
	double bookingAmount;
	double balanceAmount;
	
	String paymentMode;
	String paymentStatus;
	String status;
	String settlementStatus;
	String cardNo;
	String cvv;
	String expDate;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(String id, String paymentId, String carId, String sellerId, String buyerId, String bookingDate,
			double amount, double bookingAmount, double balanceAmount, String paymentMode, String paymentStatus,
			String status, String settlementStatus, String cardNo, String cvv, String expDate) {
		super();
		this.id = id;
		this.paymentId = paymentId;
		this.carId = carId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.bookingDate = bookingDate;
		this.amount = amount;
		this.bookingAmount = bookingAmount;
		this.balanceAmount = balanceAmount;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
		this.status = status;
		this.settlementStatus = settlementStatus;
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.expDate = expDate;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", paymentId=" + paymentId + ", carId=" + carId + ", sellerId=" + sellerId
				+ ", buyerId=" + buyerId + ", bookingDate=" + bookingDate + ", amount=" + amount + ", bookingAmount="
				+ bookingAmount + ", balanceAmount=" + balanceAmount + ", paymentMode=" + paymentMode
				+ ", paymentStatus=" + paymentStatus + ", status=" + status + ", settlementStatus=" + settlementStatus
				+ ", cardNo=" + cardNo + ", cvv=" + cvv + ", expDate=" + expDate + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(double bookingAmount) {
		this.bookingAmount = bookingAmount;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSettlementStatus() {
		return settlementStatus;
	}

	public void setSettlementStatus(String settlementStatus) {
		this.settlementStatus = settlementStatus;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	

}

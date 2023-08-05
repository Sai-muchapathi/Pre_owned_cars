package com.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "settlement")
public class Settlement {

    @Id
    String id;
    String paymentId;
    String sellerId;
    String payDate;
    double saleAmount;
    double commissionPercentage;
    double commissionAmount;
    String settlementStatus;

    public Settlement() {
        super();
    }

    public Settlement(String id, String paymentId, String sellerId, String payDate, double saleAmount, double commissionPercentage, double commissionAmount, String settlementStatus) {
        this.id = id;
        this.paymentId = paymentId;
        this.sellerId = sellerId;
        this.payDate = payDate;
        this.saleAmount = saleAmount;
        this.commissionPercentage = commissionPercentage;
        this.commissionAmount = commissionAmount;
        this.settlementStatus = settlementStatus;
    }

    @Override
    public String toString() {
        return "Settlement{" +
                "id='" + id + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", payDate='" + payDate + '\'' +
                ", saleAmount=" + saleAmount +
                ", commissionPercentage=" + commissionPercentage +
                ", commissionAmount=" + commissionAmount +
                ", settlementStatus='" + settlementStatus + '\'' +
                '}';
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

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }
}

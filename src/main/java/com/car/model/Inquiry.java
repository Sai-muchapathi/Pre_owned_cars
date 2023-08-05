package com.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inquiries")
public class Inquiry {

    @Id
    String id;
    String carId;
    String buyerId;
    String sellerId;
    String inquiry;
    String response;
    String inquiryDate;
    String responseDate;

    public Inquiry() {
    }

    public Inquiry(String id, String carId, String buyerId, String sellerId, String inquiry, String response, String inquiryDate, String responseDate) {
        this.id = id;
        this.carId = carId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.inquiry = inquiry;
        this.response = response;
        this.inquiryDate = inquiryDate;
        this.responseDate = responseDate;
    }

    @Override
    public String toString() {
        return "Inquiry{" +
                "id='" + id + '\'' +
                ", carId='" + carId + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", inquiry='" + inquiry + '\'' +
                ", response='" + response + '\'' +
                ", inquiryDate='" + inquiryDate + '\'' +
                ", responseDate='" + responseDate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(String inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }
}

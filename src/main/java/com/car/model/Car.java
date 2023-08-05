package com.car.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "car")
public class Car {

    @Id
    String id;
    String categoryId;
    Category category;
    String carId;
    String sellerId;
    String brand;
    String model;
    String regYear;
    String regNumber;
    String engineCapacity;
    String transmission;
    String kmDriven;
    String fuelType;

    String state;

    String vinNumber;
    int noOfOwner;
    double basePrice;
    String description;
    String imgUrl;
    double commissionPercentage;
    String postedDate;
    boolean isApproved = false;
    boolean isSold = false;
    boolean isBooked = false;
    String paymentStatus;

    public Car() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Car(String id, String categoryId, Category category, String carId, String sellerId, String brand, String state, String model, String regYear, String regNumber, String engineCapacity, String transmission, String kmDriven, String fuelType, String vinNumber, int noOfOwner, double basePrice, String description, String imgUrl, double commissionPercentage, String postedDate, boolean isApproved, boolean isSold, boolean isBooked, String paymentStatus) {
        this.id = id;
        this.categoryId = categoryId;
        this.category = category;
        this.carId = carId;
        this.sellerId = sellerId;
        this.brand = brand;
        this.model = model;
        this.state = state;
        this.regYear = regYear;
        this.regNumber = regNumber;
        this.engineCapacity = engineCapacity;
        this.transmission = transmission;
        this.kmDriven = kmDriven;
        this.fuelType = fuelType;
        this.vinNumber = vinNumber;
        this.noOfOwner = noOfOwner;
        this.basePrice = basePrice;
        this.description = description;
        this.imgUrl = imgUrl;
        this.commissionPercentage = commissionPercentage;
        this.postedDate = postedDate;
        this.isApproved = isApproved;
        this.isSold = isSold;
        this.isBooked = isBooked;
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", category=" + category +
                ", carId='" + carId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", regYear='" + regYear + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", transmission='" + transmission + '\'' +
                ", kmDriven='" + kmDriven + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", vinNumber='" + vinNumber + '\'' +
                ", noOfOwner=" + noOfOwner +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", commissionPercentage=" + commissionPercentage +
                ", postedDate='" + postedDate + '\'' +
                ", isApproved=" + isApproved +
                ", isSold=" + isSold +
                ", isBooked=" + isBooked +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getState() {
        return state;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegYear() {
        return regYear;
    }

    public void setRegYear(String regYear) {
        this.regYear = regYear;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(String kmDriven) {
        this.kmDriven = kmDriven;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public int getNoOfOwner() {
        return noOfOwner;
    }

    public void setNoOfOwner(int noOfOwner) {
        this.noOfOwner = noOfOwner;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

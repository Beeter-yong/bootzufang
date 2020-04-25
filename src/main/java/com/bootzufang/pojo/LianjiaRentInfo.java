package com.bootzufang.pojo;

import java.util.Date;

public class LianjiaRentInfo {
    private Integer id;
    private String houseUrl;
    private String price;
    private String area;
    private String houseType;
    private String towards;
    private String address;
    private String regional;
    private String timeLast;
    private Date timeNew;
    private String shopping;
    private String community;
    private String floor;
    private String rentImg;

    @Override
    public String toString() {
        return "LianjiaRentInfo{" +
                "id=" + id +
                ", houseUrl='" + houseUrl + '\'' +
                ", price='" + price + '\'' +
                ", area='" + area + '\'' +
                ", houseType='" + houseType + '\'' +
                ", towards='" + towards + '\'' +
                ", address='" + address + '\'' +
                ", regional='" + regional + '\'' +
                ", timeLast='" + timeLast + '\'' +
                ", timeNew=" + timeNew +
                ", shopping='" + shopping + '\'' +
                ", community='" + community + '\'' +
                ", floor='" + floor + '\'' +
                ", rentImg='" + rentImg + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public LianjiaRentInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getHouseUrl() {
        return houseUrl;
    }

    public LianjiaRentInfo setHouseUrl(String houseUrl) {
        this.houseUrl = houseUrl;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public LianjiaRentInfo setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getArea() {
        return area;
    }

    public LianjiaRentInfo setArea(String area) {
        this.area = area;
        return this;
    }

    public String getHouseType() {
        return houseType;
    }

    public LianjiaRentInfo setHouseType(String houseType) {
        this.houseType = houseType;
        return this;
    }

    public String getTowards() {
        return towards;
    }

    public LianjiaRentInfo setTowards(String towards) {
        this.towards = towards;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LianjiaRentInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getRegional() {
        return regional;
    }

    public LianjiaRentInfo setRegional(String regional) {
        this.regional = regional;
        return this;
    }

    public String getTimeLast() {
        return timeLast;
    }

    public LianjiaRentInfo setTimeLast(String timeLast) {
        this.timeLast = timeLast;
        return this;
    }

    public Date getTimeNew() {
        return timeNew;
    }

    public LianjiaRentInfo setTimeNew(Date timeNew) {
        this.timeNew = timeNew;
        return this;
    }

    public String getShopping() {
        return shopping;
    }

    public LianjiaRentInfo setShopping(String shopping) {
        this.shopping = shopping;
        return this;
    }

    public String getCommunity() {
        return community;
    }

    public LianjiaRentInfo setCommunity(String community) {
        this.community = community;
        return this;
    }

    public String getFloor() {
        return floor;
    }

    public LianjiaRentInfo setFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public String getRentImg() {
        return rentImg;
    }

    public LianjiaRentInfo setRentImg(String rentImg) {
        this.rentImg = rentImg;
        return this;
    }
}

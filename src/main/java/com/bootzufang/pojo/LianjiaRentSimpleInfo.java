package com.bootzufang.pojo;

import java.util.Date;

public class LianjiaRentSimpleInfo {
    private Integer id;
    private String price;
    private String area;
    private String address;

    @Override
    public String toString() {
        return "LianjiaRentSimpleInfo{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public LianjiaRentSimpleInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public LianjiaRentSimpleInfo setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getArea() {
        return area;
    }

    public LianjiaRentSimpleInfo setArea(String area) {
        this.area = area;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LianjiaRentSimpleInfo setAddress(String address) {
        this.address = address;
        return this;
    }
}

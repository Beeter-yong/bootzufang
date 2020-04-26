package com.bootzufang.pojo;

import java.util.Date;

public class LianjiaRentSimpleInfo {
    private Integer id;
    private String price;
    private String area;
    private String community;

    @Override
    public String toString() {
        return "LianjiaRentSimpleInfo{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", area='" + area + '\'' +
                ", community='" + community + '\'' +
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

    public String getCommunity() {
        return community;
    }

    public LianjiaRentSimpleInfo setCommunity(String community) {
        this.community = community;
        return this;
    }
}

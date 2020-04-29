package com.bootzufang.pojo;

public class Matrix {
    private int id;
    private Double price;
    private Double area;
    private Double distance;

    @Override
    public String toString() {
        return "Matrix{" +
                "id=" + id +
                ", price=" + price +
                ", area=" + area +
                ", distance=" + distance +
                '}';
    }

    public int getId() {
        return id;
    }

    public Matrix setId(int id) {
        this.id = id;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Matrix setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getArea() {
        return area;
    }

    public Matrix setArea(Double area) {
        this.area = area;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public Matrix setDistance(Double distance) {
        this.distance = distance;
        return this;
    }
}

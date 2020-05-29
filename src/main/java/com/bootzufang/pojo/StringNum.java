package com.bootzufang.pojo;

public class StringNum {
    private String name;
    private Integer num;

    @Override
    public String toString() {
        return "StringNum{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }

    public String getName() {
        return name;
    }

    public StringNum setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public StringNum setNum(Integer num) {
        this.num = num;
        return this;
    }
}

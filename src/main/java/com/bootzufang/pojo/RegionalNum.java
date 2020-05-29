package com.bootzufang.pojo;

public class  RegionalNum {
    private String regional;
    private Integer num;

    @Override
    public String toString() {
        return "RegionalNum{" +
                "regional='" + regional + '\'' +
                ", num=" + num +
                '}';
    }

    public String getRegional() {
        return regional;
    }

    public RegionalNum setRegional(String regional) {
        this.regional = regional;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public RegionalNum setNum(Integer num) {
        this.num = num;
        return this;
    }
}

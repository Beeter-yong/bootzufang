package com.bootzufang.pojo;

public class SortResult {
    private int id;
    private double score;

    @Override
    public String toString() {
        return "SortResult{" +
                "id=" + id +
                ", score=" + score +
                '}';
    }

    public int getId() {
        return id;
    }

    public SortResult setId(int id) {
        this.id = id;
        return this;
    }

    public double getScore() {
        return score;
    }

    public SortResult setScore(double score) {
        this.score = score;
        return this;
    }
}

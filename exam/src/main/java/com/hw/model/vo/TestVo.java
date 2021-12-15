package com.hw.model.vo;

public class TestVo {
    private Integer id;
    private String exam_title;
    private double point;
    private double count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExam_title() {
        return exam_title;
    }

    public void setExam_title(String exam_title) {
        this.exam_title = exam_title;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TestVo{" +
                "id=" + id +
                ", exam_title='" + exam_title + '\'' +
                ", point=" + point +
                ", count=" + count +
                '}';
    }
}

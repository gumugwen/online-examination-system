package com.hw.model;

import java.util.Date;

public class ExamResult {
    private Integer id;

    private Double point;

    private Date startTime;

    private Date endTime;

    private String examTitle;

    private Integer examId;

    private Integer studentId;

    private Integer delFlag;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "id=" + id +
                ", point=" + point +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", examTitle='" + examTitle + '\'' +
                ", examId=" + examId +
                ", studentId=" + studentId +
                ", delFlag=" + delFlag +
                ", status=" + status +
                '}';
    }
}
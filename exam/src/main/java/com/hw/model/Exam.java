package com.hw.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class Exam {
    private Integer id;

    @NotNull(message = "考卷名不能为空")
    private String title;
    @NotNull(message = "时长不能为空")
    @Min(value = 1,message = "考试时长必须大于0")
    private Integer timeLimit;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @NotNull(message = "请选中开始时间")
    private Date startTime;

    private Integer status;

    private Integer teacherId;

    @NotNull(message = "单选分数不能为空")
    private Double singlePoints;
    @NotNull(message = "多选分数不能为空")
    private Double multiPoints;
    @NotNull(message = "判断分数不能为空")
    private Double judgePoints;

    private Integer valid;

    /**
     * timezone="GMT+8" 如果不设置 后端接到的比前端传来的多8小时
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @NotNull(message = "请选择开始时间")
    private Date endTime;

    @NotNull(message = "请选中考卷类型")
    private Integer type;

    private String classIds;


    @Transient
    private List<Integer>classIdsList;
    @Transient
    private List<String> classNames;

    public List<Integer> getClassIdsList() {
        return classIdsList;
    }

    public void setClassIdsList(List<Integer> classIdsList) {
        this.classIdsList = classIdsList;
    }

    public List<String> getClassNames() {
        return classNames;
    }

    public void setClassNames(List<String> classNames) {
        this.classNames = classNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Double getSinglePoints() {
        return singlePoints;
    }

    public void setSinglePoints(Double singlePoints) {
        this.singlePoints = singlePoints;
    }

    public Double getMultiPoints() {
        return multiPoints;
    }

    public void setMultiPoints(Double multiPoints) {
        this.multiPoints = multiPoints;
    }

    public Double getJudgePoints() {
        return judgePoints;
    }

    public void setJudgePoints(Double judgePoints) {
        this.judgePoints = judgePoints;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getClassIds() {
        return classIds;
    }

    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", timeLimit=" + timeLimit +
                ", startTime=" + startTime +
                ", status=" + status +
                ", teacherId=" + teacherId +
                ", singlePoints=" + singlePoints +
                ", multiPoints=" + multiPoints +
                ", judgePoints=" + judgePoints +
                ", valid=" + valid +
                ", endTime=" + endTime +
                ", type=" + type +
                ", classIds='" + classIds + '\'' +
                '}';
    }
}
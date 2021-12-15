package com.hw.model.vo;


import java.util.Date;

public class ExamListForStudentVo {
    private Integer id;

    private String title;

    private Integer time_limit;

    private Date start_time;

    private Integer status;

    /**
     * 教师姓名
     */
    private String username;

    /**
     * 单选题分数
     */
    private double single_points;

    /**
     * 多选题分数
     */
    private double multi_points;

    /**
     * 判断题分数
     */
    private double judge_points;

    private Date end_time;

    private Integer valid;

    /**
     * 1、开始时间立即考试
     * 2、在开始时间到结束时间中的任意时段即可
     */
    private Integer type;

    private String Status;


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

    public Integer getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(Integer time_limit) {
        this.time_limit = time_limit;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getSingle_points() {
        return single_points;
    }

    public void setSingle_points(double single_points) {
        this.single_points = single_points;
    }

    public double getMulti_points() {
        return multi_points;
    }

    public void setMulti_points(double multi_points) {
        this.multi_points = multi_points;
    }

    public double getJudge_points() {
        return judge_points;
    }

    public void setJudge_points(double judge_points) {
        this.judge_points = judge_points;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ExamListForStudentVo(){

    }

    @Override
    public String toString() {
        return "ExamListForStudentVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time_limit=" + time_limit +
                ", start_time=" + start_time +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", single_points=" + single_points +
                ", multi_points=" + multi_points +
                ", judge_points=" + judge_points +
                ", end_time=" + end_time +
                ", valid=" + valid +
                ", type=" + type +
                '}';
    }
}

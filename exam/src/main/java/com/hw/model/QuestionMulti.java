package com.hw.model;

import javax.persistence.Transient;

public class QuestionMulti {
    private Integer id;

    private String title;

    private String optiona;

    private String optionb;

    private String optionc;

    private String optiond;

    private String answer;

    private Double score;

    private Integer fkTeacher;

    @Transient
    private String teacherName;

    private Integer examId;

    private Boolean delFlag;

    public QuestionMulti() {
    }

        public QuestionMulti(String title, String optiona, String optionb, String optionc, String optiond, String answer, Double score, Integer fkTeacher, Integer examId, Boolean delFlag) {
        this.title = title;
        this.optiona = optiona;
        this.optionb = optionb;
        this.optionc = optionc;
        this.optiond = optiond;
        this.answer = answer;
        this.score = score;
        this.fkTeacher = fkTeacher;
        this.examId = examId;
        this.delFlag = delFlag;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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
        this.title = title == null ? null : title.trim();
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona == null ? null : optiona.trim();
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb == null ? null : optionb.trim();
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc == null ? null : optionc.trim();
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond == null ? null : optiond.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getFkTeacher() {
        return fkTeacher;
    }

    public void setFkTeacher(Integer fkTeacher) {
        this.fkTeacher = fkTeacher;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "QuestionMulti{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", optiona='" + optiona + '\'' +
                ", optionb='" + optionb + '\'' +
                ", optionc='" + optionc + '\'' +
                ", optiond='" + optiond + '\'' +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                ", fkTeacher=" + fkTeacher +
                ", teacherName='" + teacherName + '\'' +
                ", examId=" + examId +
                ", delFlag=" + delFlag +
                '}';
    }
}
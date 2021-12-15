package com.hw.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import javax.persistence.Transient;

@ContentRowHeight(24)
@HeadRowHeight(30)
@ColumnWidth(25)
public class QuestionJudge {
    @ExcelIgnore
    private Integer id;

    @ExcelProperty("题目")
    private String title;

    @ExcelProperty("答案")
    private Boolean answer;

    @ExcelProperty("分数")
    private Double score;

    @ExcelIgnore
    private Integer fkTeacher;

    @ExcelIgnore
    @Transient
    private String teacherName;

    @ExcelIgnore
    private Integer examId;

    @ExcelIgnore
    private Boolean delFlag;

    public QuestionJudge() {
    }

    public QuestionJudge(String title, Boolean answer, Double score, Integer fkTeacher, Integer examId, Boolean delFlag) {
        this.title = title;
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

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
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
        return "QuestionJudge{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", answer=" + answer +
                ", score=" + score +
                ", fkTeacher=" + fkTeacher +
                ", teacherName='" + teacherName + '\'' +
                ", examId=" + examId +
                ", delFlag=" + delFlag +
                '}';
    }
}
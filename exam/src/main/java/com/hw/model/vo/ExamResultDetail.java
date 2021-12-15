package com.hw.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 考试结果详情
 *
 * @author  
 * @date 2020/11/21 2:31 下午
 */
public class ExamResultDetail {
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    private String studentName;
    /**
     * 得分
     */
    private Double score;
    /**
     * 考卷总分
     */
    private Double totalScore;
    private Double singlePoints;
    private Double multiPotints;
    private Double judgePoints;
    private List<QuestionDetailsDto> singleDetailList;
    private List<QuestionDetailsDto> multiDetailList;
    private List<QuestionDetailsDto> judgeDetailList;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getSinglePoints() {
        return singlePoints==null?0.0d:singlePoints;
    }

    public void setSinglePoints(Double singlePoints) {
        this.singlePoints = singlePoints;
    }

    public Double getMultiPotints() {
        return multiPotints==null?0.0d:multiPotints;
    }

    public void setMultiPotints(Double multiPotints) {
        this.multiPotints = multiPotints;
    }

    public Double getJudgePoints() {
        return judgePoints==null?0.0d:judgePoints;
    }

    public void setJudgePoints(Double judgePoints) {
        this.judgePoints = judgePoints;
    }


    public List<QuestionDetailsDto> getSingleDetailList() {
        return singleDetailList;
    }

    public void setSingleDetailList(List<QuestionDetailsDto> singleDetailList) {
        this.singleDetailList = singleDetailList;
    }

    public List<QuestionDetailsDto> getMultiDetailList() {
        return multiDetailList;
    }

    public void setMultiDetailList(List<QuestionDetailsDto> multiDetailList) {
        this.multiDetailList = multiDetailList;
    }

    public List<QuestionDetailsDto> getJudgeDetailList() {
        return judgeDetailList;
    }

    public void setJudgeDetailList(List<QuestionDetailsDto> judgeDetailList) {
        this.judgeDetailList = judgeDetailList;
    }
}

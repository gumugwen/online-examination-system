package com.hw.model.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.hw.common.convert.JudgeAnswerConvert;
import com.hw.common.convert.QuestionTypeConverter;

/**
 * 添加问题
 * @author  
 * @date 2020/11/19 8:02 下午
 */
@ContentRowHeight(24)
@HeadRowHeight(30)
@ColumnWidth(25)
public class QuestionVo {
    @ExcelIgnore
    private Integer examId;
    @ExcelProperty(value = "类型",converter = QuestionTypeConverter.class)
    private Integer type;
    @ExcelProperty("题目")
    private String title;
    @ExcelIgnore
    private Integer titleId;
    /**
     * 判断答案
     */
    @ExcelProperty(value = "判断题答案",converter = JudgeAnswerConvert.class)
    private Integer judgeAnswer;

    @ExcelProperty("选项A")
    private String optionA;
    @ExcelProperty("选项B")
    private String optionB;
    @ExcelProperty("选项C")
    private String optionC;
    @ExcelProperty("选项D")
    private String optionD;

    @ExcelProperty("分数")
    private Double score;

    @ExcelProperty("答案")
    private String singleMultiAnswer;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }


    @Override
    public String toString() {
        return "QuestionVo{" +
                "examId=" + examId +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", titleId=" + titleId +
                ", judgeAnswer=" + judgeAnswer +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", score=" + score +
                ", singleMultiAnswer='" + singleMultiAnswer + '\'' +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getJudgeAnswer() {
        return judgeAnswer;
    }

    public void setJudgeAnswer(Integer judgeAnswer) {
        this.judgeAnswer = judgeAnswer;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSingleMultiAnswer() {
        return singleMultiAnswer;
    }

    public void setSingleMultiAnswer(String singleMultiAnswer) {
        this.singleMultiAnswer = singleMultiAnswer;
    }
}

package com.hw.model;

public class ExamResultQuestion {
    private Integer id;

    private Boolean right;

    private String wrongAnswer;

    private String rightAnswer;

    private Double score;

    private Integer examResultId;

    private Integer questionId;

    private Integer qtype;

    private Boolean delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getRight() {
        return right;
    }

    public void setRight(Boolean right) {
        this.right = right;
    }

    public String getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(String wrongAnswer) {
        this.wrongAnswer = wrongAnswer == null ? null : wrongAnswer.trim();
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer == null ? null : rightAnswer.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getExamResultId() {
        return examResultId;
    }

    public void setExamResultId(Integer examResultId) {
        this.examResultId = examResultId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQtype() {
        return qtype;
    }

    public void setQtype(Integer qtype) {
        this.qtype = qtype;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "ExamResultQuestion{" +
                "id=" + id +
                ", right=" + right +
                ", wrongAnswer='" + wrongAnswer + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", score=" + score +
                ", examResultId=" + examResultId +
                ", questionId=" + questionId +
                ", qtype=" + qtype +
                ", delFlag=" + delFlag +
                '}';
    }
}
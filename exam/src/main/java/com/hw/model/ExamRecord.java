package com.hw.model;

public class ExamRecord {
    private Integer id;

    private Integer resultId;

    private Integer questionsNumber;

    private String answer;

    private Integer qtype;

    private Integer examId;

    private Integer questionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(Integer questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getQtype() {
        return qtype;
    }

    public void setQtype(Integer qtype) {
        this.qtype = qtype;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "ExamRecord{" +
                "id=" + id +
                ", resultId=" + resultId +
                ", questionsNumber=" + questionsNumber +
                ", answer='" + answer + '\'' +
                ", qtype=" + qtype +
                ", examId=" + examId +
                ", questionId=" + questionId +
                '}';
    }
}
package com.hw.model;

public class SingleRecord {
    //学生提交答案的id
    private Integer id;
    //学生提交答案的题号
    private Integer question_number;
    //学生提交的答案
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(Integer question_number) {
        this.question_number = question_number;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "SingleRecord{" +
                "id=" + id +
                ", question_number=" + question_number +
                ", answer='" + answer + '\'' +
                '}';
    }
}

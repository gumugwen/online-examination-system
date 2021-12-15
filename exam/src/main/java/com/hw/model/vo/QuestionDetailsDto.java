package com.hw.model.vo;

/**
 * 问题作答结果详情
 * @author  
 * @date 2020/11/21 3:18 下午
 */
public class QuestionDetailsDto {

    private String title;


    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    /**
     * 试题分数
     */
    private Double score;
    /**
     * 得分
     */
    private Double inScore;

    /**
     * 是否正确
     */
    private Boolean right;

    private String wrongAnswer;

    private String rightAnswer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Double getInScore() {
        return inScore;
    }

    public void setInScore(Double inScore) {
        this.inScore = inScore;
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
        this.wrongAnswer = wrongAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}

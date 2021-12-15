package com.hw.model.vo;



public class SingleQuestionResultVo {
    private Integer id;
    private Integer result_id;
    private Integer questions_number;
    private String record_answer;
    private Integer qtype;
    private Integer exam_id;
    private Integer question_id;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correct_answer;
    private Double question_score;
    private boolean right;
    private Double get_score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResult_id() {
        return result_id;
    }

    public void setResult_id(Integer result_id) {
        this.result_id = result_id;
    }

    public Integer getQuestions_number() {
        return questions_number;
    }

    public void setQuestions_number(Integer questions_number) {
        this.questions_number = questions_number;
    }

    public String getRecord_answer() {
        return record_answer;
    }

    public void setRecord_answer(String record_answer) {
        this.record_answer = record_answer;
    }

    public Integer getQtype() {
        return qtype;
    }

    public void setQtype(Integer qtype) {
        this.qtype = qtype;
    }

    public Integer getExam_id() {
        return exam_id;
    }

    public void setExam_id(Integer exam_id) {
        this.exam_id = exam_id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

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

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public Double getQuestion_score() {
        return question_score;
    }

    public void setQuestion_score(Double question_score) {
        this.question_score = question_score;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public Double getGet_score() {
        return get_score;
    }

    public void setGet_score(Double get_score) {
        this.get_score = get_score;
    }

    @Override
    public String toString() {
        return "SingleQuestionResultVo{" +
                "id=" + id +
                ", result_id=" + result_id +
                ", questions_number=" + questions_number +
                ", record_answer='" + record_answer + '\'' +
                ", qtype=" + qtype +
                ", exam_id=" + exam_id +
                ", question_id=" + question_id +
                ", title='" + title + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", correct_answer='" + correct_answer + '\'' +
                ", question_score=" + question_score +
                ", right=" + right +
                ", get_score=" + get_score +
                '}';
    }
}

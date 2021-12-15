package com.hw.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionAllTypeRecord {
    private List<SingleRecord> singleRecordList;
    private List<MultiRecord> multiRecordList;
    private List<JudgeRecord> judgeRecordList;

    public List<SingleRecord> getSingleRecordList() {
        return singleRecordList;
    }

    public void setSingleRecordList(List<SingleRecord> singleRecordList) {
        this.singleRecordList = singleRecordList;
    }

    public List<MultiRecord> getMultiRecordList() {
        return multiRecordList;
    }

    public void setMultiRecordList(List<MultiRecord> multiRecordList) {
        this.multiRecordList = multiRecordList;
    }

    public List<JudgeRecord> getJudgeRecordList() {
        return judgeRecordList;
    }

    public void setJudgeRecordList(List<JudgeRecord> judgeRecordList) {
        this.judgeRecordList = judgeRecordList;
    }

    @Override
    public String toString() {
        return "QuestionAllTypeRecord{" +
                "singleRecordList=" + singleRecordList +
                ", multiRecordList=" + multiRecordList +
                ", judgeRecordList=" + judgeRecordList +
                '}';
    }
}

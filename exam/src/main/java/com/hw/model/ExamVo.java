package com.hw.model;

import java.util.List;

/**
 * @author  
 * @date 2020/11/19 12:14 下午
 */
public class ExamVo extends Exam{

    List<String> classNameList;
    List<Integer> classIdList;

    public List<String> getClassNameList() {
        return classNameList;
    }

    public void setClassNameList(List<String> classNameList) {
        this.classNameList = classNameList;
    }

    public List<Integer> getClassIdList() {
        return classIdList;
    }

    public void setClassIdList(List<Integer> classIdList) {
        this.classIdList = classIdList;
    }

    @Override
    public String toString() {
        return "ExamVo{" +
                "classNameList=" + classNameList +
                ", classIdList=" + classIdList +
                '}';
    }
}

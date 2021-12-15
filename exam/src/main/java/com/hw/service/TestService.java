package com.hw.service;

import com.hw.mapper.ExamMapper;
import com.hw.mapper.ExamResultMapper;
import com.hw.model.Exam;
import com.hw.model.ExamResult;
import com.hw.model.Test;
import com.hw.model.vo.TestVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ExamResultMapper examResultMapper;
    @Autowired
    private StudentService studentService;

    /**
     * 待考数量
     * @return
     */
    public int ExamUoDo(){
        List<Exam> examList = new ArrayList<>();
        examList = examMapper.selectExamUndo();
        return examList.size();
    }

    /**
     * 已做数量
     * @return
     */
    public int ExamDo(){
        List<Exam> examList = new ArrayList<>();
        examList = examMapper.selectExamDo();
        return examList.size();
    }

    /**
     * 最低得分
     * @return
     */
    public int minPoint(Integer student_id){
        int count = 0;
        try{
            count = examResultMapper.selectMinPoint(student_id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return count;
        }
    }

    /**
     * 最高得分
     * @return
     */
    public int MaxPoint(Integer student_id){
        int count = 0;
        try{
            count = examResultMapper.selectMaxPoint(student_id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return count;
        }
    }

    /**
     * 返回数据
     * @param student_id
     * @return
     */
    public List<TestVo> selectExamByStudentId(Integer student_id){
        List<TestVo> testList = new ArrayList<>();
        System.out.println(examResultMapper.selectExamByStudentId(student_id).size());
        for(ExamResult e : examResultMapper.selectExamByStudentId(student_id)){
            int exam_id = e.getExamId();
            Exam exam = studentService.getExamInformation(exam_id);
            double single_count = studentService.getSingleCount(exam_id)*exam.getSinglePoints();
            double judge_count = studentService.getJudgeCount(exam_id)*exam.getJudgePoints();
            double multi_count = studentService.getMultiCount(exam_id)*exam.getMultiPoints();
            double count = single_count+judge_count+multi_count;
            TestVo testVo = new TestVo();
            testVo.setId(e.getId());
            testVo.setExam_title(e.getExamTitle());
            testVo.setPoint(e.getPoint());
            testVo.setCount(count);
            testList.add(testVo);
        }
        return testList;
    }
}

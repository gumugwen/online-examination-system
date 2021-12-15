package com.hw.mapper;

import com.hw.model.Exam;
import com.hw.model.ExamVo;
import com.hw.model.vo.ExamListForStudentVo;
import com.hw.model.vo.ExamSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    void addExamClassRecord(@Param("examId") Integer examId, @Param("classId") int classId);

    List<Exam> selectByTeacherId(@Param("teacherId") Integer teacherId, @Param("title") String title, @Param("status") Integer status, @Param("valid") Integer valid);

    void updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    void updateValidById(@Param("id") Integer examId, @Param("valid") Integer valid);

    List<ExamListForStudentVo> selectExamById(Integer classId);


    /**
     * 修改试卷和班级关联
     * @param examId
     * @param classId
     */
    void updateExamClass(@Param("examId") Integer examId, @Param("classId") Integer classId,@Param("delFlag") Integer delFlag);

    List<Exam> selectExamUndo();

    List<Exam> selectExamDo();
}
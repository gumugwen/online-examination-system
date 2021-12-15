package com.hw.mapper;

import com.hw.model.ExamRecord;
import com.hw.model.ExamResult;
import com.hw.model.vo.ExamResultVo;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ExamResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamResult record);

    int insertSelective(ExamResult record);

    ExamResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamResult record);

    int updateByPrimaryKey(ExamResult record);

    List<ExamResultVo> findByParams(@Param("teacherId") Integer teacherId, @Param("classId") Integer classId, @Param("status") Integer status, @Param("title") String title);

    int insertExamResultForStudent(ExamResult record);

    int selectExamResultByStudentExamId(@Param("exam_id") Integer exam_id, @Param("student_id")Integer student_id);

    int updateById(@Param("id")Integer id,@Param("point")Integer point);

    int selectExamResultByStudentExamIdBy(@Param("exam_id") Integer exam_id, @Param("student_id")Integer student_id);

    List<ExamResult> selectExamResultByStudentId(@Param("student_id")Integer student_id,@Param("exam_title")String exam_title);

    int selectMinPoint(@Param("student_id") Integer student_id);
    int selectMaxPoint(@Param("student_id") Integer student_id);

    List<ExamResult> selectExamByStudentId(@Param("student_id")Integer student_id);
}
package com.hw.mapper;

import com.hw.model.ExamResultQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamResultQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamResultQuestion record);

    int insertSelective(ExamResultQuestion record);

    ExamResultQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamResultQuestion record);

    int updateByPrimaryKey(ExamResultQuestion record);

    List<ExamResultQuestion> selectByResultId(@Param("resultId") Integer resultId);

    int insertExamResultQuestiionByList(@Param("list") List<ExamResultQuestion> list);

    int selectScoreByExamId(Integer exam_result_id);
}
package com.hw.mapper;

import com.hw.model.QuestionJudge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionJudgeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionJudge record);

    int insertSelective(QuestionJudge record);

    QuestionJudge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionJudge record);

    int updateByPrimaryKey(QuestionJudge record);

    List<QuestionJudge> selectByExamId(@Param("examId") Integer examId,@Param("title") String title);

    void updateDelFlagById(@Param("id") Integer id,@Param("delFlag") Boolean delFlag);

    List<QuestionJudge> selectByIds(@Param("judgeIds") List<Integer> judgeIds);

    List<QuestionJudge> selectQuestionByExamId(Integer exam_id);

    int selectQuestionCountByExamId(Integer exam_id);

    void insertBatch(@Param("questionJudgeList") List<QuestionJudge> questionJudgeList);

}
package com.hw.mapper;

import com.hw.model.QuestionMulti;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMultiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionMulti record);

    int insertSelective(QuestionMulti record);

    QuestionMulti selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionMulti record);

    int updateByPrimaryKey(QuestionMulti record);

    List<QuestionMulti> selectByExamId(@Param("examId") Integer examId,@Param("title") String title);

    void updateDelFlagById(@Param("id") Integer id,@Param("delFlag") Boolean delFlag);

    List<QuestionMulti> selectByIds(@Param("multiIds") List<Integer> multiIds);

    List<QuestionMulti> selectQuestionByExamId(Integer exam_id);

    int selectQuestionCountByExamId(Integer exam_id);

    void insertBatch(@Param("questionMultiList") List<QuestionMulti> questionMultiList);

}
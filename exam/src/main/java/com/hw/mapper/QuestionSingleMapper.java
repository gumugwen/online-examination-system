package com.hw.mapper;

import com.hw.model.QuestionDto;
import com.hw.model.QuestionMulti;
import com.hw.model.QuestionSingle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionSingleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionSingle record);

    int insertSelective(QuestionSingle record);

    QuestionSingle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionSingle record);

    int updateByPrimaryKey(QuestionSingle record);

    List<QuestionSingle> selectByExamId(@Param("examId") Integer examId,@Param("title") String title);

    void updateDelFlagById(@Param("id") Integer id,@Param("delFlag") Boolean delFlag);

    /**
     * 根据id列表查询
     * @param ids
     * @return
     */
    List<QuestionSingle> selectByIds(@Param("ids") List<Integer> ids);

    List<QuestionSingle> selectQuestionByExamId(Integer exam_id);

    int selectQuestionCountByExamId(Integer exam_id);

    void insertBatch(@Param("questionSingleList") List<QuestionSingle> questionSingleList);

}
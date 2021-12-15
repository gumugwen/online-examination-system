package com.hw.mapper;

import com.hw.model.ExamRecord;
import com.hw.model.vo.JudgeQustionResultVo;
import com.hw.model.vo.MultiQuestionResultVo;
import com.hw.model.vo.SingleQuestionResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamRecord record);

    int insertSelective(ExamRecord record);

    ExamRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamRecord record);

    int updateByPrimaryKey(ExamRecord record);

    int insertExamRecordByList(@Param("list") List<ExamRecord> list);

    List<SingleQuestionResultVo> selectUnitSingleMapByResultId(@Param("result_id") Integer result_id);

    List<MultiQuestionResultVo> selectUnitMultiMapByResultId(@Param("result_id") Integer result_id);


    List<JudgeQustionResultVo> selectUnitJudgeMapByResultId(@Param("result_id") Integer result_id);
}
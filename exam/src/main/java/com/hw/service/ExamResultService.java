package com.hw.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hw.common.AuthUser;
import com.hw.common.Constant;
import com.hw.common.TableResult;
import com.hw.mapper.*;
import com.hw.model.*;
import com.hw.model.vo.ExamRecordSearchVo;
import com.hw.model.vo.ExamResultDetail;
import com.hw.model.vo.ExamResultVo;
import com.hw.model.vo.QuestionDetailsDto;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author  
 * @date 2020/11/20 3:00 下午
 */
@Service
public class ExamResultService {

    @Autowired
    private ExamResultMapper examResultMapper;
    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private QuestionSingleMapper questionSingleMapper;
    @Autowired
    private QuestionMultiMapper questionMultiMapper;

    @Autowired
    private QuestionJudgeMapper questionJudgeMapper;

    @Autowired
    private ExamResultQuestionMapper examResultQuestionMapper;

    @Autowired
    private StudentMapper studentMapper;


    /**
     * 教师搜索考试记录
     *
     * @param vo
     * @return
     */
    public TableResult<ExamResultVo> findByParams(ExamRecordSearchVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        ExamRecordSearchVo.Data searchData = vo.getData();
        if (searchData == null) {
            searchData = new ExamRecordSearchVo.Data();
        }
        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        List<ExamResultVo> examResultVoList = examResultMapper.findByParams(authUser.getId(), searchData.getClassId(), searchData.getStatus(), searchData.getTitle());
        PageInfo<ExamResultVo> pageInfo = new PageInfo<>(examResultVoList);

        return new TableResult<>(0, "ok", (int) pageInfo.getTotal(), pageInfo.getList());

    }


    /**
     * 查询某个学生的考试详情
     * @param resultId
     * @param studentId
     * @return
     */
    public ExamResultDetail getDetail(Integer resultId,Integer studentId) {
        ExamResult examResult = examResultMapper.selectByPrimaryKey(resultId);
        ExamResultDetail detail = new ExamResultDetail();


        List<ExamResultQuestion> examResultQuestionList = examResultQuestionMapper.selectByResultId(resultId);

        //按照题目类型分组
        Map<Integer, List<ExamResultQuestion>> collect =
                examResultQuestionList.stream().collect(Collectors.groupingBy(ExamResultQuestion::getQtype));

        List<QuestionDetailsDto> questionDetailsDtoList = null;

        //单选题结果
        List<ExamResultQuestion> singleGroupList = collect.get(Constant.SINGLE);
        //多选题结果
        List<ExamResultQuestion> multiGroupList = collect.get(Constant.MULTI);
        //判断题结果
        List<ExamResultQuestion> judgeGroupList = collect.get(Constant.JUDGE);
        if (singleGroupList != null && singleGroupList.size() > 0) {
            questionDetailsDtoList = new ArrayList<>(singleGroupList.size());
            //单选题id列表
            List<Integer> singleIds = singleGroupList.stream().map(ExamResultQuestion::getQuestionId).collect(Collectors.toList());
            List<QuestionSingle> questionSingles = questionSingleMapper.selectByIds(singleIds);
            Map<Integer, List<QuestionSingle>> singleMap = questionSingles.stream().collect(Collectors.groupingBy(QuestionSingle::getId));


            QuestionDetailsDto dto;
            Double singleTotalScore = 0.0d;
            for (ExamResultQuestion singleQ : singleGroupList) {
                dto = new QuestionDetailsDto();
                List<QuestionSingle> qs = singleMap.get(singleQ.getQuestionId());
                if (qs!=null && qs.size()>0){
                    QuestionSingle questionSingle = qs.get(0);
                    dto.setInScore(singleQ.getScore());
                    dto.setOptionA(questionSingle.getOptiona());
                    dto.setOptionB(questionSingle.getOptionb());
                    dto.setOptionC(questionSingle.getOptionc());
                    dto.setOptionD(questionSingle.getOptiond());
                    dto.setRight(singleQ.getRight());
                    dto.setRightAnswer(singleQ.getRightAnswer());
                    dto.setWrongAnswer(singleQ.getWrongAnswer());
                    dto.setTitle(questionSingle.getTitle());
                    dto.setScore(questionSingle.getScore());
                    questionDetailsDtoList.add(dto);

                    singleTotalScore += questionSingle.getScore();
                }

            }
            detail.setSingleDetailList(questionDetailsDtoList);
            detail.setSinglePoints(singleTotalScore);
        }

        if (multiGroupList!=null && multiGroupList.size()>0){
            questionDetailsDtoList = new ArrayList<>(multiGroupList.size());
            List<Integer> multiIds = multiGroupList.stream().map(ExamResultQuestion::getQuestionId).collect(Collectors.toList());
            List<QuestionMulti>questionMultiList= questionMultiMapper.selectByIds(multiIds);
            Map<Integer, List<QuestionMulti>> questionMultiGroup= questionMultiList.stream().collect(Collectors.groupingBy(QuestionMulti::getId));

            QuestionDetailsDto dto=null;
            Double multiTotalScore = 0.0d;

            for (ExamResultQuestion multiRes : multiGroupList) {
                List<QuestionMulti> questionMultis = questionMultiGroup.get(multiRes.getQuestionId());
                if (questionMultis!=null && questionMultis.size()>0){
                    QuestionMulti questionMulti = questionMultis.get(0);
                    dto=new QuestionDetailsDto();
                    multiTotalScore+=questionMulti.getScore();

                    dto.setScore(questionMulti.getScore());
                    dto.setTitle(questionMulti.getTitle());
                    dto.setWrongAnswer(multiRes.getWrongAnswer());
                    dto.setRightAnswer(questionMulti.getAnswer());
                    dto.setRight(multiRes.getRight());
                    dto.setInScore(multiRes.getScore());
                    dto.setOptionA(questionMulti.getOptiona());
                    dto.setOptionB(questionMulti.getOptionb());
                    dto.setOptionC(questionMulti.getOptionc());
                    dto.setOptionD(questionMulti.getOptiond());
                    questionDetailsDtoList.add(dto);
                }
            }
            detail.setMultiDetailList(questionDetailsDtoList);
            detail.setMultiPotints(multiTotalScore);
        }
        //判断
        if (judgeGroupList!=null && judgeGroupList.size()>0){
            questionDetailsDtoList = new ArrayList<>(judgeGroupList.size());
            List<Integer> judgeIds = judgeGroupList.stream().map(ExamResultQuestion::getQuestionId).collect(Collectors.toList());
            List<QuestionJudge>questionJudgeList= questionJudgeMapper.selectByIds(judgeIds);
            Map<Integer, List<QuestionJudge>> questionJudgeGroup= questionJudgeList.stream().collect(Collectors.groupingBy(QuestionJudge::getId));

            QuestionDetailsDto dto=null;
            Double judgeTotalScore = 0.0d;

            for (ExamResultQuestion judgeRes : judgeGroupList) {
                List<QuestionJudge> questionJudges = questionJudgeGroup.get(judgeRes.getQuestionId());
                if (questionJudges!=null && questionJudges.size()>0){
                    QuestionJudge questionJudge = questionJudges.get(0);
                    dto=new QuestionDetailsDto();
                    judgeTotalScore+=questionJudge.getScore();

                    dto.setScore(questionJudge.getScore());
                    dto.setTitle(questionJudge.getTitle());
                    dto.setWrongAnswer(judgeRes.getWrongAnswer());
                    dto.setRightAnswer(questionJudge.getAnswer()?"对":"错");
                    dto.setRight(judgeRes.getRight());
                    dto.setInScore(judgeRes.getScore());
                    questionDetailsDtoList.add(dto);
                }
            }
            detail.setJudgeDetailList(questionDetailsDtoList);
            detail.setJudgePoints(judgeTotalScore);
        }

        detail.setStudentName(studentMapper.selectByPrimaryKey(studentId).getRealName());
        detail.setTotalScore((detail.getSinglePoints()+detail.getMultiPotints()+detail.getJudgePoints()));
        detail.setTitle(examResult.getExamTitle());
        detail.setScore(examResult.getPoint());
        Exam exam = examMapper.selectByPrimaryKey(examResult.getExamId());
        detail.setStartTime(exam.getStartTime());
        detail.setStartTime(examResult.getStartTime());
        return detail;
    }
}

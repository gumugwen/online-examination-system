package com.hw.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hw.common.Constant;
import com.hw.common.TableResult;
import com.hw.common.utils.DateUtils;
import com.hw.mapper.ExamMapper;
import com.hw.mapper.QuestionJudgeMapper;
import com.hw.mapper.QuestionMultiMapper;
import com.hw.mapper.QuestionSingleMapper;
import com.hw.model.*;
import com.hw.model.vo.ExamSearchVo;
import com.hw.model.vo.QuestionSearch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  
 * @date 2020/11/17 11:04 下午
 */
@Service
public class QuestionSingleService {
    @Autowired
    private QuestionSingleMapper questionSingleMapper;
    @Autowired
    private QuestionMultiMapper questionMultiMapper;
    @Autowired
    private QuestionJudgeMapper questionJudgeMapper;

    @Autowired
    private ExamMapper examMapper;

    /**
     * 默认时 没有Exam_id 没有根据title查询
     *
     * @param questionSearch
     * @return
     */
    public TableResult<QuestionDto> teacherQuestions(QuestionSearch questionSearch) {
        QuestionSearch.Data data = questionSearch.getData();
        if (data == null) {
            data = new QuestionSearch.Data();
        }
        Integer qType = data.getStatus();
        TableResult<QuestionDto> tableResult = new TableResult<>();
        tableResult.setMsg("ok");
        tableResult.setCode(0);
        if (qType == null || qType == -1) {
            List<QuestionSingle> questionSingles = questionSingleMapper.selectByExamId(questionSearch.getExamId(), data.getTitle());
            List<QuestionDto> dtoSingles = selectFromSingle(questionSingles);
            List<QuestionMulti> questionMultis = questionMultiMapper.selectByExamId(questionSearch.getExamId(), data.getTitle());
            List<QuestionDto> dtoMultis = selectFromMulti(questionMultis);
            List<QuestionJudge> questionJudges = questionJudgeMapper.selectByExamId(questionSearch.getExamId(), data.getTitle());

            List<QuestionDto> dtoJudges = selectFromJudge(questionJudges);
            List<QuestionDto> all = new ArrayList<>(dtoJudges.size() + dtoMultis.size() + dtoSingles.size());
            all.addAll(dtoJudges);
            all.addAll(dtoMultis);
            all.addAll(dtoSingles);
            tableResult.setData(all);
            tableResult.setCount(all.size());
        } else if (qType.equals(Constant.SINGLE)) {
            PageHelper.startPage(questionSearch.getPage(), questionSearch.getLimit());
            List<QuestionSingle> questionSingles = questionSingleMapper.selectByExamId(questionSearch.getExamId(), data.getTitle());
            List<QuestionDto> dtos = selectFromSingle(questionSingles);
            PageInfo<QuestionDto> pageInfo = new PageInfo<>(dtos);
            tableResult.setCount((int) pageInfo.getTotal());
            tableResult.setData(pageInfo.getList());
        } else if (qType.equals(Constant.MULTI)) {
            PageHelper.startPage(questionSearch.getPage(), questionSearch.getLimit());
            List<QuestionMulti> questionMultis = questionMultiMapper.selectByExamId(questionSearch.getExamId(), data.getTitle());

            List<QuestionDto> dtos = selectFromMulti(questionMultis);
            PageInfo<QuestionDto> pageInfo = new PageInfo<>(dtos);
            tableResult.setCount((int) pageInfo.getTotal());
            tableResult.setData(pageInfo.getList());
        } else if (qType.equals(Constant.JUDGE)) {
            PageHelper.startPage(questionSearch.getPage(), questionSearch.getLimit());
            List<QuestionJudge> questionMultis = questionJudgeMapper.selectByExamId(questionSearch.getExamId(), data.getTitle());

            List<QuestionDto> dtos = selectFromJudge(questionMultis);
            PageInfo<QuestionDto> pageInfo = new PageInfo<>(dtos);
            tableResult.setCount((int) pageInfo.getTotal());
            tableResult.setData(pageInfo.getList());
        } else {
            tableResult.setCount(0);
            tableResult.setCode(500);
            tableResult.setMsg("参数错误");
        }
        return tableResult;
    }

    private List<QuestionDto> selectFromJudge(List<QuestionJudge> questionJudges) {
        List<QuestionDto> dtos = new ArrayList<>(questionJudges.size());
        for (QuestionJudge questionJudge : questionJudges) {
            QuestionDto dto = new QuestionDto();
            dto.setType(Constant.JUDGE);
            dto.setJudgeAnswer(questionJudge.getAnswer());
            dto.setFkTeacher(questionJudge.getFkTeacher());
            dto.setId(questionJudge.getId());
            dto.setDelFlag(questionJudge.getDelFlag());
            dto.setExamId(questionJudge.getExamId());
            dto.setTitle(questionJudge.getTitle());
            dto.setScore(questionJudge.getScore());
            dtos.add(dto);
        }
        return dtos;
    }

    private List<QuestionDto> selectFromMulti(List<QuestionMulti> questionMultis) {
        List<QuestionDto> dtos = new ArrayList<>(questionMultis.size());
        for (QuestionMulti questionMulti : questionMultis) {
            QuestionDto dto = new QuestionDto();
            dto.setType(Constant.MULTI);
            BeanUtils.copyProperties(questionMulti, dto);
            dtos.add(dto);
        }
        return dtos;
    }

    private List<QuestionDto> selectFromSingle(List<QuestionSingle> questionSingles) {
        List<QuestionDto> dtos = new ArrayList<>(questionSingles.size());
        for (QuestionSingle questionSingle : questionSingles) {
            QuestionDto dto = new QuestionDto();
            dto.setType(Constant.SINGLE);
            BeanUtils.copyProperties(questionSingle, dto);
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * 教师删除试题
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delTeacherQuestion(QuestionDto dto) {
        Integer examId = dto.getExamId();
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam.getStartTime().compareTo(DateUtils.nowToDate()) > 0) {
            return false;
        }
        if (dto.getType().equals(Constant.SINGLE)) {
            questionSingleMapper.updateDelFlagById(dto.getId(), true);
        } else if (dto.getType().equals(Constant.JUDGE)) {
            questionJudgeMapper.updateDelFlagById(dto.getId(), true);
        } else if (dto.getType().equals(Constant.MULTI)) {
            questionMultiMapper.updateDelFlagById(dto.getId(), true);
        } else {
            return false;
        }
        return true;
    }
}

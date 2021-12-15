package com.hw.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hw.common.AuthUser;
import com.hw.common.Constant;
import com.hw.common.TableResult;
import com.hw.common.utils.DateUtils;
import com.hw.mapper.*;
import com.hw.model.*;
import com.hw.model.vo.ExamSearchVo;
import com.hw.model.vo.QuestionVo;
import com.hw.task.DelayTask;
import com.hw.task.ExamDelay;
import com.sun.org.apache.bcel.internal.generic.FSUB;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author  
 * @date 2020/11/17 11:03 下午
 */
@Service
public class ExamService {
    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private TbClassMapper tbClassMapper;

    @Autowired
    private QuestionSingleMapper questionSingleMapper;

    @Autowired
    private QuestionMultiMapper questionMultiMapper;

    @Autowired
    private QuestionJudgeMapper questionJudgeMapper;

    @Autowired
    private ExamDelay examDelay;


    /**
     * 发布试卷
     *
     * @param exam
     */
    @Transactional(rollbackFor = Exception.class)
    public void publish(Exam exam) {

        examMapper.insert(exam);
        String[] split = exam.getClassIds().split(",");

        for (String classId : split) {
            examMapper.addExamClassRecord(exam.getId(), Integer.parseInt(classId));
        }


    }

    public TableResult<Exam> examListOfTeacher(Integer teacherId, ExamSearchVo searchVo) {
        PageHelper.startPage(searchVo.getPage(), searchVo.getLimit());
        searchVo.setData(searchVo.getData() == null ? new ExamSearchVo.Data() : searchVo.getData());
        ExamSearchVo.Data data = searchVo.getData();
        List<Exam> examVos = examMapper.selectByTeacherId(teacherId, data.getTitle(), data.getStatus(), data.getValid());
        for (Exam exam : examVos) {
            List<TbClass> classList = tbClassMapper.selectExamId(exam.getId());
            exam.setClassIdsList(classList.stream().map(TbClass::getId).collect(Collectors.toList()));
            exam.setClassNames(classList.stream().map(TbClass::getCname).collect(Collectors.toList()));
        }
        PageInfo<Exam> pageInfo = new PageInfo<>(examVos);
        return new TableResult<Exam>(0, "ok", (int) pageInfo.getTotal(), pageInfo.getList());

    }

    @Transactional(rollbackFor = Exception.class)
    public void addQuestion(QuestionVo questionVo) {
        Integer type = questionVo.getType();
        Exam exam = examMapper.selectByPrimaryKey(questionVo.getExamId());
        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        if (type.equals(Constant.SINGLE)) {
            QuestionSingle single = convert2QuestionSingle(questionVo, exam, authUser.getId(), questionVo.getExamId());
            questionSingleMapper.insert(single);
        } else if (type.equals(Constant.MULTI)) {
            QuestionMulti multi = convert2QuestionMulti(questionVo, exam, authUser.getId(), questionVo.getExamId());
            questionMultiMapper.insert(multi);


        } else if (type.equals(Constant.JUDGE)) {
            QuestionJudge judge = convert2Questionjudge(questionVo, exam, authUser.getId(), questionVo.getExamId());
            questionJudgeMapper.insert(judge);
        }
    }

    private QuestionMulti convert2QuestionMulti(QuestionVo questionVo, Exam exam, Integer teacherId, Integer examId) {
        questionVo.setScore(exam.getMultiPoints());
        return new QuestionMulti(questionVo.getTitle(), questionVo.getOptionA(), questionVo.getOptionB(), questionVo.getOptionC(), questionVo.getOptionD(),
                questionVo.getSingleMultiAnswer(), questionVo.getScore(), teacherId, examId, false);
    }

    private QuestionSingle convert2QuestionSingle(QuestionVo questionVo, Exam exam, Integer taecherId, Integer examId) {
        questionVo.setScore(exam.getSinglePoints());
        QuestionSingle single = new QuestionSingle();
        single.setAnswer(questionVo.getSingleMultiAnswer());
        single.setDelFlag(false);
        single.setExamId(examId);
        single.setFkTeacher(taecherId);
        single.setOptiona(questionVo.getOptionA());
        single.setOptionb(questionVo.getOptionB());
        single.setOptionc(questionVo.getOptionC());
        single.setOptiond(questionVo.getOptionD());
        single.setTitle(questionVo.getTitle());
        single.setScore(questionVo.getScore());
        return single;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer publish0(Integer examId, Integer teacherId) {
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if(exam.getValid() ==0){
            return -1;
        }
        Date now = new Date();

        Date startTime = exam.getStartTime();
        if (startTime.compareTo(now)<0){
            return 2;
        }
        Date endTime = exam.getEndTime();

        examMapper.updateStatusById(examId, 2);

        examDelay.addTask(new DelayTask(examId + "-begin", "修改考卷状态为已开始", (startTime.getTime() - now.getTime())));
        examDelay.addTask(new DelayTask(examId + "-end", "修改考卷状态为已结束", (endTime.getTime() - now.getTime())));

        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateQuestion(QuestionVo questionVo) {
        Integer type = questionVo.getType();
        Exam exam = examMapper.selectByPrimaryKey(questionVo.getExamId());
        Date startTime = exam.getStartTime();
        if (startTime.compareTo(DateUtils.nowToDate()) > 0) {
            return false;
        }
        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        if (type.equals(Constant.SINGLE)) {
            QuestionSingle single = convert2QuestionSingle(questionVo, exam, authUser.getId(), questionVo.getExamId());
            single.setId(questionVo.getTitleId());
            questionSingleMapper.updateByPrimaryKeySelective(single);
        } else if (type.equals(Constant.MULTI)) {
            QuestionMulti multi = convert2QuestionMulti(questionVo, exam, authUser.getId(), questionVo.getExamId());

            multi.setId(questionVo.getTitleId());
            questionMultiMapper.updateByPrimaryKeySelective(multi);

        } else if (type.equals(Constant.JUDGE)) {
            QuestionJudge judge = convert2Questionjudge(questionVo, exam, authUser.getId(), questionVo.getExamId());
            judge.setId(questionVo.getTitleId());
            questionJudgeMapper.updateByPrimaryKeySelective(judge);
        }
        return true;

    }

    private QuestionJudge convert2Questionjudge(QuestionVo questionVo, Exam exam, Integer authUser, Integer examId) {
        questionVo.setScore(exam.getJudgePoints());
        return new QuestionJudge(questionVo.getTitle(), questionVo.getJudgeAnswer() == 1 ? true : false, questionVo.getScore(),
                authUser, examId, false);
    }

    /**
     * 教师删除考卷
     *
     * @param examId
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delExam(Integer examId) {
        Exam exam = examMapper.selectByPrimaryKey(examId);
        if (exam.getStartTime().compareTo(new Date())<0){
            return false;
        }
        examMapper.updateValidById(examId, 0);
        return true;
    }

    /**
     * 批量导入试题
     *
     * @param datas
     * @param examId
     * @param teacherId
     */
    @Transactional(rollbackFor = Exception.class)
    public void addQuestionBatch(List<QuestionVo> datas, Integer examId, Integer teacherId) {
        Map<Integer, List<QuestionVo>> typeGroup =
                datas.stream().collect(Collectors.groupingBy(QuestionVo::getType));
        Exam exam = examMapper.selectByPrimaryKey(examId);

        List<QuestionVo> singleVos = typeGroup.get(Constant.SINGLE);
        List<QuestionVo> multiVos = typeGroup.get(Constant.MULTI);
        List<QuestionVo> judgeVos = typeGroup.get(Constant.JUDGE);

        if (singleVos != null && singleVos.size() > 0) {
            List<QuestionSingle> questionSingleList = new ArrayList<>(singleVos.size());

            for (QuestionVo singleVo : singleVos) {
                questionSingleList.add(convert2QuestionSingle(singleVo, exam, teacherId, examId));
            }
            questionSingleMapper.insertBatch(questionSingleList);
        }

        if (multiVos != null && multiVos.size() > 0) {
            List<QuestionMulti> questionMultiList = new ArrayList<>(multiVos.size());

            for (QuestionVo multiVo : multiVos) {
                questionMultiList.add(convert2QuestionMulti(multiVo, exam, teacherId, examId));
            }
            questionMultiMapper.insertBatch(questionMultiList);
        }


        if (judgeVos != null && judgeVos.size() > 0) {
            List<QuestionJudge> questionJudgeList = new ArrayList<>(judgeVos.size());

            for (QuestionVo judgeVo : judgeVos) {
                questionJudgeList.add(convert2Questionjudge(judgeVo, exam, teacherId, examId));
            }
            questionJudgeMapper.insertBatch(questionJudgeList);
        }


    }

    public Exam selectById(Integer examId) {
        Exam exam = examMapper.selectByPrimaryKey(examId);
        List<TbClass> classList = tbClassMapper.selectExamId(examId);
        List<String> cNames = classList.stream().map(TbClass::getCname).collect(Collectors.toList());
        List<Integer> ids = classList.stream().map(TbClass::getId).collect(Collectors.toList());
        exam.setClassIdsList(ids);
        exam.setClassNames(cNames);
        return exam;
    }

    public void updateExam(Exam exam) {
        Exam old = examMapper.selectByPrimaryKey(exam.getId());
        List<TbClass> oldClass = tbClassMapper.selectExamId(old.getId());
        exam.setStatus(old.getStatus());
        int i = examMapper.updateByPrimaryKey(exam);
        if (i>0){
            String[] split = exam.getClassIds().split(",");
            for (TbClass oldClazz : oldClass) {
                examMapper.updateExamClass(exam.getId(),oldClazz.getId(),1);
            }
            for (String classId : split) {
                examMapper.addExamClassRecord(exam.getId(),Integer.parseInt(classId));
            }

        }
    }
}

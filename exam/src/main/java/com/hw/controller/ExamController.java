package com.hw.controller;

import com.hw.common.*;
import com.hw.mapper.TbClassMapper;
import com.hw.model.Exam;
import com.hw.model.ExamResult;
import com.hw.model.TbClass;
import com.hw.model.vo.*;
import com.hw.service.ClassService;
import com.hw.service.ExamResultService;
import com.hw.service.ExamService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author  
 * @date 2020/11/17 10:58 下午
 */
@Controller
@RequestMapping("/exam")
public class ExamController {


    @Autowired
    private ExamService examService;

    @Autowired
    private TbClassMapper tbClassMapper;

    @Autowired
    private ExamResultService examResultService;


    @GetMapping("/exam-by-id")
    @ResponseBody
    public Result findById(@RequestParam("examId")Integer examId){
        if (examId==null || examId==-1){
            return Result.fail(examId,"add");
        }
        Exam exam = examService.selectById(examId);
        return Result.ok(RespCode.SUCCESS,exam);
    }
    @PostMapping("/list")
    @RequiresRoles("teacher")
    @ResponseBody
    public TableResult<Exam> examForTeacher(@RequestBody ExamSearchVo searchVo) {
        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        return examService.examListOfTeacher(authUser.getId(), searchVo);
    }

    @RequestMapping("/toExamResultDetail")
    public String toExamResultDetail(@RequestParam("resultId")Integer resultId,@RequestParam("studentId")Integer studentId, Model model){
        ExamResultDetail detail = examResultService.getDetail(resultId,studentId);
        model.addAttribute("examDetail",detail);
        return "teacher/ExamDetail";
    }
    @PostMapping("/addQuestion")
    @RequiresRoles("teacher")
    @ResponseBody
    public Result addQuestion(@RequestBody QuestionVo questionVo) {
        System.out.println(questionVo);
        if (questionVo.getExamId() == null || questionVo == null || questionVo.getType() == null) {
            return Result.fail(RespCode.FAIL, "非法请求");
        }
        if (questionVo.getTitle() == null || questionVo.getTitle().equals("")) {
            return Result.fail(RespCode.FAIL, "请填写试题");
        }
        if (questionVo.getType().equals(Constant.SINGLE) || questionVo.getType().equals(Constant.MULTI)) {
            if (StringUtils.isAnyBlank(questionVo.getOptionA(), questionVo.getOptionB(), questionVo.getOptionC(), questionVo.getOptionD())) {
                return Result.fail(RespCode.FAIL, "请填写完整选项");
            }
            if (StringUtils.isBlank(questionVo.getSingleMultiAnswer())) {
                return Result.fail(RespCode.FAIL, "请填写该题答案");
            }
            String smAnswer = questionVo.getSingleMultiAnswer();
            if (questionVo.getType().equals(Constant.SINGLE)) {
                if (answerNotValid(smAnswer)) {
                    return Result.fail(RespCode.NOT_VALID, "答案格式非法");
                }
            } else {
                String[] split = smAnswer.split(",");
                for (String s : split) {
                    if (answerNotValid(s)) {
                        return Result.fail(RespCode.NOT_VALID, "答案格式非法");
                    }
                }
            }
        } else if (questionVo.getType().equals(Constant.JUDGE)) {
            if (questionVo.getJudgeAnswer() == null) {
                return Result.fail(RespCode.FAIL, "请选中该题答案");
            }
        } else {
            return Result.fail(RespCode.FAIL, "非法请求");
        }
        examService.addQuestion(questionVo);
        return Result.ok(RespCode.SUCCESS, "OK");
    }

    /**
     * 修改试卷
     * @param exam
     * @param bindingResult
     * @return
     */
    @PostMapping("/updateExam")
    @RequiresRoles("teacher")
    @ResponseBody
    public Result publishExam(@Valid @RequestBody Exam exam, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return Result.fail(RespCode.FAIL,bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        Date startTime = exam.getStartTime();
        Date endTime = exam.getEndTime();
        Date s=new Date(startTime.getTime()+exam.getTimeLimit()*60*1000);
        if (endTime.compareTo(s)<0){
            return Result.fail(RespCode.NOT_VALID,"考试时长不足"+exam.getTimeLimit());
        }
        Subject subject = SecurityUtils.getSubject();
        AuthUser authUser = (AuthUser) subject.getPrincipal();
        exam.setTeacherId(authUser.getId());
        exam.setValid(1);
        examService.updateExam(exam);

        return Result.ok(RespCode.SUCCESS,"保存成功");
    }


    public static boolean answerNotValid(String smAnswer) {
        return !smAnswer.equals("A")
                && !smAnswer.equals("B")
                && !smAnswer.equals("C")
                && !smAnswer.equals("D");
    }

    /**
     * 修改问题
     * @param questionVo
     * @return
     */
    @PostMapping("/updateQuestion")
    @RequiresRoles("teacher")
    @ResponseBody
    public Result updateQuestion(@RequestBody QuestionVo questionVo) {

        if (questionVo.getTitleId()==null|| questionVo.getTitleId()<1 || questionVo.getExamId() == null || questionVo == null || questionVo.getType() == null) {
            return Result.fail(RespCode.FAIL, "非法请求");
        }
        if (questionVo.getTitle() == null || questionVo.getTitle().equals("")) {
            return Result.fail(RespCode.FAIL, "请填写试题");
        }
        if (questionVo.getType().equals(Constant.SINGLE) || questionVo.getType().equals(Constant.MULTI)) {
            if (StringUtils.isAnyBlank(questionVo.getOptionA(), questionVo.getOptionB(), questionVo.getOptionC(), questionVo.getOptionD())) {
                return Result.fail(RespCode.FAIL, "请填写完整选项");
            }
            if (StringUtils.isBlank(questionVo.getSingleMultiAnswer())) {
                return Result.fail(RespCode.FAIL, "请填写该题答案");
            }
            String smAnswer = questionVo.getSingleMultiAnswer();
            if (questionVo.getType().equals(Constant.SINGLE)) {
                if (answerNotValid(smAnswer)) {
                    return Result.fail(RespCode.NOT_VALID, "答案格式非法");
                }
            } else {
                String[] split = smAnswer.split(",");
                for (String s : split) {
                    if (answerNotValid(s)) {
                        return Result.fail(RespCode.NOT_VALID, "答案格式非法");
                    }
                }
            }
        } else if (questionVo.getType().equals(Constant.JUDGE)) {
            if (questionVo.getJudgeAnswer() == null) {
                return Result.fail(RespCode.FAIL, "请选中该题答案");
            }
        } else {
            return Result.fail(RespCode.FAIL, "非法请求");
        }
        boolean res = examService.updateQuestion(questionVo);
        if (res){

            return Result.ok(RespCode.SUCCESS, "修改成功");
        }else {
            return Result.fail(RespCode.FAIL, "考试已开始，无法修改试题");

        }
    }


  /**
     * 教师删除考卷
     * @return
     */
    @PostMapping("/teacher/del")
    @RequiresRoles("teacher")
    @ResponseBody
    public Result teacherDelQuestion( @RequestBody Exam exam){
        Integer examId = exam.getId();
        Integer teacherId = exam.getTeacherId();
        if (examId==null || teacherId==null || examId<1 || teacherId<1){
            return Result.fail(RespCode.NOT_VALID,"参数非法");
        }
        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        if (!authUser.getId().equals(teacherId)){
            return Result.fail(RespCode.FORBIDDENED,"权限不足");
        }
        boolean res = examService.delExam(examId);
        if (res){
            return Result.ok(RespCode.SUCCESS,"删除成功");

        }
        return Result.fail(RespCode.FAIL,"考试已开始不可删除");

    }

    @RequestMapping("/toExamRecord")
    @RequiresRoles("teacher")
    public String  toBeMarkedPage(Model model){
        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        List<TbClass> classList = tbClassMapper.selectByTeacherId(authUser.getId());
        model.addAttribute("classList",classList);
        return "teacher/examRecord";
    }

    @PostMapping("/result/exam-record")
    @ResponseBody
    @RequiresRoles("teacher")
    public TableResult<ExamResultVo>examRecord(@RequestBody ExamRecordSearchVo vo){
        System.out.println(vo);
        return examResultService.findByParams(vo);
    }




}

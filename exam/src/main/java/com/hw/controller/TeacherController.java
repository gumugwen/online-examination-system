package com.hw.controller;

import com.hw.common.AuthUser;
import com.hw.common.RespCode;
import com.hw.common.Result;
import com.hw.model.Exam;
import com.hw.service.ExamService;
import com.sun.tools.internal.xjc.reader.xmlschema.BindRed;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author  
 * @date 2020/11/17 10:59 下午
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private static final Logger log= LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private ExamService examService;

    @RequestMapping("/toExamTable")
    public String toExamTable(){
        return "teacher/examTable";
    }

    @RequestMapping("/toExamAdd")
    @RequiresRoles("teacher")
    public String toExamAdd(@RequestParam("examId")Integer examId, Model model){
        model.addAttribute("examId",examId);
        return "teacher/examAdd";
    }



    @RequestMapping("/toQuestionAdd")
    @RequiresRoles("teacher")
    public String toQuestionAdd(){
        return "teacher/QuestionAdd";
    }

    @PostMapping("/publish")
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
        exam.setStatus(1);
        exam.setTeacherId(authUser.getId());
        exam.setValid(1);
        log.info("{}发布试卷{}",authUser,exam);
        examService.publish(exam);

        return Result.ok(RespCode.SUCCESS,"创建成功");
    }

    /**
     * 修改状态为 待考试
     * @return
     */
    @PostMapping("/publish0")
    @RequiresRoles("teacher")
    @ResponseBody
    public Result publishExam0( @RequestBody Map<String,Integer> publishData){
        Integer examId = publishData.get("id");
        Integer teacherId = publishData.get("teacherId");
        if (examId==null || teacherId==null || examId<1 || teacherId<1){
            return Result.fail(RespCode.NOT_VALID,"参数非法");
        }
        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        if (!authUser.getId().equals(teacherId)){
        }
        Integer res = examService.publish0(examId, teacherId);
        if (res==-1){
            return Result.fail(RespCode.FAIL,"考卷已删除，不可发布");
        }
        if (res==1){
            return Result.ok(RespCode.SUCCESS,"发布成功");

        }else {
            return Result.fail(RespCode.FAIL,"考试已开始，不可操作");

        }

    }
}

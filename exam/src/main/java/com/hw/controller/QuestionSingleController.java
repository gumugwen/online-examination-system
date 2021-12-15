package com.hw.controller;

import com.hw.common.AuthUser;
import com.hw.common.RespCode;
import com.hw.common.Result;
import com.hw.common.TableResult;
import com.hw.model.Exam;
import com.hw.model.QuestionDto;
import com.hw.model.vo.QuestionSearch;
import com.hw.service.QuestionSingleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author  
 * @date 2020/11/17 11:01 下午
 */
@Controller
@RequestMapping("/question/single")
public class QuestionSingleController {

    @Autowired
    private QuestionSingleService questionSingleService;

    /**
     * 教师搜索某个考卷的题目
     *
     * @param questionSearch
     * @return
     */
    @PostMapping("/teacherQs")
    @ResponseBody
    public TableResult<QuestionDto> teacherQuestions(@RequestBody QuestionSearch questionSearch) {
        if (questionSearch.getPage() == null || questionSearch.getPage() < 1) {
            questionSearch.setPage(1);
        }
        if (questionSearch.getLimit() == null || questionSearch.getLimit() < 1) {
            questionSearch.setLimit(5);
        }
        return questionSingleService.teacherQuestions(questionSearch);
    }

    /**
     * 教师删除试题
     *
     * @param dto
     * @return
     */
    @PostMapping("/teacher/del")
    @ResponseBody
    public Result delQuestion(@RequestBody QuestionDto dto) {
        if (dto.getId() == null || dto.getId() < 1) {
            return Result.fail(RespCode.FAIL, "参数非法");
        }
        if (dto.getDelFlag()) {
            return Result.ok(RespCode.SUCCESS, "OK");
        }
        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        if (!authUser.getId().equals(dto.getFkTeacher())) {
            return Result.fail(RespCode.FORBIDDENED, "权限不足");

        }
        boolean res = questionSingleService.delTeacherQuestion(dto);
        if (res) {
            return Result.ok(RespCode.SUCCESS, "OK");
        } else {
            return Result.fail(RespCode.FAIL, "考试已开始，无法删除");

        }
    }
}

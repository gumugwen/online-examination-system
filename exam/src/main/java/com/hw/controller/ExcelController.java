package com.hw.controller;

import com.alibaba.fastjson.JSON;
import com.hw.common.AuthUser;
import com.hw.common.Constant;
import com.hw.common.RespCode;
import com.hw.common.Result;
import com.hw.common.utils.ExcelUtil;
import com.hw.model.vo.QuestionVo;
import com.hw.service.ExamService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.hw.controller.ExamController.answerNotValid;

/**
 * @author  
 * @date 2020/11/26 9:44 上午
 */
@RequestMapping("/excel")
@Controller
public class ExcelController {

    @Autowired
    private ExamService examService;

    /**
     * 导出试题模板
     * @param examId
     * @param response
     * @param session
     * @throws IOException
     */
    @GetMapping("/export/ques_template")
    public void exportQuestionTemplate(@RequestParam("examId") Integer examId, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println(examId);
        List<QuestionVo> data = new ArrayList<>();
        ExcelUtil.pritEasyExcelTemplate(QuestionVo.class, data, "question_template", response, session);

    }

    /**
     * 批量导入试题
     * @param file
     * @param examId
     * @return
     * @throws IOException
     */
    @RequestMapping("/import/question")
    @ResponseBody
    public Result importExcel(MultipartFile file, @RequestParam("examId") Integer examId) throws IOException {
        if (examId == null) {
            return Result.fail(RespCode.FAIL, "非法请求");
        }
        List<QuestionVo> datas = ExcelUtil.importExcel(file, QuestionVo.class);
        for (QuestionVo questionVo : datas) {
            if (questionVo == null || questionVo.getType() == null) {
                return Result.fail(RespCode.FAIL, "请填写题目类型");
            }
            if (questionVo.getTitle() == null || questionVo.getTitle().equals("")) {
                return Result.fail(RespCode.FAIL, "请填写试题名");
            }
            if (questionVo.getType().equals(Constant.SINGLE) || questionVo.getType().equals(Constant.MULTI)) {
                if (StringUtils.isAnyBlank(questionVo.getOptionA(), questionVo.getOptionB(), questionVo.getOptionC(), questionVo.getOptionD())) {
                    return Result.fail(RespCode.FAIL, "请将单/多选题选项填写完整");
                }
                if (StringUtils.isBlank(questionVo.getSingleMultiAnswer())) {
                    return Result.fail(RespCode.FAIL, "请将单/多选题答案填写完整");
                }
                String smAnswer = questionVo.getSingleMultiAnswer();
                if (questionVo.getType().equals(Constant.SINGLE)) {
                    if (answerNotValid(smAnswer)) {
                        return Result.fail(RespCode.NOT_VALID, "答案格式非法(A-D)");
                    }
                } else {
                    String[] split = smAnswer.split(",");
                    for (String s : split) {
                        if (answerNotValid(s)) {
                            return Result.fail(RespCode.NOT_VALID, "答案格式非法(多个答案请使用英文逗号分隔)");
                        }
                    }
                }
            } else if (questionVo.getType().equals(Constant.JUDGE)) {
                if (questionVo.getJudgeAnswer() == null) {
                    return Result.fail(RespCode.FAIL, "请将判断题答案填写完整");
                }
            } else {
                return Result.fail(RespCode.FAIL, "非法请求");
            }
        }
        System.out.println(JSON.toJSONString(datas));
        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        examService.addQuestionBatch(datas,examId,authUser.getId());
        return Result.ok(RespCode.SUCCESS, "导入成功");
    }
}

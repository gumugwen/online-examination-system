package com.hw.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hw.common.utils.SessionUtils;
import com.hw.model.ExamResult;
import com.hw.model.vo.TestVo;
import com.hw.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;
    /**
     * 学生模块的首页
     * @return
     */
    @RequestMapping("/toStudentIndex")
    public String toStudentIndex(Model model) {
        Integer student_id = SessionUtils.getSessionUser().getId();
        model.addAttribute("examUnDo",testService.ExamUoDo());
        model.addAttribute("examDo",testService.ExamDo());
        model.addAttribute("maxPoint",testService.MaxPoint(student_id));
        model.addAttribute("minPoint",testService.minPoint(student_id));
        return "student/student-welcome";
    }

    /**
     * 其它模块的首页
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex() {
        return "home-welcome";
    }

    @RequestMapping("/getdate")
    @ResponseBody
    public Map<String,Object> getDate(Model model){
        Integer student_id = SessionUtils.getSessionUser().getId();
        Map<String,Object> map = new HashMap<>();
        //List<ExamResult> examResultList = new ArrayList<>();
        List<TestVo> testList = new ArrayList<>();
        testList = testService.selectExamByStudentId(student_id);
        map.put("status",testList.size());
        map.put("data",testList);
        return map;
    }


}

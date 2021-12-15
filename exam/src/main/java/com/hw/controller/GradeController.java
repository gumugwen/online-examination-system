package com.hw.controller;

import com.github.pagehelper.PageInfo;
import com.hw.model.Grade;
import com.hw.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  
 * @date 2020/11/17 11:00 下午
 */
@Controller
@RequestMapping("/admin")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping("/grade/list")
    public String toGradeList()
    {
        return "admin/grade/grade-list";
    }

    /**
     * 保存年级
     * @param grade
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/grade/list/page")
    @ResponseBody
    public Map<String,Object> getPage(String grade, Integer page, Integer limit){
        Map<String,Object> map=new HashMap<>();
        List<Grade> list = gradeService.getGradePage(grade,page,limit);
        PageInfo<Grade> gradePageInfo = new PageInfo<Grade>(list);
        map.put("code",0);
        map.put("msg","操作成功");
        map.put("count",gradePageInfo.getTotal());
        map.put("data",gradePageInfo.getList());
        return map;
    }

    @RequestMapping("/grade/add")
    public String toAdd()
    {
        return "admin/grade/grade-add";
    }

    @RequestMapping("/grade/add/do")
    @ResponseBody
    public String doAdd(String name){
        //验证年级名称重复
        Grade grade=gradeService.getGradeByName(name);
        if (grade!=null){//重复了
            return "no";
        }
        int result = gradeService.addGrade(name);
        if (result==1){
            return "ok";
        }
        return null;
    }

    /**
     * 跳转到年级修改页面
     * @param id
     * @return
     */
    @RequestMapping("/grade/edit")
    public String toEdit(Integer id, Model model){
        Grade grade = gradeService.getGradeById(id);
        model.addAttribute("grade",grade);
        return "admin/grade/grade-edit";
    }

    /**
     * 编辑年级名称
     * @param grade
     * @return
     */
    @RequestMapping("/grade/edit/do")
    @ResponseBody
    public String doEdit(Grade grade){
        //验证年级名称重复
        Grade result = gradeService.getGradeByName(grade.getName());
        if(result!=null){//重复了
            return "no";
        }
        int rows = gradeService.updateGradeById(grade);
        if(rows==1){
            return "ok";
        }
        return null;
    }

    /**
     * 逻辑删除年级
     * @param grade
     * @return
     */
    @RequestMapping("/grade/delete")
    @ResponseBody
    public String delete(Grade grade){
        int rows = gradeService.updateGradeById(grade);
        if(rows==1){
            return "ok";
        }
        return null;
    }
}

package com.hw.controller;

import com.github.pagehelper.PageInfo;
import com.hw.model.Teacher;
import com.hw.service.TeacherManagerService;
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
public class TeacherManagerController {

    @Autowired
    private TeacherManagerService teacherService;

    @RequestMapping("/teacher/list")
    public String toTeacherList()
    {
        return "admin/teacher/teacher-list";
    }

    /**
     * 保存年级
     * @param
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/teacher/list/page")
    @ResponseBody
    public Map<String,Object> getPage(String id,String real_name, Integer page, Integer limit){
        Map<String,Object> map=new HashMap<>();
        List<Teacher> list = teacherService.getTeacherPage(id,real_name,page,limit);
        PageInfo<Teacher> teacherPageInfo = new PageInfo<Teacher>(list);
        map.put("code",0);
        map.put("msg","操作成功");
        map.put("count",teacherPageInfo.getTotal());
        map.put("data",teacherPageInfo.getList());
        return map;
    }

    @RequestMapping("/teacher/add")
    public String toAdd()
    {
        return "admin/teacher/teacher-add";
    }

    @RequestMapping("/teacher/add/do")
    @ResponseBody
    public String doAdd(String id,String username ,String password,String real_name){
        Teacher result1 = teacherService.getTeacherByName(real_name);
        Teacher result2 = teacherService.getTeacherById(Integer.parseInt(id));
        if(result1!=null&&result2!=null){//重复了
            return "no";
        }
        int rows = teacherService.addTeacher(id,username,password,real_name);
        if (rows==1){
            return "ok";
        }
        return null;
    }

    /**
     * 跳转到年级修改页面
     * @param id
     * @return
     */
    @RequestMapping("/teacher/edit")
    public String toEdit(Integer id, Model model){
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher",teacher);
        return "admin/teacher/teacher-edit";
    }

    /**
     * 编辑年级名称
     * @param teacher
     * @return
     */
    @RequestMapping("/teacher/edit/do")
    @ResponseBody
    public String doEdit(Teacher teacher){
        //验证年级名称重复
        Teacher result1 = teacherService.getTeacherById(teacher.getId());
        Teacher result2 = teacherService.getTeacherByUserName(teacher.getUsername());
        if(result1!=null&&result2!=null){//重复了
            return "no";
        }
        int rows = teacherService.updateTeacherById(teacher);
        if(rows==1){
            return "ok";
        }
        return null;
    }

    /**
     * 逻辑删除年级
     * @param teacher
     * @return
     */
    @RequestMapping("/teacher/delete")
    @ResponseBody
    public String delete(Teacher teacher){
        int rows = teacherService.updateTeacherById(teacher);
        if(rows==1){
            return "ok";
        }
        return null;
    }
}

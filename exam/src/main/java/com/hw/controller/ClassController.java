package com.hw.controller;

import com.hw.common.AuthUser;
import com.hw.common.TableResult;
import com.hw.model.TbClass;
import com.hw.service.ClassService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author  
 * @date 2020/11/17 11:02 下午
 */
@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequestMapping("/list/teacher-class")
    @ResponseBody
    public TableResult<TbClass> teacherClassList(@RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "limit",defaultValue = "5")Integer size){

        AuthUser authUser = (AuthUser) SecurityUtils.getSubject().getPrincipal();
       return classService.findClassByTeacherId(authUser.getId(),page,size);
    }
}

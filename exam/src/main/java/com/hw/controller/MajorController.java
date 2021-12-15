package com.hw.controller;

import com.github.pagehelper.PageInfo;
import com.hw.model.Major;
import com.hw.service.MajorService;
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
public class MajorController {

    @Autowired
    private MajorService majorService;

    @RequestMapping("/major/list")
    public String toMajorList()
    {
        return "admin/major/major-list";
    }

    /**
     * 保存年级
     * @param major
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/major/list/page")
    @ResponseBody
    public Map<String,Object> getPage(String major, Integer page, Integer limit){
        Map<String,Object> map=new HashMap<>();
        List<Major> list = majorService.getMajorPage(major,page,limit);
        PageInfo<Major> majorPageInfo = new PageInfo<Major>(list);
        map.put("code",0);
        map.put("msg","操作成功");
        map.put("count",majorPageInfo.getTotal());
        map.put("data",majorPageInfo.getList());
        return map;
    }

    @RequestMapping("/major/add")
    public String toAdd()
    {
        return "admin/major/major-add";
    }

    @RequestMapping("/major/add/do")
    @ResponseBody
    public String doAdd(String name){
        //验证年级名称重复
        Major major=majorService.getMajorByName(name);
        if (major!=null){//重复了
            return "no";
        }
        int result = majorService.addMajor(name);
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
    @RequestMapping("/major/edit")
    public String toEdit(Integer id, Model model){
        Major major = majorService.getMajorById(id);
        model.addAttribute("major",major);
        return "admin/major/major-edit";
    }

    /**
     * 编辑年级名称
     * @param major
     * @return
     */
    @RequestMapping("/major/edit/do")
    @ResponseBody
    public String doEdit(Major major){
        //验证年级名称重复
        Major result = majorService.getMajorByName(major.getName());
        if(result!=null){//重复了
            return "no";
        }
        int rows = majorService.updateMajorById(major);
        if(rows==1){
            return "ok";
        }
        return null;
    }

    /**
     * 逻辑删除年级
     * @param major
     * @return
     */
    @RequestMapping("/major/delete")
    @ResponseBody
    public String delete(Major major){
        int rows = majorService.updateMajorById(major);
        if(rows==1){
            return "ok";
        }
        return null;
    }
}

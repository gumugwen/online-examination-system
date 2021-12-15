package com.hw.controller;

import com.github.pagehelper.PageInfo;
import com.hw.model.TbClass;
import com.hw.service.ClazzService;
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
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @RequestMapping("/clazz/list")
    public String toClassList()
    {
        return "admin/clazz/clazz-list";
    }

    /**
     * 保存年级
     * @param
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/clazz/list/page")
    @ResponseBody
    public Map<String,Object> getPage(String cno,String cname, Integer page, Integer limit){
        Map<String,Object> map=new HashMap<>();
        List<TbClass> list = clazzService.getClassPage(cno,cname,page,limit);
        PageInfo<TbClass> clazzPageInfo = new PageInfo<TbClass>(list);
        map.put("code",0);
        map.put("msg","操作成功");
        map.put("count",clazzPageInfo.getTotal());
        map.put("data",clazzPageInfo.getList());
        return map;
    }

    @RequestMapping("/clazz/add")
    public String toAdd()
    {
        return "admin/clazz/clazz-add";
    }

    @RequestMapping("/clazz/add/do")
    @ResponseBody
    public String doAdd(String cno,String cname ,String grade_id,String major_id){
        System.out.println(cno);
        System.out.println(cname);
        TbClass result1 = clazzService.getClassByName(cname);
        TbClass result2 = clazzService.getClassByNo(Integer.parseInt(cno));
        if(result1!=null||result2!=null){//重复了
            return "no";
        }
        int rows = clazzService.addClass(cno,cname,grade_id,major_id);
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
    @RequestMapping("/clazz/edit")
    public String toEdit(Integer id, Model model){
        TbClass clazz = clazzService.getClassById(id);
        model.addAttribute("clazz",clazz);
        return "admin/clazz/clazz-edit";
    }

    /**
     * 编辑年级名称
     * @param clazz
     * @return
     */
    @RequestMapping("/clazz/edit/do")
    @ResponseBody
    public String doEdit(TbClass clazz){
        //验证年级名称重复
        TbClass result1 = clazzService.getClassByNo(clazz.getCno());
        TbClass result2 = clazzService.getClassByName(clazz.getCname());
        if(result1!=null&&result2!=null){//重复了
            return "no";
        }
        int rows = clazzService.updateClassById(clazz);
        if(rows==1){
            return "ok";
        }
        return null;
    }

    /**
     * 逻辑删除年级
     * @param clazz
     * @return
     */
    @RequestMapping("/clazz/delete")
    @ResponseBody
    public String delete(TbClass clazz){
        int rows = clazzService.updateClassById(clazz);
        if(rows==1){
            return "ok";
        }
        return null;
    }
}

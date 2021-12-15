package com.hw.controller;

import com.hw.common.AuthUser;
import com.hw.model.Menu;
import com.hw.model.MenuVO;
import com.hw.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author  
 * @date 2020/11/17 10:59 下午
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/list")
    @ResponseBody
    public MenuVO menuList(){
        AuthUser principal = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        Integer role = principal.getRole();
        MenuVO vo = menuService.findByRoleId(role);
        return vo;
    }
}

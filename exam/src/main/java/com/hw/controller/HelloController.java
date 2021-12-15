package com.hw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author  
 * @date 2020/11/17 4:05 下午
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String toH(){
        return "/WEB-INF/page/login.jsp";
    }
}

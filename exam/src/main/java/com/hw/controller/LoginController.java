package com.hw.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hw.auth.UsernamePasswordTypeToken;
import com.hw.common.RespCode;
import com.hw.common.Result;
import com.hw.common.utils.MD5Utils;
import com.hw.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author  
 * @date 2020/11/18 8:59 上午
 */
@Controller
public class LoginController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;
    private static final String IMAGE_CODE_KEY="exam_image_code";

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/formLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody User loginUser, HttpSession session){
        String code = (String) session.getAttribute(IMAGE_CODE_KEY);
        if (!loginUser.getCaptcha().equals(code)){
            return Result.fail(RespCode.FAIL,"验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordTypeToken(loginUser));
        Object principal = SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("loginUser",principal);

        return Result.ok(RespCode.SUCCESS,"登录成功");
    }

    @RequestMapping("/captcha/{date}")
    public void captcha(@PathVariable("date")String date,HttpServletResponse response,HttpSession session){
        String code = defaultKaptcha.createText();
        System.out.println("生成的图形验证码是：" + code);
        // 字符串把它放到redis中，有效期1分钟
        session.setAttribute(IMAGE_CODE_KEY,code);
        // 获取验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);
        // 将验证码图片写出去
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

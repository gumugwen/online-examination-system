package com.hw.common.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author  
 * @date 2020/10/24 8:52 上午
 */
public class CookieUtils {
    private static final int DEFAULT_AGE=20*60;
    public static void setCookies(HttpServletResponse response, String object, String key){
        Cookie cookie= null;
        try {
            cookie = new Cookie(key, encode(object));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setPath("/");
        handle(response, DEFAULT_AGE, cookie);
    }

    private static String encode(String object) throws UnsupportedEncodingException {
        return URLEncoder.encode(object,"UTF-8");
    }

    public static void setCookies(HttpServletResponse response, String object, String key, int age){
        Cookie cookie= null;
        try {
            cookie = new Cookie(key, encode(object));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setPath("/");
        handle(response, age, cookie);
    }

    public static void setCookies(HttpServletResponse response, Object jsonObject, String key){
        Cookie cookie= null;
        try {
            cookie = new Cookie(key, encode(JSON.toJSONString(jsonObject)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setPath("/");
        handle(response, DEFAULT_AGE, cookie);
    }

    public static void setCookies(HttpServletResponse response, Object jsonObject, String key, int age){
        Cookie cookie= null;
        try {
            cookie = new Cookie(key,encode( JSON.toJSONString(jsonObject)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setPath("/");
        handle(response, age, cookie);
    }

    private static void handle(HttpServletResponse response, int age, Cookie cookie) {
        cookie.setMaxAge(age);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String key){
        for (Cookie cookie : request.getCookies()) {
            if (key.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }

}

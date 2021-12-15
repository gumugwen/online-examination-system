package com.hw.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

/**
 * @author  
 * @date 2020/11/18 4:16 下午
 */
public class MD5Utils {

    public static final    String getMD5(String target,String sault){
        Md5Hash md5Hash=new Md5Hash(target,sault);
        return md5Hash.toString();
    }
}

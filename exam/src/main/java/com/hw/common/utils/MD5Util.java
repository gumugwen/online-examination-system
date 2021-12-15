package com.hw.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {
    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String getMD5(String str){
        if(StringUtils.isNotBlank(str)){
            SimpleHash simpleHash = new SimpleHash("MD5",str,null,1);
            return simpleHash.toString();
        }
        return null;
    }
}

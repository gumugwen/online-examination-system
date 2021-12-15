package com.hw.auth;

import com.hw.common.utils.MD5Utils;
import com.hw.model.User;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author  
 * @date 2020/11/18 2:39 下午
 */
public class UsernamePasswordTypeToken extends UsernamePasswordToken {



    private Integer userType;

    public UsernamePasswordTypeToken(User user) {
        super(user.getUsername(), user.getPassword(),user.isRememberMe());
        this.userType = user.getRole();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}

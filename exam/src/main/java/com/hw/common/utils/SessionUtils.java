package com.hw.common.utils;

import com.hw.common.AuthUser;
import com.hw.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

public class SessionUtils {
    public static AuthUser getSessionUser(){
        /**
         * 获取session中的user信息
         */
        Session session = SecurityUtils.getSubject().getSession();
        SimplePrincipalCollection cll = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        return (AuthUser)cll.getPrimaryPrincipal();
    }
}

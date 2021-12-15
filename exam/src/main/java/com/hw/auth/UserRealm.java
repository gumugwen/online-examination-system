package com.hw.auth;

import com.hw.common.AuthUser;
import com.hw.common.Constant;
import com.hw.common.utils.MD5Utils;
import com.hw.model.*;
import com.hw.service.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;


public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);
    @Autowired
    ManagerService managerService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //此处需要查询数据库
        //获取用户信息，根据用户名查询账号数据
        UsernamePasswordTypeToken token =
                (UsernamePasswordTypeToken) authenticationToken;
        if (token.getUserType().equals(Constant.ADMIN)) {
            Manager manage = managerService.login(token.getUsername());
            if (manage == null) {
                throwException(token);
            }
            //参数一：存入用户对象
            //参数二：存入数据中获取到的密码信息
            //参数三：当前的realm名称
            SimpleAuthenticationInfo info =
                    new SimpleAuthenticationInfo(new AuthUser(manage.getId(), manage.getUsername(), token.getUserType()), manage.getPassword(), getName());
            info.setCredentialsSalt(
                    ByteSource.Util.bytes(token.getUsername().getBytes()));
            return info;
        } else if (Constant.TEACHAER.equals(token.getUserType())) {
            Teacher teacher = teacherService.login(token.getUsername());
            if (teacher == null) {
                throwException(token);
            }

            SimpleAuthenticationInfo info =
                    new SimpleAuthenticationInfo(new AuthUser(teacher.getId(), teacher.getUsername(), token.getUserType()), teacher.getPassword(), getName());
            info.setCredentialsSalt(
                    ByteSource.Util.bytes(token.getUsername().getBytes()));
            return info;
        } else if (Constant.STUDENT.equals(token.getUserType())) {
            Student stu = studentService.login(token.getUsername());
            if (stu == null) {
                throwException(token);
            }

            SimpleAuthenticationInfo info =
                    new SimpleAuthenticationInfo(new AuthUser(stu.getId(), stu.getUsername(), token.getUserType()), stu.getPassword(), getName());
            info.setCredentialsSalt(
                    ByteSource.Util.bytes(token.getUsername().getBytes()));
            return info;
        }
        return null;
    }

    private void throwException(UsernamePasswordTypeToken token) {
        log.info("用户:{},不存在", token.getUsername());
        throw new UnknownAccountException();
    }


    /**
     * 授权
     *
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AuthUser authUser = (AuthUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet<String>() {{
            add(roleService.findRoleNameById(authUser.getRole()));
        }});

        List<Menu> menuList = menuService.menuList(authUser.getRole());

        Set<String> permissions = menuList.stream().map(Menu::getCode).collect(Collectors.toSet());

        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }
}

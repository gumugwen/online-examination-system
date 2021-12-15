package com.hw.service;

import com.hw.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author  
 * @date 2020/11/17 11:04 下午
 */
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public String findRoleNameById(Integer role) {
       return roleMapper.selectByPrimaryKey(role).getRole();
    }
}

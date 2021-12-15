package com.hw.service;

import com.hw.auth.UsernamePasswordTypeToken;
import com.hw.mapper.ManagerMapper;
import com.hw.model.Manager;
import com.hw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author  
 * @date 2020/11/17 11:03 下午
 */
@Service
public class ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    public Manager login(String username){
        return managerMapper.selectByUsername(username);
    }
}

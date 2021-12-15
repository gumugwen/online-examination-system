package com.hw.service;

import com.hw.mapper.TeacherMapper;
import com.hw.model.Teacher;
import com.hw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author  
 * @date 2020/11/17 11:05 下午
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public Teacher login(String username){
        Teacher teacher = teacherMapper.selectByUserName(username);
        return teacher;
    }
}

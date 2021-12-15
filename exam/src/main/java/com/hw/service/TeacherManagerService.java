package com.hw.service;

import com.github.pagehelper.PageHelper;
import com.hw.mapper.TeacherMapper;
import com.hw.model.Teacher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  
 * @date 2020/11/17 11:03 下午
 */
@Service
public class TeacherManagerService {

    @Autowired
    private TeacherMapper teacherMapper;

    
    public List<Teacher> getTeacherPage(String id,String real_name, Integer page, Integer limit){
        PageHelper.startPage(page,limit);
        List<Teacher> list=teacherMapper.selectAll(id,real_name);
        return list;
    }

//    public TableResult<Teacher> findTeacherByTeacherId(Integer teacherId, Integer page, Integer size) {
//        PageHelper.startPage(page,size);
//        PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(teacherMapper.selectByTeacherId(teacherId));
//        TableResult<Teacher> tableResult = new TableResult<>();
//        tableResult.setCode(0);
//        tableResult.setCount((int) pageInfo.getTotal());
//        tableResult.setMsg("ok");
//        tableResult.setData(pageInfo.getList());
//        return tableResult;
//    }
    
    public int addTeacher(String id,String username ,String password,String real_name){
        if (StringUtils.isNotBlank(id)&&StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)&&StringUtils.isNotBlank(real_name)){
            Teacher teacher = new Teacher();
            teacher.setId(Integer.parseInt(id));
            teacher.setUsername(username);
            teacher.setPassword(password);
            teacher.setRealName(real_name);
            return teacherMapper.insert(teacher);
        }
        return 0;
    }

    
    public Teacher getTeacherByName(String name){
        if (StringUtils.isNotBlank(name)){
            return teacherMapper.selectByName(name);
        }
        return null;
    }

    public Teacher getTeacherByUserName(String username){
        if (StringUtils.isNotBlank(username)){
            return teacherMapper.selectByUserName(username);
        }
        return null;
    }

    


    /**
     * 根据主键查询年级信息
     * @param id
     * @return
     */
    
    public Teacher getTeacherById(Integer id){
        if (id!=null){
            return teacherMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    /**
     * 根据主键修改年级名称
     * @param teacher
     * @return
     */
    
    public int updateTeacherById(Teacher teacher) {
        if(teacher!=null){
            return teacherMapper.updateByPrimaryKeySelective(teacher);
        }
        return 0;
    }
}

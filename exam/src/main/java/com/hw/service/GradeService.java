package com.hw.service;

import com.github.pagehelper.PageHelper;
import com.hw.mapper.GradeMapper;
import com.hw.model.Grade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  
 * @date 2020/11/17 11:03 下午
 */
@Service
public class GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    
    public List<Grade> getGradePage(String grade,Integer page,Integer limit){
        PageHelper.startPage(page,limit);
        List<Grade> list=gradeMapper.selectAll(grade);
        System.out.println(grade);
        System.out.println(list.toString());
        return list;
    }

    
    public int addGrade(String name){
        if (StringUtils.isNotBlank(name)){
            Grade grade = new Grade();
            grade.setName(name);
            return gradeMapper.insert(grade);
        }
        return 0;
    }

    
    public Grade getGradeByName(String name){
        if (StringUtils.isNotBlank(name)){
            return gradeMapper.selectByName(name);
        }
        return null;
    }

    /**
     * 根据主键查询年级信息
     * @param id
     * @return
     */
    
    public Grade getGradeById(Integer id){
        if (id!=null){
            return gradeMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    /**
     * 根据主键修改年级名称
     * @param grade
     * @return
     */
    
    public int updateGradeById(Grade grade) {
        if(grade!=null){
            return gradeMapper.updateByPrimaryKeySelective(grade);
        }
        return 0;
    }
}

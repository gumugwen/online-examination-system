package com.hw.service;

import com.github.pagehelper.PageHelper;
import com.hw.mapper.MajorMapper;
import com.hw.model.Major;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  
 * @date 2020/11/17 11:03 下午
 */
@Service
public class MajorService {

    @Autowired
    private MajorMapper majorMapper;

    
    public List<Major> getMajorPage(String major,Integer page,Integer limit){
        PageHelper.startPage(page,limit);
        List<Major> list=majorMapper.selectAll(major);
        return list;
    }

    
    public int addMajor(String name){
        if (StringUtils.isNotBlank(name)){
            Major major = new Major();
            major.setName(name);
            return majorMapper.insert(major);
        }
        return 0;
    }

    
    public Major getMajorByName(String name){
        if (StringUtils.isNotBlank(name)){
            return majorMapper.selectByName(name);
        }
        return null;
    }

    /**
     * 根据主键查询专业信息
     * @param id
     * @return
     */
    
    public Major getMajorById(Integer id){
        if (id!=null){
            return majorMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    /**
     * 根据主键修改专业名称
     * @param major
     * @return
     */
    
    public int updateMajorById(Major major) {
        if(major!=null){
            return majorMapper.updateByPrimaryKeySelective(major);
        }
        return 0;
    }
}

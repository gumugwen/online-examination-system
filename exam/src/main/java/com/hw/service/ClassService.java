package com.hw.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hw.common.TableResult;
import com.hw.mapper.TbClassMapper;
import com.hw.model.TbClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author  
 * @date 2020/11/17 11:03 下午
 */
@Service
public class ClassService {
    @Autowired
    private TbClassMapper tbClassMapper;

    /**
     * 查询教师的所有班级
     * @param teacherId 教师id
     */
    public TableResult<TbClass> findClassByTeacherId(Integer teacherId,Integer page,Integer size) {
        PageHelper.startPage(page,size);
        PageInfo<TbClass> pageInfo=new PageInfo<>(tbClassMapper.selectByTeacherId(teacherId));
        TableResult<TbClass> tableResult = new TableResult<>();
        tableResult.setCode(0);
        tableResult.setCount((int) pageInfo.getTotal());
        tableResult.setMsg("ok");
        tableResult.setData(pageInfo.getList());
        return tableResult;
    }
}

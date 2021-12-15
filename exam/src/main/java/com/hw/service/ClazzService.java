package com.hw.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hw.common.TableResult;
import com.hw.mapper.TbClassMapper;
import com.hw.model.TbClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  
 * @date 2020/11/17 11:03 下午
 */
@Service
public class ClazzService {

    @Autowired
    private TbClassMapper clazzMapper;

    
    public List<TbClass> getClassPage(String cno, String cname,Integer page,Integer limit){
        PageHelper.startPage(page,limit);
        List<TbClass> list=clazzMapper.selectAll(cno,cname);
        return list;
    }

    public TableResult<TbClass> findClassByTeacherId(Integer teacherId, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        PageInfo<TbClass> pageInfo=new PageInfo<>(clazzMapper.selectByTeacherId(teacherId));
        TableResult<TbClass> tableResult = new TableResult<>();
        tableResult.setCode(0);
        tableResult.setCount((int) pageInfo.getTotal());
        tableResult.setMsg("ok");
        tableResult.setData(pageInfo.getList());
        return tableResult;
    }
    
    public int addClass(String cno,String cname ,String grade_id,String major_id){
        if (StringUtils.isNotBlank(cno)&&StringUtils.isNotBlank(cname)&&StringUtils.isNotBlank(grade_id)&&StringUtils.isNotBlank(major_id)){
            TbClass clazz = new TbClass();
            clazz.setCno(Integer.parseInt(cno));
            clazz.setCname(cname);
            clazz.setGradeId(Integer.parseInt(grade_id));
            clazz.setMajorId(Integer.parseInt(major_id));
            return clazzMapper.insert(clazz);
        }
        return 0;
    }

    
    public TbClass getClassByName(String name){
        if (StringUtils.isNotBlank(name)){
            return clazzMapper.selectByName(name);
        }
        return null;
    }

    
    public TbClass getClassByNo(Integer no){
        if (no!=null){
            return clazzMapper.selectByNo(no);
        }
        return null;
    }

    /**
     * 根据主键查询年级信息
     * @param id
     * @return
     */
    
    public TbClass getClassById(Integer id){
        if (id!=null){
            return clazzMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    /**
     * 根据主键修改年级名称
     * @param clazz
     * @return
     */
    
    public int updateClassById(TbClass clazz) {
        if(clazz!=null){
            return clazzMapper.updateByPrimaryKeySelective(clazz);
        }
        return 0;
    }
}

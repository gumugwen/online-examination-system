package com.hw.mapper;

import com.hw.model.TbClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbClass record);

    int insertSelective(TbClass record);

    TbClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbClass record);

    int updateByPrimaryKey(TbClass record);

    List<TbClass> selectByTeacherId(Integer teacherId);

    /**
     * 根据考卷id查询参考班级
     * @param id
     * @return
     */
    List<TbClass> selectExamId(Integer id);

    List<TbClass> selectAllClass();

    List<TbClass> selectAll(@Param("cno") String cno, @Param("cname") String cname);

    TbClass selectByName(String name);

    TbClass selectByNo(Integer cno);
}
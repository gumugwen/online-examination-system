package com.hw.mapper;

import com.hw.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> findByRoleId(Integer role);

    Menu selectParentMenu(Integer role);

    List<Menu> findByRoleIdAndPid(@Param("role") Integer role,@Param("pid") Integer pid);
}
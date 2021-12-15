package com.hw.mapper;

import com.hw.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    /**
     * 根据用户名和密码查询老师
     * @param username
     * @param password
     * @return
     */
    Teacher selectByUsernameAndPassword(String username, String password);

    Teacher selectByUserName(String username);

    List<Teacher> selectAll(@Param("id") String id, @Param("real_name") String real_name);

    Teacher selectByName(String name);
}
package com.hw.mapper;

import com.hw.model.Grade;
import com.hw.model.Student;
import com.hw.model.vo.StudentListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student selectByUsernameAndPassword(String username, String password);

    Student selectByUsername(String username);

    int selectClassIdStudentById(Integer id);

    String selectUsernameStudentById(Integer id);

    List<StudentListVo> selectAllStudent(@Param("realName") String realName,@Param("className") Integer className);

    List<Student> getExcelTemplate();


    int insertBatch(List<Student> list);
}
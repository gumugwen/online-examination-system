package com.hw.controller;

import com.hw.common.Constant;
import com.hw.common.utils.ExcelUtil;
import com.hw.common.utils.MD5Util;
import com.hw.common.utils.MD5Utils;
import com.hw.model.Grade;
import com.hw.model.Student;
import com.hw.model.TbClass;
import com.hw.model.User;
import com.hw.model.vo.StudentListVo;
import com.hw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student/manager")
public class StudentManagerController {
    @Autowired
    private StudentService studentService;

    //注入默认密码
    @Value("#{properties['student.password']}")
    private String intiPassword;

    /**
     * 跳转到学生添加页面
     * @return
     */
    @RequestMapping("/add")
    public String toStudentAdd(Model model){
        model.addAttribute("initPassword",intiPassword);
        model.addAttribute("roleId", Constant.STUDENT);
        return "student/student-add";
    }

    /**
     * 跳转到学生编辑页面
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String toStudentEdit(Integer id,Model model){
        System.out.println(id);
        Student student = studentService.getUserById(id);
        System.out.println(student.toString());
        List<TbClass> tbClassList = studentService.getClassInformation();
        model.addAttribute("tbClassList",tbClassList);
        model.addAttribute("user",student);
        model.addAttribute("student",student);
        return "student/student-edit";
    }


    /**
     * 添加学生
     * @param student
     * @return
     */
    @RequestMapping("/add/do")
    @ResponseBody
    public String doStudentAdd(Student student){
        student.setPassword(MD5Util.getMD5(intiPassword));
        student.setModified(false);
        String result = studentService.insertStudent(student);
        return result;
    }

    /**
     * 修改用户信息
     * @param studentListVo
     * @return
     */
    @RequestMapping("/edit/do")
    @ResponseBody
    public String doStudentEdit(StudentListVo studentListVo){
        System.out.println(studentListVo.toString());
        Student student = new Student();
        student.setId(studentListVo.getId());
        student.setRealName(studentListVo.getRealName());
        student.setClassId(Integer.parseInt(studentListVo.getClassName()));
        int rows = studentService.updateStudent(student);
        if(rows==1){
            return "ok";
        }
        return "no";
    }

    /**
     * 逻辑删除学生账号
     * @param student
     * @return
     */
    @RequestMapping("/delete/do")
    @ResponseBody
    public String delete(Student student){
        int rows = studentService.updateStudentById(student);
        if(rows==1){
            return "ok";
        }
        return null;
    }

    /**
     * 导出模板
     * @param response
     * @param session
     */
    @RequestMapping("/excel_template")
    public void printExcelTemplate(HttpServletResponse response, HttpSession session){
        List<Student> list = studentService.getExcelTemplate();
        try{
            ExcelUtil.printEasyExcelTemplate(Student.class,list,"student-template",response,session);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 导入数据
     * @param file
     * @return
     */
    @RequestMapping("/excel/import")
    @ResponseBody
    public Map importExcel(MultipartFile file){
        Map map = new HashMap();
        try{
            List<Student> list = ExcelUtil.importExcel(file,Student.class);
            System.out.println(list.toString());
            for(int i = 0;i<list.size();i++){
                list.get(i).setPassword(MD5Util.getMD5(intiPassword));
            }
            studentService.insertUserBatch(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        map.put("code","0");
        map.put("msg","");
        map.put("data","");
        return map;
    }
}

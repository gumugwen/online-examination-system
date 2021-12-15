package com.hw.service;

import com.github.pagehelper.PageHelper;
import com.hw.common.Constant;
import com.hw.mapper.*;
import com.hw.model.*;
import com.hw.model.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author  
 * @date 2020/11/17 11:05 下午
 */
@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private QuestionJudgeMapper questionJudgeMapper;

    @Autowired
    private QuestionMultiMapper questionMultiMapper;

    @Autowired
    private QuestionSingleMapper questionSingleMapper;

    @Autowired
    private ExamResultMapper examResultMapper;

    @Autowired
    private ExamRecordMapper examRecordMapper;

    @Autowired
    private ExamResultQuestionMapper examResultQuestionMapper;

    @Autowired
    private TbClassMapper tbClassMapper;

    public Student login(String username) {
        return studentMapper.selectByUsername(username);
    }

    /**
     * 获取学生需要进行的考试信息(分页查询）
     * @param page
     * @param limit
     * @param id
     * @return
     */
    public List<ExamListForStudentVo> getExamList(Integer page, Integer limit,Integer id) {
        //开启分页查询。（一定要在调用列表查询的代码上面）
        PageHelper.startPage(page, limit);
        int classId = studentMapper.selectClassIdStudentById(id);
        List<ExamListForStudentVo> list = examMapper.selectExamById(classId);
        return list;
    }

    /**
     * 获取学生列表（分页查询）
     * @param page
     * @param limit
     * @return
     */
    public List<StudentListVo> getStudentList(Integer page,Integer limit,String realName,Integer className){
        PageHelper.startPage(page,limit);
        List<StudentListVo> list = studentMapper.selectAllStudent(realName,className);
        return list;
    }

    /**
     * 获取所有的班级（用于模糊查询）
     * @return
     */
    public List<TbClass> getClassInformation(){
        List<TbClass> tbClassList = tbClassMapper.selectAllClass();
        return tbClassList;
    }

    /**
     * 获取对应的判断题
     * @param exam_id
     * @return
     */
    public List<QuestionJudge> getJudgeList(Integer exam_id){
        List<QuestionJudge> list = questionJudgeMapper.selectQuestionByExamId(exam_id);
        return list;
    }

    /**
     * 获取对应的多选题
     * @param exam_id
     * @return
     */
    public List<QuestionMulti> getMultiList(Integer exam_id){
        List<QuestionMulti> list = questionMultiMapper.selectQuestionByExamId(exam_id);
        return list;
    }

    /**
     * 获取对应的单选题
     * @param exam_id
     * @return
     */
    public List<QuestionSingle> getSingleList(Integer exam_id){
        List<QuestionSingle> list = questionSingleMapper.selectQuestionByExamId(exam_id);
        return list;
    }

    public String getStudentUsername(){
        return null;
    }

    /**
     * 获取考试信息
     * @param exam_id
     * @return
     */
    public Exam getExamInformation(Integer exam_id){
        Exam exam = examMapper.selectByPrimaryKey(exam_id);
        return exam;
    }

    /**
     * 获取对应题目的数量
     * @param exam_id
     * @return
     */
    public int getJudgeCount(Integer exam_id){
        Integer count = questionJudgeMapper.selectQuestionCountByExamId(exam_id);
        return count;
    }

    public int getMultiCount(Integer exam_id){
        Integer count = questionMultiMapper.selectQuestionCountByExamId(exam_id);
        return count;
    }

    public int getSingleCount(Integer exam_id){
        Integer count = questionSingleMapper.selectQuestionCountByExamId(exam_id);
        return count;
    }

    /**
     * 将数据插入考试结果表
     * @param examResult
     * @return
     */
    public int insertExamResult(ExamResult examResult){
        Integer count = examResultMapper.insertSelective(examResult);
        return count;
    }

    /**
     * 通过试卷id和学生id来查找考试记录
     * @param exam_id
     * @param student_id
     * @return
     */
    public Integer selectResultId(Integer exam_id,Integer student_id){
        //System.out.println("this is service:" +exam_id+","+student_id);
        Integer count = examResultMapper.selectExamResultByStudentExamId(exam_id,student_id);
        return count;
    }

    /**
     * 判断是否存在数据（即是否参加过考试）
     * @param exam_id
     * @param student_id
     * @return
     */
    public Integer selectResultIdBy(Integer exam_id,Integer student_id){
        Integer count = examResultMapper.selectExamResultByStudentExamIdBy(exam_id,student_id);
        return count;
    }

    /**
     * 插入记录表
     * @param list
     * @return
     */
    public int insertExamRecordByList(List<ExamRecord> list){
        if(list.size() == 0){
            return 0;
        }else{
            int count = examRecordMapper.insertExamRecordByList(list);
            return count;
        }
    }

    /**
     * 通过题目的id和题目的类型查找正确答案
     * @param question_id
     * @return
     */
    public String selectCorrectAnswer(Integer question_id,Integer type){
        //System.out.println(question_id);
        if(type == Constant.SINGLE){
            QuestionSingle questionSingle = new QuestionSingle();
            questionSingle = questionSingleMapper.selectByPrimaryKey(question_id);
            return questionSingle.getAnswer();
        }else if(type == Constant.MULTI){
            QuestionMulti questionMulti = new QuestionMulti();
            questionMulti = questionMultiMapper.selectByPrimaryKey(question_id);
            return questionMulti.getAnswer();
        }else if(type == Constant.JUDGE){
            QuestionJudge questionJudge = new QuestionJudge();
            questionJudge = questionJudgeMapper.selectByPrimaryKey(question_id);
            return questionJudge.getAnswer().toString();
        }else{
            return null;
        }

    }

    /**
     * 通过题目的id和题目的类型查找得分
     * @param question_id
     * @param type
     * @return
     */
    public Double selectScore(Integer question_id,Integer type){
        if(type == Constant.SINGLE){
            QuestionSingle questionSingle = new QuestionSingle();
            questionSingle = questionSingleMapper.selectByPrimaryKey(question_id);
            return questionSingle.getScore();
        }else if(type == Constant.MULTI){
            QuestionMulti questionMulti = new QuestionMulti();
            questionMulti = questionMultiMapper.selectByPrimaryKey(question_id);
            return questionMulti.getScore();
        }else if(type == Constant.JUDGE){
            QuestionJudge questionJudge = new QuestionJudge();
            questionJudge = questionJudgeMapper.selectByPrimaryKey(question_id);
            return questionJudge.getScore();
        }else{
            return null;
        }

    }

    /**
     * 执行插入考试结果表的操作
     * @param list
     * @return
     */
    public int insertExamResultQuestion(List<ExamResultQuestion> list){
        if(list.size()==0){
            return 0;
        }else{
            int count = examResultQuestionMapper.insertExamResultQuestiionByList(list);
            return count;
        }
    }

    /**
     * 获取考试的分数
     * @param exam_result_id
     * @return
     */
    public int selectScoreCount(Integer exam_result_id){
        System.out.println(exam_result_id);
        int count = examResultQuestionMapper.selectScoreByExamId(exam_result_id);
        return count;
    }

    /**
     * 更新成绩
     * @param exam_result_id
     * @param score
     * @return
     */
    public int updateExamResult(Integer exam_result_id,Integer score){
        int count = examResultMapper.updateById(exam_result_id,score);
        return count;
    }

    /**
     * 添加新的学生账户
     * @param student
     * @return
     */
    public String insertStudent(Student student) {
        if(student!=null){
            //判断账号是否已存在
            if(isStudentExist(student.getUsername())){
                return "exist";
            }else{
                int rows = studentMapper.insertSelective(student);
                if(rows==1){
                    return "ok";
                }
            }
        }
        return null;
    }

    /**
     * 判断用户名是否已存在
     * @param username
     * @return
     */
    public Boolean isStudentExist(String username){
        if(StringUtils.isNotBlank(username)){
            Student student = studentMapper.selectByUsername(username);
            if(student!=null){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据主键查询Student
     * @param id
     * @return
     */
    public Student getUserById(Integer id) {
        if(id!=null){
            return studentMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    public int updateStudent(Student student) {
        System.out.println(student.toString());
        if(student!=null){
            return studentMapper.updateByPrimaryKeySelective(student);
        }
        return 0;
    }

    /**
     * 删除学生账号
     * @param student
     * @return
     */
    public int updateStudentById(Student student) {
        if(student!=null){
            return studentMapper.updateByPrimaryKeySelective(student);
        }
        return 0;
    }

    public List<Student> getExcelTemplate() {
        return studentMapper.getExcelTemplate();
    }

    /**
     * 从Excel导入数据
     * @param list
     */
    public int insertUserBatch(List<Student> list) {
        if(list!=null && list.size()>0){
            return studentMapper.insertBatch(list);
        }
        return 0;
    }

    /**
     * 展示考试结果列表
     * @param page
     * @param limit
     * @param id
     * @return
     */
    public List<ExamResult> getExamResultList(Integer page, Integer limit, Integer id,String examName) {
        //开启分页查询。（一定要在调用列表查询的代码上面）
        PageHelper.startPage(page, limit);
        List<ExamResult> list = null;
        if(examName != null){
            list = examResultMapper.selectExamResultByStudentId(id,examName);
        }else{
            list = examResultMapper.selectExamResultByStudentId(id,"");
        }
        return list;
    }

    /**
     * 获取批改后的单选题列表
     * @param result_id
     * @return
     */
    public List<SingleQuestionResultVo> getSingleQuestions(Integer result_id){
        List<SingleQuestionResultVo> singleQuestionResultVoList = examRecordMapper.selectUnitSingleMapByResultId(result_id);
        //System.out.println("this is service111:"+singleQuestionResultVoList.toString());
        return singleQuestionResultVoList;
    }

    /**
     * 获取批改后的多选题列表
     * @param result_id
     * @return
     */
    public List<MultiQuestionResultVo> getMultiQuestions(Integer result_id){
        List<MultiQuestionResultVo> multiQuestionResultVoList = examRecordMapper.selectUnitMultiMapByResultId(result_id);
        //System.out.println("this is service222:"+multiQuestionResultVoList.toString());
        return multiQuestionResultVoList;
    }

    /**
     * 获取批改后的判断题列表
     * @param result_id
     * @return
     */
    public List<JudgeQustionResultVo> getJudgeQutstions(Integer result_id){
        List<JudgeQustionResultVo> judgeQustionResultVoList = examRecordMapper.selectUnitJudgeMapByResultId(result_id);
        //System.out.println("this is service333:"+judgeQustionResultVoList.toString());
        return judgeQustionResultVoList;
    }

}

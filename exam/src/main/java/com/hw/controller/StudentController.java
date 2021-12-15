package com.hw.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.hw.common.AuthUser;
import com.hw.common.Constant;
import com.hw.common.utils.JsonToObjectUtils;
import com.hw.common.utils.SessionUtils;
import com.hw.model.*;
import com.hw.model.vo.*;
import com.hw.service.ExamService;
import com.hw.service.StudentService;
import org.apache.http.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author  
 * @date 2020/11/17 11:02 下午
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //注入默认密码
    @Value("#{properties['student.password']}")
    private String intiPassword;

    /**
     * 跳转到学生考试界面
     *
     * @return
     */
    @RequestMapping("toStudentIndex")
    public String toStudentIndex() {
        return "student/student-index";
    }

    /**
     * 跳转到学生参加考试界面
     *
     * @return
     */
    @RequestMapping("toStudentExam")
    public String toStudentExam(HttpServletRequest request) {
        return "student/student-index";
    }

    /**
     * 跳转到学生考试记录界面
     *
     * @return
     */
    @RequestMapping("toStudentRecord")
    public String toStudentRecord() {
        return "student/student_record";
    }

    /**
     * 跳转到学生管理界面
     * @return
     */
    @RequestMapping("toStudentAdmin")
    public String toStudentAdmin(HttpServletRequest request){
        List<TbClass> tbClassList = studentService.getClassInformation();
        request.setAttribute("tbClassList",tbClassList);
        return "student/student-admin";
    }

    /**
     * 跳转到展示学生考试结果界面
     * @return
     */
    @RequestMapping("toStudentRecord/list")
    public String toStudentRecordList(Integer exam_result_id,Integer examId, Model model){
        Integer id = SessionUtils.getSessionUser().getId();
        String username = SessionUtils.getSessionUser().getUsername();
        Exam exam = studentService.getExamInformation(examId);
        Integer timeLimit = exam.getTimeLimit();
        String examName = exam.getTitle();
        List<SingleQuestionResultVo> singleQuestionResultVoList = studentService.getSingleQuestions(exam_result_id);
        List<MultiQuestionResultVo> multiQuestionResultVoList = studentService.getMultiQuestions(exam_result_id);
        List<JudgeQustionResultVo> judgeQustionResultVoList = studentService.getJudgeQutstions(exam_result_id);
        Integer singleCount = studentService.getSingleCount(examId);
        Integer judgeCount = studentService.getJudgeCount(examId);
        Integer MultiCount = studentService.getMultiCount(examId);
        //System.out.println("exam_result_id"+exam_result_id);
        //System.out.println("examId"+examId);
        //System.out.println(singleQuestionResultVoList.toString());
        //System.out.println(multiQuestionResultVoList.toString());
        //System.out.println(judgeQustionResultVoList.toString());
        model.addAttribute("singleList",singleQuestionResultVoList);
        model.addAttribute("multiList",multiQuestionResultVoList);
        model.addAttribute("judgeList",judgeQustionResultVoList);
        model.addAttribute("student_username", username);
        model.addAttribute("timeLimit", timeLimit);
        model.addAttribute("examName", examName);
        model.addAttribute("examId", examId);
        model.addAttribute("singleCount", singleCount);
        model.addAttribute("judgeCount", judgeCount);
        model.addAttribute("MultiCount", MultiCount);
        return "student/student_record_list";
    }

    /**
     * 展示学生管理的数据
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/student_admin")
    @ResponseBody
    public Map<String, Object> getPageAdmin(Integer page, Integer limit,String realName,String className) {
        Map<String, Object> map = new HashMap<>();
        Integer id = SessionUtils.getSessionUser().getId();
        List<StudentListVo> list = new ArrayList<StudentListVo>();
        if(className!=null&&className!=""){
            list = studentService.getStudentList(page,limit,realName,Integer.parseInt(className));
        }else{
            list = studentService.getStudentList(page,limit,realName,null);
        }
        //将列表数据封装到PageInfo中
        PageInfo<StudentListVo> pageInfo = new PageInfo<StudentListVo>(list);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 处理学生需要考试界面的请求
     *
     * @return
     */
    @RequestMapping("/exam/list")
    @ResponseBody
    public Map<String, Object> getPage(Integer page, Integer limit,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Integer id = SessionUtils.getSessionUser().getId();
        List<ExamListForStudentVo> list = studentService.getExamList(page, limit, id);
        //将列表数据封装到PageInfo中
        PageInfo<ExamListForStudentVo> pageInfo = new PageInfo<ExamListForStudentVo>(list);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        List<TbClass> tbClassList = studentService.getClassInformation();
        request.setAttribute("tbClassList",tbClassList);
        return map;
    }

    /**
     * 处理学生考试记录界面的请求
     *
     * @return
     */
    @RequestMapping("/exam/record/list")
    @ResponseBody
    public Map<String, Object> getPageRecord(Integer page, Integer limit,String examName) {
        Map<String, Object> map = new HashMap<>();
        Integer id = SessionUtils.getSessionUser().getId();
        List<ExamResult> list = studentService.getExamResultList(page, limit, id,examName);
        //将列表数据封装到PageInfo中
        PageInfo<ExamResult> pageInfo = new PageInfo<ExamResult>(list);
        //System.out.println(pageInfo.toString());
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 跳转到考试页面，进行考试，展示考试试卷
     *
     * @return
     */
    @RequestMapping("/exam/list/begin")
    public String toExamList(Integer exam_id, HttpServletRequest request) {
        //试卷的各类信息
        Integer id = SessionUtils.getSessionUser().getId();
        String username = SessionUtils.getSessionUser().getUsername();
        Exam exam = studentService.getExamInformation(exam_id);
        Integer timeLimit = exam.getTimeLimit();
        String examName = exam.getTitle();
        //考试状态
        Integer examId = exam_id;
        Integer examStatus = selectExam(exam_id,id);
        int examAduit = 0;
        if(examStatus != null){
            examAduit = examStatus;
        }
        //题目
        List<QuestionSingle> questionSingleList = studentService.getSingleList(exam_id);
        List<QuestionJudge> questionJudgeList = studentService.getJudgeList(exam_id);
        List<QuestionMulti> questionMultiList = studentService.getMultiList(exam_id);
        int single_count =0;
        for(int i = 0; i<questionSingleList.size();i++){
            single_count+= questionSingleList.get(i).getScore();
        }
        int judge_count =0;
        for(int i = 0; i<questionSingleList.size();i++){
            judge_count+= questionSingleList.get(i).getScore();
        }
        int multi_count =0;
        for(int i = 0; i<questionSingleList.size();i++){
            multi_count+= questionSingleList.get(i).getScore();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String examStart = df.format(new Date());
        Integer singleCount = studentService.getSingleCount(exam_id);
        Integer judgeCount = studentService.getJudgeCount(exam_id);
        Integer MultiCount = studentService.getMultiCount(exam_id);
        request.setAttribute("examStart", examStart);
        request.setAttribute("student_username", username);
        request.setAttribute("timeLimit", timeLimit);
        request.setAttribute("examName", examName);
        request.setAttribute("examId", examId);
        request.setAttribute("singleList", questionSingleList);
        request.setAttribute("judgeList", questionJudgeList);
        request.setAttribute("multiList", questionMultiList);
        request.setAttribute("singleCount", singleCount);
        request.setAttribute("judgeCount", judgeCount);
        request.setAttribute("MultiCount", MultiCount);
        request.setAttribute("examAduit", examAduit);
        request.setAttribute("single_count", single_count);
        request.setAttribute("judge_count", judge_count);
        request.setAttribute("multi_count", multi_count);
        return "student/student-join-exam";
    }

    /**
     * 判断考试状态
     * @param examId
     * @return
     */
    @RequestMapping("exam/list/judgeStatus")
    @ResponseBody
    public String JudgeExamStatus(String examId){
        Integer id = SessionUtils.getSessionUser().getId();
        System.out.println(examId);
        Integer examId1 = Integer.parseInt(examId);
        Integer examStatus = selectExam(examId1,id);
        int examAduit = 0;
        if(examStatus != null){
            examAduit = examStatus;
        }
        String advice = String.valueOf(examAduit);
        return advice;
    }

    /**
     * 判断是否有题目，以显示页面大标题
     *
     * @return
     */
    @RequestMapping("/exam/list/load")
    @ResponseBody
    public Map<String, Object> getExamQuestion(Integer exam_id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Integer id = SessionUtils.getSessionUser().getId();
        List<QuestionSingle> questionSingleList = studentService.getSingleList(exam_id);
        List<QuestionJudge> questionJudgeList = studentService.getJudgeList(exam_id);
        List<QuestionMulti> questionMultiList = studentService.getMultiList(exam_id);
        map.put("single", questionSingleList);
        map.put("judge", questionJudgeList);
        map.put("multi", questionMultiList);

        return map;
    }

    /**
     * 将学生提交的答案进行保存，批改，登分
     * @param map
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping("/exam/list/save")
    @ResponseBody
    public Map<String, String> saveExamAnswer(@RequestBody String map) throws IOException, ParseException {
        //System.out.println(map.toString());
        Map<String, String> map1 = new HashMap<>();
        if(map!=null&&map!=""){
            QuestionAllTypeRecord questionAllTypeRecord = new QuestionAllTypeRecord();

            //封装到对应的类中
            List<JudgeRecord> judgeRecordList = JsonToObjectUtils.getJudgeRecord(map);
            List<MultiRecord> multiRecordList = JsonToObjectUtils.getMultiRecord(map);
            List<SingleRecord> singleRecordList = JsonToObjectUtils.getSingleRecord(map);
            questionAllTypeRecord.setJudgeRecordList(judgeRecordList);
            questionAllTypeRecord.setMultiRecordList(multiRecordList);
            questionAllTypeRecord.setSingleRecordList(singleRecordList);
            System.out.println(questionAllTypeRecord.toString());

            /*开始存入考试结果表中*/
            ExamResult examResult = new ExamResult();
            //获取考试id
            String examIdstr = JsonToObjectUtils.getExamId(map);
            int examId = Integer.parseInt(examIdstr);
            //获取考试开始时间
            String examStartTime = JsonToObjectUtils.getExamStartTime(map);
            //获取考试结束时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String examEndTime = df.format(new Date());
            //根据考试id获取考试信息
            Exam exam = studentService.getExamInformation(examId);
            //获取单选题的题目总数量
            int single_count = questionAllTypeRecord.getSingleRecordList().size();
            //获取多选题的题目总数量
            int multi_count = questionAllTypeRecord.getMultiRecordList().size();
            //获取判断题的题目总数量
            int judge_count = questionAllTypeRecord.getJudgeRecordList().size();
            examResult.setStartTime(df.parse(examStartTime));
            examResult.setEndTime(df.parse(examEndTime));
            examResult.setExamTitle(exam.getTitle());
            examResult.setExamId(examId);
            examResult.setStudentId(SessionUtils.getSessionUser().getId());
            examResult.setDelFlag(0);
            examResult.setStatus(2);
            //存入结果表中
            int examResult_count = studentService.insertExamResult(examResult);

            //获取刚刚插入的结果表的id
            int resultId = studentService.selectResultId(examId, SessionUtils.getSessionUser().getId());
            List<ExamRecord> examRecordList = new ArrayList<>();
            //获取记录表的集合
            examRecordList = getExamRecordlist(questionAllTypeRecord,resultId,examId);
            System.out.println(examRecordList.toString());
            //存入记录表中
            int examRecord_Count = studentService.insertExamRecordByList(examRecordList);
            //存入批改表中
            int examResultQuestion_count = insertExamResultQuestion(examRecordList,resultId);
            //更新考试记录中的成绩
            int updateExamResult_count = updateExamResult(resultId);
            System.out.println(examResult_count+","+examRecord_Count+","+examResultQuestion_count+","+updateExamResult_count);
            if(examResult_count>0&&examRecord_Count>0&&examResultQuestion_count>0&&updateExamResult_count>0){
                map1.put("test", "1");
            }else{
                map1.put("test", "0");
            }
        }
        return map1;
    }

    @RequestMapping("/exam/list/test")
    @ResponseBody
    public Map<String, String> saveExamAnswer1(@RequestBody String map) throws IOException, ParseException {
        Map<String, String> map1 = new HashMap<>();
        map1.put("test", "1");
        return map1;
    }

    /**
     * 返回记录表
     * @param questionAllTypeRecord
     * @param resultId
     * @param examId
     * @return
     */
    public List<ExamRecord> getExamRecordlist(QuestionAllTypeRecord questionAllTypeRecord,Integer resultId,Integer examId){
        //将数据存入记录表中
        List<ExamRecord> examRecordList = new ArrayList<>();
        for (int i = 0; i < questionAllTypeRecord.getSingleRecordList().size(); i++) {
            ExamRecord examRecord = new ExamRecord();
            examRecord.setResultId(resultId);
            examRecord.setQuestionsNumber(questionAllTypeRecord.getSingleRecordList().get(i).getQuestion_number());
            examRecord.setAnswer(questionAllTypeRecord.getSingleRecordList().get(i).getAnswer());
            examRecord.setQtype(1);
            examRecord.setExamId(examId);
            examRecord.setQuestionId(questionAllTypeRecord.getSingleRecordList().get(i).getId());
            examRecordList.add(examRecord);
        }
        for (int i = 0; i < questionAllTypeRecord.getMultiRecordList().size(); i++) {
            ExamRecord examRecord = new ExamRecord();
            examRecord.setResultId(resultId);
            examRecord.setQuestionsNumber(questionAllTypeRecord.getMultiRecordList().get(i).getQuestion_number());
            examRecord.setAnswer(questionAllTypeRecord.getMultiRecordList().get(i).getAnswer());
            examRecord.setQtype(2);
            examRecord.setExamId(examId);
            examRecord.setQuestionId(questionAllTypeRecord.getMultiRecordList().get(i).getId());
            examRecordList.add(examRecord);
        }
        for (int i = 0; i < questionAllTypeRecord.getJudgeRecordList().size(); i++) {
            ExamRecord examRecord = new ExamRecord();
            examRecord.setResultId(resultId);
            examRecord.setQuestionsNumber(questionAllTypeRecord.getJudgeRecordList().get(i).getQuestion_number());
            examRecord.setAnswer(questionAllTypeRecord.getJudgeRecordList().get(i).getAnswer());
            examRecord.setQtype(3);
            examRecord.setExamId(examId);
            examRecord.setQuestionId(questionAllTypeRecord.getJudgeRecordList().get(i).getId());
            examRecordList.add(examRecord);
        }
        return examRecordList;
    }

    /**
     * 存入批改表中
     * @return
     */
    public int insertExamResultQuestion(List<ExamRecord> examRecordList,Integer resultId ){
        //开始存入批改表中
        List<ExamResultQuestion> examResultQuestionList = new ArrayList<>();
        for (int i = 0; i < examRecordList.size(); i++) {
            ExamResultQuestion examResultQuestion = new ExamResultQuestion();
            String answer = studentService.selectCorrectAnswer(examRecordList.get(i).getQuestionId(), examRecordList.get(i).getQtype());
            Double score = studentService.selectScore(examRecordList.get(i).getQuestionId(), examRecordList.get(i).getQtype());
            //封装进实体类中
            String str = "A,B,C,D";
            //System.out.println(answer);
            String[] str1 = answer.split(",");
            String wrongAnswer ="";
            if(Constant.SINGLE.equals(examRecordList.get(i).getQtype())||Constant.MULTI.equals(examRecordList.get(i).getQtype())){
                for(int j =0;j<str1.length;j++){
                    if(str1[j].equals("D")){
                        int x =str.indexOf(str1[j]);
                        wrongAnswer = str.substring(0, x) + str.substring(x + 1);
                        str = wrongAnswer;
                    }else{
                        int x =str.indexOf(str1[j]);
                        wrongAnswer = str.substring(0, x) + str.substring(x + 2);
                        str = wrongAnswer;
                    }
                }
            }else if(Constant.JUDGE.equals(examRecordList.get(i).getQtype())){
                if(answer.equals("true")){
                    wrongAnswer = "错";
                    answer = "对";
                }else{
                    wrongAnswer = "对";
                    answer = "错";
                }
            }
            if(wrongAnswer.endsWith(",")){
                wrongAnswer = wrongAnswer.substring(0,wrongAnswer.length()-1);
            }
            if(Constant.SINGLE.equals(examRecordList.get(i).getQtype())||Constant.MULTI.equals(examRecordList.get(i).getQtype())){
                examResultQuestion.setRight(examRecordList.get(i).getAnswer().equals(answer));
                if((examRecordList.get(i).getAnswer().equals(answer)) == true){
                    examResultQuestion.setScore(score);
                }else{
                    examResultQuestion.setScore(0.0);
                }
                examResultQuestion.setRightAnswer(answer);
            }else{
               // System.out.println("this is 1:"+answer.equals("true"));
                //System.out.println(examRecordList.get(i).getAnswer().equals("1"));
                if(answer.equals("对")&&examRecordList.get(i).getAnswer().equals("1")){
                    examResultQuestion.setRight(true);
                    examResultQuestion.setScore(score);
                    //examResultQuestion.setRightAnswer("对");
                }else if(answer.equals("错")&&examRecordList.get(i).getAnswer().equals("0")){
                    examResultQuestion.setRight(true);
                    examResultQuestion.setScore(score);
                    //examResultQuestion.setRightAnswer("错");
                }else{
                    examResultQuestion.setRight(false);
                    examResultQuestion.setScore(0.0);
                    //examResultQuestion.setRightAnswer("错误");
                }
            }
            //System.out.println("this is final wrongAnswer:"+wrongAnswer);
            //System.out.println("this is examRecordListget:"+examRecordList.get(i).getAnswer());
           // System.out.println("this is judge:"+examRecordList.get(i).getAnswer().equals(answer));
            //examResultQuestion.setRight(answer == examRecordList.get(i).getAnswer());
            examResultQuestion.setWrongAnswer(wrongAnswer);
            examResultQuestion.setRightAnswer(answer);
            examResultQuestion.setExamResultId(resultId);
            examResultQuestion.setQuestionId(examRecordList.get(i).getQuestionId());
            examResultQuestion.setQtype(examRecordList.get(i).getQtype());
            examResultQuestion.setDelFlag(false);
            examResultQuestionList.add(examResultQuestion);
        }
        //存入批改表中
        int examResultQuestion_count = studentService.insertExamResultQuestion(examResultQuestionList);
        return examResultQuestion_count;
    }

    /**
     * 更新考试结果表中的成绩
     * @param exam_result_id
     * @return
     */
    public int updateExamResult(Integer exam_result_id){
        //开始进行更新考试结果表
        //获得此次考试的分数
        int exam_score = studentService.selectScoreCount(exam_result_id);
        int updateExamResult_count = studentService.updateExamResult(exam_result_id,exam_score);
        return updateExamResult_count;
    }

    /**
     * 判断是否存在结果，用于进行考试的判定
     * @param exam_id
     * @param student_id
     * @return
     */
    public Integer selectExam(Integer exam_id,Integer student_id){
        Integer count = studentService.selectResultIdBy(exam_id,student_id);
        return count;
    }


}

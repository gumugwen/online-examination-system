package com.hw.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hw.model.JudgeRecord;
import com.hw.model.MultiRecord;
import com.hw.model.QuestionAllTypeRecord;
import com.hw.model.SingleRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JsonToObjectUtils {
    private static QuestionAllTypeRecord questionAllTypeRecord;
    public void  JsonToObject(String jsonStr) throws IOException {
    }

    public static List<SingleRecord> getSingleRecord(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<SingleRecord> singleRecordList = new ArrayList<SingleRecord>();
        JsonNode node= mapper.readTree(jsonStr);      //将Json串以树状结构读入内存
        JsonNode contents=node.get("single");//得到results这个节点下的信息
        //JsonNode contents1 = contents.get("single");
        for(int i=0;i<contents.size();i++)  //遍历results下的信息，size()函数可以得节点所包含的的信息的个数，类似于数组的长度
        {
            //System.out.println(contents1.get(i).get("id")); //读取节点下的某个子节点的值
            JsonNode geoNumber = contents.get("single");
            for (int j = 0; j < geoNumber.size(); j++)     //循环遍历子节点下的信息
            {
                SingleRecord singleRecord = new SingleRecord();
                singleRecord.setId(geoNumber.get(j).get("id").asInt());
                singleRecord.setQuestion_number(geoNumber.get(j).get("question_number").asInt());
                singleRecord.setAnswer(geoNumber.get(j).get("answer").asText());
                singleRecordList.add(singleRecord);
            }
        }
        return singleRecordList;
    }

    public static List<MultiRecord> getMultiRecord(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<MultiRecord> multiRecordList = new ArrayList<MultiRecord>();
        JsonNode node= mapper.readTree(jsonStr);      //将Json串以树状结构读入内存
        JsonNode contents=node.get("multi");//得到results这个节点下的信息
        //JsonNode contents1 = contents.get("multi");
        for(int i=0;i<contents.size();i++)  //遍历results下的信息，size()函数可以得节点所包含的的信息的个数，类似于数组的长度
        {
            //System.out.println(contents.get(i).get("id")); //读取节点下的某个子节点的值
            JsonNode geoNumber = contents.get("multi");
            for (int j = 0; j < geoNumber.size(); j++)     //循环遍历子节点下的信息
            {
                MultiRecord multiRecord = new MultiRecord();
                multiRecord.setId(geoNumber.get(j).get("id").asInt());
                multiRecord.setQuestion_number(geoNumber.get(j).get("question_number").asInt());
                multiRecord.setAnswer(geoNumber.get(j).get("answer").asText());
                multiRecordList.add(multiRecord);
            }
        }
        return multiRecordList;

    }

    public static List<JudgeRecord> getJudgeRecord(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<JudgeRecord> judgeRecordList = new LinkedList<>();
        List<JudgeRecord> judgeRecordList1;
        JsonNode node= mapper.readTree(jsonStr);      //将Json串以树状结构读入内存
        JsonNode contents=node.get("judge");//得到results这个节点下的信息
        //JsonNode contents1 = contents.get("multi");
        //System.out.println(contents.size());
        for(int i=0;i<contents.size();i++)  //遍历results下的信息，size()函数可以得节点所包含的的信息的个数，类似于数组的长度
        {
            //System.out.println(contents.get("judge").size()); //读取节点下的某个子节点的值
            JsonNode geoNumber = contents.get("judge");
            for (int j = 0; j < geoNumber.size(); j++)     //循环遍历子节点下的信息
            {
                JudgeRecord judgeRecord = new JudgeRecord();
                judgeRecord.setId(geoNumber.get(j).get("id").asInt());
                judgeRecord.setQuestion_number(geoNumber.get(j).get("question_number").asInt());
                judgeRecord.setAnswer(geoNumber.get(j).get("answer").asText());
                judgeRecordList.add(judgeRecord);
            }
        }
        return judgeRecordList;
    }

    public static String getExamId(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String examId = null;
        List<JudgeRecord> judgeRecordList1;
        JsonNode node= mapper.readTree(jsonStr);      //将Json串以树状结构读入内存
        examId=node.get("examId").asText();//得到results这个节点下的信息
        //System.out.println(contents);
        //JsonNode contents1 = contents.get("multi");
        //System.out.println(contents.size());
//        for(int i=0;i<contents.size();i++)  //遍历results下的信息，size()函数可以得节点所包含的的信息的个数，类似于数组的长度
//        {
//            examId = contents.get("examId").toString();
//            System.out.println(examId);
//        }
//        examId = contents.get("examId").toString();
        return examId;
    }

    public static String getExamStartTime(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String examStartTime = null;
        JsonNode node= mapper.readTree(jsonStr);      //将Json串以树状结构读入内存
        examStartTime=node.get("examStartTime").asText();//得到results这个节点下的信息
        //JsonNode contents1 = contents.get("multi");
        //System.out.println(contents.size());
//        for(int i=0;i<contents.size();i++)  //遍历results下的信息，size()函数可以得节点所包含的的信息的个数，类似于数组的长度
//        {
//            examStartTime = contents.get("value").toString();
//        }
//        examStartTime = contents.get("examStartTime").toString();
        return examStartTime;
    }

}

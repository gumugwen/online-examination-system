package com.hw.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hw.common.convert.JudgeAnswerConvert;
import com.hw.common.convert.QuestionTypeConverter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author  
 * @date 2020/11/26 9:10 上午
 */
public class ExcelUtil {

    public static <T> void pritEasyExcelTemplate(Class<T> clazz, List<T> list, String templateName, HttpServletResponse response, HttpSession session) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName= null;
        try {
            fileName = URLEncoder.encode(templateName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
        String path = session.getServletContext().getRealPath("/") + "excel_template/" + templateName + ".xlsx";
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .registerConverter(new JudgeAnswerConvert())
                .registerConverter(new QuestionTypeConverter())
                .head(clazz)
                .withTemplate(path)
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.fill(list,writeSheet);
        excelWriter.finish();
    }

    public static <T> void printEasyExcelTemplate(Class<T> clazz, List<T> list, String templateName, HttpServletResponse response, HttpSession session) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName= null;
        try {
            fileName = URLEncoder.encode(templateName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
        String path = session.getServletContext().getRealPath("/") + "excel_template/" + templateName + ".xlsx";
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .head(clazz)
                .withTemplate(path)
                .build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.fill(list,writeSheet);
        excelWriter.finish();
    }

    public static <T> List<T> importExcel(MultipartFile file,Class<T> clazz) throws IOException {
        List<T> data = EasyExcel.read(file.getInputStream())
                .head(clazz)
                .registerConverter(new JudgeAnswerConvert())
                .registerConverter(new QuestionTypeConverter()).sheet(0)
                .doReadSync();
        return data;
    }

    public static <T> List<T> importExcellist(MultipartFile file,Class<T> clazz) throws IOException {
        //通过EasyExcel自动解析并获取数据
        List<T> list = EasyExcel.read(file.getInputStream())
                .head(clazz) //指定实体类
                .sheet(0) //指定解析的sheet
                .doReadSync();  //将解析结果封装为list集合返回
        return list;
    }
}
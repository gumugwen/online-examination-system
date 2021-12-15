package com.hw.common.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.hw.common.Constant;
import org.apache.poi.ss.formula.functions.T;

/**
 * 题目类型转换器
 * @author  
 * @date 2020/11/26 9:31 上午
 */
public class QuestionTypeConverter implements Converter<Integer> {
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String type = cellData.getStringValue();
        if (Constant.SINGLE_STR.equals(type)||Constant.SINGLE_STR2.equals(type)){
            return Constant.SINGLE;
        }else if (Constant.MULTI_STR.equals(type)|| Constant.MULTI_STR2.equals(type)){
            return Constant.MULTI;
        }else if (Constant.JUDGE_STR.equals(type)||Constant.JUDGE_STR2.equals(type)){
            return Constant.JUDGE;
        }
        return -1;
    }

    @Override
    public CellData convertToExcelData(Integer value, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (value.equals(Constant.SINGLE)){
            return new CellData(Constant.SINGLE_STR);
        }else if (value.equals(Constant.MULTI)){
            return new CellData(Constant.MULTI_STR);
        }else if (value.equals(Constant.JUDGE)){
            return new CellData(Constant.JUDGE_STR);
        }
        return new CellData("未知题型");
    }
}

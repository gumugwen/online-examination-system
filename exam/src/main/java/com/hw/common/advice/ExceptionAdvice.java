package com.hw.common.advice;

import com.hw.common.RespCode;
import com.hw.common.Result;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author  
 * @date 2020/10/22 4:06 下午
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final String EXPTION_MSG_KEY = "message";
    private static final Logger log= LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public Object result(Exception e){
        e.printStackTrace();
        return Result.fail(RespCode.FAIL,e.getMessage());
    }

    /**
     * 参数校验错误异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result argumentValidException(MethodArgumentNotValidException e){
        e.printStackTrace();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        //多个错误，取第一个
        FieldError error = fieldErrors.get(0);
        String msg = error.getDefaultMessage();
        return Result.fail(RespCode.NOT_VALID,msg);

    }

    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView forbiddenExcetion(HttpServletRequest request, UnauthorizedException ex) {
        return new ModelAndView("401",EXPTION_MSG_KEY,"权限不足");
    }

    @ExceptionHandler({UnknownAccountException.class, IncorrectCredentialsException.class})
    @ResponseBody
    public Result authExcetion(HttpServletRequest request, Exception ex) {
        if (ex instanceof UnknownAccountException) {
            return new Result(4004,false,"用户不存在");
        }
        if (ex instanceof IncorrectCredentialsException) {
            return new Result(4005,false,"用户名或密码错误");
        }
        return Result.fail(RespCode.FAIL,"未知错误");
    }
}

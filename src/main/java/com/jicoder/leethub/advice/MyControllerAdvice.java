package com.jicoder.leethub.advice;

import com.jicoder.leethub.utils.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseResult sqlIntegrityConstraintViolationExceptionHandler(Exception exception, HttpServletResponse response){
        System.out.println(exception.getMessage());
        return new ResponseResult(response.getStatus(), 0, exception.getMessage());
    }

}

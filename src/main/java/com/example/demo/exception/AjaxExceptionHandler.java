package com.example.demo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.pojo.MessageJSONResult;


/**
* @author：Administrator
* @createDate:2019-10-08 57:12
* @description:AjaxExceptionHandler Ajax异常捕获类
*/
@ControllerAdvice
public class AjaxExceptionHandler {
	private static final String TAG = AjaxExceptionHandler.class.getName(); 
	@ResponseBody
	@ExceptionHandler(value=Exception.class)
	public MessageJSONResult getError(HttpServletRequest request , Exception e) throws Exception{
		System.out.println(TAG + " : EXCEPTION");
		return MessageJSONResult.errorException(e.getMessage());
	}
}

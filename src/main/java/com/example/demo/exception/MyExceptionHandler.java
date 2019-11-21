package com.example.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
* @author：Administrator
* @createDate:2019-10-08 03:42
* @description:MyExceptionHandler 异常捕获类
*/
@ControllerAdvice
public class MyExceptionHandler {

	public static final String MY_ERROR_VIEW = "thymeleaf/error";
	
	@ExceptionHandler(value=Exception.class)
	public Object errorHandler(HttpServletRequest req , HttpServletResponse res ,  Exception e ) throws Exception{
		e.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception" , e);
		mav.addObject("url_path", req.getRequestURI());
		mav.setViewName(MY_ERROR_VIEW);
		return mav;
		
	}
}

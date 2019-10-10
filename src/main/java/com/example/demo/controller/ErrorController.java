package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.pojo.MessageJSONResult;

/**
* @author：Administrator
* @createDate:2019-10-08 05:47
* @description:ErrorController
*/
@Controller
@RequestMapping("/err")
public class ErrorController {
	@RequestMapping("/errors")
	public String  error() {
		int a = 1 / 0;
		return "thymeleaf/error";
	}
	
	@RequestMapping("/ajaxError")
	public String ajaxError() {
		return "thymeleaf/ajaxError";
	}
	
	@RequestMapping("/postAjaxError")
	@ResponseBody
	public MessageJSONResult getAjaxError() {
		System.out.println("--------getAjaxError---------");
		int i = 1 / 0;
		return MessageJSONResult.ok();
	}
}

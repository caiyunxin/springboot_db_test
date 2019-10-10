package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.MessageJSONResult;
import com.example.demo.pojo.Resource;



/**
* 
* @author：Administrator
* @createDate:2019-09-25 30:38
* @description:HelloController
*/
@RestController
@RequestMapping("/hello")
public class HelloController {
	@Autowired
	private Resource resource;
	
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot";
	}
	
	@RequestMapping("/getResource")
	private MessageJSONResult getResource() {
		Resource target = new Resource();
		BeanUtils.copyProperties(resource, target );
		return MessageJSONResult.ok(target);
	}
	
}

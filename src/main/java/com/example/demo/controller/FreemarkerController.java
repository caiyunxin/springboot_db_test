package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.pojo.Resource;


/**
* @author：Administrator
* @createDate:2019-09-29 41:42
* @description:FreemarkerController
*/
@Controller
public class FreemarkerController {
	@Autowired
	Resource resource;
	
	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("myResource", resource);
		return "freemarker/index" ;
	}
	
	@RequestMapping("/center")
	public String center(ModelMap map) {
		return "freemarker/center/center";
	}
	
	
}

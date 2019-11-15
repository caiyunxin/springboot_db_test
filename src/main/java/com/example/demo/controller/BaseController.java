package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.SysLsOpLogService;

/**
* @author：Administrator
* @createDate:2019-11-15 52:33
* @description:BaseController
*/
@Controller
@RequestMapping("deleteLog")
public class BaseController {
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String defaultStr = "";
	
	@Autowired
	SysLsOpLogService sysLsOpLogService;
	
	@RequestMapping(value= {"/deleteInfo"})
	public String deleteInfo(ModelMap map) {
		map.addAttribute("time", defaultStr);
		return "thymeleaf/deleteInfo.html";
	}
	
	@RequestMapping(value= {"/postform"})
	public String postform(String time) throws ParseException {
		System.out.println("time is " + time);
		Date createTime = format.parse(time);
//		defaultStr = String.valueOf(sysLsOpLogService.queryMaxDataLtCreateTime(createTime).getId());
		defaultStr = String.valueOf(sysLsOpLogService.selectMaxDataLtCreateTime(createTime).getId());
		return "redirect:/deleteLog/deleteInfo";
	}
}

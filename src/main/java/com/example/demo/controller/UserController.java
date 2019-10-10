package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.MessageJSONResult;
import com.example.demo.pojo.Resource;
import com.example.demo.pojo.User;

/**
* @author：Administrator
* @createDate:2019-09-27 40:47
* @description:UserController
*/
//@Controller
@RestController					//@RestController	= @Controller + @ResponseBody  (@Controller + @ResponseBody 是SpringMVC写法)
public class UserController {
	
//	@ResponseBody
	@RequestMapping("/getUser")
	public User getUser() {
		User u = new User();
		u.setName("Test");
		u.setPassword("Test");
		u.setAge(28);
		u.setBirthday(new Date());
		u.setDesc("添加项目热部署");
		return u;
	}
	
//	@ResponseBody
	@RequestMapping("/getUserJson")
	public MessageJSONResult getUserJson() {
		User u = new User();
		u.setName("TestJSON");
		u.setPassword("TestJSON");
		u.setAge(28);
		u.setBirthday(new Date());
		u.setDesc("添加项目热部署");
		return MessageJSONResult.ok(u);
	}
}

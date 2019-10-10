package com.example.demo.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.pojo.User;


/**
* @author：Administrator
* @createDate:2019-09-29 18:20
* @description:ThymeleafController
*/
@Controller
@RequestMapping("thy")
public class ThymeleafController {
	@RequestMapping(value= {"/index" , "/index.html"})
    public String index(ModelMap map) {
        map.addAttribute("name", "pinglian-thymeleaf");
        return "thymeleaf/index.html";
    }
	
	@RequestMapping(value= {"center","center.html"})
    public String center() {
        return "thymeleaf/center/center";
    }
	
	@RequestMapping(value="/myTest")
	public String myTest(ModelMap map) {
		User u = new User();
		u.setName("superadmin");
		u.setPassword("123456");
		u.setAge(27);
		u.setBirthday(new Date());
		u.setDesc("<font color='green'><b>pinglian-mytest</b></font>");
		map.addAttribute("user", u);
		
		User u1 = new User();
		u1.setName("PL-Boot");
		u1.setPassword("123456");
		u1.setAge(20);
		u1.setBirthday(new Date());
		
		User u2 = new User();
		u2.setName("PingLinkt");
		u2.setPassword("123456");
		u2.setAge(22);
		u2.setBirthday(new Date());
		
		List<User> userList = new ArrayList<User>();
		userList.add(u);
		userList.add(u1);
		userList.add(u2);
		
		map.addAttribute("userlist", userList);
		return "thymeleaf/myTest";
	}
	
	@RequestMapping("/postform")
	public String postform (User u) {
		System.out.println("User.name : " + u.getName());
		System.out.println("User.age : " + u.getAge());
		return "redirect:/thy/myTest";
	}
}

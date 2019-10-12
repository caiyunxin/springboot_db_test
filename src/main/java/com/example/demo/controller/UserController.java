package com.example.demo.controller;

import java.util.Date;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.MessageJSONResult;
import com.example.demo.pojo.SysUser;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

/**
* @author：Administrator
* @createDate:2019-09-27 40:47
* @description:UserController
*/
//@Controller
@RestController					//@RestController	= @Controller + @ResponseBody  (@Controller + @ResponseBody 是SpringMVC写法)
public class UserController {
	
	@Autowired(required=true)
	private UserService userService;
	
	//根据当前时间生成的唯一字符串ID工具类
	@Autowired
	private Sid sid;
	
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
	
	@RequestMapping("/saveUser")
	public MessageJSONResult saveUser() throws Exception {
			SysUser u = new SysUser();
			u.setId(sid.nextShort());
			String suffix =  u.getId().substring(u.getId().length() - 4);
			u.setUsername("pinglian01_" + suffix);
			u.setPassword("pl_pwd");
			u.setNickname("nickname01_" + suffix);
			u.setAge(28);
			u.setSex(1);
			u.setIsDelete(0);
			u.setRegistTime(new Date());
			userService.saveUser(u);
			return MessageJSONResult.ok("保存成功");
		
	}
	
	@RequestMapping("/updateUser")
	public MessageJSONResult updateUser(SysUser user) {
		SysUser u = new SysUser();
		u.setId("10002");
		u.setUsername("pinglian02");
		u.setPassword("pl_pwd");
		u.setNickname("nickname02");
		userService.updateUser(u);
		return MessageJSONResult.ok("修改成功");
	}
	
	@RequestMapping("/deleteUser")
	public MessageJSONResult deleteUser(String userId) {
		userService.deleteUser(userId);
		return MessageJSONResult.ok("删除成功");
	}
	
	@RequestMapping("/queryUserById")
	public MessageJSONResult queryUserById(String userId) {
		return MessageJSONResult.ok(userService.queryUserById(userId));
	}
	
	@RequestMapping("/queryUserList")
	public MessageJSONResult queryUserList(SysUser user) {
		SysUser sUser = new SysUser();
		sUser.setUsername("03");
		sUser.setNickname("03");
		return MessageJSONResult.ok(userService.queryUserList(sUser));
	}
	
	@RequestMapping("/queryUserListPaged")
	public MessageJSONResult queryUserListPaged(Integer currentPage) {
		if(currentPage == null) {
			currentPage = 1;
		}
		int pageSize = 10;
		SysUser user = new SysUser();
		return MessageJSONResult.ok(userService.queryUserListPaged(user, currentPage, pageSize));
	}
	
}

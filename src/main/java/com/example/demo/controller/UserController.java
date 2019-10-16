package com.example.demo.controller;

import java.util.Date;
import java.util.concurrent.Future;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.MessageJSONResult;
import com.example.demo.pojo.SysUser;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.task.AsyncTask;

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
	
	//Redis2.0.5中的字符串模板类
	@Autowired
	private StringRedisTemplate strRedis;
	
	//异步任务类
	@Autowired
	private AsyncTask asyncTask;
	
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
	
	@RequestMapping("/queryUserInfoById")
	public MessageJSONResult queryUserInfoById(String userId) {
		SysUser u = userService.queryUserById(userId);
		return MessageJSONResult.ok(u);
	}
	
	
	@RequestMapping("/testRedis")
	public MessageJSONResult getRedis() {
		strRedis.opsForValue().set("pinglian-cache", "hello pinglian~~~~~~~");
		System.out.println("--- Redis set success ---");
		return MessageJSONResult.ok(strRedis.opsForValue().get("pinglian-cache"));
	}
	
	//异步任务
	@RequestMapping("/testAsyncTask")
	public String testAsyncTask() {
		String time = "";
		try {
			long startTime = System.currentTimeMillis();
			Future<Boolean> future1 = asyncTask.doTask11();
			Future<Boolean> future2 = asyncTask.doTask22();
			Future<Boolean> future3 = asyncTask.doTask33();
			
			while (!future1.isDone() || !future2.isDone() || !future3.isDone()) {
				if(future1.isDone() && future2.isDone() && future3.isDone())
					break;
			}
			long endTime =  System.currentTimeMillis();
			time = String.valueOf(endTime - startTime);
			System.out.println("任务全部完成，总耗时：" + time + "毫秒");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return time;
	}
	
}

package com.example.demo.controller;
/**
* @author：Administrator
* @createDate:2019-10-15 31:54
* @description:RedisController
*/

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.MessageJSONResult;
import com.example.demo.pojo.SysUser;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.RedisOperator;


@RestController
@RequestMapping("redis")
public class RedisController {
	
	/*
	 * String-focused extension of RedisTemplate. 
	 * Since most operations against Redis are String based, 
	 * this class provides a dedicated class that minimizes configuration of its more generic template especially in terms of serializers.
	 */
	@Autowired
	private StringRedisTemplate strTemplate;
	/*
	 * 自定义Redis工具类
	 */
	@Autowired
	private RedisOperator redisOperation;
	
	//Redis 设置用户
	@RequestMapping("/setUser")
	public MessageJSONResult setUser() {
		
		SysUser user = new SysUser();
		user.setId("123321");
		user.setUsername("pl-redis");
		user.setAge(33);
		user.setPassword("pl-password");
		user.setNickname("pl");
		user.setIsDelete(0);
		user.setRegistTime(new Date());
		strTemplate.opsForValue().set("pl-user", JsonUtils.objectToJson(user));
		return MessageJSONResult.ok("setUser success");
	}
	
	//根据key获取用户
	@RequestMapping("/getUser")
	public MessageJSONResult getUser(String key) {
		if(key == null || key.isEmpty()) {
			return MessageJSONResult.errorMsg("key is null");
		}
		SysUser user = JsonUtils.jsonToPojo(strTemplate.opsForValue().get(key), SysUser.class);
		return MessageJSONResult.ok(user);
	}
	
	//设置用户集合
	@RequestMapping("setUserList")
	public MessageJSONResult setUserlist() {
		SysUser user = new SysUser();
		user.setUsername("屏联科技");
		user.setPassword("123456");
		user.setNickname("屏联");
		user.setAge(18);
		user.setIsDelete(0);
		user.setRegistTime(new Date());
		
		SysUser user1 = new SysUser();
		user1.setUsername("米有科技");
		user1.setPassword("123456");
		user1.setNickname("米有");
		user1.setAge(28);
		user1.setIsDelete(0);
		user1.setRegistTime(new Date());
		
		SysUser user2 = new SysUser();
		user2.setUsername("pingliankeji");
		user2.setPassword("123456");
		user2.setNickname("pinglian");
		user2.setAge(8);
		user2.setIsDelete(0);
		user2.setRegistTime(new Date());
		
		List<SysUser> userList = new ArrayList<SysUser>();
		userList.add(user);
		userList.add(user1);
		userList.add(user2);
		
		redisOperation.set("json:info:userlist", JsonUtils.objectToJson(userList) , 2000);
		return MessageJSONResult.ok("setUserList success");
	}
	
	//获取用户集合
	@RequestMapping("/getUserList")
	public MessageJSONResult getUserList(String key) {
		if(key == null || key.isEmpty()) {
			return MessageJSONResult.errorMsg("key is null or is Empty");
		}
		return MessageJSONResult.ok(JsonUtils.jsonToList(redisOperation.get(key), SysUser.class));
	}
}

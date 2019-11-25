package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.CommonConfig;
import com.example.demo.service.SysLsOpLogService;
import com.example.demo.task.AsyncTask;

/**
* @author：Administrator
* @createDate:2019-11-15 52:33
* @description:BaseController
*/
@Controller
@RequestMapping("deleteLog")
public class BaseController {
	
	@Autowired
	SysLsOpLogService sysLsOpLogService;
	@Autowired
	AsyncTask deleteTask;
	
	@RequestMapping(value= {"/deleteInfo"})
	public String deleteInfo(ModelMap map) {
		map.addAttribute("time", CommonConfig.defaultTimeStr);
		map.addAttribute("submitStr", CommonConfig.submitStr);
		return "thymeleaf/deleteInfo.html";
	}
	
	
	@RequestMapping(value= {"/postDelete"})
	public String postDelete(String userName, String userPwd, String submitValue) throws Exception {
		System.out.println(" userName = " + userName + " , userPwd = " + userPwd + " , submitValue = " + submitValue);
		
		if(null == userName || userName.isEmpty() || !userName.equals("tengshike@pinglian.hk")) {
			CommonConfig.defaultTimeStr = "<font color='red'><b>用户名不正确</b></font>";
		}else if(null == userPwd || userPwd.isEmpty() || !userPwd.equals("tengshike@pinglian.hk")) {
			CommonConfig.defaultTimeStr = "<font color='red'><b>密码不正确</b></font>";
		}else {
			if (submitValue.equals("启动")) {
				System.out.println("启动时线程名称 ： " + Thread.currentThread().getName());
				if (!CommonConfig.started) {
					// 启动删除任务
					CommonConfig.started = true;
					CommonConfig.defaultTimeStr = "<font color='green'><b>删除任务已启动</b></font>";
					CommonConfig.submitStr = "停止";
					deleteTask.doDeleteById(userName);
				}
			} else if (submitValue.equals("停止")) {
				// 停止删除任务
				System.out.println("停止时线程名称 ： " + Thread.currentThread().getName());
				CommonConfig.started = false;
				CommonConfig.defaultTimeStr = "<font color='red'><b>删除任务已停止</b></font>";
				CommonConfig.submitStr = "启动";
			}
		}
	
		return "redirect:/deleteLog/deleteInfo";
	}
	
}

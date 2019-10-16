package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
* @author：Administrator
* @createDate:2019-10-16 45:09
* @description:RedisInterceptor 实现 HandlerInterceptor 接口
*/
public class RedisInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO 在请求处理之前进行调用（Controller方法调用之前）
		System.out.println("被RedisInterceptor preHandle()拦截，放行...");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
		System.out.println("被RedisInterceptor postHandle()拦截.");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
		
	}
	
}

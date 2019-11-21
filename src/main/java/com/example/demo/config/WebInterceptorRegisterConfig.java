package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.interceptor.RedisInterceptor;
import com.example.demo.interceptor.DeleteInterceptor;


/**
* @author：Administrator
* @createDate:2019-10-16 54:06
* @description:WebInterceptorRegisterConfig  WebMvcConfigurerAdapter , @deprecated as of 5.0 
* 更改为继承WebMvcConfigurationSupport类来注册拦截器
*/
@Configuration
//public class WebInterceptorRegisterConfig extends WebMvcConfigurerAdapter {
public class WebInterceptorRegisterConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		// TODO 根据注册顺序，注册拦截器
		
		registry.addInterceptor(new DeleteInterceptor()).addPathPatterns("/deleteLog/postDelete");
		registry.addInterceptor(new RedisInterceptor()).addPathPatterns("/redis/**");
		super.addInterceptors(registry);
	}
	
	
}

package com.example.demo.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
* @author：Administrator
* @createDate:2019-09-27 43:51
* @description:Resource
*/
//表示此类是资源文件
@Configuration
//配置文件中每个属性的前缀，此前缀后面加.加类中的属性对应配置文件中的属性
@ConfigurationProperties(prefix="dts.server")
//资源文件地址，项目打包发布后会将src\main\resources路径下的文件打包到classpath路径下。SpringBoot版本不同此处配置也不一样。
@PropertySource(value="classpath:resource.properties")
public class Resource {
	private String host;
	private String port;
	private String timeout;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	
	
}

package com.example.demo.config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author：Administrator
* @createDate:2019-11-15 59:47
* @description:CommonConfig 通用属性配置
*/
public class CommonConfig {
	
	/**
	 * 是否已经启动，true：已启动， false:未启动（默认）。
	 */
	public static boolean started = false;
	
	/**
	 * 格式化时间
	 */
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 输入的时间字符串
	 */
	public static String defaultTimeStr = ""; 
	
	/**
	 * 输入的时间字符串换行成Date类型
	 */
	public static Date standardCreateTime = null;
	
	/**
	 * 启动按钮字符串
	 */
	public static String submitStr = "启动";
	
	/**
	 * 删除总数量
	 */
	public static Long deleteTotal = 0l;
	
	/**
	 * 当前表中最小ID
	 */
	public static Long minId = 0l;
	
	/**
	 * 每次删除数量,与sql语句中limit保持一致
	 */
	public static int deleteCount = 3;
	
	/**
	 * 反转程序执行状态
	 * @param status 
	 */
	public static void reversalStatus(boolean status) {
		CommonConfig.submitStr = status ? "启动" : "停止";
		CommonConfig.started = !status;
		System.out.println("status is " + CommonConfig.started);
	}
}

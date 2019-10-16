package com.example.demo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
* @author：Administrator
* @createDate:2019-10-16 22:44
* @description:SchedulingTask 定时器任务
*/
@Component
public class SchedulingTask {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	//每过3秒执行一次任务
	//@Scheduled(fixedRate = 3000)
	//cron条件解析器地址  http://cron.qqe2.com/
	@Scheduled(cron = "30,50 22-24 11-11 1/1 * ? ")
	public void reportCurrentTime() {
		System.out.println("现在时间：" + dateFormat.format(new Date()));
	}
}

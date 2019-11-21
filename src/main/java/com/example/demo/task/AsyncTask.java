package com.example.demo.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.example.demo.config.CommonConfig;
import com.example.demo.mail.SendEmail;
import com.example.demo.mapper.SysLsOpLogMapperCustomer;

/**
* @author：Administrator
* @createDate:2019-10-16 31:59
* @description:AsyncTask 异步任务
*/
@Component
public class AsyncTask {
	@Autowired
	public SysLsOpLogMapperCustomer sysLsOpLogMapperCustomer;	
//	@Async
//    public Future<Boolean> doTask11() throws Exception {
//        long start = System.currentTimeMillis();
//        Thread.sleep(1000);
//        long end = System.currentTimeMillis();
//        System.out.println("任务1耗时:" + (end - start) + "毫秒");
//        return new AsyncResult<>(true);
//    }
//    
//	@Async
//    public Future<Boolean> doTask22() throws Exception {
//        long start = System.currentTimeMillis();
//        Thread.sleep(700);
//        long end = System.currentTimeMillis();
//        System.out.println("任务2耗时:" + (end - start) + "毫秒");
//        return new AsyncResult<>(true);
//    }
//    
//	@Async
//    public Future<Boolean> doTask33() throws Exception {
//        long start = System.currentTimeMillis();
//        Thread.sleep(600);
//        long end = System.currentTimeMillis();
//        System.out.println("任务3耗时:" + (end - start) + "毫秒");
//        return new AsyncResult<>(true); 
//    }
	
	
	@Async
	public Future<Boolean> doDeleteById(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		CommonConfig.deleteTotal = 0L;
		StringBuffer msgBuffer = new StringBuffer();
		String msgError = "无";
		String startTimeStr = format.format(new Date());
		Long startMinId = 0L;
		try {
			startMinId = CommonConfig.minId = sysLsOpLogMapperCustomer.selectMinId().getId();
			if(CommonConfig.minId <= 0) {
				return new AsyncResult<>(true);
			}
			CommonConfig.minId += CommonConfig.deleteCount;
			int res = 0;
			while (CommonConfig.started) {
//				System.out.println("循环时线程名称 ： "+Thread.currentThread().getName());
				CommonConfig.minId += res;
				Thread.sleep(3000);
				res = sysLsOpLogMapperCustomer.deleteDataLeId(CommonConfig.minId);
				if(res <= 0) {
					//停止删除任务
					CommonConfig.started = false;
					CommonConfig.submitStr = "启动";
				};
				CommonConfig.deleteTotal += res;
				System.out.println("minId : "+ CommonConfig.minId + ", currentTime : " + format.format(new Date()));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			msgError = e.getMessage();
		}finally {
			//发送邮件
			String endTimeStr = format.format(new Date());
			msgBuffer.append("###################\n");
			msgBuffer.append("开始时间 ： ");
			msgBuffer.append(startTimeStr + "\n");
			msgBuffer.append("开始删除ID : ");
			msgBuffer.append(startMinId + "\n");
			msgBuffer.append("当前最小ID : ");
			msgBuffer.append(CommonConfig.minId + "\n");
			msgBuffer.append("此次总共删除 : ");
			msgBuffer.append(CommonConfig.deleteTotal);
			msgBuffer.append("条数据。\n");
			msgBuffer.append("结束时间 ： ");
			msgBuffer.append(endTimeStr + "\n");
			msgBuffer.append("错误 : ");
			msgBuffer.append(msgError);
			msgBuffer.append("\n");
			msgBuffer.append("###################");
//			System.out.println("--- msg is ---\n" + msgBuffer.toString());
			SendEmail.Send("数据删除", msgBuffer.toString());
		}
		
		
		
		return new AsyncResult<>(true); 
	}
	
}

package com.example.demo.service;

import java.util.Date;

import com.example.demo.pojo.SysLsOpLog;

/**
* @author：Administrator
* @createDate:2019-11-15 03:19
* @description:SysLsOpLogService
*/
public interface SysLsOpLogService {
	
	public SysLsOpLog queryMaxDataLtCreateTime(Date createTime);
	
	public SysLsOpLog selectMaxDataLtCreateTime(Date createTime);
	
}

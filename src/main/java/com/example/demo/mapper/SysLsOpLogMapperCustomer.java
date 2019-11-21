package com.example.demo.mapper;

import java.util.Date;
import java.util.List;

import com.example.demo.pojo.SysLsOpLog;

/**
* @author：Administrator
* @createDate:2019-11-15 31:53
* @description:SysLsOpLogMapperCustomer
*/
public interface SysLsOpLogMapperCustomer {
	
	SysLsOpLog selectSysLsOpLogById(Long maxId);
	
	List<SysLsOpLog> selectMaxDataLtCreateTime(Date createTime);
	
	int deleteDataLeId(Long maxId);
	
	SysLsOpLog selectMinId();
}

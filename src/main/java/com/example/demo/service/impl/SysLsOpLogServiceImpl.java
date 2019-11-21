package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.SysLsOpLogMapper;
import com.example.demo.mapper.SysLsOpLogMapperCustomer;
import com.example.demo.pojo.SysLsOpLog;
import com.example.demo.service.SysLsOpLogService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
* @author：Administrator
* @createDate:2019-11-15 05:23
* @description:SysLsOpLogServiceImpl
*/
@Service
public class SysLsOpLogServiceImpl implements SysLsOpLogService {

	@Autowired
	public SysLsOpLogMapper sysLsOpLogMapper;
	@Autowired
	public SysLsOpLogMapperCustomer sysLsOpLogMapperCustomer;
	
	@Override
	public SysLsOpLog queryMaxDataLtCreateTime(Date createTime) {
		Example example = new Example(SysLsOpLog.class);
		Criteria criteria = example.createCriteria();
		criteria.andCondition("create_time < ", createTime);
		return sysLsOpLogMapper.selectByExample(example).get(0);
	}

	@Override
	public SysLsOpLog selectMaxDataLtCreateTime(Date createTime) {
		// TODO 自定义条件查询
		return sysLsOpLogMapperCustomer.selectMaxDataLtCreateTime(createTime).get(0);
	}

	@Override
	public SysLsOpLog selectSysLsOpLogById(Long maxId) {
		// TODO 跟ID获取SysLsOpLog对象
		return sysLsOpLogMapperCustomer.selectSysLsOpLogById(maxId);
	}
	

}

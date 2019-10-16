package com.example.demo.mapper;

import java.util.List;

import com.example.demo.pojo.SysUser;

/**
* @author：Administrator
* @createDate:2019-10-14 52:58
* @description:SysUserMapperCustom 自定义Mapper
*/
public interface SysUserMapperCustom {
	List<SysUser> queryUserInfoById(String id);
}

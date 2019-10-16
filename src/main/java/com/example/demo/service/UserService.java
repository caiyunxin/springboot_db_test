package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.SysUser;

/**
* @author：Administrator
* @createDate:2019-10-11 18:22
* @description:UserService 
*/
public interface UserService {
	
	public void saveUser(SysUser user) throws Exception;
	
	public void updateUser(SysUser user);
	
	public void deleteUser(String userId);
	
	public SysUser queryUserById(String userId);
	
	public List<SysUser> queryUserList(SysUser user);
	
	public List<SysUser> queryUserListPaged(SysUser user , Integer page, Integer pageSize);
	
	public SysUser queryUserInfoById(String id);
}

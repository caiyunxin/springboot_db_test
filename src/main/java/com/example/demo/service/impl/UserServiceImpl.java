package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.example.demo.mapper.SysUserMapper;
import com.example.demo.pojo.SysUser;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
* @author：Administrator
* @createDate:2019-10-11 23:51
* @description:UserServiceImpl
*/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private SysUserMapper userMapper;
	
	@Override
	public void saveUser(SysUser user) throws Exception {
		// TODO 添加用户
		System.out.println("user.id = " + user.getId() + " , user.username = " + user.getUsername());
		userMapper.insert(user);
	}

	@Override
	public void updateUser(SysUser user) {
		// TODO 修改用户
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void deleteUser(String userId) {
		// TODO 根据ID删除用户
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public SysUser queryUserById(String userId) {
		// TODO 根据ID查询用户
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<SysUser> queryUserList(SysUser user) {
		// TODO 根据条件查询数据集合
		//创建查询条件类Criteria
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
			//设置查询条件
			criteria.andLike("username", "%"+user.getUsername()+"%");
		}
		if(!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%"+user.getNickname()+"%");
		}
		//返回条件查询后的结果
		return userMapper.selectByExample(example);
	}

	@Override
	public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
		// TODO 根据条件分页查询
		//使用PageHelper开始分页
		PageHelper.startPage(page, pageSize);
		//创建条件类
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		
		if(!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
			criteria.andLike("username", "%"+user.getUsername()+"%");
		}
		//设置排序
		example.orderBy("registTime").desc();
		return userMapper.selectByExample(example);
	}	

}

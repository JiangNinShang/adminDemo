package main.newer.service;

import java.util.HashMap;

import main.newer.dto.UserDto;

public interface UserService {
	/**
	 * 登录的方法
	 * @param user
	 * @return boolean
	 */
	Boolean Login(UserDto user);
	/**
	 * 注册的方法
	 * @param user
	 * @return
	 */
	Boolean regiest(UserDto user);
	/**
	 * 查询所有用户名
	 * @return
	 */
	HashMap<String, Object> getName();
}

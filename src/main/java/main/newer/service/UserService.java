package main.newer.service;

import java.util.HashMap;

import main.newer.domain.User;
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
	/**-
	 * 根据字符搜索用户名
	 * @param name
	 * @return
	 */
	HashMap<String, Object> getbyName(String name);
	/**
	 * 根据用户名修盖密码
	 * @param name
	 * @param pwd
	 * @return
	 */
	Boolean updataPwd(String name,String pwd);
	
	User getUserByName(String name);
	
}

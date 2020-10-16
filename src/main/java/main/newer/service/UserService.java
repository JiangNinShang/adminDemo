package main.newer.service;

import main.newer.dto.UserDto;

public interface UserService {
	/**
	 * 
	 * @param user
	 * @return boolean
	 */
	Boolean Login(UserDto user);
}

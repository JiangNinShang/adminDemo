package main.newer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.newer.domain.User;
import main.newer.dto.UserDto;
import main.newer.mapper.UserMapper;
import main.newer.service.UserService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper um;
	
	/**
	 * 登录验证
	 */
	@Override
	public Boolean Login(UserDto user) {
		Example e = new Example(User.class);
		Criteria c =  e.createCriteria();
		c.andEqualTo("uname", user.getUname());
		c.andEqualTo("upwd", user.getUpwd());
		User ur = um.selectOneByExample(e);
		if(null == ur) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean regiest(UserDto user) {
		User u = new User(user.getUname(),user.getUpwd(),0,user.getPhone(),user.getSex(),user.getAge(),0,user.getMail(),0,0);
		int i = um.insert(u);
		if(i == 0) {
			return false;
		}
		return true;
	}

}

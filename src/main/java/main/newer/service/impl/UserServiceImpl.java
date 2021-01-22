package main.newer.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.newer.domain.User;
import main.newer.dto.UserDto;
import main.newer.mapper.UserMapper;
import main.newer.service.UserService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper um;

	/**
	 * 登录验证
	 */
	@Override
	public Boolean Login(UserDto user) {
		Example e = new Example(User.class);
		Criteria c = e.createCriteria();
		c.andEqualTo("uname", user.getUname());
		c.andEqualTo("upwd", user.getUpwd());
		User ur = um.selectOneByExample(e);
		if (null == ur) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean regiest(UserDto user) {
		User u = new User(user.getUname(), user.getUpwd(), 0, user.getPhone(), user.getSex(), user.getAge(), 0,
				user.getMail(), 0, 0);
		int i = um.insert(u);
		if (i == 0) {
			return false;
		}
		return true;
	}

	@Override
	public HashMap<String, Object> getName() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (User u : um.selectAll()) {
			map.put(u.getUname(), null);
		}
		return map;
	}

	@Override
	public HashMap<String, Object> getbyName(String name) {
		Example e = new Example(User.class);
		Criteria c = e.createCriteria();
		c.andEqualTo("uname", name);
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (User u : um.selectByExample(e)) {
			map.put(null, u);
		}
		return map;
	}

	@Override
	public Boolean updataPwd(String name, String pwd) {
		Example e = new Example(User.class);
		Criteria c = e.createCriteria();
		c.andEqualTo("uname", name);
		User user = um.selectOneByExample(e);
		user.setUpwd(pwd);
		int num = um.updateByPrimaryKey(user);
		if(num!= 1) {
			return false;
		}
		return true;
	}

	@Override
	public User getUserByName(String name) {
		Example e = new Example(User.class);
		Criteria c = e.createCriteria();
		c.andEqualTo("uname", name);
		return um.selectOneByExample(e);
	}

}

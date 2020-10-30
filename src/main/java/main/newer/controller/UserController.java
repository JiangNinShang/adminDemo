package main.newer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import main.newer.dto.UserDto;
import main.newer.service.UserService;

@CrossOrigin // @CrossOrigin所有网站都可以跨域访问
@RestController
@RequestMapping("wdnmd")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService us;

	@ApiOperation("用户登录")
	@PostMapping("/login")
	@RequestMapping("login")
	public boolean login(@RequestBody UserDto user) {
		logger.info(user.toString());
		return us.Login(user);
	}

	@ApiOperation("用户注册")
	@PostMapping("/register")
	@RequestMapping("register")
	public boolean register(@RequestBody UserDto u) {
		logger.info(u.toString());
		return us.regiest(u);
	}
}

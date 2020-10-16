package main.newer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import main.newer.domain.User;
import main.newer.dto.UserDto;
import main.newer.service.UserService;

@CrossOrigin // @CrossOrigin所有网站都可以跨域访问
@RestController
@RequestMapping("wdnmd")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService us;

	@ApiOperation("返回地址集合")
	@PostMapping("/login")
	@RequestMapping("login")
	public boolean login(@RequestBody UserDto user) {
		return us.Login(user);
	}

	@ApiOperation("返回地址集合")
	@PostMapping("/register")
	public boolean register(User u) {
		return true;
	}
}

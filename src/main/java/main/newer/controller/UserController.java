package main.newer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.newer.domain.User;
import main.newer.service.UserService;

@CrossOrigin //@CrossOrigin所有网站都可以跨域访问
@RestController
@SuppressWarnings("all")
@RequestMapping("wdnmd")
public class UserController {
	@Autowired
	UserService us;
	
	@GetMapping("login")
	public boolean login(User u) {
		return true;
	}
	@PostMapping("register")
	public boolean register(User u) {
		return true;
	}
}

package main.newer.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import main.newer.config.ResultJSON;
import main.newer.dto.UserDto;
import main.newer.service.UserService;

@CrossOrigin(origins = "*",maxAge = 3600) // @CrossOrigin所有网站都可以跨域访问
@RestController
@RequestMapping("wdnmd")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService us;

	@ApiOperation("用户登录")
	@PostMapping("/login")
	@RequestMapping("login")
	public String login(@RequestBody UserDto user) {
		logger.info(user.toString());
		if (StringUtils.isEmpty(user.getUname()) || StringUtils.isEmpty(user.getUpwd())) {
			return new ResultJSON(false, "用户名或密码为空", 400).toJson();
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken upt = new UsernamePasswordToken(user.getUname(), user.getUpwd());
		try {
			// 进行验证，这里可以捕获异常，然后返回对应信息
			subject.login(upt);
		} catch (UnknownAccountException e) {
			logger.info("用户名不存在！");
			return ResultJSON.error("用户名不存在").toJson();
		} catch (AuthenticationException e) {
			logger.info("账号或密码错误！");
			return ResultJSON.error("账号或密码错误").toJson();
		} catch (AuthorizationException e) {
			logger.info("没有权限！");
			return ResultJSON.error("没有权限").toJson();
		}
		if(user.isAuto()) {
			//记住我
		}
		return ResultJSON.ok(us.Login(user)).toJson();
	}

	@ApiOperation("用户注册")
	@PostMapping("/register")
	@RequestMapping("register")
	public ResultJSON register(@RequestBody UserDto u) {
		logger.info(u.toString());
		return ResultJSON.ok(us.regiest(u));
	}
	@ApiOperation("用户名")
	@GetMapping("/getName")
	@RequestMapping("getName")
	public ResultJSON getName() {
		logger.info("getName");
		Map<String, Object> data = us.getName();
		return ResultJSON.ok(data);
	}

	@ApiOperation("根据用户名查询")
	@PostMapping("/getbyName")
	@RequestMapping("getbyName")
	public ResultJSON getbyName(String name) {
		Map<String, Object> data = us.getbyName(name);
		return ResultJSON.ok(data);
	}

	@ApiOperation("根据用户名修改密码")
	@PostMapping("/updataPwd")
	@RequestMapping("updataPwd")
	public ResultJSON updataPwd(String name, String pwd) {
		return ResultJSON.ok(us.updataPwd(name, pwd));
	}
}

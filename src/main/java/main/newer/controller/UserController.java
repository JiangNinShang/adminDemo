package main.newer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import main.newer.util.HttpHost;

@CrossOrigin(origins = "*", maxAge = 3600) // @CrossOrigin所有网站都可以跨域访问
@RestController
@RequestMapping("user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService us;
	@ApiOperation("用户登录")
	@PostMapping("/login")
	@RequestMapping("login")
	public ResultJSON login(@RequestBody UserDto user,HttpServletRequest  http) {
		Subject subject = SecurityUtils.getSubject();
		HttpHost h = new HttpHost();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUname(), user.getUpwd(), user.isAuto(),h.getCliectIp(http));
		try {
			// 进行验证，这里可以捕获异常，然后返回对应信息
			subject.login(token);
		} catch (UnknownAccountException e) {
			logger.info("用户名不存在！");
			return ResultJSON.error("用户名不存在");
		} catch (AuthenticationException e) {
			logger.info("账号或密码错误！");
			return ResultJSON.error("账号或密码错误");
		} catch (AuthorizationException e) {
			logger.info("没有权限！");
			return ResultJSON.error("没有权限");
		}
		
		return ResultJSON.ok("登录成功");
	}

	@ApiOperation("用户注册")
	@PostMapping("/register")
	@RequestMapping("register")
	public ResultJSON register(@RequestBody UserDto u) {
		if (us.regiest(u)) {
			return ResultJSON.ok("注册成功");
		} else {
			logger.info("注册失败");
			return ResultJSON.error("注册失败");
		}
	}

	@ApiOperation("用户名")
	@GetMapping("/getName")
	@RequestMapping("getName")
	public ResultJSON getName() {
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

	@PostMapping("/updataPwd")
	@RequestMapping("updataPwd")
	public ResultJSON updataPwd(String name, String pwd) {
		return ResultJSON.ok(us.updataPwd(name, pwd));
	}

	@ApiOperation("登出")
	@GetMapping("/logout")
	@RequestMapping("logout")
	public String logout() {
		logger.error("退出");
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在sessionlistener监听session销毁，清理权限缓存
		}
		return "用户成功登出";
	}
}

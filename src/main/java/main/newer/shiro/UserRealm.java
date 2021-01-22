package main.newer.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import main.newer.domain.Jp;
import main.newer.domain.Jurisdiction;
import main.newer.domain.Position;
import main.newer.domain.User;
import main.newer.domain.UserPosition;
import main.newer.service.JpService;
import main.newer.service.JurisdictionService;
import main.newer.service.PositionService;
import main.newer.service.UserPositionService;
import main.newer.service.UserService;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	UserService us;
	UserPositionService ups;
	PositionService ps;
	JpService js;
	JurisdictionService j;

	@Override
	/**
	 * @MethodName doGetAuthorizationInfo
	 * @Description 权限配置类
	 * @Param [principalCollection]
	 * @Return AuthorizationInfo
	 * @Author WangShiLin
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录用户名
		String name = (String) principalCollection.getPrimaryPrincipal();
		// 查询用户名称
		User user = us.getUserByName(name);
		// 添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		for (UserPosition up : ups.getbyuid(user.getUid())) {
			for (Position p : ps.getbypid(up.getPid())) {
				// 添加角色
				simpleAuthorizationInfo.addRole(p.getPname());
				for (Jp jp : js.getbypid(p.getPid())) {
					for (Jurisdiction jur : j.getbuJid(jp.getJid())) {
						simpleAuthorizationInfo.addStringPermission(jur.getJpath());
					}
				}
			}
		}
		return simpleAuthorizationInfo;
	}

	@Override
	/**
	 * @MethodName doGetAuthenticationInfo
	 * @Description 认证配置类
	 * @Param [authenticationToken]
	 * @Return AuthenticationInfo
	 * @Author WangShiLin
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
			return null;
		}
		// 获取用户信息
		String name = authenticationToken.getPrincipal().toString();
		User user = us.getUserByName(name);
		if (user == null) {
			// 这里返回后会报出对应异常
			return null;
		} else {
			// 这里验证authenticationToken和simpleAuthenticationInfo的信息
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,
					user.getUpwd().toString(), getName());
			return simpleAuthenticationInfo;
		}
	}
}

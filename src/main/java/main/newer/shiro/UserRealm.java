package main.newer.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

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

/**
 * 同时开启身份验证和权限验证
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
	UserService us;
	UserPositionService ups;
	PositionService ps;
	JpService js;
	JurisdictionService j;
	/**
	 * 限定这个 Realm 只处理 UsernamePasswordToken
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	/**
	 * 认证(登录时调用)：查询数据库，将获取到的用户安全数据封装返回 功能：
	 * 用来进行身份认证，也就是说验证用户输入的账号和密码是否正确，获取身份验证信息，错误抛出异常
	 *
	 * @param token 用户身份信息 token
	 * @return 返回封装了用户信息的 AuthenticationInfo 实例
	 */

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		// 查询数据库获取用户信息
		User user = us.getUserByName(username);
		// 用户不存在
		if (user == null) {
			throw new UnknownAccountException("用户不存在！");
		}
		// 用户被锁定
		if (user.getState() == 1) {
			throw new LockedAccountException("该用户已被锁定，暂时无法登录！");
		}
		// 若存在，将此用户存放到登录认证info中，无需自己做密码对比Shiro会为我们进行密码对比校验
		/**
		 * 将获取到的用户数据封装成 AuthenticationInfo 对象返回，此处封装为 SimpleAuthenticationInfo 对象 参数1.
		 * 认证的实体信息，可以是从数据库中获取到的用户实体类对象或者用户名 参数2. 查询获取到的登录密码(加密后的密码) 参数3. 盐值 参数4. 当前
		 * Realm 对象的名称，直接调用父类的 getName() 方法即可
		 */
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, // 用户名
				user.getUpwd(), // 密码
				ByteSource.Util.bytes(user.getSalt()),
				getName() // realm name
		);
		return simpleAuthenticationInfo;
	}

	/**
	 * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取当前用户
		// User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
		User user = (User) principals.getPrimaryPrincipal();
		if (user == null) {
			return null;
		}
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

}

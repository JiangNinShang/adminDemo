package main.newer.shiro;


import lombok.extern.slf4j.Slf4j;
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
import main.newer.util.JwtToken;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * JwtRealm 只负责校验 JwtToken
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

	@Autowired
    UserService us;
	UserPositionService ups;
	PositionService ps;
	JpService js;
	JurisdictionService j;

    /**
     * 限定这个 Realm 只处理我们自定义的 JwtToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 默认使用此方法进行认证（用户名正确与否验证），错误抛出异常即可
     * 此处的 SimpleAuthenticationInfo 可返回任意值，密码校验时不会用到它
     * token：从Controller传递过来的，包含username
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        if (jwtToken.getPrincipal() == null) {
            throw new AccountException("JWT token参数异常！");
        }
        // 从 JwtToken 中获取当前用户的用户名
        String username = jwtToken.getPrincipal().toString();
        // 查询数据库获取用户信息
        User user = us.getUserByName(username);

        // 用户不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        // 用户被锁定
        if (user.getState() == 0) {
            throw new LockedAccountException("该用户已被锁定，暂时无法登录！");
        }

        //若存在，将此用户存放到登录认证info中，无需自己做密码对比Shiro会为我们进行密码对比校验
        /**
         * 将获取到的用户数据封装成 AuthenticationInfo 对象返回，此处封装为 SimpleAuthenticationInfo 对象
         *  参数1. 认证的实体信息，可以是从数据库中获取到的用户实体类对象或者用户名
         *  参数2. 查询获取到的登录密码(加密后的密码)
         *  参数3. 盐值
         *  参数4. 当前 Realm 对象的名称，直接调用父类的 getName() 方法即可
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, username,getName());
        return info;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户
        //User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
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

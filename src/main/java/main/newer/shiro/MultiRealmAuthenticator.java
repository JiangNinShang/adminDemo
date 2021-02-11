package main.newer.shiro;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 选择使用哪个realm来进行认证
 * 自定义认证器，解决 Shiro 异常无法返回的问题
 * 用来解决Shiro中出现的具体的认证异常无法正常返回，仅返回父类 AuthenticationException 的问题
 */
@Slf4j
public class MultiRealmAuthenticator extends ModularRealmAuthenticator {
	private static Logger logger = LoggerFactory.getLogger(MultiRealmAuthenticator.class);
	/**
	 * 通过传入数据类型来选择使用哪个realm
	 * @param realms
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token)
			throws AuthenticationException {
		AuthenticationStrategy strategy = getAuthenticationStrategy();

		AuthenticationInfo authenticationInfo = strategy.beforeAllAttempts(realms, token);

		if (logger.isTraceEnabled()) {
			logger.trace("Iterating through {} realms for PAM authentication", realms.size());
		}
		AuthenticationException authenticationException = null;
		for (Realm realm : realms) {
			authenticationInfo = strategy.beforeAttempt(realm, token, authenticationInfo);

			if (realm.supports(token)) { //调用Realm的supports方法
				logger.trace("Attempting to authenticate token [{}] using realm [{}]", token, realm);

				AuthenticationInfo info = null;
				try {
					info = realm.getAuthenticationInfo(token);  //调用Realm的doGetAuthenticationInfo方法
				} catch (AuthenticationException e) {
					authenticationException = e;
					if (logger.isDebugEnabled()) {
						String msg = "Realm [" + realm
								+ "] threw an exception during a multi-realm authentication attempt:";
						logger.debug(msg, e);
					}
				}
				authenticationInfo = strategy.afterAttempt(realm, token, info, authenticationInfo, authenticationException);
			} else {
				logger.debug("Realm [{}] does not support token {}.  Skipping realm.", realm, token);
			}
		}
		if (authenticationException != null) {
			throw authenticationException;
		}
		authenticationInfo = strategy.afterAllAttempts(token, authenticationInfo);

		return authenticationInfo;
	}
}

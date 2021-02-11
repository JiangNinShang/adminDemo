package main.newer.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import main.newer.util.GlobalExceptionHandler;
import main.newer.util.JwtConst;

import java.io.UnsupportedEncodingException;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class JwtCredentialsMatcher implements CredentialsMatcher {
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * JwtCredentialsMatcher只需验证JwtToken内容是否合法
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
		String token = authenticationToken.getCredentials().toString();
		String username = authenticationToken.getPrincipal().toString();
		try {
			Algorithm algorithm = Algorithm.HMAC256(JwtConst.SECRET);
			JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
			verifier.verify(token);
			return true;
		} catch (JWTVerificationException e) {
			logger.error(e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

}

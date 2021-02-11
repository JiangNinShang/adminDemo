package  main.newer.util;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
	private static final long serialVersionUID = -1573130169526415317L;

	/**
	 * 加密后的 JWT token串
 	 */
	private String token;

	private String userName;

	public JwtToken(String token) {
		this.token = token;
		this.userName = JwtUtil.getUsername(token, "username");
	}

	@Override
	public Object getPrincipal() {
		return this.userName;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}
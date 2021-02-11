package  main.newer.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Jwt工具类
 */
public class JwtUtil {

	/**
	 * 生成签名，指定过期时间
	 *
	 * @param username 用户名
	 * @param secret 密钥
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalArgumentException 
	 */
	public static String createToken(String username, String secret) throws IllegalArgumentException, UnsupportedEncodingException {
		try {
			// 设置过期时间
			Date date = new Date(System.currentTimeMillis() + JwtConst.EXPIRE_TIME);
			// 私钥和加密算法
			Algorithm algorithm = Algorithm.HMAC256(secret);
			// 设置头部信息
			Map<String, Object> header = new HashMap<>(2);
			header.put("Type", "Jwt");
			header.put("alg", "HS256");
			// 附带username信息
			return JWT.create()
					.withClaim("username", username)
					.withIssuedAt(new Date())
					//到期时间
					.withExpiresAt(date)
					//创建一个新的JWT，并使用给定的算法进行标记
					.sign(algorithm);
		} catch (JWTCreationException e) {
			return null;
		}
	}

	/**
	 * 验证token是否正确
	 *
	 * @param token
	 * @param username 用户名
	 * @param secret 密钥
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalArgumentException 
	 */
	public static boolean verify(String token, String username, String secret) throws IllegalArgumentException, UnsupportedEncodingException {
		try {
			Algorithm algorithm = null;
			try {
				algorithm = Algorithm.HMAC256(secret);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JWTVerifier verifier = JWT.require(algorithm)
					//在token中附带username信息
					.withClaim("username", username)
					.build();
			//验证 token
			verifier.verify(token);
			return true;
		} catch (JWTVerificationException exception) {
			return false;
		}
	}

	/**
	 * 获得token中的用户名信息，无需secret解密也能获得
	 *
	 * @param token
	 * @param filed
	 * @return 返回token中的用户名信息
	 */
	public static String getUsername(String token, String filed) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim(filed).asString();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 *  获取 token的签发时间
	 *
	 * @param token
	 * @return
	 */
	public static Date getIssuedAt(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getIssuedAt();
		} catch (JWTDecodeException e) {
			return null;
		}
	}
	/**
	 *  获取 token的过期时间
	 *
	 * @param token
	 * @return
	 */
	public static Date getExpiresAt(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getExpiresAt();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * 验证 token是否过期
	 *
	 * @param token
	 * @return
	 */
	public static boolean isTokenExpired(String token) {
		Date now = Calendar.getInstance().getTime();
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getExpiresAt().before(now);
	}

	/**
	 * 刷新 token的过期时间
	 *
	 * @param token
	 * @param secret
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalArgumentException 
	 */
	public static String refreshTokenExpired(String token, String secret) throws IllegalArgumentException, UnsupportedEncodingException {
		DecodedJWT jwt = JWT.decode(token);
		Map<String, Claim> claims = jwt.getClaims();
		try {
			Date date = new Date(System.currentTimeMillis() + JwtConst.EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(secret);
			Builder builer = JWT.create().withExpiresAt(date);
			for (Entry<String, Claim> entry : claims.entrySet()) {
				builer.withClaim(entry.getKey(), entry.getValue().asString());
			}
			// 附带username，nickname信息
			return builer.sign(algorithm);
		} catch (JWTCreationException e) {
			return null;
		}
	}

	/**
	 * 生成8位随机盐
	 */
	public static String genSalt(){
		return  RandomStringUtils.randomAlphabetic(8);
	}
	/**
	 * 加盐加密
	 */
	public static String saltPassword(String crdentials,String salt){
		ByteSource byteSource = ByteSource.Util.bytes(salt);
		SimpleHash simpleHash = new SimpleHash("MD5", crdentials, byteSource, 1024);
		return simpleHash.toString();
	}

	public static void main(String[] args) throws IllegalArgumentException, UnsupportedEncodingException {
		Date ss = getExpiresAt("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDI3MzQ2OTQsImlhdCI6MTYwMjczNDM5NCwidXNlcm5hbWUiOiJsaXNpIn0.3ScUijtPidzMMJz-i26KFOyg5sideJv0sMQe1neZA18");
		System.out.println(ss);
		String token = createToken("zhangsan", JwtConst.SECRET);
		System.out.println("token: "+token);
		boolean verify = verify(token, "zhangsan", JwtConst.SECRET);
		System.out.println("verify: "+verify);
		boolean tokenExpired = isTokenExpired(token);
		System.out.println("tokenExpired: "+tokenExpired);
		Date issuedAt = getIssuedAt(token);
		System.out.println("issuedAt: "+issuedAt);
		Date expiresAt = getExpiresAt(token);
		System.out.println("expiresAt: "+expiresAt);
		String username = getUsername(token, "username");
		System.out.println(username);


		String salt = genSalt();
		System.out.println(salt);
		String password = saltPassword("123456", salt);
		System.out.println(password);
	}
}
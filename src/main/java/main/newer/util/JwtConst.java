package  main.newer.util;

public interface JwtConst {
    /**
     * 过期时间45分钟
      */
    long EXPIRE_TIME = 45 * 60 * 1000;

    /**
     * 私钥
     */
    String SECRET = "SECRET_VALUE";


    /**
     * Header头
     */
    String AUTH_HEADER = "x-auth";
}

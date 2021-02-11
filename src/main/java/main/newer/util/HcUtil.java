package main.newer.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class HcUtil {

    public static int SHIRO_DECODE_ITERATIONS = 1024;

    /**
     * 生成指定位数的随机盐
     */
    public static String genSalt(Integer length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    /**
     * 加盐加密
     */
    public static String saltPassword(String crdentials, String salt) {
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        SimpleHash simpleHash = new SimpleHash("MD5", crdentials, byteSource, SHIRO_DECODE_ITERATIONS);
        return simpleHash.toString();
    }
    //这两个方法的功能一样
    public static String encodePassword(String password,String salt){
        //重点：对明文密码进行MD5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(password, salt, HcUtil.SHIRO_DECODE_ITERATIONS);
        return md5Hash.toHex();
    }

    public static void main(String[] args) {
        String salt = genSalt(8);
        System.out.println(salt);
        String password = saltPassword("123456", salt);
        System.out.println(password);
        String s = encodePassword("123456", salt);
        System.out.println(s);
    }
}

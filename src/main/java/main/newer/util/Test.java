package main.newer.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Test {
	 public static void main(String[] args) {
	        String password = "123580aa";//要加密的字符串
	        String salt = "jnsnb";//盐
	        Integer hashIterations = 1024;//散列次数
	        //4.利用SimpleHash来设置md5(上面三种都可以通过这个来设置，这里举例加盐加散列次数的)
	        //第一个参数是算法名称，这里指定md5，第二个是要加密的密码，第三个参数是加盐，第四个是散列次数
	        SimpleHash hash = new SimpleHash("md5", password, salt,hashIterations);
	        System.out.println(hash.toString());
	    }
}

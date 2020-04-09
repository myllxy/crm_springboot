package cn.myllxy.crm.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Util {
    //加密方式
    public static final String ALGORITHMNAME = "MD5";
    //盐值
    public static final String SALT = "itsource";
    //加密次数
    public static final Integer HASHITERATIONS = 10;


    public static String createMd5(String source) {
        SimpleHash hash = new SimpleHash(ALGORITHMNAME, source, SALT, HASHITERATIONS);
        return hash.toString();
    }
}
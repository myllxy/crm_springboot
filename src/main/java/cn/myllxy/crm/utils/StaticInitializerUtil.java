package cn.myllxy.crm.utils;


import org.springframework.stereotype.Component;

@Component
public class StaticInitializerUtil {
    public StaticInitializerUtil(TokenSettings tokenSettings,RedisSettings redisSettings) {
        JwtTokenUtil.setTokenSettings(tokenSettings);
        RedisUtil.setRedisSettings(redisSettings);
    }
}

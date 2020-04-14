package cn.myllxy.crm.utils;

import cn.myllxy.crm.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


/**
 * 获取连接池对象
 */
@Slf4j
public class RedisUtil {
    private static String host;
    private static int port;
    private static long timeout;
    private static int maxidle;
    private static int maxtotal;
    private static long maxwaitmillis;
    private static boolean testonborrow;
    private static String password;

    private static JedisPool jedisPool = null;

    public static void setRedisSettings(RedisSettings redisSettings) {
        host = redisSettings.getHost();
        port = redisSettings.getPort();
        timeout = redisSettings.getTimeout();
        maxidle = redisSettings.getMaxidle();
        maxtotal = redisSettings.getMaxtotal();
        maxwaitmillis = redisSettings.getMaxwaitmillis();
        testonborrow = redisSettings.isTestonborrow();
        password = redisSettings.getPassword();
        //1 创建连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //2 进行配置-四个配置
        config.setMaxIdle(maxidle);//最小连接数
        config.setMaxTotal(maxtotal);//最大连接数
        config.setMaxWaitMillis(maxwaitmillis);//最长等待时间
        config.setTestOnBorrow(testonborrow);//测试连接时是否畅通
        jedisPool = new JedisPool(config, host, port, (int) timeout, password);
    }

    //获取连接
    public static Jedis getSource() {
        return jedisPool.getResource();
    }

    //关闭资源
    public static void closeSource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }

    /**
     * 设置字符值
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        Jedis jedis = getSource();
        jedis.set(key, value);
        closeSource(jedis);
    }

    /**
     * 设置
     *
     * @param key
     * @param value
     */
    public static void set(byte[] key, byte[] value) {
        Jedis jedis = getSource();
        jedis.set(key, value);
        closeSource(jedis);
    }

    /**
     * 添加set元素
     *
     * @param key
     * @param value
     */
    public static void sAdd(String key, String... value) {
        Jedis jedis = getSource();
        jedis.sadd(key, value);
        closeSource(jedis);
    }

    /**
     * 移除set集合中的某个元素
     *
     * @param key
     * @param value
     */
    public static void sRemove(String key, String... value) {
        Jedis jedis = getSource();
        jedis.srem(key, value);
        closeSource(jedis);
    }

    /**
     * 查找redis中指定key的某个set集合内容
     *
     * @param key
     * @return redis中指定key的某个set集合
     */
    public static Set sMembers(String key) {
        Jedis jedis = getSource();
        try {
            return jedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
        return null;
    }

    /**
     * @param key
     * @return
     */
    public static byte[] get(byte[] key) {
        Jedis jedis = getSource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
        return null;
    }

    /**
     * 设置字符值
     *
     * @param key
     */
    public static String get(String key) {
        Jedis jedis = getSource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
        return null;
    }

    public static void delete(String key) {
        Jedis jedis = getSource();
        try {
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
    }

    /**
     * 判断是否存在该key
     *
     * @param key
     */
    public static boolean isExist(String key) {
        Jedis jedis = getSource();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
        return false;
    }

    /**
     * 获取所有点赞相关数据
     *
     * @return
     */
    public static HashMap<String, Object> getLikeKeys() {
        Jedis jedis = getSource();
        try {
            HashMap<String, Object> map = new HashMap<>();
            /* 前置模糊查询出所有匹配的key */
            Set<String> pointListKeys = jedis.keys("*" + RedisConstant.POINTLIST);
            Set<String> pointCountKeys = jedis.keys("*" + RedisConstant.POINTCOUNT);
            Iterator<String> pointListIterator = pointCountKeys.iterator();
            Iterator<String> pointCountIterator = pointListKeys.iterator();
            while (pointListIterator.hasNext()) {
                String next = pointListIterator.next();
                /* 这里的value是set */
                map.put(next, sMembers(next));
            }
            while (pointCountIterator.hasNext()) {
                String next = pointCountIterator.next();
                /* 这里的value是普通字符串 */
                map.put(next, get(next));
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSource(jedis);
        }
        return null;
    }

}

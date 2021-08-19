package top.sakura.crm.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-17 20:01
 */
public class RedisPoolUtils {

    private static JedisPool jedisPool = null;

    public static JedisPool openPool(String host, int port) {
        if (jedisPool == null) {
            //创建连接池对象
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //连接池中最大Jedis实例数量，默认是8
            jedisPoolConfig.setMaxTotal(10);
            //设置最大空闲连接数，可以保留足够的连接，让获取连接快速
            jedisPoolConfig.setMaxIdle(3);
            //提前检查Jedis连接，true表示获取的Jedis连接一定是可用的
            jedisPoolConfig.setTestOnBorrow(true);
            //创建Jedis连接池，没有设置Redis访问密码时使用以下方式
            jedisPool = new JedisPool(jedisPoolConfig,host,port);

            //设置了Redis访问密码时使用以下方式
                                        //Jedis连接池配置 ip 端口 超时时间 密码
            //jedisPool = new JedisPool(jedisPoolConfig,host,port,6*1000,"123456");
        }
        return jedisPool;
    }

    public static void close() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }
}

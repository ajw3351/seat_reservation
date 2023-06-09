package ajw.Login;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Database {
    private static final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    private static final String ip = "210.115.43.184";
    private static final int port = 6379;
    private static final int timeOut = 10000;
    private static final String requirepass = "pass";
    private static final JedisPool pool = new JedisPool(jedisPoolConfig, ip, port, timeOut, requirepass);
    private static final ThreadLocal<Jedis> jedis = new ThreadLocal<>();

    private static final Database mInstance = new Database();

    public static Database getInstance() {
        return mInstance;
    }
    /*
     * 제디스 객체를 스레드별로 하나씩 사용할 수 있도록 함
     */
    public Jedis getJedis() {
        var result = jedis.get();
        if (result == null) {
            result = pool.getResource();
            jedis.set(result);
        }
        return result;
    }
}

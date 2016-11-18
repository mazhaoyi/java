package mb_redis_core.com.doordu.redis.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * redis数据源
 */
public interface RedisDataSource {
	/**
	 * 获取redis客户端
	 * @param isSlaver 是否是从库client
	 * @return redis客户端
	 */
	public ShardedJedis getClient();
	/**
	 * 从集群获得jedis客户端
	 * @return
	 */
	public Jedis getJedis();
}

package mb_redis_core.com.doordu.redis.pool.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mb_redis_core.com.doordu.redis.pool.RedisDataSource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Component
public class RedisDataSourceImpl implements RedisDataSource {
	private static final Logger logger = LoggerFactory.getLogger(RedisDataSourceImpl.class);
	
	@Autowired
	private ShardedJedisPool sharedJedisPool;
	
	@Autowired
	private JedisSentinelPool jedisSentinelPool;
	
	@Override
	public ShardedJedis getClient() {
		try {
			ShardedJedis sharedJedis = null;
			sharedJedis = sharedJedisPool.getResource();
			return sharedJedis;
		} catch (Exception e) {
			logger.error("get SharedJedis error !", e);
		}
		return null;
	}

	@Override
	public Jedis getJedis() {
		try {
			Jedis jedis = null;
			jedis = jedisSentinelPool.getResource();
			return jedis;
		} catch (Exception e) {
			logger.error("get Jedis error !", e);
		}
		return null;
	}

}

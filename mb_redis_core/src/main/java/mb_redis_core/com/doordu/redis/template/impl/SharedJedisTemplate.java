package mb_redis_core.com.doordu.redis.template.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mb_redis_core.com.doordu.redis.pool.RedisDataSource;
import mb_redis_core.com.doordu.redis.serialize.impl.FastJsonRedisSerializer;
import mb_redis_core.com.doordu.redis.template.RedisClientTemplate;
import redis.clients.jedis.ShardedJedis;
/**
 * 适合多个master
 *
 */
@Component
public class SharedJedisTemplate implements RedisClientTemplate {
	private static final Logger logger = LoggerFactory.getLogger(SharedJedisTemplate.class);
	
	@Autowired
	private RedisDataSource redisDataSource;
	
	@Autowired
	private FastJsonRedisSerializer serializer;
	
	public <K, V> String set(K key, V value) {
		String resp = null;
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return resp;
			}
			resp = shardedJedis.set(serializer.serialize(key), serializer.serialize(value));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K, V> V get(K key) {
		ShardedJedis shardedJedis = null;
		V value = null;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return null;
			}
			byte[] resp = shardedJedis.get(serializer.serialize(key));
			value = serializer.deserialize(resp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

	@Override
	public <K> boolean del(K key) {
		ShardedJedis shardedJedis = null;
		boolean resp = false;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return resp;
			}
			Long respl = shardedJedis.del(serializer.serialize(key));
			resp = respl > 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> boolean rpush(K key, V... values) {
		ShardedJedis shardedJedis = null;
		boolean resp = false;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return resp;
			}
			Long respl = shardedJedis.rpush(serializer.serialize(key), serializer.serialize(values));
			resp = respl > 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> boolean lpush(K key, V... values) {
		ShardedJedis shardedJedis = null;
		boolean resp = false;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return resp;
			}
			Long respl = shardedJedis.lpush(serializer.serialize(key), serializer.serialize(values));
			resp = respl > 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K> long llen(K key) {
		ShardedJedis shardedJedis = null;
		long resp = 0;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return resp;
			}
			resp = shardedJedis.llen(serializer.serialize(key));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K, V> V lindex(K key, long index) {
		ShardedJedis shardedJedis = null;
		V value = null;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return value;
			}
			byte[] tmp = shardedJedis.lindex(serializer.serialize(key), index);
			if (tmp == null) {
				return value;
			}
			value = serializer.deserialize(tmp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

	@Override
	public <K, V> String lset(K key, long index, V value) {
		ShardedJedis shardedJedis = null;
		String resp = null;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return resp;
			}
			resp = shardedJedis.lset(serializer.serialize(key), index, serializer.serialize(value));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K, V> long lrem(K key, long count, V value) {
		ShardedJedis shardedJedis = null;
		long resp = 0;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return resp;
			}
			resp = shardedJedis.lrem(serializer.serialize(key), count, serializer.serialize(value));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K, V> V lpop(K key) {
		ShardedJedis shardedJedis = null;
		V value = null;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return value;
			}
			byte[] tmp = shardedJedis.lpop(serializer.serialize(key));
			if (tmp == null) {
				return value;
			}
			value = serializer.deserialize(tmp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

	@Override
	public <K, V> V rpop(K key) {
		ShardedJedis shardedJedis = null;
		V value = null;
		try {
			shardedJedis = redisDataSource.getClient();
			if (shardedJedis == null) {
				logger.error("ShardedJedis is null ! Please check it !");
				return value;
			}
			byte[] tmp = shardedJedis.rpop(serializer.serialize(key));
			if (tmp == null) {
				return value;
			}
			value = serializer.deserialize(tmp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}
		}
		return value;
	}

}

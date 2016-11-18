package mb_redis_core.com.doordu.redis.template.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mb_redis_core.com.doordu.redis.pool.RedisDataSource;
import mb_redis_core.com.doordu.redis.serialize.impl.FastJsonRedisSerializer;
import mb_redis_core.com.doordu.redis.template.RedisClientTemplate;
import redis.clients.jedis.Jedis;

/**
 * 一个master多个slaver
 * 如果master挂了则下个salver顶上作为master
 * 适合主从备份
 * @author admin
 *
 */
@Component
public class JedisTemplate implements RedisClientTemplate {
	private static final Logger logger = LoggerFactory.getLogger(JedisTemplate.class);
	
	@Autowired
	private RedisDataSource redisDataSource;
	
	@Autowired
	private FastJsonRedisSerializer serializer;
	
	public <K, V> String set(K key, V value) {
		String resp = null;
		Jedis jedis = null;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return resp;
			}
			resp = jedis.set(serializer.serialize(key), serializer.serialize(value));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K, V> V get(K key) {
		Jedis jedis = null;
		V value = null;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return null;
			}
			byte[] resp = jedis.get(serializer.serialize(key));
			value = serializer.deserialize(resp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	@Override
	public <K> boolean del(K key) {
		Jedis jedis = null;
		boolean resp = false;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return resp;
			}
			Long respl = jedis.del(serializer.serialize(key));
			resp = respl > 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> boolean rpush(K key, V... values) {
		Jedis jedis = null;
		boolean resp = false;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return resp;
			}
			Long respl = jedis.rpush(serializer.serialize(key), serializer.serialize(values));
			resp = respl > 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> boolean lpush(K key, V... values) {
		Jedis jedis = null;
		boolean resp = false;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return resp;
			}
			Long respl = jedis.lpush(serializer.serialize(key), serializer.serialize(values));
			resp = respl > 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K> long llen(K key) {
		Jedis jedis = null;
		long resp = 0;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return resp;
			}
			resp = jedis.llen(serializer.serialize(key));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K, V> V lindex(K key, long index) {
		Jedis jedis = null;
		V value = null;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return value;
			}
			byte[] tmp = jedis.lindex(serializer.serialize(key), index);
			if (tmp == null) {
				return value;
			}
			value = serializer.deserialize(tmp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	@Override
	public <K, V> String lset(K key, long index, V value) {
		Jedis jedis = null;
		String resp = null;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return resp;
			}
			resp = jedis.lset(serializer.serialize(key), index, serializer.serialize(value));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K, V> long lrem(K key, long count, V value) {
		Jedis jedis = null;
		long resp = 0;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return resp;
			}
			resp = jedis.lrem(serializer.serialize(key), count, serializer.serialize(value));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return resp;
	}

	@Override
	public <K, V> V lpop(K key) {
		Jedis jedis = null;
		V value = null;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return value;
			}
			byte[] tmp = jedis.lpop(serializer.serialize(key));
			if (tmp == null) {
				return value;
			}
			value = serializer.deserialize(tmp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

	@Override
	public <K, V> V rpop(K key) {
		Jedis jedis = null;
		V value = null;
		try {
			jedis = redisDataSource.getJedis();
			if (jedis == null) {
				logger.error("jedis is null ! Please check it !");
				return value;
			}
			byte[] tmp = jedis.rpop(serializer.serialize(key));
			if (tmp == null) {
				return value;
			}
			value = serializer.deserialize(tmp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return value;
	}

}

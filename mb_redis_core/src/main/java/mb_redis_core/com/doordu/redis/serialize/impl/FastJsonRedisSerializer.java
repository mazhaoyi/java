package mb_redis_core.com.doordu.redis.serialize.impl;

import org.apache.commons.lang3.SerializationException;
import org.springframework.stereotype.Component;

import mb_redis_core.com.doordu.redis.serialize.RedisSerializer;
import mb_redis_util.com.doordu.util.FastJsonSerializerUtils;

/**
 * FastJson序列化redis数据
 * @author admin
 *
 * @param <T>
 */
@Component
public class FastJsonRedisSerializer implements RedisSerializer {

	@Override
	public <T> byte[] serialize(T t) throws SerializationException {
		return FastJsonSerializerUtils.serialize(t);
	}

	@Override
	public <T> T deserialize(byte[] bytes) throws SerializationException {
		return FastJsonSerializerUtils.deserialize(bytes);
	}

}

package mb_redis_core.com.doordu.redis.serialize;

import org.apache.commons.lang.SerializationException;

/**
 * redis对象序列化
 * @author admin
 *
 */
public interface RedisSerializer {
	/**
	 * Serialize the given object to binary data.
	 * 
	 * @param t object to serialize
	 * @return the equivalent binary data
	 */
	public <T> byte[] serialize(T t) throws SerializationException;

	/**
	 * Deserialize an object from the given binary data.
	 * 
	 * @param bytes object binary representation
	 * @return the equivalent object instance
	 */
	public <T> T deserialize(byte[] bytes) throws SerializationException;
}

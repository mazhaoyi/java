package mb_redis_util.com.doordu.util;

import org.apache.commons.lang.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * FastJson序列化Object
 *
 */
public class FastJsonSerializerUtils {
	public static final byte[] EMPTY_ARRAY = new byte[0];
	
	/**
	 * Serialize the given object to binary data.
	 * 
	 * @param t object to serialize
	 * @return the equivalent binary data
	 */
	public static <T> byte[] serialize(T t) throws SerializationException {
		if (t == null) {
			return EMPTY_ARRAY;
		}
		return JSON.toJSONBytes(t, SerializerFeature.WriteClassName);
	}

	/**
	 * Deserialize an object from the given binary data.
	 * 
	 * @param bytes object binary representation
	 * @return the equivalent object instance
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		return (T) JSON.parse(bytes);
	}
}

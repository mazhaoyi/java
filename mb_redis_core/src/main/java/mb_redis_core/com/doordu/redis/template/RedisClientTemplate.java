package mb_redis_core.com.doordu.redis.template;
/**
 * redis客户端模板，处理各种数据
 */
public interface RedisClientTemplate {
	/**
	 * 存储一个值
	 * @param key 任意 Object 类型
	 * @param value  任意 Object 类型
	 * @return String 
	 */
	public <K, V> String set(K key, V value);
	/**
	 * 获取一个值
	 * @param key 任意 Object 类型
	 * @return 任意 Object 类型
	 */
	public <K, V> V get(K key);
	/**
	 * 删除一个值
	 * @param key 任意 Object 类型
	 * @return boolean
	 */
	public <K> boolean del(K key);
	/**
	 * 在key所关联的List尾部插入所给出的所有的values
	 * @param key 任意 Object 类型
	 * @param values
	 */
	@SuppressWarnings("unchecked")
	public <K, V> boolean rpush(K key, V... values);
	/**
	 * 在key所关联的List头部插入所给出的所有的values
	 * @param key 任意 Object 类型
	 * @param values
	 */
	@SuppressWarnings("unchecked")
	public <K, V> boolean lpush(K key, V... values);
	/**
	 * 查询key所关联的List的长度，如果为空就返回0，如果关联的不是List，就抛出错误信息
	 * @param key
	 * @return
	 */
	public <K> long llen(K key);
	/**
	 * 查询key关联的List指定的下标的元素，index = -1表示尾部元素
	 * @param key
	 * @param index
	 * @return
	 */
	public <K, V> V lindex(K key, long index);
	/**
	 * 在key关联的List指定的下标index，设置新值
	 * @param key
	 * @param index
	 * @param value
	 */
	public <K, V> String lset(K key, long index, V value);
	/**
	 * 在指定的key关联的List中，删除count个值等于value的元素。
	 * 
	 * count > 0，从头向尾遍历并删除。
	 * count < 0，从尾向头遍历并删除。
	 * count = 0，删除链表中所有的值等于value的元素
	 * 
	 * @param key
	 * @param count
	 * @param value
	 * @return 返回删除的元素的数量，如果key关联的List不存在，直接返回0。
	 */
	public <K, V> long lrem(K key, long count, V value);
	/**
	 * 返回并弹出指定key关联的链表的第一个元素，即头部元素，如果该key不存在，则返回null
	 * @param key
	 * @return
	 */
	public <K, V> V lpop(K key);
	/**
	 * 返回并弹出指定key关联的链表的最后一个元素，即尾部元素，如果该key不存在，则返回null
	 * @param key
	 * @return
	 */
	public <K, V> V rpop(K key);
}

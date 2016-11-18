package mb_redis_core.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mb_redis_core.com.doordu.redis.template.RedisClientTemplate;

public class RedisTest extends BaseJunit4Test {
	@Autowired
	private RedisClientTemplate jedisTemplate;
	
	@Test
	public void setString() {
		String key = "g";
		String value = "good";
		String res = jedisTemplate.set(key, value);
		System.out.println(res);
	}
	
	@Test
	public void setLong() {
		Long key = 100L;
		Long value = 200L;
		String res = jedisTemplate.set(key, value);
		System.out.println(res);
		
		Long v = jedisTemplate.get(key);
		System.out.println(v.getClass());
	}
	
	@Test
	public void setMap() {
		Map<Integer, List<String>> map = new HashMap<>();
		List<String> list1 = new ArrayList<>();
		list1.add("123");
		list1.add("456");
		List<String> list2 = new ArrayList<>();
		list2.add("abc");
		list2.add("def");
		map.put(1, list1);
		map.put(2, list2);
		
		String res = jedisTemplate.set(111, map);
		System.out.println(res);
	}
	
	@Test
	public void get() {
		String res1 = jedisTemplate.get(123);
		System.out.println(res1);
		Map<Integer, List<String>> map = jedisTemplate.get(111);
		System.out.println(map);
		
		List<String> list1 = map.get(1);
		List<String> list2 = map.get(2);
		for (String str : list1) {
			System.out.println(str);
		}
		for (String str : list2) {
			System.out.println(str);
		}
	}
	
	@Test
	public void del() {
		boolean res = jedisTemplate.del(123);
		System.out.println(res);
		boolean res2 = jedisTemplate.del(111);
		System.out.println(res2);
		boolean res3 = jedisTemplate.del(null);
		System.out.println(res3);
		boolean res4 = jedisTemplate.del(100L);
		System.out.println(res4);
		boolean res5 = jedisTemplate.del("g");
		System.out.println(res5);
	}
}

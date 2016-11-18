package mb_redis_core.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import mb_redis_core.com.doordu.redis.template.RedisClientTemplate;
import mb_redis_core.core.vo.UserRTExtVo;
import mb_redis_core.core.vo.UserRTVo;

public class RedisListTest extends BaseJunit4Test {
	private static final Logger logger = LoggerFactory.getLogger(RedisListTest.class);
	
	@Autowired
	private RedisClientTemplate jedisTemplate;
	
	@Test
	public void addList() {
		List<Map<Integer, UserRTVo>> users = new ArrayList<>();
		for (int j = 0; j < 5; j++) {
			Map<Integer, UserRTVo> userMap1 = new HashMap<>();
			for (int n = 0; n < 5; n++) {
				UserRTVo ur1 = new UserRTVo();
				ur1.setUserId(j);
				ur1.setName("zhangsan -------- " + j + "----" + n);
				if (j % 2 == 0) {
					ur1.setIsAdmin(true);
				} else {
					ur1.setIsAdmin(false);
				}
				List<UserRTExtVo> urvs = new ArrayList<>();
				for (int i = 0; i < 5; i++) {
					UserRTExtVo urev = new UserRTExtVo();
					urev.setSay("good morning ! ---- " + i + "===" + j + "----" + n);
					urev.setCreateTime(new Date());
					urvs.add(urev);
				}
				ur1.setExts(urvs);
				userMap1.put(j, ur1);
			}
			users.add(userMap1);
		}
		String res = jedisTemplate.set(888, users);
		System.out.println(res);
	}
	
	@Test
	public void getList() {
		logger.info("get redis key 888");
		List<Map<Integer, UserRTVo>> users = jedisTemplate.get(888);
		for (Map<Integer, UserRTVo> user : users) {
			for (Map.Entry<Integer, UserRTVo> entry : user.entrySet()) {
				System.out.println(entry.getKey());
				UserRTVo u = entry.getValue();
				System.out.println(u.getUserId() + ">>>" + u.getIsAdmin() + ">>>" + u.getName());
				List<UserRTExtVo> ul = u.getExts();
				for (UserRTExtVo uv : ul) {
					System.out.println(uv.getCreateTime() + ">>>>" + uv.getSay());
				}
			}
		}
	}
	
	@Test
	public void remove() {
		logger.info("remove redis key 888");
		boolean res = jedisTemplate.del(888);
		logger.info("remove redis key 888 result is {}", res);
	}
}

package mb_redis_user_s.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.alibaba.fastjson.JSON;

import mb_redis_user_d.com.doordu.entity.UserEntity;
import mb_redis_user_d.com.doordu.entity.ext.UserProperties;
import mb_redis_user_s.com.doordu.service.UserService;

public class UserServiceTest extends BaseJunit4Test {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	@Autowired
	private UserService service;
	
	@Test
//	@Rollback(false)
	public void save() throws ParseException {
		UserEntity user = new UserEntity();
		user.setAge(28);
		user.setBirthday(DateFormat.getDateInstance().parse("1989-05-04"));
		user.setCreateTime(new Date());
		user.setIsValid(true);
		user.setName("张三");
		
		UserProperties userInfoExt = new UserProperties();
		userInfoExt.setSex(1);
//		userInfoExt.setSay("good morning !");
		userInfoExt.setLike("beautiful girl ! 么么哒 ！");
		
		user.setProperties(userInfoExt);
		
		UserEntity entity = service.save(user);
		logger.info("saved user.userId = {}", entity.getUserId());
	}
	
	@Test
	public void get() {
		UserEntity user = service.getById(3);
		logger.info("user = {}", JSON.toJSONString(user));
	}
}

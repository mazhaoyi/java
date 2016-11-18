package mb_redis_c.com.doordu.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import mb_redis_c.com.doordu.user.vo.UserVo;
import mb_redis_user_d.com.doordu.entity.UserEntity;
import mb_redis_user_s.com.doordu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/get")
	@ResponseBody
	public UserEntity get(Integer userId) {
		logger.info("userId = {}", userId);
		return userService.getById(userId);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public UserEntity save(@RequestBody UserVo user) {
		String userJson = JSON.toJSONString(user);
		logger.info(userJson);
		UserEntity ue = JSON.parseObject(userJson, UserEntity.class);
		return userService.save(ue);
	}
}

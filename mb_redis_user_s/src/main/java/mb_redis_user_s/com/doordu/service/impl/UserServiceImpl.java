package mb_redis_user_s.com.doordu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mb_redis_user_d.com.doordu.dao.UserDao;
import mb_redis_user_d.com.doordu.entity.UserEntity;
import mb_redis_user_s.com.doordu.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public List<UserEntity> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	public UserEntity getById(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.getById(userId);
	}

	public UserEntity save(UserEntity user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		return user;
	}

	public UserEntity update(UserEntity user) {
		// TODO Auto-generated method stub
		userDao.update(user);
		return user;
	}

}

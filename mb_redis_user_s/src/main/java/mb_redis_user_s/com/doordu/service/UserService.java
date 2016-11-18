package mb_redis_user_s.com.doordu.service;

import java.util.List;

import mb_redis_user_d.com.doordu.entity.UserEntity;

public interface UserService {
public List<UserEntity> list();
	
	public UserEntity getById(Integer userId);
	
	public UserEntity save(UserEntity user);
	
	public UserEntity update(UserEntity user);
}

package mb_redis_user_d.com.doordu.dao;

import java.util.List;

import mb_redis_user_d.com.doordu.entity.UserEntity;

public interface UserDao {
	public List<UserEntity> list();
	
	public UserEntity getById(Integer userId);
	
	public int save(UserEntity user);
	
	public int update(UserEntity user);
}

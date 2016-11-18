package mb_redis_test_d.com.doordu.dao;

import java.util.List;

import mb_redis_test_d.com.doordu.entity.RoleEntity;

public interface RoleDao {
	public int save(RoleEntity role);
	
	public List<RoleEntity> list();
	
	public int update(RoleEntity role);
}

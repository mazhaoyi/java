package mb_redis_role_s.com.doordu.service;

import java.util.List;

import mb_redis_role_d.com.doordu.entity.RoleEntity;

public interface RoleService {
	
	public int save(RoleEntity role);
	
	public List<RoleEntity> list();
	
	public int update(RoleEntity role);
}

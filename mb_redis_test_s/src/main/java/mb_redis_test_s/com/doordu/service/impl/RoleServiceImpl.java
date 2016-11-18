package mb_redis_test_s.com.doordu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mb_redis_test_d.com.doordu.dao.RoleDao;
import mb_redis_test_d.com.doordu.entity.RoleEntity;
import mb_redis_test_s.com.doordu.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public int save(RoleEntity role) {
		// TODO Auto-generated method stub
		return roleDao.save(role);
	}

	@Override
	public List<RoleEntity> list() {
		return roleDao.list();
	}

	@Override
	public int update(RoleEntity role) {
		return roleDao.update(role);
	}

}

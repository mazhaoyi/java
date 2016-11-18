package mb_redis_test_s.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.alibaba.fastjson.JSON;

import mb_redis_test_d.com.doordu.entity.RoleEntity;
import mb_redis_test_d.com.doordu.entity.ext.RolePros;
import mb_redis_test_s.com.doordu.service.RoleService;

public class RoleTest extends BaseJunit4Test {
	@Autowired
	private RoleService roleService;
	
	@Test
//	@Rollback(false)
	public void save() {
		RoleEntity role = new RoleEntity();
		role.setName("网管");
		
		RolePros pros = new RolePros();
		pros.setIsAdmin(true);
		pros.setRoleAge(22);
		pros.setRoleDate(new Date());
		pros.setRoleSay("role say morning !");
		role.setPros(pros);
		
		roleService.save(role);
		System.out.println(JSON.toJSONString(role));
	}
	
	@Test
	public void list() {
		List<RoleEntity> roles = roleService.list();
		for (RoleEntity role : roles) {
			System.out.println(JSON.toJSON(role));
			System.out.println(role.getRoleId() + "--" + role.getName());
			RolePros rp = role.getPros();
			if (rp != null) {
				System.out.println(rp.getRoleSay() + "--" + rp.getRoleAge() + "--" + rp.getIsAdmin() + "--" + rp.getRoleDate());
			}
			System.out.println("---------------------------------------");
		}
	}
}

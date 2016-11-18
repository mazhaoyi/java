package mb_redis_c.com.doordu.role.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import mb_redis_c.com.doordu.role.vo.RoleVo;
import mb_redis_test_d.com.doordu.entity.RoleEntity;
import mb_redis_test_s.com.doordu.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/save")
	@ResponseBody
	public RoleVo save(@RequestBody RoleVo role) {
		String roleJson = JSON.toJSONString(role);
		logger.info(roleJson);
		RoleEntity entity = JSON.parseObject(roleJson, RoleEntity.class);
		roleService.save(entity);
		role.setRoleId(entity.getRoleId());
		return role;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<RoleVo> list() {
		List<RoleEntity> roleEntities = roleService.list();
		String rolesJson = JSON.toJSONString(roleEntities);
		List<RoleVo> roleVos = JSON.parseObject(rolesJson, new TypeReference<List<RoleVo>>(){});
		return roleVos;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public RoleVo update(@RequestBody RoleVo roleVo) {
		String roleJson = JSON.toJSONString(roleVo);
		RoleEntity role = JSON.parseObject(roleJson, RoleEntity.class);
		roleService.update(role);
		return roleVo;
	}
}

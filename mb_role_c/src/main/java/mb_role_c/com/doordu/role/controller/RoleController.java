package mb_role_c.com.doordu.role.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import mb_redis_role_d.com.doordu.entity.RoleEntity;
import mb_redis_role_s.com.doordu.service.RoleService;
import mb_role_c.com.doordu.role.vo.RoleVo;

@Controller
@RequestMapping("/role")
@Api(value = "交涉")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	
	@ApiOperation(value = "保存")
	@ApiParam
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RoleVo save(@RequestBody RoleVo role) {
		String roleJson = JSON.toJSONString(role);
		logger.info(roleJson);
		RoleEntity entity = JSON.parseObject(roleJson, RoleEntity.class);
		roleService.save(entity);
		role.setRoleId(entity.getRoleId());
		return role;
	}
	
	@ApiOperation(value = "列表")
	@ApiParam
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<RoleVo> list() {
		List<RoleEntity> roleEntities = roleService.list();
		String rolesJson = JSON.toJSONString(roleEntities);
		List<RoleVo> roleVos = JSON.parseObject(rolesJson, new TypeReference<List<RoleVo>>(){});
		return roleVos;
	}
	
	@ApiOperation(value = "更新")
	@ApiParam
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RoleVo update(@RequestBody RoleVo roleVo) {
		String roleJson = JSON.toJSONString(roleVo);
		RoleEntity role = JSON.parseObject(roleJson, RoleEntity.class);
		roleService.update(role);
		return roleVo;
	}
}

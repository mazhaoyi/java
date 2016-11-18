package mb_role_c.com.doordu.role.vo;

import mb_redis_role_d.com.doordu.entity.ext.RolePros;

public class RoleVo {
	private Integer roleId;
	private String name;
	private RolePros pros;
	public RolePros getPros() {
		return pros;
	}
	public void setPros(RolePros pros) {
		this.pros = pros;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

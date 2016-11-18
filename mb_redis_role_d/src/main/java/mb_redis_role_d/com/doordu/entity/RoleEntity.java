package mb_redis_role_d.com.doordu.entity;

import java.io.Serializable;

import mb_redis_role_d.com.doordu.entity.ext.RolePros;

public class RoleEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6210886733674239518L;
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

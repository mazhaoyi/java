package mb_redis_test_d.com.doordu.entity.ext;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class RolePros {
	private Boolean isAdmin;
	private Integer roleAge;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date roleDate;
	private String roleSay;
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getRoleAge() {
		return roleAge;
	}
	public void setRoleAge(Integer roleAge) {
		this.roleAge = roleAge;
	}
	public Date getRoleDate() {
		return roleDate;
	}
	public void setRoleDate(Date roleDate) {
		this.roleDate = roleDate;
	}
	public String getRoleSay() {
		return roleSay;
	}
	public void setRoleSay(String roleSay) {
		this.roleSay = roleSay;
	}
}

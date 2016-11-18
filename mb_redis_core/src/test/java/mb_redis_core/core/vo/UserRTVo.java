package mb_redis_core.core.vo;

import java.util.List;

public class UserRTVo {
	private Integer userId;
	private String name;
	private Boolean isAdmin;
	private List<UserRTExtVo> exts;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public List<UserRTExtVo> getExts() {
		return exts;
	}
	public void setExts(List<UserRTExtVo> exts) {
		this.exts = exts;
	}
}

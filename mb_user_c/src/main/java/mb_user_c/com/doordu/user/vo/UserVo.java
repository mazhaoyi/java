package mb_user_c.com.doordu.user.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import mb_redis_user_d.com.doordu.entity.ext.UserProperties;

public class UserVo {
	private String name;
	private Integer age;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JSONField(format = "yyyy-MM-dd")
	private Date birthday;
	private Boolean isValid;
	private UserProperties properties;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public UserProperties getProperties() {
		return properties;
	}
	public void setProperties(UserProperties properties) {
		this.properties = properties;
	}
}

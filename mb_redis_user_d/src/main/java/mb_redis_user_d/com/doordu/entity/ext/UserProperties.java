package mb_redis_user_d.com.doordu.entity.ext;

public class UserProperties {
	private Integer sex;
	private String say;
	private String like;
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getSay() {
		return say;
	}
	public void setSay(String say) {
		this.say = say;
	}
}

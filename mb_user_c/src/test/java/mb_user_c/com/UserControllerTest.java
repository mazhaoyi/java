package mb_user_c.com;

import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import mb_user_c.com.doordu.user.vo.UserVo;

public class UserControllerTest {
	@Test
	public void save() throws ParseException {
		UserVo uv = new UserVo();
		uv.setAge(20);
		uv.setBirthday(DateUtils.parseDate("1987-04-01", "yyyy-MM-dd"));
		uv.setCreateTime(Calendar.getInstance().getTime());
		uv.setIsValid(true);
		uv.setName("王五");
		System.out.println(JSON.toJSONString(uv));
	}
}

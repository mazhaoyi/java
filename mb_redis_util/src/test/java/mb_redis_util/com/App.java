package mb_redis_util.com;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class App {
	public static final String[] PATTERNS = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm:ss"};
	
	public static void main(String[] args) throws ParseException {
		Date date = DateFormat.getDateInstance().parse("2010-10-20");
		Date dateTime = DateFormat.getDateTimeInstance().parse("2010-10-20 20:30:25");
		Date time = DateFormat.getTimeInstance().parse("20:30:25");
		
		System.out.println(date);
		System.out.println(dateTime);
		System.out.println(time);
		
		System.out.println(DateFormat.getDateInstance().format(date));
		System.out.println(DateFormat.getDateTimeInstance().format(dateTime));
		System.out.println(DateFormat.getTimeInstance().format(time));
		
		Date date1 = DateUtils.parseDate("2010-10-20", PATTERNS);
		Date date2 = DateUtils.parseDate("2010-10-20 20:30:25", PATTERNS);
		Date date3 = DateUtils.parseDate("20:30:25", PATTERNS);
		
		System.out.println(date1);
		System.out.println(date2);
		System.out.println(date3);
		
		System.out.println(DateFormatUtils.format(date1, "yyyy-MM-dd"));
		System.out.println(DateFormatUtils.format(date2, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateFormatUtils.format(date3, "HH:mm:ss"));
	}
}

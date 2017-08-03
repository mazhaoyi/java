package mb_redis_util.com.doordu.util;

import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.TypeReference;
/**
 * json文件读取
 * @author admin
 *
 */
public class JsonFileUtils {
	/**
	 * 从json文件读取json字符串
	 * @param name
	 * @return
	 * @throws IOException 
	 */
	public static String getJson(String name) throws Exception {
		String json = null;
		InputStreamReader fr = null;
//		FileReader fr = null;
		JSONReader jr = null;
		try {
			// jar包中的文件读取不到，要用流读取
//			String path = JsonFileUtils.class.getClassLoader().getResource(name).getPath();
			
			fr = new InputStreamReader(JsonFileUtils.class.getClassLoader().getResourceAsStream(name));
//			fr = new FileReader(path);
			jr = new JSONReader(fr);
			
			json = jr.readString();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (jr != null) {
				jr.close();
			}
		}
		return json;
	}
	/**
	 * 从json文件读取json对象
	 * @param name
	 * @param type
	 * @return
	 * @throws IOException 
	 */
	public static <T> T getObject(String name, TypeReference<T> type) throws Exception {
		String json = getJson(name);
		T t = JSON.parseObject(json, type);
		return t;
	}
}
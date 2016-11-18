package mb_redis_util.com.doordu.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	 */
	public static String getJson(String name) {
		String json = null;
		FileReader fr = null;
		JSONReader jr = null;
		try {
			String path = JsonFileUtils.class.getClassLoader().getResource(name).getPath();
			
			fr = new FileReader(path);
			jr = new JSONReader(fr);
			
			json = jr.readString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}
	/**
	 * 从json文件读取json对象
	 * @param name
	 * @param type
	 * @return
	 */
	public static <T> T getObject(String name, TypeReference<T> type) {
		String json = getJson(name);
		T t = JSON.parseObject(json, type);
		return t;
	}
}
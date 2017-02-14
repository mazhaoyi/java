package mb_redis_core.com.doordu.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.alibaba.fastjson.JSON;
import java.util.Map;

/**
 * mybatis 自定义JSON类型，对应数据库JSON类型
 * 
 * JSON类型以{}开头和结尾
 * 
 * {
 *    "name": "zhangsan",
 *    "images": [
 *        {
 *            "imageId": "1",
 *            "path": "/a/b.png"
 *        },
 *        {
 *            "imageId": "2",
 *            "path": "/c/d.png"
 *        }
 *    ]
 * }
 * 
 * @param <T>
 */
@MappedTypes({Map.class})
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {
	private Class<T> clazz;
	
	public JsonTypeHandler(Class<T> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Type argument cannot be null !");
		}
		this.clazz = clazz;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, JSON.toJSONString(parameter));
	}

	@Override
	public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String str = rs.getString(columnName);
		return JSON.parseObject(str, clazz);
	}

	@Override
	public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return JSON.parseObject(rs.getString(columnIndex), clazz);
	}

	@Override
	public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return JSON.parseObject(cs.getString(columnIndex), clazz);
	}

}

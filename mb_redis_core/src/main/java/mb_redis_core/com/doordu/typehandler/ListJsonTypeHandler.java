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
 * mybatis 自定义List JSON类型，对应数据库JSON类型
 * 
 * JSON是数组形式以[]开头和结尾
 * <ul>
 * <li>
 * [
 *    {
 *        "name": "zhangsan",
 *        "age": 20
 *    },
 *    {
 *        "name": "lisi",
 *        "age": 22
 *   
 * ]
 * </li>
 * </ul>
 * @param <L> List
 * @param <E> List中元素对应的JAVA类型
 */
@MappedTypes({Map.class})
public class ListJsonTypeHandler<L, E> extends BaseTypeHandler<L> {
	private Class<E> clazz;
	
	public ListJsonTypeHandler(Class<E> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Type argument cannot be null !");
		}
		this.clazz = clazz;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, L parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, JSON.toJSONString(parameter));
	}

	@SuppressWarnings("unchecked")
	@Override
	public L getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String str = rs.getString(columnName);
		return (L) JSON.parseArray(str, clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public L getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return (L) JSON.parseArray(rs.getString(columnIndex), clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public L getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return (L) JSON.parseArray(cs.getString(columnIndex), clazz);
	}

}

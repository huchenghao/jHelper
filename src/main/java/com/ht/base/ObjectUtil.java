package com.ht.base;

import java.util.Arrays;

import com.google.common.base.Objects;
import com.ht.annotation.Nullable;
/**
 * @ClassName: ObjectUtil
 * @Description: 1.Object打印优化，主要解决数组的打印<br/>2.多个对象的HashCode串联
 * @author: huchenghao
 * @date: 2018年7月11日 下午3:03:22
 */
public class ObjectUtil {
	private static final String NULL = "null";
	
	/**
	 * @Title: equals
	 * @Description: 判断两个对象是否相等
	 * @param a
	 * @param b
	 * @return
	 * @author huchenghao
	 */
	public static boolean equals(@Nullable Object a, @Nullable Object b) {
		return Objects.equal(a, b);
	}
	
	/**
	 * 
	 * @Title: hashCode
	 * @Description: 多个对象的HashCode串联, 组成新的HashCode
	 * @param objects
	 * @return
	 * @author huchenghao
	 */
	public static int hashCode(Object... objects) {
		return Arrays.hashCode(objects);
	}

	/**
	 * @Title: toPrettyString
	 * @Description: 对象的toString()<br/>
	 * 				 value为数组时会，返回拼接字符串。类似：[a, b, c]
	 * 				 value为某个实体对象时，返回对象地址。类似：com.ht.test.User@15db9742
	 * 				 value为某个集合时(集合为泛型String)，返回拼接字符串。类似：{a,b}
	 * 				 value为某个集合时(集合为对象User)，返回拼接字符串。类似：  {com.ht.test.User@15db9742,com.ht.test.User@6d06d69c}
	 * @param value
	 * @return
	 * @author huchenghao
	 */
	@SuppressWarnings("rawtypes")
	public static String toPrettyString(Object value) {
		if (value == null) {
			return NULL;
		}
		Class<?> type = value.getClass();
		if (type.isArray()) {
			Class componentType = type.getComponentType();
			if (componentType.isPrimitive()) {
				return primitiveArrayToString(value, componentType);
			} else {
				return objectArrayToString(value);
			}
		} else if (value instanceof Iterable) {
			// 因为Collection的处理也是默认调用元素的toString(),
			// 为了处理元素是数组的情况，同样需要重载
			return collectionToString(value);
		}
		return value.toString();
	}
	
	private static String primitiveArrayToString(Object value, Class componentType) {
		StringBuilder sb = new StringBuilder();

		if (componentType == int.class) {
			sb.append(Arrays.toString((int[]) value));
		} else if (componentType == long.class) {
			sb.append(Arrays.toString((long[]) value));
		} else if (componentType == double.class) {
			sb.append(Arrays.toString((double[]) value));
		} else if (componentType == float.class) {
			sb.append(Arrays.toString((float[]) value));
		} else if (componentType == boolean.class) {
			sb.append(Arrays.toString((boolean[]) value));
		} else if (componentType == short.class) {
			sb.append(Arrays.toString((short[]) value));
		} else if (componentType == byte.class) {
			sb.append(Arrays.toString((byte[]) value));
		} else if (componentType == char.class) {
			sb.append(Arrays.toString((char[]) value));
		} else {
			throw new IllegalArgumentException("unsupport array type");
		}

		return sb.toString();
	}

	private static String objectArrayToString(Object value) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');

		Object[] array = (Object[]) value;
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(toPrettyString(array[i]));
		}
		sb.append(']');
		return sb.toString();
	}

	private static String collectionToString(Object value) {
		Iterable iterable = (Iterable) value;
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		int i = 0;
		for (Object o : iterable) {
			if (i > 0) {
				sb.append(',');
			}
			sb.append(toPrettyString(o));
			i++;
		}
		sb.append('}');
		return sb.toString();
	}
}

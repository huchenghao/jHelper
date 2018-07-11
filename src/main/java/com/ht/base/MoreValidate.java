package com.ht.base;

import com.ht.annotation.Nullable;

/**
 * 
 * @ClassName: MoreValidate
 * @Description: 校验为正数 || 校验为非负数
 * @author: huchenghao
 * @date: 2018年7月11日 下午2:56:53
 */
public class MoreValidate {
	
	/**
	 * 
	 * @Title: positive
	 * @Description: 校验为正数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static int positive(@Nullable String role, int x) {
		if (x <= 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
		}
		return x;
	}

	/**
	 * 
	 * @Title: positive
	 * @Description: 校验为正数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static Integer positive(@Nullable String role, Integer x) {
		if (x.intValue() <= 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
		}
		return x;
	}

	/**
	 * 
	 * @Title: positive
	 * @Description: 验为正数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static long positive(@Nullable String role, long x) {
		if (x <= 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
		}
		return x;
	}

	/**
	 * 
	 * @Title: positive
	 * @Description: 校验为正数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static Long positive(@Nullable String role, Long x) {
		if (x.longValue() <= 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
		}
		return x;
	}
	/**
	 * 
	 * @Title: positive
	 * @Description: 校验为正数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static double positive(@Nullable String role, double x) {
		if (!(x > 0)) { // not x < 0, to work with NaN.
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}

	/**
	 * 
	 * @Title: nonNegative
	 * @Description: 校验为非负数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static int nonNegative(@Nullable String role, int x) {
		if (x < 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}

	/**
	 * 
	 * @Title: nonNegative
	 * @Description: 校验为非负数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static Integer nonNegative(@Nullable String role, Integer x) {
		if (x.intValue() < 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}

	/**
	 * 
	 * @Title: nonNegative
	 * @Description: 校验为非负数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static long nonNegative(@Nullable String role, long x) {
		if (x < 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}

	/**
	 * 
	 * @Title: nonNegative
	 * @Description: 校验为非负数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static Long nonNegative(@Nullable String role, Long x) {
		if (x.longValue() < 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}
	/**
	 * 
	 * @Title: nonNegative
	 * @Description: 校验为非负数则返回该数字，否则抛出异常.
	 * @param role
	 * @param x
	 * @return
	 * @author huchenghao
	 */
	public static double nonNegative(@Nullable String role, double x) {
		if (!(x >= 0)) { // not x < 0, to work with NaN.
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}
}

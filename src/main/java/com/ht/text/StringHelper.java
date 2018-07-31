package com.ht.text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: StringHelper
 * @Description: 字符串工具类，提供一些字符串相关的便捷方法
 * @author: huchenghao
 * @date: 2017年9月21日 上午11:03:06
 */
public class StringHelper {
	
	private StringHelper(){
		throw new AssertionError();
	}
	/**
     * is null or its length is 0 or it is made by space
     * <pre>
     * isBlank(null) = true;
     * isBlank(&quot;&quot;) = true;
     * isBlank(&quot;  &quot;) = true;
     * isBlank(&quot;a&quot;) = false;
     * isBlank(&quot;a &quot;) = false;
     * isBlank(&quot; a&quot;) = false;
     * isBlank(&quot;a b&quot;) = false;
     * </pre>
     *
     * @param str str
     * @return if string is null or its size is 0 or it is made by space, return
     * true, else return false.
     */
	public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }
	
	 /**
     * is null or its length is 0
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     *
     * @param str str
     * @return if string is null or its size is 0, return true, else return
     * false.
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }
    
    /**
     * get length of CharSequence
     * <pre>
     * length(null) = 0;
     * length(\"\") = 0;
     * length(\"abc\") = 3;
     * </pre>
     *
     * @param str str
     * @return if str is null or empty, return 0, else return {@link
     * CharSequence#length()}.
     */
    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }
    
    /**
     * null Object to empty string
     * <pre>
     * nullStrToEmpty(null) = &quot;&quot;;
     * nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
     * nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
     * </pre>
     *
     * @param str str
     * @return String
     */
    public static String nullStrToEmpty(Object str) {
        return (str == null?"":(str instanceof String ? (String) str : str.toString()));
    }
    
    /**
     * 
     * @Title: capitalizeFirstLetter
     * @Description: Convert the first letter to upper case
     * @param str
     * @return
     * @return: String
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c))
                ? str
                : new StringBuilder(str.length()).append(
                Character.toUpperCase(c))
                .append(str.substring(1))
                .toString();
    }
    /**
     * 
     * @Title: utf8Encode
     * @Description: 返回一个utf8的字符串
     * @param str
     * @return
     * @return: String
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }
    /**
     * 
     * @Title: isAllDigital
     * @Description: 是否是纯数字
     * @param str
     * @return
     * @return: boolean
     */
    public static boolean isAllDigital(String str) {
    	char[] chars = str.toCharArray();
        boolean result = true;
        for (int w = 0; w < chars.length; w++) {
            if (!Character.isDigit(chars[w])) {
                result = false;
                break;
            }
        }
        return result;
    }
   
	
}

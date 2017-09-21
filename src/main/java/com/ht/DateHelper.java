package com.ht;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: DateHelper
 * @Description: 日期工具类
 * @author: huchenghao
 * @date: 2017年9月20日 下午6:57:23
 */
public class DateHelper {
	private static final String YEAR_MONTH_DAY_TEMPLATE = "yyyy-MM-dd";
	private static final String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_TEMPLATE = "yyyy-MM-dd hh:mm:ss";
	private static final String YEARMONTHDAYHOURMINUTESECONDTEMPLATE = "yyyyMMddhhmmss";
	
	private static SimpleDateFormat df_ymd=new SimpleDateFormat(YEAR_MONTH_DAY_TEMPLATE);
	private static SimpleDateFormat df_ymdhms=new SimpleDateFormat(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_TEMPLATE);
	private static SimpleDateFormat df_ymdhms_no=new SimpleDateFormat(YEARMONTHDAYHOURMINUTESECONDTEMPLATE);
	
	/**
	 * 
	 * @Title: longTimeForYMDHMSStr
	 * @Description: long类型的时间戳转换为yyyy-MM-dd hh:mm:ss格式的字符串
	 * @param currentTimeMillis
	 * @return
	 * @return: String
	 */
	public static String longTimeForYMDHMSStr(Long currentTimeMillis){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentTimeMillis);
		return  df_ymdhms.format(calendar.getTime());
	}
	/**
	 * 
	 * @Title: formatDateToYMDHMSStr
	 * @Description: Date格式日期转换为yyyy-MM-dd hh:mm:ss格式的字符串
	 * @param date
	 * @return
	 * @return: String
	 */
	public static String formatDateForYMDHMSStr(Date date){
		return df_ymdhms.format(date);
	}
	/**
	 * 
	 * @Title: formatDateToYMDStr
	 * @Description: Date格式日期转换为yyyy-MM-dd格式的字符串
	 * @param date
	 * @return
	 * @return: String
	 */
	public static String formatDateToYMDStr(Date date){
		return df_ymd.format(date);
	}
	/**
	 * 
	 * @Title: formatDate
	 * @Description: Date格式日期转换为format格式的字符串
	 * @param date
	 * @param format
	 * @return
	 * @return: String
	 */
	public static String formatDate(Date date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	/**
	 * 
	 * @Title: getTodayStr
	 * @Description: 获取String格式的，今天的日期
	 * @return
	 * @return: String
	 */
	public static String getTodayStr(){
		return df_ymd.format(new Date());
	}
	/**
	 * 
	 * @Title: getNowTime
	 * @Description: 获取String格式的，今天的yyyy-MM-dd HH:mm:ss
	 * @return
	 * @return: String
	 */
	public static String getNowTime(){
		return df_ymdhms.format(new Date());
	}
	/**
	 * 
	 * @Title: getNowTimeToNo
	 * @Description:  获取String格式的，现在的yyyyMMddHHmmss
	 * @return
	 * @return: String
	 */
	public static String getNowTimeToNo(){
		return df_ymdhms_no.format(new Date());
	}
	
	/**
	 * 
	 * @Title: comparDate
	 * @Description: 比较两个Date的大小，1>2:1;1<2:0;1=null || 2== null:-1
	 * @param date1
	 * @param date2
	 * @return
	 * @return: int
	 */
	public static int comparDate(Date date1,Date date2){
		if(null == date1 || null == date2){
			return -1;
		}else if(date1.getTime() > date2.getTime()){
			return 1;
		}else{
			return 0;
		}
	}
	/**
	 * 
	 * @Title: parseYMDStrForDate
	 * @Description: yyyy-MM-dd 格式字符串转换为Date格式
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 * @return: Date
	 */
	public static Date parseYMDStrForDate(String dateStr) throws ParseException{
		return df_ymd.parse(dateStr);
	}
	/**
	 * 
	 * @Title: parseYMDHMSStrForDate
	 * @Description: yyyy-MM-dd hh:mm:ss格式字符串转换为Date格式
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 * @return: Date
	 */
	public static Date parseYMDHMSStrForDate(String dateStr) throws ParseException{
		return df_ymdhms.parse(dateStr);
	}
	/**
	 * 
	 * @Title: parseDate
	 * @Description: dateStr日期格式的字符串，转换为format格式的Date
	 * @param dateStr
	 * @param format
	 * @return
	 * @throws ParseException
	 * @return: Date
	 */
	public static Date parseDate(String dateStr,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStr);
	}
	/**
	 * 
	 * @Title: getAmOrPm
	 * @Description: 获取指定的日期是上午还是下午
	 * @param date
	 * @return
	 * @return: Integer
	 */
	public static Integer getAmOrPm(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
	    @SuppressWarnings("static-access")
		Integer int_date = cal.get(cal.AM_PM);
		return int_date;
	}
	/**
	 * 
	 * @Title: addTimeByDays
	 * @Description: 当前时间+指定天数
	 * @param days
	 * @return
	 * @return: String
	 */
	public static String addTimeByDays(int days){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		return df_ymd.format(cal.getTime());
	}
	/**
	 * 
	 * @Title: addTimeByDays
	 * @Description: 指定日期字符串+天数
	 * @param dateStr
	 * @param days
	 * @return
	 * @throws ParseException
	 * @return: String
	 */
	public static String addTimeByDays(String dateStr,int days) throws ParseException{
		Calendar cal=Calendar.getInstance();
		cal.setTime(parseYMDStrForDate(dateStr));
		cal.add(Calendar.DAY_OF_MONTH, days);
		return df_ymd.format(cal.getTime());
	}
	/**
	 * 
	 * @Title: addTimeByHours
	 * @Description: 指定时间+hours
	 * @param date
	 * @param hours
	 * @return
	 * @return: Date
	 */
	public static Date addTimeByHours(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}
	/**
	 * 
	 * @Title: addTimeByMinutes
	 * @Description: 指定日期+minutes
	 * @param date
	 * @param minutes
	 * @return
	 * @return: Date
	 */
	public static Date addTimeByMinutes(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
	/**
	 * 
	 * @Title: addTimeByMonths
	 * @Description:指定日期+months
	 * @param date
	 * @param months
	 * @return
	 * @return: Date
	 */
	public static Date addTimeByMonths(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	/**
	 * 
	 * @Title: addTimeByYears
	 * @Description: 指定时间+years
	 * @param date
	 * @param years
	 * @return
	 * @return: Date
	 */
	public static Date addTimeByYears(Date date, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}
	/**
	 * 
	 * @Title: subtractTimeByMonth
	 * @Description: 指定日期-months
	 * @param date
	 * @param months
	 * @return
	 * @return: Date
	 */
	public static Date subtractTimeByMonth(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - months);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @Title: subtractTimeByDays
	 * @Description: 指定时间-days
	 * @param date
	 * @param days
	 * @return
	 * @return: Date
	 */
	public static Date subtractTimeByDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - days);
		return calendar.getTime();
	}
	/**
	 * 
	 * @Title: subtractTimeByHours
	 * @Description: 指定时间-hours
	 * @param date
	 * @param hours
	 * @return
	 * @return: Date
	 */
	public static Date subtractTimeByHours(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - hours);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @Title: getBetweenDate
	 * @Description: 获取两个日期之间日期 包括两个日期
	 * @param from
	 * @param to
	 * @return
	 * @throws ParseException
	 * @return: List<String>
	 */
	public static List<String> getBetweenDate(String from,String to) throws ParseException{
		 List<String> rtnlist=new ArrayList<String>();
		 Date  fdate=parseYMDStrForDate(from);
		 Date  edate=parseYMDStrForDate(to);
		 //如果开始日期比结束日期大 互换日期
        if(edate.getTime()<fdate.getTime()){
       	 Date date=fdate;
       	 fdate=edate;
       	 edate=date;
        }		 
		 Calendar ca1=Calendar.getInstance();
		 ca1.setTime(fdate);
		 Calendar ca2=Calendar.getInstance();
		 ca2.setTime(edate);
		 
		 long count=(ca2.getTimeInMillis()-ca1.getTimeInMillis())/86400000;
		 rtnlist.add(formatDateToYMDStr(ca1.getTime()));
		 for(long i=0;i<count;i++){
			   ca1.add(Calendar.DAY_OF_YEAR, 1);
			   rtnlist.add(formatDateToYMDStr(ca1.getTime()));
		 }
		 return rtnlist;
	}
	/**
	 * 
	 * @Title: subDaysBetweenTwoDate
	 * @Description: 两个日期相差的天数
	 * @param longdate
	 * @param shortdate
	 * @return
	 * @return: int
	 */
	public static int subDaysBetweenTwoDate(Date longdate,Date shortdate){
		if(null == longdate || null == shortdate){
			return -1;
		}else{
			return (int)(longdate.getTime() - shortdate.getTime())/86400000; 
		}
	}
	/**
	 * 
	 * @Title: subMinsBetweenTwoDate
	 * @Description: 两个日期相差的分钟数
	 * @param longdate
	 * @param shortdate
	 * @return
	 * @return: int
	 */
	public static int subMinsBetweenTwoDate(Date longdate,Date shortdate){
		if(null == longdate || null == shortdate){
			return -1;
		}else{
			return (int)(longdate.getTime() - shortdate.getTime())/60000; 
		}
	}
	
	/**
	 * 
	 * @Title: getWeek
	 * @Description: 获取是周几
	 * @param weekday
	 * @return
	 * @return: String
	 */
	public static String getWeek(int weekday){
		String dayNames[] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		return dayNames[weekday];
	}
	/**
	 * 
	 * @Title: todayWeek
	 * @Description: 今天是周几
	 * @return
	 * @return: String
	 */
	public static String todayWeek(){
		String weekString = "";
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date); 
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		weekString = getWeek(dayOfWeek - 1);
		return weekString;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(todayWeek());
		
	}
	
}

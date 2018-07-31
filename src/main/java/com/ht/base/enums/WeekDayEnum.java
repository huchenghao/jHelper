package com.ht.base.enums;

public enum WeekDayEnum {
	Mon("Monday",1), 
	Tue("Tuesday",2), 
	Wed("Wednesday",3), 
	Thu("Thursday",4), 
	Fri( "Friday",5), 
	Sat("Saturday",6), 
	Sun("Sunday",7),
	UNKNOW("Unknow",99);
	
	private final String day;
	private final int index;
	  
	private WeekDayEnum(String day,int index) {
	     this.day = day;
	     this.index = index;
	}
	
	@Override
	public String toString() {
		return this.index+"_"+this.day;
	}
	/**
	 * 
	 * @Title: getWeekName
	 * @Description: 周N就传N 如想要获取周一,则传1
	 * @param index
	 * @return
	 * @author huchenghao
	 */
	public static String getWeekName(int index){
		for(WeekDayEnum day:WeekDayEnum.values()){
			if(day.getIndex() == index){
				return day.day;
			}
		}
		return null;
	}
	
	

	public String getDay() {
		return day;
	}

	public int getIndex() {
		return index;
	}
	
	
	
}

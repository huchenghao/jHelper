package com.ht.base.enums;

public enum WeekDay {
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
	  
	private WeekDay(String day,int index) {
	     this.day = day;
	     this.index = index;
	}
	
	@Override
	public String toString() {
		return this.index+"_"+this.day;
	}
	public static String getWeekName(int index){
		for(WeekDay day:WeekDay.values()){
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

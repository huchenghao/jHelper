package com.ht.test;

import java.util.ArrayDeque;
import java.util.Comparator;

import com.ht.collection.QueueUtil;

public class Test {

	public static void main(String[] args) {
		ArrayDeque<String> ss = QueueUtil.newArrayDeque(16);
		ss.addFirst("1");
		ss.
		System.out.println(ss.size());
		
		
	}
}

class MyComparator implements Comparator<User>{
	public int compare(User o1, User o2) {
		Integer age1 = o1.getAge();
		Integer age2 = o2.getAge();
		return age1.compareTo(age2);
	}
}

class User{
	public String name;
	public String sex;
	public int age;
	
	
	public User(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}

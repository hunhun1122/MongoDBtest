package com.gwm.monodb.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mytest")
public class Person {
	@Id
	private String id;
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
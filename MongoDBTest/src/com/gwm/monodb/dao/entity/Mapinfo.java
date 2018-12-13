package com.gwm.monodb.dao.entity;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="Mapinfo")
public class Mapinfo {
@Id
private String id;
private double[] loc;
private String name;
private String category;

public void setId(String id) {
	this.id = id;
}
public String getId() {
	return id;
}
@PersistenceConstructor
Mapinfo(String name, double[] loc) {
  super();
  this.name = name;
  this.loc = loc;
}

public Mapinfo(String name, double x, double y) {
  super();
  this.name = name;
  this.loc = new double[] { x, y };
}


public void setLoc(double[] loc) {
	this.loc = loc;
}
public double[] getLoc() {
	return loc;
}
public void setName(String name) {
	this.name = name;
}
public String getName() {
	return name;
}
public void setCategory(String category) {
	this.category = category;
}
public String getCategory() {
	return category;
}
@Override
public String toString() {
  return "Mapinfo [id=" + id + ", name=" + name + ", loc="
      + Arrays.toString(loc) + "]";
}
}

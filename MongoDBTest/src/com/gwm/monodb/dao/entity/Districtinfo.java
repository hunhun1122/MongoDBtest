package com.gwm.monodb.dao.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 县区
 * @author gw00089267
 *
 */

@Document(collection="district")
public class Districtinfo {
@Id
private String id;
private int adcode;
private double[] center;
private String level;
private String name;
private String longname;


public String getLongname() {
	return longname;
}
public void setLongname(String longname) {
	this.longname = longname;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getAdcode() {
	return adcode;
}
public void setAdcode(int adcode) {
	this.adcode = adcode;
}
public double[] getCenter() {
	return center;
}
public void setCenter(double[] center) {
	this.center = center;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}



}

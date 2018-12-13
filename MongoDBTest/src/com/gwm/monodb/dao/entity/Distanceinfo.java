package com.gwm.monodb.dao.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 县区之间的距离
 * @author gw00089267
 *
 */

@Document(collection="distance")
public class Distanceinfo {
@Id
private String id;
private int oneadcode;
private String onelevel;
private String onename;
private String onelongname;

private int twoadcode;
private String twolevel;
private String twoname;
private String twolongname;
private double distance;



public double getDistance() {
	return distance;
}
public void setDistance(double distance) {
	this.distance = distance;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getOneadcode() {
	return oneadcode;
}
public void setOneadcode(int oneadcode) {
	this.oneadcode = oneadcode;
}
public String getOnelevel() {
	return onelevel;
}
public void setOnelevel(String onelevel) {
	this.onelevel = onelevel;
}
public String getOnename() {
	return onename;
}
public void setOnename(String onename) {
	this.onename = onename;
}
public String getOnelongname() {
	return onelongname;
}
public void setOnelongname(String onelongname) {
	this.onelongname = onelongname;
}
public int getTwoadcode() {
	return twoadcode;
}
public void setTwoadcode(int twoadcode) {
	this.twoadcode = twoadcode;
}
public String getTwolevel() {
	return twolevel;
}
public void setTwolevel(String twolevel) {
	this.twolevel = twolevel;
}
public String getTwoname() {
	return twoname;
}
public void setTwoname(String twoname) {
	this.twoname = twoname;
}
public String getTwolongname() {
	return twolongname;
}
public void setTwolongname(String twolongname) {
	this.twolongname = twolongname;
}




}

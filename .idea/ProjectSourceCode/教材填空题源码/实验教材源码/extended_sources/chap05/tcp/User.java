/*******************************************************************
 * * Copyright (C) 电子科技大学中山学院计算机学院
 * * All rights reserved.
 * *
 * 文件名:labcore  Message.java
 * 描述:TODO
 *
 * @Author:Hevean
 * @Date:2015-5-13
 * @Version:1.0.0
 * *
 * *******************************************************************/
package net.tcp;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	String username;//用户名
	String password;//密码
	int age;//年龄
	float weight;//体重
	float height;//身高
	Date birthday;//出生时间
	String cellphone;//手机号码
	String email;//email地址
	public User(String username, String password, int age, float weight,
			float height, Date birthday, String cellphone, String email) {
		this.username = username;
		this.password = password;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.birthday = birthday;
		this.cellphone = cellphone;
		this.email = email;
	}
	@Override
	//重载toString()方法
	public String toString() {
		return "\nUser [username=" + username + ", \npassword=" + password
				+ ", \nage=" + age + ", \nweight=" + weight + ", \nheight=" + height
				+ ", \nbirthday=" + birthday + ", \ncellphone=" + cellphone
				+ ", \nemail=" + email + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

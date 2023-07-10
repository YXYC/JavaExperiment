/*******************************************************************
 * * Copyright (C) ���ӿƼ���ѧ��ɽѧԺ�����ѧԺ
 * * All rights reserved.
 * *
 * �ļ���:labcore  Message.java
 * ����:TODO
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
	String username;//�û���
	String password;//����
	int age;//����
	float weight;//����
	float height;//���
	Date birthday;//����ʱ��
	String cellphone;//�ֻ�����
	String email;//email��ַ
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
	//����toString()����
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

package com.jdog.project.projectment;

import java.util.Date;

import com.jdog.project.menuauth.Menuauth;

public class Projectment {

	private int id;
	private String title;
	private int available;
	private String brief;
	private Date addtime;
	private Menuauth menuauth;
	private String contact; //联系人邮箱
	
	private int num;
	
	
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Menuauth getMenuauth() {
		return menuauth;
	}
	public void setMenuauth(Menuauth menuauth) {
		this.menuauth = menuauth;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	
}

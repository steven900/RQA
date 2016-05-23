package com.jdog.project.pricereport;

import com.jdog.project.price.Price;

public class Pricereport {
	
	private int id;
	private int projectid;
	private int priceid;
	private int available;
	
	private Price price;
	
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectid() {
		return projectid;
	}
	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	public int getPriceid() {
		return priceid;
	}
	public void setPriceid(int priceid) {
		this.priceid = priceid;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	
}

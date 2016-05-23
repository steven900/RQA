package com.jdog.project.bugmanage;

public class BugImg {

	
	private int id;
	private String pic;
	private int bugid;
	private int available=1;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getBugid() {
		return bugid;
	}
	public void setBugid(int bugid) {
		this.bugid = bugid;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	
}

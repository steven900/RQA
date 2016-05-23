package com.jdog.project.bugmanage;

import java.util.Date;

import com.jdog.project.user.User;

/**
 * 问题管理
 * @author freed
 *
 */
public class Bug {
	
	private int id;
	private String title; //标题
	private String brief; //描述
	private String reportid;// 上报用户id
	private int projectid;//项目id

	private int state; //bug 状态  0 未修复  1 开发人员修复
	private int repaired; //测试人员取人修复
	private int sentTo; // 分配用户id
	private String sentToUser;//分配给谁
	private String pic;//
	
	private int available=1; //物理删除
	private Date addtime;
	
	private User user;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getSentToUser() {
		return sentToUser;
	}
	public void setSentToUser(String sentToUser) {
		this.sentToUser = sentToUser;
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
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public int getProjectid() {
		return projectid;
	}
	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getRepaired() {
		return repaired;
	}
	public void setRepaired(int repaired) {
		this.repaired = repaired;
	}
	public int getSentTo() {
		return sentTo;
	}
	public void setSentTo(int sentTo) {
		this.sentTo = sentTo;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	
	
}

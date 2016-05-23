package wx.weixin.bean;

import java.util.ArrayList;
import java.util.List;

public class Weixinfuwumenu { 
  private Integer available;
  private Integer dorder;
  private Integer id;
  private String name;
  private Integer pid;
  private String url;
  private String wtype;
  private List<Weixinfuwumenu> list  =  new ArrayList<Weixinfuwumenu>();
  
  
  public List<Weixinfuwumenu> getList() {
	return list;
}
public void setList(List<Weixinfuwumenu> list) {
	this.list = list;
}
public Integer getAvailable() {
 		return available;
  } 
  public void setAvailable(Integer available){
	this.available = available;
  } 
  public Integer getDorder() {
 		return dorder;
  } 
  public void setDorder(Integer dorder){
	this.dorder = dorder;
  } 
  public Integer getId() {
 		return id;
  } 
  public void setId(Integer id){
	this.id = id;
  } 
  public String getName() {
 		return name;
  } 
  public void setName(String name){
	this.name = name;
  } 
  public Integer getPid() {
 		return pid;
  } 
  public void setPid(Integer pid){
	this.pid = pid;
  } 
  public String getUrl() {
 		return url;
  } 
  public void setUrl(String url){
	this.url = url;
  } 
  public String getWtype() {
 		return wtype;
  } 
  public void setWtype(String wtype){
	this.wtype = wtype;
  } 
} 

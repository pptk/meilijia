package com.modle;

public class ActivitylistModle {
	private String aid;   //活动编号
	private String starttext;  //活动开始时间
	private String stoptext; //活动截止时间
	private String content;    //活动内容
	private String image;  //活动图片
	
	public ActivitylistModle() {
		super();
	}
	
	public ActivitylistModle(String aid,String starttext,String stoptext,String image){
		super();
		this.aid=aid;
		this.starttext=starttext;
		this.stoptext=stoptext;
		this.image=image;
	}
	
	
	
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getStarttext() {
		return starttext;
	}
	public void setStarttext(String starttext) {
		this.starttext = starttext;
	}
	public String getStoptext() {
		return stoptext;
	}
	public void setStoptext(String stoptext) {
		this.stoptext = stoptext;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}

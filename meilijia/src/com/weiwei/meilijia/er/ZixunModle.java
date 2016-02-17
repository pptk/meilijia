package com.weiwei.meilijia.er;

public class ZixunModle {
    
	private String cid;   //咨询问题编号
	private String name;  //咨询人用户名
	private String content; //咨询的问题内容
	private String time="";     //咨询的发布时间
	private String image1;//图片1
	private String image2;//图片2
	private String image3;//图片3
	//private String count;//图片张数
//	private String state;//回复状态
	public ZixunModle() {
		super();
	}
	public ZixunModle(String cid, String name, String content, String time,
			String image1, String image2, String image3) {
		super();
		this.cid = cid;
		this.name = name;
		this.content = content;
		this.time = time;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
//		this.state = state;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
//	public String getState() {
//		return state;
//	}
//	public void setState(String state) {
//		this.state = state;
//	}
	
}

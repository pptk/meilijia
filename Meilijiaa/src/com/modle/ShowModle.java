package com.modle;

public class ShowModle {
    
	private String sid;
	private String name;
	private String image1;
	private String image2;
	private String image3;
	private String content;
	private String time="";
	
	public ShowModle()
	{
		super();
	}
	
	public ShowModle(String sid, String name, String content, String image1, String image2, String image3, String time) {
		super();
		this.sid=sid;
		this.name = name;
		this.content=content;
		this.image1 =image1;
		this.image2 =image2;
		this.image3 =image3;
		this.time = time;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}

package com.example.meifushow;

public class ShowInfo {
	private String username;
	private String one;
	private String two;
	private String three;
	private String xinde;
	private String createTime;
	
	public ShowInfo(String username, String one, String two, String three,
			String xinde, String createTime) {
		super();
		this.username = username;
		this.one = one;
		this.two = two;
		this.three = three;
		this.xinde = xinde;
		this.createTime = createTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public String getTwo() {
		return two;
	}
	public void setTwo(String two) {
		this.two = two;
	}
	public String getThree() {
		return three;
	}
	public void setThree(String three) {
		this.three = three;
	}
	public String getXinde() {
		return xinde;
	}
	public void setXinde(String xinde) {
		this.xinde = xinde;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}

package com.modle;

public class Userinfo {
	private String name;
	private String qianming;//个性签名
	private String nianling;//年龄段
	private String diqu;//地区
	
	public Userinfo() {
		super();
	}
	
	public Userinfo(String qianming, String nianling, String diqu) {
		super();
		this.setQianming(qianming);
		this.setNianling(nianling);
		this.setDiqu(diqu);
	}

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public String getNianling() {
		return nianling;
	}

	public void setNianling(String nianling) {
		this.nianling = nianling;
	}

	public String getDiqu() {
		return diqu;
	}

	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package com.weiwei.meilijia.er;

public class UserModle {
	private String name;   //用户名
	private String qianming;//个性签名
	private String nianling;//年龄段
	private String diqu;//地区
	
	public UserModle() {
		super();
	}
	
	public UserModle(String qianming, String nianling, String diqu) {
		super();
		this.qianming = qianming;
		this.nianling = nianling;
		this.diqu = diqu;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
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
}

package com.weiwei.meilijia.er;

public class UserModle {
	private String name;   //�û���
	private String qianming;//����ǩ��
	private String nianling;//�����
	private String diqu;//����
	
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

package com.modle;



public class User {

	private int uid;    //用户编号
	private String name;   //用户名
	private String pwd;    //用户密码
	private String qianming;//个性签名
	private String nianling;//年龄段
	private String diqu;//地区
	
	public User() {
		super();
	}
	
	public User(String name, String qianming, String nianling, String diqu) {
		super();
		this.name = name;
		this.qianming = qianming;
		this.nianling = nianling;
		this.diqu = diqu;
	}

	public User(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	
	public User(String qianming, String nianling, String diqu) {
		super();
		this.qianming = qianming;
		this.nianling = nianling;
		this.diqu = diqu;
	}
	
	public int getUid()
	{
		return uid;
	}
	public void setUid(int uid)
	{
		this.uid=uid;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String pwd)
	{
		this.pwd=pwd;
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
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result
				+ ((name== null) ? 0 : name.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User  other = (User) obj;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String toString() {
		return "User [name=" + name + ", pwd=" + pwd + "]";
	} 	
	
}

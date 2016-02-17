package com.modle;

public class Expertinfo {
      
	private String eid;   //专家编号
	private String name;  //专家姓名
	private String pwd;   //专家登录密码
	private String hospital;//专家所属医院
//	private String photoPath;  //专家头像路径
	
	public Expertinfo() {
		super();
	}
	
	public Expertinfo(String eid,String name,String pwd,String hospital){
		super();
		this.eid=eid;
		this.name = name;
		this.pwd = pwd;
		this.hospital=hospital;
	//	this.imagepath=imagepath;
	}
	
	public String getEid()
	{
		return eid;
	}
	public void setEid(String eid)
	{
		this.eid=eid;
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

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
/*	public String getPhotoPath()
	{
		return photoPath;
	}
	public void setPhotoPath(String photoPath)
	{
		this.photoPath=photoPath;
	}
	
*/	
	
	
}

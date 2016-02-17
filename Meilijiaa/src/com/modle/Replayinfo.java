package com.modle;

public class Replayinfo {
    
	private String rid;    //咨询问题编号
	private String name;  //专家姓名
	private String content;  //专家回复内容
	private String time="";    //回复时间
	
	public Replayinfo() {
		super();
	}
	
	public Replayinfo(String rid,String name,String content,String time){
		super();
		this.rid=rid;
		this.name = name;
		this.content = content;
	//	this.imagepath=imagepath;
		this.time=time;
	}
	
	public String getRid()
	{
		return rid;
	}
	public void setRid(String rid)
	{
		this.rid=rid;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content=content;
	}
	
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time=time;
	}
	
	
	
	
}

package com.modle;

public class Replayinfo {
    
	private String rid;    //��ѯ������
	private String name;  //ר������
	private String content;  //ר�һظ�����
	private String time="";    //�ظ�ʱ��
	
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

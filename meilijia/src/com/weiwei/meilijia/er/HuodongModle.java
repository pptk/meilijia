package com.weiwei.meilijia.er;

public class HuodongModle {
   
	private String aid;   //����
	private String starttext;  //���ʼʱ��
	private String stoptext; //���ֹʱ��
	private String content;    //�����
    private String image;     //�ͼƬ
	public HuodongModle() {
		super();
	}
	
	public HuodongModle(String starttext,String stoptext,String content,String image){
		super();
	//	this.aid=aid;
		this.starttext = starttext;
		this.stoptext=stoptext;
		this.content = content;
		this.image=image;
	}
	
	public String getAid()
	{
		return aid;
	}
	public void setAid(String aid)
	{
		this.aid=aid;
	}
	
	public String getStarttext()
	{
		return starttext;
	}
	public void setStarttext(String starttext)
	{
		this.starttext=starttext;
	} 
	
	public String getStoptext() {
		return stoptext;
	}
	public void setStoptext(String stoptext) {
		this.stoptext = stoptext;
	}
	
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content=content;
	} 
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}

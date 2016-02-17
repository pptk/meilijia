package com.modle;

public class QiaomenModle {
	private String qid; //窍门专题编号
	private String title;//窍门的专题标题
	private String content;//窍门的概要内容
	private String ctitle;//窍门的方法标题
	private String fangfa;//窍门的方法内容
	private String image;//窍门图片
	
	public QiaomenModle()
	{
		super();
	}
	
	public QiaomenModle(String pid,String title,String image)
	{
		super();
		this.setQid(pid);
		this.title=title;
		this.image=image;
	}
	
	public QiaomenModle(String title,String content,String ctitle,String fangfa,
			String image)
	{
		super();
		this.title=title;
		this.content=content;
		this.ctitle=ctitle;
		this.fangfa=fangfa;
		this.image=image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCtitle() {
		return ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	public String getFangfa() {
		return fangfa;
	}

	public void setFangfa(String fangfa) {
		this.fangfa = fangfa;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}
}


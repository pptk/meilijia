package com.modle;

public class QiaomenModle {
	private String qid; //����ר����
	private String title;//���ŵ�ר�����
	private String content;//���ŵĸ�Ҫ����
	private String ctitle;//���ŵķ�������
	private String fangfa;//���ŵķ�������
	private String image;//����ͼƬ
	
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


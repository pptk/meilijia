package com.weiwei.meilijia.er;

public class ZixunModle {
    
	private String cid;   //��ѯ������
	private String name;  //��ѯ���û���
	private String content; //��ѯ����������
	private String time="";     //��ѯ�ķ���ʱ��
	private String image1;//ͼƬ1
	private String image2;//ͼƬ2
	private String image3;//ͼƬ3
	//private String count;//ͼƬ����
//	private String state;//�ظ�״̬
	public ZixunModle() {
		super();
	}
	public ZixunModle(String cid, String name, String content, String time,
			String image1, String image2, String image3) {
		super();
		this.cid = cid;
		this.name = name;
		this.content = content;
		this.time = time;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
//		this.state = state;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
//	public String getState() {
//		return state;
//	}
//	public void setState(String state) {
//		this.state = state;
//	}
	
}

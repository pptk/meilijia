package com.modle;

public class XingzuoModle {
  private String x_id;
  private String x_image;
  private String x_title;
  private String x_content;
  private String x_pic;
  private String x_pict;
  private String x_goodnam;
  private String x_goodname;
  private String x_imag;
  private String goodjie;
  private String goodjieshao;
  public XingzuoModle(){
	  super();
  }
  public XingzuoModle(String x_id,String x_image,String x_title){
	  super();
	  this.setX_id(x_id);
	  this.x_image=x_image;
	  this.x_title=x_title;
	  
  }
  
public XingzuoModle(String x_title,String x_content,String x_goodnam,
		String x_goodname, String goodjie, String goodjieshao, String x_imag,String x_pic, String x_pict) {
	super();
	
	this.x_title = x_title;
	this.x_content = x_content;
	this.x_goodnam = x_goodnam;
	this.x_goodname = x_goodname;
	this.goodjie = goodjie;
	this.goodjieshao = goodjieshao;
	this.x_imag = x_imag;
	this.x_pic = x_pic;
	this.x_pict = x_pict;
	
}

public String getX_image() {
	return x_image;
}
public void setX_image(String x_image) {
	this.x_image = x_image;
}
public String getX_title() {
	return x_title;
}
public void setX_title(String x_title) {
	this.x_title = x_title;
}
public String getX_content() {
	return x_content;
}
public void setX_content(String x_content) {
	this.x_content = x_content;
}
public String getX_pic() {
	return x_pic;
}
public void setX_pic(String x_pic) {
	this.x_pic = x_pic;
}
public String getX_pict() {
	return x_pict;
}
public void setX_pict(String x_pict) {
	this.x_pict = x_pict;
}
public String getX_goodnam() {
	return x_goodnam;
}
public void setX_goodnam(String x_goodnam) {
	this.x_goodnam = x_goodnam;
}
public String getX_goodname() {
	return x_goodname;
}
public void setX_goodname(String x_goodname) {
	this.x_goodname = x_goodname;
}
public String getX_imag() {
	return x_imag;
}
public void setX_imag(String x_imag) {
	this.x_imag = x_imag;
}
public String getGoodjie() {
	return goodjie;
}
public void setGoodjie(String goodjie) {
	this.goodjie = goodjie;
}
public String getGoodjieshao() {
	return goodjieshao;
}
public void setGoodjieshao(String goodjieshao) {
	this.goodjieshao = goodjieshao;
}
public String getX_id() {
	return x_id;
}
public void setX_id(String x_id) {
	this.x_id = x_id;
}
}

package com.weiwei.meilijia.yi;

public class ShopInfo {
	private int id ;//编号。
	private String goodsName;//商品名称
	private String goodsFrom;//厂家
	private String introduce;//介绍
	private String creaTime;//上架时间
	private String nowPirce;//现在价格。
	private int number;//库存
	private byte[] picture;//图片。
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsFrom() {
		return goodsFrom;
	}
	public void setGoodsFrom(String goodsFrom) {
		this.goodsFrom = goodsFrom;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCreaTime() {
		return creaTime;
	}
	public void setCreaTime(String creaTime) {
		this.creaTime = creaTime;
	}
	public String getNowPirce() {
		return nowPirce;
	}
	public void setNowPirce(String nowPirce) {
		this.nowPirce = nowPirce;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public ShopInfo(int id, String goodsName, String goodsFrom,
			String introduce, String creaTime, String nowPirce, int number,
			byte[] picture) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.goodsFrom = goodsFrom;
		this.introduce = introduce;
		this.creaTime = creaTime;
		this.nowPirce = nowPirce;
		this.number = number;
		this.picture = picture;
	}
	
}

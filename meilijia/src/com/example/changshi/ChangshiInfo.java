package com.example.changshi;

public class ChangshiInfo {
    private String id;
	private String name;
    private String introduce;
    private String picture;
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getPicture() {
		return picture;
	}
	public void setPriture(String picture) {
		this.picture = picture;
	}
	public ChangshiInfo( String id,String name, String introduce, String picture) {
		super();
		this.id=id;
		this.name = name;
		this.introduce = introduce;
		this.picture = picture;
	}
}

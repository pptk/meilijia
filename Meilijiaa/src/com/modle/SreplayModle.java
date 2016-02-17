package com.modle;

public class SreplayModle {
     
	private String srid;    //咨询问题编号
	private String srname;  //专家姓名
	private String srcontent;  //专家回复内容
	private String srtime="";    //回复时间
	
	public SreplayModle() {
		super();
	}
	
	public SreplayModle(String srid,String srname,String srcontent,String srtime){
		super();
		this.setSrid(srid);
		this.setSrname(srname);
		this.setSrcontent(srcontent);
		this.setSrtime(srtime);
	}

	public String getSrid() {
		return srid;
	}

	public void setSrid(String srid) {
		this.srid = srid;
	}

	public String getSrname() {
		return srname;
	}

	public void setSrname(String srname) {
		this.srname = srname;
	}

	public String getSrcontent() {
		return srcontent;
	}

	public void setSrcontent(String srcontent) {
		this.srcontent = srcontent;
	}

	public String getSrtime() {
		return srtime;
	}

	public void setSrtime(String srtime) {
		this.srtime = srtime;
	}
	
	
}

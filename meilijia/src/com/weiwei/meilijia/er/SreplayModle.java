package com.weiwei.meilijia.er;

public class SreplayModle {
      
	private String srid;
	private String srname;
	private String srcontent;
	private String srtime;
	
	public SreplayModle(String srname,String srtime,String srcontent)
	{
		super();
		this.setSrname(srname);
		this.setSrtime(srtime);
		this.setSrcontent(srcontent);
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


	public String getSrid() {
		return srid;
	}


	public void setSrid(String srid) {
		this.srid = srid;
	}
	
}

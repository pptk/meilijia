package com.modle;

public class SreplayModle {
     
	private String srid;    //��ѯ������
	private String srname;  //ר������
	private String srcontent;  //ר�һظ�����
	private String srtime="";    //�ظ�ʱ��
	
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

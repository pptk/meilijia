<%@ page language="java" contentType="text/html; charset=utf-8" import="com.conn.*,com.dao.*,com.daogo.*,
com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*"%>
<%
 
      List<ActivitylistModle> list = new ArrayList<ActivitylistModle>();
  
	  ActivityDao dao = new ActivityDaogo();
	  ActivitylistModle ac=new ActivitylistModle();

	  list = dao.getHuodongList();
 	  String s = JSON.toJSONString(list);
	  out.print(s);  
 
%>
<%@ page language="java" contentType="text/html; charset=utf-8" import="com.conn.*,com.dao.*,com.daogo.*,
com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*"%>
<%
	  String param=request.getParameter("httpclient");
      if("get".equals(param))
      { 
      String aid=request.getParameter("a_id");
      List<HuodonginfoModle> list = new ArrayList<HuodonginfoModle>();
  
	  ActivityDao dao = new ActivityDaogo();
	  HuodonginfoModle ac=new HuodonginfoModle();
	  ac.setAid(aid);
	  list = dao.getHuodonginfoList(aid);
 	  String s = JSON.toJSONString(list);
	  out.print(s);  
      }else
      {
        out.print("请求不成功");
      }
%>
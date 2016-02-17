<%@page language="java" contentType="text/html; charset=utf-8" import="com.conn.*,com.dao.*,com.daogo.*,
com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*" %> 
<% 
      String param=request.getParameter("httpclient"); 
      if("get".equals(param)) 
      {  
      List<Zixuninfo> list = new ArrayList<Zixuninfo>(); 
	  ZixuninfoDao dao = new ZixuninfoDaogo(); 
	  list = dao.getConsultList();
 	  String s = JSON.toJSONString(list); 
	  out.print(s);   
      }else 
     { 
        out.print("请求不成功"); 
      }
 
%>
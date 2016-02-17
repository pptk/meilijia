<%@ page language="java" contentType="text/html; charset=utf-8"
    import="com.conn.*,com.dao.*,com.daogo.*,
      com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*"
    pageEncoding="utf-8"%>
<%

     String param=request.getParameter("httpclient");
     if("get".equals(param))
     { 
      String srid=request.getParameter("s_id");
      
      List<SreplayModle> list = new ArrayList<SreplayModle>();
  
	  SreplayDao dao = new SreplayDaogo();
	  SreplayModle sre=new SreplayModle();
	  sre.setSrid(srid);
	  list = dao.getReplay(srid);
 	  String s = JSON.toJSONString(list);
	  out.print(s);  
     }else
     {
        out.print("请求不成功");
     }

 %>

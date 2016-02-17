<%@ page language="java" import="com.conn.*,com.dao.*,com.daogo.*,
com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*" pageEncoding="utf-8"%>
<%
   String param=request.getParameter("httpclient");
   if("get".equals(param))
   {
     String name=request.getParameter("username");
     List<Userinfo> list=new ArrayList<Userinfo>();
     
     UserinfoDao dao=new UserinfoDaogo();
     Userinfo user=new Userinfo();
     user.setName(name);
     list=dao.getZiliaoList(name);
     String s=JSON.toJSONString(list);
     out.print(s); 
   }else{
       out.print("请求不成功");
   }

%>

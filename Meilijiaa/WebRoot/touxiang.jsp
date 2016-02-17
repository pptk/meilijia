<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*,com.conn.*,
com.dao.*,com.daogo.*,com.tools.*,com.modle.*,java.text.*,java.io.*"
    pageEncoding="utf-8"%>
<%
   String param=request.getParameter("httpclient");
   if("get".equals(param))
   {
     String username=request.getParameter("username");
     UserinfoDao dao=new UserinfoDaogo();
     String uri=dao.find_touxiang(username);
     if(uri!=null)
     {
        out.print(uri);
     }else{
        out.print("");
     }
   
   
   }



 %>

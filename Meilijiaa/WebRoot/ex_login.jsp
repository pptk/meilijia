<%@ page language="java" contentType="text/html; charset=utf-8" import="com.conn.*,com.dao.*,com.modle.*,com.daogo.*" 
    pageEncoding="utf-8"%>
<%
   request.setCharacterEncoding("utf-8");
   response.setCharacterEncoding("utf-8");
   String eid=request.getParameter("eid");
   String pwd=request.getParameter("pwd");
   ExpertinfoDao dao=new ExpertinfoDaogo();
   String ex=dao.login_deal(eid, pwd);
   if(ex.equals("yes"))
   {
     out.print("登陆成功");
   }else
   {
     out.print("登录失败");
   }
%>
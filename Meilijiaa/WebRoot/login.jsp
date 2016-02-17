<%@ page language="java" import="com.conn.*,com.dao.*,com.modle.*,com.daogo.*" contentType="text/html; charset=utf-8"%>
<%
 
   String username=request.getParameter("username");
   String pwd=request.getParameter("pwd");
//   String username="momo";
 //  String pwd="123";
   UserinfoDao dao=new UserinfoDaogo();
   String user=dao.login_deal(username, pwd);
   if(user.equals("yes"))
   {
     out.print(username);
    
   }else
   {
     out.print("登录失败");
   }

%>


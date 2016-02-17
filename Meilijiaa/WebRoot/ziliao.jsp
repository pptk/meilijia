<%@ page language="java" contentType="text/html; charset=utf-8"
   import="java.util.*,com.conn.*,com.dao.*,com.daogo.*,com.tools.*,com.modle.*,java.text.*,java.io.*"
    pageEncoding="utf-8"%>
<%
   request.setCharacterEncoding("utf-8");
   response.setCharacterEncoding("utf-8");
   String param=request.getParameter("httpclient");

   if("send".equals(param))
   {
    String username=request.getParameter("username");
    String qianming=request.getParameter("qianming");
    String nianling=request.getParameter("nianling");
    String diqu=request.getParameter("diqu");
    UserinfoDao dao=new UserinfoDaogo();
    User user=new User();
    
    user.setName(username);
    user.setQianming(qianming);
    user.setNianling(nianling);
    user.setDiqu(diqu);
    
    dao.modify_Ziliao(user);
    String tishi=dao.ziliao_deal(username, qianming, nianling, diqu);
    if(tishi.equals("yes"))
    {
      out.print("修改成功！！");
    }else{
      out.print("no");
    }
  }  
    
    
%>

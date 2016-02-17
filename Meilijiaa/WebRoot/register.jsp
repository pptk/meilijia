<%@ page language="java" import="com.conn.*,com.modle.*,com.dao.*,com.daogo.*" contentType="text/html;charset=utf-8"%>
<%
   request.setCharacterEncoding("utf-8");
   response.setCharacterEncoding("utf-8");
   String param=request.getParameter("param");
   if("post".equals(param))
   {
      String username=request.getParameter("username");
      String pwd=request.getParameter("pwd");
      UserinfoDao dao=new UserinfoDaogo();
      User user=new User();
      
      user.setName(username);
      user.setPwd(pwd);
      dao.add_user(user);
      String flag = dao.login_deal(username, pwd);
      if("yes".equals(flag)){
    	    out.print("注册成功");
    	}
	}else{
        out.print("注册失败");
    	
 }
%>


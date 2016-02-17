<%@ page language="java" contentType="text/html; charset=utf-8"
    import="com.conn.*,com.modle.*,com.dao.*,com.daogo.*"
    pageEncoding="utf-8"%>
<%
     String param=request.getParameter("param");
    if("post".equals(param))
    {
      String eid=request.getParameter("eid");
      String password=request.getParameter("pwd");

      ExpertinfoDao dao=new ExpertinfoDaogo();
      Expertinfo ex=new Expertinfo();
      
      ex.setEid(eid);
      ex.setPwd(password);
      dao.modify_pwd(ex);
      String flag=dao.login_deal(eid, password);
      if("yes".equals(flag))
      {
        out.print("修改成功");
      }else
      {
        out.print("修改失败");
      }  
    }


 %>

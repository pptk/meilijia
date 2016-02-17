<%@ page language="java" import="com.conn.*,com.dao.*,com.daogo.*,
com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*" pageEncoding="utf-8"%>
<%
     //  String param=request.getParameter("httpclient");
      String param="get";
      if("get".equals(param))
      { 
   //   String qid=request.getParameter("x_id");
      String qid="1";
      List<XingzuoModle> list = new ArrayList<XingzuoModle>();
  
	  XingzuoDao dao = new XingzuoDaogo();
	  XingzuoModle mm=new XingzuoModle();
	  mm.setX_id(qid);
	  list = dao.getXingzuoinfoList(qid);
 	  String s = JSON.toJSONString(list);
	  out.print(s);  
      }else
      {
        out.print("请求不成功");
      }
%>

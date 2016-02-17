<%@ page language="java" import="com.conn.*,com.dao.*,com.daogo.*,
com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*" pageEncoding="utf-8"%>
<%    
     String param=request.getParameter("httpclient");
    //  String param=1;
      if("get".equals(param))
      { 
      String qid=request.getParameter("q_id");
      List<QiaomenModle> list = new ArrayList<QiaomenModle>();
  
	  QiaomenDao dao = new QiaomenDaogo();
	  QiaomenModle qm=new QiaomenModle();
	  qm.setQid(qid);
	  list = dao.getQiaomeninfoList(qid);
 	  String s = JSON.toJSONString(list);
	  out.print(s);  
      }else
      {
        out.print("请求不成功");
      }
%>


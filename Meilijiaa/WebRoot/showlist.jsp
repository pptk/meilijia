<%@ page language="java" contentType="text/html; charset=utf-8" import="com.conn.*,com.dao.*,com.daogo.*,
com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*"%>
<%

      List<ShowModle> list = new ArrayList<ShowModle>(); 
	  ShowDao dao = new ShowDaogo(); 
	  list = dao.getShowList();
 	  String s = JSON.toJSONString(list); 
	  out.print(s); 

 %>

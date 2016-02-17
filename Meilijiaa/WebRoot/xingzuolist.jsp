<%@ page language="java" import="com.conn.*,com.dao.*,com.daogo.*,
com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*" pageEncoding="utf-8"%>
<%
    
    List<XingzuoModle> list=new ArrayList<XingzuoModle>();
    
    XingzuoDao dao=new XingzuoDaogo();
    XingzuoModle qm=new XingzuoModle();
    
    list=dao.getXingzuoList();
    String s=JSON.toJSONString(list);
    out.print(s);
%>



<%@ page language="java" import="com.conn.*,com.dao.*,com.daogo.*,
com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*" pageEncoding="utf-8"%>
<%
    
    List<QiaomenModle> list=new ArrayList<QiaomenModle>();
    
    QiaomenDao dao=new QiaomenDaogo();
    QiaomenModle qm=new QiaomenModle();
    
    list=dao.getQiaomenList();
    String s=JSON.toJSONString(list);
    out.print(s);
%>


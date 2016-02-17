<%@ page language="java" contentType="text/html; charset=utf-8"
    import="com.conn.*,com.dao.*,com.daogo.*,
      com.modle.*,java.util.*,com.tools.*,com.alibaba.fastjson.*"
    pageEncoding="utf-8"%>
<%

      String param=request.getParameter("httpclient");
      if("get".equals(param))
      { 
      String rid=request.getParameter("c_id");
      List<Replayinfo> list = new ArrayList<Replayinfo>();
  
	  ReplayinfoDao dao = new ReplayinfoDaogo();
	  Replayinfo replay=new Replayinfo();
	  replay.setRid(rid);
	  list = dao.getReplay(rid);
 	  String s = JSON.toJSONString(list);
	  out.print(s);  
      }else
      {
        out.print("请求不成功");
      }

 %>

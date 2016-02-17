<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.util.*,com.conn.*,com.dao.*,com.daogo.*,com.tools.*,com.modle.*,java.text.*,java.io.*"
    pageEncoding="utf-8"%>
<%
     request.setCharacterEncoding("utf-8");
     response.setCharacterEncoding("utf-8");
     String param=request.getParameter("httpclient");
     if("send".equals(param))
     {
     String srid=request.getParameter("s_id");
     String name=request.getParameter("username");
     String content=request.getParameter("miaosu");
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	 String time=df.format(new Date());
	 
     SreplayDao dao=new SreplayDaogo();
     SreplayModle sre=new SreplayModle();

     //获取专家的姓名
     sre.setSrid(srid);
     sre.setSrname(name);
     sre.setSrcontent(content);
     sre.setSrtime(time);
     dao.add_Replay(sre);

     String flag=dao.replay_deal(name, content, time);
     if(flag.equals("yes"))
     {
      out.print("发表成功");
     }else
     {
      out.print("发表失败");
     }
       
   }


%>
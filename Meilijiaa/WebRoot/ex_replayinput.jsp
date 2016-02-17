<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.util.*,com.conn.*,com.dao.*,com.daogo.*,com.tools.*,com.modle.*,java.text.*,java.io.*"
    pageEncoding="utf-8"%>
<%
     request.setCharacterEncoding("utf-8");
     response.setCharacterEncoding("utf-8");
     String param=request.getParameter("httpclient");
     if("send".equals(param))
     {
     String eid=request.getParameter("e_id");
     String cid=request.getParameter("c_id");
     String content=request.getParameter("miaosu");
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	 String time=df.format(new Date());
     ExpertinfoDao dao=new ExpertinfoDaogo();
     ZixuninfoDao con=new ZixuninfoDaogo();
     ReplayinfoDao re=new ReplayinfoDaogo();
     Zixuninfo zixun=new Zixuninfo();
     Replayinfo reinfo=new Replayinfo();
     //获取专家的姓名
     String name=dao.find_name(eid);
     reinfo.setRid(cid);
     reinfo.setName(name);
     reinfo.setContent(content);
     reinfo.setTime(time);
     re.add_Replay(reinfo);
   //  zixun.setState(state);
     zixun.setCid(cid);
   //  con.modify_state(zixun);
     String flag=re.replay_deal(name, content, time);
     if(flag.equals("yes"))
     {
      out.print("发表成功");
     }else
     {
      out.print("发表失败");
     }
       
   }


%>
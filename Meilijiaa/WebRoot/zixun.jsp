<%@ page language="java"
	import="java.util.*,com.conn.*,com.dao.*,com.daogo.*,com.tools.*,com.modle.*,java.text.*,java.io.*"
	pageEncoding="utf-8"%>
<%
   request.setCharacterEncoding("utf-8");
   response.setCharacterEncoding("utf-8");
   String param=request.getParameter("httpclient");
   if("send".equals(param))
    {
    String count = request.getParameter("count");//先获取传输的图片张数。
     String name=request.getParameter("username");
     String content=request.getParameter("miaosu");
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	  String time=df.format(new Date());
	
	  ZixuninfoDao dao=new ZixuninfoDaogo();
	  Zixuninfo zixun=new Zixuninfo();
	  
	  zixun.setName(name);
	  zixun.setContent(content);
      zixun.setTime(time);
   //   zixun.setState(state);
      
      //判断用户选择的图片张数，选择对应的方法执行
      if(Integer.parseInt(count) == 1){//如果一张图。
          String image1= request.getParameter("name0");
         // String image1="http://192.168.56.1:8088/Meilijiaaimage/head.jpg";
		 zixun.setImagepath1(image1);
		}
		if(Integer.parseInt(count) == 2){//如果两张图。
		 String image1= request.getParameter("name0");
		 zixun.setImagepath1(image1);
		 String image2= request.getParameter("name1");
		 zixun.setImagepath2(image2);
		}
		if(Integer.parseInt(count) == 3){//如果三张图。
		String image1= request.getParameter("name0");
		 zixun.setImagepath1(image1);
		 String image2= request.getParameter("name1");
		 zixun.setImagepath2(image2);
		 String image3= request.getParameter("name2");
		 zixun.setImagepath3(image3);
		}
    //    zixun.setCount(count);
        dao.add_consult(zixun);
        String tishi=dao.consult_deal(name, content,time);
	   if(tishi.equals("yes")){
   	    out.print("发表成功");
    	}
	}else{
        out.print("no");  
  
  }
%>



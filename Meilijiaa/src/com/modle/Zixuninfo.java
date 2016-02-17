package com.modle;


public class Zixuninfo {

		
		private String cid;   //咨询问题编号
		private String name;  //咨询人用户名
		private String content; //咨询的问题内容
		private String time="";     //咨询的发布时间
		private String state;   //是否有回复,0为没有回复
		private String imagepath1="";  //图片路径1
		private String imagepath2="";  //图片路径2
		private String imagepath3="";  //图片路径3
		private String count; //图片张数
		public Zixuninfo() {
			super();
		}
		
		public Zixuninfo(String cid,String name,String content,String time,String imagepath1,
				String imagepath2,String imagepath3,String count,String state){
			super();
			this.cid=cid;
			this.name = name;
			this.content = content;
			this.time=time;
			this.imagepath1=imagepath1;
			this.imagepath2=imagepath2;
			this.imagepath3=imagepath3;
			this.count=count;
			this.state=state;
		}
		
		public String getCid()
		{
			return cid;
		}
		public void setCid(String cid)
		{
			this.cid=cid;
		}
		
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name=name;
		} 
		
		public String getContent()
		{
			return content;
		}
		public void setContent(String content)
		{
			this.content=content;
		} 
		
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getImagepath1()
		{
			return imagepath1;
		}
		public void setImagepath1(String imagepath1)
		{
			this.imagepath1=imagepath1;
		} 
		public String getImagepath2()
		{
			return imagepath2;
		}
		public void setImagepath2(String imagepath2)
		{
			this.imagepath2=imagepath2;
		} 
		public String getImagepath3()
		{
			return imagepath3;
		}
		public void setImagepath3(String imagepath3)
		{
			this.imagepath3=imagepath3;
		} 
		
		public String getCount()
		{
			return count;
		}
		public void setCount(String count)
		{
			this.count=count;
		} 
		
		public String toString() {
			return "Zixuninfo [name=" + name + ", content=" + content +",time="	+ time +",imagepath1="+ imagepath1 +",imagepath2="+ imagepath2 +",imagepath3="+imagepath3+
					",state="+ state +",count="+ count +"]";
		} 	
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name== null) ? 0 : name.hashCode());
			result = prime * result + ((content == null) ? 0 : content.hashCode());
			result = prime * result + ((time == null) ? 0 : time.hashCode());
			result = prime * result
					+ ((imagepath1 == null) ? 0 : imagepath1.hashCode());
			result = prime * result
					+ ((imagepath2 == null) ? 0 : imagepath2.hashCode());
			result = prime * result + ((state == null) ? 0 : state.hashCode());
			result = prime * result
					+ ((imagepath3 == null) ? 0 : imagepath3.hashCode());
			result = prime * result + ((count == null) ? 0 : count.hashCode());
			return result;
		}	
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Zixuninfo other = (Zixuninfo) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (content == null) {
				if (other.content != null)
					return false;
			} else if (!content.equals(other.content))
				return false;
			if (time == null) {
				if (other.time != null)
					return false;
			}
			return true;
		}
	}


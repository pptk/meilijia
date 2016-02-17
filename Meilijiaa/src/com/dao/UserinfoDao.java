package com.dao;

import java.io.Serializable;
import java.util.List;

import com.modle.User;
import com.modle.Userinfo;
import com.modle.Zixuninfo;




public interface UserinfoDao {

	Serializable add_user(User userinfo); 
	
	String login_deal(String username,String pwd);
	
	String find_touxiang(String username);
	
	Serializable modify_Ziliao(User userinfo);
	
	String ziliao_deal(String username,String qianming,String nianling,String diqu);
	
	List<Userinfo> getZiliaoList(String name);
}

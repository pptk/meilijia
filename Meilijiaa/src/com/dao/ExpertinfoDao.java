package com.dao;

import java.io.Serializable;

import com.modle.Expertinfo;



public interface ExpertinfoDao {
  
	String login_deal(String eid,String pwd);
	
    Serializable modify_pwd(Expertinfo expertinfo);
   
    String  find_name(String eid);
}

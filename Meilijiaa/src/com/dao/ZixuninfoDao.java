package com.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import com.modle.Zixuninfo;

public interface  ZixuninfoDao{

	Serializable add_consult(Zixuninfo zixun);
	
    String consult_deal(String name,String content,String time); //×ÉÑ¯ÑéÖ¤
    
    List<Zixuninfo> getZixunList(String name);
    
    List<Zixuninfo> getConsultList();
    
    Serializable modify_state(Zixuninfo zixuninfo);
}

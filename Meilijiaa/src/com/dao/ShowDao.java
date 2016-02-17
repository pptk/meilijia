package com.dao;

import java.io.Serializable;
import java.util.List;

import com.modle.ShowModle;
import com.modle.Zixuninfo;


public interface ShowDao {
   
	List<ShowModle> getShowList();
	
	Serializable add_show(ShowModle show);
	
	String show_deal(String name,String content,String time);
}

package com.dao;

import java.util.List;

import com.modle.ActivitylistModle;
import com.modle.HuodonginfoModle;
import com.modle.Zixuninfo;


public interface ActivityDao {
  
	List<HuodonginfoModle> getHuodonginfoList(String aid);
	
	List<ActivitylistModle> getHuodongList();
}

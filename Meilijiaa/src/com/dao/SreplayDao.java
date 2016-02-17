package com.dao;

import java.io.Serializable;
import java.util.List;


import com.modle.SreplayModle;

public interface SreplayDao {
    
    List<SreplayModle> getReplay(String srid);
	
    Serializable add_Replay(SreplayModle sre);
	
	String replay_deal(String name,String content,String time); //ªÿ∏¥—È÷§
}

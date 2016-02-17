package com.dao;

import java.io.Serializable;
import java.util.List;

import com.modle.Replayinfo;



public interface ReplayinfoDao {

	List<Replayinfo> getReplay(String cid);
	
    Serializable add_Replay(Replayinfo replay);
	
	String replay_deal(String name,String content,String time); //ªÿ∏¥—È÷§
}

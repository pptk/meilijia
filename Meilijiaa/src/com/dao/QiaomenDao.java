package com.dao;

import java.util.List;


import com.modle.QiaomenModle;

public interface QiaomenDao {
    List<QiaomenModle> getQiaomeninfoList(String qid);
	
	List<QiaomenModle> getQiaomenList();     
}

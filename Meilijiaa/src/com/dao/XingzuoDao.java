package com.dao;

import java.util.List;
import com.modle.XingzuoModle;

public  interface  XingzuoDao {
	 List<XingzuoModle> getXingzuoinfoList(String x_id);
	
	 List<XingzuoModle> getXingzuoList() ;
}

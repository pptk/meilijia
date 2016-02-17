package com.daogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.ActivityDao;
import com.modle.ActivitylistModle;
import com.modle.HuodonginfoModle;
import com.modle.Zixuninfo;


public class ActivityDaogo implements ActivityDao{

	// 建立连接数据库
		private Connection conn = null;
		private PreparedStatement pstm = null;
		private ResultSet rs = null;

	public List<HuodonginfoModle> getHuodonginfoList(String aid) {
		try {
			HuodonginfoModle frag = null;
			conn = ConnectionFactory.getConnection();
			List<HuodonginfoModle> list = new ArrayList<HuodonginfoModle>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT a_starttext,a_stoptext,a_content,a_image ");
			sql.append(" FROM huodong ");
			sql.append(" WHERE a_id=? ");
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1,aid);
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new HuodonginfoModle();
				//frag.setAid(rs.getString("a_id"));
				frag.setStarttext(rs.getString("a_starttext"));
				frag.setStoptext(rs.getString("a_stoptext"));
				frag.setContent(rs.getString("a_content"));
				frag.setImage(rs.getString("a_image"));
				list.add(frag);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.close(conn, pstm, rs);
		}
	}

	public List<ActivitylistModle> getHuodongList() {
		try {
			ActivitylistModle frag = null;
			conn = ConnectionFactory.getConnection();
			List<ActivitylistModle> list = new ArrayList<ActivitylistModle>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT a_id,a_starttext,a_stoptext,a_image ");
			sql.append(" FROM huodong ");
			sql.append(" ORDER BY a_id DESC");
			
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new ActivitylistModle();
				frag.setAid(rs.getString("a_id"));
				frag.setStarttext(rs.getString("a_starttext"));
				frag.setStoptext(rs.getString("a_stoptext"));
				frag.setImage(rs.getString("a_image"));
				list.add(frag);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.close(conn, pstm, rs);
		}
	}

}

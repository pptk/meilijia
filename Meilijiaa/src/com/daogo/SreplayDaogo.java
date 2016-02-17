package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.SreplayDao;
import com.modle.Replayinfo;
import com.modle.SreplayModle;

public class SreplayDaogo implements SreplayDao{
	
	//建立连接对象 
		private Connection conn=null;
		private PreparedStatement pstm = null;
		private ResultSet rs = null;

	public List<SreplayModle> getReplay(String srid) {
		try {
			
			SreplayModle frag = null;
			conn = ConnectionFactory.getConnection();
			List<SreplayModle> list = new ArrayList<SreplayModle>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT s_rid,s_rname,s_rcontent,s_rtime ");
			sql.append(" FROM s_replay ");
			sql.append(" WHERE s_rid=? ");
			
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1,srid);
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new SreplayModle();
				frag.setSrname(rs.getString("s_rname"));
				frag.setSrcontent(rs.getString("s_rcontent"));
				frag.setSrtime(rs.getString("s_rtime"));
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

	public Serializable add_Replay(SreplayModle sre) {
		try {
			conn = ConnectionFactory.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO s_replay(s_rid,s_rname,s_rcontent,s_rtime) ");
			sql.append(" VALUES(?,?,?,?) ");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, sre.getSrid());
			pstm.setString(2, sre.getSrname());
			pstm.setString(3, sre.getSrcontent());
			pstm.setString(4, sre.getSrtime());

			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();

			if (rs.next()) {
				return rs.getInt(1);
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.close(conn, pstm, rs);
		}
	}

	public String replay_deal(String name, String content, String time) {
		try {

			String flag = "no";
			conn = ConnectionFactory.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT s_rname,s_rcontent,s_rtime ");
			sql.append(" FROM s_replay ");
			sql.append(" WHERE s_rname =? ");
			sql.append(" AND s_rcontent= ?");
			sql.append(" AND s_rtime= ?");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, name);
			pstm.setString(2, content);
			pstm.setString(3, time);

			rs = pstm.executeQuery();
			if (rs.next()) {
				flag = "yes";
				return flag;
			} else {

				return flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.close(conn, pstm, rs);
		}
	}

}

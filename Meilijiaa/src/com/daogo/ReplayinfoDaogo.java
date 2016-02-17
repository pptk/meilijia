package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.ReplayinfoDao;
import com.modle.Replayinfo;


public class ReplayinfoDaogo implements ReplayinfoDao{

	//建立连接对象 
	private Connection conn=null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	public List<Replayinfo> getReplay(String cid) {
		try {
			Replayinfo frag = null;
			conn = ConnectionFactory.getConnection();
			List<Replayinfo> list = new ArrayList<Replayinfo>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT r_id,r_name,r_content,r_time ");
			sql.append(" FROM replay ");
			sql.append(" WHERE r_id=? ");
			
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1,cid);
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new Replayinfo();
				frag.setName(rs.getString("r_name"));
				frag.setContent(rs.getString("r_content"));
				frag.setTime(rs.getString("r_time"));
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


	public Serializable add_Replay(Replayinfo replay) {
		try {
			conn = ConnectionFactory.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO replay(r_id,r_name,r_content,r_time) ");
			sql.append(" VALUES(?,?,?,?) ");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, replay.getRid());
			pstm.setString(2, replay.getName());
			pstm.setString(3, replay.getContent());
			pstm.setString(4, replay.getTime());

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
			sql.append(" SELECT r_name,r_content,r_time ");
			sql.append(" FROM replay ");
			sql.append(" WHERE r_name =? ");
			sql.append(" AND r_content= ?");
			sql.append(" AND r_time= ?");

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

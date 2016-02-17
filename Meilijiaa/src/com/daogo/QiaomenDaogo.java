package com.daogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.QiaomenDao;
import com.modle.QiaomenModle;

public class QiaomenDaogo implements QiaomenDao{
    
	
	// 建立连接数据库
			private Connection conn = null;
			private PreparedStatement pstm = null;
			private ResultSet rs = null;

	public List<QiaomenModle> getQiaomeninfoList(String qid) {
		try{
			QiaomenModle frag = null;
			conn = ConnectionFactory.getConnection();
			List<QiaomenModle> list = new ArrayList<QiaomenModle>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT q_title,q_content,q_ctitle,q_fangfa,q_image ");
			sql.append(" FROM qiaomen ");
			sql.append(" WHERE q_id=? ");
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1,qid);
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new QiaomenModle();
				frag.setTitle(rs.getString("q_title"));
				frag.setContent(rs.getString("q_content"));
				frag.setCtitle(rs.getString("q_ctitle"));
				frag.setFangfa(rs.getString("q_fangfa"));
				frag.setImage(rs.getString("q_image"));
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

	public List<QiaomenModle> getQiaomenList() {
		try {
			QiaomenModle frag = null;
			conn = ConnectionFactory.getConnection();
			List<QiaomenModle> list = new ArrayList<QiaomenModle>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT q_id,q_title,q_image ");
			sql.append(" FROM qiaomen ");
			sql.append(" ORDER BY q_id ");
			
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new QiaomenModle();
				frag.setQid(rs.getString("q_id"));
				frag.setTitle(rs.getString("q_title"));
				frag.setImage(rs.getString("q_image"));
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

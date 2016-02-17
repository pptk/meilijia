package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.ShowDao;
import com.modle.ShowModle;
import com.modle.Zixuninfo;


public class ShowDaogo implements ShowDao{
    
	// 建立连接数据库
		private Connection conn = null;
		private PreparedStatement pstm = null;
		private ResultSet rs = null;
		
	public List<ShowModle> getShowList() {
	    
		try {
			ShowModle frag = null;
			conn = ConnectionFactory.getConnection();
			List<ShowModle> list = new ArrayList<ShowModle>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT s_id,s_name,s_content,s_time,s_image1,s_image2,s_image3 ");
			sql.append(" FROM show ");
			sql.append(" ORDER BY s_id DESC");
			
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag =new ShowModle();
				frag.setSid(rs.getString("s_id"));
				frag.setName(rs.getString("s_name"));
				frag.setContent(rs.getString("s_content"));
				frag.setTime(rs.getString("s_time"));
				frag.setImage1(rs.getString("s_image1"));
				frag.setImage2(rs.getString("s_image2"));
				frag.setImage3(rs.getString("s_image3"));
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

	public Serializable add_show(ShowModle show) {
		
		try {
			conn = ConnectionFactory.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO show(s_name,s_content,s_time,s_image1,s_image2,s_image3)");
			sql.append("VALUES(?,?,?,?,?,?)");

			pstm = conn.prepareStatement(sql.toString());

			pstm.setString(1, show.getName());
			pstm.setString(2, show.getContent());
			pstm.setString(3, show.getTime());
			pstm.setString(4, show.getImage1());
			pstm.setString(5, show.getImage2());
			pstm.setString(6, show.getImage3());
			
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

	public String show_deal(String name, String content, String time) {
		try {
			String flag = "no";
			conn = ConnectionFactory.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT s_name,s_content,s_time ");
			sql.append(" FROM show ");
			sql.append(" WHERE s_name =? ");
			sql.append(" AND s_content= ?");
			sql.append(" AND s_time= ?");
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

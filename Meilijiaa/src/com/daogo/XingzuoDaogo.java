package com.daogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.XingzuoDao;
import com.modle.XingzuoModle;

public class XingzuoDaogo implements XingzuoDao {
// 建立连接数据库
     private Connection conn = null;
	 private PreparedStatement pstm = null ;
	 private ResultSet rs = null;
	 public List<XingzuoModle> getXingzuoinfoList(String x_id){
			try{
				XingzuoModle frag = null;
				conn = ConnectionFactory.getConnection();
				List<XingzuoModle> list = new ArrayList<XingzuoModle>();
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT x_title,x_content,x_pic,x_pict,x_goodnam,x_goodname,goodjie,goodjieshao,x_imag ");
				sql.append(" FROM xingzuo ");
				sql.append(" WHERE x_id=? ");
				pstm = conn.prepareStatement(sql.toString());
				pstm.setString(1,x_id);
				rs = pstm.executeQuery();
				while (rs.next()) {
					frag = new XingzuoModle();
				    frag.setX_title(rs.getString("x_title"));
				    frag.setX_content(rs.getString("x_content"));
				    frag.setX_goodnam(rs.getString("x_goodnam"));
				    frag.setX_goodname(rs.getString("x_goodname"));
				    frag.setGoodjie(rs.getString("goodjie"));
				    frag.setGoodjieshao(rs.getString("goodjieshao"));
				    frag.setX_imag(rs.getString("x_imag"));
				    frag.setX_pic(rs.getString("x_pic"));
				    frag.setX_pict(rs.getString("X_pict"));
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
		 
		public List<XingzuoModle> getXingzuoList() {
			try {
				XingzuoModle frag = null;
				conn = ConnectionFactory.getConnection();
				List<XingzuoModle> list = new ArrayList<XingzuoModle>();
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT x_id,x_title,x_image ");
				sql.append(" FROM xingzuo ");
				sql.append(" ORDER BY x_id ");
				
				pstm = conn.prepareStatement(sql.toString());
				rs = pstm.executeQuery();
				while (rs.next()) {
					frag = new XingzuoModle();
					frag.setX_image(rs.getString("x_image"));
					frag.setX_id(rs.getString("x_id"));
					frag.setX_title(rs.getString("x_title"));
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



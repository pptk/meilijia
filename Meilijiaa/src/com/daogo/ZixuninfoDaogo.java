package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.conn.ConnectionFactory;
import com.dao.ZixuninfoDao;
import com.modle.Zixuninfo;

public class ZixuninfoDaogo implements ZixuninfoDao {

	// 建立连接数据库
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	public Serializable add_consult(Zixuninfo zixun) {

		try {
			conn = ConnectionFactory.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO consult(c_name,c_content,c_time,c_image1,c_image2,c_image3)");
			sql.append("VALUES(?,?,?,?,?,?)");

			pstm = conn.prepareStatement(sql.toString());

			pstm.setString(1, zixun.getName());
			pstm.setString(2, zixun.getContent());
			pstm.setString(3, zixun.getTime());
			pstm.setString(4, zixun.getImagepath1());
			pstm.setString(5, zixun.getImagepath2());
			pstm.setString(6, zixun.getImagepath3());
		//	pstm.setString(7, zixun.getState());
		//	pstm.setString(8, zixun.getCount());
			
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
	public String consult_deal(String name, String content, String time) {
		try {
			String flag = "no";
			conn = ConnectionFactory.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT c_name,c_content,c_time ");
			sql.append(" FROM consult ");
			sql.append(" WHERE c_name =? ");
			sql.append(" AND c_content= ?");
			sql.append(" AND c_time= ?");
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

	//用户自己发表的咨询
		public List<Zixuninfo> getZixunList(String name){
			try {
				Zixuninfo frag = null;
				conn = ConnectionFactory.getConnection();
				List<Zixuninfo> list = new ArrayList<Zixuninfo>();
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT c_id,c_name,c_content,c_time,c_image1,c_image2,c_image3 ");
				sql.append(" FROM consult ");
				sql.append(" WHERE c_name=? ");
				sql.append(" ORDER BY c_id DESC");
				
				pstm = conn.prepareStatement(sql.toString());
				pstm.setString(1,name);
				rs = pstm.executeQuery();
				while (rs.next()) {
					frag = new Zixuninfo();
					frag.setCid(rs.getString("c_id"));
					frag.setName(rs.getString("c_name"));
					frag.setContent(rs.getString("c_content"));
					frag.setTime(rs.getString("c_time"));
					frag.setImagepath1(rs.getString("c_image1"));
					frag.setImagepath2(rs.getString("c_image2"));
					frag.setImagepath3(rs.getString("c_image3"));
				//	frag.setCount(rs.getString("c_count"));
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



	//获取未回复的咨询
		public List<Zixuninfo> getConsultList() {
			try {
				Zixuninfo frag = null;
				conn = ConnectionFactory.getConnection();
				List<Zixuninfo> list = new ArrayList<Zixuninfo>();
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT c_id,c_name,c_content,c_time,c_image1,c_image2,c_image3 ");
				sql.append(" FROM consult ");
//				sql.append(" WHERE c_state = '0' ");
				sql.append(" ORDER BY c_id DESC");
				
				pstm = conn.prepareStatement(sql.toString());
				rs = pstm.executeQuery();
				while (rs.next()) {
					frag = new Zixuninfo();
					frag.setCid(rs.getString("c_id"));
					frag.setName(rs.getString("c_name"));
					frag.setContent(rs.getString("c_content"));
					frag.setTime(rs.getString("c_time"));
					frag.setImagepath1(rs.getString("c_image1"));
					frag.setImagepath2(rs.getString("c_image2"));
					frag.setImagepath3(rs.getString("c_image3"));
				//	frag.setCount(rs.getString("c_count"));
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


		//根据回复改变咨询的回复状态
		public Serializable modify_state(Zixuninfo zixuninfo) {
			try{
				
				conn = ConnectionFactory.getConnection();
				
				StringBuffer sql = new StringBuffer();
				sql.append(" UPDATE consult ");
				sql.append(" SET c_state=? ");
				sql.append(" WHERE c_id=? ");
				
				pstm = conn.prepareStatement(sql.toString());
				
				pstm.setString(1, zixuninfo.getState());
				pstm.setString(2, zixuninfo.getCid());
			
				pstm.executeUpdate();
			//	rs = pstm.getGeneratedKeys();		
				if (rs.next()) {
					return null;
				}

				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				ConnectionFactory.close(conn, pstm, rs);
			}
		}

		
		public static void main(String[] args) {
			ZixuninfoDaogo dao = new ZixuninfoDaogo();
			List<Zixuninfo> list = dao.getZixunList("momo");
			Zixuninfo model = new Zixuninfo();
			model = list.get(9);
			String x = model.getContent();
			System.out.println(x);
		}

}

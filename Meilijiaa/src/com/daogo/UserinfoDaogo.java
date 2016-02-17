package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.UserinfoDao;
import com.modle.User;
import com.modle.Userinfo;
import com.modle.Zixuninfo;


public class UserinfoDaogo implements UserinfoDao{

	//建立连接对象
	 
		private Connection conn=null;
		private PreparedStatement pstm = null;
		private ResultSet rs = null;
		
		public  String login_deal(String username, String pwd) {
			try{
				
				String flag="no";
				conn = ConnectionFactory.getConnection();
				//用pstm发送一条SQL语句
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT u_name,u_pwd ");
				sql.append(" FROM userinfo ");
				sql.append(" WHERE u_name =? " );
				sql.append(" AND u_pwd= ?" );
				
				pstm=conn.prepareStatement(sql.toString());
				pstm.setString(1,username);
				pstm.setString(2, pwd);

				rs = pstm.executeQuery();
				if(rs.next()){
					flag="yes";
					return flag;
				}else{
					
					return flag;
				}
				//如果找到了、将他存到user里、然后return出去
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}finally{
				ConnectionFactory.close(conn, pstm, rs);
			}

		}

		public Serializable add_user(User userinfo) {
          
			try{
				conn = ConnectionFactory.getConnection();
				
				StringBuffer sql = new StringBuffer();
				sql.append("INSERT INTO userinfo(u_name,u_pwd)");
				sql.append("VALUES(?,?)");
				
				pstm = conn.prepareStatement(sql.toString());
				
				pstm.setString(1, userinfo.getName());
				pstm.setString(2, userinfo.getPwd());
			
				pstm.executeUpdate();
				rs = pstm.getGeneratedKeys();
				
				if(rs.next()){
					return  rs.getInt(1);
				}
				
				return null;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}finally{
				ConnectionFactory.close(conn, pstm, rs);
			}
		}


		public String find_touxiang(String username) {

            try{
            	 String photopath=null;
            	 conn=ConnectionFactory.getConnection();
            	 StringBuffer sql=new StringBuffer();
            	 sql.append(" SELECT u_photoPath ");
            	 sql.append(" FROM userinfo ");
            	 sql.append(" WHERE u_name= ? ");
            	 
            	 pstm = conn.prepareStatement(sql.toString());
            	 pstm.setString(1,username);
            	 rs = pstm.executeQuery();
            	 if(rs.next()){
     				photopath = rs.getString("u_photoPath");
     			}
            	 
            	 return photopath;
             }catch(Exception e)
             {
            	 return null;
             }
			
		}

		public Serializable modify_Ziliao(User userinfo) {
			try{
				String flag="no";
				conn = ConnectionFactory.getConnection();
				
				StringBuffer sql = new StringBuffer();
				sql.append(" UPDATE userinfo ");
				sql.append(" SET u_signature=?  ");
				sql.append(" ,u_age=? ");
				sql.append(" , u_area=? ");
				sql.append(" WHERE u_name=? ");
				
				pstm = conn.prepareStatement(sql.toString());
				
				
				pstm.setString(1, userinfo.getQianming());
				pstm.setString(2, userinfo.getNianling());
				pstm.setString(3, userinfo.getDiqu());
				pstm.setString(4, userinfo.getName());
				pstm.executeUpdate();
				rs = pstm.getGeneratedKeys();
				
				if(rs.next()){
					return  rs.getInt(1);
				}
				
				return null;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}finally{
				ConnectionFactory.close(conn, pstm, rs);
			}
		}

		public String ziliao_deal(String username, String qianming,
				String nianling, String diqu) {
			try {
				String flag = "no";
				conn = ConnectionFactory.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT u_name,u_signature,u_age,u_area ");
				sql.append(" FROM userinfo ");
				sql.append(" WHERE u_name =? ");
				sql.append(" AND u_signature= ? ");
				sql.append(" AND u_age= ? ");
				sql.append(" AND u_area= ? ");
				pstm = conn.prepareStatement(sql.toString());
				pstm.setString(1, username);
				pstm.setString(2, qianming);
				pstm.setString(3, nianling);
				pstm.setString(4, diqu);
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
		
		
		
		public static void main(String[] args) {
			UserinfoDaogo dao = new UserinfoDaogo();
			List<Userinfo> list=new ArrayList<Userinfo>();
			String name="momo";
//			User user = new User("momo","sdfgsdgsd","100","dfgsd");
//			String x = dao.ziliao_deal("momo","sdfgsdgsd","100","dfgsd");
			list=dao.getZiliaoList(name);
			System.out.println(list);
		}
		
		public List<Userinfo> getZiliaoList(String name) {
			try {
				Userinfo frag = null;
				conn = ConnectionFactory.getConnection();
				List<Userinfo> list = new ArrayList<Userinfo>();
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT u_signature,u_age,u_area ");
				sql.append(" FROM userinfo ");
				sql.append(" WHERE u_name=? ");
				
				pstm = conn.prepareStatement(sql.toString());
				pstm.setString(1,name);
				rs = pstm.executeQuery();
				while (rs.next()) {
					frag = new Userinfo();
					frag.setQianming(rs.getString("u_signature"));
					frag.setNianling(rs.getString("u_age"));
					frag.setDiqu(rs.getString("u_area"));
					list.add(frag);
				}
				return list;
			} catch (SQLException e) {
				
				e.printStackTrace();
				return null;
			} finally {
				ConnectionFactory.close(conn, pstm, rs);
			}
		}
}
		
		


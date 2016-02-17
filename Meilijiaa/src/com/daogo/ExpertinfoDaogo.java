package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.conn.ConnectionFactory;
import com.dao.ExpertinfoDao;
import com.modle.Expertinfo;

public class ExpertinfoDaogo implements ExpertinfoDao{

	//建立连接对象 
	private Connection conn=null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	public String login_deal(String eid, String pwd) {
	try{
		
		String flag="no";
		conn = ConnectionFactory.getConnection();
		//用pstm发送一条SQL语句
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT e_id,e_pwd ");
		sql.append(" FROM expert");
		sql.append(" WHERE e_id =?" );
		sql.append(" AND e_pwd= ?" );
		
		pstm=conn.prepareStatement(sql.toString());
		pstm.setString(1,eid);
		pstm.setString(2,pwd);

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



	public  Serializable modify_pwd(Expertinfo expertinfo) {
		try{
			
			conn = ConnectionFactory.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE expert ");
			sql.append(" SET e_pwd=? ");
			sql.append(" WHERE e_id=? ");
			
			pstm = conn.prepareStatement(sql.toString());
			
			pstm.setString(1, expertinfo.getPwd());
			pstm.setString(2, expertinfo.getEid());
		
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

	public String find_name(String eid) {
		try{
			String name = null;
			conn = ConnectionFactory.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT e_name ");
			sql.append(" FROM expert ");
			sql.append(" WHERE e_id = ?");
			
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, eid);
			rs = pstm.executeQuery();
			if(rs.next()){
				name = rs.getString("e_name");
			}
			return name;
			
		}catch(Exception e){
			return null;
		}
	}
	public static void main(String[] args) {
		ExpertinfoDaogo dao = new ExpertinfoDaogo();
		String x = dao.login_deal("1267002029", "123456");
		System.out.println(x);
	}
}
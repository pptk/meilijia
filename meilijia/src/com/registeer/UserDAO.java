package com.registeer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.weiwei.meilijia.dao.DBOpenHelper;
import com.weiwei.meilijia.model.Tb_user;

/**
 * 用户注册数据表，注册时读取用户对象信息存入数据表中，登陆时按用户名和密码检索表中的数据进行鉴权
 * 
 * @Author 卫伟
 * @Time 2014-12-05
 */
public class UserDAO {
    private DBOpenHelper dbHelper;
    public UserDAO(Context context){
    	dbHelper=new DBOpenHelper(context);
    }
     // 登录时按输入的username,password查询user数据表的数据进行鉴权（是否为合法用户）
     public boolean login(String username,String password){
    	// 以读写方式打开数据库，做查询数据表中的username,password字段的所有元组
    	 SQLiteDatabase adb=dbHelper.getReadableDatabase();
    	 String sql="select*from user where username=? and password=?";
    	 //使用游标 (Cursors) 来导航浏览查询结果
    	 Cursor cursor=adb.rawQuery(sql, new String[] {username,password});
    	 //如果游标指向第一行，则返回 true
    	 if(cursor.moveToFirst()==true){
    		 cursor.close();
    		 return true;
    	 }
    	 return false;    	 
     }
     // 注册用户输入的信息存入tb_user数据表 
     public boolean register(Tb_user tb_user){
    	// 以读写方式打开数据库，按照表定义插入数据
    	 SQLiteDatabase adb=dbHelper.getReadableDatabase();
    	 String sql = "insert into user(username,password,name,sex,age,phone) values(?,?,?,?,?,?)";
 		Object obj[] = { tb_user.getUsername(), tb_user.getPassword(),
 				tb_user.getName(), tb_user.getSex(), tb_user.getAge(), tb_user.getPhone() };
 		adb.execSQL(sql, obj);
 		return true;
 	} 
  // 进入系统后按用户名搜索数据库的信息并输出
 	public String[] readDisplay(String username) {
 		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
 		String sql = "select * from user where username=?;";
 		Cursor cursor = sdb.rawQuery(sql, new String[] { username });
 		cursor.moveToFirst();
 		String baseInfo[] = { cursor.getString(1), cursor.getString(2),
 				cursor.getString(3), cursor.getString(4), cursor.getString(5),
 				cursor.getString(6) };
 		return baseInfo;
 	}
 }
    


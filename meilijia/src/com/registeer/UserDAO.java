package com.registeer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.weiwei.meilijia.dao.DBOpenHelper;
import com.weiwei.meilijia.model.Tb_user;

/**
 * �û�ע�����ݱ�ע��ʱ��ȡ�û�������Ϣ�������ݱ��У���½ʱ���û���������������е����ݽ��м�Ȩ
 * 
 * @Author ��ΰ
 * @Time 2014-12-05
 */
public class UserDAO {
    private DBOpenHelper dbHelper;
    public UserDAO(Context context){
    	dbHelper=new DBOpenHelper(context);
    }
     // ��¼ʱ�������username,password��ѯuser���ݱ�����ݽ��м�Ȩ���Ƿ�Ϊ�Ϸ��û���
     public boolean login(String username,String password){
    	// �Զ�д��ʽ�����ݿ⣬����ѯ���ݱ��е�username,password�ֶε�����Ԫ��
    	 SQLiteDatabase adb=dbHelper.getReadableDatabase();
    	 String sql="select*from user where username=? and password=?";
    	 //ʹ���α� (Cursors) �����������ѯ���
    	 Cursor cursor=adb.rawQuery(sql, new String[] {username,password});
    	 //����α�ָ���һ�У��򷵻� true
    	 if(cursor.moveToFirst()==true){
    		 cursor.close();
    		 return true;
    	 }
    	 return false;    	 
     }
     // ע���û��������Ϣ����tb_user���ݱ� 
     public boolean register(Tb_user tb_user){
    	// �Զ�д��ʽ�����ݿ⣬���ձ����������
    	 SQLiteDatabase adb=dbHelper.getReadableDatabase();
    	 String sql = "insert into user(username,password,name,sex,age,phone) values(?,?,?,?,?,?)";
 		Object obj[] = { tb_user.getUsername(), tb_user.getPassword(),
 				tb_user.getName(), tb_user.getSex(), tb_user.getAge(), tb_user.getPhone() };
 		adb.execSQL(sql, obj);
 		return true;
 	} 
  // ����ϵͳ���û����������ݿ����Ϣ�����
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
    


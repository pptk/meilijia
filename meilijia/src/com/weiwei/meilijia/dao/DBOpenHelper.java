package com.weiwei.meilijia.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	private static final int VERSION = 1;// 定义数据库版本号
	private static final String DBNAME = "meilijia.db";// 定义数据库名
	public DBOpenHelper(Context context) {
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		String sql = "create table user(id integer primary key autoincrement,username varchar(20),password varchar(20),name varchar(10),sex varchar(5),age integer,phone varchar(13))";
		db.execSQL(sql);
		db.execSQL("CREATE TABLE IF NOT EXISTS schedule(scheduleID integer primary key autoincrement,scheduleTypeID integer,remindID integer,scheduleContent text,scheduleDate text)");
		db.execSQL("CREATE TABLE IF NOT EXISTS scheduletagdate(tagID integer primary key autoincrement,year integer,month integer,day integer,scheduleID integer)");
		db.execSQL("create table Tb_shuiyou(timedd varchar(50),shui varchar(50),you varchar(50)) ");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS schedule");
		db.execSQL("DROP TABLE IF EXISTS scheduletagdate");
		onCreate(db);
	}

}

package com.weiwei.meilijia.dao;

import java.util.ArrayList;
import java.util.List;

import com.weiwei.meilijia.model.Tb_shuiyou;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ShuiYouDao {
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public ShuiYouDao(Context context) {
		helper = new DBOpenHelper(context);
	}

	// 添加测试记录
	public void add(Tb_shuiyou tb_shuiyou) {
		db = helper.getWritableDatabase();//
		db.execSQL("insert into tb_shuiyou(timedd,shui,you) values (?,?,?)",
				new Object[] { tb_shuiyou.getTimedd(),
				tb_shuiyou.getShui(), tb_shuiyou.getYou() });
	}

	public List<Tb_shuiyou> findall() {
		List<Tb_shuiyou> model = new ArrayList<Tb_shuiyou>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select timedd,shui,you from tb_shuiyou",
				null);
		while (cursor.moveToNext()) {
			// 将遍历到的支出信息添加到集合中
			model.add(new Tb_shuiyou(cursor.getString(cursor
					.getColumnIndex("timedd")), cursor.getString(cursor
					.getColumnIndex("shui")), cursor.getString(cursor
					.getColumnIndex("you"))));
		}
		return model;
	}
}

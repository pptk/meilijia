package com.weiwei.meilijia.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Window;



/**
 * 所有页面的父页，可用于共享数据、设置跳转方式等共同的效果。
 * 
 * @author 仲丛旭
 * 
 */
public class FatherActivity extends Activity {
//	/** 当前的城市id号 */
//	public static String cityFatherId = "101110101";
//	/** 当前界面背景图片id */
//	public static String backgroundId = "bg_help1";
	/** 存放解析所有城市名的map，例：朝阳-北京-北京 101010300 */
	public static Map<String, String> cityAllMap = null;
	/** 显示未来天数的数字 */
	public static int dayCount = 3;
	/** 父界面中的<"id" /"provinceName"，省名/省id>map缓存 */
	public static List<Map<String, String>> listFatherProvinces = null;
	/** 父界面中的<"id" /"provinceName"，市名/市id>map缓存 */
	public static Map<String, List<Map<String, String>>> mapFatherCitys = new HashMap<String, List<Map<String, String>>>();
	/** 父界面中的<"id" /"provinceName"，地区名/地区id>map缓存 */
	public static Map<String, List<Map<String, String>>> mapFatherAreas = new HashMap<String, List<Map<String, String>>>();
	/**
	 * 用来缓存用户首页界面的天气状态
	 * 
	 * 键是城市编号，值是相应的解析好的Map数据
	 */
	public static Map<String, Map<String, String>> userMap = new HashMap<String, Map<String, String>>();

	/**
	 * 初始化activity的一些信息，包括没有标题栏，把当前acivity设置到自定义的堆栈中去
	 * 
	 * @param activity
	 */
	public void initActivity(Activity activity) {
		AppManager.getAppManager().addActivity(activity);
		/* 设置为无标题栏并且为全屏模式 */
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	/**
	 * 获取城市id
	 * @return
	 */
	public String getCityFatherId(){
		SharedPreferences sharedata = getSharedPreferences("data", 0);
		String data = sharedata.getString("item", null);
		Log.v("cola","data="+data);
		return data;
	}
	/**
	 * 设置城市id
	 * @param cityFatherId
	 */
	public void setCityFatherId(String cityFatherId){
		Editor sharedata = getSharedPreferences("data", 0).edit();
		sharedata.putString("item",cityFatherId);
		sharedata.commit();
	}
	/**
	 * 获取背景id
	 * @return
	 */
	public String getBgFatherId(){
		SharedPreferences sharedata = getSharedPreferences("data2", 0);
		String data = sharedata.getString("item2", null);
		Log.v("cola","data2="+data);
		return data;
	}
	/**
	 * 设置背景id
	 * @param cityFatherId
	 */
	public void setBgFatherId(String bgFatherId){
		Editor sharedata = getSharedPreferences("data2", 0).edit();
		sharedata.putString("item2",bgFatherId);
		sharedata.commit();
	}
}

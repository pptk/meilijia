package com.weiwei.meilijia.base;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * public class DermaPagerAdapter
 * 
 * 用来绑定数据和view
 * 
 * @author huyong
 * 
 */
public class DermaPagerAdapter extends PagerAdapter {
	@SuppressWarnings("unused")
	private final String TAG = "DermaPagerAdapter";
	/** 界面列表 */
	private List<View> views = null;
	/** 上下文内�?*/
	@SuppressWarnings("unused")
	private Context context = null;

	public DermaPagerAdapter(List<View> views, Context context) {
		this.views = views;
		this.context = context;
	}

	/*
	 * �?��arg1位置的界�?non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View,
	 * int, java.lang.Object)
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	/*
	 * 获得当前界面�?non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return views.size();
	}

	// /*
	// * 初始化arg1位置的界�?non-Javadoc)
	// *
	// * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view
	// * .View, int)
	// */
	// @Override
	// public Object instantiateItem(View container, int position) {
	// Log.i(TAG, "==instantiateItem()start");
	// View view = inflater.inflate(resId, null);
	// ((ViewPager) container).addView(views.get(position), 0);
	// Log.i(TAG, "==instantiateItem()end");
	// return views.get(position);
	// }
	@Override
	public Object instantiateItem(View container, int position) {
		// Log.i(TAG,
		// "==instantiateItem()start,container="+container+",position="+position);
		((ViewPager) container).addView(views.get(position));
		// Log.i(TAG, "==instantiateItem()end");
		return views.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}
}

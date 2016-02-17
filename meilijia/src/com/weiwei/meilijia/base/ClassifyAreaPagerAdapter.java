package com.weiwei.meilijia.base;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * 适配分类查询下面的地区展示滑动操�?
 * 
 * @author 仲丛�?
 * 
 */
public class ClassifyAreaPagerAdapter extends PagerAdapter {
	private final String TAG = "ClassifyAreaPagerAdapter";
	/** 界面列表 */
	private List<View> views = null;
	/** 上下文内�?*/
	@SuppressWarnings("unused")
	private Context context = null;

	public ClassifyAreaPagerAdapter(List<View> views, Context context) {
		Log.i(TAG, "==ClassifyAreaPagerAdapter()");
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

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position));
		return views.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}

}

package com.weiwei.meilijia.base;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;

public class UserPagerAdapter extends PagerAdapter  {
	private final String TAG = "UserPagerAdapter";
	/** 鐣岄潰鍒楄〃 */
	private List<View> views = null;
	/** 涓婁笅鏂囧唴瀹?*/
	@SuppressWarnings("unused")
	private Context context = null;

	public UserPagerAdapter(List<View> views, Context context) {
		this.views = views;
		this.context = context;
	}

	//public UserPagerAdapter(List<String> list, BianhuaActivity bianhuaActivity,
	//		String inputStr) {
		// TODO 自动生成的构造函数存根
	//}

	/*
	 * 閿?瘉arg1浣嶇疆鐨勭晫闈?non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View,
	 * int, java.lang.Object)
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		// ((ViewPager) container).removeView(views.get(position));
	}

	/*
	 * 鑾峰緱褰撳墠鐣岄潰鏁?non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return views.size();
	}

	/*
	 * 鍒濆鍖朼rg1浣嶇疆鐨勭晫闈?non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view
	 * .View, int)
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		Log.i(TAG, "==instantiateItem()");
		((ViewPager) container).addView(views.get(position), 0);
		return views.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}
}

package com.weiwei.meilijia.base;

import java.util.ArrayList;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yu.R;

/**
 * 皮肤�?
 * 
 * @author 仲丛�?
 * 
 */
public class DermaPage extends FatherActivity {
	private final String TAG = "DermaPage";
	/** 左三角图片按�?*/
	private ImageButton themeLeft;
	/** 右三角图片按�?*/
	private ImageButton themeRight;
	/** 文字 */
	private TextView themeTitle;
	/** 页数 */
	private TextView themPer;
	/** 应用按钮 */
	private Button themeApp;
	/** 用于实现多页面的切换效果 */
	private ViewPager viewpage;
	/** 用来绑定数据的�?配器 */
	private DermaPagerAdapter adapter;
	/** 装分页显示的view的数�?*/
	private List<View> views;
	/** 记录当前选中位置 */
	private int currentIndex;

	// private final int itemSize = 7;

	// /**
	// * Layout中的XML资源
	// */
	// private static final int help_bg_drawable[] = { R.layout.slide_bg_view01,
	// R.layout.slide_bg_view02, R.layout.slide_bg_view03,
	// R.layout.slide_bg_view04, R.layout.slide_bg_view05,
	// R.layout.slide_bg_view06, R.layout.slide_bg_view07 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "==onCreate()");
		initActivity(this);
		init();
	}

	/**
	 * 初始化操�?
	 */
	private void init() {
		Log.i(TAG, "==init()");

		setContentView(R.layout.activity_derma_page);
		/* 创建�?��新的用于存放各个页面的数�?*/
		views = new ArrayList<View>();

		LayoutInflater inflater = LayoutInflater.from(this);
		/* 初始化引导图片列�?添加到视图列表中 */
		views.add(inflater.inflate(R.layout.slide_bg_view01, null));
		views.add(inflater.inflate(R.layout.slide_bg_view02, null));
		views.add(inflater.inflate(R.layout.slide_bg_view03, null));
		views.add(inflater.inflate(R.layout.slide_bg_view04, null));
		Log.i(TAG, "==views()end");
		viewpage = (ViewPager) findViewById(R.id.bg_viewpager);// 显示在控件的位置
		adapter = new DermaPagerAdapter(views, this); // 初始化Adapter
		viewpage.setAdapter(adapter);// 设置适配�?
		viewpage.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// Log.i(TAG, "==onPageSelected()");
				setCurDot(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		}); // 绑定回调

		themeApp = (Button) findViewById(R.id.theme_app);
		themeLeft = (ImageButton) findViewById(R.id.theme_left);
		themeRight = (ImageButton) findViewById(R.id.theme_right);
		themeTitle = (TextView) findViewById(R.id.theme_title);
		themPer = (TextView) findViewById(R.id.them_per);
		/* 设置背景 */
		themeApp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Log.i(TAG, "==onClick()");
				Log.i(TAG, "==���ݵ���Ӧ�ð�ť��������Ӧ�ı���");
				String backgroundId = "bg_help" + (currentIndex + 1);
				Log.i(TAG, "backgroundId==" + backgroundId);
				setBgFatherId(backgroundId);
				Intent intent = new Intent(DermaPage.this, BianhuaActivity.class);
				DermaPage.this.startActivity(intent);
				overridePendingTransition(R.anim.push_down_in,
						R.anim.push_down_out);// 上往下推出效�?
			}
		});
		/* 设置左三角按钮监�?*/
		themeLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				viewpage.setCurrentItem(currentIndex - 1);
			}
		});
		Snippet.setButtonFocusChanged(themeLeft);

		/* 设置右三角按钮监�?*/
		themeRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				viewpage.setCurrentItem(currentIndex + 1);
			}
		});
		Snippet.setButtonFocusChanged(themeRight);

	}

	/**
	 * 当前引导小点的�?�?
	 */
	private void setCurDot(int positon) {
		Log.i(TAG, "==setCurDot()");
		Log.i(TAG, "setCurDot()positon==" + positon);
		if (positon < 0 || positon > 4 || currentIndex == positon) {
			Log.i(TAG, "==意外�?��");
			return;
		}

		switch (positon) {
		case 0:
			themeLeft.setVisibility(4);
			themeTitle.setText("超凡脱俗");
			themPer.setText("1/4");
			break;
		case 1:
			themeLeft.setVisibility(0);
			themeTitle.setText("清新自然");
			themPer.setText("2/4");
			break;
		case 2:
			themeTitle.setText("少年情�?");
			themPer.setText("3/4");
			themeRight.setVisibility(0);
			break;
		case 3:
			themeTitle.setText("延绵不绝");
			themPer.setText("4/4");
			themeRight.setVisibility(4);
			break;
		}
		currentIndex = positon;
		Log.i(TAG, "setCurDot()==end");
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);// 上往下推出效�?
	}
}

package com.example.yu;


import com.weiwei.meilijia.base.BaseActivity;
import com.weiwei.meilijia.config.Constants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Ӧ�ó��������ࣺ��ʾ��ӭ���沢��ת��������
 * 
 * @author ��ΰ
 * @version
 * @created 2014-12-08
 */
public class WelcomeActivity extends BaseActivity {
	public static final String TAG = WelcomeActivity.class.getSimpleName();
	private ImageView mSplashItem_iv = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weiwei.meilijia.ui.base.BaseActivity#findViewById()
	 */

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		mSplashItem_iv = (ImageView) findViewById(R.id.splash_loading_item);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		Constants.SCREEN_DENSITY = metrics.density;
		Constants.SCREEN_HEIGHT = metrics.heightPixels;
		Constants.SCREEN_WIDTH = metrics.widthPixels;
		findViewById();
		initView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.weiwei.meilijia.base.BaseActivity#initView()
	 */
	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		Animation translate = AnimationUtils.loadAnimation(this,
				R.anim.splash_loading);
		translate.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				// ����LoginActivity���൱��Intent
				openActivity(HomeActivity.class);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				WelcomeActivity.this.finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

		});
		mSplashItem_iv.setAnimation(translate);
	}

}
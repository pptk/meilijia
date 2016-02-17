package com.weiwei.meilijia.base;

import com.example.yu.R;

import android.os.Bundle;

public class AboutPage extends FatherActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActivity(this);
		init();
	}

	private void init() {
		setContentView(R.layout.activity_about_page);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);// 上往下推出效�?
	}
}

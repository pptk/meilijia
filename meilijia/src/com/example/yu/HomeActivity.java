package com.example.yu;

import com.weiwei.meilijia.appmanager.AppManager;

import com.weiwei.meilijia.text.ShuiYouTest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class HomeActivity extends TabActivity {
	public static final String TAG = HomeActivity.class.getSimpleName();
	private RadioGroup mTabButtonGroup;
	private TabHost mTabHost;
	public static final String TAB_MAIN = "MAIN_ACTIVITY";
	public static final String TAB_TEST = "TEST_ACTIVITY";
	public static final String TAB_CLASSIFICATION = "CLASSIFITION_ACTIVITY";
	public static final String TAB_FOUND = "FOUND_ACTIVITY";
	public static final String TAB_MY = "MY_ACTIVITY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getInstance().addActivity(this);
		setContentView(R.layout.activity_home);
		findViewById();
		initView();
	}

	private void findViewById() {
		mTabButtonGroup = (RadioGroup) findViewById(R.id.home_radio_button_group);

	}

	private void initView() {
		mTabHost = getTabHost();
		Intent i_main = new Intent(this, IndexActivity.class);
		Intent i_test = new Intent(this, ShuiYouTest.class);
		//Intent i_classification = new Intent(this, Classification.class);
		Intent i_found = new Intent(this, FoundActivity.class);
		Intent i_my = new Intent(this, MyActivity.class);
		mTabHost.addTab(mTabHost.newTabSpec(TAB_MAIN).setIndicator(TAB_MAIN)
				.setContent(i_main));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_TEST).setIndicator(TAB_TEST)
				.setContent(i_test));
	//	mTabHost.addTab(mTabHost.newTabSpec(TAB_CLASSIFICATION)
	//			.setIndicator(TAB_CLASSIFICATION).setContent(i_classification));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_FOUND).setIndicator(TAB_FOUND)
				.setContent(i_found));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_MY).setIndicator(TAB_MY)
				.setContent(i_my));
		mTabHost.setCurrentTabByTag(TAB_MAIN);
		mTabButtonGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						switch (checkedId) {
						case R.id.home_tab_mlq:
							mTabHost.setCurrentTabByTag(TAB_MAIN);
							break;
						case R.id.home_tab_cs:
							mTabHost.setCurrentTabByTag(TAB_TEST);
							break;
					//	case R.id.home_tab_cg:
					//		mTabHost.setCurrentTabByTag(TAB_CLASSIFICATION);
					//		break;
						case R.id.home_tab_fx:
							mTabHost.setCurrentTabByTag(TAB_FOUND);
							break;
						case R.id.home_tab_my:
							mTabHost.setCurrentTabByTag(TAB_MY);
							break;
						default:
							break;
						}
					}

				});
	}

}

package com.weiwei.meilijia.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.yu.R;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
public class ChengshiActivity extends FatherActivity{
    private final String TAG="ChengshiActivity";
    /*上面的水平滑动的页面*/
    private HorizontalScrollView scrollView_classify;
	/** 显示每个市的单选按钮组 */
	private RadioGroup radioGroup_classify;
	/** 下方的可横向拖动的控件 */
	private ViewPager viewPager_classify_areas;
	/** 下拉地区列表布局 */
	private LinearLayout lin_classify_provinces;
	/** 下拉地区列表 */
	private ListView listView_classify_provinces;
	/** 标题栏上的那个按钮 */
	private Button button_title_classify;

	/* 各种自定义 */
	/** 自定定处理省的适配器 */
	private ClassifyListViewAdapter cListViewAdapter;

	/** 显示市名称时，每个button的长度 */
	int lenButton = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "==onCreate()");
		initClassify();
		getProvinces();
	}

	/**
	 * 获取省的列表数据,并进行数据适配
	 */
	private void getProvinces() {
		Log.i(TAG, "==getProvinces()");
		if (listFatherProvinces == null) {
			/* 去解析，将解析结果保存到父界面缓存里去 */
			listFatherProvinces = PullParserHelper.getListProvinceByXml(this
					.getResources().getXml(R.xml.citys_weather));
		}
		/* 展示数据 */
		cListViewAdapter = new ClassifyListViewAdapter(this,
				listFatherProvinces);
		listView_classify_provinces.setAdapter(cListViewAdapter);
	}

	/**
	 * 初始化最初界面状态
	 */
	private void initClassify() {
		Log.i(TAG, "==initClassify()");
		/* 自定义标题栏 */
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);// 设置自定义
		setContentView(R.layout.activity_classify_page);// 设置显示界面
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.bianhua_activity);// 设置标题
		AppManager.getAppManager().addActivity(this);
		/* 初始化能看见的布局 */
		scrollView_classify = (HorizontalScrollView) findViewById(R.id.scrollView_classify);
		radioGroup_classify = (RadioGroup) findViewById(R.id.radioGroup_classify);
		viewPager_classify_areas = (ViewPager) findViewById(R.id.viewPager_classify_areas);
		lin_classify_provinces = (LinearLayout) findViewById(R.id.lin_classify_provinces);
		listView_classify_provinces = (ListView) findViewById(R.id.listView_classify_provinces);
		button_title_classify = (Button) findViewById(R.id.button_title_classify);
		/* 设置监听 */
		listView_classify_provinces
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						TextView provinceName = (TextView) arg1
								.findViewById(R.id.textView_item_classify_name);
						TextView id = (TextView) arg1
								.findViewById(R.id.textView_item_classify_id);
						button_title_classify.setText(provinceName.getText()
								.toString());
						lin_classify_provinces.setVisibility(View.GONE);
						String provinceId = id.getText().toString();
						getCity(provinceId);
					}
				});

		OnClickListener mOnClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "==onClick()");
				int vId = v.getId();
				if (vId == button_title_classify.getId()) {
					int isShow = lin_classify_provinces.getVisibility();
					Log.i(TAG, "isShow==" + isShow);
					if (isShow == View.VISIBLE) {
						lin_classify_provinces.setVisibility(View.GONE);
					} else {
						lin_classify_provinces.setVisibility(View.VISIBLE);
					}
				}
			}

		};
		button_title_classify.setOnClickListener(mOnClickListener);
	}

	/**
	 * 根据省id，得到相应的城市名及id
	 * 
	 * @param provinceId
	 */
	protected void getCity(String provinceId) {
		Log.i(TAG, "==getCity()");
		/* 得到数据 */
		List<Map<String, String>> listCitys = mapFatherCitys.get(provinceId);
		if (listCitys == null) {
			/* 根据省id得到的父界面缓存数据不存在，重新解析一下，然后保存到父界面缓存中去 */
			listCitys = PullParserHelper.getListCityByXml(this.getResources()
					.getXml(R.xml.citys_weather), provinceId);
			mapFatherCitys.put(provinceId, listCitys);
		}
		Log.i(TAG, "listCitys==" + listCitys);
		getButtonLen(listCitys.size());

		/* 把数据添加到每一个单选按钮上 */
		radioGroup_classify.removeAllViews();
		List<View> myViews = new ArrayList<View>();// 保存view
		for (int i = 0; i < listCitys.size(); i++) {
			RadioButton rButton = new RadioButton(this);
			rButton.setLayoutParams(new LinearLayout.LayoutParams(lenButton,
					LayoutParams.MATCH_PARENT));
			rButton.setTag(R.id.tag_id, listCitys.get(i).get("id"));// 设置id
			rButton.setTag(R.id.tag_index, i);// 设置位置
			rButton.setText(listCitys.get(i).get("cityName"));
			rButton.setGravity(Gravity.CENTER);// 文字居中
			rButton.setBackgroundResource(R.drawable.radiobtn_select);// 背景状态设置
			rButton.setTextColor(getResources().getColor(R.color.white));
			rButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));// 去掉单选按钮前面的点颜色为空
			rButton.setPadding(0, 0, 0, 0);// 文字在上下左右的边距
			rButton.setSingleLine(true);// 文字在一行显示

			/* 将单选按钮放入单选按钮组容器中 */
			radioGroup_classify.addView(rButton);

			/* 为每一个按钮设置相应的listView布局 */
			ListView lView = getArea(rButton.getTag(R.id.tag_id).toString());

			myViews.add(lView);// 添加每一个view
		}
		/* 适配每个listView到viewPager上 */
		ClassifyAreaPagerAdapter cAreaPagerAdapter = new ClassifyAreaPagerAdapter(
				myViews, this);
		viewPager_classify_areas.setAdapter(cAreaPagerAdapter);
		radioGroup_classify.getChildAt(0).performClick();
		/* 设置监听 */
		radioGroup_classify
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						RadioButton rButton = (RadioButton) group
								.findViewById(checkedId);
						Log.i(TAG, "onCheckedChanged()==rButton-->" + rButton);
						int index = (Integer) rButton.getTag(R.id.tag_index);
						viewPager_classify_areas.setCurrentItem(index);
						/* 更新当前蓝色横条距离左边的距离 */
						int x = (index - 1) * lenButton;
						int y = 0;
						Log.i(TAG, "onCheckedChanged()x==" + x);
						Log.i(TAG, "onCheckedChanged()y==" + y);
						scrollView_classify.smoothScrollTo(x, y);
					}
				});
		viewPager_classify_areas
				.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						/* 滑动ViewPager的时候,让上方的HorizontalScrollView自动切换 */
						radioGroup_classify.getChildAt(arg0).performClick();

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {

					}
				});
	}

	/**
	 * 根据手机尺寸获得按钮长度
	 * 
	 * @param i
	 */
	@SuppressWarnings("deprecation")
	private void getButtonLen(int i) {
		Display display = getWindowManager().getDefaultDisplay(); // Activity#getWindowManager()
		lenButton = 0;
		switch (i) {
		case 1:
			lenButton = display.getWidth();
			break;
		case 2:
			lenButton = display.getWidth() * 1 / 2;
			break;
		case 3:
			lenButton = display.getWidth() * 1 / 3;
			break;
		default:
			lenButton = display.getWidth() * 3 / 10;
		}
	}

	/**
	 * 得到地区
	 * 
	 * @param cityId
	 * @param count
	 */
	private ListView getArea(String cityId) {
		/* 创建每一个listView,并为其安装好数据 */
		ListView lView = new ListView(this);
		lView.setTag(cityId);
		lView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		lView.setDivider(null);// 设置分隔物为空
		/* 得到数据源 */
		List<Map<String, String>> listAreas = mapFatherCitys.get(cityId);
		if (listAreas == null) {
			/* 根据省id得到的父界面缓存数据不存在，重新解析一下，然后保存到父界面缓存中去 */
			listAreas = PullParserHelper.getListAreaByXml(this.getResources()
					.getXml(R.xml.citys_weather), cityId);
			mapFatherCitys.put(cityId, listAreas);
		}
		/* 生成适配器的Item和动态数组对应的元素 */
		ClassifyAreaListAdapter adapter = new ClassifyAreaListAdapter(this,
				listAreas);
		/* 添加适配器 */
		lView.setAdapter(adapter);
		lView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView tView = (TextView) arg1
						.findViewById(R.id.textView_item_classifyAreas_name);
				String cityIdStr = tView.getTag().toString();
				if (cityIdStr != null && !cityIdStr.equals("")) {
					Log.i(TAG,"cityIdStr=="+cityIdStr);
					setCityFatherId(cityIdStr);
					Intent intent = new Intent(ChengshiActivity.this,
							BianhuaActivity.class);
					ChengshiActivity.this.startActivity(intent);
				} else {
					Toast.makeText(ChengshiActivity.this, "没找到这个地点的id",
							Toast.LENGTH_LONG).show();
				}

			}
		});
		return lView;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);// 上往下推出效果
	}
}



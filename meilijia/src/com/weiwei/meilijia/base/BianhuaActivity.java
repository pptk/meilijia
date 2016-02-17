package com.weiwei.meilijia.base;

import java.sql.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.example.yu.R;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class BianhuaActivity extends FatherActivity {
	private final String TAG = "BianhuaActivity";
	/** 主界面背景 */
	private ImageView imageView_user_bg;
	/** 显示引导页中所有页面的控件 */
	private ViewPager viewPager_user = null;
	/** 保存引导页布局 */
	private List<View> views;
	/** 适配viewpager的适配器 */
	private UserPagerAdapter userPagerAdapter = null;
	/** 存放显示数据的map */
	private Map<String, String> map = null;
	/** 天气图片 */
	private ImageView imageView_user_imgOne = null;
	/** 城市名称 */
	private TextView textView_user_city = null;
	/** 右上角方向日期 */
	private TextView textView_user_date = null;
	/** 今天温度 */
	private TextView textView_user_temp = null;
	/** 滚动显示皮肤状况 */
	private TextView textView_user_index_d = null;
	/** 紫外线 */
	private TextView textView_user_index_uv;
	/** 皮肤水分 */
	private TextView textView_user_index_xc;
	/** 上妆*/
	private TextView textView_user_index_tr;
	/** 舒适指数 */
	private TextView textView_user_index_co;
	/** 卸妆 */
	private TextView textView_user_index_cl;
	/** 皮肤油分 */
	private TextView textView_user_index_ls;
	/** 过敏 */
	private TextView textView_user_index_ag;

	/** 天气 */
	private TextView textView_user_weather = null;
	/** 双击退出标识 */
	private static Boolean isExit = false;
	// /** 可自动提示的文本框 */
	// private AutoCompleteTextView autoCompleteTextView_searchText = null;

	/* 搜索页上的控件 */
	/** 搜索文本框 */
	private EditText editText_user_search_text = null;
	/** 搜索文本框上的删除小图标 */
	private Button button_user_search_delete = null;
	/** 显示智能查询的listView */
	private ListView listView_user_search_person = null;

	/* 自定义的adapter */
	/** 适配listView的自己写的adapter */
	private UserPagerAdapter uSearchAdapter = null;

	/** 显示其它五天信息的分页 */
	private LinearLayout linear_user_first_other;

	/** 搜索界面的其它功能框 */
	private LinearLayout lin_user_search_other;
	/** 分类查询按钮 */
	private Button button_user_search_classify;
	/** 更换皮肤按钮 */
	private Button button_user_search_derma;
	///** 帮 助按钮 */
	//private Button button_user_search_helper;
	///** 关 于按钮 */
//	private Button button_user_search_about;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "==onCreate");
		initActivity(this);
		initPager();
		initLayout();
		initData();
		requestUrlData();
	}

	/**
	 * 初始化能用到的数据
	 */
	private void initData() {
		Log.i(TAG, "initData()cityAllMap==" + cityAllMap);
		if (cityAllMap == null) {
			cityAllMap = PullParserHelper.listparserXml(this.getResources()
					.getXml(R.xml.citys_weather));
		}
	}

	/**
	 * 请求网络数据
	 */
	private void requestUrlData() {
		String cityId  = getCityFatherId();
		Log.i(TAG, "cityId==" + cityId);
		if (userMap.get(cityId) == null) {
			Log.i(TAG, "==缓存数据存在，则进行网络请求数据并解析显示");
			new MyTask(this).execute("http://m.weather.com.cn/data/"
					+ cityId + ".html");
		} else {
			Log.i(TAG, "==省略网络请求，直接适配数据");
			map = userMap.get(cityId);
			showResult();
			Log.i(TAG, "map==" + map);
		}
	}

	/**
	 * 适配listView
	 */
	private void listViewAdapter(String inputStr) {
		Log.i(TAG, "listViewAdapter()==inputStr-->" + inputStr);
		Log.i(TAG, "cityAllMap==" + cityAllMap);
		List<String> list = new ArrayList<String>();// 用于展示结果的list
		for (String strKey : cityAllMap.keySet()) {
			if (inputStr != null && !"".equals(inputStr)
					&& strKey.contains(inputStr)) {
				/* 满足包含输入框中输入的文字内容的，进行添加展式 */
				list.add(strKey);
			}
		}

		/* 初始化Adapter */
		userPagerAdapter = new UserPagerAdapter(views, this);
		viewPager_user.setAdapter(userPagerAdapter);
	}

	/**
	 * 初始化需要的分页
	 */
	private void initPager() {
		setContentView(R.layout.activity_user_page);
		/* 初始化布局控件及监听 */
		viewPager_user = (ViewPager) findViewById(R.id.viewPager_user);
		imageView_user_bg = (ImageView) findViewById(R.id.imageView_user_bg);
		/* 初始化引导图片列表 */
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.bianhua_layout, null));
		views.add(inflater.inflate(R.layout.bianhua, null));
		/* 初始化Adapter */
		userPagerAdapter = new UserPagerAdapter(views, this);
		viewPager_user.setAdapter(userPagerAdapter);
	}

	/**
	 * 初始化界面中出现的布局
	 */
	private void initLayout() {
		View firstView = views.get(0);// 得到第一个页面view
		Log.i(TAG, "firstView==" + firstView);
		imageView_user_imgOne = (ImageView) firstView
				.findViewById(R.id.imageView_user_imgOne);
		textView_user_city = (TextView) firstView
				.findViewById(R.id.textView_user_city);
		textView_user_date = (TextView) firstView
				.findViewById(R.id.textView_user_date);
		textView_user_temp = (TextView) firstView
				.findViewById(R.id.textView_user_temp);
		textView_user_index_d = (TextView) firstView
				.findViewById(R.id.textView_user_index_d);
		textView_user_index_uv = (TextView) firstView
				.findViewById(R.id.textView_user_index_uv);
		textView_user_index_xc = (TextView) firstView
				.findViewById(R.id.textView_user_index_xc);
		textView_user_index_tr = (TextView) firstView
				.findViewById(R.id.textView_user_index_tr);
		textView_user_index_co = (TextView) firstView
				.findViewById(R.id.textView_user_index_co);
		textView_user_index_cl = (TextView) firstView
				.findViewById(R.id.textView_user_index_cl);
		textView_user_index_ls = (TextView) firstView
				.findViewById(R.id.textView_user_index_ls);
		textView_user_index_ag = (TextView) firstView
				.findViewById(R.id.textView_user_index_ag);

		textView_user_weather = (TextView) firstView
				.findViewById(R.id.textView_user_weather);
		linear_user_first_other = (LinearLayout) firstView
				.findViewById(R.id.linear_user_first_other);

		View firstView2 = views.get(1);// 得到第二个页面view
		editText_user_search_text = (EditText) firstView2
				.findViewById(R.id.editText_user_search_text);
		button_user_search_delete = (Button) firstView2
				.findViewById(R.id.button_user_search_delete);
		listView_user_search_person = (ListView) firstView2
				.findViewById(R.id.listView_user_search_person);
		lin_user_search_other = (LinearLayout) firstView2
				.findViewById(R.id.lin_user_search_other);
		button_user_search_classify = (Button) firstView2
				.findViewById(R.id.button_user_search_classify);
		button_user_search_derma = (Button) firstView2
				.findViewById(R.id.button_user_search_derma);
	//	button_user_search_helper = (Button) firstView2
	//			.findViewById(R.id.button_user_search_helper);
	//	button_user_search_about = (Button) firstView2
	//			.findViewById(R.id.button_user_search_about);

	//	Snippet.setButtonFocusChanged(button_user_search_about);
		Snippet.setButtonFocusChanged(button_user_search_classify);
		//Snippet.setButtonFocusChanged(button_user_search_helper);
		Snippet.setButtonFocusChanged(button_user_search_derma);

		OnClickListener myClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				int vId = v.getId();
				if (vId == button_user_search_delete.getId()) {
					editText_user_search_text.setText("");// 清空编辑显示框
					lin_user_search_other.setVisibility(View.VISIBLE);
				} else if (vId == button_user_search_derma.getId()) {
					goDermaPage();
			//	} else if (vId == button_user_search_helper.getId()) {
			//		goGuidePage();
			//	} else if (vId == button_user_search_about.getId()) {
			//		goAboutPage();
				} else if (vId == button_user_search_classify.getId()) {
					goClassifyPage();
				}
			}

		};

		/* 删除小按钮单击操作 */
		button_user_search_delete.setOnClickListener(myClickListener);
		/* 其它单击监听 */
		button_user_search_derma.setOnClickListener(myClickListener);
	//	button_user_search_helper.setOnClickListener(myClickListener);
	//	button_user_search_about.setOnClickListener(myClickListener);
		button_user_search_classify.setOnClickListener(myClickListener);

		/* 编辑框的内容监听 */
		editText_user_search_text.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int arg1, int arg2,
					int arg3) {

				String inputStr = s.toString();
				/* 判断是否存在删除小图标 */
				if (inputStr.equals("")) {
					button_user_search_delete.setVisibility(View.INVISIBLE);
					lin_user_search_other.setVisibility(View.VISIBLE);
				} else {
					button_user_search_delete.setVisibility(View.VISIBLE);
					lin_user_search_other.setVisibility(View.GONE);
				}

				listViewAdapter(inputStr);// 显示得到的数据
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}
		});
		/* listView监听 */
		listView_user_search_person
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						TextView text = (TextView) arg1
								.findViewById(R.id.textView_item_user_cityName);
						String str = text.getText().toString();
						Log.i(TAG, "str==" + str);
						String cityIdStr = cityAllMap.get(str);
						Log.i(TAG, "cityIdStr==" + cityIdStr);
						if (cityIdStr != null && !cityIdStr.equals("")) {
							setCityFatherId(cityIdStr); 
							Intent intent = new Intent(BianhuaActivity.this,
									BianhuaActivity.class);
							BianhuaActivity.this.startActivity(intent);
						} else {
							Toast.makeText(BianhuaActivity.this, "没找到这个地点的id",
									Toast.LENGTH_LONG).show();
						}
					}
				});
		// imageView_user_bg.setImageResource(getImageId(backgroundId));
		String backgroundId = getBgFatherId();
		Log.i(TAG,"backgroundId=="+backgroundId);
		imageView_user_bg.setBackgroundResource(getImageId1(backgroundId));
	}

	public void goDermaPage() {
		Intent intent = new Intent(BianhuaActivity.this, DermaPage.class);
		BianhuaActivity.this.startActivity(intent);
		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);// 下往上推出效果
	}

//	public void goGuidePage() {
//		Intent intent = new Intent(BianhuaActivity.this, GuidePage.class);
//		BianhuaActivity.this.startActivity(intent);
//		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);// 下往上推出效果
//	}

	public void goClassifyPage() {
		Intent intent = new Intent(BianhuaActivity.this, ClassifyPage.class);
		BianhuaActivity.this.startActivity(intent);
		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);// 下往上推出效果
	}

	public void goAboutPage() {
		Intent intent = new Intent(BianhuaActivity.this, AboutPage.class);
		BianhuaActivity.this.startActivity(intent);
		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);// 下往上推出效果
	}

	class MyTask extends AsyncTask<String, Void, byte[]> {
		private Context context;
		private ProgressDialog pdDialog;

		public MyTask(Context context) {
			this.context = context;
			pdDialog = new ProgressDialog(context);
			pdDialog.setMessage("正在加载中，请稍后ing...");
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pdDialog.show();
		}

		@Override
		protected byte[] doInBackground(String... params) {
			Log.i(TAG, "params[0]=" + params[0]);
			return HttpURLConnHelper.downLoadByteFromURL(params[0]);
		}

		@Override
		protected void onPostExecute(byte[] result) {
			super.onPostExecute(result);
			if (result == null) {
				pdDialog.dismiss();
				Toast.makeText(context, "无法连接到网络", Toast.LENGTH_LONG).show();
				return;
			}
			try {
				String JSONStr = new String(result, "UTF-8");
				Log.i(TAG, "==开始解析JSON文件,JSONStr-->" + JSONStr);
				map = JSONHelper.parserJsonAll(JSONStr, "weatherinfo");
				Log.i(TAG, "==开始把数据缓存到内存中");
				userMap.put(getCityFatherId(), map);
				Log.i(TAG, "==显示解析结果");
				showResult();
			} catch (Exception e) {
				Log.i(TAG, "==显示最终结果异常");
				e.printStackTrace();
			}
			pdDialog.dismiss();
		}

	}

	@SuppressWarnings("deprecation")
	public void showResult() {
		/* 展示未来五天的天气情况 */
		SimpleDateFormat formatterDate = new SimpleDateFormat("EEEE",
				Locale.CHINA);
		Date curDate = new Date(System.currentTimeMillis());

		for (int i = 0; i < dayCount; i++) {
			/* 把星期+1，为下一次循环做准备 */
			curDate.setDate(curDate.getDate() + 1);

			/*
			 * 设置星期
			 */
			TextView week = new TextView(this);
			week.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
			week.setGravity(Gravity.CENTER);// 文字居中
			// weeks[i].setTextColor(getResources().getColor(R.color.black));//
			// 设置文字颜色
			week.setPadding(0, 0, 0, 0);// 文字在上下左右的边距
			week.setSingleLine(true);// 文字在一行显示
			week.setText(formatterDate.format(curDate));

			/*
			 * 设置天气图标
			 */
			ImageView img = new ImageView(this);
			img.setLayoutParams(new LinearLayout.LayoutParams(45,
					45));
			img.setBackgroundResource(getImageId("b1_"
					+ map.get("img" + (i * 2 + 3))));
			/*
			 * 温度
			 */
			TextView temp = new TextView(this);
			temp.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
			temp.setGravity(Gravity.CENTER);// 文字居中
			// weeks[i].setTextColor(getResources().getColor(R.color.black));//
			// 设置文字颜色
			temp.setPadding(0, 0, 0, 0);// 文字在上下左右的边距
			temp.setSingleLine(true);// 文字在一行显示
			temp.setText(map.get("temp" + (i + 2)));

			/*
			 * 天气描述
			 */
			TextView weather = new TextView(this);
			weather.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
			weather.setGravity(Gravity.CENTER);// 文字居中
			// weeks[i].setTextColor(getResources().getColor(R.color.black));//
			// 设置文字颜色
			weather.setPadding(0, 0, 0, 0);// 文字在上下左右的边距
			weather.setSingleLine(true);// 文字在一行显示
			weather.setText(map.get("weather" + (i + 2)));

			/*
			 * 风向
			 */
			TextView wind = new TextView(this);
			wind.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
			wind.setGravity(Gravity.CENTER);// 文字居中
			// weeks[i].setTextColor(getResources().getColor(R.color.black));//
			// 设置文字颜色
			wind.setPadding(0, 0, 0, 0);// 文字在上下左右的边距
			wind.setSingleLine(true);// 文字在一行显示
			wind.setText(map.get("wind" + (i + 2)));

			/*
			 * 每一天布局
			 */
			LinearLayout linear = new LinearLayout(this);
			linear.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT, 1.0f));
			linear.setOrientation(LinearLayout.VERTICAL);
			linear.setGravity(Gravity.CENTER);
			linear.addView(week);
			linear.addView(img);
			linear.addView(temp);
			linear.addView(weather);
			linear.addView(wind);

			/* 添加到最终显示的布局上 */
			linear_user_first_other.addView(linear);

		}
		Log.i(TAG, "==showResult()");
		String s1 = map.get("img1");
		Log.i(TAG, "s1==" + s1);
		Log.i(TAG, "imageView_user_imgOne==" + imageView_user_imgOne);
		Log.i(TAG, "getImageId(s1)==" + getImageId("b1_" + s1));
		imageView_user_imgOne.setImageResource(getImageId("b1_" + s1));// 假设现在是白天
		// if ("99".equals(s2)) {
		// img2.setVisibility(View.GONE);
		// } else {
		// img2.setImageResource(getImageId(s2));
		// }

		textView_user_city.setText(map.get("city"));
		textView_user_temp.setText(map.get("temp1") + "");
		textView_user_weather.setText(map.get("weather1") + ","
				+ map.get("wind1"));
		textView_user_date.setText(map.get("date_y") + map.get("week"));
		//String str = "穿衣指数：" + map.get("index_d");
		 String str = "根据天气的状态，做出科学的护肤变化！皮肤敏感时！请慎重护肤补妆";
		textView_user_index_d.setText(str);
		str = "紫外线:" + map.get("index_uv");
		textView_user_index_uv.setText(str);
		str = "补妆:" + map.get("index_xc");
		textView_user_index_xc.setText(str);
		str = "控油:" + map.get("index_tr");
		textView_user_index_tr.setText(str);
		str = "舒适指数:" + map.get("index_co");
		textView_user_index_co.setText(str);
		str = "卸妆:" + map.get("index_cl");
		textView_user_index_cl.setText(str);
		str = "补水:" + map.get("index_ls");
		textView_user_index_ls.setText(str);
		str = "过敏:" + map.get("index_ag");
		textView_user_index_ag.setText(str);
	}

	public int getImageId(String id) {
		Log.i(TAG, "==getImageId()id-->" + id);
		Class<R.drawable> cls = R.drawable.class;
		try {
			return cls.getDeclaredField(id).getInt(null);
		} catch (Exception e) {
			Log.i(TAG, "==getImageId()异常");
			e.printStackTrace();
			return R.drawable.default_headphoto_icon;
		}
	}
	public int getImageId1(String id) {
		Log.i(TAG, "==getImageId()id-->" + id);
		Class<R.drawable> cls = R.drawable.class;
		try {
			return cls.getDeclaredField(id).getInt(null);
		} catch (Exception e) {
			Log.i(TAG, "==getImageId()异常");
			e.printStackTrace();
			return R.drawable.bg_help3;
		}
	}
	/*
	 * 菜单、返回键响应(non-Javadoc)
	 * 
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Timer tExit = null;
			if (isExit == false) {
				isExit = true; // 准备退出
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				tExit = new Timer();
				tExit.schedule(new TimerTask() {
					@Override
					public void run() {
						isExit = false; // 取消退出
					}
				}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

			} else {
				this.finish();
				AppManager.getAppManager().AppExit(this);
				System.exit(0);
			}
		}
		return false;
	}

}


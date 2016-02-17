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
	/** �����汳�� */
	private ImageView imageView_user_bg;
	/** ��ʾ����ҳ������ҳ��Ŀؼ� */
	private ViewPager viewPager_user = null;
	/** ��������ҳ���� */
	private List<View> views;
	/** ����viewpager�������� */
	private UserPagerAdapter userPagerAdapter = null;
	/** �����ʾ���ݵ�map */
	private Map<String, String> map = null;
	/** ����ͼƬ */
	private ImageView imageView_user_imgOne = null;
	/** �������� */
	private TextView textView_user_city = null;
	/** ���ϽǷ������� */
	private TextView textView_user_date = null;
	/** �����¶� */
	private TextView textView_user_temp = null;
	/** ������ʾƤ��״�� */
	private TextView textView_user_index_d = null;
	/** ������ */
	private TextView textView_user_index_uv;
	/** Ƥ��ˮ�� */
	private TextView textView_user_index_xc;
	/** ��ױ*/
	private TextView textView_user_index_tr;
	/** ����ָ�� */
	private TextView textView_user_index_co;
	/** жױ */
	private TextView textView_user_index_cl;
	/** Ƥ���ͷ� */
	private TextView textView_user_index_ls;
	/** ���� */
	private TextView textView_user_index_ag;

	/** ���� */
	private TextView textView_user_weather = null;
	/** ˫���˳���ʶ */
	private static Boolean isExit = false;
	// /** ���Զ���ʾ���ı��� */
	// private AutoCompleteTextView autoCompleteTextView_searchText = null;

	/* ����ҳ�ϵĿؼ� */
	/** �����ı��� */
	private EditText editText_user_search_text = null;
	/** �����ı����ϵ�ɾ��Сͼ�� */
	private Button button_user_search_delete = null;
	/** ��ʾ���ܲ�ѯ��listView */
	private ListView listView_user_search_person = null;

	/* �Զ����adapter */
	/** ����listView���Լ�д��adapter */
	private UserPagerAdapter uSearchAdapter = null;

	/** ��ʾ����������Ϣ�ķ�ҳ */
	private LinearLayout linear_user_first_other;

	/** ����������������ܿ� */
	private LinearLayout lin_user_search_other;
	/** �����ѯ��ť */
	private Button button_user_search_classify;
	/** ����Ƥ����ť */
	private Button button_user_search_derma;
	///** �� ����ť */
	//private Button button_user_search_helper;
	///** �� �ڰ�ť */
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
	 * ��ʼ�����õ�������
	 */
	private void initData() {
		Log.i(TAG, "initData()cityAllMap==" + cityAllMap);
		if (cityAllMap == null) {
			cityAllMap = PullParserHelper.listparserXml(this.getResources()
					.getXml(R.xml.citys_weather));
		}
	}

	/**
	 * ������������
	 */
	private void requestUrlData() {
		String cityId  = getCityFatherId();
		Log.i(TAG, "cityId==" + cityId);
		if (userMap.get(cityId) == null) {
			Log.i(TAG, "==�������ݴ��ڣ�����������������ݲ�������ʾ");
			new MyTask(this).execute("http://m.weather.com.cn/data/"
					+ cityId + ".html");
		} else {
			Log.i(TAG, "==ʡ����������ֱ����������");
			map = userMap.get(cityId);
			showResult();
			Log.i(TAG, "map==" + map);
		}
	}

	/**
	 * ����listView
	 */
	private void listViewAdapter(String inputStr) {
		Log.i(TAG, "listViewAdapter()==inputStr-->" + inputStr);
		Log.i(TAG, "cityAllMap==" + cityAllMap);
		List<String> list = new ArrayList<String>();// ����չʾ�����list
		for (String strKey : cityAllMap.keySet()) {
			if (inputStr != null && !"".equals(inputStr)
					&& strKey.contains(inputStr)) {
				/* ��������������������������ݵģ��������չʽ */
				list.add(strKey);
			}
		}

		/* ��ʼ��Adapter */
		userPagerAdapter = new UserPagerAdapter(views, this);
		viewPager_user.setAdapter(userPagerAdapter);
	}

	/**
	 * ��ʼ����Ҫ�ķ�ҳ
	 */
	private void initPager() {
		setContentView(R.layout.activity_user_page);
		/* ��ʼ�����ֿؼ������� */
		viewPager_user = (ViewPager) findViewById(R.id.viewPager_user);
		imageView_user_bg = (ImageView) findViewById(R.id.imageView_user_bg);
		/* ��ʼ������ͼƬ�б� */
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.bianhua_layout, null));
		views.add(inflater.inflate(R.layout.bianhua, null));
		/* ��ʼ��Adapter */
		userPagerAdapter = new UserPagerAdapter(views, this);
		viewPager_user.setAdapter(userPagerAdapter);
	}

	/**
	 * ��ʼ�������г��ֵĲ���
	 */
	private void initLayout() {
		View firstView = views.get(0);// �õ���һ��ҳ��view
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

		View firstView2 = views.get(1);// �õ��ڶ���ҳ��view
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
					editText_user_search_text.setText("");// ��ձ༭��ʾ��
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

		/* ɾ��С��ť�������� */
		button_user_search_delete.setOnClickListener(myClickListener);
		/* ������������ */
		button_user_search_derma.setOnClickListener(myClickListener);
	//	button_user_search_helper.setOnClickListener(myClickListener);
	//	button_user_search_about.setOnClickListener(myClickListener);
		button_user_search_classify.setOnClickListener(myClickListener);

		/* �༭������ݼ��� */
		editText_user_search_text.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int arg1, int arg2,
					int arg3) {

				String inputStr = s.toString();
				/* �ж��Ƿ����ɾ��Сͼ�� */
				if (inputStr.equals("")) {
					button_user_search_delete.setVisibility(View.INVISIBLE);
					lin_user_search_other.setVisibility(View.VISIBLE);
				} else {
					button_user_search_delete.setVisibility(View.VISIBLE);
					lin_user_search_other.setVisibility(View.GONE);
				}

				listViewAdapter(inputStr);// ��ʾ�õ�������
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
		/* listView���� */
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
							Toast.makeText(BianhuaActivity.this, "û�ҵ�����ص��id",
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
		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);// �������Ƴ�Ч��
	}

//	public void goGuidePage() {
//		Intent intent = new Intent(BianhuaActivity.this, GuidePage.class);
//		BianhuaActivity.this.startActivity(intent);
//		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);// �������Ƴ�Ч��
//	}

	public void goClassifyPage() {
		Intent intent = new Intent(BianhuaActivity.this, ClassifyPage.class);
		BianhuaActivity.this.startActivity(intent);
		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);// �������Ƴ�Ч��
	}

	public void goAboutPage() {
		Intent intent = new Intent(BianhuaActivity.this, AboutPage.class);
		BianhuaActivity.this.startActivity(intent);
		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);// �������Ƴ�Ч��
	}

	class MyTask extends AsyncTask<String, Void, byte[]> {
		private Context context;
		private ProgressDialog pdDialog;

		public MyTask(Context context) {
			this.context = context;
			pdDialog = new ProgressDialog(context);
			pdDialog.setMessage("���ڼ����У����Ժ�ing...");
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
				Toast.makeText(context, "�޷����ӵ�����", Toast.LENGTH_LONG).show();
				return;
			}
			try {
				String JSONStr = new String(result, "UTF-8");
				Log.i(TAG, "==��ʼ����JSON�ļ�,JSONStr-->" + JSONStr);
				map = JSONHelper.parserJsonAll(JSONStr, "weatherinfo");
				Log.i(TAG, "==��ʼ�����ݻ��浽�ڴ���");
				userMap.put(getCityFatherId(), map);
				Log.i(TAG, "==��ʾ�������");
				showResult();
			} catch (Exception e) {
				Log.i(TAG, "==��ʾ���ս���쳣");
				e.printStackTrace();
			}
			pdDialog.dismiss();
		}

	}

	@SuppressWarnings("deprecation")
	public void showResult() {
		/* չʾδ�������������� */
		SimpleDateFormat formatterDate = new SimpleDateFormat("EEEE",
				Locale.CHINA);
		Date curDate = new Date(System.currentTimeMillis());

		for (int i = 0; i < dayCount; i++) {
			/* ������+1��Ϊ��һ��ѭ����׼�� */
			curDate.setDate(curDate.getDate() + 1);

			/*
			 * ��������
			 */
			TextView week = new TextView(this);
			week.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
			week.setGravity(Gravity.CENTER);// ���־���
			// weeks[i].setTextColor(getResources().getColor(R.color.black));//
			// ����������ɫ
			week.setPadding(0, 0, 0, 0);// �������������ҵı߾�
			week.setSingleLine(true);// ������һ����ʾ
			week.setText(formatterDate.format(curDate));

			/*
			 * ��������ͼ��
			 */
			ImageView img = new ImageView(this);
			img.setLayoutParams(new LinearLayout.LayoutParams(45,
					45));
			img.setBackgroundResource(getImageId("b1_"
					+ map.get("img" + (i * 2 + 3))));
			/*
			 * �¶�
			 */
			TextView temp = new TextView(this);
			temp.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
			temp.setGravity(Gravity.CENTER);// ���־���
			// weeks[i].setTextColor(getResources().getColor(R.color.black));//
			// ����������ɫ
			temp.setPadding(0, 0, 0, 0);// �������������ҵı߾�
			temp.setSingleLine(true);// ������һ����ʾ
			temp.setText(map.get("temp" + (i + 2)));

			/*
			 * ��������
			 */
			TextView weather = new TextView(this);
			weather.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
			weather.setGravity(Gravity.CENTER);// ���־���
			// weeks[i].setTextColor(getResources().getColor(R.color.black));//
			// ����������ɫ
			weather.setPadding(0, 0, 0, 0);// �������������ҵı߾�
			weather.setSingleLine(true);// ������һ����ʾ
			weather.setText(map.get("weather" + (i + 2)));

			/*
			 * ����
			 */
			TextView wind = new TextView(this);
			wind.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1.0f));
			wind.setGravity(Gravity.CENTER);// ���־���
			// weeks[i].setTextColor(getResources().getColor(R.color.black));//
			// ����������ɫ
			wind.setPadding(0, 0, 0, 0);// �������������ҵı߾�
			wind.setSingleLine(true);// ������һ����ʾ
			wind.setText(map.get("wind" + (i + 2)));

			/*
			 * ÿһ�첼��
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

			/* ��ӵ�������ʾ�Ĳ����� */
			linear_user_first_other.addView(linear);

		}
		Log.i(TAG, "==showResult()");
		String s1 = map.get("img1");
		Log.i(TAG, "s1==" + s1);
		Log.i(TAG, "imageView_user_imgOne==" + imageView_user_imgOne);
		Log.i(TAG, "getImageId(s1)==" + getImageId("b1_" + s1));
		imageView_user_imgOne.setImageResource(getImageId("b1_" + s1));// ���������ǰ���
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
		//String str = "����ָ����" + map.get("index_d");
		 String str = "����������״̬��������ѧ�Ļ����仯��Ƥ������ʱ�������ػ�����ױ";
		textView_user_index_d.setText(str);
		str = "������:" + map.get("index_uv");
		textView_user_index_uv.setText(str);
		str = "��ױ:" + map.get("index_xc");
		textView_user_index_xc.setText(str);
		str = "����:" + map.get("index_tr");
		textView_user_index_tr.setText(str);
		str = "����ָ��:" + map.get("index_co");
		textView_user_index_co.setText(str);
		str = "жױ:" + map.get("index_cl");
		textView_user_index_cl.setText(str);
		str = "��ˮ:" + map.get("index_ls");
		textView_user_index_ls.setText(str);
		str = "����:" + map.get("index_ag");
		textView_user_index_ag.setText(str);
	}

	public int getImageId(String id) {
		Log.i(TAG, "==getImageId()id-->" + id);
		Class<R.drawable> cls = R.drawable.class;
		try {
			return cls.getDeclaredField(id).getInt(null);
		} catch (Exception e) {
			Log.i(TAG, "==getImageId()�쳣");
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
			Log.i(TAG, "==getImageId()�쳣");
			e.printStackTrace();
			return R.drawable.bg_help3;
		}
	}
	/*
	 * �˵������ؼ���Ӧ(non-Javadoc)
	 * 
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Timer tExit = null;
			if (isExit == false) {
				isExit = true; // ׼���˳�
				Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
				tExit = new Timer();
				tExit.schedule(new TimerTask() {
					@Override
					public void run() {
						isExit = false; // ȡ���˳�
					}
				}, 2000); // ���2������û�а��·��ؼ�����������ʱ��ȡ�����ղ�ִ�е�����

			} else {
				this.finish();
				AppManager.getAppManager().AppExit(this);
				System.exit(0);
			}
		}
		return false;
	}

}


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
    /*�����ˮƽ������ҳ��*/
    private HorizontalScrollView scrollView_classify;
	/** ��ʾÿ���еĵ�ѡ��ť�� */
	private RadioGroup radioGroup_classify;
	/** �·��Ŀɺ����϶��Ŀؼ� */
	private ViewPager viewPager_classify_areas;
	/** ���������б��� */
	private LinearLayout lin_classify_provinces;
	/** ���������б� */
	private ListView listView_classify_provinces;
	/** �������ϵ��Ǹ���ť */
	private Button button_title_classify;

	/* �����Զ��� */
	/** �Զ�������ʡ�������� */
	private ClassifyListViewAdapter cListViewAdapter;

	/** ��ʾ������ʱ��ÿ��button�ĳ��� */
	int lenButton = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "==onCreate()");
		initClassify();
		getProvinces();
	}

	/**
	 * ��ȡʡ���б�����,��������������
	 */
	private void getProvinces() {
		Log.i(TAG, "==getProvinces()");
		if (listFatherProvinces == null) {
			/* ȥ������������������浽�����滺����ȥ */
			listFatherProvinces = PullParserHelper.getListProvinceByXml(this
					.getResources().getXml(R.xml.citys_weather));
		}
		/* չʾ���� */
		cListViewAdapter = new ClassifyListViewAdapter(this,
				listFatherProvinces);
		listView_classify_provinces.setAdapter(cListViewAdapter);
	}

	/**
	 * ��ʼ���������״̬
	 */
	private void initClassify() {
		Log.i(TAG, "==initClassify()");
		/* �Զ�������� */
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);// �����Զ���
		setContentView(R.layout.activity_classify_page);// ������ʾ����
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.bianhua_activity);// ���ñ���
		AppManager.getAppManager().addActivity(this);
		/* ��ʼ���ܿ����Ĳ��� */
		scrollView_classify = (HorizontalScrollView) findViewById(R.id.scrollView_classify);
		radioGroup_classify = (RadioGroup) findViewById(R.id.radioGroup_classify);
		viewPager_classify_areas = (ViewPager) findViewById(R.id.viewPager_classify_areas);
		lin_classify_provinces = (LinearLayout) findViewById(R.id.lin_classify_provinces);
		listView_classify_provinces = (ListView) findViewById(R.id.listView_classify_provinces);
		button_title_classify = (Button) findViewById(R.id.button_title_classify);
		/* ���ü��� */
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
	 * ����ʡid���õ���Ӧ�ĳ�������id
	 * 
	 * @param provinceId
	 */
	protected void getCity(String provinceId) {
		Log.i(TAG, "==getCity()");
		/* �õ����� */
		List<Map<String, String>> listCitys = mapFatherCitys.get(provinceId);
		if (listCitys == null) {
			/* ����ʡid�õ��ĸ����滺�����ݲ����ڣ����½���һ�£�Ȼ�󱣴浽�����滺����ȥ */
			listCitys = PullParserHelper.getListCityByXml(this.getResources()
					.getXml(R.xml.citys_weather), provinceId);
			mapFatherCitys.put(provinceId, listCitys);
		}
		Log.i(TAG, "listCitys==" + listCitys);
		getButtonLen(listCitys.size());

		/* ��������ӵ�ÿһ����ѡ��ť�� */
		radioGroup_classify.removeAllViews();
		List<View> myViews = new ArrayList<View>();// ����view
		for (int i = 0; i < listCitys.size(); i++) {
			RadioButton rButton = new RadioButton(this);
			rButton.setLayoutParams(new LinearLayout.LayoutParams(lenButton,
					LayoutParams.MATCH_PARENT));
			rButton.setTag(R.id.tag_id, listCitys.get(i).get("id"));// ����id
			rButton.setTag(R.id.tag_index, i);// ����λ��
			rButton.setText(listCitys.get(i).get("cityName"));
			rButton.setGravity(Gravity.CENTER);// ���־���
			rButton.setBackgroundResource(R.drawable.radiobtn_select);// ����״̬����
			rButton.setTextColor(getResources().getColor(R.color.white));
			rButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));// ȥ����ѡ��ťǰ��ĵ���ɫΪ��
			rButton.setPadding(0, 0, 0, 0);// �������������ҵı߾�
			rButton.setSingleLine(true);// ������һ����ʾ

			/* ����ѡ��ť���뵥ѡ��ť�������� */
			radioGroup_classify.addView(rButton);

			/* Ϊÿһ����ť������Ӧ��listView���� */
			ListView lView = getArea(rButton.getTag(R.id.tag_id).toString());

			myViews.add(lView);// ���ÿһ��view
		}
		/* ����ÿ��listView��viewPager�� */
		ClassifyAreaPagerAdapter cAreaPagerAdapter = new ClassifyAreaPagerAdapter(
				myViews, this);
		viewPager_classify_areas.setAdapter(cAreaPagerAdapter);
		radioGroup_classify.getChildAt(0).performClick();
		/* ���ü��� */
		radioGroup_classify
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						RadioButton rButton = (RadioButton) group
								.findViewById(checkedId);
						Log.i(TAG, "onCheckedChanged()==rButton-->" + rButton);
						int index = (Integer) rButton.getTag(R.id.tag_index);
						viewPager_classify_areas.setCurrentItem(index);
						/* ���µ�ǰ��ɫ����������ߵľ��� */
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
						/* ����ViewPager��ʱ��,���Ϸ���HorizontalScrollView�Զ��л� */
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
	 * �����ֻ��ߴ��ð�ť����
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
	 * �õ�����
	 * 
	 * @param cityId
	 * @param count
	 */
	private ListView getArea(String cityId) {
		/* ����ÿһ��listView,��Ϊ�䰲װ������ */
		ListView lView = new ListView(this);
		lView.setTag(cityId);
		lView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		lView.setDivider(null);// ���÷ָ���Ϊ��
		/* �õ�����Դ */
		List<Map<String, String>> listAreas = mapFatherCitys.get(cityId);
		if (listAreas == null) {
			/* ����ʡid�õ��ĸ����滺�����ݲ����ڣ����½���һ�£�Ȼ�󱣴浽�����滺����ȥ */
			listAreas = PullParserHelper.getListAreaByXml(this.getResources()
					.getXml(R.xml.citys_weather), cityId);
			mapFatherCitys.put(cityId, listAreas);
		}
		/* ������������Item�Ͷ�̬�����Ӧ��Ԫ�� */
		ClassifyAreaListAdapter adapter = new ClassifyAreaListAdapter(this,
				listAreas);
		/* ��������� */
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
					Toast.makeText(ChengshiActivity.this, "û�ҵ�����ص��id",
							Toast.LENGTH_LONG).show();
				}

			}
		});
		return lView;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);// �������Ƴ�Ч��
	}
}



package com.example.yu;

import com.weiwei.meilijia.base.Geomark;
import com.weiwei.meilijia.base.Rain;
import com.weiwei.meilijia.base.RainAnimotion;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class TesthandActivity extends Activity{
	static int width;
    int height;
    @Override
    protected void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.test_record_hand);

		DisplayMetrics dm = new DisplayMetrics();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels;
		Log.i("系统信息", "该设备的分辨是：" + width + "*" + height);
		Geomark.right = width - 35;
		Geomark.gapX = (width - 45) / 23;
		Rain.right = width - 42;// 17*24=408
		Rain.gapX = (width - 45) / 25;

		RainAnimotion.right = width - 42;// 17*24=408
		RainAnimotion.gapX = (width - 45) / 25;


		final LinearLayout ll1 = (LinearLayout) findViewById(R.id.linechart1);
		LinearLayout tl1 = (LinearLayout) findViewById(R.id.templl);

		final Geomark geomark1 = (Geomark) findViewById(R.id.geomark_view1);

		tl1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ll1.getVisibility() == 0) {
					ll1.setVisibility(View.GONE);
					geomark1.setVisibility(View.GONE);
				} else {
					ll1.setVisibility(View.VISIBLE);// 设置可见
					geomark1.setVisibility(View.VISIBLE);
				}

			}
		});
		final LinearLayout ll2 = (LinearLayout) findViewById(R.id.linechart2);
		LinearLayout tl2 = (LinearLayout) findViewById(R.id.windll);

		final Geomark geomark2 = (Geomark) findViewById(R.id.geomark_view2);

		tl2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ll2.getVisibility() == 0) {
					ll2.setVisibility(View.GONE);
					geomark2.setVisibility(View.GONE);
				} else {
					ll2.setVisibility(View.VISIBLE);// 设置可见
					geomark2.setVisibility(View.VISIBLE);
				}

			}
		});
		final LinearLayout ll3 = (LinearLayout) findViewById(R.id.linechart3);
		LinearLayout tl3 = (LinearLayout) findViewById(R.id.rainll);

		final RainAnimotion geomark3 = (RainAnimotion) findViewById(R.id.geomark_view3);

		tl3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ll3.getVisibility() == 0) {
					ll3.setVisibility(View.GONE);
					geomark3.setVisibility(View.GONE);
				} else {
					ll3.setVisibility(View.VISIBLE);// 设置可见
					geomark3.setVisibility(View.VISIBLE);
				}

			}
		});
    }
}


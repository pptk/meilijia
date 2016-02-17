package com.example.yu;
import com.weiwei.meilijia.base.Geomark;
import com.weiwei.meilijia.base.PinChart;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class TesthufupinActivity extends Activity {
	static int width;
	int height;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_record_hufupin);

		// sfv =(SurfaceView)findViewById(R.id.SurfaceView01);

		DisplayMetrics dm = new DisplayMetrics();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels;
		Log.i("系统信息", "该设备的分辨是：" + width + "*" + height);
		Geomark.right = width - 35;
		Geomark.gapX = (width - 45) / 23;

		
		final LinearLayout ll4 = (LinearLayout) findViewById(R.id.linechart4);
		LinearLayout tl4 = (LinearLayout) findViewById(R.id.wetll);
		final PinChart geomark4 = (PinChart) findViewById(R.id.geomark_view4);
		// final PinChart geomark4 = (PinChart)
		// findViewById(R.id.geomark_view4);

		tl4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// float[] init = {};
				if (ll4.getVisibility() == 0) {
					ll4.setVisibility(View.GONE);
					geomark4.setVisibility(View.GONE);
					for (int i = 0; i < PinChart.humidity.length; i++) {
						geomark4.mSweep[i] -= PinChart.humidity[i];
					}
				} else {
					ll4.setVisibility(View.VISIBLE);// 设置可见
					geomark4.setVisibility(View.VISIBLE);
				}

			}
		});
	}
}

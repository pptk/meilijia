package com.weiwei.meilijia.yi;

import java.math.BigDecimal;
import com.example.yu.R;
import android.R.integer;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button reset;
	private TextView position, brand, dowhat, start_tv, result, tv1, tv2;
	private TextView position_1, brand_1, dowhat_1, opinion;
	private EditText edtposition, edtbrand, edtdowhat;
	private PopupWindow mypopupwindow, resultPopupWindow;
	private LinearLayout linearlayout2;
	private Handler handler, ohandler;
	private int flag = 1;
	private String start_one = "2", start_two = "1";
	private int titleBarHeight;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hufup);
		initView();// ��ʼ��������
		initPopupwindow();// ����popupwindow
		initResultPopupWindow();

		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mypopupwindow.isShowing()) {
					mypopupwindow.dismiss();
				} else {
					mypopupwindow.showAtLocation(
							findViewById(R.id.linearlayout0), Gravity.CENTER,
							10, 10);
				}
			}
		});
		linearlayout2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��һ�ε��
				Log.i("flag��ֵ", String.valueOf(flag));
				if (flag == 1) {
					start_tv.setText("������......");
					new oneThread().start();// ��ʼ�̡߳���ʱ����
				}
				// �ڶ��ε��
				if (flag == 2) {
					start_tv.setText("������......");
					new twoThread().start();// ��ʼ�̡߳���ʱ����
				}
			}
		});
		ohandler = new Handler() {
			public void handleMessage(Message msg) {
				flag = flag + 1;
				start_tv.setText("�ٴβ���");
				start_one = RandClass.RandomInty(45, 30);
				result.setText(start_one + "%");// ��ʾ�����ˮ��
				tv1.setText(start_one);

				Toast.makeText(MainActivity.this, "ʹ�û���Ʒ֮ǰ���������Ŷ~", 3000)
						.show();
				super.handleMessage(msg);
			}
		};
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				flag = 1;
				start_two = RandClass.RandomInty(80, 45);
				result.setText(start_two + "%");// ��ʾ�����ˮ��
				tv2.setText(start_two);
				Float f = Float.parseFloat(start_two) - Float.parseFloat(start_one);
				BigDecimal b = new BigDecimal(f);  
				float f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				opinion.setText("����������ʾ��"
						+ brand.getText().toString()
						+ "Ϊ���ļ���������"
						+ String.valueOf(f1) + "%ˮ��.");
				resultPopupWindow.showAtLocation(
						findViewById(R.id.oo), Gravity.CENTER, 0,
						0);
				start_tv.setText("��ʼ����");
				super.handleMessage(msg);
			}
		};

	}

	// ��һ����ʱ�߳�
	private class oneThread extends Thread {
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ohandler.sendMessage(ohandler.obtainMessage());
		}
	}

	// �ڶ�����ʱ�߳�
	private class twoThread extends Thread {
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			handler.sendMessage(handler.obtainMessage());
		}
	}

	// �ڶ��β�����ɺ���ʾ��popupwindow
	private void initResultPopupWindow() {
		Screen screen = new Screen(this);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.activity_hufup_result, null);
		ImageView img= (ImageView)layout.findViewById(R.id.imagevie); 
		tv1 = (TextView) layout.findViewById(R.id.tv1);
		tv2 = (TextView) layout.findViewById(R.id.tv2);
		position_1 = (TextView) layout.findViewById(R.id.position_1);
		brand_1 = (TextView) layout.findViewById(R.id.brand_1);
		dowhat_1 = (TextView) layout.findViewById(R.id.dowhat_1);
		opinion = (TextView) layout.findViewById(R.id.opinion);

		resultPopupWindow = new PopupWindow(layout);
		resultPopupWindow.setFocusable(true);
		resultPopupWindow.setWidth(screen.getWidth());
		resultPopupWindow.setHeight(screen.getHeight());
		// ���ñ���
		// ColorDrawable color = new ColorDrawable(0x7DC0C0C0);
		// resultPopupWindow.setBackgroundDrawable(color);
		// ������ʾ����
		resultPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

		// mypopupwindow.setBackgroundDrawable(this.getResources().getDrawable(
		// R.drawable.pop_bg));
		resultPopupWindow.setOutsideTouchable(true);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				resultPopupWindow.dismiss();
			}
		});
	}

	// ���ð�ť��ʾ��popupwindow
	private void initPopupwindow() {
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.acitivity_hufup_pupupwindow,
				null);
		Button btn = (Button) layout.findViewById(R.id.btn);
		Button cancle = (Button) layout.findViewById(R.id.cancle);
		edtposition = (EditText) layout.findViewById(R.id.position_);
		edtbrand = (EditText) layout.findViewById(R.id.brand_);
		edtdowhat = (EditText) layout.findViewById(R.id.dowhat_);

		mypopupwindow = new PopupWindow(layout);
		mypopupwindow.setFocusable(true);
		mypopupwindow.setWidth(LayoutParams.MATCH_PARENT);
		mypopupwindow.setHeight(LayoutParams.MATCH_PARENT);
		// ���ñ���
		ColorDrawable color = new ColorDrawable(0x7DC0C0C0);
		mypopupwindow.setBackgroundDrawable(color);
		// ������ʾ����
		// mypopupwindow.setAnimationStyle(R.style.mypopwindow_anim_style);

		// mypopupwindow.setBackgroundDrawable(this.getResources().getDrawable(
		// R.drawable.pop_bg));
		mypopupwindow.setOutsideTouchable(true);

		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (edtposition.getText().toString().equals("")
						&& edtbrand.getText().toString().equals("")
						&& edtdowhat.getText().toString().equals("")) {
					Toast.makeText(MainActivity.this, "��Ϣ����Ϊ��Ŷ~",
							Toast.LENGTH_LONG).show();
				} else {
					position.setText(edtposition.getText().toString());
					brand.setText(edtbrand.getText().toString());
					dowhat.setText(edtdowhat.getText().toString());
					mypopupwindow.dismiss();
					position_1.setText(edtposition.getText().toString());
					brand_1.setText(edtbrand.getText().toString());
					dowhat_1.setText(edtdowhat.getText().toString());
				}
			}
		});
		cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mypopupwindow.dismiss();
			}
		});
	}

	private void initView() {
		reset = (Button) findViewById(R.id.reset);
		position = (TextView) findViewById(R.id.position);
		brand = (TextView) findViewById(R.id.brand);
		dowhat = (TextView) findViewById(R.id.dowhat);
		linearlayout2 = (LinearLayout) findViewById(R.id.linearlayout2);
		start_tv = (TextView) findViewById(R.id.start_tv);
		result = (TextView) findViewById(R.id.result);
	}
}
package com.weiwei.meilijia.text;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.example.yu.R;
import com.weiwei.meilijia.dao.ShuiYouDao;
import com.weiwei.meilijia.model.Tb_shuiyou;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
public class ShuiYouTest extends Activity implements OnClickListener {
	private LinearLayout btnTest, linearlayout;
	private TextView youzhi, shuifen, textView1, tis;
	private String y, s, ints, inty, str;
	private int j = 0;
	private ImageView imagevie;
	private String TAG;
	private PopupWindow mPopupWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pxh_shuiyou_test);
		intpopupwindow();
		initDatas();
	}
	private void intpopupwindow(){
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.tis_xml,null);
		mPopupWindow = new PopupWindow(view);
		mPopupWindow.setFocusable(true);//���Ե��
		mPopupWindow.setWidth(LayoutParams.WRAP_CONTENT);//��
		mPopupWindow.setHeight(LayoutParams.WRAP_CONTENT);//�ߡ�
		mPopupWindow.setBackgroundDrawable(this.getResources().getDrawable(  
                R.drawable.ic_launcher));// ���ñ���ͼƬ�������ڲ��������ã�Ҫͨ������������  
		mPopupWindow.setOutsideTouchable(true);// ����popupwindow�ⲿ��popupwindow��ʧ�����Ҫ�����popupwindowҪ�б���ͼƬ�ſ��Գɹ�������  
	}
	private void initDatas() {
		youzhi = (TextView) findViewById(R.id.youzhi);
		shuifen = (TextView) findViewById(R.id.shuifen);
		textView1 = (TextView) findViewById(R.id.textView1);
		LinearLayout record = (LinearLayout) findViewById(R.id.record);

		linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
		tis = (TextView) findViewById(R.id.tis);
		btnTest = (LinearLayout) findViewById(R.id.btnTest);
		imagevie = (ImageView) findViewById(R.id.imagevie);
		imagevie.setOnClickListener(this);
		btnTest.setOnClickListener(this);
		record.setOnClickListener(this);
		linearlayout.setOnClickListener(this);
		SimpleDateFormat formatter = new SimpleDateFormat("MM��dd�� HH:mm");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		str = formatter.format(curDate);
	}

	// �߳�
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		/**
		 * ��ʼ����
		 */
		case R.id.btnTest:
			textView1.setText("������...");
			new YThread().start();
			break;
		/**
		 * ���ذ�ť
		 */
		case R.id.imagevie:
			onBackPressed();

			break;
		case R.id.record:
			Intent intent = new Intent(ShuiYouTest.this, RecordActivity.class);
			startActivity(intent);
			break;
		case R.id.linearlayout:
			if(mPopupWindow.isShowing()){
				mPopupWindow.dismiss();
			}else{
				//��ʾ��linearlayout�ؼ��·�
//				mPopupWindow.showAsDropDown(linearlayout);
				//�ڸ�View�о�����ʾ
				View v1 = getWindow().getDecorView().findViewById(android.R.id.content);//��ȡ��ǰ��View
				mPopupWindow.showAtLocation(v1, Gravity.CENTER, 0, 0);
			}
			
			break;
		default:
			break;
		}
	}

	private Handler yHandler = new Handler() {
		public void handleMessage(Message msg) {
			String kk = RandomInty();
			String xx = RandomInts();
			youzhi.setText("�ͷ֣�" + kk + "%");
			shuifen.setText("ˮ�֣�" + xx+ "%");
			textView1.setText("��ʼ");
			ints = shuifen.getText().toString();
			inty = youzhi.getText().toString();
			// ����
			ShuiYouDao dao = new ShuiYouDao(ShuiYouTest.this);
			Tb_shuiyou model = new Tb_shuiyou(str, ints, inty);
			dao.add(model);

			linearlayout.setVisibility(View.VISIBLE);
			Float s = Float.parseFloat(xx);//ˮ
			Float y = Float.parseFloat(kk);//��
			if(s>40 && y<30){
				tis.setText("ˮ��ƽ��Ŷ~");
			}
			if(s>40 && y>30){
				tis.setText("����ƫ��~");
			}
			if(s<40 && y<30){
				tis.setText("Ƥ��ȱˮ~");
			}if(s <40 && y>30){
				tis.setText("~~~");
			}
		};
	};

	private class YThread extends Thread {
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			yHandler.sendMessage(yHandler.obtainMessage());
		}
	}

	// �����������֬
	private String RandomInty() {
		int scale = 2;// ��ʾ��λС��
		int roundingMode = 4;// ��������

		int max1 = 40, min1 = 10;// ���ø�����Χ
		Random random1 = new Random();// �����������

		float xx = random1.nextFloat();// ���һ��������
		BigDecimal bd = new BigDecimal((double) xx);
		bd = bd.setScale(scale, roundingMode);
		xx = bd.floatValue();

		float yz = random1.nextInt(max1) % (max1 - min1 + 1) + min1 + xx;
		y = String.valueOf(yz);

		return y;
	}

	// ���������ˮ��
	private String RandomInts() {
		int scale = 2;// ��ʾ��λС��
		int roundingMode = 4;// ��������
		int max2 = 75, min2 = 30;
		Random random2 = new Random();
		float xx = random2.nextFloat();// ���һ��������
		BigDecimal bd = new BigDecimal((double) xx);
		bd = bd.setScale(scale, roundingMode);
		xx = bd.floatValue();

		float sf = random2.nextInt(max2) % (max2 - min2 + 1) + min2 + xx;
		s = String.valueOf(sf);

		return s;
	}
}

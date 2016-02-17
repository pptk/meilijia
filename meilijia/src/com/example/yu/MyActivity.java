package com.example.yu;

import com.weiwei.meilijia.circle.RoundImageView;
import com.weiwei.meilijia.er.DataActivity;
import com.weiwei.meilijia.er.DatainfoActivity;
import com.weiwei.meilijia.er.LoginActivity;
import com.weiwei.meilijia.second.AboutxinxiActivity;









import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyActivity extends Activity {
	private TextView personal_title_right_tv, personal_title_left_tv,denglu;
	private RelativeLayout my_xiaoxi, my_sixin, my_pengyou, my_tiezhi,
			my_jifen, my_hongbao, my_shezhi,personal_header_rly;
	private RoundImageView head;
    public String result="";
    public String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		initView();
	}
	private void initView() {
		denglu=(TextView)findViewById(R.id.denglu);
		denglu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MyActivity.this,LoginActivity.class);
				startActivityForResult(intent,0x11);
				
			}
		});
		personal_title_right_tv = (TextView) findViewById(R.id.personal_title_right_tv);
		personal_title_left_tv = (TextView) findViewById(R.id.personal_title_left_tv);
		personal_header_rly = (RelativeLayout) findViewById(R.id.personal_header_rly);
		my_xiaoxi = (RelativeLayout) findViewById(R.id.my_xiaoxi);
		head=(RoundImageView)findViewById(R.id.head);
		my_sixin = (RelativeLayout) findViewById(R.id.my_sixin);
		my_pengyou = (RelativeLayout) findViewById(R.id.my_pengyou);
		my_tiezhi = (RelativeLayout) findViewById(R.id.my_tiezhi);
		my_jifen = (RelativeLayout) findViewById(R.id.my_jifen);
		my_hongbao = (RelativeLayout) findViewById(R.id.my_hongbao);
		my_shezhi = (RelativeLayout) findViewById(R.id.my_shezhi);
		MyOnclickListener mOnclickListener = new MyOnclickListener();
	//	denglu.setOnClickListner(mOnclickListener);
		personal_title_right_tv.setOnClickListener(mOnclickListener);
		personal_header_rly.setOnClickListener(mOnclickListener);
	//	personal_title_left_tv.setOnClickListener(mOnclickListener);
	//	my_xiaoxi.setOnClickListener(mOnclickListener);
	//	my_sixin.setOnClickListener(mOnclickListener);
	//	my_pengyou.setOnClickListener(mOnclickListener);
	//	my_tiezhi.setOnClickListener(mOnclickListener);
	//	my_jifen.setOnClickListener(mOnclickListener);
	//	my_hongbao.setOnClickListener(mOnclickListener);
	//	my_shezhi.setOnClickListener(mOnclickListener);
	}

	private class MyOnclickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int mID = v.getId();
			switch (mID) {
			//case R.id.denglu:
			//	Intent intent=new Intent(MyActivity.this,LoginActivity.class);
			//	startActivityForResult(intent,0x11);
			//	break;
			case R.id.personal_title_right_tv:
				Intent intent1 = new Intent(MyActivity.this, DataActivity.class);
				startActivity(intent1);
				break;
			// case R.id.personal_title_left_tv:
			case R.id.personal_header_rly:
				Intent intent2 = new Intent(MyActivity.this, DatainfoActivity.class);
				startActivity(intent2);
				break;
			default:
				break;
			}

		}
		
		

	}



	
public void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == 0x11 && resultCode == 0x11) { //判断是否为待处理的结果
		Bundle bundle = data.getExtras(); //获取传递的数据包
		result = bundle.getString("result");
		if(result != null){
			denglu.setText(result);
	    	denglu.setVisibility(View.VISIBLE);
	    	//tishi.setVisibility(View.GONE);
		}
		name=bundle.getString("username");//获取用户名
	    SharedPreferences sp=MyActivity.this.getSharedPreferences("actm",Context.MODE_PRIVATE);
	    //获取SharedPreferences
	    String uname=sp.getString(name,null);
        SharedPreferences.Editor editor=sp.edit();//获取editor引用
	    editor.putString("uname",name);//用户名放入editor中
	    editor.commit(); //提交editor
	}
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

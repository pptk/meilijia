package com.example.yu;
import com.weiwei.meilijia.er.FindXingzuoActivity;
import com.weiwei.meilijia.er.FindhuodongActivity;
import com.weiwei.meilijia.er.FindqiaomenActivity;
import com.weiwei.meilijia.er.QiaomenDetialActivity;
import com.weiwei.meilijia.second.FindtixinActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class FoundActivity extends Activity {
    private LinearLayout tixin,huodong,jiaocheng;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find);
		initView();
	}
	private void initView(){
		tixin=(LinearLayout)findViewById(R.id.tixin);
		MyOnclickListener mOnclickListener = new MyOnclickListener();
		tixin.setOnClickListener(mOnclickListener);
		huodong=(LinearLayout)findViewById(R.id.ll_activityss);
		huodong.setOnClickListener(mOnclickListener);
		jiaocheng=(LinearLayout)findViewById(R.id.jc_activitys);
		jiaocheng.setOnClickListener(mOnclickListener);
	}
	private class MyOnclickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int mID = v.getId();
			switch (mID) {
		      case R.id.tixin:
		       	 Intent intent=new Intent(FoundActivity.this,FindXingzuoActivity.class);
				 startActivity(intent);
				  break;
		      case R.id.ll_activityss:
			       	 Intent intent1=new Intent(FoundActivity.this,FindhuodongActivity.class);
					 startActivity(intent1);
					  break;
		      case R.id.jc_activitys:
		    	  Intent intent2=new Intent(FoundActivity.this,FindqiaomenActivity.class);
		    	  startActivity(intent2);
				   break;   
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

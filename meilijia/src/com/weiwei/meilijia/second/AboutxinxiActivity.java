package com.weiwei.meilijia.second;
import com.example.yu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class AboutxinxiActivity extends Activity{
      private RelativeLayout guanyumeijia;
      @Override
      protected void onCreate(Bundle savedInstanceState){
    	  super.onCreate(savedInstanceState);
    	  setContentView(R.layout.about_layout);
    	  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    	  initView();
      }
      private void initView(){
    	 guanyumeijia=(RelativeLayout)findViewById(R.id.guanyumeijia);
    	 MyOnclickListener mOnclickListener = new MyOnclickListener();
    	 guanyumeijia.setOnClickListener(mOnclickListener);
      }
      private class MyOnclickListener implements View.OnClickListener {

  		@Override
  		public void onClick(View v) {
  			// TODO Auto-generated method stub
  			int mID = v.getId();
  			switch (mID) {
  			case R.id.guanyumeijia:
  				Intent intent = new Intent(AboutxinxiActivity.this, AboutviewActivity.class);
  				startActivity(intent);
  				break;
  			// case R.id.personal_title_left_tv:

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



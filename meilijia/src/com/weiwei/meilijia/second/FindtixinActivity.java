package com.weiwei.meilijia.second;

import java.util.ArrayList;

import com.example.yu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class FindtixinActivity extends Activity {
	protected static final String StartActivity = null;
	TextView startButton;
    EditText minuteText;
    EditText secondText;
    int minute;
    int second;
    @Override
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);  
    	setContentView(R.layout.maina);
    	startButton=(TextView)findViewById(R.id.button_start);
    	minuteText=(EditText)findViewById(R.id.minute);
    	secondText=(EditText)findViewById(R.id.second);
    	startButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!minuteText.getText().toString().equals("")){
					minute=Integer.parseInt(minuteText.getText().toString());
				}
				if(!secondText.getText().toString().equals("")){
					second=Integer.parseInt(secondText.getText().toString());
				}
				if(minute!=0|| second !=0){
					System.out.println(minute+":"+second);
					ArrayList<Integer> list=new ArrayList<Integer>();
					list.add(minute);
					list.add(second);
					Toast.makeText(FindtixinActivity.this, "面膜时间设置成功", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(FindtixinActivity.this, com.weiwei.meilijia.base.StartActivity.class);
					intent.putIntegerArrayListExtra("times", list);  
				    startActivity(intent);  

				}
			}
    		
    	});
    }
    @Override  
        protected void onResume() {  
            // TODO Auto-generated method stub  
            minute = 0;  
            second = 0;  
            super.onResume();  
       }  
  
	

}

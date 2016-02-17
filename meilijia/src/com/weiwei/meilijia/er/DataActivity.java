package com.weiwei.meilijia.er;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import com.example.yu.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends Activity{
          
	private TextView nicheng,qianming,nianling,diqu,touxiang,nichengtext;
    private ImageView touxiang_image;
    private TextView tubiao1,tubiao2,tubiao3,tubiao4,tubiao5;
    private EditText qianmingtext,nianlingtext,diqutext;
    private Button input;
//    public AsynctaskImageLoad loader = new AsynctaskImageLoad();
    public Handler handler;
    public String result="";
    public String username;
    
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_info_activity);
		initView();
		SharedPreferences sp = getSharedPreferences("actm", MODE_PRIVATE);
		username = sp.getString("uname", "");
//		Intent intent=getIntent();
//		username=intent.getStringExtra("username");
		nichengtext.setText(username);
		touxiang_image.setImageResource(R.drawable.mm);

		
		input.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ("".equals(qianmingtext.getText().toString())||"".equals(nianlingtext.getText().toString())
						||"".equals(diqutext.getText().toString())) 
					{
					Toast.makeText(DataActivity.this, "请输入完整信息！！",
							Toast.LENGTH_LONG).show();
					return;
				}
				// 创建一个新的线程，用于发送并读取信息
				new Thread(new Runnable() {
					public void run() {
						send();
						Message m = handler.obtainMessage();
						handler.sendMessage(m);
					}

				}).start();
			}
			
		});
		handler = new Handler() {
			public void handleMessage(Message msg) {
				if(result!=null&&result!="fail")
				{
					Toast.makeText(DataActivity.this,result,Toast.LENGTH_SHORT).show();
				}
				finish();
				super.handleMessage(msg);
			}
		};
		
		
	}//oncreate
	
	
	private void initView(){
		nicheng=(TextView)findViewById(R.id.nicheng);
		qianming=(TextView)findViewById(R.id.qianming);
		nianling=(TextView)findViewById(R.id.nianling);
		diqu=(TextView)findViewById(R.id.address);
		touxiang=(TextView)findViewById(R.id.touxiang);
		nichengtext=(TextView)findViewById(R.id.nicheng_text);
		input=(Button)findViewById(R.id.input);
		touxiang_image=(ImageView)findViewById(R.id.acc_header_iv);
		tubiao1=(TextView)findViewById(R.id.account_info_info_he);
		tubiao2=(TextView)findViewById(R.id.account_info_info_nc);
		tubiao3=(TextView)findViewById(R.id.account_info_info_gxqm);
		tubiao4=(TextView)findViewById(R.id.account_info_info_age);
		tubiao5=(TextView)findViewById(R.id.account_info_info_address);
		qianmingtext=(EditText)findViewById(R.id.qianming_text);
		nianlingtext=(EditText)findViewById(R.id.nianling_text);
		diqutext=(EditText)findViewById(R.id.address_text);
	}
	
	
	
	

	private void send() {
		String target = Ipconfig.urlstr + "/ziliao.jsp"; // 要提交的目标地址
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target); // 创建HttpPost对象
		// 将要传递的参数保存到List集合中
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "send"));
		params.add(new BasicNameValuePair("username",username));
		params.add(new BasicNameValuePair("qianming", qianmingtext.getText().toString()));//个性签名
		params.add(new BasicNameValuePair("nianling", nianlingtext.getText()
				.toString())); // 年龄段
		params.add(new BasicNameValuePair("diqu",diqutext.getText().toString()));//地区
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse = httpclient.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				result = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
				Log.i("send", result);
				Log.i("142",String.valueOf(httpResponse.getStatusLine().getStatusCode()));
			} else {
				result = "fail";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // 输出异常信息
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // 输出异常信息
		} catch (IOException e) {
			e.printStackTrace(); // 输出异常信息
		}
		
	}	
	
	
}

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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{

	 private TextView title,username,password,space;
	 private EditText reg_username,reg_pwd;
	 private Button register;
	 private Handler handler;
	 private String result="";
	 
	 protected void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.register_activity);
		 
		 title=(TextView)findViewById(R.id.title);
		 username=(TextView)findViewById(R.id.username);
		 password=(TextView)findViewById(R.id.password);
		 space=(TextView)findViewById(R.id.space);
		 //输入框
		 reg_username=(EditText)findViewById(R.id.reg_username);
		 reg_pwd=(EditText)findViewById(R.id.reg_password);
		 //获取注册按钮
		 register=(Button)findViewById(R.id.reg_register);
		 register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if("".equals(reg_username.getText().toString()) || "".equals(reg_pwd.getText().toString())){
					Toast.makeText(RegisterActivity.this, "请输入意见内容再提交，谢谢^_^", Toast.LENGTH_LONG).show();
				}
				else{
					new Thread(new Runnable(){
						@Override
						public void run() {
							// TODO Auto-generated method stub
							send();
							Message m = handler.obtainMessage();
							handler.sendMessage(m);
						}
						
					}).start();
				}
			}
		});
		 
		 
		 handler = new Handler(){
             	public void handleMessage(Message msg){
		    	    if (result != null) {
			    		Intent intent = getIntent(); // 获取Intent对象
						Bundle bundle = new Bundle(); // 实例化传递的数据包
						bundle.putString("result", result);
						intent.putExtras(bundle); // 将数据包保存到intent中
						setResult(0x12, intent); // 设置返回的结果码，并返回调用该Activity的Activity
						finish();
		    	    }
					super.handleMessage(msg);	
		    	}
		    };
		 
	 }
	 
	 public void send(){
			String target =Ipconfig.urlstr+"/register.jsp";//要提交的目标地址
			HttpClient httpclient = new DefaultHttpClient();//创建HttpClient对象
			HttpPost httpRequest = new HttpPost(target);//创建HttpPost对象
			List<NameValuePair> params = new ArrayList<NameValuePair>();//将要传递的参数保存到params的List集合中
			
			params.add(new BasicNameValuePair("param","post"));
			params.add(new BasicNameValuePair("username",reg_username.getText().toString()));
			params.add(new BasicNameValuePair("pwd",reg_pwd.getText().toString()));
			try{
				httpRequest.setEntity(new UrlEncodedFormEntity(params,"utf-8"));//设置编码方式
				HttpResponse httpResponse = httpclient.execute(httpRequest);
				if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//如果请求成功
					result+=EntityUtils.toString(httpResponse.getEntity());
					
				}else{
					result="请求失败";
				}
			}catch(UnsupportedEncodingException e1){
				e1.printStackTrace();
			}catch(ClientProtocolException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
		

}

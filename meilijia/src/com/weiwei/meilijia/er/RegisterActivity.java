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
		 //�����
		 reg_username=(EditText)findViewById(R.id.reg_username);
		 reg_pwd=(EditText)findViewById(R.id.reg_password);
		 //��ȡע�ᰴť
		 register=(Button)findViewById(R.id.reg_register);
		 register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if("".equals(reg_username.getText().toString()) || "".equals(reg_pwd.getText().toString())){
					Toast.makeText(RegisterActivity.this, "����������������ύ��лл^_^", Toast.LENGTH_LONG).show();
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
			    		Intent intent = getIntent(); // ��ȡIntent����
						Bundle bundle = new Bundle(); // ʵ�������ݵ����ݰ�
						bundle.putString("result", result);
						intent.putExtras(bundle); // �����ݰ����浽intent��
						setResult(0x12, intent); // ���÷��صĽ���룬�����ص��ø�Activity��Activity
						finish();
		    	    }
					super.handleMessage(msg);	
		    	}
		    };
		 
	 }
	 
	 public void send(){
			String target =Ipconfig.urlstr+"/register.jsp";//Ҫ�ύ��Ŀ���ַ
			HttpClient httpclient = new DefaultHttpClient();//����HttpClient����
			HttpPost httpRequest = new HttpPost(target);//����HttpPost����
			List<NameValuePair> params = new ArrayList<NameValuePair>();//��Ҫ���ݵĲ������浽params��List������
			
			params.add(new BasicNameValuePair("param","post"));
			params.add(new BasicNameValuePair("username",reg_username.getText().toString()));
			params.add(new BasicNameValuePair("pwd",reg_pwd.getText().toString()));
			try{
				httpRequest.setEntity(new UrlEncodedFormEntity(params,"utf-8"));//���ñ��뷽ʽ
				HttpResponse httpResponse = httpclient.execute(httpRequest);
				if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//�������ɹ�
					result+=EntityUtils.toString(httpResponse.getEntity());
					
				}else{
					result="����ʧ��";
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

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.yu.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DatainfoActivity extends Activity{
    
	private TextView username,qianming,nianling,diqu;
	private TextView usernametext,qianmingtext,nianlingtext,diqutext;
	private TextView title;
	public String result="";
	public List<UserModle> list=null;
	public Handler handler;
	public String name;
	
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datainfo_layout);
		SharedPreferences sp = getSharedPreferences("actm", MODE_PRIVATE);
		name = sp.getString("uname", "");
		initView();
		initDatas();
	}//oncreate
	
	
	private void initDatas() {

		new Thread(new Runnable() {
			@Override
			   public void run() {
				// TODO Auto-generated method stub
				result = send();// �ӷ�������ȡ����
				list = jxJSON(result);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			   }




		      }).start();
		handler=new Handler(){
		   public void handleMessage(Message msg)	
		   {
			   if(list==null)
			   {
				   Toast.makeText(DatainfoActivity.this,"�����쳣",Toast.LENGTH_SHORT).show();
			   }else
			   {
				   UserModle modle=list.get(0);
				   usernametext.setText(name);
				   qianmingtext.setText(modle.getQianming());
				   nianlingtext.setText(modle.getNianling());
				   diqutext.setText(modle.getDiqu());
			   }
			   super.handleMessage(msg);
		   }
		  
		};
		
		
	}
/*
 * ����json����
 */

	protected List<UserModle> jxJSON(String userinfo) {
		List<UserModle> user=new ArrayList<UserModle>();
		if(userinfo.equals(""))
		{
			Log.i("weikong","û����������");
			return null;
		}
		try{
			Log.i("JSON != null",userinfo);
			JSONArray xx=new JSONArray(userinfo); //ת��JSON��ʽ
			JSONObject obj=xx.getJSONObject(0);
			String str1=obj.getString("qianming");
			String str2=obj.getString("nianling");
			String str3=obj.getString("diqu");
			UserModle frag=new UserModle(str1,str2,str3);
			user.add(frag);
			return user;
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}


	private void initView() {
		
		username=(TextView)findViewById(R.id.username);
		qianming=(TextView)findViewById(R.id.qianming);
		nianling=(TextView)findViewById(R.id.nianling);
		diqu=(TextView)findViewById(R.id.diqu);
		title=(TextView)findViewById(R.id.title);
		usernametext=(TextView)findViewById(R.id.reg_account);
		qianmingtext=(TextView)findViewById(R.id.reg_qianming);
		nianlingtext=(TextView)findViewById(R.id.reg_age);
		diqutext=(TextView)findViewById(R.id.reg_address);
	}
	
	
	
	
	
	private String send() {
		String x = null;
		String target=Ipconfig.urlstr+"/getdatainfo.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
     	params.add(new BasicNameValuePair("httpclient", "get"));
		params.add(new BasicNameValuePair("username",name));//�����û���
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse httpResponse =httpclient.execute(httpRequest); // ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // �������ɹ�
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // ��ȡ���ص��ַ���
				Log.i("send", x);
				} else {
				result = "fail";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // ����쳣��Ϣ
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		} catch (IOException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
		return x;

	}
	
}
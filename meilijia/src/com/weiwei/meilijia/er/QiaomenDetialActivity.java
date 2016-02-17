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
import org.json.JSONObject;

import com.example.yu.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QiaomenDetialActivity extends Activity{
  
	public TextView title,title_2,title_content,content,qiaomen_title,fangfa;
	public ImageView image;
	public TextView space, title_3;
	private List<QiaomenModle> list = null;
	private Intent mIntent;
	private String result;
	private Handler handler;
	public String qid;
	public AsynctaskImageLoad loader = new AsynctaskImageLoad();
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qiaomeninfo);
		initView();// ��ȡID
		Intent intent = getIntent();// ��ȡintent
		qid = intent.getStringExtra("q_id");
		list = new ArrayList<QiaomenModle>();
		initDatas();
	}//oncreate

	private void loadImage(String url,ImageView img){
		CallbackImpl callbackImpl = new CallbackImpl(img);
		Drawable cacheImage = loader.loadDrawable(url, callbackImpl);
		if(cacheImage != null){
			img.setImageDrawable(cacheImage);
		}
	}
	
	
	private void initView() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.title);
		title_2 = (TextView) findViewById(R.id.title_2);
		title_3 = (TextView) findViewById(R.id.title_3);
		space = (TextView) findViewById(R.id.space);
		//imagevie=(ImageView)findViewById(R.id.imagell);
	    
	}


	private void initDatas() {
		// TODO Auto-generated method stub
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
		
		handler = new Handler() {
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (list == null) {
					Toast.makeText(QiaomenDetialActivity.this, "�����쳣",
							Toast.LENGTH_LONG).show();
				} else {
					QiaomenModle modle = list.get(0);
					title_content = (TextView) findViewById(R.id.title_content);
					content = (TextView) findViewById(R.id.content);
					image=(ImageView)findViewById(R.id.image);
					qiaomen_title=(TextView)findViewById(R.id.qiaomen_title);
					fangfa=(TextView)findViewById(R.id.fangfa);
					
					title_content.setText(modle.getTitle());// ��ʾǰ100���ַ�
					content.setText(modle.getContent());
					qiaomen_title.setText(modle.getCtitle());
					fangfa.setText(modle.getFangfa());
			     	loadImage(modle.getImage(),image);
				}

				super.handleMessage(msg);
			}
		};
	}


	/*
	 * ����jsons���M
	 */
	private List<QiaomenModle> jxJSON(String result) {
		List<QiaomenModle> hd = new ArrayList<QiaomenModle>();
		if(result.equals(""))
		{
			Log.i("senddd","û�лظ�");
			return null;
		}
			try{
				JSONArray jsonarr = new JSONArray(result);
				for(int i = 0;i<jsonarr.length();i++){
					JSONObject obj = jsonarr.getJSONObject(0);
					String title= obj.getString("title");
					String content= obj.getString("content");
					String ctitle = obj.getString("ctitle");
					String fangfa=obj.getString("fangfa");
					String pic = obj.getString("image");
					QiaomenModle huo = new QiaomenModle(title,content,ctitle,fangfa,Ipconfig.urlstr+"/"+pic);
					hd.add(huo);
					return hd;
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return null;
	}

  	private String send() {
  		String x = null;
		String target = Ipconfig.urlstr + "/qiaomeninfo.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "get"));
		params.add(new BasicNameValuePair("q_id",qid));// �����û���
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse httpResponse = httpclient.execute(httpRequest); // ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // �������ɹ�
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // ��ȡ���ص��ַ���
				Log.i("send", x);
			} else {
				x = null;
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // ����쳣��Ϣ
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		} catch (IOException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
		return x;
	}//send
	
}

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

import com.example.changshi.ChangshiActivity;
import com.example.yu.FoundActivity;
import com.example.yu.IndexActivity;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HuodongDetialActivity extends Activity{

	public TextView title,title_2, starttime,stoptime,content,starttext,stoptext;
	public ImageView image;
	public TextView space, title_3;
	private List<HuodongModle> list = null;
	private Intent mIntent;
	private String result;
	private Handler handler;
	public String aid;
	public AsynctaskImageLoad loader = new AsynctaskImageLoad();
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.huodonginfo);
		initView();// 获取ID
		Intent intent = getIntent();// 获取intent
		aid = intent.getStringExtra("a_id");
		list = new ArrayList<HuodongModle>();
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
		starttime = (TextView) findViewById(R.id.starttime);
		stoptime = (TextView) findViewById(R.id.stoptime);
		space = (TextView) findViewById(R.id.space);
		//imagevie=(ImageView)findViewById(R.id.imagell);
	    
	}


	private void initDatas() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				result = send();// 从服务器获取数据
				list = jxJSON(result);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();
		
		handler = new Handler() {
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (list == null) {
					Toast.makeText(HuodongDetialActivity.this, "网络异常",
							Toast.LENGTH_LONG).show();
				} else {
					HuodongModle modle = list.get(0);
					starttext = (TextView) findViewById(R.id.starttext);
					stoptext = (TextView) findViewById(R.id.stoptext);
					content = (TextView) findViewById(R.id.content);
					image=(ImageView)findViewById(R.id.image);
					starttext.setText(modle.getStarttext());// 显示前100个字符
					stoptext.setText(modle.getStoptext());
					content.setText(modle.getContent());
//					Bitmap bit = BitmapFactory.decodeByteArray(modle.getImage(), 0, modle.getImage().length); //将图片流转化为bitmap类型 
//					image.setImageBitmap(bit);
					loadImage(modle.getImage(),image);
				}

				super.handleMessage(msg);
			}
		};
	}


	/*
	 * 解析jsons数組
	 */
	private List<HuodongModle> jxJSON(String result) {
		List<HuodongModle> hd = new ArrayList<HuodongModle>();
		if(result != null){
			try{
				JSONArray jsonarr = new JSONArray(result);
				for(int i = 0;i<jsonarr.length();i++){
					JSONObject obj = jsonarr.getJSONObject(i);
					String starttext= obj.getString("starttext");
					String stoptext= obj.getString("stoptext");
					String content = obj.getString("content");
					String pic = obj.getString("image");
//					byte[] pic=PageService.getImage(Ipconfig.urlstr+"/"+obj.getString("image"));
					HuodongModle huo = new HuodongModle(starttext,stoptext,content, Ipconfig.urlstr+"/"+pic);
					hd.add(huo);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else{
			Log.i("jxJSON", "没有获取到数据");
		}
		return hd;
	}

  	private String send() {
  		String x = null;
		String target = Ipconfig.urlstr + "/huodonginfo.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "get"));
		params.add(new BasicNameValuePair("a_id",aid));// 传递用户名
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse = httpclient.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
				Log.i("send", x);
			} else {
				x = null;
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // 输出异常信息
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // 输出异常信息
		} catch (IOException e) {
			e.printStackTrace(); // 输出异常信息
		}
		return x;
	}//send
	
  	
}

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
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ZixunDetialActivity extends Activity{
   
	public TextView title,username,showtime,content;
	public TextView tishi,ex_name,rep_time,rep_content;
	public TextView space1,space2,space3,space4,image_space1,image_space2;
	public ImageView image1,image2,image3;
	private List<ReplayModle> list=null;
	private String result;
	private Handler handler;
	public String cid;
	public AsynctaskImageLoad loader = new AsynctaskImageLoad();
	public String one="one";
	public String two="two";
	public String three="three";
	public String cimage1,cimage2,cimage3;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.replay_info);
		initView();//获取ID方法
		/*
		 *获取从上一个activity传递过来的数据
		 */
		Intent intent = getIntent();//获取intent
	    cid= intent.getStringExtra("c_id");
		String cname= intent.getStringExtra("c_name");
		String ctime= intent.getStringExtra("c_time");
		String ccontent= intent.getStringExtra("c_content");
	    cimage1= intent.getStringExtra("c_image1");
		cimage2= intent.getStringExtra("c_image2");
		cimage3= intent.getStringExtra("c_image3");
		Log.i("cname",cname);
		Log.i("image1",cimage1);
		Log.i("image2",cimage2);
		Log.i("image3",cimage3);
	//	String count= intent.getStringExtra("c_count");
	//	Log.i("image",count);
	   //将数据显示在控件中
		username.setText(cname);
		showtime.setText(ctime);
		content.setText(ccontent);
		loadImage(cimage1,image1);
		loadImage(cimage2,image2);
		loadImage(cimage3,image3);
		//根据传递的图片数，判断显示
//		if(cimage1!=null&&cimage2==null&&cimage3==null)
//		{
//			loadImage(cimage1,image1);
//		//	image2.setVisibility(View.GONE);
//		//	image3.setVisibility(View.GONE);
//		}
//		if(cimage1!=null&&cimage2!=null&&cimage3==null)
//		{
//			loadImage(cimage1,image1);
//			loadImage(cimage2,image2);
//		//	image3.setVisibility(View.GONE);
//		}
//		if(cimage1!=null&&cimage2!=null&&cimage3!=null)
//		{
//			loadImage(cimage1,image1);
//			loadImage(cimage2,image2);
//			loadImage(cimage3,image3);
//		}

		// 新建list
//		list = new ArrayList<ReplayModle>();
//
		initDatas();
		

	}
	private void loadImage(String url,ImageView img){
		CallbackImpl callbackImpl = new CallbackImpl(img);
		Drawable cacheImage = loader.loadDrawable(url, callbackImpl);
		if(cacheImage != null){
			img.setImageDrawable(cacheImage);
		}
	}
	
private void initDatas() {
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
	
	   handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (list==null) {
                image1.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent1 = new Intent();
						intent1.putExtra("log",one);
						intent1.putExtra("c_image1", cimage1);
						intent1.setClass(ZixunDetialActivity.this,BigImageActivity.class);
						startActivity(intent1);
						
					}
				});
		        image2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent2=new Intent();
						intent2.putExtra("log",two);
						intent2.putExtra("c_image2", cimage2);
						intent2.setClass(ZixunDetialActivity.this,BigImageActivity.class);
						startActivity(intent2);
						
					}
				});
		       image3.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent3=new Intent();
						intent3.putExtra("log",three);
						intent3.putExtra("c_image3", cimage3);
						intent3.setClass(ZixunDetialActivity.this,BigImageActivity.class);
						startActivity(intent3);
						
					}
				});
			} else {
				
                   image1.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent1 = new Intent();
						intent1.putExtra("log",one);
						intent1.putExtra("c_image1", cimage1);
						intent1.setClass(ZixunDetialActivity.this,BigImageActivity.class);
						startActivity(intent1);
						
					}
				});
		        image2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent2=new Intent();
						intent2.putExtra("log",two);
						intent2.putExtra("c_image2", cimage2);
						intent2.setClass(ZixunDetialActivity.this,BigImageActivity.class);
						startActivity(intent2);
						
					}
				});
		       image3.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent3=new Intent();
						intent3.putExtra("log",three);
						intent3.putExtra("c_image3", cimage3);
						intent3.setClass(ZixunDetialActivity.this,BigImageActivity.class);
						startActivity(intent3);
						
					}
				});
				
	
				
				    ReplayModle modle=list.get(0);
				    ex_name=(TextView)findViewById(R.id.expertname);
				    rep_time=(TextView)findViewById(R.id.replay_time);
				    rep_content=(TextView)findViewById(R.id.replay_content);
				    ex_name.setText(modle.getName());//显示前100个字符
					rep_time.setText(modle.getTime());
					rep_content.setText(modle.getContent());		        
				   }

			super.handleMessage(msg);
		}
	};
        
		
}

/*
 * 解析jsons数据
 */
	private List<ReplayModle> jxJSON(String replay){
		List<ReplayModle> rep = new ArrayList<ReplayModle>();
		if(replay.equals(""))
		{
			Log.i("senddd","没有回复");
			return null;
		}
		try{
		  	Log.i("JSON = null", replay);
		    JSONArray xx = new JSONArray(replay);// 转换成JSOn格式		
			JSONObject jsonobject = xx.getJSONObject(0);
			String str1 = jsonobject.getString("name");
			String str2 = jsonobject.getString("time");
			String str3 = jsonobject.getString("content");
			ReplayModle frag = new ReplayModle(str1,
					str3,str2);
			rep.add(frag);
			return rep;

		}catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
/*
 * 获取控件
 */	
	private void initView() {
		  title=(TextView)findViewById(R.id.title);
		  tishi=(TextView)findViewById(R.id.tishi);
		  username=(TextView)findViewById(R.id.username);
		  showtime=(TextView)findViewById(R.id.showtime);
		  content=(TextView)findViewById(R.id.content);
		  space1=(TextView)findViewById(R.id.space1);
		  space2=(TextView)findViewById(R.id.space2);
		  space3=(TextView)findViewById(R.id.space3);
		  space4=(TextView)findViewById(R.id.space4);
		  image1=(ImageView)findViewById(R.id.image1);
		  image2=(ImageView)findViewById(R.id.image2);
		  image3=(ImageView)findViewById(R.id.image3);
		  image_space1=(TextView)findViewById(R.id.image_space1);
		  image_space2=(TextView)findViewById(R.id.image_space2);
	}

	
/*
 * send方法
 */
	private String send() {
		// TODO Auto-generated method stub
		String x = null;
		String target=Ipconfig.urlstr+"/replayinfo.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
     	params.add(new BasicNameValuePair("httpclient", "get"));
		params.add(new BasicNameValuePair("c_id", cid));//传递用户名
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse =httpclient.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
				Log.i("send", x);
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
		return x;
	
	}
}

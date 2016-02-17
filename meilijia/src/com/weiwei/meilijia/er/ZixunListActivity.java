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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.yu.R;

//import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ZixunListActivity extends Activity{
     
	public Button input;
	private ListView lv;
	private List<ZixunModle> list;
	private String result;
	private Handler handler;
	public String username;
	private String log="";
	private String count="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_list);
		//获取用户名
		// SharedPreferences sp=getSharedPreferences("actm", MODE_PRIVATE);
	   //  username=sp.getString("uname", "");
		Bundle b=getIntent().getExtras();
		 username=b.getString("username");
		// 找到listview
		list = new ArrayList<ZixunModle>();// 新建list
		lv = (ListView)findViewById(R.id.listview);// 获取listview
		input=(Button)findViewById(R.id.input);
		input.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			   Intent intent2=new Intent(ZixunListActivity.this,ZixunActivity.class); 
	           startActivityForResult(intent2,0x13);
				
			}
		});
		
		initDatas();
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

		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				// 把Result转换成list
				if (result.equals("")) {
					Toast.makeText(ZixunListActivity.this, "网络异常", Toast.LENGTH_LONG)
							.show();
				} else {

					lv.setAdapter(new MyAdapter(ZixunListActivity.this, list));// 适配器
					lv.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							ZixunModle model = list.get(position);
							String cid= model.getCid();
							String cname=model.getName();
							String ctime=model.getTime();
							String ccontent=model.getContent();
							String cimage1= model.getImage1();
							String cimage2= model.getImage2();
							String cimage3= model.getImage3();
						//	String ccount=model.getCount();
							Intent intent = new Intent();
							intent.putExtra("c_id", cid);
							intent.putExtra("c_name", cname);
							intent.putExtra("c_time", ctime);
							intent.putExtra("c_content", ccontent);
							intent.putExtra("c_image1", cimage1);
							intent.putExtra("c_image2", cimage2);
							intent.putExtra("c_image3", cimage3);
						//	intent.putExtra("c_count", count);
							intent.setClass(ZixunListActivity.this,ZixunDetialActivity.class);
							startActivity(intent);
							Log.i("这是onItemClick", cid);
						}

					});
				}
				super.handleMessage(msg);

			}
		};
	}

	private List<ZixunModle> jxJSON(String info){
		List<ZixunModle> zi = new ArrayList<ZixunModle>();
		try{
		JSONArray xx = new JSONArray(info);// 转换成JSOn格式
		for (int i = 0; i < xx.length(); i++) {
			JSONObject jsonobject = xx.getJSONObject(i);
			String cid= jsonobject.getString("cid");
			String name = jsonobject.getString("name");
			String content = jsonobject.getString("content");
			String time = jsonobject.getString("time");
			String pic1 = jsonobject .getString("imagepath1");
			String pic2 = jsonobject .getString("imagepath2");
			String pic3 = jsonobject .getString("imagepath3");

		//	String count=jsonobject.getString("count");
			ZixunModle frag = new ZixunModle(cid, name,content,time,pic1,pic2,pic3);
			zi.add(frag);
		}
		}catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return zi;
	}

	

	
	private String send() {
		String x = null;
		String target=Ipconfig.urlstr+"/contentlist.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
     	params.add(new BasicNameValuePair("httpclient", "get"));
		params.add(new BasicNameValuePair("username", username));//传递用户名
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
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0x13&& resultCode == 0x13) { //判断是否为待处理的结果
			Bundle bundle = data.getExtras(); //获取传递的数据包
			log= bundle.getString("result");
			count=bundle.getString("count");
			if("".equals(log)){
			    Toast.makeText(ZixunListActivity.this, "发表失败", Toast.LENGTH_LONG).show();
			}else{
			    Toast.makeText(ZixunListActivity.this,log, Toast.LENGTH_LONG).show();
			}
		}
	}
	
}

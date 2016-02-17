package com.weiwei.meilijia.yi;

import java.util.ArrayList;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.example.yu.R;
public class Shoping extends Activity {
	private ListView lv;
	private List<ShopInfo> list;
	private Handler handler;
	private pxhAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pxh_shop_listview);
		lv = (ListView) findViewById(R.id.lv);// 找到listview
		list = new ArrayList<ShopInfo>();
		
		new Thread(new Runnable() {
			public void run() {
				Log.i("run(", "线程开始");
				String x = send();// 调用send()获取返回的字符串。
				list = jxJSON(x);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				adapter = new pxhAdapter(Shoping.this, list);
				lv.setAdapter(adapter);
				super.handleMessage(msg);
			}
		};
	}

	private List<ShopInfo> jxJSON(String result) {
		List<ShopInfo> ll = new ArrayList<ShopInfo>();
		if(result != null){
			try{
				JSONArray jsonarr = new JSONArray(result);
				for(int i = 0;i<jsonarr.length();i++){
					JSONObject obj = jsonarr.getJSONObject(i);
					int kk = obj.getInt("id");
					String str1 = obj.getString("name");
					String str2 = obj.getString("from");
					String str3 = obj.getString("introduce");
					String str4 = obj.getString("creaTime");
					String str5 = obj.getString("nowPrice");
					byte[] pic = PageService.getImage("http://211.149.197.221:8080/meilijia3/"+obj.getString("priture"));
					int k = obj.getInt("number");
					ShopInfo sa = new ShopInfo(kk, str1, str2, str3, str4, str5, k, pic);
					ll.add(sa);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else{
			Log.i("jxJSON", "没有获取到数据");
		}
		return ll;
	}

	private String send() {
		String str = null;
		String url ="http://211.149.197.221:8080/meilijia3/agoods.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		Log.i("send(", "try外面");
		HttpResponse response;
		try {
			Log.i("send(", "执行请求1");
			response = httpclient.execute(request);//执行请求
			Log.i("response", response.toString());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  //如果请求成功
				str = EntityUtils.toString(response.getEntity()).trim();
				Log.i("send(", str);
				return str;
			} else {
				str = "请求失败";
				Log.i("send(", "请求失败");
				return str;

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return str;

	}
}

package com.weiwei.meilijia.er;

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

import com.example.yu.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FindXingzuoActivity extends Activity {
	private ListView xing;
	private TextView title;
	private List<XingzuoModle> list;
	private Handler handler;
	private XingzuoAdapter adapter;
	private String x;
	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.xingzuolist);
		title=(TextView)findViewById(R.id.title);
		xing = (ListView) findViewById(R.id.xing);// 找到listview
		list = new ArrayList<XingzuoModle>();
		
		new Thread(new Runnable() {
			public void run() {
				Log.i("run(", "线程开始");
				x = send();// 调用send()获取返回的字符串。
				list = jxJSON(x);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if (x.equals("")) {
					Toast.makeText(FindXingzuoActivity.this, "网络异常", Toast.LENGTH_LONG)
							.show();
				} else {
				  adapter = new XingzuoAdapter(FindXingzuoActivity.this, list);
				  xing.setAdapter(adapter);
				  xing.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							XingzuoModle model = list.get(position);
							String xid= model.getX_id();
							Intent intent = new Intent();
							intent.putExtra("x_id", xid);
							intent.setClass(FindXingzuoActivity.this,XingzuoDetialActivity.class);
							startActivity(intent);
							Log.i("这是onItemClick", xid);
						}

					});
				}
				  super.handleMessage(msg);
			}
		};
	}
	private List<XingzuoModle> jxJSON(String result) {
		List<XingzuoModle> ll = new ArrayList<XingzuoModle>();
		if(result != null){
			try{
				JSONArray jsonarr = new JSONArray(result);
				for(int i = 0;i<jsonarr.length();i++){
					JSONObject obj = jsonarr.getJSONObject(i);
					String id = obj.getString("x_id");
					String image=obj.getString("x_image");
					String title=obj.getString("x_title");
//					String stoptext=obj.getString("stoptext");
     			
//					byte[] pic=PageService.getImage(Ipconfig.urlstr+"/"+obj.getString("image"));
					XingzuoModle sa = new XingzuoModle(id,Ipconfig.urlstr+"/"+image,title);
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
		String url =Ipconfig.urlstr+"/xingzuolist.jsp";
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
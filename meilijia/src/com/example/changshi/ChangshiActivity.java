package com.example.changshi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.yu.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

public class ChangshiActivity extends Activity{
     private ListView lv;
     private Handler handler;
     private List<ChangshiInfo> list;
     private ChangshiAdapter adapter;
     @Override
     protected void onCreate(Bundle savedInstanceState){
    	 super.onCreate(savedInstanceState);
    	 setContentView(R.layout.changshi); 
    	 list=new ArrayList<ChangshiInfo>();
    	 lv=(ListView)findViewById(R.id.lv);
    	 new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				String str=send();
				list=jxJSON(str);
				Log.i("Thread",list.toString());
				Message msg=handler.obtainMessage();
				handler.sendMessage(msg);
			}
    		 
    	 }).start();
    	 handler=new Handler(){
    		 @Override
    		 public void handleMessage(Message msg){
    			 adapter=new ChangshiAdapter(list,ChangshiActivity.this);
    		     lv.setAdapter(adapter);
    		     super.handleMessage(msg);
    		 }
    	 };
    	 
     }
     protected String send() {
 		// TODO 自动生成的方法存根
    	 String str=null;
    	 HttpClient httpclient=new DefaultHttpClient();
    	 HttpPost request=new HttpPost(
    			   "http://192.168.2.115:8080/meilijia3/achangshi.jsp");
    	 HttpResponse response = null;
 		try {
 			response = httpclient.execute(request);
 			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
 				str = EntityUtils.toString(response.getEntity()).trim();
 				Log.i("send", str);
 				return str;
 			} else {
 				Log.i("没有 访问成功", response.toString());
 				return null;
 			}
 		} catch (Exception e) {

 		}
 		return null;
 	}
	protected List<ChangshiInfo> jxJSON(String str) {
		// TODO 自动生成的方法存根
		List<ChangshiInfo> lst=new ArrayList<ChangshiInfo>();
		if(str.equals("")){
			Log.i("jxJSON", "数据为空哦~");
		} else {
			try {
				JSONArray jsArr = new JSONArray(str);
				for (int i = 0; i < jsArr.length(); i++) {
					JSONObject jsobj = jsArr.getJSONObject(i);
					String id=jsobj.getString("id");
					String name = jsobj.getString("name");
					String introduce = jsobj.getString("introduce");
					String priture = jsobj.getString("picture");
					lst.add(new ChangshiInfo(id, introduce, name, priture));
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return lst;
		}
		return null;
	}
}

 
package com.example.meifushow;

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

public class MainActivity extends Activity {
	private ListView lv;
	private Handler handler;
	private List<ShowInfo> list;
	private ShowAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meifushow);
		list = new ArrayList<ShowInfo>();
		lv = (ListView) findViewById(R.id.lv);
		new Thread(new Runnable() {
			@Override
			public void run() {
				String str = send();
				list = jxJSON(str);
				Log.i("Thread", list.toString());
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				adapter = new ShowAdapter(list, MainActivity.this);
				lv.setAdapter(adapter);
				super.handleMessage(msg);
			}
		};
	}

	private String send() {
		String str = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(
				"http://192.168.2.115:8080/meilijia3/ashow.jsp");
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

	private List<ShowInfo> jxJSON(String str) {
		List<ShowInfo> lst = new ArrayList<ShowInfo>();
		if (str.equals("")) {
			Log.i("jxJSON", "数据为空哦~");
		} else {
			try {
				JSONArray jsArr = new JSONArray(str);
				for (int i = 0; i < jsArr.length(); i++) {
					JSONObject jsobj = jsArr.getJSONObject(i);
					String username = jsobj.getString("username");
					String xinde = jsobj.getString("xinde");
					String creaTime = jsobj.getString("creaTime");
					String one = jsobj.getString("one");
					String two = jsobj.getString("two");
					String three = jsobj.getString("three");
					lst.add(new ShowInfo(username, one, two, three, xinde,
							creaTime));
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return lst;
		}
		return null;
	}
}

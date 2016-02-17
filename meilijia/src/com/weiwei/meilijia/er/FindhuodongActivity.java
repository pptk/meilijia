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

public class FindhuodongActivity extends Activity{
    
	private ListView lv;
	private TextView title;
	private List<ActivityModle> list;
	private Handler handler;
	private HuodongAdapter adapter;
	private String x;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.huodonglist);
		
		title=(TextView)findViewById(R.id.title);
		lv = (ListView) findViewById(R.id.lv);// �ҵ�listview
		list = new ArrayList<ActivityModle>();
		
		new Thread(new Runnable() {
			public void run() {
				Log.i("run(", "�߳̿�ʼ");
				x = send();// ����send()��ȡ���ص��ַ�����
				list = jxJSON(x);
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		}).start();
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if (x.equals("")) {
					Toast.makeText(FindhuodongActivity.this, "�����쳣", Toast.LENGTH_LONG)
							.show();
				} else {
				  adapter = new HuodongAdapter(FindhuodongActivity.this, list);
				  lv.setAdapter(adapter);
				  lv.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							ActivityModle model = list.get(position);
							String aid= model.getAid();
							Intent intent = new Intent();
							intent.putExtra("a_id", aid);
							intent.setClass(FindhuodongActivity.this,HuodongDetialActivity.class);
							startActivity(intent);
							Log.i("����onItemClick", aid);
						}

					});
				}
				  super.handleMessage(msg);
			}
		};
	}

	private List<ActivityModle> jxJSON(String result) {
		List<ActivityModle> ll = new ArrayList<ActivityModle>();
		if(result != null){
			try{
				JSONArray jsonarr = new JSONArray(result);
				for(int i = 0;i<jsonarr.length();i++){
					JSONObject obj = jsonarr.getJSONObject(i);
					String id = obj.getString("aid");
					String starttext=obj.getString("starttext");
					String stoptext=obj.getString("stoptext");
					String pic=obj.getString("image");
//					byte[] pic=PageService.getImage(Ipconfig.urlstr+"/"+obj.getString("image"));
					ActivityModle sa = new ActivityModle(id, starttext,stoptext,Ipconfig.urlstr+"/"+pic);
					ll.add(sa);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else{
			Log.i("jxJSON", "û�л�ȡ������");
		}
		return ll;
	}

	private String send() {
		String str = null;
		String url =Ipconfig.urlstr+"/huodonglist.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		Log.i("send(", "try����");
		HttpResponse response;
		try {
			Log.i("send(", "ִ������1");
			response = httpclient.execute(request);//ִ������
			Log.i("response", response.toString());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  //�������ɹ�
				str = EntityUtils.toString(response.getEntity()).trim();
				Log.i("send(", str);
				return str;
			} else {
				str = "����ʧ��";
				Log.i("send(", "����ʧ��");
				return str;

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return str;

	}
}

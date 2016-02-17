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

public class FindqiaomenActivity extends Activity{
	private ListView lv;
	private TextView title;
	private List<QiaomenModle> list;
	private Handler handler;
	private QiaomenAdapter adapter;
	private String x;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qiaomenlist);
		
		title=(TextView)findViewById(R.id.title);
		lv = (ListView) findViewById(R.id.lv);// �ҵ�listview
		list = new ArrayList<QiaomenModle>();
		
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
					Toast.makeText(FindqiaomenActivity.this, "�����쳣", Toast.LENGTH_LONG)
							.show();
				} else {
				  adapter = new QiaomenAdapter(FindqiaomenActivity.this, list);
				  lv.setAdapter(adapter);
				  lv.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							QiaomenModle model = list.get(position);
							String qid= model.getQid();
							Intent intent = new Intent();
							intent.putExtra("q_id", qid);
							intent.setClass(FindqiaomenActivity.this,QiaomenDetialActivity.class);
							startActivity(intent);
							Log.i("����onItemClick", qid);
						}

					});
				}
				  super.handleMessage(msg);
			}
		};
	}

	private List<QiaomenModle> jxJSON(String result) {
		List<QiaomenModle> ll = new ArrayList<QiaomenModle>();
		if(result != null){
			try{
				JSONArray jsonarr = new JSONArray(result);
				for(int i = 0;i<jsonarr.length();i++){
					JSONObject obj = jsonarr.getJSONObject(i);
					String id = obj.getString("qid");
//					String stoptext=obj.getString("stoptext");
     				String pic=obj.getString("image");
					String title=obj.getString("title");
//					byte[] pic=PageService.getImage(Ipconfig.urlstr+"/"+obj.getString("image"));
					QiaomenModle sa = new QiaomenModle(id,title,Ipconfig.urlstr+"/"+pic);
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
		String url =Ipconfig.urlstr+"/qiaomenlist.jsp";
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

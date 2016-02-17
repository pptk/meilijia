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

import com.example.yu.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowReplayActivity extends Activity{
      
	public TextView title,space1,space2;
	public EditText content;
	public Button rep_button;
	private String result="";
	private Handler handler;
	public String sid;
	public String name;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sreplay_input);
		//��ȡ�ϸ�activity���ݹ�����ֵ
		Intent intent=getIntent();
		sid=intent.getStringExtra("s_id");

		SharedPreferences sp = getSharedPreferences("actm", MODE_PRIVATE);
		name = sp.getString("uname", "");
		
		title=(TextView)findViewById(R.id.title);
		content=(EditText)findViewById(R.id.replay_miaosu); 
		space1=(TextView)findViewById(R.id.space);
		space2=(TextView)findViewById(R.id.space1);
		rep_button=(Button)findViewById(R.id.replay_button);
		rep_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if("".equals(content.getText().toString()))
				{
					Toast.makeText(ShowReplayActivity.this,"������Ҫ�ظ������ݣ���",Toast.LENGTH_LONG).show();
					return;
				}
	                				
				//����һ���µ��̣߳����ڷ��Ͳ���ȡ��Ϣ
				new Thread(new Runnable()
				{
					public void run()
					{
						send();
						Message m=handler.obtainMessage();
						handler.sendMessage(m);
					}

				}).start();
			}
		});
		
		 handler = new Handler(){
	          	public void handleMessage(Message msg){
                     
	          		Intent intent =getIntent(); // ��ȡIntent����
	          		Bundle bundle = new Bundle(); // ʵ�������ݵ����ݰ�
					bundle.putString("result", result);
					intent.putExtras(bundle);
					setResult(0x19, intent); // ���÷��صĽ���룬�����ص��ø�Activity��Activity
	                finish();

				    super.handleMessage(msg);	
			       }
			    };
		
	}
	

	public void send() {
		String target = Ipconfig.urlstr+"/sreplayinput.jsp"; // Ҫ�ύ��Ŀ���ַ
		HttpClient httpclient=new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target); // ����HttpPost����
		// ��Ҫ���ݵĲ������浽List������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "send"));
		params.add(new BasicNameValuePair("s_id", sid));//ר�ұ��
		params.add(new BasicNameValuePair("username",name));
		params.add(new BasicNameValuePair("miaosu",content.getText().toString())); //�ظ������Ϣ
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse httpResponse =httpclient.execute(httpRequest); // ִ��HttpClient����
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // �������ɹ�
				result = EntityUtils.toString(httpResponse.getEntity()).trim(); // ��ȡ���ص��ַ���
				Log.i("send", result);
				} else {
				result = "fail";
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace(); // ����쳣��Ϣ
		} catch (ClientProtocolException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		} catch (IOException e) {
			e.printStackTrace(); // ����쳣��Ϣ
		}
	}
}

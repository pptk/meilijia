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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button login,register;
	private TextView title;
	private EditText pwd;
    private String result = "";	//������ʾ�������
	private String resultTv="";//������
	private Handler handler; // ����һ��Handler����
    private String username;//�洢�û���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		login=(Button)findViewById(R.id.loginButton);
		register=(Button)findViewById(R.id.registerButton);
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username=((EditText)findViewById(R.id.login_username)).getText().toString();
				pwd=(EditText)findViewById(R.id.login_password);
				if("".equals(username) || "".equals(pwd.getText().toString()))
				{
        		    Toast.makeText(LoginActivity.this, "�˺Ż������벻��Ϊ��", Toast.LENGTH_LONG).show();// Toast��ʾ��õĽ��
                }else{ 
                	
					// ����һ�����̣߳�ʵ���û���¼
					new Thread(new Runnable() {
						public void run() {
							login();	//�û���¼
							Message m = handler.obtainMessage(); // ��ȡһ��Message
							handler.sendMessage(m); // ������Ϣ
						}
					}).start();// �����߳�
               }
			}
		});
		
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result!=null) {
		        	Intent intent = getIntent(); // ��ȡIntent����
					Bundle bundle = new Bundle(); // ʵ�������ݵ����ݰ�
					bundle.putString("username",username);
					bundle.putString("result", result);
					intent.putExtras(bundle); // �����ݰ����浽intent��
				    setResult(0x11, intent); // ���÷��صĽ���룬�����ص��ø�Activity��Activity	
				    finish();
				}
				super.handleMessage(msg);
			}
		};
		
	/*
	 * ע�����˻�����¼�
	 */
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
  }
public void login() {
			String target = Ipconfig.urlstr+"/login.jsp"; // Ҫ�ύ��Ŀ���ַ
			HttpClient httpclient=new DefaultHttpClient();
			HttpPost httpRequest = new HttpPost(target); // ����HttpPost����
			// ��Ҫ���ݵĲ������浽List������
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username",username)); // �û���
			params.add(new BasicNameValuePair("pwd", pwd.getText().toString())); // ����
			try {
				String TAG=null;
				httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
				HttpResponse httpResponse =httpclient.execute(httpRequest); // ִ��HttpClient����
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // �������ɹ�
					result += EntityUtils.toString(httpResponse.getEntity()).trim(); // ��ȡ���ص��ַ���
					Log.i(TAG, httpResponse.getStatusLine().getStatusCode()+"OK");
				} else {
					Log.i(TAG, httpResponse.getStatusLine().getStatusCode()+"");
					result = "����ʧ�ܣ�";
				}
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace(); // ����쳣��Ϣ
			} catch (ClientProtocolException e) {
				e.printStackTrace(); // ����쳣��Ϣ
			} catch (IOException e) {
				e.printStackTrace(); // ����쳣��Ϣ
			}
			
		}
	

protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == 0x12 && resultCode == 0x12) { //�ж��Ƿ�Ϊ������Ľ��
		Bundle bundle = data.getExtras(); //��ȡ���ݵ����ݰ�
		resultTv = bundle.getString("result");
		if("".equals(resultTv)){
		    Toast.makeText(LoginActivity.this, "�ύʧ��", Toast.LENGTH_LONG).show();
		}else{
		    Toast.makeText(LoginActivity.this, resultTv, Toast.LENGTH_LONG).show();
		}
	}

}



	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

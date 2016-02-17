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
    private String result = "";	//保存显示结果变量
	private String resultTv="";//重载用
	private Handler handler; // 声明一个Handler对象
    private String username;//存储用户名

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
        		    Toast.makeText(LoginActivity.this, "账号或者密码不能为空", Toast.LENGTH_LONG).show();// Toast显示获得的结果
                }else{ 
                	
					// 创建一个新线程，实现用户登录
					new Thread(new Runnable() {
						public void run() {
							login();	//用户登录
							Message m = handler.obtainMessage(); // 获取一个Message
							handler.sendMessage(m); // 发送消息
						}
					}).start();// 开启线程
               }
			}
		});
		
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (result!=null) {
		        	Intent intent = getIntent(); // 获取Intent对象
					Bundle bundle = new Bundle(); // 实例化传递的数据包
					bundle.putString("username",username);
					bundle.putString("result", result);
					intent.putExtras(bundle); // 将数据包保存到intent中
				    setResult(0x11, intent); // 设置返回的结果码，并返回调用该Activity的Activity	
				    finish();
				}
				super.handleMessage(msg);
			}
		};
		
	/*
	 * 注册新账户点击事件
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
			String target = Ipconfig.urlstr+"/login.jsp"; // 要提交的目标地址
			HttpClient httpclient=new DefaultHttpClient();
			HttpPost httpRequest = new HttpPost(target); // 创建HttpPost对象
			// 将要传递的参数保存到List集合中
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username",username)); // 用户名
			params.add(new BasicNameValuePair("pwd", pwd.getText().toString())); // 密码
			try {
				String TAG=null;
				httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
				HttpResponse httpResponse =httpclient.execute(httpRequest); // 执行HttpClient请求
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
					result += EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
					Log.i(TAG, httpResponse.getStatusLine().getStatusCode()+"OK");
				} else {
					Log.i(TAG, httpResponse.getStatusLine().getStatusCode()+"");
					result = "请求失败！";
				}
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace(); // 输出异常信息
			} catch (ClientProtocolException e) {
				e.printStackTrace(); // 输出异常信息
			} catch (IOException e) {
				e.printStackTrace(); // 输出异常信息
			}
			
		}
	

protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == 0x12 && resultCode == 0x12) { //判断是否为待处理的结果
		Bundle bundle = data.getExtras(); //获取传递的数据包
		resultTv = bundle.getString("result");
		if("".equals(resultTv)){
		    Toast.makeText(LoginActivity.this, "提交失败", Toast.LENGTH_LONG).show();
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

package com.weiwei.meilijia.er;

import java.io.File;
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
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class ZixunActivity extends Activity {

	public EditText zixuntext;
	public Button zixun_input;
	private String result = "";
	public String state="0";
	private Handler handler;
	public String username;
	private GridView gv;
	private GridViewAdapter adapter;
	private ArrayList<imageinfo> list;
	private String[] s;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zixun_info);
		SharedPreferences sp = getSharedPreferences("actm", MODE_PRIVATE);
		username = sp.getString("uname", "");
		/*
		 * 获取注册信息
		 */
		/*
		 * 获取咨询信息
		 */
		s = new String[3];// 指定空间。
		zixuntext = (EditText) findViewById(R.id.zixun_miaosu);
		gv = (GridView) findViewById(R.id.gv);
		list = new ArrayList<imageinfo>();
		adapter = new GridViewAdapter(list, this);
		gv.setAdapter(adapter);

		zixun_input = (Button) findViewById(R.id.zixun_input);
		zixun_input.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ("".equals(zixuntext.getText().toString())) {
					Toast.makeText(ZixunActivity.this, "请输入要咨询的内容！！",
							Toast.LENGTH_LONG).show();
					return;
				}
				// 创建一个新的线程，用于发送并读取信息
				new Thread(new Runnable() {
					public void run() {
						postimg();
						send();
						Message m = handler.obtainMessage();
						handler.sendMessage(m);
					}
				}).start();
			}
		});
		handler = new Handler() {
			public void handleMessage(Message msg) {
				Intent intent = getIntent(); // 获取Intent对象
				Bundle bundle = new Bundle(); // 实例化传递的数据包
				bundle.putString("result", result);
			//	bundle.putString("count", String.valueOf(list.size()));
				Log.i("resh",result);
				intent.putExtras(bundle); // 将数据包保存到intent中
				setResult(0x13, intent); // 设置返回的结果码，并返回调用该Activity的Activity
				finish();
				super.handleMessage(msg);
			}
		};
	}

	// 上传的同时、将图片名称生成新的地址保存到数组s中。
	public void postimg() {
		for (int i = 0; i < list.size(); i++) {
			String path = getAbsoluteImagePath(list.get(i).getImgUri());// uri变path
			File file = new File(path);// path变File
			UploadUtils.uploadFile(file, Ipconfig.urlstr
					+ "/servlet/UploadUtils");// 上传。
			int start = path.lastIndexOf("/");// 设置获取反斜杠后面的。
			String name = path.substring(start + 1);// 得到string去掉反斜杠。
			String sqlname = Ipconfig.urlstr + "/image/" + name;// 重新组成新的string、作为数据库保存地址。
			s[i] = sqlname;
		}
	}

	// 通过URI获取文件绝对路径
	protected String getAbsoluteImagePath(Uri uri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(uri, proj, // Which columns to return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)

		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();

		return cursor.getString(column_index);
	}

	class GridViewAdapter extends BaseAdapter {
		private ArrayList<imageinfo> list;
		private Context context;

		public GridViewAdapter(ArrayList<imageinfo> list, Context context) {
			super();
			this.list = list;
			this.context = context;
		}

		public void add(imageinfo info) {
			list.add(info);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size() + 1;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return getItem(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView img;
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.gv_item, null);
			img = (ImageView) convertView.findViewById(R.id.img);
			if (position == list.size()) {// 当position为最后一个时、下一个显示加
				img.setImageBitmap(BitmapFactory.decodeResource(getResources(),
						R.drawable.icon_addpic_unfocused));
			} else {
				img.setImageURI(list.get(position).getImgUri());
			}
			img.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent1 = new Intent();
					intent1.addCategory(Intent.CATEGORY_OPENABLE);
					intent1.setType("image/*");
					intent1.setAction(Intent.ACTION_GET_CONTENT);
					startActivityForResult(intent1, 1);
				}
			});
			return convertView;
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 1:
			Log.i("这是", "呵呵1");
			Uri uri = data.getData();
			imageinfo info = new imageinfo(uri);
			adapter.add(info);
			adapter.notifyDataSetChanged();
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void send() {
		String target = Ipconfig.urlstr + "/zixun.jsp"; // 要提交的目标地址
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target); // 创建HttpPost对象
		// 将要传递的参数保存到List集合中
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "send"));
		params.add(new BasicNameValuePair("username", username));//姓名
		params.add(new BasicNameValuePair("miaosu", zixuntext.getText()
				.toString())); // 咨询情况描述信息
	//	params.add(new BasicNameValuePair("state", state));//回复状态值
		for (int i = 0; i < list.size(); i++) {
			params.add(new BasicNameValuePair("name"+i, s[i]));//用循环将图片一张张传到服务器。
		}
		params.add(new BasicNameValuePair("count", String.valueOf(list.size())));//将图片张数上传到服务器。
		Log.i("count",String.valueOf(list.size()));
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse = httpclient.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				result = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
				Log.i("send", result);
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
	}
}
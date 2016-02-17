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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
public class ShowActivity extends Activity{
    
	public EditText meifutext;
	public Button meifu_input;
	private String result = "";
	private Handler handler;
	public String name;
	private GridView gv;
	private GridViewAdapter adapter;
	private ArrayList<imageinfo> list;
	private String[] s;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_input);
		
		SharedPreferences sp = getSharedPreferences("actm", MODE_PRIVATE);
		name = sp.getString("uname", "");
		/*
		 * ��ȡע����Ϣ
		 */
		/*
		 * ��ȡ��ѯ��Ϣ
		 */
		s = new String[3];// ָ���ռ䡣
		meifutext = (EditText) findViewById(R.id.meifu_miaosu);
		gv = (GridView) findViewById(R.id.gv);
		list = new ArrayList<imageinfo>();
		adapter = new GridViewAdapter(list, this);
		gv.setAdapter(adapter);

		meifu_input = (Button) findViewById(R.id.meifu_input);
		meifu_input.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ("".equals(meifutext.getText().toString())) {
					Toast.makeText(ShowActivity.this, "������Ҫ��ѯ�����ݣ���",
							Toast.LENGTH_LONG).show();
					return;
				}
				// ����һ���µ��̣߳����ڷ��Ͳ���ȡ��Ϣ
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
				Intent intent = getIntent(); // ��ȡIntent����
				Bundle bundle = new Bundle(); // ʵ�������ݵ����ݰ�
				bundle.putString("result", result);
			//	bundle.putString("count", String.valueOf(list.size()));
				Log.i("resh",result);
				intent.putExtras(bundle); // �����ݰ����浽intent��
				setResult(0x18, intent); // ���÷��صĽ���룬�����ص��ø�Activity��Activity
				finish();
				super.handleMessage(msg);
			}
		};
	}

	// �ϴ���ͬʱ����ͼƬ���������µĵ�ַ���浽����s�С�
	public void postimg() {
		for (int i = 0; i < list.size(); i++) {
			String path = getAbsoluteImagePath(list.get(i).getImgUri());// uri��path
			File file = new File(path);// path��File
			UploadUtils.uploadFile(file, Ipconfig.urlstr
					+ "/servlet/UploadUtils");// �ϴ���
			int start = path.lastIndexOf("/");// ���û�ȡ��б�ܺ���ġ�
			String name = path.substring(start + 1);// �õ�stringȥ����б�ܡ�
			String sqlname = Ipconfig.urlstr + "/image/" + name;// ��������µ�string����Ϊ���ݿⱣ���ַ��
			s[i] = sqlname;
		}
	}

	// ͨ��URI��ȡ�ļ�����·��
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
			if (position == list.size()) {// ��positionΪ���һ��ʱ����һ����ʾ��
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
			Log.i("����", "�Ǻ�1");
			Uri uri = data.getData();
			imageinfo info = new imageinfo(uri);
			adapter.add(info);
			adapter.notifyDataSetChanged();
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void send() {
		String target = Ipconfig.urlstr + "/show.jsp"; // Ҫ�ύ��Ŀ���ַ
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target); // ����HttpPost����
		// ��Ҫ���ݵĲ������浽List������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("httpclient", "send"));
		params.add(new BasicNameValuePair("username", name));//����
		params.add(new BasicNameValuePair("miaosu", meifutext.getText()
				.toString())); // ��ѯ���������Ϣ
	//	params.add(new BasicNameValuePair("state", state));//�ظ�״ֵ̬
		for (int i = 0; i < list.size(); i++) {
			params.add(new BasicNameValuePair("name"+i, s[i]));//��ѭ����ͼƬһ���Ŵ�����������
		}
		params.add(new BasicNameValuePair("count", String.valueOf(list.size())));//��ͼƬ�����ϴ�����������
		Log.i("count",String.valueOf(list.size()));
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // ���ñ��뷽ʽ
			HttpResponse httpResponse = httpclient.execute(httpRequest); // ִ��HttpClient����
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

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.yu.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowDetialActivity extends Activity{
    
	public TextView title,username,showtime,content;
	public TextView tishi,ex_name,rep_time,rep_content;
	public TextView space1,space2,space3,space4,image_space1,image_space2;
	public ImageView image1,image2,image3;
	public ListView lv;
	public Button input;
	private List<SreplayModle> list=null;
	private String result;
	private Handler handler;
	public String sid;
	public AsynctaskImageLoad loader = new AsynctaskImageLoad();
	public String one="one";
	public String two="two";
	public String three="three";
	public String simage1,simage2,simage3;
    private String log="";
    private SreplayAdapter adapter;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_info);
		initView();//获取ID方	
		
		/*
		 *获取从上一个activity传递过来的数据
		 */
		Intent intent = getIntent();//获取intent
	    sid= intent.getStringExtra("s_id");
		String sname= intent.getStringExtra("s_name");
		String stime= intent.getStringExtra("s_time");
		String scontent= intent.getStringExtra("s_content");
	    simage1= intent.getStringExtra("s_image1");
		simage2= intent.getStringExtra("s_image2");
		simage3= intent.getStringExtra("s_image3");
		Log.i("sname",sname);
		Log.i("image1",simage1);
		Log.i("image2",simage2);
		Log.i("image3",simage3);
	//	String count= intent.getStringExtra("c_count");
	//	Log.i("image",count);
	   //将数据显示在控件中
		username.setText(sname);
		showtime.setText(stime);
		content.setText(scontent);
		loadImage(simage1,image1);
		loadImage(simage2,image2);
		loadImage(simage3,image3);
        
		input.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent2=new Intent(ShowDetialActivity.this,ShowReplayActivity.class);
				intent2.putExtra("s_id",sid);
				startActivityForResult(intent2,0x19);
			}
		});

		// 新建list
		list = new ArrayList<SreplayModle>();

		initDatas();
		

	}
	
	
	private void loadImage(String url,ImageView img){
		CallbackImpl callbackImpl = new CallbackImpl(img);
		Drawable cacheImage = loader.loadDrawable(url, callbackImpl);
		if(cacheImage != null){
			img.setImageDrawable(cacheImage);
		}
	}
	
private void initDatas() {
	   new Thread(new Runnable() {
		@Override
		   public void run() {
			// TODO Auto-generated method stub
			result = send();// 从服务器获取数据
			list = jxJSON(result);
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		   }


	      }).start();
	
	   handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (result==null) {
                image1.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent1 = new Intent();
						intent1.putExtra("log",one);
						intent1.putExtra("s_image1", simage1);
						intent1.setClass(ShowDetialActivity.this,BigImageActivity2.class);
						startActivity(intent1);
						
					}
				});
		        image2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent2=new Intent();
						intent2.putExtra("log",two);
						intent2.putExtra("s_image2", simage2);
						intent2.setClass(ShowDetialActivity.this,BigImageActivity2.class);
						startActivity(intent2);
						
					}
				});
		       image3.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent3=new Intent();
						intent3.putExtra("log",three);
						intent3.putExtra("s_image3", simage3);
						intent3.setClass(ShowDetialActivity.this,BigImageActivity2.class);
						startActivity(intent3);
						
					}
				});
			} else {
				
				
				
				
                   image1.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent1 = new Intent();
						intent1.putExtra("log",one);
						intent1.putExtra("s_image1", simage1);
						intent1.setClass(ShowDetialActivity.this,BigImageActivity2.class);
						startActivity(intent1);
						
					}
				});
		        image2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent2=new Intent();
						intent2.putExtra("log",two);
						intent2.putExtra("s_image2", simage2);
						intent2.setClass(ShowDetialActivity.this,BigImageActivity2.class);
						startActivity(intent2);
						
					}
				});
		       image3.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent3=new Intent();
						intent3.putExtra("log",three);
						intent3.putExtra("s_image3", simage3);
						intent3.setClass(ShowDetialActivity.this,BigImageActivity2.class);
						startActivity(intent3);
						
					}
				});
				
		        lv.setAdapter(new SreplayAdapter(ShowDetialActivity.this, list));// 适配器
				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
		       
				        SreplayModle modle=list.get(position);
				        String srid= modle.getSrid();
						String srname=modle.getSrname();
						String srtime=modle.getSrtime();
						String srcontent=modle.getSrcontent(); 
		       
				   }
				});
			}
			super.handleMessage(msg);
		}
	};
        
		
}

/*
 * 解析jsons数据
 */
	private List<SreplayModle> jxJSON(String replay){
		List<SreplayModle> srep = new ArrayList<SreplayModle>();
		if (replay.equals("")) {
			Log.i("jxJSON", "数据为空哦~");
		} else {
		   try{
		  	Log.i("JSON = null", replay);
		  	JSONArray xx = new JSONArray(replay);// 转换成JSOn格式	
		  	for (int i = 0; i < xx.length();i++)
		  	{
			JSONObject jsonobject = xx.getJSONObject(i);
			String srname = jsonobject.getString("srname");
			String srtime = jsonobject.getString("srtime");
			String srcontent = jsonobject.getString("srcontent");
			SreplayModle frag = new SreplayModle(
					srname,srtime,srcontent);
			srep.add(frag);
		  	}
		}catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		   return srep;
		}
		return null;
	}
	
/*
 * 获取控件
 */	
	private void initView() {
		  title=(TextView)findViewById(R.id.title);
		  tishi=(TextView)findViewById(R.id.tishi);
		  username=(TextView)findViewById(R.id.username);
		  showtime=(TextView)findViewById(R.id.showtime);
		  content=(TextView)findViewById(R.id.content);
		  space1=(TextView)findViewById(R.id.space1);
		  space2=(TextView)findViewById(R.id.space2);
		  space3=(TextView)findViewById(R.id.space3);
		  space4=(TextView)findViewById(R.id.space4);
		  image1=(ImageView)findViewById(R.id.image1);
		  image2=(ImageView)findViewById(R.id.image2);
		  image3=(ImageView)findViewById(R.id.image3);
		  image_space1=(TextView)findViewById(R.id.image_space1);
		  image_space2=(TextView)findViewById(R.id.image_space2);
		  lv=(ListView)findViewById(R.id.lv);
		  input=(Button)findViewById(R.id.pinglun_input);
	}

	
/*
 * send方法
 */
	private String send() {
		// TODO Auto-generated method stub
		String x = null;
		String target=Ipconfig.urlstr+"/sreplayinfo.jsp";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequest = new HttpPost(target);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
     	params.add(new BasicNameValuePair("httpclient", "get"));
		params.add(new BasicNameValuePair("s_id", sid));//传递用户名
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 设置编码方式
			HttpResponse httpResponse =httpclient.execute(httpRequest); // 执行HttpClient请求
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
				x = EntityUtils.toString(httpResponse.getEntity()).trim(); // 获取返回的字符串
				Log.i("send", x);
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
		return x;
	
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0x19&& resultCode == 0x19) { //判断是否为待处理的结果
			Bundle bundle = data.getExtras(); //获取传递的数据包
			log= bundle.getString("result");
			if("".equals(log)){
			    Toast.makeText(ShowDetialActivity.this, "评论失败", Toast.LENGTH_LONG).show();
			}else{
			    Toast.makeText(ShowDetialActivity.this,log, Toast.LENGTH_LONG).show();
			}
		}
	}
	
}

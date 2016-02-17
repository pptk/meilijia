package com.weiwei.meilijia.er;

import java.util.ArrayList;
import java.util.List;

import com.example.yu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class HuodongAdapter extends BaseAdapter{
	private Context context;
	private List<ActivityModle> list = new ArrayList<ActivityModle>();
	public AsynctaskImageLoad loader = new AsynctaskImageLoad();
	
	public HuodongAdapter(Context context, List<ActivityModle> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		//加载布局管理器
		ActivityModle model = list.get(position);
		
			//将xml布局转换为view对象
			convertView = inflater.inflate(R.layout.huodonglist_item,
					null);
			//利用view对象，找到布局中的组件
	    	TextView showid = (TextView) convertView.findViewById(R.id.showid);
			ImageView picture = (ImageView)convertView.findViewById(R.id.image);
		    TextView starttime=(TextView) convertView.findViewById(R.id.starttime);
		    TextView stoptime=(TextView) convertView.findViewById(R.id.stoptime);
		    TextView starttext=(TextView) convertView.findViewById(R.id.starttext);
		    TextView stoptext=(TextView) convertView.findViewById(R.id.stoptext);
			showid.setText(model.getAid());
			starttext.setText(model.getStarttext());
			stoptext.setText(model.getStoptext());
//			Bitmap bit = BitmapFactory.decodeByteArray(model.getImage(), 0, model.getImage().length); //将图片流转化为bitmap类型 
//			picture.setImageBitmap(bit);
//			Log.i("url",model.getImage());
			loadImage(model.getImage(),picture);
			return convertView;
		}
	
	private void loadImage(String url,ImageView img){
		CallbackImpl callbackImpl = new CallbackImpl(img);
		Drawable cacheImage = loader.loadDrawable(url, callbackImpl);
		if(cacheImage != null){
			img.setImageDrawable(cacheImage);
		}
	}
}
